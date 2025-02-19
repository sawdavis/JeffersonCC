
/* 
 * Name: Sawyer Davis
 * Due Date: October 21, 2022
 * Project Name: Lab 06
 * Project Description: This program will take Player and CPU inputted rock paper
 * scissors actions and will generate generate the winner and display a win
 * tally.
 */


#include <iostream>
#include <iomanip>
#include <cstring>
#include <fstream>
using namespace std;

/*
 * 
 */
int main(void) {


    ifstream cpuChoiceFile;
    ifstream winTallyInFile;
    ofstream winTallyOutFile;
    char playerName[80];
    char playerChoice, cpuChoice;
    int pWinTally, drawTally, cpuWinTally;
    char result;
    
    //Get Player Name
    cout << "Enter Player Name: ";
    cin.getline(playerName, 80);
    
    //Get Player Choice
    cout << "(R)ock, (P)aper, or (S)cissors? ";
    cin >> playerChoice;

    //Get CPU Choice
    cpuChoiceFile.open("cpuChoice.txt");
    cpuChoiceFile >> cpuChoice;
    cpuChoiceFile.close();
    
    //Get Tally Information
    winTallyInFile.open("winTally.txt");
    winTallyInFile.ignore(1000, ':');
    winTallyInFile >> pWinTally;
    winTallyInFile.ignore(1000, ':');
    winTallyInFile >> drawTally;
    winTallyInFile.ignore(1000, ':');
    winTallyInFile >> cpuWinTally;
    winTallyInFile.close();
    
    //Determine Results
    if(playerChoice == cpuChoice){
        result = 'd';
    }
    else{
        if ((playerChoice == 'R' && cpuChoice == 'S') || (playerChoice == 'P' && cpuChoice == 'R')
                || (playerChoice == 'S' && cpuChoice == 'P')) {
            result = 'w';
        }
        else{
            result = 'l';
        }
    }
    //Calculate New Win Tallies
    if (result == 'd') {
        drawTally = drawTally + 1;
    }
    if (result == 'w') {
        pWinTally = pWinTally + 1;
    }
    if (result == 'l') {
        cpuWinTally = cpuWinTally + 1;
    }
    
    //Store Results to File
    winTallyOutFile.open("winTally.txt");
    winTallyOutFile << "Human Wins: " << pWinTally << endl;
    winTallyOutFile << "Draws: " << drawTally << endl;
    winTallyOutFile << "CPU Wins: " << cpuWinTally;
    winTallyOutFile.close();
    
    //Display Winner
    if (result == 'd') {
        cout << "Draw!";
    }
    if (result == 'w') {
        cout << playerName << " Wins!";
    }
    if (result == 'l') {
        cout << "CPU Wins!";
    }
    
    
            
    return 0;
}

