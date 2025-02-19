package cis250lab02;

import java.util.Scanner;
import java.io.*;

public class CIS250Lab02 {

    /*
    02/05/2024
    Sawyer Davis
    Lab 02
    Due 02/14/2024
    Performs information tracking for fundraiser pre-orders. The program will
    continously ask the user for product number and quantities until the user
    chooses to stop. The program will then display and order summary including a
    calculated total with tax.
     */ 
    
    public static void main(String[] args) throws IOException {
        Product [] products;
        
        products = GetProducts();
        AddEaProduct(products);
        GenerateSummary(products);
        
        
        
    }
    
    public static Product [] GetProducts ()throws IOException {
        Product [] products;
        products = new Product[8];
        File productFile;
        
        Scanner productFileSC;
        productFile = new File("productInfo.txt");
        productFileSC = new Scanner(productFile);
        
        String newDesc;
        double newPrice;
        int newQuan = 0;
        
        for(int cnt = 0; cnt < products.length; cnt++){
            newDesc = productFileSC.nextLine();
            newPrice = productFileSC.nextDouble();
            productFileSC.nextLine();
            products[cnt] = new Product( );
            products[cnt].Set(newDesc, newQuan, newPrice);
        }
        productFileSC.close();
        
        return products;
    }
    
    public static void AddEaProduct(Product[] products){
        int prodChoice, quantity;
        
        for(int cnt = 0; cnt < products.length; cnt++){
            System.out.println(cnt + ". " + products[cnt].GetDescription() + " " + products[cnt].GetPrice());
        }
        
        prodChoice = GetChoice();
        while(prodChoice !=8){
            quantity = GetQuantity();
            if(quantity > -1)
                products[prodChoice].IncreaseQuantity(quantity);
            else
                products[prodChoice].DecreaseQuantity(quantity);
           
            prodChoice = GetChoice();
        }
        
    }
    
    
    public static int GetChoice(){
        int prodChoice;
        Scanner kbd = new Scanner(System.in);
        
        System.out.println("Would you like to add a Product(0-7) or Quit(8)?");
        prodChoice = kbd.nextInt();
        while(prodChoice != 0 && prodChoice != 1 && prodChoice != 2 && prodChoice != 3 && prodChoice != 4 && prodChoice != 5 && prodChoice != 6 && prodChoice != 7 && prodChoice != 8){
            System.out.println("Would you like to add a Product(1-8) or Quit(9)?");
            prodChoice = kbd.nextInt();
        }
        return prodChoice;
    }
    
    public static int GetQuantity(){
        int quantity;
        Scanner kbd = new Scanner(System.in);
        
        System.out.println("What would you like to change quanity by?(+/-)");
        quantity = kbd.nextInt();
                
        return quantity;
    }
    
    public static void GenerateSummary (Product[] products){
        double totalCost = 0;
        
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("Sale Summary");
        System.out.println(" ");
        System.out.println("Product Information");
        for(int cnt = 0; cnt < products.length; cnt++){
            if(products[cnt].GetQuantity() > 0){
                System.out.println("Pie Type:   " + products[cnt].GetDescription());
                System.out.println("Individual Price:   " + products[cnt].GetPrice());
                System.out.println("Quantity:   " + products[cnt].GetQuantity());
                System.out.printf("%s%.2f\n" ,"Extended price: $",products[cnt].GetExtendedCost());
                totalCost = totalCost + products[cnt].GetExtendedCost();
            }
        }
        
        System.out.println(" ");
        System.out.printf("%s%.2f\n" ,"Subtotal Due: $",totalCost);       
        System.out.printf("%s%.2f\n" ,"Total Due: $",(totalCost * 1.08));
    }
    
}
