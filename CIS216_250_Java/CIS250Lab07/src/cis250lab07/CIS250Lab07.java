package cis250lab07;

import java.util.Scanner;
import java.io.*;

public class CIS250Lab07{
    
    /*
    04/12/2024
    Sawyer Davis
    Lab 07
    Due 04/22/2024
    This program will allow the user to manage zip code information. The user 
    can look up a zipcode, display all zipcodes for a given place or state.
    Additionally the user can modify, add, or remove zipcodes.
     */ 
    
    public static void main(String[] args) throws IOException{
        ListADT list;
        list = new ListADT();
        
        list.Create(42000, true);
        Load(list);
        ControlMenu(list);
        Save(list);
    }
    
    //Load Info
    public static void Load(ListADT list)throws IOException{  
        File inf;
        Scanner infSC;
        String zip, place, state;
        Element tempElement;
        
        inf = new File("zips.txt");
        infSC = new Scanner(inf);
        tempElement = new Element();
        while(list.IsFull( ) == false && infSC.hasNext()){
            zip = infSC.nextLine();
            place = infSC.nextLine();
            state = infSC.nextLine();
            
            tempElement.Set(zip, place, state);
            list.Add(tempElement);
        }
        infSC.close();
    }
    
    //Get Choice
    public static char GetChoice(){
        char choice;
        choice = '?';
        while(choice != 'A' && choice != 'M' && choice != 'D' && choice != 'L' && choice != 'S' && choice != 'P' && choice != 'Q'){
        Scanner kbd = new Scanner(System.in);
        System.out.println("Zip Code CONTROL MENU");
        System.out.println("-----------------------------");
        System.out.println("(A)dd Zip Code");
        System.out.println("(M)odify Zip Code");
        System.out.println("(D)elete Zip Code");
        System.out.println("(L)ook Up Zip Code");
        System.out.println("(S)tate Zip Codes");
        System.out.println("(P)lace Zip Codes");
        System.out.println("(Q)uit");
        choice = kbd.nextLine().toUpperCase().charAt(0);
        }
        return choice;
    }
    
    //Control Menu
    public static void ControlMenu(ListADT list){
        char choice;
        
        choice = GetChoice();
        while(choice != 'Q'){
            if(choice == 'A'){
                Add(list);
            }
            if(choice == 'M'){
                Modify(list);
            }
            if(choice == 'D'){
                Delete(list);
            }
            if(choice == 'L'){
                LookUp(list);
            }
            if(choice == 'S'){
                State(list);
            }
            if(choice == 'P'){
                Place(list);
            }
            
            choice = GetChoice();
        } 
    }
    
    //Look Up Zip Code
    public static void LookUp(ListADT list){
        Scanner kbd = new Scanner(System.in);
        String searchValue;
        Element temp;
        
        System.out.print("Enter the zip code to look up:  ");
        searchValue = kbd.nextLine( );
        if(list.Search(searchValue)){
            temp = list.Retrieve();
            System.out.printf("%-10s%15s%4s\n", temp.GetZip(), temp.GetPlace(), temp.GetState());
        }
        else
            System.out.println(searchValue + " NOT found.");
    }
    
    //Display Place Zipcodes
    public static void Place(ListADT list){
        Element temp;
        String searchValue;
        Scanner kbd = new Scanner(System.in);
        
        System.out.print("Enter the place to display zipcodes:  ");
        searchValue = kbd.nextLine( );
        list.Reset( );
        while(!list.AtEnd( )) {
            temp = list.Retrieve( );
            if(temp.GetPlace().compareToIgnoreCase(searchValue) == 0)
                System.out.printf("%-10s%15s%4s\n", temp.GetZip(), temp.GetPlace(), temp.GetState());
            list.GetNext( );
        }
    }
    
    //Display State Zipcodes
    public static void State(ListADT list){
        Element temp;
        String searchValue;
        Scanner kbd = new Scanner(System.in);
        
        System.out.print("Enter the state to display zipcodes:  ");
        searchValue = kbd.nextLine( );
        list.Reset( );
        while(!list.AtEnd( )) {
            temp = list.Retrieve( );
            if(temp.GetState().compareToIgnoreCase(searchValue) == 0)
                System.out.printf("%-10s%15s%4s\n", temp.GetZip(), temp.GetPlace(), temp.GetState());
            list.GetNext( );
        }
    }
    
    //Add Zipcode
    public static void Add(ListADT list){
        Scanner kbd = new Scanner(System.in);
        String zip, place, state;
        Element temp;
        
        System.out.print("Enter the zip, place, and state (on separate lines):  ");
        zip = kbd.nextLine( );
        place = kbd.nextLine( );
        state = kbd.nextLine( );
        
        temp = new Element( );
        temp.Set(zip, place, state);
            
        list.Add(temp);
    }
    
    //Modify Zipcode
    public static void Modify(ListADT list){
        Scanner kbd = new Scanner(System.in);
        String searchValue, place, state;
        Element temp;
        
        temp = new Element();
        System.out.print("Enter the zip code to modify:  ");
        searchValue = kbd.nextLine( );
        if(list.Search(searchValue)){
            list.Remove(searchValue);
            System.out.print("Enter the modified place, and state (on separate lines):  ");
            place = kbd.nextLine();
            state = kbd.nextLine();
            
            temp.Set(searchValue, place, state);
            list.Add(temp);
        }
        else
            System.out.println(searchValue + " NOT found nor modified.");
    }
    
    //Delete Zipcode
    public static void Delete(ListADT list){
        Scanner kbd = new Scanner(System.in);
        String searchValue;
        
        System.out.print("Enter the zip code you want deleted:  ");
        searchValue = kbd.nextLine( );
        
        if(list.Remove(searchValue))
            System.out.println(searchValue + " found and deleted.");
        else
            System.out.println(searchValue + " NOT found nor deleted.");
    }
    
    //Save
    public static void Save(ListADT list) throws IOException{
        PrintWriter outPW;
        Element temp;
        
        outPW = new PrintWriter("changed.txt");
        list.Reset( );
        while(!list.AtEnd( )) {
            temp = list.Retrieve( );
            outPW.println(temp.GetZip());
            outPW.println(temp.GetPlace());
            outPW.println(temp.GetState());
            list.GetNext( );
        }
        
        outPW.close();
        list.Destroy( );
    }
}
