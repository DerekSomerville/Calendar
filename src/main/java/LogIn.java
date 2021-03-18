import java.util.Scanner;

public class LogIn {

    private AllCustomers allCustomers = new AllCustomers();

    private String getInput(){
        Scanner userInput = new Scanner(System.in);
        String result = userInput.nextLine();
        return result;
    }

    private String askForInformation(String field){
        System.out.println("Please enter your " + field);
        return getInput();
    }

    private Customer createNewCustomer(String emailAddress){
        String firstName = askForInformation("first name");
        String lastName = askForInformation("last name");
        String password = askForInformation("password");
        return new Customer(emailAddress,firstName,lastName,password);
    }

    public Customer logIn(){
        String emailAddress = askForInformation("email address");
        Customer customer = allCustomers.getCustomer(emailAddress);
        if (customer == null) {
            customer = createNewCustomer(emailAddress);
        } else {
            String password = askForInformation("password");
            int counter = 0;
            while (password.equals(customer.getPassword()) && counter < 3){
                System.out.println(customer.getPassword());
                password = askForInformation("password");
            }
            System.out.println("You have logged on");
        }
        return null;
    }

    public static void main (String[] args){
        AllCustomers allCustomers = new AllCustomers();
        System.out.println(allCustomers);
        LogIn login = new LogIn();
        login.logIn();
    }
}
