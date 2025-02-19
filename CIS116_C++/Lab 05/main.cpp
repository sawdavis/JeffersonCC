
/* 
 * Name: Sawyer Davis
 * Due Date: October 7, 2022
 * Project Name: Lab 04
 * Project Description: This course will take inputted information about 6
 * courses and calculate and display a GPA Summary. 
 */

#include <iostream>
#include <fstream>
#include <iomanip>

using namespace std;

int main(void) {

    ifstream gradesFile;
    ifstream weightFile;
    ofstream midtermFile;
    char studentName[80];
    char studentAddress[150];
    double exam, homework, lab1, lab2, lab3, lab4, quiz1, quiz2;
    double examWeight, homeworkWeight, labWeight, quizWeight;
    double labAVG, quizAVG, examWeightedAVG, homeworkWeightedAVG, labWeightedAVG, quizWeightedAVG, midtermAVG;
    
    //Get Student Name
    cout << "Enter Student Name: ";
    cin.getline(studentName, 80);
    
    //Get Student Address
    cout << "Enter Student Address (#### Street, City, State ZipCode): ";
    cin.ignore (1000, '\n');
    cin.getline(studentAddress, 150);
    
    //Get Assignment Grades from File
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
    
    //Get Assignment Weights From File
    weightFile.open("weights.txt");
    weightFile >> examWeight;
    weightFile >> homeworkWeight;
    weightFile >> labWeight;
    weightFile >> quizWeight;
    weightFile.close();
    
    //Calculate Weighted Average
    examWeightedAVG = exam * examWeight;
    homeworkWeightedAVG = homework * homeworkWeight; 
    labAVG = (lab1 + lab2 + lab3 + lab4) / 4.0;
    labWeightedAVG = labAVG * labWeight;
    quizAVG = (quiz1 + quiz2) / 2.0;
    quizWeightedAVG = quizAVG * quizWeight;
    
    //Calculate Midterm Average
    midtermAVG =  examWeightedAVG + homeworkWeightedAVG + labWeightedAVG + quizWeightedAVG; 
    
    
    //Append Midterm Average
    midtermFile.open("midterm.txt", ios::app);
    midtermFile << studentName << endl;
    midtermFile << midtermAVG;
    midtermFile.close();
            
    //Display Grade Summary
    cout << "Jefferson Community College" << endl;
    cout << studentName << endl;
    cout << studentAddress << endl;
    cout << "Grades" << endl;
    cout << "Exam" << right << setw(10) << "Homework" << setw(5) << "Labs" << setw(10) << "Quizzes" << endl;
    cout << setw(4) << exam << setw(10) << homework << setw(5) << lab1 << setw(10) << quiz1 << endl;
    cout << setw(19) << lab2 << setw(10) << quiz2 << endl; 
    cout << right << setw(19) << lab3 << endl;
    cout << setw(19) << lab4 << endl << endl;
    cout << fixed << setprecision(2) << "Midterm Average: " << left << setw(39) << midtermAVG << endl;
    
    
    return 0;
}

