package laboratory8;

import java.util.Scanner;
import java.io.*;

public class Laboratory8 {

    /*
    04/11/2023
    Sawyer Davis
    Lab 07
    Due 04/11/2023
    Performs functions seen in an audio receiver. The program allows the user
    to turn off the receiver, change band (from AM to FM and vice versa),
    increment the frequency, decrement the frequency, choose a preset frequency
    (up to five per band), store current frequency as a preset frequency,
    increment volume, and decrement volume.*/
    
    public static void main(String[] args) throws IOException {
        Receiver theReceiver;
        theReceiver = new Receiver( );
        theReceiver.LoadSettings();
        PerformEachTask(theReceiver);
        theReceiver.SaveSettings();
    }
    
    public static void PerformEachTask(Receiver theReceiver){
        char choice;
        
        
        choice = GetChoice();
        while(choice != '8'){
            PerformChoice(choice, theReceiver);
            theReceiver.DisplayStatus();
            choice = GetChoice();
            
        }
    }
    
    public static char GetChoice(){
        char choice;
        Scanner kbd = new Scanner(System.in);
        
        System.out.println("1. Increase Volume");
        System.out.println("2. Decrease Volume");
        System.out.println("3. Increase Frequency");
        System.out.println("4. Decrease Frequency");
        System.out.println("5. Set New Preset");
        System.out.println("6. Select New Preset");
        System.out.println("7. Change Band");
        System.out.println("8. Turn Off Receiver");
        
        choice = kbd.nextLine().charAt(0);
        
        return choice;
    }
    
    public static void PerformChoice(char choice, Receiver theReceiver){
        if(choice == '1'){
            theReceiver.IncreaseVolume();
        }
        if(choice == '2'){
            theReceiver.DecreaseVolume();
        }
        if(choice == '3'){
            theReceiver.IncreaseFrequency();
        }
        if(choice == '4'){
            theReceiver.DecreaseFrequency();
        }
        if(choice == '5'){
            SetNewPreset(theReceiver);
        }
        if(choice == '6'){
            SelectNewPreset(theReceiver);
        }
        if(choice == '7'){
            theReceiver.ChangeBand();
        }
        if(choice == '8'){
            System.out.println("RECEIVER OFF");
        }
    }
    
    //Select New Preset
    public static void SelectNewPreset(Receiver theReceiver){
        int presetChoice;
        Scanner kbd = new Scanner(System.in);
        System.out.print("What Preset Would you like to Select?  ");
        presetChoice = kbd.nextInt();
        if(presetChoice < 1 || presetChoice > 5){
            System.out.print("What Preset Would you Like to Select?  ");
            presetChoice = kbd.nextInt();
        }
        theReceiver.SelectPreset(presetChoice);
    }

    //Set New Preset
    public static void SetNewPreset(Receiver theReceiver){
        int presetChoice;
        Scanner kbd = new Scanner(System.in);
        System.out.print("What Preset Would you like to Replace?  ");
        presetChoice = kbd.nextInt();
        if(presetChoice < 1 || presetChoice > 5){
            System.out.print("What Preset Would you Like to Replace?  ");
            presetChoice = kbd.nextInt();
        }
        theReceiver.SetPreset(presetChoice);
    }
    
    
}
