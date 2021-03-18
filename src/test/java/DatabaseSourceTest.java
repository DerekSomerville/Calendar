import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseSourceTest {

    private DatabaseSource databaseSource = new DatabaseSource();

    @org.junit.jupiter.api.Test
    void getDataFromTableSize() {
        List<List<String>> queryData = databaseSource.getDataFromTable(AllCustomers.tableName, AllCustomers.customerFields);
        assertEquals(1,queryData.size());
    }

    @org.junit.jupiter.api.Test
    void getDataFromTableName() {
        List<List<String>> queryData = databaseSource.getDataFromTable(AllCustomers.tableName, AllCustomers.customerFields);
        assertEquals("Matt",queryData.get(0).get(1));
    }

    @org.junit.jupiter.api.Test
    void getDataFromTableNameStub() {
        databaseSource.setDataExecute(new StubExecute());
        List<List<String>> queryData = databaseSource.getDataFromTable(AllCustomers.tableName, AllCustomers.customerFields);
        assertEquals("derek.somerville1@glasgow.ac.uk",queryData.get(0).get(1));
    }

    @Test
    void getSelectQuery(){
        assertEquals("Select * from customer",databaseSource.getSelectQuery(AllCustomers.tableName) );
    }

    @Test
    void getSelectQueryTank(){
        assertTrue(true);
        assertEquals("Select * from tank",databaseSource.getSelectQuery("tank") );
    }

    @Test
    void getSelectQueryWhere(){
        assertEquals("Select * from customer where emailAddress = \"derek.somerville@glasgow.ac.uk\"",databaseSource.getSelectQueryWhere(AllCustomers.tableName,"emailAddress","derek.somerville@glasgow.ac.uk"));
    }
}