package cis250lab09;

import java.util.Scanner;
import java.io.*;

public class CIS250Lab09 {
    
    /*
    05/03/2024
    Sawyer Davis
    Lab 07
    Due 05/08/2024
    This program will allow the user to serch for numbers until they decide to
    quit. When the user quits the average number of levels traversed is displayed.
     */ 

    public static void main(String[] args) throws IOException{
        char choice;
        BSTreeADT tree;
        int numSearch, totalCount;
        
        
        numSearch = 0;
        totalCount = 0;
        tree = new BSTreeADT( );
        tree.Create(200000, 7);
        Add(tree);
        choice = '?';
        while(choice != 'N'){
        totalCount = Search(tree, totalCount);     
        choice = GetChoice();   
        numSearch++;
        }
        Display(numSearch, totalCount);
    }
    
    public static void Add(BSTreeADT tree)throws IOException{
        File inf;
        Scanner infSC;
        Element temp;
        int num;
        
        inf = new File("numbers.txt");
        infSC = new Scanner(inf);
        while(infSC.hasNext( ) && tree.IsFull( ) == false) {
            num = infSC.nextInt( );
            temp = new Element( );
            temp.SetNum(num);
            tree.Add(temp);
            //System.out.println(num);
        }
        infSC.close( );
    }
    
    public static char GetChoice(){
        char choice;
        Scanner kbd = new Scanner(System.in);
        
        choice = '?';
        while(choice != 'Y' && choice !='N'){
            System.out.print("Would you like to search for another number? (Y/N) ");
            choice = kbd.nextLine().toUpperCase().charAt(0);
        }
            
        return choice;
    }
    
    public static int Search(BSTreeADT tree, int totalCount){
        Scanner kbd = new Scanner(System.in);
        String searchValue;
        Element temp;
        
        System.out.print("Enter the number to search for: ");
        searchValue = kbd.nextLine();
        temp = tree.Retrieve(searchValue);
        
        if(temp == null)
            System.out.println(searchValue + " NOT FOUND");
        else{
            System.out.println(searchValue + " WAS FOUND");
            System.out.println("Levels to find " + searchValue +": " + tree.GetCount());
            totalCount = totalCount + tree.GetCount();
        }
        
        return totalCount;
    }
    
    public static void Display(int numSearch, int totalCount){
        int avg;
        
        avg = totalCount / numSearch;
        System.out.println("The average levels traversed per search was " + avg);
    }
}
