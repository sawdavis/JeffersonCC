package cis250lab09;

import java.io.*;

/*CONSTRAINTS ON APP:
    -must have class named Element
    -Element class must have a GetKey that returns a String
    -Element must have a deep-copy clone method
    -for displaying data, Element must implement a Display method with a char param for order
    -for storing data in a file, Element must have a Store method with a PrintWriter parameter
*/

public class BSTreeADT {
    private Node theRoot;
    private int maxSize;
    private boolean created;
    private int keyWidth;
    private int count;
    
    /*Methods
    done-Add
    Delete (specific)
    done-Retrieve (specific)
    done-Create
    Destroy
    done-GetSize
    done-IsFull
    done-IsEmpty
    Iterators (TRAVERSALS)??    Kind of - the Tree will do all iterations
    */
    
    public int GetCount(){
        return count;
    }
    
    public void Save(String fileName) throws IOException {
        //Desc:  Saves all data in the file specified by the given filename.
        //Pre:  Given filename must be properly loaded.
        //Post:  When the tree exists, a File with the given file name will 
        //              exist with all tree data stored in it.
        //       When the tree does NOT exist, no file is created.
        PrintWriter savePW;
        
        if(created) {
            savePW = new PrintWriter(fileName);
            
            //recur
            theRoot.GetData( ).Store(savePW);
            //SaveNode(savePW, theRoot.GetLeft( ));
            //SaveNode(savePW, theRoot.GetRight( ));
            //end recur
            
            savePW.close( );
        }
    }
    
    private void ShowSubTree(Node aRoot, char order) {
        if(aRoot != null) {
            if(order == 'D') {
                    ShowSubTree(aRoot.GetRight( ), order);
                    aRoot.GetData( ).Display(keyWidth);
                    ShowSubTree(aRoot.GetLeft( ), order);
            }
            else if(order == 'A') {
                ShowSubTree(aRoot.GetLeft( ), order);
                aRoot.GetData( ).Display(keyWidth);
                ShowSubTree(aRoot.GetRight( ), order);
            }
            else if(order == 'R') {
                aRoot.GetData( ).Display(keyWidth);
                ShowSubTree(aRoot.GetLeft( ), order);
                ShowSubTree(aRoot.GetRight( ), order);
            }
            else {
                ShowSubTree(aRoot.GetLeft( ), order);
                ShowSubTree(aRoot.GetRight( ), order);
                aRoot.GetData( ).Display(keyWidth);
            }
        }
    }
    
    public void ShowTree(char order) {   //TRAVERSAL
        //Desc:  Displays all data in the tree.
        //Pre:  order parameter must be properly loaded A-In ASC, D-In DESC, R-pRe, O-pOst.
        //Post:  When the tree is empty or unusable, displays a message.
        //       Otherwirs displays all data in the tree.
        if(!created || IsEmpty( ))
            System.out.println("Nothing to display.");
        else 
            if(theRoot != null) {
                if(order == 'D') {
                    ShowSubTree(theRoot.GetRight( ), order);
                    theRoot.GetData( ).Display(keyWidth);
                    ShowSubTree(theRoot.GetLeft( ), order);
                }
                else if(order == 'A') {
                    ShowSubTree(theRoot.GetLeft( ), order);
                    theRoot.GetData( ).Display(keyWidth);
                    ShowSubTree(theRoot.GetRight( ), order);
                }
                else if(order == 'R') {
                    theRoot.GetData( ).Display(keyWidth);
                    ShowSubTree(theRoot.GetLeft( ), order);
                    ShowSubTree(theRoot.GetRight( ), order);
                }
                else {
                    ShowSubTree(theRoot.GetLeft( ), order);
                    ShowSubTree(theRoot.GetRight( ), order);
                    theRoot.GetData( ).Display(keyWidth);
                }
            }
    }
    
    public Element Retrieve(String searchValue) {
        //Desc:  Returns the element (if possible) that matches the given key.
        //Pre:  Given element must be properly set as a String.
        //Post:  When the tree does not exist, returns null.
        //       When the tree exists and the matching item is found, returns a 
        //          copy of the element.
        //       When the tree exists and the matching item is NOT found (or empty), 
        //          returns null.
        Element returnedElement;
        count = 0;
        if(!created)
            returnedElement = null;
        else
            if(theRoot == null)
                 returnedElement = null;
            else
                if(theRoot.GetData( ).GetKey(keyWidth).equals(searchValue)){
                     returnedElement = theRoot.GetData( ).Clone( );
                     count++;
                }
                else
                    if(theRoot.GetData( ).GetKey(keyWidth).compareTo(searchValue) > 0){
                        //Go left
                        returnedElement = RetrieveNode(searchValue, theRoot.GetLeft( ));
                        count++;
                    }
                    else{
                        //Go right
                        returnedElement = RetrieveNode(searchValue, theRoot.GetRight( ));
                        count++;
                    }
        return returnedElement;
    }
    
