package cis250lab07;

public class Element {
    private String zip;
    private String place;
    private String state;
    
    public String GetKey( ) {
        return zip;
    }
    
    public void Set(String zip, String place, String state) {
        this.zip = zip;
        this.place = place;
        this.state = state;
    }
    
    public String GetZip( ) {
        return zip;
    }
    
    public String GetPlace( ) {
        return place;
    }
    
    public String GetState( ) {
        return state;
    }
    
    public Element Clone( ) {
        Element clonedItem;
        
        clonedItem = new Element( );
        clonedItem.Set(zip, place, state);
        
        return clonedItem;
    }
}

