
/* 
 * Name: Sawyer Davis
 * Due Date: November 15, 2022
 * Project Name: Lab 08
 * Project Description: This program will take inputted gr
 */

#include <iostream>
#include <fstream>
#include <iomanip>

using namespace std;

//Function Prototype
void GetStudentInformation(char[], char[]);
void GetStudentName(char[]);
void GetStudentAddress(char[]);
void GetAssignmentGrades(ifstream&, double&, double&, double&, double&, double&, double&, double&, double&);
void GetAssignmentWeights(ifstream&, double&, double&, double&, double&);
void CalculateWeightedAverage(double, double, double, double, double, double, double, double, double, double, double, double, double&, double&, double&, double&);
double CalculateMidtermAverage (double, double, double, double);
void AppendMidtermSummary(ofstream&, char[], double);
void DisplayGradeSummary(char[], char[], double, double, double, double, double, double, double, double, double);

int main(void) {
    
    char studentName[80], studentAddress[150];
    ifstream gradesFile, weightsFile;
    ofstream midtermFile;
    double exam, homework, lab1, lab2, lab3, lab4, quiz1, quiz2;
    double examWeight, homeworkWeight, labWeight, quizWeight, midtermAVG;
    double examWeightedAVG, homeworkWeightedAVG, labWeightedAVG, quizWeightedAVG;
    
    //Call Function
    GetStudentInformation(studentName, studentAddress);
    GetAssignmentGrades(gradesFile, exam, homework, lab1, lab2, lab3, lab4, quiz1, quiz2);
    GetAssignmentWeights(weightsFile, examWeight, homeworkWeight, labWeight, quizWeight);
    CalculateWeightedAverage(exam, examWeight, homework, homeworkWeight, lab1, lab2, lab3, lab4, labWeight, quiz1, quiz2, quizWeight, examWeightedAVG, homeworkWeightedAVG, labWeightedAVG, quizWeightedAVG);
    midtermAVG = CalculateMidtermAverage(examWeightedAVG, homeworkWeightedAVG, labWeightedAVG, quizWeightedAVG);
    AppendMidtermSummary(midtermFile, studentName, midtermAVG);
    DisplayGradeSummary(studentName, studentAddress, exam, homework, lab1, quiz1, lab2, quiz2, lab3, lab4, midtermAVG);
    
    return 0;
}

//Get Student Name
void GetStudentName(char studentName[80]){
    
    cout << "Enter Student Name: ";
    cin.getline(studentName, 80);
    
}

//Get Student Address
void GetStudentAddress(char studentAddress[150]){
    
    cout << "Enter Student Address (#### Street, City, State ZipCode): ";
    cin.getline(studentAddress, 150);
    cout << endl << endl;
}
//Get Student Information
void GetStudentInformation(char studentName[80], char studentAddress[150]){
    
    GetStudentName(studentName);
    GetStudentAddress(studentAddress);     
}

//Get Assignment Grades from File
void GetAssignmentGrades(ifstream & gradesFile, double & exam, double & homework, double & lab1, double & lab2, double & lab3, double & lab4, double & quiz1, double & quiz2){
    
    //Get Grades From File
    gradesFile.open("grades.txt");
    gradesFile >> exam;
    gradesFile >> homework;
    gradesFile >> lab1;
    gradesFile >> lab2;
    gradesFile >> lab3;
    gradesFile >> lab4;
    gradesFile >> quiz1;
    gradesFile >> quiz2;
    gradesFile.close();
}
//Get Assignment Weights from File
void GetAssignmentWeights(ifstream & weightsFile, double & examWeight, double & homeworkWeight, double & labWeight, double & quizWeight){
   
    //Get Weights From File
    weightsFile.open("weights.txt");
    weightsFile >> examWeight;
    weightsFile >> homeworkWeight;
    weightsFile >> labWeight;
    weightsFile >> quizWeight;
    weightsFile.close();

}

void CalculateWeightedAverage(double exam, double examWeight, double homework,
    double homeworkWeight, double lab1, double lab2, double lab3,
    double lab4, double labWeight, double quiz1, double quiz2, double quizWeight,
    double & examWeightedAVG, double & homeworkWeightedAVG, double & labWeightedAVG, double & quizWeightedAVG){
    
    //Calculate Weighted Average      
    examWeightedAVG = (exam * examWeight);
    homeworkWeightedAVG = (homework * homeworkWeight);
    labWeightedAVG = ((lab1 + lab2 +lab3 + lab4) / 4.0) * labWeight;
    quizWeightedAVG = ((quiz1 + quiz2) / 2.0) * quizWeight;
       
}

double CalculateMidtermAverage(double examWeightedAVG, double homeworkWeightedAVG, double labWeightedAVG, double quizWeightedAVG){
    
    //Calculate Midterm Average
    double midtermAVG;
    
    midtermAVG = (examWeightedAVG + homeworkWeightedAVG + labWeightedAVG + quizWeightedAVG);
    
    return midtermAVG;
}

void AppendMidtermSummary(ofstream & midtermFile, char studentName[80], double midtermAVG){
    
    //Append Midterm Summary to File
    midtermFile.open("midterm.txt", ios::app);
    midtermFile << studentName << endl;
    midtermFile << midtermAVG;
    midtermFile.close();
    
}

void DisplayGradeSummary(char studentName[80],char studentAddress[80],
        double exam, double homework, double lab1, double quiz1, double lab2,
        double quiz2, double lab3, double lab4, double midtermAVG){
    
    //Display Grade Summary to User
    cout << "Jefferson Community College" << endl;
    cout << studentName << endl;
    cout << studentAddress << endl << endl;
    cout << "Grades" << endl;
    cout << "Exam" << right << setw(10) << "Homework" << setw(5) << "Labs" << setw(10) << "Quizzes" << endl;
    cout << setw(4) << exam << setw(10) << homework << setw(5) << lab1 << setw(10) << quiz1 << endl;
    cout << setw(19) << lab2 << setw(10) << quiz2 << endl; 
    cout << right << setw(19) << lab3 << endl;
    cout << setw(19) << lab4 << endl << endl;
    cout << fixed << setprecision(2) << "Midterm Average: " << left << setw(39) << midtermAVG << endl;
}