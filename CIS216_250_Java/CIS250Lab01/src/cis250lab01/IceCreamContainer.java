package cis250lab01;

import java.util.Scanner;
import java.io.*;

public class IceCreamContainer {
    private char containerType;
    private int iCMass, hTMass, cMass, rConeMax, wConeMax, cupMax;
    private double cupPrice, rConePrice, wConePrice;
    
    //Load Ice Cream Data
    public IceCreamContainer(String fileName) throws IOException {
        File iceCreamFile;
        Scanner iceCreamFileSC;
        iceCreamFile = new File(fileName);
        iceCreamFileSC = new Scanner(iceCreamFile);
        
        cupPrice = iceCreamFileSC.nextDouble();
        rConePrice = iceCreamFileSC.nextDouble();
        wConePrice = iceCreamFileSC.nextDouble();
        cupMax = iceCreamFileSC.nextInt();
        rConeMax = iceCreamFileSC.nextInt();
        wConeMax = iceCreamFileSC.nextInt();
    }
    
    //Set Container
    public void SetContainer(char coneChoice){
        if(GetTotalMass() == 0){
            containerType = coneChoice;
        }
        else{
            System.out.println("ERROR: Cannot Change Cone Type - Cone Type Reamains:    " + containerType);
        }
    }
    
    //Add Ice Cream
    public void AddIC(int seconds){
        if(containerType == 'C'){
            if(cupMax >= (iCMass + hTMass + cMass) +80 * seconds){
                iCMass = iCMass + (80 * seconds);
                System.out.println((80 * seconds) + " grams of mass added");
            }
            else
                System.out.println("Cannot Add: Only " + (cupMax - (iCMass + hTMass + cMass)) + " grams reamining.");
        }
        if(containerType == 'R'){
            if(rConeMax >= (iCMass + hTMass + cMass) + 80 * seconds){
                iCMass = iCMass + (80 * seconds);
                System.out.println((80 * seconds) + " grams of mass added");
            }
            else
                System.out.println("Cannot Add: Only " + (rConeMax - (iCMass + hTMass + cMass)) + " grams reamining.");
        }
        if(containerType == 'W'){
            if(wConeMax >= (iCMass + hTMass + cMass) + 80 * seconds){
                iCMass = iCMass + (80 * seconds);
                System.out.println((80 * seconds) + " grams of mass added");
            }
            else
                System.out.println("Cannot Add: Only " + (wConeMax - (iCMass + hTMass + cMass)) + " grams reamining.");
        }
        
    }
    
    //Add Hot Toppings
    public void AddHT(int seconds){
        if(containerType == 'C'){
            if(cupMax >= (iCMass + hTMass + cMass) + 65 * seconds){
                hTMass = hTMass + (65 * seconds);
                System.out.println((65 * seconds) + " grams of mass added");
            }
            else
                System.out.println("Cannot Add: Only " + (cupMax - (iCMass + hTMass + cMass)) + " grams reamining.");
        }
        if(containerType == 'R'){
            if(rConeMax >= (iCMass + hTMass + cMass) + 65 * seconds){
                hTMass = hTMass + (65 * seconds);
                System.out.println((65 * seconds) + " grams of mass added");
            }
            else
                System.out.println("Cannot Add: Only " + (rConeMax - (iCMass + hTMass + cMass)) + " grams reamining.");
        }
        if(containerType == 'W'){
            if(wConeMax >= (iCMass + hTMass + cMass) + 65 * seconds){
                hTMass = hTMass + (65 * seconds);
                System.out.println((65 * seconds) + " grams of mass added");
            }
            else
                System.out.println("Cannot Add: Only " + (wConeMax - (iCMass + hTMass + cMass)) + " grams reamining.");
        }
    }
    
    //Add Candy
    public void AddC(int seconds){
        if(containerType == 'C'){
            if(cupMax >= (iCMass + hTMass + cMass) + 47 * seconds){
                cMass = cMass + (47 * seconds);
                System.out.println((47 * seconds) + " grams of mass added");
            }
            else
                System.out.println("Cannot Add: Only " + (cupMax - (iCMass + hTMass + cMass)) + " grams reamining.");
        }
        if(containerType == 'R'){
            if(rConeMax >= (iCMass + hTMass + cMass) + 47 * seconds){
                cMass = cMass + (47 * seconds);
                System.out.println((47 * seconds) + " grams of mass added");
            }
            else
                System.out.println("Cannot Add: Only " + (rConeMax - (iCMass + hTMass + cMass)) + " grams reamining.");
        }
        if(containerType == 'W'){
            if(wConeMax >= (iCMass + hTMass + cMass) + 47 * seconds){
                cMass = cMass + (47 * seconds);
                System.out.println((47 * seconds) + " grams of mass added");
            }
            else
                System.out.println("Cannot Add: Only " + (wConeMax - (iCMass + hTMass + cMass)) + " grams reamining.");
        }
    }
    
    //Get Ice Cream Mass
    public int GetICMass(){
        return iCMass;
    }
    
    //Get Hot Topping Mass
    public int GetHTMass(){
        return hTMass;
    }
    //Get Candy Mass
    public int GetCMass(){
        return cMass;
    }
    
    //Get Total Mass
    
    public int GetTotalMass (){
        int totalMass;
        totalMass = iCMass + hTMass + cMass;
        
        return totalMass;
    }
    
    public double GetTotal(){
        double total = 0;
      
        if(containerType == 'C')
            total = cupPrice + (((iCMass + hTMass + cMass)/100) * 0.87);
        
        if(containerType == 'R')
            total = rConePrice + (((iCMass + hTMass + cMass)/100) * 0.87);
 
        if(containerType == 'W')
            total = wConePrice + (((iCMass + hTMass + cMass)/100) * 0.87);
                
        return total;
    }
}
