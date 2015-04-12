package rules;

/**
 * Created by hosainfathelbab on 4/7/15.
 */
public class Ending implements Rule {
    @Override
    public boolean isValid (String word, String abbreviation, int index) {
        return word.length() == index + abbreviation.length();
    }
}
