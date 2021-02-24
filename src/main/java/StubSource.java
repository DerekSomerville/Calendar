import java.util.ArrayList;
import java.util.List;

public class StubSource implements  DataSource{
    @Override
    public List<List<String>> getDataFromTable(String tableName, String[] columnNames) {
        List<List<String>> queryData = new ArrayList<List<String>>();
        List<String> queryRow = new ArrayList<String>();

        for (int counter = 1 ; counter <4; counter++) {
            queryRow = new ArrayList<String>();
            queryRow.add("derek.somerville" + String.valueOf(counter)+ "@glasgow.ac.uk");
            queryRow.add("Derek");
            queryRow.add("Somerville" + String.valueOf(counter));
            queryRow.add(String.valueOf(counter));
            queryData.add(queryRow);
        }
        return queryData;
    }

}
