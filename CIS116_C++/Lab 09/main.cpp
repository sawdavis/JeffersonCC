
/* 
 * Name: Sawyer Davis
 * Due Date: November 26, 2022
 * Project Name: Lab 09
 * Project Description: This program is used to determine and display an electrical bill to customers of the company.
 * The information used such as name, address, their current usage and previous usage to calculate the amount due.
 * Using functions and loops, this lab is made to display an easy bill to the customer.
 */

#include <iostream>
#include <fstream>
#include <iomanip>

using namespace std;

//Function Prototypes
void GetFileName(char[]);
int GetNumberOfCustomers(ifstream&, char[]);
void GetCustomerInformation(ifstream&, char[], char[], char[], int&, char&, char[], double&, double&);
double CalculateMonthlyUsage(double, double);
double CalculateAmountDue(double, char);
void DisplayElectricBill(char[], char[], char[], int, char[], double, double);
void ProcessEachCustomerInfo(ifstream&, int);

int main(void) {

    char fileName[80];
    int numCustomers;
    ifstream infoFile;
   
    GetFileName(fileName);
    numCustomers = GetNumberOfCustomers(infoFile, fileName);
    ProcessEachCustomerInfo(infoFile, numCustomers);
    
    return 0;
}

//Get File Name
void GetFileName(char fileName[80]){
    
    cout << "Enter File Name: ";
    cin.getline(fileName, 80);
    cout << endl;
}
//Get Number of Customers
int GetNumberOfCustomers(ifstream & infoFile, char fileName[80]){
    
    int numCustomers;
    
    infoFile.open(fileName);
    infoFile >> numCustomers;
    
    return numCustomers;
}
//Get Customer Information From File
void GetCustomerInformation(ifstream & infoFile, char name[80], char address[80], char state[80], int & account, char & meterType, char date[80], double & current, double & previous){
    infoFile.ignore(1000, '\n'); 
    infoFile.getline(name, 80);
    infoFile.getline(address, 80);
    infoFile.getline(state, 80);
    infoFile >> account;
    infoFile >> meterType;
    infoFile.ignore(1000, '\n');
    infoFile.getline(date, 80);
    infoFile >> current;
    infoFile >> previous;
              
}
//Calculate Monthly Usage
double CalculateMonthlyUsage(double current, double previous){
    double kwhUsage;
    
    kwhUsage = current - previous;
    
    return kwhUsage;
}
//Calculate Amount Due
double CalculateAmountDue(double kwhUsage, char meterType){
    double amountDue;
    
    if(kwhUsage < 301){
        amountDue = 52.11;
    }
    else{
        if(kwhUsage > 300 && kwhUsage < 1001){
            amountDue = (kwhUsage - 300) * 0.19226 + 52.11;
        }
        else{
            if(kwhUsage > 1000){
                amountDue = (kwhUsage - 1000) * 0.16981 + 186.69;
            }
        }
    }
    
    if(meterType == 'C'){
        amountDue = amountDue * 0.95;
    }
    else{
        amountDue = amountDue;
    }
    
    return amountDue;
}
//Display Electric Bill
void DisplayElectricBill(char name[80], char address[80], char state[80], int account, char date[80], double kwhUsage, double amountDue){
    
    cout << "Electric Bill" << endl << endl;
    cout << name << endl;
    cout << address << endl;
    cout << state << endl << endl;
    cout << "Account Number: " << account << endl;
    cout << "Reading Date: " << date << endl;
    cout << "Kilowatt Hours Used This Month: " << kwhUsage << " KWH" << endl;
    cout << fixed << setprecision(2) << "Billable Amount: $" << amountDue << endl << endl;
}
//Process Each Customers Information
void ProcessEachCustomerInfo(ifstream & infoFile, int numCustomers){
        char name[80], address[80], state[80], date[80];
        int  account;
        char meterType;
        double current, previous, kwhUsage, amountDue;
        
    for(int cnt = 0; cnt < numCustomers; cnt++){
        GetCustomerInformation(infoFile, name, address, state, account, meterType, date, current, previous);
        kwhUsage = CalculateMonthlyUsage(current, previous);
        amountDue = CalculateAmountDue(kwhUsage, meterType); 
        DisplayElectricBill(name, address, state, account, date, kwhUsage, amountDue);
    }
}
