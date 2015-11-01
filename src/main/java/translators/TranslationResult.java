package translators;

import com.google.common.base.Strings;
import utils.grade2.Constants;
import utils.grade2.LineBreaker;
import utils.grade2.Tashkeel;

/**
 * Created by hosainfathelbab on 5/15/15.
 */
public class TranslationResult {
    private Tashkeel tashkeel;
    private StringBuffer firstLine, secondLine;
    private static final int DEFUALT_LINE_LENGTH = 55;

    public TranslationResult(Tashkeel tashkeel) {
        if(tashkeel != null) {
            this.tashkeel = tashkeel;
        }
        else {
            this.tashkeel = Tashkeel.ksa;
        }
        this.secondLine = new StringBuffer();

        // intialize the first line string buffer iff we are translating egyptian way.
        if(this.tashkeel == Tashkeel.egypt) {
            this.firstLine = new StringBuffer();
        }
    }

    public void append(String symbol) {
        secondLine.append(symbol);
    }

    public void appendTashkeel(String symbol) {
        if (tashkeel == Tashkeel.ksa) {
            secondLine.append(symbol);
        } else {
            int offset = secondLine.length() - firstLine.length() - 1;
            if (offset > 0) {
                firstLine.append(Strings.repeat(" ", offset));
            }
            firstLine.append(symbol);
        }
    }

    public StringBuffer getFirstLine() {
        return firstLine;
    }

    public StringBuffer getSecondLine() {
        return secondLine;
    }

    public void append(TranslationResult translationResult) {
        if(this.tashkeel == Tashkeel.egypt) {
            alignLines();
            this.firstLine.append(translationResult.getFirstLine().toString());
        }
        this.secondLine.append(translationResult.getSecondLine().toString());
    }

    private void alignLines(){
        if(secondLine.length() > firstLine.length()) {
            firstLine.append(Strings.repeat(" ", secondLine.length() - firstLine.length()));
        }
        else if(secondLine.length() < firstLine.length()) {
            secondLine.append(Strings.repeat(" ", firstLine.length() - secondLine.length()));
        }
    }

    public String toString(){
        String output = "";
        if(tashkeel == Tashkeel.egypt) {
            output = LineBreaker.breakLinesWithTashkeel(firstLine.toString(), secondLine.toString(), DEFUALT_LINE_LENGTH);
        }
        else {
            output = secondLine.toString();
        }
        return output;
    }

    public String toString(int lineLength){
        String output = "";
        if(tashkeel == Tashkeel.egypt) {
            output = LineBreaker.breakLinesWithTashkeel(firstLine.toString(), secondLine.toString(), lineLength);
        }
        else {
            output = secondLine.toString();
        }
        return output;
    }
}
