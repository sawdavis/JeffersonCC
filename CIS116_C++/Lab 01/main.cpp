
#include <iostream>

using namespace std;
/*
 Name:
 Due Date:
 Project Number:
 Project Description:
 */
int main(void) {

    double sidewalkLength;
    double sidewalkWidth;
    double sidewalkSqFt;
    double gravelDepth;
    double concreteDepth;
    double gravelPrice;
    double concretePrice;
    double contractor1SqFtPrice;
    double contractor2LaborPrice;
    double gravelVolume;
    double concreteVolume;
    double gravelCost;
    double concreteCost; 
    double contractor1Cost;
    double contractor2Cost;
    
    //Set Sidewalk Dimensions
    sidewalkLength = 109;
    sidewalkWidth = 5;
    gravelDepth = 0.5;
    concreteDepth =1.0 / 3.0;
    
    //Set Material Prices
    gravelPrice = 13.36;
    concretePrice = 81.02;
    
    //Set Contractor Price
    contractor1SqFtPrice = 8.54;
    contractor2LaborPrice = 4162.99;
    
    //Calculate Sidewalk Square Footage
    sidewalkSqFt = sidewalkLength * sidewalkWidth;
    
    //Calculate Volume of each Material Needed
    gravelVolume = (sidewalkSqFt * gravelDepth) / 27.0;
    concreteVolume = (sidewalkSqFt * concreteDepth) / 27.0;
    
    //Calculate Cost of Each Material Needed
    gravelCost = gravelVolume * gravelPrice;
    concreteCost = concreteVolume * concretePrice;
    
    //Calculate Contractor 1 Cost
    contractor1Cost = sidewalkSqFt * contractor1SqFtPrice;
    
    //Calculate Contractor 2 Cost
    contractor2Cost = contractor2LaborPrice + gravelCost + concreteCost;
    
    //Display Contractor Cost Summary
    cout << "Square Feet: " << sidewalkSqFt << endl;
    cout << "Contractor 1 Cost " << contractor1Cost << endl;
    cout << "Gravel Volume: " << gravelVolume << endl;
    cout << "Concrete Volume: " << concreteVolume << endl;
    cout << "Contractor 2 Cost: " << contractor2Cost << endl;
    return 0;
}

