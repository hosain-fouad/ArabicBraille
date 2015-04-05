package tables;

import rules.Rule;

import java.util.List;

/**
 * Created by hosainfathelbab on 4/4/15.
 */
public class Grade2Appreviation {

    private List<Rule> rules;
    private String symbol;
    private String word;

    @Override
    public int hashCode() {
        int hash=7;
        for (int i=0; i < word.length(); i++) {
            hash = hash*31+ word.charAt(i);
        }
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Grade2Appreviation))
            return false;
        if (obj == this)
            return true;

        Grade2Appreviation otherObject = (Grade2Appreviation) obj;
        return this.word.equals(otherObject.word);
    }

    public List<Rule> getRules() {
        return rules;
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
