package labratory01;

import java.util.Scanner;

public class Labratory01 { //start of class definition

    public static void main(String[] args) {
        Scanner kbd = new Scanner(System.in);

        int length, width, gravelDepth, concreteDepth, sidewalkArea,
                gravelVolume, concreteVolume;
        double gravelPrice, concretePrice, laborCost, sqFtPrice, gravelCost,
                concreteCost, contractor1Cost, contractor2Cost;

        //Get Sidewalk Dimensions
        System.out.print("Enter the length of the sidewalk (in feet): ");
        length = kbd.nextInt();
        System.out.print("Enter the width of the sidewalk (in feet): ");
        width = kbd.nextInt();
        System.out.print("Enter the depth of the gravel (in inches): ");
        gravelDepth = kbd.nextInt();
        System.out.print("Enter the depth of the concrete (in inches): ");
        concreteDepth = kbd.nextInt();

        //Get Material Prices
        System.out.print("Enter the price of a cubic yard of gravel: ");
        gravelPrice = kbd.nextDouble();
        System.out.print("Enter the price of a cubic yard of concrete: ");
        concretePrice = kbd.nextDouble();

        //Get Labor Cost
        System.out.print("Enter the labor cost for contractor 2: ");
        laborCost = kbd.nextDouble();

        //Get Price per Square Foot
        System.out.print("Enter the price per square foot for Conractor 1: ");
        sqFtPrice = kbd.nextDouble();
        System.out.println();

        //Calculate Sidewalk Area
        sidewalkArea = (int) (width * length);

        //Calculate Volume of Each Material
        gravelVolume = (int) (sidewalkArea * gravelDepth / 12.0 / 27.0 + 0.999);
        concreteVolume = (int) (sidewalkArea * concreteDepth / 12.0 / 27.0 + 0.999);

        //Calculate Material Costs
        gravelCost = gravelVolume * gravelPrice;
        concreteCost = concreteVolume * concretePrice;

        //Calculate Contractor 1 Cost
        contractor1Cost = sqFtPrice * sidewalkArea;

        //Calculate Contractor 2 Cost
        contractor2Cost = laborCost + gravelCost + concreteCost;

        //Display Contractor Cost Summary
        System.out.println("Contractor 1");
        System.out.println("Square Footage: " + sidewalkArea + " Square Feet");
        System.out.printf("Cost: $%1.2f\n", contractor1Cost);
        System.out.println();

        System.out.println("Contractor 2");
        System.out.println("Concrete Volume: " + concreteVolume + " Cubic Feet");
        System.out.println("Gravel Volume: " + gravelVolume + " Cubic Feet");
        System.out.printf("Concrete Cost: $%1.2f\n", concreteCost);
        System.out.printf("Gravel Cost: $%1.2f\n", gravelCost);
        System.out.printf("Cost: $%1.2f\n", contractor2Cost);

    }//end of main

}//end of class definition
