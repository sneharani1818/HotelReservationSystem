import model.*;
public class Driver {
    public static void main(String[] args) {
        //following two statements were just for testing Customer class
        Customer customer=new Customer("first", "second", "j@domain.com");
        System.out.println(customer);
        //testing for invalid email address and exception must be generated
        Customer customer1= new Customer("firstName", "lastName","email");
        System.out.println(customer1);
    }
}