    private Element RetrieveNode(String searchValue, Node aRoot) {
        Element returnedElement;
        
        if(aRoot == null)
            returnedElement = null;
        else 
            if(aRoot.GetData( ).GetKey(keyWidth).equals(searchValue)){
                returnedElement = aRoot.GetData( ).Clone( );
                count++;
            }
            else
                if(aRoot.GetData( ).GetKey(keyWidth).compareTo(searchValue) > 0){
                    returnedElement = RetrieveNode(searchValue, aRoot.GetLeft( ));
                    count++;
                }
                else{
                    returnedElement = RetrieveNode(searchValue, aRoot.GetRight( ));
                    count++;
                }

        return returnedElement;
    }
    
    private Node AddNode(Element givenElement, Node aRoot) {
        //System.out.println(givenElement.GetTreeNum( ));
        if(aRoot == null) {
                aRoot = new Node( );
                aRoot.SetData(givenElement.Clone( ));
                //System.out.println(aRoot.GetData( ).GetTreeNum( ));
                aRoot.SetLeft(null);
                aRoot.SetRight(null);
        }
        else if(givenElement.GetKey(keyWidth).compareTo(
                aRoot.GetData( ).GetKey(keyWidth)) < 0)
                        aRoot.SetLeft(AddNode(givenElement, aRoot.GetLeft( )));
             else
                        aRoot.SetRight(AddNode(givenElement, aRoot.GetRight( )));
        
        return aRoot;
    }
    
    public boolean Add(Element givenElement) {
        //Desc:  Places the given element in the correct spot in the tree 
        //              if possible.
        //Pre:  Given element must be properly set.
        //      Given element must have a unique key.
        //Post:  When the tree exists and is NOT full, the given element is 
        //              added, returns a true value.
        //       When the tree does not exist or is full, does not add the 
        //              given element, returns a false value.
        boolean success;
        
        if(IsFull( ) || !created)
            success = false;
        else {
            success = true;
            //System.out.println(givenElement.GetTreeNum( ));
            if(theRoot == null) {
                theRoot = new Node( );
                theRoot.SetData(givenElement.Clone( ));
                theRoot.SetLeft(null);
                theRoot.SetRight(null);
            }
            else if(givenElement.GetKey(keyWidth).compareTo(
                    theRoot.GetData( ).GetKey(keyWidth)) < 0)
                        theRoot.SetLeft(AddNode(givenElement, theRoot.GetLeft( )));
                 else
                        theRoot.SetRight(AddNode(givenElement, theRoot.GetRight( )));
        }
        
        return success;
    }
    
    public BSTreeADT( ) {
        //Desc:  Sets the tree to an unusable state.
        //Pre:  None.
        //Post:  An instance will exist in an unusable state (needs to be created).
        theRoot = null;
        maxSize = -99;
        created = false;
        keyWidth = -9;
    }
    
    public void Create(int uMaxSize, int uKeyWidth) {
        //Desc:  Sets the tree to a usable state with a given maximum size.
        //Pre:  uMaxSize must be a positive, non-0 number.
        //Post:  The tree will exist with the given maximum size (capacity).
        theRoot = null;
        maxSize = uMaxSize;
        created = true;
        keyWidth = uKeyWidth;
    }
    
    public boolean IsEmpty( ) {
        //Desc:  Identifies when there are no items in the tree.
        //Pre:  None.
        //Post:  When the tree exists (was created) and there are items in the 
        //          tree, returns false.
        //       When the tree does NOT exist OR has no items, returns true.
        boolean empty;
        
        //if(created && theRoot != null)
        //    empty = false;
        //else
        //    empty = true;
        
        empty = !(created && theRoot != null);
        
        return empty;
    }
    
    public boolean IsFull( ) {
        //Desc:  Identifies if there is any remaining capacity.
        //Pre:  None.
        //Post:  
        boolean full;
        Node tNode;
        Element tElement;
        
        full = false;
        
        if(!created || GetSize( ) >= maxSize)
            full = true;
        
        try {
            tNode = new Node( );
            tElement = new Element( );
        } catch(OutOfMemoryError e) {
            full = true;
        }
        
        tNode = null;  //defensive
        tElement = null;  //defensive
        
        return full;
    }
    
    public int GetSize( ) {
        //Desc:  Returns the number of items in the tree.
        //Pre:  None.
        //Post:  The number of items will be returned (when the tree does 
        //          not exist, returns 0).
        int count;
        
        if(theRoot == null)
            count = 0;
        else
            count = 1 + GetSubTreeSize(theRoot.GetLeft( )) + 
                    GetSubTreeSize(theRoot.GetRight( ));
        
        return count;
    }
    
    private int GetSubTreeSize(Node aRoot) {
        int count;
        
        if(aRoot == null)
            count = 0;
        else
            count = 1 + GetSubTreeSize(aRoot.GetLeft( )) + 
                    GetSubTreeSize(aRoot.GetRight( ));
        
        return count;
    }
}

