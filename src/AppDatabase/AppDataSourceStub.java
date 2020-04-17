package AppDatabase;

import java.util.List;

public class AppDataSourceStub implements AppDataSource {

    public List<List<String>> getDataFromTable(String tableName, String[] columnNames) {
        List<List<String>> queryData = null;
        List<String> queryRow = null;
        queryRow.add("Derek Somerville");
        queryRow.add("Testing and Software Improvements");
        queryRow.add("1");
        queryRow.add("1");
        queryRow.add("1");
        queryRow.add("2020");
        for (int counter = 1 ; counter <28; counter++) {
            queryRow.set(2, String.valueOf(counter));
            queryRow.set(3, String.valueOf(counter));
            queryData.add(queryRow);
        }
        return queryData;
    }
}
