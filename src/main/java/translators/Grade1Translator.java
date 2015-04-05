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

        for (char c : input.toCharArray()) {
            if (table.containsKey(c)) {
                sb.append(table.get(c));
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }
}
