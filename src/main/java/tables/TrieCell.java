package tables;

import java.util.Map;

/**
 * Created by hosainfathelbab on 4/6/15.
 */
public class TrieCell {
    private Map<Character, TrieCell> children = null;
    private Grade2Abbreviation abbreviation = null;

    public Map<Character, TrieCell> getChildren() {
        return children;
    }

    public Grade2Abbreviation getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(Grade2Abbreviation abbreviation) {
        this.abbreviation = abbreviation;
    }

    public boolean hasChild(Character c){
        return this.children.containsKey(c);
    }

    public boolean hasAbbreviation(){
        return abbreviation ==null? false:true;
    }

    public void inserChild (Character c, TrieCell childCell) {
        if(this.children.containsKey(c)) {
            throw new IllegalArgumentException("Exception happened while trying to add existing cell in the trie.");
        }
        this.children.put(c,childCell);
    }
}
