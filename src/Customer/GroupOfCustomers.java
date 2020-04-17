package AppDatabase;
import AppDatabase.AppDatabaseConnection;


import AppDatabase.AppDatabaseConnection;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class GroupOfCustomers {
    AppDatabase.AppDataSource dataSource = null;
    List<Customer.CustomerData> allCustomers;
    final String dataSourceName = "StudentTimeTable";
    final String[] dataSourceFields = {"StudentName","UniModule","dayOfWeek","date","month","year"};
    final int studentNamePosition = 0;
    final int uniModulePosition = 1;
    final int dayOfWeekPosition = 2;
    final int datePosition = 3;
    final int monthPosition = 4;
    final int yearPosition = 5;

    GroupOfCustomers(AppDatabase.AppDataSource dataSource){
        this.dataSource = dataSource;
    }

    private void createAllCustomers(){
        List<List<String>> groupOfCustomers = this.dataSource.getDataFromTable(this.dataSourceName,this.dataSourceFields);
        for (List<String> individualCustomer: groupOfCustomers ) {
            Customer.CustomerData customer = new Customer.CustomerData(individualCustomer.get(this.studentNamePosition),
                    individualCustomer.get(this.uniModulePosition),
                    individualCustomer.get(this.dayOfWeekPosition),
                    Integer.parseInt(individualCustomer.get(this.datePosition)),
                    Integer.parseInt(individualCustomer.get(this.monthPosition)),
                    Integer.parseInt(individualCustomer.get(this.yearPosition)));
        }
    }
    public List<Customer.CustomerData> getAllCustomers(){
        return this.allCustomers;
    }

    public static void main(String[ ] args) {
        GroupOfCustomers groupOfCustomers = new GroupOfCustomers(new AppDatabaseConnection());
    }
}