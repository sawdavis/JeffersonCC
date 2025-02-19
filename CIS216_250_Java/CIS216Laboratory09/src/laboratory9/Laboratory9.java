package laboratory9;

import java.io.*;
import java.util.Scanner;

public class Laboratory9 {

    /*
    04/26/2023
    Sawyer Davis
    Lab 07
    Due 05/02/2023
    Performs billing for a local water company. The user can either produce
    bills for all customers in the system, or choose to add additional readings
    to the list of customers. This reads and writes customer information to a
    file, and gets its rates from an additional file.*/
    public static void main(String[] args) throws IOException {
        rates theRates;
        theRates = new rates();

        theRates.LoadRates();
        PerformEachTask(theRates);

    }

    //Perform Each Task
    public static void PerformEachTask(rates theRates) throws IOException {
        char userChoice;
        char choice;
        Scanner kbd = new Scanner(System.in);
        
        userChoice = 'P';
        while (userChoice != 'Q') {
            choice = GetUserChoice(kbd);
            PerformChoice(choice, theRates);

            System.out.print("Would you like to (P)roceed or (Q)uit?    ");
            userChoice = kbd.nextLine().charAt(0);
        }
    }

    //Get User Choice
    public static char GetUserChoice(Scanner kbd) {
        char choice;

        System.out.print("Would you like to (G)enerate Bills or (A)dd a Reading?    ");
        choice = kbd.nextLine().charAt(0);
        while (choice != 'G' && choice != 'A') {
            System.out.print("Would you like to (G)enerate Bills or (A)dd a Reading?    ");
            choice = kbd.nextLine().charAt(0);
        }

        return choice;

    }

    //Perform User Choice
    public static void PerformChoice(char choice, rates theRates) throws IOException{
        
        
        if(choice == 'G')
            GeneratesBills(theRates);
        
        if(choice == 'A')
            AddReading();
         
    }

    //Generates Bills
    public static void GeneratesBills(rates theRates) throws IOException {
        File customerFile;
        Scanner customerFileSC;
        customerFile = new File("custreadings.txt");
        customerFileSC = new Scanner(customerFile);
        customer givenCustomer;
        givenCustomer = new customer();
        
        while(customerFileSC.hasNext()){
            givenCustomer.Load(customerFileSC);
           // theRates.DetermineAmountDue(givenCustomer);
            DisplayBill(givenCustomer, theRates);
        }
    }

    //Display Bill
    public static void DisplayBill(customer givenCustomer, rates theRates) {
        System.out.printf("%40s\n", "Miraculous Water Systems, INC.");
        System.out.printf("%33s\n", "918 Water Street");
        System.out.printf("%34s\n\n", "Watertown, NY 13601");
        System.out.printf("%43s\n\n", "If it is good water, it is Miraculus");
        System.out.println(givenCustomer.GetName());
        System.out.println(givenCustomer.GetStreet());
        System.out.println(givenCustomer.GetCity() + ", " + givenCustomer.GetState() + " " + givenCustomer.GetZip());
        System.out.println("");
        System.out.println("Meter:  " + givenCustomer.GetMeterNumber());
        System.out.println("Monthly Usage:  " + givenCustomer.GetUsage());
        System.out.printf("%-1s%1.2f", "Amount Due: ", theRates.DetermineAmountDue(givenCustomer));
        System.out.println("");
        System.out.println("");
        
    }

    //Add Reading
    public static void AddReading() throws IOException{
        customer givenCustomer;
        
        givenCustomer = GetCustomerInformation();
        givenCustomer.Store();

    }

    //Get Customer Information
    public static customer GetCustomerInformation() {
        customer givenCustomer;
        givenCustomer = new customer();
        Scanner kbd = new Scanner(System.in);
        String nName, nStreet, nCity, nState, nZip, nMeterNumber;
        double nUsage;
        
        System.out.print("What is the customer name?    ");
        nName = kbd.nextLine();
        System.out.print("What is the Street?    ");
        nStreet = kbd.nextLine();
        System.out.print("What is the City?    ");
        nCity = kbd.nextLine();
        System.out.print("What is the State?    ");
        nState = kbd.nextLine();
        System.out.print("What is the Zip?    ");
        nZip = kbd.nextLine();
        System.out.print("What is the Meter Number?    ");
        nMeterNumber = kbd.nextLine();
        System.out.print("What is the Usage?    ");
        nUsage = kbd.nextDouble();
        
        givenCustomer.Set(nName, nStreet, nCity, nState, nZip, nMeterNumber, nUsage);
        return givenCustomer;
        
    }
}
