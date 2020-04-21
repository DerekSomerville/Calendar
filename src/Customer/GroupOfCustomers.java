package AppDatabase;
import AppDatabase.AppDatabaseConnection;
import Customer.CustomerData;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class GroupOfCustomers {
    AppDatabase.AppDataSource dataSource = new AppDatabaseConnection();
    List<Customer.CustomerData> allCustomers = new ArrayList<CustomerData>();
    final String dataSourceName = "StudentTimeTable";
    final String[] dataSourceFields = {"StudentName","UniModule","dayOfWeek","date","month","year"};
    final int studentNamePosition = 0;
    final int uniModulePosition = 1;
    final int dayOfWeekPosition = 2;
    final int datePosition = 3;
    final int monthPosition = 4;
    final int yearPosition = 5;

    private void createAllCustomers(){
        List<List<String>> groupOfCustomers = this.dataSource.getDataFromTable(this.dataSourceName,this.dataSourceFields);
        for (List<String> individualCustomer: groupOfCustomers ) {
            Customer.CustomerData customer = new Customer.CustomerData(individualCustomer.get(this.studentNamePosition),
                    individualCustomer.get(this.uniModulePosition),
                    individualCustomer.get(this.dayOfWeekPosition),
                    Integer.parseInt(individualCustomer.get(this.datePosition)),
                    Integer.parseInt(individualCustomer.get(this.monthPosition)),
                    Integer.parseInt(individualCustomer.get(this.yearPosition)));
            this.allCustomers.add(customer);
        }
    }
    public List<Customer.CustomerData> getAllCustomers(){
        return this.allCustomers;
    }
    public void displayCustomers(){
        for (Customer.CustomerData eachRecord : allCustomers) {
            System.out.println(eachRecord);
        }
    }

    public static void main(String[ ] args) {
        GroupOfCustomers groupOfCustomers = new GroupOfCustomers();
        groupOfCustomers.createAllCustomers();
        groupOfCustomers.displayCustomers();

    }
}
