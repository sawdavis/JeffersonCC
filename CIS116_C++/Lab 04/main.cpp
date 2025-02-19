
/* 
 * Name: Sawyer Davis
 * Due Date: October 7, 2022
 * Project Name: Lab 04
 * Project Description: This course will take inputted information about 6
 * courses and calculate and display a GPA Summary. 
 */

#include <iostream>
#include <iomanip>
#include <cstring>

using namespace std;

int main(void) {

    //Set Variables
    char studentName[80];
    char semester[80];
    char course1Name[80], course2Name[80], course3Name[80], course4Name[80], course5Name[80], course6Name[80];
    int course1AttHrs, course2AttHrs, course3AttHrs, course4AttHrs, course5AttHrs, course6AttHrs, totalAttHrs;
    int course1Weight, course2Weight, course3Weight, course4Weight, course5Weight, course6Weight;
    int qualityPts1, qualityPts2, qualityPts3, qualityPts4, qualityPts5, qualityPts6, totalQualityPts;
    double gpa;
    //Get Student Name
    cout << "Enter Student Name: " << endl;
    cin.getline(studentName, 80);
    
    //Get Semester
    cout << "Enter the Semester: " << endl;
    cin.getline(semester, 80);
    
    //Get Course Information
    cout << "Enter Course 1 Name: " << endl;
    cin.getline(course1Name, 80);
    cout << "Enter Course 1 Attempted Hours: " << endl;
    cin >> course1AttHrs;
    cout << "Enter Course 1 Grade Weight: " << endl;
    cin >> course1Weight;
    cin.ignore (1000, '\n');

    cout << "Enter Course 2 Name: " << endl;
    cin.getline(course2Name, 80);
    cout << "Enter Course 2 Attempted Hours: " << endl;
    cin >> course2AttHrs;
    cout << "Enter Course 2 Grade Weight: " << endl;
    cin >> course2Weight;
    cin.ignore (1000, '\n');

    cout << "Enter Course 3 Name: " << endl;
    cin.getline(course3Name, 80);
    cout << "Enter Course 3 Attempted Hours: " << endl;
    cin >> course3AttHrs;
    cout << "Enter Course 3 Grade Weight: " << endl;
    cin >> course3Weight;
    cin.ignore (1000, '\n');

    cout << "Enter Course 4 Name: " << endl;
    cin.getline(course4Name, 80);
    cout << "Enter Course 4 Attempted Hours: " << endl;
    cin >> course4AttHrs;
    cout << "Enter Course 4 Grade Weight: " << endl;
    cin >> course4Weight;
    cin.ignore (1000, '\n');

    cout << "Enter Course 5 Name: " << endl;
    cin.getline(course5Name, 80);
    cout << "Enter Course 5 Attempted Hours: " << endl;
    cin >> course5AttHrs;
    cout << "Enter Course 5 Grade Weight: " << endl;
    cin >> course5Weight;
        cin.ignore (1000, '\n');

    cout << "Enter Course 6 Name: " << endl;
    cin.getline(course6Name, 80);
    cout << "Enter Course 6 Attempted Hours: " << endl;
    cin >> course6AttHrs;
    cout << "Enter Course 6 Grade Weight: " << endl;
    cin >> course6Weight;
    
    //Calculate Quality Points for Each Course
    qualityPts1 = course1AttHrs * course1Weight;
    qualityPts2 = course2AttHrs * course2Weight;
    qualityPts3 = course3AttHrs * course3Weight;
    qualityPts4 = course4AttHrs * course4Weight;
    qualityPts5 = course5AttHrs * course5Weight;
    qualityPts6 = course6AttHrs * course6Weight;
    
    //Calculate Total Quality Points
    totalQualityPts = qualityPts1 + qualityPts2 + qualityPts3 + qualityPts4 + qualityPts5 + qualityPts6;
    
    //Calculate Total Attempted Hours
    totalAttHrs = course1AttHrs + course2AttHrs + course3AttHrs + course4AttHrs + course5AttHrs + course6AttHrs;
    
    //Calculate GPA
    gpa = totalQualityPts / (totalAttHrs + 0.0);
    
    //Display GPA Summary
        //Display Student Information
        //Display Semester Grade Summary 
    
    //Display Student Information
    cout << "GPA Report for " << studentName << "(" << semester << " Semester)" << endl << endl;
    
    //Display Semester Grade Summary
    cout << left << setw(30) << "Course Name" << right << setw(10) << "Att. Hrs." << setw(14) << "Grd. Wght." << endl;
    cout << left << setw(30) << course1Name << right << setw(10) << course1AttHrs << setw(14) << course1Weight << endl;
    cout << left << setw(30) << course2Name << right << setw(10) << course2AttHrs << setw(14) << course2Weight << endl;
    cout << left << setw(30) << course3Name << right << setw(10) << course3AttHrs << setw(14) << course3Weight << endl;
    cout << left << setw(30) << course4Name << right << setw(10) << course4AttHrs << setw(14) << course4Weight << endl;
    cout << left << setw(30) << course5Name << right << setw(10) << course5AttHrs << setw(14) << course5Weight << endl;
    cout << left << setw(30) << course6Name << right << setw(10) << course6AttHrs << setw(14) << course6Weight << endl << endl;
    cout << "Total Attempted Hours: " << setw(30) << totalAttHrs << endl;
    cout << "Total Quality Points: " << setw(30) << totalQualityPts << endl << endl;
    cout << fixed << setprecision(2) << "GPA: " << setw(30) << gpa; 

    return 0;
}
