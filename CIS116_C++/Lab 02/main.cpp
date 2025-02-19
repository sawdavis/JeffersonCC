
/* 
 * Name: Sawyer Davis
 * Project Number: Lab 02
 * Due Date: September 23, 2022
 * Project Description: This program takes an inputted amount due and an
 * inputted amount tendered and calculates the change due. The program then
 * calculates and displays the change due, and the number of bills and coins
 * dispensed. 
 */

#include <iostream>

using namespace std;

int main(void) {

    double amountDue;
    double amountTendered;
    double changeDue;
    int dollarChange;
    int coinChange;
    int num20s;
    int num10s;
    int num5s;
    int num1s;
    int numQs;
    int numDs;
    int numNs;
    int numPs;
    
// Get Amount Due
    cout << "Enter the amount due: " << endl;
    cin >> amountDue;
    
// Get Amount Tendered
    cout << "Enter the amount Tendered: " << endl;
    cin >> amountTendered;
    
// Calculate Change Due
    changeDue = amountTendered - amountDue;
    
// Calculate Dollar Change
    dollarChange = changeDue;
    
// Calculate Coin Change
    coinChange = (changeDue - dollarChange) * 100 + 0.5;
            
//Calculate Number of Each Bill to Return
    num20s =  dollarChange / 20;
    dollarChange = dollarChange % 20;
    num10s =  dollarChange / 10;
    dollarChange = dollarChange % 10;
    num5s =  dollarChange / 5;
    dollarChange = dollarChange % 5;
    num1s =  dollarChange / 1;
// Calculate Number of Each Coin to Return
    numQs=  coinChange / 25;
    coinChange = coinChange % 25;
    numDs =  coinChange / 10;
    coinChange = coinChange % 10;
    numNs =  coinChange / 5;
    coinChange = coinChange % 5;
    numPs =  coinChange / 1;
    
//Display Change Summary
    cout << "Amount Due: " << amountDue << endl;
    cout << "Amount Tendered: " << amountTendered << endl;
    cout << "Change Due: " << changeDue << endl;
    cout << "Give the following Bills:" << endl;
    cout << num20s << "-$20 Bill(s)" << endl;
    cout << num10s << "-$10 Bill(s)" << endl;
    cout << num5s << "-$5 Bill(s)" << endl;
    cout << num1s << "-$1 Bill(s)" << endl;
    cout << "Give the following Coins:" << endl;
    cout << numQs << "-Quarter(s)" << endl;
    cout << numDs << "-Dime(s)" << endl;
    cout << numNs << "-Nickel(s)" << endl;
    cout << numPs << "-Pennie(s)" << endl;

return 0;
}

