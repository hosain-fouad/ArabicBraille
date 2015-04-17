package rules;

import utils.grade2.Constants;

/**
 * Created by hosainfathelbab on 4/17/15.
 * this is a special case rule that will be used only for "ية", "ات", "ون"
 * it make sure that those words should only appear at the end of the word and
 * the symbol before it doesn't have 1,2,3 cells in it.
 */
public class EndingAndNo123SymbolBefore implements Rule {
    @Override
    public boolean isValid(String word, String abbreviation, int index, String wordTranslationSoFar) {
        if(word.length() != index + abbreviation.length()) {
            return false;
        }
        if(wordTranslationSoFar.length() > 0){
            String lastSymbol = wordTranslationSoFar.substring(wordTranslationSoFar.length()-1);
            if(Constants.brailleSymbolReverseMapping.containsKey(lastSymbol)) {
                String usedCells = Constants.brailleSymbolReverseMapping.get(lastSymbol);
                if(usedCells.contains("1") || usedCells.contains("2") || usedCells.contains("3")) {
                    return false;
                }
            }

        }
        return true;
    }
}
