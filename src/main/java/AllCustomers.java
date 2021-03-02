import java.util.Arrays;
import java.util.List;

public class AllCustomers {

    private Customer[] listOfCustomers = new Customer[5];
    private DataSource databaseSource = new DatabaseSource();

    public void setDatabaseSource(DataSource databaseSource) {
        this.databaseSource = databaseSource;
        refresh();
    }

    public Customer[] getListOfCustomers() {
        return listOfCustomers;
    }

    public static String tableName = "customer";
    public static String[] customerFields = {"emailAddress","firstName","lastName","password"};

    AllCustomers(){
        refresh();
    }
    public void refresh(){
        List<List<String>> customerData = databaseSource.getDataFromTable(tableName, customerFields);
        int counter = 0;
        for (List<String> rawCustomer : customerData) {
            listOfCustomers[counter] = new Customer(rawCustomer.get(0),rawCustomer.get(1),rawCustomer.get(2),rawCustomer.get(3));
            counter += 1;
        }
    }

    public static void main(String[] args){
        AllCustomers allCustomers = new AllCustomers();
        allCustomers.setDatabaseSource(new StubSource());
        System.out.println(allCustomers);
    }

    @Override
    public String toString() {
        return "AllCustomers{" +
                "listOfCustomers=" + Arrays.toString(listOfCustomers) +
                '}';
    }
}
