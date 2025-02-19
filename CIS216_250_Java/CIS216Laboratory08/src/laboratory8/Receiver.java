package laboratory8;

import java.io.*;
import java.util.Scanner;

public class Receiver {

    private int volume, currentFreqAM;
    private String currentBand;
    private double currentFreqFM;
    private int[] presetAM;
    private double[] presetFM;

    //Load Settings
    public void LoadSettings() throws IOException {
        File receiverFile;
        Scanner receiverFileSC;
        receiverFile = new File("receiver.txt");
        receiverFileSC = new Scanner(receiverFile);

        presetAM = new int[5];
        presetFM = new double[5];

        currentBand = receiverFileSC.nextLine();
        currentFreqFM = receiverFileSC.nextDouble();
        currentFreqAM = receiverFileSC.nextInt();
        volume = receiverFileSC.nextInt();
        presetFM[0] = receiverFileSC.nextDouble();
        presetFM[1] = receiverFileSC.nextDouble();
        presetFM[2] = receiverFileSC.nextDouble();
        presetFM[3] = receiverFileSC.nextDouble();
        presetFM[4] = receiverFileSC.nextDouble();
        presetAM[0] = receiverFileSC.nextInt();
        presetAM[1] = receiverFileSC.nextInt();
        presetAM[2] = receiverFileSC.nextInt();
        presetAM[3] = receiverFileSC.nextInt();
        presetAM[4] = receiverFileSC.nextInt();

        receiverFileSC.close();
    }
    //Display Settings
    public void DisplaySettings() {
        System.out.println("Current Band:   " + currentBand);
        System.out.println("Current FM Frequency:   " + currentFreqFM);
        System.out.println("Current AM Frequency:   " + currentFreqAM);
        System.out.println("Current Volume:   " + volume);
        System.out.println("FM Preset 1:   " + presetFM[0]);
        System.out.println("FM Preset 2:   " + presetFM[1]);
        System.out.println("FM Preset 3:   " + presetFM[2]);
        System.out.println("FM Preset 4:   " + presetFM[3]);
        System.out.println("FM Preset 5:   " + presetFM[4]);
        System.out.println("AM Preset 1:   " + presetAM[0]);
        System.out.println("AM Preset 2:   " + presetAM[1]);
        System.out.println("AM Preset 3:   " + presetAM[2]);
        System.out.println("AM Preset 4:   " + presetAM[3]);
        System.out.println("AM Preset 5:   " + presetAM[4]);

    }

    //Save Settings
    public void SaveSettings() throws IOException {
        PrintWriter receiverPW;
        receiverPW = new PrintWriter("receiver.txt");

        receiverPW.println(currentBand);
        receiverPW.println(currentFreqFM);
        receiverPW.println(currentFreqAM);
        receiverPW.println(volume);
        receiverPW.println(presetFM[0]);
        receiverPW.println(presetFM[1]);
        receiverPW.println(presetFM[2]);
        receiverPW.println(presetFM[3]);
        receiverPW.println(presetFM[4]);
        receiverPW.println(presetAM[0]);
        receiverPW.println(presetAM[1]);
        receiverPW.println(presetAM[2]);
        receiverPW.println(presetAM[3]);
        receiverPW.println(presetAM[4]);
        receiverPW.close();
    }

    //Change Band
    public void ChangeBand() {
        if (currentBand.equals("AM")) {
            currentBand = "FM";
        } else {
            currentBand = "AM";
        }

    }
    
    //Increase Frequency
    public void IncreaseFrequency(){
        if (currentBand.equals("AM")) {
            currentFreqAM += 10;
        } else {
            currentFreqFM += 0.2;
        }
    }
    
    //Decrease Frequency
    public void DecreaseFrequency(){
        if (currentBand.equals("AM")) {
            currentFreqAM -= 10;
        } else {
            currentFreqFM -= 0.2;
        }
    }
    
    //Increase Volume
    public void IncreaseVolume(){
        if(volume > -1 && volume < 10){
            volume++;
        }
        else{
            System.out.println("Volume is Unable to be Modified");
        }
    }
    
    //Decrease Volume
    public void DecreaseVolume(){
        if(volume > 0 && volume < 11){
            volume--;
        }
        else{
            System.out.println("Volume is Unable to be Modified");
        }
    }
    
    //Display Status
    public void DisplayStatus(){
        if(currentBand.equals("FM")){
            System.out.println("Current Band:   " + currentBand);
            System.out.println("Current Frequency:   " + currentFreqFM);
            System.out.println("Current Volume:   " + volume);
            System.out.println("Preset 1:   " + presetFM[0]);
            System.out.println("Preset 2:   " + presetFM[1]);
            System.out.println("Preset 3:   " + presetFM[2]);
            System.out.println("Preset 4:   " + presetFM[3]);
            System.out.println("Preset 5:   " + presetFM[4]);
        }
        else{
            System.out.println("Current Band:   " + currentBand);
            System.out.println("Current Frequency:   " + currentFreqAM);
            System.out.println("Current Volume:   " + volume);
            System.out.println("Preset 1:   " + presetAM[0]);
            System.out.println("Preset 2:   " + presetAM[1]);
            System.out.println("Preset 3:   " + presetAM[2]);
            System.out.println("Preset 4:   " + presetAM[3]);
            System.out.println("Preset 5:   " + presetAM[4]);
        }
            
    }
    
    //Select Preset
    public void SelectPreset(int presetChoice){
        if(currentBand.equals("FM")){
            currentFreqFM = presetFM[presetChoice - 1];
        }
        else{
            currentFreqAM = presetAM[presetChoice - 1];
        }
    }
    
    //Set Preset 
    public void SetPreset(int presetChoice){
        if(currentBand.equals("FM")){
            presetFM[presetChoice - 1] = currentFreqFM;
        }
        else{
            presetAM[presetChoice - 1] = currentFreqAM;
        }
    }
}
