package cis250lab06;

import java.util.Scanner;
import java.io.*;

public class CIS250Lab06 {
    /*
    03/21/2024
    Sawyer Davis
    Lab 06
    Due 03/27/2024
    This program will allow users control gasoline consumption. Users can add
    lookup or delete individual items. When the user decides to quit an expense
    report is then created.
     */ 
    public static void main(String[] args) throws IOException{
        Node start;
        
        start = Load();
        start = ControlMenu(start);
        Save(start);
    }
    
    //Load Info
    public static Node Load()throws IOException{
        boolean memErr;
        Node start;
        Element tempElement;
        Node tempNode;
        File inf;
        Scanner infSC;
        String date, time;
        double cost;
        int numItems;
        
        numItems = 0;
        memErr = false;
        start = null;
        inf = new File("gas.txt");
        infSC = new Scanner(inf);
        while(!memErr && infSC.hasNext( )) {
            date = infSC.nextLine( );
            time = infSC.nextLine( );
            cost = infSC.nextDouble( );
            infSC.nextLine( );
            numItems ++;
            
            try {
                tempElement = new Element( );
                tempElement.SetDate(date);
                tempElement.SetTime(time);
                tempElement.SetCost(cost);

                tempNode = new Node( );
                tempNode.SetElement(tempElement);
                tempNode.SetNext(start);
                
                memErr = (tempElement == null || tempNode == null);
                
                start = tempNode;
                tempNode = null;
            } catch(Error e) {
                memErr = true;
            }
            
        }
        infSC.close( );
        if(!memErr) 
            System.out.println("No memory issues.");
        else
            System.out.println("Unable to fully load:  memory issues.");
        
    return start;
    }
    
    //Control Menu
    public static Node ControlMenu(Node start){
        char choice;
        
        choice = GetChoice();
        while(choice != 'Q'){
            if(choice == 'A'){
                start = Add(start);
            }
            if(choice == 'L'){
                LookUp(start);
            }
            if(choice == 'D'){
                start = Delete(start);
            }
            if(choice == 'G'){
                GenerateReport(start);
            }
            choice = GetChoice();
        }
        
        return start;
    }
    
    //Get Choice
    public static char GetChoice(){
        char choice;
        choice = '?';
        while(choice != 'A' && choice != 'L' && choice != 'D' && choice != 'G' && choice != 'Q'){
        Scanner kbd = new Scanner(System.in);
        System.out.println("GAS LOG CONTROL MENU");
        System.out.println("-----------------------------");
        System.out.println("(A)dd Item");
        System.out.println("(L)ook Up Item");
        System.out.println("(D)elete Item");
        System.out.println("(G)enerate Report");
        System.out.println("(Q)uit");
        choice = kbd.nextLine().toUpperCase().charAt(0);
        }
        return choice;
    }
    
    //Add Item
    public static Node Add(Node start){
        String date, time;
        double cost;
        Scanner kbd = new Scanner(System.in);
        Element tempElement;
        Node tempNode, prev, current;
        
        System.out.print("Enter the new date to insert:  ");
        date = kbd.nextLine( );
        System.out.print("Enter the new time to insert:  ");
        time = kbd.nextLine( );
        System.out.print("Enter the new cost to insert:  ");
        cost = kbd.nextDouble( );
        kbd.nextLine( );
        

        tempElement = new Element();
        tempElement.SetDate(date);
        tempElement.SetTime(time);
        tempElement.SetCost(cost);
        
        tempNode = new Node( );
        tempNode.SetElement(tempElement);
        tempNode.SetNext(start);
        start = tempNode;
        
        return start;
    }
    
    //Look Up Item
    public static void LookUp(Node start){
        String sDate;
        Scanner kbd = new Scanner(System.in);
        Node current;
        
        
        System.out.print("Enter the date to look up:  ");
        sDate = kbd.nextLine( );
        
        current = start;
        while(current != null && current.GetElement( ).GetDate( ).compareTo(sDate) != 0)
            current = current.GetNext( );
        if(current != null) {
            System.out.println("Date: " + current.GetElement( ).GetDate( ));
            System.out.println("Time: " + current.GetElement( ).GetTime( ));
            System.out.println("Cost: $" + current.GetElement( ).GetCost( ));
        }
        else
            System.out.println("Cannot Find Date");
    }
    
    //Delete Item
    public static Node Delete(Node start){
        String sDate;
        Scanner kbd = new Scanner(System.in);
        Node current, prev;
        
        System.out.print("Enter the date to delete:  ");
        sDate = kbd.nextLine( );
        
        prev = null;
        current = start;
        while(current != null && 
                current.GetElement( ).GetDate( ).compareTo(sDate) != 0) {
            prev = current;
            current = current.GetNext( );
        }
        
        if(current != null) {
            if(prev != null)
                prev.SetNext(current.GetNext( ));
            else
                start = current.GetNext( );  
            current.GetElement( ).SetDate("XSDCDSS"); 
            current.GetElement( ).SetTime("XSDCDSS"); 
            current.GetElement( ).SetCost(-999); 
            current.SetElement(null); 
            current.SetNext(null); 
            current = start;
            prev = null;
            System.out.println(sDate + " found/removed.");
        } else
            System.out.println(sDate + " not found/removed.");
        
        return start;
    }
    
    //Generate Report
    public static void GenerateReport(Node start){
        Node current;
        int numItems;
        double total, avg;
        
        
        numItems = 0;
        total = 0;
        current = start;
        System.out.println("Gas Expenditure Report");
        System.out.println("-----------------------------");
        System.out.printf("%-12s%8s%12s\n", "Date", "Time", "Cost");
        while(current != null) {
            System.out.printf("%-12s%8s%7c%4.2f\n", current.GetElement( ).GetDate( ),
                    current.GetElement( ).GetTime( ), '$', current.GetElement( ).GetCost( ));
            total = total + current.GetElement().GetCost();
            current = current.GetNext( );
            numItems++;
        }
        avg = total / numItems;
        System.out.printf("%-26s%1c%4.2f\n", "Total Cost:", '$', total);
        System.out.printf("%-26s%1c%4.2f\n", "Average Cost:", '$', avg);
        System.out.println("-----------------------------");
        System.out.println("");
    }
    
    //Generate Report
    public static void Save(Node start)throws IOException {
        Node current;
        PrintWriter outPW;
        
        
        outPW = new PrintWriter("changed.txt");
        current = start;
        while(current != null) {
            outPW.println(current.GetElement().GetDate());
            outPW.println(current.GetElement().GetTime());
            outPW.println(current.GetElement().GetCost());
            current = current.GetNext( );
        }
        
        outPW.close();
    }
    
}
