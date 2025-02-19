package laboratory2;

import java.util.Scanner;
import java.io.*;

public class Laboratory2 {

    public static void main(String[] args) throws IOException {
        Scanner kbd = new Scanner(System.in);
        File droneFile;
        Scanner droneFileSC;

        String customerName, streetAddress, cityStateZip, portName1, portName2,
                portName3, portName4, portName5, portName6, portName7;
        double deliveryX, deliveryY, foodWeight, portX1, portX2, portX3, portX4,
                portX5, portX6, portX7, portY1, portY2, portY3, portY4, portY5,
                portY6, portY7, speed1, speed2, speed3, speed4, speed5, speed6,
                speed7, range1, range2, range3, range4, range5, range6, range7,
                payload1, payload2, payload3, payload4, payload5, payload6,
                payload7, distance1, distance2, distance3, distance4, distance5,
                distance6, distance7, eTA1, eTA2, eTA3, eTA4, eTA5, eTA6, eTA7;

        //Get Customer Information
        System.out.print("Enter Customer Name: ");
        customerName = kbd.nextLine();
        System.out.print("Enter Street Address: ");
        streetAddress = kbd.nextLine();
        System.out.print("Enter City, State, and Zip: ");
        cityStateZip = kbd.nextLine();
        System.out.print("Enter Delivery 'X' Coordinate: ");
        deliveryX = kbd.nextDouble();
        System.out.print("Enter Delivery 'Y' Coordinate: ");
        deliveryY = kbd.nextDouble();
        System.out.print("Enter the weight of the payload: ");
        foodWeight = kbd.nextDouble();
        System.out.println();

        //Get Drone Information from File
        droneFile = new File("drones.txt");
        droneFileSC = new Scanner(droneFile);
        portName1 = droneFileSC.nextLine();
        portX1 = droneFileSC.nextDouble();
        portY1 = droneFileSC.nextDouble();
        speed1 = droneFileSC.nextDouble();
        range1 = droneFileSC.nextDouble();
        payload1 = droneFileSC.nextDouble();
        droneFileSC.nextLine();
        portName2 = droneFileSC.nextLine();
        portX2 = droneFileSC.nextDouble();
        portY2 = droneFileSC.nextDouble();
        speed2 = droneFileSC.nextDouble();
        range2 = droneFileSC.nextDouble();
        payload2 = droneFileSC.nextDouble();
        droneFileSC.nextLine();
        portName3 = droneFileSC.nextLine();
        portX3 = droneFileSC.nextDouble();
        portY3 = droneFileSC.nextDouble();
        speed3 = droneFileSC.nextDouble();
        range3 = droneFileSC.nextDouble();
        payload3 = droneFileSC.nextDouble();
        droneFileSC.nextLine();
        portName4 = droneFileSC.nextLine();
        portX4 = droneFileSC.nextDouble();
        portY4 = droneFileSC.nextDouble();
        speed4 = droneFileSC.nextDouble();
        range4 = droneFileSC.nextDouble();
        payload4 = droneFileSC.nextDouble();
        droneFileSC.nextLine();
        portName5 = droneFileSC.nextLine();
        portX5 = droneFileSC.nextDouble();
        portY5 = droneFileSC.nextDouble();
        speed5 = droneFileSC.nextDouble();
        range5 = droneFileSC.nextDouble();
        payload5 = droneFileSC.nextDouble();
        droneFileSC.nextLine();
        portName6 = droneFileSC.nextLine();
        portX6 = droneFileSC.nextDouble();
        portY6 = droneFileSC.nextDouble();
        speed6 = droneFileSC.nextDouble();
        range6 = droneFileSC.nextDouble();
        payload6 = droneFileSC.nextDouble();
        droneFileSC.nextLine();
        portName7 = droneFileSC.nextLine();
        portX7 = droneFileSC.nextDouble();
        portY7 = droneFileSC.nextDouble();
        speed7 = droneFileSC.nextDouble();
        range7 = droneFileSC.nextDouble();
        payload7 = droneFileSC.nextDouble();
        droneFileSC.nextLine();
        droneFileSC.close();

        //Determine Distances to Delivery
        distance1 = Math.sqrt(Math.pow(deliveryX - portX1, 2) + Math.pow(deliveryY - portY1, 2) * 2.6163);
        distance2 = Math.sqrt(Math.pow(deliveryX - portX2, 2) + Math.pow(deliveryY - portY2, 2) * 2.6163);
        distance3 = Math.sqrt(Math.pow(deliveryX - portX3, 2) + Math.pow(deliveryY - portY3, 2) * 2.6163);
        distance4 = Math.sqrt(Math.pow(deliveryX - portX4, 2) + Math.pow(deliveryY - portY4, 2) * 2.6163);
        distance5 = Math.sqrt(Math.pow(deliveryX - portX5, 2) + Math.pow(deliveryY - portY5, 2) * 2.6163);
        distance6 = Math.sqrt(Math.pow(deliveryX - portX6, 2) + Math.pow(deliveryY - portY6, 2) * 2.6163);
        distance7 = Math.sqrt(Math.pow(deliveryX - portX7, 2) + Math.pow(deliveryY - portY7, 2) * 2.6163);

        //Determine ETAs for Delivery
        eTA1 = distance1 / speed1;
        eTA2 = distance2 / speed2;
        eTA3 = distance3 / speed3;
        eTA4 = distance4 / speed4;
        eTA5 = distance5 / speed5;
        eTA6 = distance6 / speed6;
        eTA7 = distance7 / speed7;

        //Display Drone Travel Summary
        System.out.println("Customer: " + customerName);
        System.out.println(streetAddress);
        System.out.println(cityStateZip);
        System.out.println("Payload weight: " + foodWeight);
        System.out.println("Delivery coordiantes: " + deliveryX + ", " + deliveryY);
        System.out.printf("%-15s%15s%15s%15s%15s%15s%15s%15s\n", "Drone Port", "Port Name", "Coordinates", "Drone Speed", "Max Range", "Payload", "Distance", "Travel Time");
        System.out.printf("%-15s%15s%10.1f%1s%1.1f%15.0f%15.0f%15.1f%15.3f%15.3f\n", "1", portName1, portX1, ",", portY1, speed1, range1, payload1, distance1, eTA1);
        System.out.printf("%-15s%15s%10.1f%1s%1.1f%15.0f%15.0f%15.1f%15.3f%15.3f\n", "2", portName2, portX2, ",", portY2, speed2, range2, payload2, distance2, eTA2);
        System.out.printf("%-15s%15s%10.1f%1s%1.1f%15.0f%15.0f%15.1f%15.3f%15.3f\n", "3", portName3, portX3, ",", portY3, speed3, range3, payload3, distance3, eTA3);
        System.out.printf("%-15s%15s%10.1f%1s%1.1f%15.0f%15.0f%15.1f%15.3f%15.3f\n", "4", portName4, portX4, ",", portY4, speed4, range4, payload4, distance4, eTA4);
        System.out.printf("%-15s%15s%10.1f%1s%1.1f%15.0f%15.0f%15.1f%15.3f%15.3f\n", "5", portName5, portX5, ",", portY5, speed5, range5, payload5, distance5, eTA5);
        System.out.printf("%-15s%15s%10.1f%1s%1.1f%15.0f%15.0f%15.1f%15.3f%15.3f\n", "6", portName6, portX6, ",", portY6, speed6, range6, payload6, distance6, eTA6);
        System.out.printf("%-15s%15s%10.1f%1s%1.1f%15.0f%15.0f%15.1f%15.3f%15.3f\n", "7", portName7, portX7, ",", portY7, speed7, range7, payload7, distance7, eTA7);

    }

}
