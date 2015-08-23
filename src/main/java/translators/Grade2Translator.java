package translators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import rules.Rule;
import tables.Grade1Map;
import tables.Grade2Abbreviation;
import tables.Trie;
import utils.grade2.Constants;
import utils.grade2.Tashkeel;
import utils.grade2.WordIterator;

import java.util.Iterator;

/**
 * Created by hosainfathelbab on 4/4/15.
 */
@Repository(value = "grade2Translator")
public class Grade2Translator extends Translator {

    @Autowired
    private Translator grade1Translator;

    @Override
    public String translate(String input) {

        StringBuffer sb = new StringBuffer();

        Iterator<String> wordIterator = new WordIterator(input);
        while (wordIterator.hasNext()) {
            String word = wordIterator.next();
            if (word.endsWith("ما") || word.endsWith("لم")) {
                if (wordIterator.hasNext()) {
                    word += wordIterator.next();
                    if (wordIterator.hasNext()) {
                        word += wordIterator.next();
                    }
                }
            }
            sb.append(translateWord(word));
        }

        return sb.toString();
    }

    @Override
    public TranslationResult translate(String input, Tashkeel tashkeel) {

        TranslationResult translationResult = new TranslationResult(tashkeel);

        Iterator<String> wordIterator = new WordIterator(input);
        while (wordIterator.hasNext()) {
            String word = wordIterator.next();
            if (word.endsWith("ما") || word.endsWith("لم")) {
                if (wordIterator.hasNext()) {
                    word += wordIterator.next();
                    if (wordIterator.hasNext()) {
                        word += wordIterator.next();
                    }
                }
            }
            translationResult.append(translateWordWithTashkeel(word, tashkeel));
        }

        return translationResult;
    }

    @Deprecated
    private String translateWord(String word) {
        StringBuffer sb = new StringBuffer();
        int index = 0;
        while (index < word.length()) {

            if (isNumber(word.charAt(index))) {
                sb.append("⠼");
                while (index < word.length() && isNumber(word.charAt(index))) {
                    sb.append(Grade1Map.table.get(word.charAt(index++)));
                }
                continue;
            }

            Grade2Abbreviation abbreviation = Trie.getInstance().getLongestMatchAbbreviation(word, index);
            if (abbreviation != null) {
                boolean success = true;
                if (abbreviation.getRules().size() > 0) {
                    success = false;
                    for (Rule rule : abbreviation.getRules()) {
                        if (rule.isValid(word, abbreviation.getWord(), index, sb.toString())) {
                            success = true;
                            break;
                        }
                    }
                }
                if (success) {
                    if (abbreviation.getWord().length() > 2 && (index > 0 || index + abbreviation.getWord().length() < word.length()) && !abbreviation.getWord().equals("كان")) {
                        sb.append("⠤");
                    }
                    sb.append(abbreviation.getSymbol());

                    index = index + abbreviation.getWord().length();
                    continue;
                }
            }
            sb.append(grade1Translator.translate(word.charAt(index) + ""));
            index++;
        }
        return sb.toString();
    }

    private TranslationResult translateWordWithTashkeel(String word, Tashkeel tashkeel) {
        TranslationResult tr = new TranslationResult(tashkeel);
        int index = 0;
        while (index < word.length()) {

            if (isNumber(word.charAt(index))) {
                tr.append("⠼");
                while (index < word.length() && isNumber(word.charAt(index))) {
                    tr.append(Grade1Map.table.get(word.charAt(index++)));
                }
                continue;
            }

            Grade2Abbreviation abbreviation = Trie.getInstance().getLongestMatchAbbreviationWithTashkeel(word, index);
            if (abbreviation != null) {
                String abbreviatedWordWithTashkeel = extractAbbreviationWithTashkeel(abbreviation, word, index);
                boolean success = true;
                if (abbreviation.getRules().size() > 0) {
                    success = false;
                    for (Rule rule : abbreviation.getRules()) {
                        if (rule.isValid(word, abbreviatedWordWithTashkeel, index, tr.toString())) {
                            success = true;
                            break;
                        }
                    }
                }
                if (success) {
                    if (abbreviation.getWord().length() > 2 && (index > 0 || index + abbreviatedWordWithTashkeel.length() < word.length()) && !abbreviation.getWord().equals("كان")) {
                        tr.append("⠤");
                    }
                    tr.append(abbreviation.getSymbol());

                    index = index + abbreviatedWordWithTashkeel.length();
                    continue;
                }
            }
            translateAndAppendSingleChar(word.charAt(index), tr);
            index++;
        }
        return tr;
    }

    private boolean isNumber(char c) {
        if ((c >= '0' && c <= '9') || (c >= '٠' && c <= '٩')) {
            return true;
        } else {
            return false;
        }
    }

    private void translateAndAppendSingleChar (char c , TranslationResult tr) {
        if (Grade1Map.table.containsKey(c)) {
            if (Constants.tashkeel.contains(c)) {
                tr.appendTashkeel(Grade1Map.table.get(c));
            } else {
                tr.append(Grade1Map.table.get(c));
            }
        }
        else {
            tr.append(c + "");
        }
    }

    private String extractAbbreviationWithTashkeel(Grade2Abbreviation abbreviation, String word, int startIndex) {
        int endIndex = startIndex;
        for (char c : abbreviation.getWord().toCharArray()) {
            while (c != word.charAt(endIndex)) {
                endIndex++;
            }
            endIndex++;
        }
        if (endIndex < word.length() - 1 && Constants.tashkeel.contains(word.charAt(endIndex))) {
            endIndex++;
        }
        return word.substring(startIndex, endIndex);
    }
}
