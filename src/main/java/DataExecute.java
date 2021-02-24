import java.sql.ResultSet;

public interface DataExecute {

    public ResultSet executeSelect(String selectQuery);
}
