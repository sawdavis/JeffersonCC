package cis250lab05;

import java.util.Scanner;
import java.io.*;

public class CIS250Lab05 {
    /*
    03/04/2024
    Sawyer Davis
    Lab 04
    Due 03/18/2024
    This program will allow users control the lobby of a restaurant. The user
    can change store status, simulate arrived customers, change cashier status,
    move customers to registers, and completete their order. When there are no 
    more customers and the restaraunt is closed a business summary is displayed.
     */ 
    public static void main(String[] args) throws IOException {
        QueueADT queue;
        Cashier cashier1, cashier2, cashier3, cashier4;
        int numCust;
        PrintWriter outPW;
        
        
        outPW = new PrintWriter("times.txt");
        outPW.close();
        queue = new QueueADT();
        queue.Create(100);
        cashier1 = new Cashier();
        cashier2 = new Cashier();
        cashier3 = new Cashier();
        cashier4 = new Cashier();
        numCust = ControlMenu(cashier1, cashier2, cashier3, cashier4, queue);
        BusinessComplete(numCust);
    }
    
    //Cashier is Busy
    public static boolean CashBusy(Cashier cashier1, Cashier cashier2, Cashier cashier3, Cashier cashier4){
        boolean cashBusy;
        cashBusy = false;
        
        if(cashier1.GetStatus() == 'B'){
            cashBusy = true;
        }
        if(cashier2.GetStatus() == 'B'){
            cashBusy = true;
        }
        if(cashier3.GetStatus() == 'B'){
            cashBusy = true;
        }
        if(cashier4.GetStatus() == 'B'){
            cashBusy = true;
        }
        
        return cashBusy;
    }
    
    //Change Store Status
    public static char ChangeStoreStatus(char rStatus){
        if(rStatus == 'C'){
            rStatus ='O';
        }else{
        if(rStatus == 'O'){  
            rStatus = 'C';
        }
        else
            System.out.println("Cannot Change Restaurant Status.");
        }
        return rStatus;
    }
    
    //Change Cashier Status
    public static void ChangeCashierStatus(Cashier cashier1, Cashier cashier2, Cashier cashier3, Cashier cashier4){
        int choice;
        Scanner kbd = new Scanner(System.in);
        
        choice = 0;
        while(choice != 1 && choice != 2 && choice !=3 && choice != 4){
        System.out.println("Which cashier would you like to Open / Close?");
        choice = kbd.nextInt(); 
        if(choice == 1){
            cashier1.ChangeStatus();
        }
        if(choice == 2){
            cashier2.ChangeStatus();
        }
        if(choice == 3){
            cashier3.ChangeStatus();
        }
        if(choice == 4){
            cashier4.ChangeStatus();
        }
        
        }
    }
    
    //Move Customer
    public static void MoveCust(Cashier cashier1, Cashier cashier2, Cashier cashier3, Cashier cashier4, QueueADT queue){
        int choice;
        Scanner kbd = new Scanner(System.in);
        long aTime2;
        Element element;
        
        choice = 0;
        while(choice != 1 && choice != 2 && choice !=3 && choice != 4){
        System.out.println("Which Cashier would you like to move to?");
        choice = kbd.nextInt(); 
        if(choice == 1){
            if(cashier1.GetStatus() == 'O'){
                element = queue.Dequeue();
                aTime2 = System.currentTimeMillis();
                element.Set2(aTime2);
                cashier1.SetElement(element);
                cashier1.BusyCashier();
            }
            else
                System.out.println("Cannot Move Customer");
                
        }
        if(choice == 2){
            if(cashier2.GetStatus() == 'O'){
                element = queue.Dequeue();
                aTime2 = System.currentTimeMillis();
                element.Set2(aTime2);
                cashier2.SetElement(element);
                cashier2.BusyCashier();
            }
            else
                System.out.println("Cannot Move Customer");
        }
        if(choice == 3){
            if(cashier3.GetStatus() == 'O'){
                element = queue.Dequeue();
                aTime2 = System.currentTimeMillis();
                element.Set2(aTime2);
                cashier3.SetElement(element);
                cashier3.BusyCashier();
            }
            else
                System.out.println("Cannot Move Customer");
        }
        if(choice == 4){   
            if(cashier4.GetStatus() == 'O'){
                element = queue.Dequeue();
                aTime2 = System.currentTimeMillis();
                element.Set2(aTime2);
                cashier4.SetElement(element);
                cashier4.BusyCashier();
            }
            else
                System.out.println("Cannot Move Customer");
        }
        
        }
        
    }
    
    //Customer Arrives
    public static int CustArrive(char rStatus, QueueADT queue, int numCust){
        long aTime1;
        Element element;
        if(rStatus == 'O' && !queue.IsFull()){
            element = new Element();
            aTime1 = System.currentTimeMillis();
            element.Set1(aTime1);
            queue.Enqueue(element);
            numCust ++;
        }
        else
            System.out.println("Customer Cannot be added at this time.");
            
        
        return numCust;
    }
    
