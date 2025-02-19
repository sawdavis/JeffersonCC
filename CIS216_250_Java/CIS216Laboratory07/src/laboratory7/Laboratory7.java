package laboratory7;

import java.util.Scanner;
import java.io.*;

public class Laboratory7 {

    /*
    04/11/2023
    Sawyer Davis
    Lab 07
    Due 04/11/2023
    Performs parts inventory management for a car dealership. It allows the user
    to serach for a product, add products to the inventory,
    delete a product from iventory, edit a products price or quantity,
    or display all products. All information is then saved to a file once
    inventory management is conclcuded. 
     */
    public static void main(String[] args) throws IOException {
        int[] iD;
        iD = new int[100];
        String[] description;
        description = new String[100];
        double[] price;
        price = new double[100];
        int[] quantity;
        quantity = new int[100];
        int numItems;

        numItems = GetPartInfo(iD, description, price, quantity);
        numItems = PerformEachTask(numItems, iD, description, quantity, price);
        SaveProducts(numItems, iD, description, price, quantity);
    }

    //Get Part Info
    public static int GetPartInfo(int[] iD, String[] description, double[] price, int[] quantity) throws IOException {
        int numItems = 0;
        File partsFile;
        Scanner partsFileSC;
        partsFile = new File("parts.txt");
        partsFileSC = new Scanner(partsFile);

        while (partsFileSC.hasNext() && numItems < iD.length) {
            iD[numItems] = partsFileSC.nextInt();
            partsFileSC.nextLine();
            description[numItems] = partsFileSC.nextLine();
            price[numItems] = partsFileSC.nextDouble();
            quantity[numItems] = partsFileSC.nextInt();

            numItems++;

        }

        return numItems;
    }

    //Perfrom Each Task
    public static int PerformEachTask(int numItems, int[] iD,
            String[] description, int[] quantity,
            double[] price) {
        char userChoice = 'O';
        while (userChoice != 'E') {
            userChoice = GetUserChoice();
            numItems = PerformUserChoice(userChoice, numItems, iD, description, price, quantity);
        }
        return numItems;
    }

    //Save Products
    public static void SaveProducts(int numItems, int[] iD, String[] description, double[] price, int[] quantity) throws IOException {
        PrintWriter partsPW;

        partsPW = new PrintWriter("parts.txt");
        for (int cnt = 0; cnt < numItems; cnt++) {
            partsPW.println(iD[cnt]);
            partsPW.println(description[cnt]);
            partsPW.println(price[cnt]);
            partsPW.println(quantity[cnt]);
        }
        partsPW.close();
    }

    //Get User Choice
    public static char GetUserChoice() {
        Scanner kbd = new Scanner(System.in);
        char userChoice;
        System.out.print("Would you like to (A)dd Part, (S)ee All Parts, (L)ook Up Part, Change (Q)uantity, Change (P)rice, (D)elete Part, or (E)xit the Program?   ");
        userChoice = kbd.nextLine().toUpperCase().charAt(0);
        
        return userChoice;
    }

    //Perform User Choice
    public static int PerformUserChoice(char userChoice, int numItems, int[] iD,
            String[] description, double[] price, int[] quantity) {

        if (userChoice == 'A') {
            numItems = AddNewParts(numItems, iD, description, price, quantity);
        }
        if (userChoice == 'S') {
            DisplayAll(numItems, iD, description, price, quantity);
        }
        if (userChoice == 'L') {
            numItems = LookUpPart(numItems, iD, description, price, quantity);
        }
        if (userChoice == 'Q') {
            ChangeQuantity(numItems, iD, description, price, quantity);
        }
        if (userChoice == 'P') {
            ChangePrice(numItems, iD, description, price, quantity);
        }
        if (userChoice == 'D') {
            numItems = DeletePart(numItems, iD, description, price, quantity);
        }
        if (userChoice == 'E') {
            System.out.println("YOU HAVE EXITED THE PROGRAM");
        }

            return numItems;
        }
        
        
        //Add New Parts
        public static int AddNewParts(int numItems, int[] iD, String[] description, double[] price, int[] quantity) {

        char done;
        Scanner kbd = new Scanner(System.in);

        if (numItems < iD.length) {
            do {
                System.out.print("Enter the new product ID:  ");
                iD[numItems] = kbd.nextInt();
                kbd.nextLine();
                System.out.print("Enter the description of the product: ");
                description[numItems] = kbd.nextLine();
                System.out.print("Enter the new product price:  ");
                price[numItems] = kbd.nextDouble();
                while (price[numItems] < 0) {
                    System.out.print("Enter the new product price:  ");
                    price[numItems] = kbd.nextDouble();
                }
                System.out.print("Enter the new product quantity:  ");
                quantity[numItems] = kbd.nextInt();
                kbd.nextLine();
                while (quantity[numItems] < 0) {
                    System.out.print("Enter the new product quantity:  ");
                    quantity[numItems] = kbd.nextInt();               
                    kbd.nextLine();
                }
                
                numItems++;
                System.out.print("Are you done?  (Y/N)  ");
                done = kbd.nextLine().toUpperCase().charAt(0);
            } while (done == 'N' && numItems < iD.length);
        } else {
            System.out.println("Cannot add:  product list is full.");
        }
        return numItems;
    }
        

