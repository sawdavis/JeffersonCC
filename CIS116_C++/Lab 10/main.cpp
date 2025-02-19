/* 
 * Name: Sawyer Davis
 * Due Date: December 13, 2022
 * Project Name: Lab 10
 * Project Description: This program is used to calculate a average weather
 * information and display a weather report from weather data stored inside a
 *  file.
 */

#include <iostream>
#include <fstream>
#include <iomanip>
#include <cstring>

using namespace std;

//Function Prototypes
void GetFileName(char[]);
void GetDate(ifstream&, char[], char[]);
void DisplayHeadings(char[]);
void ProcessWeatherInfo(ifstream&, int&, int&, double&, double&, int&);
void GetWeatherInfo(ifstream&, int&, int&, int&, int&, char[], double&, double&);
void ConvertTo12hr(int&, char[]);
void UpdateTotals(int&, int, int&, int, double&, double, double&, double, int&);
void DisplayReport(int, int, char[], int, int, char[], double, double);
void ComputeAverages(int, int, int, double, double, double&, double&, double&, double&);
double ComputeAverage(int, int);
void AppendAverages(char[], double, double, double, double);


int main(void) {

    char fileName[80], date[80];
    ifstream infoFile;
    int totalTemp = 0;
    int totalSpeed = 0;
    int numLoop = 0;
    double totalHumidity = 0, totalBar = 0;
    double avgTemp, avgSpeed, avgHumidity, avgBar;
    
    //Get File Name
    GetFileName(fileName);
    //Get Date
    GetDate(infoFile, fileName, date);
    //Display Headings
    DisplayHeadings(date);
    //Process Weather Information
    ProcessWeatherInfo(infoFile, totalTemp, totalSpeed, totalHumidity, totalBar, numLoop);
    //Compute Averages
    ComputeAverages(totalTemp, numLoop, totalSpeed, totalHumidity, totalBar, avgTemp, avgSpeed, avgHumidity, avgBar);
    //Append Averages
    AppendAverages(date, avgTemp, avgSpeed, avgHumidity, avgBar);
    return 0;
}

//Get File Name
void GetFileName(char fileName[80]){
    
    cout << "Enter File Name: ";
    cin.getline(fileName, 80);
    cout << endl;
}

//Get Date
void GetDate(ifstream & infoFile, char fileName[80], char date[80]){
    
    infoFile.open(fileName);
    infoFile.getline(date, 80);
    
}

//Display Headings
void DisplayHeadings(char date[80]){
    
    cout << "Date:  " << date << endl << endl;
    cout << left << "Time" << right << setw(15) << "Air Temp" << setw(20) << "Wind Speed" << setw(25) << "Wind Direction" << setw(15) << "Humidity" << setw(25) << "Bar Pressure" << endl;
}

//Process Weather Information
void ProcessWeatherInfo(ifstream & infoFile, int & totalTemp, int & totalSpeed, double & totalHumidity, double & totalBar, int & numLoop){
    
    int hours, min, temp, windSpeed;
    char windDir[3];
    double humidity, barPressure;
    char amPM[80];
    
    while(!infoFile.eof()){
        
        GetWeatherInfo(infoFile, hours, min, temp, windSpeed, windDir, humidity, barPressure);
        ConvertTo12hr(hours, amPM);
        UpdateTotals(totalTemp, temp, totalSpeed, windSpeed, totalHumidity, humidity, totalBar, barPressure, numLoop);
        DisplayReport(hours, min, amPM, temp, windSpeed, windDir, humidity, barPressure);
    
    }
    
    infoFile.close();
    
    
}

//Get Weather Info
void GetWeatherInfo(ifstream & infoFile, int & hours, int & min, int & temp, int & windSpeed, char windDir[3], double & humidity, double & barPressure){
    
    infoFile >> hours;
    infoFile >> min;
    infoFile >> temp;
    infoFile >> windSpeed;
    infoFile >> windDir; 
    infoFile >> humidity;
    infoFile >> barPressure;
    infoFile.ignore(1000, '\n');

}

// Convert to 12hr Time
void ConvertTo12hr(int & hours, char amPM[80]){
    if(hours > 11){
        strcpy(amPM, "PM");
    }
    else{
        strcpy(amPM, "AM");
    }
    
    if(hours > 12){
        hours = hours - 12;
    }
    
    if(hours == 0){
        hours = 12;
    }
    
}

//Update Totals
void UpdateTotals(int & totalTemp, int temp, int & totalSpeed, int windSpeed, double & totalHumidity, double humidity, double & totalBar, double barPressure, int & numLoop){
    totalTemp = totalTemp + temp;
    totalSpeed = totalSpeed + windSpeed;
    totalHumidity = totalHumidity + humidity;
    totalBar = totalBar + barPressure;
    numLoop++;
}

//Display Report
void DisplayReport(int hours, int min, char amPM[80], int temp, int windSpeed, char windDir[2], double humidity, double barPressure){
    cout << setfill('0') << setw(2) << hours << ":" << setw(2) << min << amPM << setfill(' ') << right << setw(12) << temp << setw(20) << windSpeed << setw(25) << windDir << setw(15) << fixed << setprecision(2) << humidity << setw(25) << barPressure << endl;

}

void ComputeAverages(int totalTemp, int numLoop, int totalSpeed, double totalHumidity, double totalBar, double & avgTemp, double & avgSpeed, double & avgHumidity, double & avgBar){
    
    
    avgTemp = ComputeAverage(totalTemp, numLoop);
    avgSpeed = ComputeAverage(totalSpeed, numLoop);
    avgHumidity= ComputeAverage(totalHumidity, numLoop);
    avgBar = ComputeAverage(totalBar, numLoop);
}

double ComputeAverage(int total,  int numLoop){
    
    double avg;
    
    avg = total/(numLoop + 0.00);
    
    return avg;     
}

void AppendAverages(char date[80], double avgTemp, double avgSpeed, double avgHumidity, double avgBar){
    
    ofstream avgFile;
    
    avgFile.open("avgInfo.txt", ios::app);
    avgFile << "Date: " << date << endl;
    avgFile << left << "Avg. Air Temp" << right << setw(20) << "Avg. Wind Speed" << setw(25) << "Avg. Humidity" << setw(25) << "Avg. Bar Pressure" << endl;
    avgFile << fixed << setprecision(2) << setw(13) << avgTemp << right << setw(20) << avgSpeed << setw(25) << avgHumidity << setw(25) << avgBar << endl;
    
    avgFile.close();
}
