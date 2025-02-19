package laboratory10;

import java.util.Scanner;
import java.io.*;

public class Laboratory10 {

    /*
    05/08/2023
    Sawyer Davis
    Lab 10
    Due 05/09/2023
    Performs parts inventory management for a car dealership. It allows the user
    to serach for a product, add products to the inventory,
    delete a product from iventory, edit a products price or quantity,
    or display all products. All information is then saved to a file once
    inventory management is conclcuded. 
     */
    public static void main(String[] args) throws IOException {
        Part[] theInventory;
        theInventory = new Part[100];
        int numParts;
        numParts = LoadInventory(theInventory);
        numParts = PerformEachTask(theInventory, numParts);
        SaveInventory(numParts , theInventory);
    }

    //Load Inventory
    public static int LoadInventory(Part[] theInventory) throws IOException {
        int numParts;
        Part temp;
        numParts = 0;
        int loc;
        File partsFile;
        Scanner partsFileSC;
        partsFile = new File("parts.txt");
        partsFileSC = new Scanner(partsFile);

        while (numParts < theInventory.length && partsFileSC.hasNext()) {
            temp = new Part();
            temp.Load(partsFileSC);
            if (numParts < theInventory.length) {
                loc = 0;
                while (loc < numParts && temp.GetID().compareTo(theInventory[loc].GetID()) > 0) {
                    loc++;
                }
                for (int cnt = numParts; cnt > loc; cnt--) {
                    theInventory[cnt] = theInventory[cnt - 1];
                }
                theInventory[loc] = temp;
                numParts++;
            }
        }

        return numParts;
    }

