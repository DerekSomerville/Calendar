import java.util.List;

public interface DataSource {
    public List<List<String>> getDataFromTable (String tableName, String[] columnNames);
    public List<String> getDataFromTableWhere (String tableName, String[] columnNames,String whereField, String filter);
}
