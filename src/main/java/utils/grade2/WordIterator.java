package utils.grade2;

import java.util.Iterator;
import java.util.List;

/**
 * Created by hosainfathelbab on 4/4/15.
 */
public class WordIterator implements Iterator<String>{

    private int charIndex = 0;
    private String text;
    public WordIterator( String input) {
        this.text = input;
    }

    @Override
    public boolean hasNext() {
        if(this.charIndex < this.text.length()) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public String next() {
        int endIndex = this.charIndex;
        while(endIndex < this.text.length() && !Constants.punctuation.contains(this.text.charAt(endIndex))) {
            endIndex++;
        }
        if(endIndex == this.charIndex) endIndex++;

        String nextWord = this.text.substring(this.charIndex, endIndex);
        this.charIndex = endIndex;
        return nextWord;
    }

    @Override
    public void remove() {
        // no implementation
    }
}
