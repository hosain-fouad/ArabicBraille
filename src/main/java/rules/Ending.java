package rules;

/**
 * Created by hosainfathelbab on 4/7/15.
 * This rules validates that the abbreviations appears at the end of the word.
 */
public class Ending implements Rule {
    @Override
    public boolean isValid(String word, String abbreviation, int index, String wordTranslationSoFar) {
        return word.length() == index + abbreviation.length();
    }
}
