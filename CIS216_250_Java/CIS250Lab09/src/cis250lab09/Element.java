package cis250lab09;


import java.io.*;

public class Element {
    private int num;
    
    public void Display(int numDigits) {
        System.out.printf("%s %-20s%10d\n", GetKey(numDigits));
    }
    
    public void SetNum(int uNum) {
        num = uNum;
    }
    
    
    public int GetNum( ) {
        return num;
    }
    
    public String GetKey(int digits) {
        String key;
        
        key = String.format("%0"+digits+"d", num);
        //System.out.println(key);
        return key;
    }
    
    public Element Clone( ) {
        Element clonedElement;
        
        clonedElement = new Element( );
        clonedElement.SetNum(num);
        
        return clonedElement;
    }
    
    public void Store(PrintWriter savePW) {
        savePW.println(num);

    }
}

