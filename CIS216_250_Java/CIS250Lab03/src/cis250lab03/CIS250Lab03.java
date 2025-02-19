package cis250lab03;

import java.util.Scanner;
import java.io.*;

public class CIS250Lab03 {

    public static void main(String[] args) throws IOException {
        BagADT studentRoster;
        studentRoster = new BagADT();
        studentRoster.Create(250);
        Student student;
        
        student = LoadStudents(studentRoster);
        EditStudentRoster(student, studentRoster);
        GenerateTeams(studentRoster);
        
    }
    
    //Process Students
    public static Student LoadStudents(BagADT studentRoster) throws IOException {
        Student student;
        student = new Student();
        String fileName, uFirstName, uLastName;
        int uAge;
        Scanner kbd = new Scanner(System.in);
        
        System.out.println("What is the name of the file?");
        fileName = kbd.nextLine();
        
        File studentFile;
        Scanner studentFileSC;
        studentFile = new File(fileName);
        studentFileSC = new Scanner(studentFile);
        for(int cnt = 0; cnt < 200; cnt++){
            uFirstName = studentFileSC.nextLine();
            uLastName = studentFileSC.nextLine();
            uAge = studentFileSC.nextInt();
            studentFileSC.nextLine();
            student.SetAll(uFirstName, uLastName, uAge);
            studentRoster.Add(student);
        }
        studentFileSC.close();
        
        return student;
    }
    
    //Edit Student Roster
    public static void EditStudentRoster(Student student, BagADT studentRoster){
        char choice;
        Scanner kbd = new Scanner(System.in);
        
        System.out.println("Would you like to (A)dd Student, (R)emove Student, or (Q)uit?");
        choice = kbd.nextLine().charAt(0);
        while(choice != 'Q'){
            if(choice == 'A'){
                AddStudent(student, studentRoster);
                System.out.println("Would you like to (A)dd Student, (R)emove Student, or (Q)uit?");
                choice = kbd.nextLine().charAt(0);
            }
            else
                if(choice == 'R'){
                    RemoveStudent(student, studentRoster);
                    System.out.println("Would you like to (A)dd Student, (R)emove Student, or (Q)uit?");
                    choice = kbd.nextLine().charAt(0);
                }
                else{
                    System.out.println("Would you like to (A)dd Student, (R)emove Student, or (Q)uit?");
                    choice = kbd.nextLine().charAt(0);
                }
        }
    }
    
    //Generate Teams
    public static void GenerateTeams(BagADT studentRoster) throws IOException{
        String uFirstName, uLastName;
        int uAge;
        int cnt;
        cnt = studentRoster.GetCurrentSize();
        Student temp;
        
        PrintWriter AssignTeam1 = new PrintWriter("Team1.txt");
        PrintWriter AssignTeam2 = new PrintWriter("Team2.txt");
        PrintWriter AssignTeam3 = new PrintWriter("Team3.txt");
        PrintWriter AssignTeam4 = new PrintWriter("Team4.txt");
        
        while(!studentRoster.IsEmpty()){
            temp = studentRoster.Remove();
            uFirstName = temp.GetKey();
            uLastName = temp.GetLastName();
            uAge = temp.GetAge();
            switch((cnt%4)+1){
                case 1:
                    AssignTeam1.println(uFirstName + ", " + uLastName + ", " + uAge);
                    break;
                case 2:
                    AssignTeam2.println(uFirstName + ", " + uLastName + ", " + uAge);
                    break;
                case 3:
                    AssignTeam3.println(uFirstName + ", " + uLastName + ", " + uAge);
                    break;
                case 4:
                    AssignTeam4.println(uFirstName + ", " + uLastName + ", " + uAge);
                    break;
            }
            cnt--;
        }
        AssignTeam1.close();
        AssignTeam2.close();
        AssignTeam3.close();
        AssignTeam4.close();
    }
    
    //Add Students
    public static void AddStudent(Student student, BagADT studentRoster){
        boolean success;
        String uFirstName, uLastName;
        int uAge;
        Scanner kbd = new Scanner(System.in);
        
        System.out.println("What is the first name?");
        uFirstName = kbd.nextLine();
        System.out.println("What is the last name?");
        uLastName = kbd.nextLine();
        System.out.println("What age?");
        uAge = kbd.nextInt();
        kbd.nextLine();
        student.SetAll(uFirstName, uLastName, uAge);
        success = studentRoster.Add(student);
        if(success == true)
            System.out.println("Student Added");
        else
            System.out.println("Student Cannot be Added");
    }
    
    //Remove Student
    public static void RemoveStudent(Student student, BagADT studentRoster){
        Scanner kbd = new Scanner(System.in);
        String key;
        char corr;
        Student temp;
        
        
        System.out.println("Enter student first name:   ");
        key = kbd.nextLine();
       
        corr = 'N';
        while(corr == 'N') {
           temp = studentRoster.RemoveSpecific(key);
           System.out.println(temp.GetKey() + " " + temp.GetLastName() + " Age: " + temp.GetAge());
           System.out.println("Is this the correct student?   ");
           corr = kbd.nextLine().charAt(0);
           if(corr == 'N'){
                   studentRoster.Add(temp);
                   
           }
           
       }
    }
}
