package utils.grade2;

import java.util.List;

/**
 * Created by hosainfathelbab on 4/4/15.
 */
public class WordProcessor {
    public static List<String> getWords(String input) {
        return null;
    }

    public static String getNextWord(String input, int startIndex) {
        if(startIndex >= input.length()) {
            return null;
        }

        int endIndex = startIndex;
        while(endIndex < input.length() && Constants.punctuation.contains(input.charAt(endIndex))) {
            endIndex++;
        }
        return input.substring(startIndex, endIndex + 1);
    }
}
