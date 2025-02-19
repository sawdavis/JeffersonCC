/* 
 * Name: Sawyer Davis
 * Due Date: November 1, 2022
 * Project Name: Lab 07
 * Project Description: This program will take Taxpayers Information including 
 * their assessed home value, determine their bracket, calculate the taxes they
 * owe, display their tax bill, and append tax statistics. 
 */

#include <iostream>
#include <fstream>
#include <iomanip>
#include <cstring>

using namespace std;

int main(void) {

    //Variables
    char fileName[80], currentDate[80], dueDate[80];
    ifstream taxInfoFile;
    ofstream taxStatsFile;
    int numTaxpayers;
    char taxpayerName[80], taxpayerAddress[80];
    double homeAssessment;
    double taxDue;
    int under10k = 0, between10k30k = 0, between30k60k = 0, over60k = 0;
    double totalTaxes = 0;
    
    
    
    //Get File Name
    cout << "Enter File Name: ";
    cin.getline(fileName, 80);
    
    //Get Current Date
    cout << "Enter Current Date(MM/DD/YYYY): ";
    cin.getline(currentDate, 80);
    
    //Get Due Date
    cout << "Enter Due Date(MM/DD/YYYY): ";
    cin.getline(dueDate, 80);
    
    //Get Number of Taxpayers
    taxInfoFile.open(fileName);
    taxInfoFile >> numTaxpayers;
    
    //Process Each Taxpayer
    for(int cnt = 0; cnt < numTaxpayers; cnt++){
        //Get Taxpayer Name
        taxInfoFile.ignore(1000, '\n');
        taxInfoFile.getline(taxpayerName, 80);
        //Get TaxPayer Address
        taxInfoFile.getline(taxpayerAddress, 80);
        //Get Taxpayer Home Assessment
        taxInfoFile >> homeAssessment;
        
        //Calculate Property Tax
    if(homeAssessment < 10000){
        taxDue = homeAssessment * 0.03;
    }
    else{
        if(homeAssessment >= 10000 && homeAssessment <= 30000){
            taxDue = homeAssessment * 0.04;
        }
        else{
            if(homeAssessment >= 30001 && homeAssessment <= 60000){
                taxDue = homeAssessment * 0.05;
            }
            else{
                taxDue = homeAssessment * 0.06;
            }
        }
    }
    
    //Add to Bracket Total
    if(homeAssessment < 10000){
        under10k++;
    }
    else{
        if(homeAssessment >= 10000 && homeAssessment <= 30000){
            between10k30k++;
        }
        else{
            if(homeAssessment >= 30001 && homeAssessment <= 60000){
                between30k60k++;
            }
            else{
                over60k++;
            }
        }
    }
        totalTaxes = totalTaxes + taxDue;
    //Display Individual Summary
        cout << right << setw(47) << "City Of Bikini Bottom" << endl << endl;
        cout << setw(45) << "Property Tax Bill" << endl << endl;
        cout << left <<  currentDate << endl << endl;
        cout << taxpayerName << endl;
        cout << taxpayerAddress << endl << endl;
        cout << fixed << setprecision(2) << "Current Assessed Value: $" << homeAssessment << endl << endl;
        cout << "Property Tax Due: $" << taxDue << endl << endl;
        cout << "Date DUE: " << dueDate << endl << endl;   
    }
    taxInfoFile.close();
    
    //Append Bracket Totals
    taxStatsFile.open("taxStatistics.txt", ios::app);
    taxStatsFile << right << setw(47) << "City Of Bikini Bottom" << endl << endl;
    taxStatsFile << setw(48) << "Property Tax Statistics" << endl << endl;
    taxStatsFile << setw(35) << "As of " << currentDate << endl << endl;
    taxStatsFile << left << "Number of assessments below $10,000: " << right << setw(18) << under10k << endl;
    taxStatsFile << left << "Number of assessments between $10,000-$30,000: " << right << setw(8) << between10k30k << endl;
    taxStatsFile << left << "Number of assessments between $30,001-$60,000: " << right << setw(8) << between30k60k << endl;
    taxStatsFile << left << "Number of assessments above $60,000: " << right << setw(18) << over60k << endl << endl;
    taxStatsFile << left << "Total Taxes: " << right << setw(42) << fixed << setprecision(2) << "$" << totalTaxes << endl << endl;
    taxStatsFile.close();
    
    return 0;
}

