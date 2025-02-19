package cis250lab05;

public class QueueADT {
    private Element [ ] items;
    private int front, back;
    private boolean created;

    /*METHODS
        done-Enqueue
        done-Dequeue
        GetNumber
        done-IsFull
        done-IsEmpty
        done-Create
        done-Destroy
        */
    public void Destroy( ) {
        //Desc:  Puts the queue in an unusable state.
        //Pre:  None.
        //Post:  The queue shall no longer exist (be not created).
        for(int cnt = 0; cnt < items.length; cnt++)
            items[cnt] = null;
        created = false;
        front = -10;
        back = -100;
        items = null;
    }
    
    public boolean Enqueue(Element givenElement) {
        //Desc:  Adds a copy of the given element to the end of the queue, if possible.
        //Pre:  Given element must be properly loaded.
        //Post:  When the queue is full or the queue does not exist (NOT created)
        //          returns flase - the queue is unchanged.
        //       When the queue is not full and has been created, adds the copy and returns true.
        boolean added;
        
        if(!created || IsFull( ))
            added = false;
        else {
            added = true;
            items[back] = givenElement.Clone( );
            back = (back + 1) % items.length;
        }
        
        return added;
    }
    
    public Element Dequeue( ) {
        //Desc:  Removes/returns the front element if possible.
        //Pre:  None.
        //Post:  When the queue exists (created) AND it is not empty, gives 
        //          back the front element.
        //       When the queue does not exist (not created) OR is empty,
        //          gives back null.
        Element elementToReturn;
        if(created && !IsEmpty( )) {//IsEmpty( ) == false)
            elementToReturn = items[front];
            items[front] = null;
            /*front++;
            if(front == items.length)
                front = 0;*/
            front = (front + 1) % items.length;
        }
        else
            elementToReturn = null;
        
        return elementToReturn;
    }
    
    public boolean IsEmpty( ) {
        //Desc:  Identifies if the queue is empty or not.
        //Pre:  None.
        //Post:  When the queue exists AND is empty, returns true.
        //       When the queue exists (created) and has items in it, returns false.
        //       When the queue does NOT exist (NOT created), returns true.
        boolean empty;
        
        empty = true;
        if(created && front != back)
            empty = false;
        
        return empty;
    }
    
    public boolean IsFull( ) {
        //Desc:  Identifies if the queue has any capacity remaining (i.e. can 
        //              be enqueued).
        //Pre:  None.
        //Post:  When the queue exists (was created) and there is capacity, 
        //              returns false.
        //       When the queue does NOT exist (NOT created), returns true 
        //              - technically there is no way to enqueue anything.
        //       When the queue does exist (created) and has NO capacity,
        //              returns true.
            boolean full;
            if(!created || (back + 1) % items.length == front)
                full = true;
            else
                full = false;
           return full;
    }
    
    public void Create(int givenSize) {
        //Desc:  Establishes a Queue with a given size.
        //Pre:  The given size must be greater than 0.
        //Post:  The queue will exist (have been created) as an empty queue 
        //          with the given size.
        created = true;
        items = new Element[givenSize + 1];
        front = 0;
        back = 0;
    }
    
    public QueueADT( ) {
        //Desc:  Initializes the Queue to an UNUSABLE state.
        //Pre:  None.
        //Post:  The Queue will NOT exist and is UNUSABLE.
        created = false;
        items = null;
        front = -10;
        back = -100;
    }
}