    //Order Complete
    public static void OrderComplete(Cashier cashier1, Cashier cashier2, Cashier cashier3, Cashier cashier4) throws IOException {
        int choice;
        Scanner kbd = new Scanner(System.in);
        long aTime3;
        PrintWriter outPW;
        FileWriter outFW;
        Element element;
        
        element = null;
        choice = 0;
        while(choice != 1 && choice != 2 && choice !=3 && choice != 4){
        System.out.println("Which order do you want to complete?");
        choice = kbd.nextInt(); 
        if(choice == 1){
            //cashier1.BusyCashier();
            if(cashier1.GetStatus() == 'B'){
                aTime3 = System.currentTimeMillis();
                element = cashier1.GetElement();
                element.Set3(aTime3);
                cashier1.OpenCashier();
            }
            else
                System.out.println("Cannot Complete Order");    
        }
        
        if(choice == 2){
         
           if(cashier2.GetStatus() == 'B'){
                aTime3 = System.currentTimeMillis();
                element = cashier2.GetElement();
                element.Set3(aTime3);
                cashier2.OpenCashier();
            }
            else
                System.out.println("Cannot Complete Order"); 
        }
        
        if(choice == 3){
            
            if(cashier3.GetStatus() == 'B'){
                aTime3 = System.currentTimeMillis();
                element = cashier3.GetElement();
                element.Set3(aTime3);
                cashier3.OpenCashier();
            }
            else
                System.out.println("Cannot Complete Order");
        }
        if(choice == 4){
            if(cashier4.GetStatus() == 'B'){
                aTime3 = System.currentTimeMillis();
                element = cashier4.GetElement();
                element.Set3(aTime3);
                cashier4.OpenCashier();
            }
            else
                System.out.println("Cannot Complete Order");
        }
        
        }
        if(element != null) {
            outFW = new FileWriter("times.txt", true);
            outPW = new PrintWriter(outFW);

            outPW.println(element.GetTime1());
            outPW.println(element.GetTime2());
            outPW.println(element.GetTime3());

            outPW.close();
        }
    }
    
    //End of Business
    public static void BusinessComplete(int numCust)throws IOException{
        File inf;
        Scanner infSC;
        long time1, time2, time3, totalWait, totalService, wait, service, avgWait, avgService;
        
        totalWait = 0;
        totalService = 0;
        inf = new File("times.txt");
        infSC = new Scanner(inf);
        
        for(int cnt = 1; cnt < numCust + 1; cnt++){
            time1 = infSC.nextLong();
            time2 = infSC.nextLong();
            time3 = infSC.nextLong();
            wait = (time2 - time1) / 1000;
            totalWait = totalWait + wait;
            service = (time3 - time2) / 1000;
            totalService = totalService + service;
            System.out.println("Customer " + cnt + " wait time: " + wait + " seconds");
            System.out.println("Customer " + cnt + " service time: " + service + " seconds");
        }
        if(numCust == 0 ){
            avgWait = 0;
            avgService = 0;
        }
        else{
        avgWait = totalWait / numCust;
        avgService = totalService / numCust;
        }
        System.out.println("Average Wait Time: " + avgWait + " seconds");
        System.out.println("Average Service Time: " + avgService + " seconds");
        infSC.close();
        
        
        
    }
    
    //Control Menu
    public static int ControlMenu(Cashier cashier1, Cashier cashier2, Cashier cashier3, Cashier cashier4, QueueADT queue) throws IOException{
        char choice, rStatus;
        int numCust;
        
        rStatus = 'O';
        numCust = 0;
        while(rStatus == 'O' || !queue.IsEmpty() || CashBusy(cashier1, cashier2, cashier3, cashier4)){
            choice = GetChoice(rStatus, cashier1, cashier2, cashier3, cashier4, queue, numCust);
            if(choice == 'S' || choice == 's'){
                rStatus = ChangeStoreStatus(rStatus);
            }
            if(choice == 'C' || choice == 'c'){
                ChangeCashierStatus(cashier1, cashier2, cashier3, cashier4);
            }
            if(choice == 'M' || choice == 'm'){
                MoveCust(cashier1, cashier2, cashier3, cashier4, queue);
            }
            if(choice == 'A' || choice == 'a'){
                numCust = CustArrive(rStatus, queue, numCust);
            }
            if(choice == 'O' || choice == 'o'){
                OrderComplete(cashier1, cashier2, cashier3, cashier4);
            }
            
            if(choice != 'S' && choice != 's' && choice != 'C' && choice != 'c' && choice != 'M' && choice != 'm' && choice != 'A' && choice != 'a' && choice != 'O' && choice != 'o'){
                System.out.println("Choice Invalid");
            }
            
        }
        
        return numCust;
    }
    
    //Get Choice
    public static char GetChoice(char rStatus, Cashier cashier1, Cashier cashier2, Cashier cashier3, Cashier cashier4, QueueADT queue, int numCust){
        char choice;
        Scanner kbd = new Scanner(System.in);
        System.out.println("RESTAURANT LOBBY CONTROL MENU");
        System.out.println("-----------------------------");
        System.out.println("Restaurant Status:        " + rStatus);
        System.out.println("Cashier 1 Status:         " + cashier1.GetStatus());
        System.out.println("Cashier 2 Status:         " + cashier2.GetStatus());
        System.out.println("Cashier 3 Status:         " + cashier3.GetStatus());
        System.out.println("Cashier 4 Status:         " + cashier4.GetStatus());
        System.out.println("Customers Today:          " + numCust);
        System.out.println("-----------------------------");
        System.out.println("(S)Change Store Staus(Open/Close)");
        System.out.println("(C)Change Cashier Status(Open/Close)");
        System.out.println("(M)Move Next Customer");
        System.out.println("(A)Customer Arrives");
        System.out.println("(O)Order Complete");
        choice = kbd.nextLine().charAt(0);
        
        return choice;
    }
}
