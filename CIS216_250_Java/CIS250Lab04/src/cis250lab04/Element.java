package cis250lab04;
//Created by APPLICATION programmer with a CONSTRAINT from the utility programmer.
public class Element {
    private char letter;
    
    //Set Letter
    public void SetLetter(char uLetter) {
        letter = uLetter;
    }
    
    //Get Letter
    public char GetLetter( ) {
        return letter;
    }
    
    //Clone
    public Element Clone( ) {
        Element clonedItem;
        clonedItem = new Element( );
        clonedItem.SetLetter(letter);
        return clonedItem;
    }
}