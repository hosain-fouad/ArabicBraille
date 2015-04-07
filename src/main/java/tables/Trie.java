package tables;

import java.util.Map;

/**
 * Created by hosainfathelbab on 4/6/15.
 */
public class Trie {
    private TrieCell root;
    private static Trie instance = new Trie();

    private Trie() {
        constructBraille2Trie();
    }

    public void constructBraille2Trie(){
        root = new TrieCell();
        for(Grade2Appreviation appreviation: Grade2Table.getTable().values()){
            insertAppreviation(appreviation);
        }
    }

    public static Trie getInstance() {
        return instance;
    }

    public void insertAppreviation(Grade2Appreviation appreviation) {
        TrieCell currentCell = root;
        for (char c : appreviation.getWord().toCharArray()) {
            if (!currentCell.getChildren().containsKey(c)) {
                TrieCell newChild = new TrieCell();
                currentCell.inserChild(c, newChild);
            }
            currentCell = currentCell.getChildren().get(c);
        }
        currentCell.setAppreviation(appreviation);
    }

    public Grade2Appreviation getLongestMatchAppreviation(String input, int index) {
        char[] chars = input.toCharArray();
        Grade2Appreviation longestAppreviation = null;
        TrieCell currentCell = root;
        for (int i = index; i < chars.length; i++) {
            char currentChar = chars[i];
            if (currentCell.hasChild(currentChar)) {
                currentCell = currentCell.getChildren().get(currentChar);
                if (currentCell.hasAppreviation()) {
                    longestAppreviation = currentCell.getAppreviation();
                }
            }
        }
        return longestAppreviation;
    }
}
