package rules;

import utils.grade2.Constants;

/**
 * Created by hosainfathelbab on 4/16/15.
 */
public class NoObjectPronouns implements Rule {
    @Override
    public boolean isValid(String word, String abbreviation, int index, String wordTranslationSoFar) {
        return !Constants.objectPronouns.contains(word.substring(index+abbreviation.length()));
    }
}
