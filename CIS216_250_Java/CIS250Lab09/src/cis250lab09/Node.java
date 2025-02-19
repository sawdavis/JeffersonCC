package cis250lab09;

public class Node {
    private Element data;
    private Node left, right;
    
    public void SetData(Element givenElement) {
        data = givenElement;
    }
    
    public Element GetData( ) {
        return data;
    }
    
    public void SetLeft(Node uLeft) {
        left = uLeft;
    }
    
    public Node GetLeft( ) {
        return left;
    }
    
    public void SetRight(Node uRight) {
        right = uRight;
    }
    
    public Node GetRight( ) {
        return right;
    }
}

