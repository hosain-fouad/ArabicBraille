package tables;

import rules.Rule;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hosainfathelbab on 4/4/15.
 */
public class Grade2Abbreviation {

    private List<Rule> rules = new ArrayList<Rule>();
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
        if (!(obj instanceof Grade2Abbreviation))
            return false;
        if (obj == this)
            return true;

        Grade2Abbreviation otherObject = (Grade2Abbreviation) obj;
        return this.word.equals(otherObject.word);
    }

    public List<Rule> getRules() {
        return rules;
    }

    public Grade2Abbreviation addRule(Rule rule) {
        this.rules.add(rule);
        return this;
    }

    public Grade2Abbreviation setRules(List<Rule> rules) {
        this.rules = rules;
        return this;
    }

    public String getSymbol() {
        return symbol;
    }

    public Grade2Abbreviation setSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public String getWord() {
        return word;
    }

    public Grade2Abbreviation setWord(String word) {
        this.word = word;
        return this;
    }
}
