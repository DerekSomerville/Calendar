import java.sql.*;
import java.util.List;

public class DatabaseExecute implements DataExecute{

    private Connection connection;

    DatabaseExecute(){
        connection = getConnection();
    }

    private Connection getConnection(){
        String databaseUrlSuffix = "src/main/resources/applicationDB.db";
        Connection connection = DatabaseConnection.createDatabaseConnection(databaseUrlSuffix);
        return connection;
    }

    public ResultSet executeSelect(String selectQuery){
        ResultSet resultSet = null;
        try  {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(selectQuery);
        } catch (SQLException e) {
            throw new Error("Problem", e);
        }
        return resultSet;
    }

    public void executeSQLCommand(String sqlCommand) {
        //this.errorLogging.writeToLog("DBExecuteSQL.executeSQLCommand", "To attempt:" + sqlCommand);
        try {
            Statement statement = this.getConnection().createStatement();
            statement.execute(sqlCommand);
        } catch (Exception sqlExp) {
            System.out.println("DBExecuteSQL.executeSQLCommand: " + "An error occurred{" + sqlExp.getMessage());
            System.out.println("DBExecuteSQL.executeSQLCommand: " + "With command{" + sqlCommand);
        }
    }

    private PreparedStatement prepareSqlStatementFromList(String sqlCommand, List<String[]> dataRows) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = this.connection.prepareStatement(sqlCommand);
            for (String[] fieldValues : dataRows) {
                prepareSqlStatement(preparedStatement, fieldValues);
            }
            preparedStatement.executeUpdate();
        } catch (Exception sqlExp) {
            System.out.println("DBExecuteSQL.prepareSqlStatementFromList: " + "An error occurred:" + sqlExp.getMessage());
            System.out.println("DBExecuteSQL.prepareSqlStatementFromList: " + "With command:" + sqlCommand);
        }
        return preparedStatement;
    }

    private String duplicateSqlCommandForList(String sqlCommand, int sizeOfList) {
        String newSqlCommand = "";
        for (int counter = 1; counter < sizeOfList; counter++) {
            newSqlCommand += sqlCommand;
        }
        return newSqlCommand;
    }

    private PreparedStatement prepareSqlStatement(PreparedStatement preparedStatement, String[] fieldValues) {
        int counter = 1;
        int numberOfFields = fieldValues.length;
        try {
            for (String fieldValue : fieldValues) {
                preparedStatement.setString(counter, fieldValue);
                counter += 1;
            }
        } catch (Exception sqlExp) {
            System.out.println("DBExecuteSQL.prepareSqlStatement: " + "An error occurred:" + sqlExp.getMessage());
        }
        return preparedStatement;
    }

    public void insertData(String sqlCommand, List<String[]> dataRows) {
        String duplicatedSQLCommand = this.duplicateSqlCommandForList(sqlCommand, dataRows.size());
        try {
            this.prepareSqlStatementFromList(sqlCommand, dataRows);
        } catch (Exception sqlExp) {
            System.out.println("DBExecuteSQL.insertData: " + "An error occurred:" + sqlExp.getMessage());
            throw sqlExp;
        }
    }

    public String generateInsertStatement(String tableName, String[] fieldNames){
        int counter = 0;
        String sqlColumns = "";
        String sqlValues = "";
        String sqlUniqueColumn = "";
        String sqlInsert = "";
        for (String columnName : fieldNames){
            if (counter == 0 ){
                sqlUniqueColumn = columnName;
            } else {
                sqlColumns += ", ";
                sqlValues += ", ";
            }
            sqlColumns += columnName;
            sqlValues += "?";
            counter += 1;
        }
        sqlInsert = "INSERT INTO " + tableName + "(" + sqlColumns + ") VALUES(" + sqlValues + "); \n";
        return sqlInsert;
    }

    private String generateDropTable(String tableName){
        return "DROP TABLE IF EXISTS " + tableName;
    }

    public void dropTable(String tableName, String[] fieldNames){
        String sqlDropTable = this.generateDropTable(tableName);
        try {
            this.executeSQLCommand(sqlDropTable);
        } catch (Exception sqlExp) {
            System.out.println("DBSetup.dropTable: " + sqlExp.getMessage());
        }
    }
    public String generateCreateTableStatement(String tableName, String[] fieldNames){
        String sqlCreateTable =  "CREATE TABLE IF NOT EXISTS " + tableName + "(\n";
        int counter = 0;
        boolean fieldUnique = false;
        for (String columnName : fieldNames){
            if (counter == 0){
                fieldUnique = true;
            } else {
                sqlCreateTable += ",";
            }
            sqlCreateTable += columnName + " TEXT NOT NULL ";
            if (fieldUnique) {
                sqlCreateTable += " UNIQUE ";
            }
            counter += 1;
            fieldUnique = false;
        }
        sqlCreateTable += ");";
        return sqlCreateTable;
    }

    public void createTable(String tableName, String[] fieldNames){
        String sqlCreateTable = this.generateCreateTableStatement(tableName,fieldNames);
        try {
            this.executeSQLCommand(sqlCreateTable);
        } catch (Exception sqlExp) {
            System.out.println("DBSetup.createTable: " + sqlExp.getMessage());
        }
    }
}
