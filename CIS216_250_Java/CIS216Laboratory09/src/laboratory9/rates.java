package laboratory9;

import java.util.Scanner;
import java.io.*;

public class rates {

    static double resLow, resHigh, comLow, comMid, comHigh, indLow, indHigh,
            inst;
    
    //Load Rates
    public void LoadRates()throws IOException{
        File ratesFile;
        Scanner ratesFileSC;
        ratesFile = new File("prices.txt");
        ratesFileSC = new Scanner(ratesFile);
        
        resLow = ratesFileSC.nextDouble();
        resHigh = ratesFileSC.nextDouble();
        comLow = ratesFileSC.nextDouble();
        comMid = ratesFileSC.nextDouble();
        comHigh = ratesFileSC.nextDouble();
        indLow = ratesFileSC.nextDouble();
        indHigh = ratesFileSC.nextDouble();
        inst = ratesFileSC.nextDouble();
        
        ratesFileSC.close();
        
    }
    
    //Determine Amount Due
    public double DetermineAmountDue(customer givenCustomer){
        double amountDue = 0;
        char cType;
        double us;
        cType = givenCustomer.GetCustomerType();
        us = givenCustomer.GetUsage();
        if(givenCustomer.GetCustomerType()=='R'&& givenCustomer.GetUsage() < 101){
            amountDue = givenCustomer.GetUsage() * resLow;
        }
        if(givenCustomer.GetCustomerType()=='R'&& givenCustomer.GetUsage() > 100){
            amountDue = givenCustomer.GetUsage() * resHigh;
        }
        if(givenCustomer.GetCustomerType()=='C'&& givenCustomer.GetUsage() < 101){
            amountDue = givenCustomer.GetUsage() * comLow;
        }
        if(givenCustomer.GetCustomerType()=='C'&& givenCustomer.GetUsage() > 100 && givenCustomer.GetUsage() <401){
            amountDue = givenCustomer.GetUsage() * comMid;
        }
        if(givenCustomer.GetCustomerType()=='C'&& givenCustomer.GetUsage() > 400){
            amountDue = givenCustomer.GetUsage() * comHigh;
        }
        if(givenCustomer.GetCustomerType()=='I'&& givenCustomer.GetUsage() < 1001){
            amountDue = givenCustomer.GetUsage() * indLow;
        }
        if(givenCustomer.GetCustomerType()=='I'&& givenCustomer.GetUsage() > 1000){
            amountDue = givenCustomer.GetUsage() * indHigh;
        }
        if(givenCustomer.GetCustomerType()=='N'){
            amountDue = givenCustomer.GetUsage() * inst;
        }
        
        return amountDue;
    }

}
