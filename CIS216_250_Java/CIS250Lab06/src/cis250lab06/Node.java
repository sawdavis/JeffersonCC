package cis250lab06;

public class Node {
    private Element element; //like an Element
    private Node next;
    
    public void SetElement(Element aElement) {
        element = aElement;
    }
    
    public Element GetElement( ) {
        return element; 
    }
    
    public void SetNext(Node nNext) {
        next = nNext;
    }
    
    public Node GetNext( ) {
        return next;
    }
}