    //Display All
    public static void DisplayAll(int numItems, int[] iD,
            String[] description, double[] price, int[] quantity) {

        System.out.printf("\n%-15s%15s%10s%20s\n", "Product Id", "Product Description",
                "Price", "Quantity");

        for (int cnt = 0; cnt < numItems; cnt++) {
            System.out.printf("%-15d%-14s%15.2f%20d\n", iD[cnt], description[cnt],
                    price[cnt], quantity[cnt]);
        }
        System.out.println();
    }

    //Look Up Part
    public static int LookUpPart(int numItems, int[] iD,
            String[] description, double[] price, int[] quantity) {
        int location;
        location = PerformSearch(iD, numItems);
        PerformLookUp(numItems, iD, description, price, quantity, location);
        return numItems;
    }

    //Perform Search
    public static int PerformSearch(int[] iD, int numItems) {
        Scanner kbd = new Scanner(System.in);
        int searchValue;
        int location = 0;
        //kbd.nextLine();
        System.out.println("Enter the part you are looking for:   ");
        searchValue = kbd.nextInt();
        while (location < numItems && searchValue != (iD[location])) {
            location++;
        }
        return location;

    }

    //Perform Look Up
    public static void PerformLookUp(int numItems, int[] iD, String[] description, double[] price, int[] quantity, int location) {
        if (location < numItems) {
            System.out.printf("\n%-15s%15s%10s%20s\n", "Product ID", "Product Description",
                    "Price", "Quantity");
            System.out.printf("%-15d%19s%10.2f%20d\n\n", iD[location],
                    description[location], price[location], quantity[location]);
        } else {
            System.out.println("PRODUCT NOT FOUND");
        }
    }

    //Change Quantity
    public static void ChangeQuantity(int numItems, int[] iD,
            String[] description, double[] price, int[] quantity) {
        int location;
        location = PerformSearch(iD, numItems);
        PerformChangeQuantity(numItems, quantity, location);

    }

    //Perform Change Quantity
    public static void PerformChangeQuantity(int numItems, int[] quantity, int location) {
        Scanner kbd = new Scanner(System.in);
        if (location == numItems) {
            System.out.println("PRODUCT NOT FOUND");
        } else {
            kbd.nextLine();
            System.out.println("What is the new quantity: ");
            quantity[location] = kbd.nextInt();
        }
    }

    //Change Price
    public static void ChangePrice(int numItems, int[] iD,
            String[] description, double[] price, int[] quantity) {
        int location;
        location = PerformSearch(iD, numItems);
        PerformChangePrice(numItems, price, location);
    }

    //Perform Change Price
    public static void PerformChangePrice(int numItems, double[] price, int location) {
        Scanner kbd = new Scanner(System.in);
        if (location == numItems) {
            System.out.println("PRODUCT NOT FOUND");
        } else {
            kbd.nextLine();
            System.out.println("What is the new price: ");
            price[location] = kbd.nextDouble();
        }
    }

    //Delete Part
    public static int DeletePart(int numItems, int[] iD,
            String[] description, double[] price, int[] quantity) {
        int location;
        location = PerformSearch(iD, numItems);
        numItems = PerformDeletePart(numItems, iD, description, price, quantity, location);

        return numItems;
    }

    //Perform Delete Part
    public static int PerformDeletePart(int numItems, int[] iD, String[] description, double[] price, int[] quantity, int location) {

        if (location < numItems) {
            System.out.println("Part " + iD[location] + " has been deleted.");
            iD[location] = iD[numItems - 1];
            description[location] = description[numItems - 1];
            price[location] = price[numItems - 1];
            quantity[location] = quantity[numItems - 1];

            numItems--;
        }
        return numItems;
    }
    
    
}
