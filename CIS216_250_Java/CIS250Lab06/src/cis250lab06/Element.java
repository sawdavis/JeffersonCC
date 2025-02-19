package cis250lab06;

public class Element {
    private String date, time;
    private double cost;
    
    public void SetDate(String nDate) {
        date = nDate;
    }
    
    public void SetTime(String aTime) {
        time = aTime;
    }
    
    public void SetCost(double aCost) {
        cost = aCost;
    }
    
    public String GetDate( ) {
        return date;
    }
    
    public String GetTime( ) {
        return time;
    }
    
    public double GetCost( ) {
        return cost;
    }
}

