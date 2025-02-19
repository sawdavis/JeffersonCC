package laboratory05;

import java.util.Scanner;
import java.io.*;

public class Laboratory05 {

    /*
    03/13/2023
    Sawyer Davis
    Lab 05
    Due 03/14/2023
    Perfroms generation of usage summaries for a local phone company. This 
    program will store area code descriptor information from a file, and use 
    those descriptors to classify outgoing calls in the summary.
     */
    public static void main(String[] args) throws IOException {

        //Variables
        String[] descriptor;
        descriptor = new String[999];
        


        //Function Calls
        descriptor = GetDescriptorInfo();
        ProcessEachSummary(descriptor);

    }

    //Get Descriptor Info
    public static String[] GetDescriptorInfo() throws IOException {
        int areaCode;
        String[] descriptor;
        descriptor = new String[1000];
        File areaCodeFile;
        Scanner areaCodeFileSC;
        areaCodeFile = new File("areaCodes.txt");
        areaCodeFileSC = new Scanner(areaCodeFile);

        for (int cnt = 0; cnt < 999; cnt++) {
            descriptor[cnt] = "Unused";
        }

        while (areaCodeFileSC.hasNext()) {

            areaCode = areaCodeFileSC.nextInt();
            areaCodeFileSC.nextLine();
            descriptor[areaCode] = areaCodeFileSC.nextLine();
        }

        return descriptor;
    }
    
    //Process Each Summary
    public static void ProcessEachSummary(String[] descriptor)throws IOException{
        Scanner kbd = new Scanner(System.in);
        String fileName;
        String[] customerInfo;
        customerInfo= new String[4];
        char userChoice;
        int[] totals;
        totals = new int[2];
        
        File customerFile;
        Scanner customerFileSC;

        
         do {
            System.out.print("Would you like to process a summary?(Y/N) ");
            userChoice = kbd.nextLine().charAt(0);
        } while (userChoice != 'Y' && userChoice != 'N');
        
         if(userChoice == 'Y'){
        fileName = GetFileName(kbd);
        customerFile = new File(fileName);
        customerFileSC = new Scanner(customerFile);
        GetCustomerInfo(customerFileSC, customerInfo);
        DisplayReportHeadings(customerInfo);
        while(customerFileSC.hasNext()){
            ProcessEachCaller(customerFileSC, totals, descriptor);
        }
        DisplayTotals(totals);
        }
    }
    
    //Get File Name
    public static String GetFileName(Scanner kbd) {
        String fileName;

        System.out.print("Enter the file name:  ");
        fileName = kbd.nextLine();
        System.out.println();

        return fileName;
    }

    //Get Customer Info
    public static void GetCustomerInfo(Scanner customerFileSC, String[] customerInfo) throws IOException {
        
        
        customerInfo[0] = customerFileSC.nextLine();
        customerInfo[1] = customerFileSC.nextLine();
        customerInfo[2] = customerFileSC.nextLine();
        customerInfo[3] = customerFileSC.nextLine();

        
    }
    
    //Display Report Headings
    public static void DisplayReportHeadings(String[] customerInfo){
        System.out.printf("%37s\n\n", "Phones, Phones, Phones");
        System.out.printf("%-20s%35s\n", "Customer Name:", customerInfo[0]);
        System.out.printf("%-20s%35s\n", "Customer Address:", customerInfo[1]);
        System.out.printf("%-20s%35s\n", "City, State, Zip:", customerInfo[2]);
        System.out.printf("%-20s%35s\n\n", "Customer Number:", customerInfo[3]);
        System.out.println();
        System.out.printf("%-20s%18s%20s\n", "Number Called", "City, State", "Call Duration");
    }
    
    //Process Each Caller
    public static void ProcessEachCaller(Scanner customerFileSC, int[] totals, String[] descriptor){      
        int[] callInfo;
        callInfo= new int[4];

        
        callInfo = GetCallInfo(customerFileSC);
        UpdateTotals(callInfo, totals);
        DisplaySummaryLine(callInfo, descriptor);
        
        //customerFileSC.close();
    }
    
    //Get Call Info
    public static int[] GetCallInfo(Scanner customerFileSC){
        int[] callInfo;
        callInfo= new int[4];
        
        callInfo[0] = customerFileSC.nextInt();
        callInfo[1] = customerFileSC.nextInt();
        callInfo[2] = customerFileSC.nextInt();
        callInfo[3] = customerFileSC.nextInt();
        
        return callInfo;
    }
    
    //Update Totals
    public static void UpdateTotals(int[] callInfo, int [] totals){
        totals[0]++;
        totals[1] = totals[1] + callInfo[3];
        
    }
    
    //Display Call Summary Line
    public static void DisplaySummaryLine(int[] callInfo, String[] descriptor){
        
        System.out.print(callInfo[0] + "-" + callInfo[1] + "-" + callInfo[2]);
        System.out.printf("%26s%20s\n", descriptor[callInfo[0]], callInfo[3]);
    }
    
    //Display Totals
    public static void DisplayTotals(int[] totals){
        System.out.println();
        System.out.println("Total Numbers Called: " + totals[0]);
        System.out.println("Total Calling Time: " + totals[1]);
    }

}
