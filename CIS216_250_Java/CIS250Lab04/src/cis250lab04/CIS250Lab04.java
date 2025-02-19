package cis250lab04;

import java.util.Scanner;

public class CIS250Lab04 {
    
    /*
    03/01/2024
    Sawyer Davis
    Lab 04
    Due 02/04/2024
    This program will allow users to choose if they would like to test a word
    to see if its a palindrome. The user will enter a word and the program will
    display whether the word is a palindrome or not.
     */ 
    public static void main(String[] args) {
        StackADT letters;
        letters = new StackADT();
        Element element;
        element = new Element();
        char choice;
        String word;
        boolean palindrome;
        
        letters.Create(50); // longest single word palindrome is 19 characters
        
        choice = GetChoice();
        while(choice != 'N'){
            word = GetWord();
            palindrome = TestWord(word, element, letters);
            DisplayResults(word, palindrome);
            choice = GetChoice();
        }
    }
    
    //Get Choice
    public static char GetChoice(){
        char choice;
        Scanner kbd = new Scanner(System.in);
        choice = '?';
        while(choice != 'Y' && choice != 'N'){
            System.out.println("Would you like to test a word?(Y/N)");
            choice = kbd.nextLine().charAt(0);
        }
            
        return choice;
    }
    
    //Get Word
    public static String GetWord(){
        String word;
        Scanner kbd = new Scanner(System.in);
        
        System.out.println("What word would you like to test?");
        word = kbd.nextLine();
        
        return word;
    }
    
    //Test Word
    public static boolean TestWord(String word, Element element, StackADT letters){
        boolean palindrome;
        Element temp;
        String tempWord;
        
        palindrome = true;
        tempWord = word.toUpperCase();
        for(int cnt = 0; cnt < word.length(); cnt++){
            element.SetLetter(tempWord.charAt(cnt));
            letters.Push(element);
        }
        
        for(int cnt = 0; cnt < word.length(); cnt++){
            temp = letters.Pop();
            if(temp.GetLetter() != tempWord.charAt(cnt))
                palindrome = false;               
            }        
        return palindrome;
    }
    
    //Display Results
    public static void DisplayResults(String word, boolean palindrome){
        if(palindrome == true)
            System.out.println(word + " is a palindrome!");
        else
            System.out.println(word + " is not a palindrome!");
    }
}

