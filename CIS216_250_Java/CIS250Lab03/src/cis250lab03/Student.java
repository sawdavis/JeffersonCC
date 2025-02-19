package cis250lab03;

public class Student {
    /* CONSTRAINTS of APPLICATION programmer:
        Must provide a class called Student that holds the application data.
        Must provide a method that performs a DEEP COPY named Clone.
        Must provide a method that gets a searchable String value named GetKey.
    */
    private String firstName, lastName;
    private int age;
    
    public String GetKey( ) {
        return firstName;
    }
    
    public Student Clone( ) {
        Student clonedItem;
        
        clonedItem = new Student( );
        clonedItem.SetAll(firstName, lastName, age);
        
        return clonedItem;
    }
    
    public void SetAll(String uFirstName, String uLastName, int uAge) {
        firstName = uFirstName;
        lastName = uLastName;
        age = uAge;
    }
    
    public String GetLastName( ) {
        return lastName;
    }
    
    public int GetAge( ) {
        return age;
    }
}
