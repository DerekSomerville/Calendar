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

    private List<List<String>> getDataFromTable(){
        return databaseSource.getDataFromTable(tableName, customerFields);
    }

    private List<String> getDataFromTableWhere(String filter){
        return databaseSource.getDataFromTableWhere(tableName, customerFields,customerFields[0], filter );
    }

    public Customer getCustomer(String emailAddress){
        List<String> rawCustomer = getDataFromTableWhere(emailAddress);
        if (rawCustomer.size() > 0) {
            return createCustomer(rawCustomer);
        } else return null;
    }

    private Customer createCustomer(List<String> rawCustomer){
        return new Customer(rawCustomer.get(0),rawCustomer.get(1),rawCustomer.get(2),rawCustomer.get(3));
    }

    AllCustomers(){
        refresh();
    }
    public void refresh(){
        List<List<String>> customerData = getDataFromTable();
        int counter = 0;
        for (List<String> rawCustomer : customerData) {
            listOfCustomers[counter] = createCustomer(rawCustomer);
            counter += 1;
        }
    }

    public static void main(String[] args){
        AllCustomers allCustomers = new AllCustomers();
        allCustomers.setDatabaseSource(new DatabaseSource());
        System.out.println(allCustomers);
    }

    @Override
    public String toString() {
        return "AllCustomers{" +
                "listOfCustomers=" + Arrays.toString(listOfCustomers) +
                '}';
    }
}
