import DAO.ExternalEmployeeDAO;
import model.Person;

import org.junit.jupiter.api.Test;
import utils.DataInitializer;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InternalEmpTest {
    ExternalEmployeeDAO externalEmployeeDAO = new ExternalEmployeeDAO();
    DataInitializer init = new DataInitializer();

    @BeforeEach
    void setUp() {
        init.initializeData();
    }

    @Test
    void testAddNewPerson() {
        int initialSize = externalEmployeeDAO.getAllEmployees()
                                             .size();
        Person newPerson = new Person("7", "Johny", "Bravo", "333-123-4567", "Johny.Bravo@example.com", "55512345679");
        externalEmployeeDAO.create(newPerson);
        assertEquals(initialSize + 1, externalEmployeeDAO.getAllEmployees()
                                                         .size());
    }

    @Test
    void testRemovePerson() {
        int initialSize = externalEmployeeDAO.getAllEmployees()
                                             .size();
        externalEmployeeDAO.remove("2");
        assertEquals(initialSize - 1, externalEmployeeDAO.getAllEmployees()
                                                         .size());
    }

    @Test
    void testFindAndModifyPerson() {
        Person person = externalEmployeeDAO.find("John", "Doe", "123-456-7890");
        person.setLastName("Bravo");
        externalEmployeeDAO.modify(person);
        assertEquals(externalEmployeeDAO.find("John", "Bravo", "123-456-7890"), person);
    }
}
