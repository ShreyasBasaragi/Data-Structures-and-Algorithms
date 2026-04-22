public class TrieNode {
    
    // Array for child nodes of each node
    TrieNode[] children;
    
    // Used for indicating the end of a string
    boolean isEndOfWord;

    // Constructor
    public TrieNode() {
      
        // Initialize the wordEnd 
        // variable with false
        isEndOfWord = false;

        // Initialize every index of 
        // the child array with null
        // In Java, we do not have to 
        // explicitely assign null as 
        // the values are by default 
        // assigned as null 
        children = new TrieNode[26];
    }

    public void add(TrieNode t, String s){
        for (int i = 0; i < s.length(); i++) {
            if (t.children[s.charAt(i)-'a'] == null) {
                t.children[s.charAt(i)-'a'] = new TrieNode();
                t = t.children[s.charAt(i)-'a'];
            }else{
                t = t.children[s.charAt(i)-'a'];
            }
        }
        t.isEndOfWord = true;
    }
}
