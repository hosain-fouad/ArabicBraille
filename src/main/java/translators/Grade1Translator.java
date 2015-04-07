package translators;

import org.springframework.stereotype.Repository;
import tables.Grade1Map;

import java.util.HashMap;

/**
 * Created by hosainfathelbab on 4/4/15.
 */
@Repository(value = "grade1Translator")
public class Grade1Translator extends Translator{
    @Override
    public String translate(String input) {
        HashMap<Character, String> table = Grade1Map.table;
        StringBuffer sb = new StringBuffer();

        char[] chars = input.toCharArray();

        for (int i=0; i<chars.length; i++) {
            char c = chars[i];
            if (table.containsKey(c)) {
                if(isNumber(c) && (i == 0 || !isNumber(chars[i-1]))){
                    sb.append("⠼");
                }
                sb.append(table.get(c));
            } else {
                sb.append(c);
            }
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
