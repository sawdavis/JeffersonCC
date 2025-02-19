package cis250lab04;
//Written by a Utility programmer
public class StackADT {
    //CONSTRAINT on Application programmer:  Must provide an object called Element.
    //CONSTRAINT on Application programmer:  Must provide a method named Clone 
    //          that performs a DEEP COPY.
    //Attributes
    private int top;
    private Element [ ] items;
    private boolean created;
    
    //Methods
    /*
    done-SetSize - Create??
    done-IsFull
    done-Remove from stack (POP)
    done-Add to stack (PUSH)
    done-IsEmpty
    Destroy - clear
    done-GetSize
    */
    public void Destroy( ) {
        //Desc:  Sets the stack to an unusable state.
        //Pre:  None.
        //Post:  The stack will be in an unusable state (not created).
        top = -25;
        created = false;
        if(items != null)
            for(int cnt = 0; cnt < items.length; cnt++)
                items[cnt] = null;
        items = null;
    }
    
    public boolean Push(Element givenItem) {
        //Desc:  Adds a COPY of the given item to the top of the stack if possible.
        //Pre:  Stack must not be full.
        //Post:  When the stack exists (created) the given item is added to 
        //          the top of the stack.  Returns a true value.
        //       When the stack does not exist (NOT created) returns a false value.
        boolean success;
        
        if(created) {
            success = true;
            items[top] = givenItem.Clone( );
            top++;
        } else
            success = false;
        
        return success;
    }
    public Element Pop( ) {
        //Desc:  Gives back the top item if possible.
        //Pre:  Stack must not be empty.
        //Post:  When the stack exists (created) gives back the top item.
        //       When the stack does not exist (NOT created) gives back null.
        Element poppedItem;
        
        if(created) {
            poppedItem = items[top - 1];
            items[top - 1] = null;
            top--;
        } else
            poppedItem = null;        
        
        return poppedItem;
    }
    
    public StackADT( ) {
        //Desc:  Ensures the stack is in a not-created state (unusable).
        //Pre:  None.
        //Post:  The stack will exist but be utterly unusable.
        top = -25;
        created = false;
        items = null;
    }
    
    public void Create( ) {
        //Desc:  Creates a usable stack that is empty using a default size.
        //Pre:  None.
        //Post:  The stack will exist as an empty stack with the default capacity.
        items = new Element[10];
        top = 0;
        created = true;
    }
    
    public void Create(int specifiedSize) {
        //Desc:  Creates a usable stack that is empty using the specified size.
        //Pre:  The specified size must be greater than 0.
        //Post:  The stack will exist as an empty stack with the specified capacity.
        items = new Element[specifiedSize];
        top = 0;
        created = true;
    }
    
    public int GetSize( ) {
        //Desc:  Returns the number of items on the stack.
        //Pre:  None.
        //Post:  When the stack exists, returns the number of items and remains unchanged.
        //       When the stack does not exist, returns a negative number.
        return top;
    }
    
    public boolean IsFull( ) {
        //Desc:  Identifies if the stack has any remaining capacity.
        //Pre:  None.
        //Post:  When the stack exists and is NOT full returns false and remains unchanged.
        //       When the stack exists and is full returns true and remains unchanged.
        //       When the stack does not exist returns a "false" true.
        boolean full;
        
        if(!created || top == items.length)
            full = true;
        else
            full = false;
        
        return full;
    }
    
    public boolean IsEmpty( ) {
        //Desc:  Identifies if the stack is bereft of items.
        //Pre:  None.
        //Post:  When the stack exists and is NOT empty returns false and remains unchanged.
        //       When the stack exists and is empty returns true and remains unchanged.
        //       When the stack does not exist returns a "false" true.
        boolean empty;
        
        if(!created || top == 0)
            empty = true;
        else
            empty = false;
        
        return empty;
    }
}

