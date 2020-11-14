package structs;

public class TrieNode {
    public String letter;
    public LinkedL childs = LinkedL();
    public boolean wordEnd;

    public TrieNode(boolean wordEnd, String letter){

        this.letter = letter;
        this.wordEnd = wordEnd;
    }
}
