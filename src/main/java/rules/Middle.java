package rules;

/**
 * Created by hosainfathelbab on 4/7/15.
 */
public class Middle implements Rule {
    @Override
    public boolean isValid(String word, String abbreviation, int index, String wordTranslationSoFar) {
        return index > 0 && word.length() > index + abbreviation.length();
    }
}
