package utils;

import DAO.ExternalPersonDAO;
import DAO.InternalPersonDAO;
import model.Person;

import java.util.ArrayList;
import java.util.List;

public class DataInitializer {
    public void initializeData(){
        ExternalPersonDAO externalPersonDAO = new ExternalPersonDAO();
        InternalPersonDAO internalPersonDAO = new InternalPersonDAO();

        externalPersonDAO.saveToXml(createExternalEmployees());
        internalPersonDAO.saveToXml(createInternalEmployees());

    }

    private List<Person> createInternalEmployees() {
        List<Person> internalEmployees = new ArrayList<>();

        internalEmployees.add(new Person("4", "Susan", "Williams", "333-111-4444", "susan.williams@example.com", "33311144443"));
        internalEmployees.add(new Person("5", "Michael", "Smith", "222-555-7777", "michael.smith@example.com", "22255577776"));
        internalEmployees.add(new Person("6", "Laura", "Johnson", "444-888-9999", "laura.johnson@example.com", "44488899998"));

        return internalEmployees;
    }

    private List<Person> createExternalEmployees() {
        List<Person> externalEmployees = new ArrayList<>();

        externalEmployees.add(new Person("1", "John", "Doe", "123-456-7890", "john.doe@example.com", "12345678901"));
        externalEmployees.add(new Person("2", "Alice", "Smith", "987-654-3210", "alice.smith@example.com", "98765432109"));
        externalEmployees.add(new Person("3", "Bob", "Johnson", "555-123-4567", "bob.johnson@example.com", "55512345678"));

        return externalEmployees;
    }
}
