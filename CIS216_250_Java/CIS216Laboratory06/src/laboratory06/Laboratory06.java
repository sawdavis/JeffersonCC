package laboratory06;

import java.util.Scanner;
import java.io.*;

public class Laboratory06 {

    /*
    03/13/2023
    Sawyer Davis
    Lab 06
    Due 04/4/2023
    Performs inventory management for a small candy company. It allows the user
    to serach for a product, add to a products qunatity, or sell an amount of
    product from the current inventory. All information is then saved to a file
    once inventory management is conclcuded. 
     */
    
    public static void main(String[] args) throws IOException {
        String[] productName;
        productName = new String[10];
        int[] packageQTY;
        packageQTY = new int[10];
        double[] price;
        price = new double[10];
        int[] quantity;
        quantity = new int[10];
        File candyFile;
        
        
        candyFile = GetProductInfo(productName, packageQTY, price, quantity);
        PerformEachTask(productName, packageQTY, price, quantity, candyFile);
    }
    
    //Get Product Info
    public static File GetProductInfo(String[] productName, int[] packageQTY, double[] price, int[] quantity) throws IOException {
        File candyFile;
        Scanner candyFileSC;
        candyFile = new File("candy.txt");
        candyFileSC = new Scanner(candyFile);
        
        for (int cnt = 0; cnt < 10; cnt++) {
            productName[cnt] = candyFileSC.nextLine();
            packageQTY[cnt] = candyFileSC.nextInt();
            price[cnt] = candyFileSC.nextDouble();
            quantity[cnt] = candyFileSC.nextInt();
            candyFileSC.nextLine();
        }
        candyFileSC.close();
        
        return candyFile;
    }
    
    //Perform Each Task
    public static void PerformEachTask(String[] productName, int[] packageQTY, double[] price, int[] quantity, File candyFile) throws IOException {
        Scanner kbd = new Scanner(System.in);
        char userChoice;
        userChoice = 'G';
        String searchValue = "poop";
        
        int location;
        
        while(userChoice != 'Q'){
            searchValue = GetSearchValue(searchValue, kbd);
            SearchForProduct(searchValue, productName);
            userChoice = GetUserChoice(userChoice, kbd);
            location = SearchForProduct(searchValue, productName);
            PerformUserChoice(userChoice, location, productName, packageQTY, price, quantity, kbd);
            
        }
        SaveProducts(productName, packageQTY, price, quantity, candyFile);
    }
    
    //Get User Choice
    public static char GetUserChoice(char userChoice, Scanner kbd){
        System.out.print("Would you like to (A)dd to Quantity, (S)ell Products, (L)ookup Item, or (Q)uit?   ");
        userChoice = kbd.nextLine().charAt(0);
        
        return userChoice;
    }
    
    //Get Search Value
    public static String GetSearchValue(String searchValue, Scanner kbd){
        System.out.print("What Product are you looking for?   ");
        searchValue = kbd.nextLine();
        
        return searchValue;
    }
    
    //Search For Product
    public static int SearchForProduct(String searchValue, String[] productName){
        int location = 0;
        while(location < 10 && searchValue.compareTo(productName[location]) != 0)
            location++;
        
        return location;
    }
    
    //Perfrom User Choice
    public static void PerformUserChoice(char userChoice, int location, String[] productName, int[] packageQTY, double[] price, int[] quantity, Scanner kbd){
        if(userChoice == 'A')
            AddQuantity(location, quantity, productName, kbd);
        if(userChoice == 'S')
            SellProduct(location, quantity, productName, kbd);
        if(userChoice == 'L')
            DisplayMatch(location, productName, packageQTY, price, quantity);
            
    }
    
    //Add Quantity 
    public static void AddQuantity(int location, int[] quantity, String[] productName, Scanner kbd){
        int addedAmount = 0;
        
        if(location == 10)
            System.out.println("PRODUCT NOT FOUND");
        else{
            while(addedAmount < 1){
                System.out.println("How much would you like to add to " + productName[location] + "?   ");
                addedAmount = kbd.nextInt();
                kbd.nextLine();
            }
            quantity[location] = quantity[location] + addedAmount;
        }
    }
    
    //Sell Product
    public static void SellProduct(int location, int[] quantity, String[] productName, Scanner kbd){
        int soldAmount = 0;
        
        if(location == 10)
            System.out.println("PRODUCT NOT FOUND");
        else{
            while(soldAmount < 1){
                System.out.println("How much would you like to sell of " + productName[location] + "?   ");
                soldAmount = kbd.nextInt();
                kbd.nextLine();
            }
            quantity[location] = quantity[location] - soldAmount;
        }
    }
        
    //Display Match
    public static void DisplayMatch(int location, String[] productName, int[] packageQTY, double[] price, int[] quantity){
        if(location == 10)
            System.out.println("PRODUCT NOT FOUND");
        else{
            System.out.println("Product Name:   " + productName[location]);
            System.out.println("Number of Items in each Package:   " + packageQTY[location]);
            System.out.println("Price:   $" + price[location]);
            System.out.println("Quantity on Hand:   " + quantity[location]);
        }
    }
    
    //Save Products
    public static void SaveProducts(String[] productName, int[] packageQTY, double[] price, int[] quantity, File candyFile)throws IOException{
        PrintWriter candyPW;
        
        candyPW = new PrintWriter("candy.txt");
        for (int cnt = 0; cnt < 10; cnt++) {
            candyPW.println(productName[cnt]);
            candyPW.println(packageQTY[cnt]);
            candyPW.println(price[cnt]);
            candyPW.println(quantity[cnt]);
        }
        candyPW.close();
        
    }
    
}
