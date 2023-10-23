import DAO.ExternalPersonDAO;
import model.Person;

import org.junit.jupiter.api.Test;
import utils.DataInitializer;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InternalEmpTest {
    ExternalPersonDAO externalPersonDAO = new ExternalPersonDAO();
    DataInitializer init = new DataInitializer();

    @BeforeEach
    void setUp() {
        init.initializeData();
    }

    @Test
    void testAddNewPerson() {
        int initialSize = externalPersonDAO.getAllEmployees()
                                           .size();
        Person newPerson = new Person("7", "Johny", "Bravo", "333-123-4567", "Johny.Bravo@example.com", "55512345679");
        externalPersonDAO.create(newPerson);
        assertEquals(initialSize + 1, externalPersonDAO.getAllEmployees()
                                                       .size());
    }

    @Test
    void testRemovePerson() {
        int initialSize = externalPersonDAO.getAllEmployees()
                                           .size();
        externalPersonDAO.remove("2");
        assertEquals(initialSize - 1, externalPersonDAO.getAllEmployees()
                                                       .size());
    }

    @Test
    void testFindAndModifyPerson() {
        Person person = externalPersonDAO.find("John", "Doe", "123-456-7890");
        person.setLastName("Bravo");
        externalPersonDAO.modify(person);
        assertEquals(externalPersonDAO.find("John", "Bravo", "123-456-7890"), person);
    }
}
