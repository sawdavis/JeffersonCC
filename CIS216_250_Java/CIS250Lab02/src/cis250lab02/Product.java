package cis250lab02;

import java.util.Scanner;
import java.io.*;

public class Product {
    
    private String description;
    private int quantity;
    private double price;
    
    //Product
    public Product(){
        quantity = 0;
    }
    
    //Set
    public void Set(String newDesc, int newQuan, double newPrice){
        description = newDesc;
        quantity = newQuan;
        price = newPrice;
        
    }
    
    //Get Description
    public String GetDescription(){
        return description;
    }
    
    //Get Quantity
    public int GetQuantity(){
        return quantity;
    }
    
    //Get Price
    public double GetPrice(){
        return price;
    }
    
    //Increase Quantitiy
    public void IncreaseQuantity(int addedQuantity){
        quantity = quantity + addedQuantity;
        System.out.println("Quantity is now " + quantity);
    }
    
    //Decrease Quantity
    public void DecreaseQuantity(int decreasedQuantity){
        if(quantity - Math.abs(decreasedQuantity) > -1){
            quantity = quantity - Math.abs(decreasedQuantity);
            System.out.println("Quantity is now " + quantity);
        }
        else{
            System.out.println("Quantity Decreased to 0 ");
        }
    }
    
    //Get Extended Cost
    public double GetExtendedCost(){
        double extendedCost;
        
        extendedCost = quantity * price;
        
        return extendedCost;
    }
    
}
