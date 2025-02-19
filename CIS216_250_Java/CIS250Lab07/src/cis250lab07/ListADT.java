package cis250lab07;

public class ListADT {
    //CONSTRAINT on App programmer:  Requires creation of Element class.
    //CONSTRAINT on App programmer:  Element must have a method named Clone
    //                                  that performs a deep copy.
    //CONSTRAINT on App programmer:  Element must have a GetKey method that 
    //                                  returns a String "key".
    
    //Attributes
    private int size;
    private Node current;
    private Node previous;
    private Node start;
    private boolean created;
    private boolean ordered;
    
    /*Methods
    Observers, Manipulators, Iterators (traversal)
    
    done-Add
    done-Remove (delete specific item)
    done-IsEmpty
    done-IsFull
    done-Destroy
    done-Create - fixed size or not???
    done-GetNumItemsInIt - GetCurrentSize
    done-Constructor
    done-Retrieve (gives back a copy - leaves the data in the list)
    done-Search (locates - IF POSSIBLE - a matching data item)
    
    ITERATE:
    done-Reset (get the current item back to start)
    done-GetNext (move the current node to the next node)
    done-AtEnd (identify if we are at the end of the list)
    */
    
    public boolean Remove(String searchValue) {
        //Desc:  Remove a specific list item (if possible).
        //Pre:  The specified item (a key) must be properly loaded.
        //Post:  When the specified item is found, it will be removed AND a 
        //          value of true will be returned.
        //       When the specified item is NOT found, a value of false will 
        //          be returned.
        //       When the list does not exist (not created), returns a value 
        //          of false.
        boolean removed;
        
        removed = false;
        if(created) {
            removed = Search(searchValue);  //tells us if it is in here and 
                                               //we can remove it AND positions
                                               //previous and current
            if(removed == true) {
                if(previous == null)
                    start = current.GetNextNode( );
                else
                    previous.SetNext(current.GetNextNode( ));
                
                //defensive
                current.SetData(null);
                current.SetNext(null);
                Reset( );
            }
        }
        
        return removed;
    }
    
    public ListADT( ) {
        //Desc:  Instantiates a ListADT object that is UNUSABLE (not created).
        //Pre:  None.
        //Post:  Object will exist but be unusable (until "created").
        
        size = -200;
        current = null;  //does not really matter
        previous = null;  //does not really matter
        start = null;  //does not really matter
        created = false;
        ordered = false;  //does not really matter 
    }
    
    public void Destroy( ) {
        //Desc:  Puts the list into an unusable state.
        //Pre:  None.
        //Post:  The list will be in an unusable state.
        //Defensive - eliminate all nodes
        if(created) {
            Reset( );
            while(!AtEnd( )) {
                GetNext( );
                start.SetData(null);
                start.SetNext(null);
                start = current;
            }
        }
        
        size = -200;
        current = null;  //does not really matter
        previous = null;  //does not really matter
        start = null;  //does not really matter
        created = false;
        ordered = false;  //does not really matter 
    }
    
    public void Create(int givenSize, boolean isOrdered) {
        //Desc:  Sets the List up as empty.
        //       Sets a maximum "size" based upon the givenSize parameter.
        //       Sets the ordering based upon the givenOrdering parameter.
        //Pre:  Given size must be greater than 0.
        //Post:  List will exist with a maximimum size and ordering.
        size = givenSize;
        ordered = isOrdered;
        created = true;
        current = null;
        start = null;
        previous = null;
    }
    
    public boolean IsEmpty( ) {
        //Desc:  Identifies if the list is empty.
        //Pre:  None.
        //Post:  When the list exists but has no data, returns true.
        //       When the list exists but contains data, returns false.
        //       When the list does NOT exist, returns a meaningless answer.
        boolean empty;
        
        empty = false;
        if(created && GetCurrentSize( ) == 0 || start == null)
            empty = true;
        //if(!created)
        //    empty = false;
        
        return empty;
    }
    
    public Element Retrieve( ) {
        //Desc:  Gives back a copy of the "current" element.
        //Pre:  None.
        //Post:  When the list was created and is not empty nor at the end, 
        //              returns a copy of the "current" item.
        //       When the list was NOT created OR is empty OR is at the end, 
        //              returns a null;
        Element retElement;
        
        if(created && !IsEmpty( ) && !AtEnd( ))
            retElement = current.GetData( ).Clone( );
        else
            retElement = null;
        
        return retElement;
    }
    
