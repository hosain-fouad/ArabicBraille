package tables;

import java.util.Map;

/**
 * Created by hosainfathelbab on 4/6/15.
 */
public class TrieCell {
    private Map<Character, TrieCell> children = null;
    private Grade2Appreviation appreviation = null;

    public Map<Character, TrieCell> getChildren() {
        return children;
    }

    public Grade2Appreviation getAppreviation() {
        return appreviation;
    }

    public void setAppreviation(Grade2Appreviation appreviation) {
        this.appreviation = appreviation;
    }

    public boolean hasChild(Character c){
        return this.children.containsKey(c);
    }

    public boolean hasAppreviation(){
        return appreviation==null? false:true;
    }

    public void inserChild (Character c, TrieCell childCell) {
        if(this.children.containsKey(c)) {
            throw new IllegalArgumentException("Exception happened while trying to add existing cell in the trie.");
        }
        this.children.put(c,childCell);
    }
}
