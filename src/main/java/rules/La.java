package rules;

/**
 * Created by hosainfathelbab on 4/28/15.
 * This rules only used to validate the appreviation for "LA" and decide if it should be abbriviated (use one symbol) or not (use two symbols)
 */
public class La implements Rule {

    @Override
    public boolean isValid(String word, String abbreviation, int index, String wordTranslationSoFar) {
        if(index == 1 && word.charAt(0) == 'ا' ) {
            return false;
        }
        else {
            return true;
        }
    }
}
