package tables;

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
        for(Grade2Abbreviation abbreviation: Grade2Table.getTable()){
            insertAbbreviation(abbreviation);
        }
    }

    public static Trie getInstance() {
        return instance;
    }

    public void insertAbbreviation(Grade2Abbreviation abbreviation) {
        TrieCell currentCell = root;
        for (char c : abbreviation.getWord().toCharArray()) {
            if (!currentCell.getChildren().containsKey(c)) {
                TrieCell newChild = new TrieCell();
                currentCell.inserChild(c, newChild);
            }
            currentCell = currentCell.getChildren().get(c);
        }
        currentCell.setAbbreviation(abbreviation);
    }

    public Grade2Abbreviation getLongestMatchAbbreviation(String input, int index) {
        char[] chars = input.toCharArray();
        Grade2Abbreviation longestAbbreviation = null;
        TrieCell currentCell = root;
        for (int i = index; i < chars.length; i++) {
            char currentChar = chars[i];
            if (currentCell.hasChild(currentChar)) {
                currentCell = currentCell.getChildren().get(currentChar);
                if (currentCell.hasAbbreviation()) {
                    longestAbbreviation = currentCell.getAbbreviation();
                }
            }
        }
        return longestAbbreviation;
    }
}
