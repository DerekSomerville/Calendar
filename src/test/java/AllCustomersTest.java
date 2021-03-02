import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AllCustomersTest {

    @Test
    void refreshDatabase() {
        AllCustomers allCustomers = new AllCustomers();
        allCustomers.refresh();
        assertEquals("Matt",allCustomers.getListOfCustomers()[0].getFirstName());
    }

    @Test
    void refreshStub() {
        AllCustomers allCustomers = new AllCustomers();
        allCustomers.setDatabaseSource(new StubSource());
        allCustomers.refresh();
        assertEquals("Derek",allCustomers.getListOfCustomers()[0].getFirstName());
    }
}