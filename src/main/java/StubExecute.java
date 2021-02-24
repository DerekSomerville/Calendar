import java.sql.ResultSet;
import java.util.List;

public class StubExecute implements DataExecute{

    @Override
    public ResultSet executeSelect(String selectQuery) {
        StubSource stubSource = new StubSource();
        List<List<String>> queryData = stubSource.getDataFromTable(AllCustomers.tableName,AllCustomers.customerFields);
        return new StubResultSet(queryData,AllCustomers.customerFields);
    }
}
