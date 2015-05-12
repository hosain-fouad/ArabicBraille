package rules;

import utils.grade2.Constants;

/**
 * Created by hosainfathelbab on 4/16/15.
 * This rules validates that the abbreviations is not followed by pronuouns.
 */
public class NoObjectPronouns implements Rule {
    @Override
    public boolean isValid(String word, String abbreviation, int index, String wordTranslationSoFar) {
        return !Constants.objectPronouns.contains(word.substring(index+abbreviation.length()));
    }
}
