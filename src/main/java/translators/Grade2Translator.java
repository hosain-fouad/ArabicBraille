package translators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import rules.Rule;
import tables.Grade1Map;
import tables.Grade2Appreviation;
import tables.Grade2Table;
import tables.Trie;
import utils.grade2.Hyphenation;
import utils.grade2.WordProcessor;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created by hosainfathelbab on 4/4/15.
 */
@Repository(value = "grade2Translator")
public class Grade2Translator extends Translator{

    @Autowired
    private Translator grade1Translator;

    private Map<String, Grade2Appreviation> grade2Table = Grade2Table.getTable();

    @Override
    public String translate(String input) {
        // split based on the space
        List<String> words = WordProcessor.getWords(input);
        StringBuffer sb = new StringBuffer();
        for(String word: words){
            sb.append(translateWord(word)).append(" ");
        }
        return sb.toString();
    }

    private String translateWord(String word){
        StringBuffer sb = new StringBuffer();
        int index = 0;
        while (index < word.length()) {

            if(isNumber(word.charAt(index))){
                sb.append("⠼");
                while(isNumber(word.charAt(index))){
                    sb.append(Grade1Map.table.get(word.charAt(index++)));
                }
                continue;
            }

            Grade2Appreviation appreviation = Trie.getInstance().getLongestMatchAppreviation(word, index);
            if (appreviation != null) {
                boolean success = true;
                for (Rule rule : appreviation.getRules()) {
                    success = success & rule.isValid(word, index);
                }
                if (success) {
                    index = index + appreviation.getWord().length();
                    sb.append(appreviation.getSymbol());
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
