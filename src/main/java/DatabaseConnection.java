import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static Connection createDatabaseConnection(String databaseUrlSuffix){
        Connection connection = null;
        try{
            connection = DriverManager.getConnection("jdbc:sqlite:" + databaseUrlSuffix);
        } catch (SQLException e) {
            throw new Error("Problem", e);
        }
        return connection;
    }
}
