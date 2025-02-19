package cis250lab01;

import java.util.Scanner;
import java.io.*;

public class CIS250Lab01 {

    /*
    01/28/2024
    Sawyer Davis
    Lab 01
    Due 02/03/2024
    Performs information tracking for ice cream sales. This program keeps track
    of the container type, volume of ice cream, hot toppings, and candy
    toppings. The program will not allow the container to be filled over its
    capacity, and will produce a sale summary that displays the masses and 
    total.
     */ 
    
    public static void main(String[] args) throws IOException {
        String fileName = "IceCreamData.txt";
        IceCreamContainer cone;
        cone = new IceCreamContainer(fileName);
        
        AddEaItem(cone);
        DisplayReceipt(cone);
       
        
    }
    
    public static void AddEaItem(IceCreamContainer cone)  {
        char userChoice;
        char coneChoice;
        int seconds;
        
        userChoice = GetChoice();
        while(userChoice != 'Q'){
            
            if(userChoice == 'N'){
                coneChoice = GetConeChoice();
                cone.SetContainer(coneChoice);
            }
            
            if(userChoice == 'I'){
                seconds = GetSeconds();
                cone.AddIC(seconds);
                
            }
            
            if(userChoice == 'H'){
                seconds = GetSeconds();
                cone.AddHT(seconds);
                
            }
            
            if(userChoice == 'C'){
                seconds = GetSeconds();
                cone.AddC(seconds);
                
            }
            userChoice = GetChoice();
        }
           
        
    }
    
    
    
    public static char GetChoice(){
        char userChoice;
        Scanner kbd = new Scanner(System.in);
        
        System.out.println("Would you like a (N)ew Container, to add (I)ce Cream, to add (H)ot Topping, to add (C)andy, or to (Q)uit");
        userChoice = kbd.nextLine().charAt(0);
        while(userChoice != 'N' && userChoice != 'I' && userChoice != 'C' && userChoice != 'Q' && userChoice != 'H'){
            System.out.println("Would you like a (N)ew Container, to add (I)ce Cream, to add (H)ot Topping, to add (C)andy, or to (Q)uit");
            userChoice = kbd.nextLine().charAt(0);
        }
        return userChoice;
    }
    
    public static char GetConeChoice(){
        char coneChoice;
        Scanner kbd = new Scanner(System.in);
        
        System.out.println("Would you like a (C)up, (R)egular Cone, or (W)affle Cone?");
        coneChoice = kbd.nextLine().charAt(0);
        while(coneChoice != 'C' && coneChoice != 'R' && coneChoice != 'W'){
            System.out.println("Would you like a (C)up, (R)egular Cone, or (W)affle Cone?");
            coneChoice = kbd.nextLine().charAt(0);
        }
        
        return coneChoice;
    }
    
    public static int GetSeconds(){
        int seconds;
        Scanner kbd = new Scanner(System.in);
        
        System.out.println("How many seconds would you like to dispense this item?");
        seconds = kbd.nextInt();
                
        return seconds;
    }
    
    public static void DisplayReceipt(IceCreamContainer cone){
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("Sale Summary");
        System.out.println(" ");
        System.out.println("Cone Information");
        System.out.println("Ice Cream Mass:  " + cone.GetICMass() + " grams");
        System.out.println("Hot Topping Mass:  " + cone.GetHTMass() + " grams");
        System.out.println("Candy Mass:  " + cone.GetCMass() + " grams");
        System.out.println("Total Mass:  " + cone.GetTotalMass() + " grams");
        System.out.println(" ");
        System.out.printf("%s%.2f" ,"Total price: $",cone.GetTotal());
        System.out.println(" ");
        
    }
}
    