    //Perform Each Task
    public static int PerformEachTask(Part[] theInventory, int numParts) {
        char userChoice = 'O';
        while (userChoice != 'E') {
            userChoice = GetUserChoice();
            numParts = PerformUserChoice(userChoice, numParts, theInventory);
        }
        return numParts;
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
    public static int PerformUserChoice(char userChoice, int numParts, Part[] theInventory) {

        if (userChoice == 'A') {
            numParts = AddNew(theInventory, numParts);
        }
        if (userChoice == 'S') {
            DisplayAll(theInventory, numParts);
        }
        if (userChoice == 'L') {
            LookUp(theInventory, numParts);
        }
        if (userChoice == 'Q') {
            ChangeQuantity(theInventory, numParts);
        }
        if (userChoice == 'P') {
            ChangePrice(theInventory, numParts);
        }
        if (userChoice == 'D') {
            numParts = DeletePart(theInventory, numParts);
        }
        if (userChoice == 'E') {
            System.out.println("YOU HAVE EXITED THE PROGRAM");
        }

        return numParts;
    }

    //Look Up
    public static void LookUp(Part[] theInventory, int numParts) {
        Scanner kbd = new Scanner(System.in);
        String searchValue;
        System.out.println("Enter the part you are looking for:   ");
        searchValue = kbd.nextLine();
        int location = 0;

        while (location < numParts && searchValue.compareTo(theInventory[location].GetID()) != 0) {
            location++;
        }

        if (location < numParts && searchValue.compareTo(theInventory[location].GetID()) == 0) {
            System.out.printf("\n%-15s%15s%10s%20s\n", "Product ID", "Product Description",
                    "Price", "Quantity");
            System.out.printf("%-15s%19s%10.2f%20d\n\n", theInventory[location].GetID(),
                    theInventory[location].GetDescription(), theInventory[location].GetPrice(), theInventory[location].GetQuantity());
        } else {
            System.out.println("PRODUCT NOT FOUND");
        }

    }

    //Add New
    public static int AddNew(Part[] theInventory, int numParts) {
        Scanner kbd = new Scanner(System.in);
        int nQuantity;
        String nProductID, nDescription;
        double nPrice;
        int loc;

        if (numParts < theInventory.length) {
            System.out.print("Enter the new product ID:  ");
            nProductID = kbd.nextLine();
            System.out.print("Enter the description of the product: ");
            nDescription = kbd.nextLine();
            System.out.print("Enter the new product price:  ");
            nPrice = kbd.nextDouble();
            while (nPrice < 0) {
                System.out.print("Enter the new product price:  ");
                nPrice = kbd.nextDouble();
            }
            System.out.print("Enter the new product quantity:  ");
            nQuantity = kbd.nextInt();
            kbd.nextLine();
            while (nQuantity < 0) {
                System.out.print("Enter the new product quantity:  ");
                nQuantity = kbd.nextInt();
                kbd.nextLine();
            }
            if (numParts < theInventory.length) {
                loc = 0;
                while (loc < numParts && nProductID.compareTo(theInventory[loc].GetID()) > 0) {
                    loc++;
                }
                for (int cnt = numParts; cnt > loc - 1; cnt--) {
                    theInventory[cnt] = theInventory[cnt - 1];
                }
                theInventory[loc] = new Part();
                theInventory[loc].Set(nProductID, nDescription, nQuantity, nPrice);
                numParts++;
            }
        } else {
            System.out.println("Cannot add:  product list is full.");
        }
        return numParts;
    }

    //Delete Part
    public static int DeletePart(Part[] theInventory, int numParts) {
        Scanner kbd = new Scanner(System.in);
        String searchValue;
        System.out.printf("Enter the Part to be deleted:  ");
        searchValue = kbd.nextLine();
        int location;

        location = 0;
        while (location < numParts && searchValue.compareTo(theInventory[location].GetID()) > 0) {
            location++;
        }

        //Delete match - if possible
        if (location < numParts && searchValue.equals(theInventory[location].GetID())) {
            for (int current = location; current < numParts - 1; current++) {
                theInventory[current] = theInventory[current + 1];
            }
            numParts--;
        }

        return numParts;
    }

    //Change Quantity
    public static void ChangeQuantity(Part[] theInventory, int numParts) {
        Scanner kbd = new Scanner(System.in);
        String searchValue;
        System.out.println("Enter the part you are looking for:   ");
        searchValue = kbd.nextLine();
        int location = 0;

        while (location < numParts && searchValue.compareTo(theInventory[location].GetID()) != 0) {
            location++;
        }

        if (location == numParts) {
            System.out.println("PRODUCT NOT FOUND");
        } else {
            System.out.println("What is the new quantity: ");
            theInventory[location].SetQuantity(kbd.nextInt());
        }

    }

    //Change Price
    public static void ChangePrice(Part[] theInventory, int numParts) {
        Scanner kbd = new Scanner(System.in);
        String searchValue;
        System.out.println("Enter the part you are looking for:   ");
        searchValue = kbd.nextLine();
        int location = 0;

        while (location < numParts && searchValue.compareTo(theInventory[location].GetID()) != 0) {
            location++;
        }

        if (location == numParts) {
            System.out.println("PRODUCT NOT FOUND");
        } else {
            System.out.println("What is the new price: ");
            theInventory[location].SetPrice(kbd.nextDouble());
        }
    }

    //Display All
    public static void DisplayAll(Part[] theInventory, int numParts) {

        System.out.printf("\n%-15s%15s%10s%20s\n", "Product Id", "Product Description",
                "Price", "Quantity");

        for (int cnt = 0; cnt < numParts; cnt++) {
            System.out.printf("%-15s%-14s%15.2f%20d\n", theInventory[cnt].GetID(), theInventory[cnt].GetDescription(),
                    theInventory[cnt].GetPrice(), theInventory[cnt].GetQuantity());
        }
        System.out.println();
    }

    //SaveInventory
    public static void SaveInventory(int numItems, Part[] theInventory) throws IOException {
        PrintWriter partsPW;

        partsPW = new PrintWriter("parts.txt");
        for (int cnt = 0; cnt < numItems; cnt++) {
            partsPW.println(theInventory[cnt].GetID());
            partsPW.println(theInventory[cnt].GetDescription());
            partsPW.println(theInventory[cnt].GetPrice());
            partsPW.println(theInventory[cnt].GetQuantity());
        }
        partsPW.close();
    }
}
