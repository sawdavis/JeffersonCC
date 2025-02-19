package laboratory10;

import java.util.Scanner;

public class Part {

    private int quantity;
    private String productID, description;
    private double price;

    //Load
    public void Load(Scanner partsFileSC) {
        productID = partsFileSC.nextLine();
        description = partsFileSC.nextLine();
        price = partsFileSC.nextDouble();
        quantity = partsFileSC.nextInt();
        partsFileSC.nextLine();
    }
    
    //Set
    public void Set(String nProductID, String nDescription, int nQuantity, double nPrice){
        productID = nProductID;
        description = nDescription;
        price = nPrice;
        quantity = nQuantity;
    }
    
    //Get Product ID
    public String GetID(){
        return productID;
    }
    
    //Get Description
    public String GetDescription(){
        return description;
    }
    
    //Get Price
    public double GetPrice(){
        return price;
    }
    
    //Get Quantity
    public int GetQuantity(){
        return quantity;
    }
    
    //Set Quantity
    public void SetQuantity(int nQuantity){
        quantity = nQuantity;
    }
    
    //SetPrice
    public void SetPrice(double nPrice){
        price = nPrice;
    }
    
}
