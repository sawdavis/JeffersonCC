package laboratory9;

import java.io.*;
import java.util.Scanner;

public class customer {

    private String name;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String meterNumber;
    private double usage;

    //Load
    public void Load(Scanner customerFileSC) {
        name = customerFileSC.nextLine();
        street = customerFileSC.nextLine();
        city = customerFileSC.nextLine();
        state = customerFileSC.nextLine();
        zip = customerFileSC.nextLine();
        meterNumber = customerFileSC.nextLine();
        usage = customerFileSC.nextDouble();
        customerFileSC.nextLine();
    }
    
    //Get Name
    public String GetName(){
        return name;
    }
    
    //Get Street
    public String GetStreet(){
        return street;
    }
    
    //Get City
    public String GetCity(){
        return city;
    }
    
    //Get State
    public String GetState(){
        return state;
    }
    
    //Get Zip
    public String GetZip(){
        return zip;
    }
    
    //Get Meter Number
    public String GetMeterNumber(){
        return meterNumber;
    }
    
    //Get Name
    public double GetUsage(){
        return usage;
    }
    
    //Get Customer Type
    public char GetCustomerType(){
        char custType;
        custType = meterNumber.charAt(0);
        return custType;
    }
    
    //Store
    public void Store()throws IOException{
        FileWriter customerFW;
        PrintWriter customerPW;
        customerFW = new FileWriter("custreadings.txt", true);
        customerPW = new PrintWriter(customerFW);
        
        customerPW.println(name);
        customerPW.println(street);
        customerPW.println(city);
        customerPW.println(state);
        customerPW.println(zip);
        customerPW.println(meterNumber);
        customerPW.println(usage);
        customerPW.close( );
        
    }
    
    //Set
    public void Set(String nName, String nStreet, String nCity, String nState,
            String nZip, String nMeterNumber, double nUsage){
        name = nName;
        street = nStreet;
        city = nCity;
        state = nState;
        zip = nZip;
        meterNumber = nMeterNumber;
        usage = nUsage;
    }
}
