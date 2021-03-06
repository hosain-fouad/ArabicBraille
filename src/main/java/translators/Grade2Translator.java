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

    private TranslationResult translateWordWithTashkeel(String word, Tashkeel tashkeel) {
        TranslationResult tr = new TranslationResult(tashkeel);
        int index = 0;
        int lastAbbriviatedIndex = 0;
        while (index < word.length()) {
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
                    if(lastAbbriviatedIndex < index) {
                        tr.append(grade1Translator.translate(word.substring(lastAbbriviatedIndex, index), tashkeel));
                    }

                    if (abbreviation.getWord().length() > 2 && (index > 0 || index + abbreviatedWordWithTashkeel.length() < word.length()) && !abbreviation.getWord().equals("كان")) {
                        tr.append("⠤");
                    }
                    tr.append(abbreviation.getSymbol());

                    index = index + abbreviatedWordWithTashkeel.length();
                    lastAbbriviatedIndex = index;
                }
                else {
                    index++;
                }
            }
            else {
                index++;
            }
        }

        if(lastAbbriviatedIndex < word.length() && lastAbbriviatedIndex < index) {
            tr.append(grade1Translator.translate(word.substring(lastAbbriviatedIndex, index), tashkeel));
        }

        return tr;

    }


    private String extractAbbreviationWithTashkeel(Grade2Abbreviation abbreviation, String word, int startIndex) {
        int endIndex = startIndex;
        for (char c : abbreviation.getWord().toCharArray()) {
            while (c != word.charAt(endIndex)) {
                endIndex++;
            }
            endIndex++;
        }
        while (endIndex < word.length() - 1 && Constants.tashkeel.contains(word.charAt(endIndex))) {
            endIndex++;
        }
        return word.substring(startIndex, endIndex);
    }
}
