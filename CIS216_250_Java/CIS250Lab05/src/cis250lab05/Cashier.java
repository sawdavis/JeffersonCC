package cis250lab05;

public class Cashier {
    private char status;
    private Element element;
    
    public Cashier(){
        status = 'C';
    }
    
    public void SetElement(Element givenElement){
        element = givenElement;
    }
    
    public Element GetElement(){
        return element;
    }
    
    public char GetStatus(){
        return status;
    }
       
    public char BusyCashier(){
        if(status == 'O'){
            status = 'B';
            System.out.println("Cashier is now busy.");
        }
        else
            System.out.println("Cashier cannot be busy because it is closed.");
          
        return status;        
    }
    
    
    public char OpenCashier(){
        if(status == 'B'){
            status = 'O';
            System.out.println("Cashier is now opened.");
        }
        else
            System.out.println("Cashier cannot be opened up because it is not busy.");
          
        return status;        
    }
    
    public char ChangeStatus(){
        //Close
        if(status == 'O'){
            status = 'C';
            System.out.println("Cashier closed.");
        }
        else{
            if(status == 'C'){
            status = 'O';
            System.out.println("Cashier Opened.");
        }
        else
            System.out.println("Cashier busy and cannot be closed.");
        
        }
        
  
        
        return status;        
    }
}



