package laboratory04;

import java.util.Scanner;
import java.io.*;

public class Laboratory04 {

    /*
    03/06/2023
    Sawyer Davis
    Lab 04
    Due 03/07/2023
    Create a java program that will perform inventory for a paint company.
    This will include updating product info, searching for products,
    and adding new products to the inventory.
     */
    public static void main(String[] args) throws IOException {
        //Variables
        char userChoice;
        Scanner kbd = new Scanner(System.in);
        userChoice = 7;

        while (userChoice != 'Q') {
            userChoice = GetUserChoice(kbd);
            ProcessUserChoice(userChoice, kbd);
        }
    }

    //Get User Choice
    public static char GetUserChoice(Scanner kbd) {
        char userChoice;

        do {
            System.out.print("Would you like to (L)ook Up Item, (M)odify Item,"
                    + " (A)dd Item, or (Q)uit Program:   ");
            userChoice = kbd.nextLine().charAt(0);
        } while (userChoice != 'L' && userChoice != 'M' && userChoice != 'A'
                && userChoice != 'Q');

        return userChoice;
    }

    //Process User Choice
    public static void ProcessUserChoice(char userChoice, Scanner kbd)
            throws IOException {
        if (userChoice == 'L') {
            LookUpItem(kbd);
        }
        if (userChoice == 'M') {
            ModifyItem(kbd);
        }
        if (userChoice == 'A') {
            AddProduct(kbd);
        }
        if (userChoice == 'Q') {
            System.out.println("YOU HAVE QUIT THE PROGRAM");
        }

    }

    //Look Up Item
    public static void LookUpItem(Scanner kbd) throws IOException {
        String searchKey;
        String[] product;
        searchKey = GetSearchKey(kbd);
        product = ProductSearch(searchKey);
        DisplayProductInfo(product, searchKey);
    }

    //Get Search Key
    public static String GetSearchKey(Scanner kbd) {
        String searchKey;
        System.out.print("Enter search key:  ");
        searchKey = kbd.nextLine();
        return searchKey;
    }

    //Product Search
    public static String[] ProductSearch(String searchKey) throws IOException {
        File paintFile;
        Scanner paintFileSC;
        String[] product;

        product = new String[6];
        paintFile = new File("c:/users/sawda/paint.txt");
        paintFileSC = new Scanner(paintFile);
        product[0] = paintFileSC.nextLine();
        product[1] = paintFileSC.nextLine();
        product[2] = paintFileSC.nextLine();
        product[3] = paintFileSC.nextLine();
        product[4] = paintFileSC.nextLine();
        product[5] = paintFileSC.nextLine();
        while (paintFileSC.hasNext() && searchKey.compareTo(product[0]) != 0) {
            product[0] = paintFileSC.nextLine();
            product[1] = paintFileSC.nextLine();
            product[2] = paintFileSC.nextLine();
            product[3] = paintFileSC.nextLine();
            product[4] = paintFileSC.nextLine();
            product[5] = paintFileSC.nextLine();
        }

        paintFileSC.close();
        return product;
    }

    //Display Product Information
    public static void DisplayProductInfo(String[] product, String searchKey) {
        System.out.println("");
        if (searchKey.compareTo(product[0]) == 0) {
            System.out.println("Search Key: " + product[0]);
            System.out.println("Product Name: " + product[1]);
            System.out.println("Percent Red: " + product[2]);
            System.out.println("Percent Green: " + product[3]);
            System.out.println("Percent Blue: " + product[4]);
            System.out.println("Amount in Gallons: " + product[5]);
        } else {
            System.out.println("THIS PRODUCT DOES NOT EXIST");
        }

    }

    //Add Product
    public static void AddProduct(Scanner kbd) throws IOException {
        String[] newInfo;
        //newInfo = new String[6];
        newInfo = GetNewProductInfo(kbd);
        AppendProductInfo(newInfo);
    }

