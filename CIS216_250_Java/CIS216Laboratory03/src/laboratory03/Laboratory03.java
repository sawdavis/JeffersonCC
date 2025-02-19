package laboratory03;

import java.util.Scanner;
import java.io.*;

public class Laboratory03 {

    public static void main(String[] args) throws IOException {

        Scanner kbd = new Scanner(System.in);
        File priceFile;
        Scanner priceFileSC;

        String customerName, address, city, state, zipCode;
        char choice;
        double xLgBoxPrice, lgBoxPrice, mdBoxPrice, smBoxPrice, indBoxPrice,
                shovelPrice, xLgShipping, lgShipping, mdShipping, smShipping,
                indShipping, xLgProduct, lgProduct, mdProduct, smProduct,
                indProduct, shippingTotal, productTotal, totalCost, newXLgPrice,
                newLgPrice, newMdPrice, newSmPrice, newIndPrice, newShovelPrice;
        int numShovels, numXLBoxes, numLBoxes, numMBoxes, numSBoxes, numIBoxes;
        PrintWriter outPW;
        FileWriter outFW;

        //Get File Information
        priceFile = new File("prices.txt");
        priceFileSC = new Scanner(priceFile);
        priceFileSC.nextLine();
        xLgBoxPrice = priceFileSC.nextDouble();
        priceFileSC.nextLine();
        priceFileSC.nextLine();
        lgBoxPrice = priceFileSC.nextDouble();
        priceFileSC.nextLine();
        priceFileSC.nextLine();
        mdBoxPrice = priceFileSC.nextDouble();
        priceFileSC.nextLine();
        priceFileSC.nextLine();
        smBoxPrice = priceFileSC.nextDouble();
        priceFileSC.nextLine();
        priceFileSC.nextLine();
        indBoxPrice = priceFileSC.nextDouble();
        priceFileSC.nextLine();
        priceFileSC.nextLine();
        shovelPrice = priceFileSC.nextDouble();
        priceFileSC.close();

        //Get Customer Choice
        System.out.print("Do you want to change (P)rices or produce an"
                + " (I)nvoice? ");
        choice = kbd.next().charAt(0);
        kbd.nextLine();
        System.out.println();

        //Perfrom User Choice
        if (choice == 'I') {
            //Get Customer Information
            System.out.print("Customer: ");
            customerName = kbd.nextLine();
            System.out.print("Address: ");
            address = kbd.nextLine();
            System.out.print("City: ");
            city = kbd.nextLine();
            System.out.print("State: ");
            state = kbd.nextLine();
            System.out.print("ZipCode: ");
            zipCode = kbd.nextLine();

            //Get Number of Shovels
            System.out.print("Number of Shovels Ordered: ");
            numShovels = kbd.nextInt();

            //Determine number of Each Needed Box Size
            numXLBoxes = numShovels / 20;
            numShovels = numShovels - numXLBoxes * 20;
            numLBoxes = numShovels / 15;
            numShovels = numShovels - numLBoxes * 15;
            numMBoxes = numShovels / 10;
            numShovels = numShovels - numMBoxes * 10;
            numSBoxes = numShovels / 5;
            numIBoxes = numShovels - numSBoxes * 5;

            //Caclulate Costs
            xLgShipping = numXLBoxes * xLgBoxPrice;
            lgShipping = numLBoxes * lgBoxPrice;
            mdShipping = numMBoxes * mdBoxPrice;
            smShipping = numMBoxes * smBoxPrice;
            indShipping = numIBoxes * indBoxPrice;
            xLgProduct = numXLBoxes * 20 * shovelPrice;
            lgProduct = numLBoxes * 15 * shovelPrice;
            mdProduct = numMBoxes * 10 * shovelPrice;
            smProduct = numSBoxes * 5 * shovelPrice;
            indProduct = numIBoxes * 1 * shovelPrice;

            //Calculate Totals
            shippingTotal = xLgShipping + lgShipping + mdShipping + smShipping + 
                    indShipping;
            productTotal = xLgProduct + lgProduct + mdProduct + smProduct + 
                    indProduct;
            totalCost = shippingTotal + productTotal;
            
            //Display Invoice
            System.out.println();
            System.out.printf("%40s\n","Miracle Snow Shovels Unlimited");
            System.out.printf("%33s\n","West Nowhere, NH");
            System.out.println();
            System.out.printf("%33s\n","Shipping Invoice");
            System.out.println("CUSTOMER:");
            System.out.println(customerName);
            System.out.println(address);
            System.out.println(city + ", " + state + ' ' + zipCode);
            System.out.println();
            System.out.printf("%-25s%50s%20s\n","ITEMS SHIPPED","SHIPPING", "PRODUCT COST");
            if(numXLBoxes > 0)
                System.out.printf("%-2d%-23s%50.2f%20.2f\n", numXLBoxes, "Extra Large Case(s)", xLgShipping, xLgProduct);
            if(numLBoxes > 0)
                System.out.printf("%-2d%-23s%50.2f%20.2f\n", numLBoxes, "Large Case(s)", lgShipping, lgProduct);
            if(numMBoxes > 0)
                System.out.printf("%-2d%-23s%50.2f%20.2f\n", numMBoxes, "Medium Case(s)", mdShipping, mdProduct);
            if(numSBoxes > 0)
                System.out.printf("%-2d%-23s%50.2f%20.2f\n", numSBoxes, "Small Case(s)", smShipping, smProduct);
            if(numIBoxes > 0)
                System.out.printf("%-2d%-23s%50.2f%20.2f\n", numIBoxes, "Individual Case(s)", indShipping, indProduct);
            System.out.printf("%-25s%50.2f%20.2f\n","Totals:", shippingTotal, productTotal);
            System.out.printf("%-25s%70.2f\n","Total Bill:", totalCost);
            
        }

        if (choice == 'P') {

            //Get New Prices
            System.out.print("Enter the new x-large box price: ");
            newXLgPrice = kbd.nextDouble();
            System.out.print("Enter the new large box price: ");
            newLgPrice = kbd.nextDouble();
            System.out.print("Enter the new medium box price: ");
            newMdPrice = kbd.nextDouble();
            System.out.print("Enter the new small box price: ");
            newSmPrice = kbd.nextDouble();
            System.out.print("Enter the new individual box price: ");
            newIndPrice = kbd.nextDouble();
            System.out.print("Enter the new shovel price: ");
            newShovelPrice = kbd.nextDouble();
            
            //Check New Price Validity
            if(newXLgPrice <= 0)
                newXLgPrice = xLgBoxPrice;
            if(newLgPrice <= 0)
                newLgPrice = lgBoxPrice;
            if(newMdPrice <= 0)
                newMdPrice = mdBoxPrice;
            if(newSmPrice <= 0)
                newSmPrice = smBoxPrice;
            if(newIndPrice <= 0)
                newIndPrice = indBoxPrice;
            if(newShovelPrice <= 0)
                newShovelPrice = shovelPrice;
            
            //Update New Prices
            outFW = new FileWriter("prices.txt", false);
            outPW = new PrintWriter(outFW);
            outPW.println("XLarge Box Price");
            outPW.printf("%-1.2f\n", newXLgPrice);
            outPW.println("Large Box Price");
            outPW.printf("%-1.2f\n", newLgPrice);
            outPW.println("Medium Box Price");
            outPW.printf("%-1.2f\n", newMdPrice);
            outPW.println("Small Box Price");
            outPW.printf("%-1.2f\n", newSmPrice);
            outPW.println("Individual Box Price");
            outPW.printf("%-1.2f\n", newIndPrice);
            outPW.println("Shovel Price");
            outPW.printf("%-1.2f\n", newShovelPrice);
            outPW.close( );
            
            
        }

    }

}
