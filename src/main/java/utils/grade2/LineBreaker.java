package utils.grade2;

import com.google.common.base.Strings;

/**
 * Created by hosainfathelbab on 5/15/15.
 */
public class LineBreaker {

    public static String breakLinesWithTashkeel(String tashkeelLine, String wordsLine, int lineLength) {


        if(tashkeelLine.length() < wordsLine.length()) {
            tashkeelLine = Strings.padEnd(tashkeelLine, wordsLine.length(), ' ');
        }
        else if(tashkeelLine.length() > wordsLine.length()) {
            wordsLine = Strings.padEnd(wordsLine, tashkeelLine.length(), ' ');
        }

        StringBuffer brokenLines = new StringBuffer();

        int index = 0;
        while(index < wordsLine.length()) {
            int breakIndex = index+lineLength < wordsLine.length() ? index+lineLength : wordsLine.length();
            brokenLines.append(tashkeelLine.substring(index, breakIndex));
            brokenLines.append("\n");
            brokenLines.append(wordsLine.substring(index, breakIndex));
            brokenLines.append("\n");

            index += lineLength;
        }

        return brokenLines.toString();
    }
}
