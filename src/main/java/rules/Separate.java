package rules;

/**
 * Created by hosainfathelbab on 4/7/15.
 * This class is for separate word validation
 */
public class Separate implements Rule {
    @Override
    public boolean isValid(String word, String abbreviation, int index, String wordTranslationSoFar) {
        return index==0 && word.length() == abbreviation.length();
    }
}
