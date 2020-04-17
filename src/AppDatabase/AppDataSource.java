package AppDatabase;
import java.util.ArrayList;
import java.util.List;

public interface AppDataSource {
    public List<List<String>> getDataFromTable (String tableName, String[] columnNames);
}
