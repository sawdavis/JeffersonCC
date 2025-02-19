package cis250lab03;
//Written by UTILITY programmer
public class BagADT {
    private Student [ ] items;
    private int numItems;
    /* CONSTRAINTS of APPLICATION programmer:
        Must provide a class called Student that holds the application data.
        Must provide a method that performs a DEEP COPY named Clone.
        Must provide a method that gets a searchable String value named GetKey.
    */
    
    public boolean Contains(String searchValue) {
        //Desc:  Identifies whether or not a given search value is in the bag (case insensitive).
        //Pre:  Bag must exist (have been created).
        //      Search value must be properly loaded (as a String).
        //Post:  When there is at least one matching item, returns true
        //       When there are NO matching items, returns false;
        boolean hasItem;
        
        hasItem = (GetFrequency(searchValue) > 0);
        
        return hasItem;
    }
    
    public int GetFrequency(String searchValue) {
        //Desc:  Gives back how many bag items match the given search value (case insensitive).
        //Pre:  Bag must exist (have been created).
        //      Search value must be properly loaded (as a String).
        //Post:  Will return the number of matching bag items.
        //       The bag will remain unchanged.
        int frequency;
        
        frequency = 0;
        for(int loc = 0; loc < numItems; loc++)
            if(items[loc].GetKey( ).equalsIgnoreCase(searchValue))
                frequency++;
        
        return frequency;
    }
    
    public Student RemoveSpecific(String searchValue) {
        //Desc:  Removes a SPECIFIED item from the bag (case insensitive) - if possible.
        //Pre:  Bag must exist (have been created).
        //      Search value must be properly loaded (as a String).
        //Post:  When there is an item that matches the search value, it 
        //          will be removed/returned.
        //       When there is no item that matches the search value, NULL
        //          will be returned.
        int loc;
        Student itemToReturn;
        
        //perform search
        loc = 0;
        while(loc < numItems && 
            searchValue.compareToIgnoreCase(items[loc].GetKey( )) != 0)
                loc++;
        
        //retrieve/remove item IF FOUND
        if(loc < numItems) {
            itemToReturn = items[loc];
            items[loc] = items[numItems - 1];
            items[numItems - 1] = null;
            numItems--;
        }
        else
            itemToReturn = null;
        
        return itemToReturn;
    }
    
    public Student Remove( ) {
        //Desc:  Removes an item from the bag.
        //Pre:  Bag must exist (have been created).
        //Post:  When the bag is NOT empty, an item will be removed/returned.
        //       When the bag IS empty, returns null.
        Student itemToReturn;
        
        if(IsEmpty( ) == false) {
            itemToReturn = items[numItems - 1];
            items[numItems - 1] = null;
            numItems--;
        } else
            itemToReturn = null;        
        
        return itemToReturn;
    }
    
    public Student[ ] ToArray( ) {
        //Desc:  Gives back a copy of all items in an array.
        //Pre:  Bag must exist (have been created).
        //Post:  An array with a copy of the bag students is returned.
        //       The bag will remain unchanged.
        Student [ ] arrToReturn;
        
        arrToReturn = new Student[numItems];
        
        for(int cnt = 0; cnt < numItems; cnt++) {
            arrToReturn[cnt] = items[cnt].Clone( );
        }
        return arrToReturn;
    }
    
    public int GetCurrentSize( ) {
        //Desc:  Returns the the number of items in the bag.
        //Pre:  Bag must exist (have been created).
        //Post:  The number of items will be returned - the bag remains unchanged.
        return numItems;
    }
    
    public void Clear( ) {
        //Desc:  Reset the bag to an empty status.
        //Pre:  Bag must exist (have been created).
        //Post:  The bag will be empty with the same capacity.
        numItems = 0; //required
        
        //DEFENSIVE CODING
        for(int cnt = 0; cnt < items.length; cnt++)
            items[cnt] = null;
    }
    
    public boolean Add(Student givenStudent) {
        //Desc:  Adds a copy of the  given student to the bag and reports on 
        //          the success.
        //Pre:  Bag must exist (have been created).
        //      Given student must be properly loaded.
        //Post:  When the bag is NOT full, a copy of the given student will 
        //          be in the bag AND a value of true will be returned.
        //       When the bag IS FULL, the contents of the bag remain 
        //          unchanged AND a value of false is returned.
        boolean success;
        
        if(IsFull( ))
            success = false;
        else {
            success = true;
            items[numItems] = givenStudent.Clone( );
            numItems++;
        }
        
        return success;
    }
    
    public void Create( ) {
        //Desc:  Initializes the bag to an empty state and sets the default 
        //              capacity of 10.
        //Pre:  None.
        //Post:  The bag will exist as empty with the default capacity of 10.
        Create(10);
    }
    
    public void Create(int givenCapacity) {
        //Desc:  Initializes the bag to an empty state and sets the given capacity.
        //Pre:  givenCapacity will be properly set (greater than 0).
        //Post:  The bag will exist as empty with the given capacity.
        numItems = 0;
        items = new Student[givenCapacity];
    }
    
    public boolean IsEmpty( ) {
        //Desc:  Identifies if the bag has 0 items in it.
        //Pre:  Bag must exist (have been created).
        //Post:  When the bag is empty, returns a true value.
        //       When the bag is NOT empty, returns a false value.
        //       The bag always remains unchanged.
        boolean empty;
        
        /*if(numItems == 0)
            empty = true;
        else
            empty = false;*/
        
        empty = (numItems == 0);
        
        return empty;
    }
    
    public boolean IsFull( ) {
        //Desc:  Identifies if the bag has no more capacity.
        //Pre:  Bag must exist (have been created).
        //Post:  When the bag has no more capacity, returns true.
        //       When the bags has more capacity, returns false.
        //       The bag remains unchanged.
        boolean full;
        
        if(numItems == items.length)
            full = true;
        else
            full = false;
        
        return full;
    }
    
   
            
}