import java.util.ArrayList;

class Node {
    int data;
    ArrayList<Node> children;

    Node(int data)
    {
        this.data = data;
        this.children = new ArrayList<Node>();
    }
}
