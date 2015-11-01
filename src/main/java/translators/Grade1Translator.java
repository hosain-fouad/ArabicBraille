package translators;

import org.springframework.stereotype.Repository;
import tables.Grade1Map;
import utils.grade2.Constants;
import utils.grade2.Tashkeel;
import utils.grade2.WordIterator;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by hosainfathelbab on 4/4/15.
 */
@Repository(value = "grade1Translator")
public class Grade1Translator extends Translator{

    @Override
    public TranslationResult translate(String input, Tashkeel tashkeel) {
        TranslationResult tr = new TranslationResult(tashkeel);

        Iterator<String> wordIterator = new WordIterator(input);
        while (wordIterator.hasNext()) {
            String word = wordIterator.next();
            tr.append(translateWordWithTashkeel(word, tashkeel));
        }

        return tr;
    }

    private TranslationResult translateWordWithTashkeel(String word, Tashkeel tashkeel) {
        HashMap<Character, String> table = Grade1Map.table;
        TranslationResult tr = new TranslationResult(tashkeel);

        word = handleSpecialCases(word);

        char[] chars = word.toCharArray();

        for (int i=0; i<chars.length; i++) {
            char c = chars[i];
            if (table.containsKey(c)) {
                if(isNumber(c) && (i == 0 || !isNumber(chars[i-1]))){
                    tr.append("⠼");
                }

                // handle the Shadda case
                if(c == Constants.SHADDA) {
                    handleShaddaCase(word, i, tr);
                }
                else {
                    tr.append(table.get(c));
                }

            } else {
                tr.append(c + "");
            }
        }

        return tr;
    }

    private String handleSpecialCases(String word) {
        // handle "لا"
        while(word.contains("لا")) {
            int index = word.indexOf("لا");
            if(index == 1 && word.charAt(0) == 'ا' ) {
                word = word.replace("لا", "⠇⠁");
            }
            else {
                word = word.replace("لا", "⠧");
            }
        }

        return word;
    }

    /**
     * works only with KSA tashkeel for now.
     * TODO: need to be updated if we are going to handle Egypt Tashkeel for Qura'n
     */
    private void handleShaddaCase(String word, int shaddaIndex, TranslationResult currentTR) {
        int backTrackIndex = shaddaIndex-1;
        while(backTrackIndex >= 0) {
            if(!Constants.tashkeel.contains(word.toCharArray()[backTrackIndex])) {
                StringBuffer sb = currentTR.getSecondLine();
                int startReplaceIndex = sb.length() - ( shaddaIndex - backTrackIndex );
                String newTranslationWithShadda = Grade1Map.table.get(Constants.SHADDA) + sb.substring(startReplaceIndex);
                sb.replace(startReplaceIndex, sb.length(), newTranslationWithShadda);
            }
            backTrackIndex--;
        }
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