    public int GetCurrentSize( ) {
        //Desc:  Returns the number of items in the list.
        //Pre:  None.
        //Post:  When list does NOT exist, returns meaningless value.
        //       When list exists, returns how many data elements are present.
        int numItems;
        Node temp;
        
        if(created) {
            numItems = 0;
            temp = start;
            while(temp != null) {
                numItems++;
                temp = temp.GetNextNode( );
            }
            temp = null;  //defensive
        }
        else
            numItems = -100;
        
        return numItems;
    }
    
    public boolean IsFull( ) {
        //Desc:  Identifies if the list has any remaining capacity.
        //Pre:  None.
        //Post:  When the list was created and there is capacity, returns false.
        //       When the list was created and there is NO capacity, returns true.
        //       When the list has not been created, returns true - safest option.
        boolean full;
        Node tempNode;
        Element tempElement;
        
        full = false;
        try {
            tempNode = new Node( );
            tempElement = new Element( );
        } catch (OutOfMemoryError e) {
            full = true;
        }
        tempNode = null;  //defensive
        tempElement = null;  //defensive
        if(size == GetCurrentSize( ))
            full = true;
        
        return full;
    }
    
    public boolean Add(Element givenElement) {
        //Desc:  Places a copy of the given element in the list based upon the ordering.
        //Pre:  givenElement must be properly loaded.
        //Post:  When the list does not exist (not created), returns false value.
        //       When the list is full, returns a false value.
        //       Otherwise, returns a true value and a copy of the given element is properly placed.
        boolean added;
        if(!created || IsFull( ))
            added = false;
        else {
            added = true;
            if(ordered)
                Insert(givenElement);
            else
                Prepend(givenElement);
        }
        return added;
    }
    
    public void Reset( ) {
        //Desc:  Resets the "current" item to be the first item.
        //Pre:  None.
        //Post:  If the list was created, the "current" item is the first item.
        //       If the list was not created, results are undefined.
        current = start;
        previous = null;
    }
    
    public boolean AtEnd( ) {
        //Desc:  Identifies if we are at the end of the list (i.e. not accessing data).
        //Pre:  None.
        //Post:  If the list was created, returns a true when we are at the end (past good data).
        //       If the list was created, returns a false when we are on good data.
        //       If the list was not created, results are undefined.
        boolean atEnd;
        
        if(current != null)
            atEnd = false;
        else
            atEnd = true;
        
        return atEnd;
    }
    
    public void GetNext( ) {
        //Desc:  Moves the "current" item to the next item.
        //Pre:  Must not be at the end of the list.
        //Post:  If the list was created, moves the "current" item to the next item.
        //       If the list was NOT created, the results are undefined.
        if(created) {
            previous = current;
            current = current.GetNextNode( );
        }
    }
    
    public boolean Search(String searchKey) {
        //Desc:  Locates the searchKey (if possible).
        //Pre:  searchKey must be properly loaded.
        //Post:  If the item is located, a true value will be returned.
        //       If the item is NOT located OR the list does NOT exist, returns 
        //              a false value.
        //       If the list is ORDERED, the "current" location will be the 
        //          location where the data would be placed.
        boolean found;
        
        found = false;
        if(created && ordered) {
            Reset( );
            while(!AtEnd( ) && 
                    current.GetData( ).GetKey( ).compareTo(searchKey) < 0)
                GetNext( );
            
            if(current != null && current.GetData( ).GetKey( ).equals(searchKey))
                found = true;
            } else
                if(created) { //UNORDERED
                    Reset( );
                    while(!AtEnd( ) && 
                            current.GetData( ).GetKey( ).compareTo(searchKey) != 0)
                        GetNext( );

                    if(current != null && current.GetData( ).GetKey( ).equals(searchKey))
                        found = true;
                }
        return found;
    }
    
    private void Insert(Element givenElement) {
        Node tempNode;
        
        //Sets up node to insert
        tempNode = new Node( );
        tempNode.SetData(givenElement.Clone( ));
        
        //locate insertion point
        Search(givenElement.GetKey( ));
        
        //performs the insertion
        if(previous != null)
            previous.SetNext(tempNode);
        else
            start = tempNode;
        
        tempNode.SetNext(current);
        
        current = tempNode;
        
        tempNode = null; //defensive
    }
    
    private void Prepend(Element givenElement) {
        Node tempNode;
        
        tempNode = new Node( );
        tempNode.SetData(givenElement.Clone( ));
        tempNode.SetNext(start);
        start = tempNode;
        tempNode = null;  //defensive
    }
}
