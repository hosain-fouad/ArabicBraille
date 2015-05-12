package tables;

import utils.grade2.Constants;

/**
 * Created by hosainfathelbab on 4/6/15.
 * This Trie is being constructed once when the application starts.
 * It is a singleton class that when called for the first time it will construct the trie.
 * It build a trie representation that contains all the abbriviations for Braille grade 2.
 * This trie will be used to extract possible abbriviations from words.
 */
public class Trie {
    private TrieCell root;
    private static Trie instance;
    static{
        instance = new Trie();
    }

    private Trie() {
        constructBraille2Trie();
    }

    /*
    * build the trie
    */
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

    /*
    * get longest match grade 2 abbreviation. It deosn't work with words that has tashkeel, use "getLongestMatchAbbreviationWithTashkeel" instead.
    */
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
            else{
                break;
            }
        }
        return longestAbbreviation;
    }

    /*
    * get longest match grade 2 abbreviation while taking care that the word may or maynot have tashkeel.
    */
    public Grade2Abbreviation getLongestMatchAbbreviationWithTashkeel(String input, int index) {
        char[] chars = input.toCharArray();
        Grade2Abbreviation longestAbbreviation = null;
        TrieCell currentCell = root;
        for (int i = index; i < chars.length; i++) {
            char currentChar = chars[i];
            if(Constants.tashkeel.contains(currentChar)) {
                continue;
            }
            if (currentCell.hasChild(currentChar)) {
                currentCell = currentCell.getChildren().get(currentChar);
                if (currentCell.hasAbbreviation()) {
                    longestAbbreviation = currentCell.getAbbreviation();
                }
            }
            else{
                break;
            }
        }
        return longestAbbreviation;
    }
}
