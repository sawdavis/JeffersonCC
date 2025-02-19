package cis250lab07;

public class Node {
    private Element data;
    private Node next;
    
    public void SetData(Element givenItem) {
        data = givenItem;
    }
    
    public Element GetData( ) {
        return data;
    }
    
    public void SetNext(Node givenNode) {
        next = givenNode;
    }
    
    public Node GetNextNode( ) {
        return next;
    }
}
