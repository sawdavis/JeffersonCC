package cis250lab08;

import java.util.Scanner;

public class CIS250Lab08 {
    /*
    04/25/2024
    Sawyer Davis
    Lab 08
    Due 04/29/2024
    This program will allow the user to convert valid numbers to bases 2-16.
     */ 
    
    public static void main(String[] args) {
        int num;
        int base;
        char [] remainders;
        int ans;
        int remain;
        char choice;
        int index;

        index = 0;
        ans = 0;
        remain = 0;
        remainders = new char[100];
        choice = '?';
        while(choice != 'N'){
        num = GetNumber();
        base = GetBase();
        System.out.print("The converted number is ");
        remainders = Convert(num, base, remainders, ans, remain, index);
        System.out.println();
        choice = GetChoice();
        }
    }
    
    public static int GetNumber(){
        int num;
        Scanner kbd = new Scanner(System.in);
        
        num = -1;
        while(num < 0){
            System.out.print("Enter the number to be converted(POSITIVE): ");
            num = kbd.nextInt();
        }
        
        return num;
    }
    
    public static int GetBase(){
        int base;
        Scanner kbd = new Scanner(System.in);
        
        base = -1;
        while(base < 2 || base > 16){
            System.out.print("Enter the base to convert to (2-16): ");
            base = kbd.nextInt();
        }
        
        return base;
    }
    
    public static char GetChoice(){
        char choice;
        Scanner kbd = new Scanner(System.in);
        
        choice = '?';
        while(choice != 'Y' && choice !='N'){
            System.out.println("Would you like to convert another number? (Y/N) ");
            choice = kbd.nextLine().toUpperCase().charAt(0);
        }
            
        return choice;
    }
    public static char[] Convert(int num, int base, char [] remainders, int ans, int remain, int index){
        ans = num / base;
        remain = num % base;
        num = ans;
        if(remain < 10){
        remainders[index] = (char)(48 + remain);
        }
        else{
        switch(remain){
            case 10:remainders[index] = 'A';
                    break;
            case 11:remainders[index] = 'B';
                    break;
            case 12:remainders[index] = 'C';
                    break;
            case 13:remainders[index] = 'D';
                    break;
            case 14:remainders[index] = 'E';
                    break;
            case 15:remainders[index] = 'F';
                    break;
            }
        }
        if(num != 0){
            index++;
            remainders = Convert(num, base, remainders, ans, remain, index);
            index--;
            System.out.print(remainders[index]);
        }
        else{
            System.out.print(remainders[index]);
        }

        return remainders;
}
}

