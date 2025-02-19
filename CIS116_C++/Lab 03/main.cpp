
/* 
 * Name: Sawyer Davis
 * Due Date: 10/02/2022
 * Project Name: Lab 03
 * Project Description: This project ask the user to input the name of the
 * vessel in distress, the position of the vessel in distress, the number of 
 * people on board, and the time and date. The program then calculates the 
 * distance and time it will take for each rescue vessel to reach the ship in 
 * distress, and then displays the calculated data. 
 */

#include <iostream>
#include <cmath>
#include <cstring>
#include <iomanip>

using namespace std;

int main(void) {

    //Variables
    char cg1Name[80], cg2Name[80], cg3Name[80], cg4Name[80], cg5Name[80], cg6Name[80],cg7Name[80];
    int cg1XCoord, cg2XCoord,cg3XCoord, cg4XCoord, cg5XCoord, cg6XCoord, cg7XCoord;
    int cg1YCoord, cg2YCoord,cg3YCoord, cg4YCoord, cg5YCoord, cg6YCoord, cg7YCoord; 
    int cg1Speed, cg2Speed, cg3Speed, cg4Speed, cg5Speed, cg6Speed, cg7Speed;
    int cg1Cap, cg2Cap, cg3Cap, cg4Cap, cg5Cap, cg6Cap, cg7Cap;
    char distressVesselName[80], dateTime[80];
    int distressXCoord, distressYCoord, numPeopleToRescue;
    double distance1, distance2, distance3, distance4, distance5, distance6, distance7;
    double time1, time2, time3, time4, time5, time6, time7;
    
    //Set Coast Guard Vessel Information
    strcpy(cg1Name, "The Hercules");
    cg1XCoord = 0;
    cg1YCoord = 20;
    cg1Speed = 22;
    cg1Cap = 8;
    
    strcpy(cg2Name, "S.S. Glowworm");
    cg2XCoord = 5;
    cg2YCoord = 5;
    cg2Speed = 30;
    cg2Cap = 5;
    
    strcpy(cg3Name, "S.S. Jawesome");
    cg3XCoord = 18;
    cg3YCoord = 2;
    cg3Speed = 12;
    cg3Cap = 25;
    
    strcpy(cg4Name, "Neptune");
    cg4XCoord = 29;
    cg4YCoord = 10;
    cg4Speed = 19;
    cg4Cap = 14;

    strcpy(cg5Name, "S.S. Anne");
    cg5XCoord = 3;
    cg5YCoord = 12;
    cg5Speed = 23;
    cg5Cap = 8;
    
    strcpy(cg6Name, "Aquaholic");
    cg6XCoord = 11;
    cg6YCoord = 17;
    cg6Speed = 12;
    cg6Cap = 25;
    
    strcpy(cg7Name, "S.S. Ourang Medan");
    cg7XCoord = 12;
    cg7YCoord = 22;
    cg7Speed = 22;
    cg7Cap = 8;
    
    //Get Name of Distress Vessel
    cout << "Enter Name of Vessel in Distress: " << endl;
    cin.getline (distressVesselName, 80);
    
    //Get Location of Distress Vessel
    cout << "Enter Distress Vessel X-Coordinate: " << endl;
    cin >> distressXCoord;
    cout << "Enter Distress Vessel Y-Coordinate: " << endl;
    cin >> distressYCoord;
    
    //Get Number of People to Rescue
    cout << "Enter the Number of People to be Rescued: " << endl;
    cin >> numPeopleToRescue;
    
    //Get Date and Time of Incident
    cout << "Enter Date and Time (MM/DD/YYYY HH:MM AM/PM): " << endl;
    cin.ignore (1000, '\n');
    cin.getline (dateTime, 80);
    
    //Calculate Distances to Distress Vessels
    distance1 = sqrt((pow(cg1XCoord-distressXCoord,2))+(pow(cg1YCoord-distressYCoord,2)));
    distance2 = sqrt((pow(cg2XCoord-distressXCoord,2))+(pow(cg2YCoord-distressYCoord,2)));
    distance3 = sqrt((pow(cg3XCoord-distressXCoord,2))+(pow(cg3YCoord-distressYCoord,2)));
    distance4 = sqrt((pow(cg4XCoord-distressXCoord,2))+(pow(cg4YCoord-distressYCoord,2)));
    distance5 = sqrt((pow(cg5XCoord-distressXCoord,2))+(pow(cg5YCoord-distressYCoord,2)));
    distance6 = sqrt((pow(cg6XCoord-distressXCoord,2))+(pow(cg6YCoord-distressYCoord,2)));
    distance7 = sqrt((pow(cg7XCoord-distressXCoord,2))+(pow(cg7YCoord-distressYCoord,2)));
    
    //Calculate Time to Distance Vessel
    time1 = distance1 / cg1Speed;
    time2 = distance2 / cg2Speed;
    time3 = distance3 / cg3Speed;
    time4 = distance4 / cg4Speed;
    time5 = distance5 / cg5Speed;
    time6 = distance6 / cg6Speed;
    time7 = distance7 / cg7Speed;
    
    //Display Rescue Summary
    cout << "Vessel in Distress: " << distressVesselName << endl; 
    cout << "Coordinates " << "(" << distressXCoord << "," << distressYCoord << ")" << endl;
    cout << "People to Rescue: " << numPeopleToRescue << endl;
    cout << "Date: " << dateTime << endl;
    cout << left << setw(20) << "Vessel Name" << setw(20) << "Capacity" << setw(20) << "Distance" << setw(20) << "Time" << setw(20) << endl;
    cout << fixed << setprecision(2) << setw(20) << cg1Name << setw(20) << cg1Cap << setw(20) << distance1 << setw(20) << time1 << setw(20) << endl;
    cout << setw(20) << cg2Name << setw(20) << cg2Cap << setw(20) << distance2 << setw(20) << time2 << setw(20) << endl;
    cout << setw(20) << cg3Name << setw(20) << cg3Cap << setw(20) << distance3 << setw(20) << time3 << setw(20) << endl;
    cout << setw(20) << cg4Name << setw(20) << cg4Cap << setw(20) << distance4 << setw(20) << time4 << setw(20) << endl;
    cout << setw(20) << cg5Name << setw(20) << cg5Cap << setw(20) << distance5 << setw(20) << time5 << setw(20) << endl;
    cout << setw(20) << cg6Name << setw(20) << cg6Cap << setw(20) << distance6 << setw(20) << time6 << setw(20) << endl;
    cout << setw(20) << cg7Name << setw(20) << cg7Cap << setw(20) << distance7 << setw(20) << time7 << setw(20) << endl;
    
    return 0;
}
