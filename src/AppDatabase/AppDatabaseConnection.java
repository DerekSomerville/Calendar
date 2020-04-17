package AppDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class AppDatabaseConnection implements AppDatabase.AppDataSource {
    Connection databaseConnection = null;
    String databaseUrl = "jdbc:sqlite:C:\\LocalDev\\TSI\\Database\\chinook\\StudentTimeTable.db";

    AppDatabaseConnection(){
        this.databaseConnection = createDatabaseConnection();
    }

    private Connection createDatabaseConnection(){
        try {
            this.databaseConnection = DriverManager.getConnection(this.databaseUrl);
        } catch (SQLException e) {
            throw new Error("Problem", e);
        } return this.databaseConnection;
    }

    private String getQuery(String tableName){
        return "select * from " + tableName;
    }

    public List<List<String>> getDataFromTable(String tableName, String[] columnNames){
        List<List<String>> queryData = null;
        ArrayList<String> queryRow = null;
        try {
            Statement databaseStatement = this.databaseConnection.createStatement();
            ResultSet queryResultSet = databaseStatement.executeQuery(getQuery(tableName));
            while (queryResultSet.next()) {
                queryRow = null;
                for (String columnName:columnNames){
                    queryRow.add(queryResultSet.getString(columnName));
                }
                queryData.add(queryRow);
            }
        } catch (SQLException e) {
            throw new Error("Problem", e);
        }
        return queryData;
    }

}