    //Get New Product Info
    public static String[] GetNewProductInfo(Scanner kbd) {

        String[] newInfo;
        newInfo = new String[6];
        double redTint, greenTint, blueTint, gallons;

        System.out.print("Enter the Product ID: ");
        newInfo[0] = kbd.nextLine();
        System.out.print("Enter the Product Name: ");
        newInfo[1] = kbd.nextLine();
        System.out.print("Enter the percent red tint: ");
        redTint = kbd.nextDouble();
        while (redTint < 0 || redTint > 100.00) {
            System.out.print("Error, please re-enter the red tint percentage:"
                    + " ");
            redTint = kbd.nextDouble();
        }
        System.out.print("Enter the percent green tint: ");
        greenTint = kbd.nextDouble();
        while (greenTint < 0 || greenTint > 100.00) {
            System.out.print("Error, please re-enter the green tint percentage:"
                    + " ");
            greenTint = kbd.nextDouble();
        }
        System.out.print("Enter the percent blue tint: ");
        blueTint = kbd.nextDouble();
        while (blueTint < 0 || blueTint > 100.00) {
            System.out.print("Error, please re-enter the blue tint percentage:"
                    + " ");
            blueTint = kbd.nextDouble();
        }
        System.out.print("Enter the gallons available: ");
        gallons = kbd.nextDouble();
        while (gallons < 0.00) {
            System.out.print("Error, please re-enter the available gallons: ");
            gallons = kbd.nextDouble();
        }
        kbd.nextLine( );
        
        newInfo[2] = String.format("%1.2f", redTint);
        newInfo[3] = String.format("%1.2f", greenTint);
        newInfo[4] = String.format("%1.2f", blueTint);
        newInfo[5] = String.format("%1.2f", gallons);

        return newInfo;
    }

    //Append Product Info
    public static void AppendProductInfo(String[] newInfo) throws IOException {
        FileWriter paintFW;
        PrintWriter outPaintPW;
        paintFW = new FileWriter("c:/users/sawda/paint.txt", true);
        outPaintPW = new PrintWriter(paintFW);

        outPaintPW.println(newInfo[0]);
        outPaintPW.println(newInfo[1]);
        outPaintPW.println(newInfo[2]);
        outPaintPW.println(newInfo[3]);
        outPaintPW.println(newInfo[4]);
        outPaintPW.println(newInfo[5]);

        outPaintPW.close();

    }

    //Modify Item
    public static void ModifyItem(Scanner kbd) throws IOException {
        String searchKey;
        String[] newProduct;

        searchKey = GetSearchKey(kbd);
        newProduct = GetValidItemInfo(kbd);
        ProcessEachItem(searchKey, newProduct);
    }

    //Get Valid Item Info
    public static String[] GetValidItemInfo(Scanner kbd) {
        String[] newProduct;
        //double gallons;

        newProduct = new String[2];
        System.out.print("What is the new product name? ");
        newProduct[0] = kbd.nextLine();
        System.out.print("How many gallons of this item are there? ");
        newProduct[1] = kbd.nextLine();
        while ((Double.parseDouble(newProduct[1])) < 0) {
            System.out.print("Enter a Positive Amount of Gallons ");
            newProduct[1] = kbd.nextLine();
        }

        return newProduct;
    }

    //Process Each Item
    public static void ProcessEachItem(String searchKey, String[] newProduct)
            throws IOException {
        String[] product;
        File paintFile;
        Scanner paintFileSC;
        paintFile = new File("c:/users/sawda/paint.txt");
        paintFileSC = new Scanner(paintFile);
        PrintWriter outPaintPW;
        outPaintPW = new PrintWriter("c:/users/sawda/poop.txt");

        File tempFile;

        while (paintFileSC.hasNext()) {
            product = GetProductInfoFromFile(searchKey, paintFile, paintFileSC);
            StoreAppropriateItemInfo(product, newProduct, searchKey, outPaintPW,
                    paintFileSC);
        }
        paintFileSC.close();
        outPaintPW.close();

        paintFile.delete();
        tempFile = new File("c:/users/sawda/poop.txt");
        tempFile.renameTo(paintFile);
    }

    //Get Product Info From File
    public static String[] GetProductInfoFromFile(String searchKey, File paintFile, Scanner paintFileSC)
            throws IOException {

        String[] product;

        product = new String[6];

        product[0] = paintFileSC.nextLine();
        product[1] = paintFileSC.nextLine();
        product[2] = paintFileSC.nextLine();
        product[3] = paintFileSC.nextLine();
        product[4] = paintFileSC.nextLine();
        product[5] = paintFileSC.nextLine();

        return product;
    }

    //Store Appropriate Item Info
    public static void StoreAppropriateItemInfo(String[] product,
            String[] newProduct, String searchKey, PrintWriter outPaintPW,
            Scanner paintFileSC) throws IOException {

        if (searchKey.compareTo(product[0]) != 0) {
            outPaintPW.println(product[0]);
            outPaintPW.println(product[1]);
            outPaintPW.println(product[2]);
            outPaintPW.println(product[3]);
            outPaintPW.println(product[4]);
            outPaintPW.println(product[5]);
        } else {
            outPaintPW.println(product[0]);
            outPaintPW.println(newProduct[0]);
            outPaintPW.println(product[2]);
            outPaintPW.println(product[3]);
            outPaintPW.println(product[4]);
            outPaintPW.println(newProduct[1]);
        }

    }

}
