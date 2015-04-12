package translators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import rules.Rule;
import tables.Grade1Map;
import tables.Grade2Abbreviation;
import tables.Trie;
import utils.grade2.WordIterator;
import java.util.Iterator;

/**
 * Created by hosainfathelbab on 4/4/15.
 */
@Repository(value = "grade2Translator")
public class Grade2Translator extends Translator{

    @Autowired
    private Translator grade1Translator;

    @Override
    public String translate(String input) {

        StringBuffer sb = new StringBuffer();

        Iterator<String> wordIterator = new WordIterator(input);
        while(wordIterator.hasNext()){
            sb.append(translateWord(wordIterator.next()));
        }

        return sb.toString();
    }

    private String translateWord(String word){
        StringBuffer sb = new StringBuffer();
        int index = 0;
        while (index < word.length()) {

            if(isNumber(word.charAt(index))){
                sb.append("⠼");
                while(index < word.length() && isNumber(word.charAt(index))){
                    sb.append(Grade1Map.table.get(word.charAt(index++)));
                }
                continue;
            }

            Grade2Abbreviation abbreviation = Trie.getInstance().getLongestMatchAbbreviation(word, index);
            if (abbreviation != null) {
                boolean success = true;
                if(abbreviation.getRules().size() > 0) {
                    success = false;
                    for (Rule rule : abbreviation.getRules()) {
                        if (rule.isValid(word, abbreviation.getWord(), index)) {
                            success = true;
                            break;
                        }
                    }
                }
                if (success) {
                    index = index + abbreviation.getWord().length();
                    sb.append(abbreviation.getSymbol());
                    continue;
                }
            }
            sb.append(grade1Translator.translate(word.charAt(index) + ""));
            index++;
        }
        return sb.toString();
    }

    private boolean isNumber(char c){
        if((c>='0'&&c<='9') || (c>='٠'&&c<='٩')) {
            return true;
        }
        else{
            return false;
        }
    }
}
