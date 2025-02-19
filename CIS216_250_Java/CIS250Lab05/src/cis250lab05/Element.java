package cis250lab05;

public class Element {
    private long time1, time2, time3;
    
    
    public void Set1(long aTime1) {
        time1 = aTime1;
    }
    
    public void Set2(long aTime2) {
        time2 = aTime2;
    }
    
    public void Set3(long aTime3) {
        time3 = aTime3;
    }
    
    public void SetAll(long aTime1, long aTime2, long aTime3) {
        time1 = aTime1;
        time2 = aTime2;
        time3 = aTime3;
    }
    
    public long GetTime1( ) {
        return time1;
    }
    
    public long GetTime2( ) {
        return time2;
    }
    
    public long GetTime3( ) {
        return time3;
    }
    public Element Clone( ) {
        Element clonedItem;
        
        clonedItem = new Element( );
        clonedItem.SetAll(time1, time2, time3);
        
        return clonedItem;
    }
}
