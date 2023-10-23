package utils;

import DAO.ExternalEmployeeDAO;
import DAO.InternalEmployeeDAO;
import model.Person;

import java.util.ArrayList;
import java.util.List;

public class DataInitializer {
    public void initializeData(){
        ExternalEmployeeDAO externalPersonDAO = new ExternalEmployeeDAO();
        InternalEmployeeDAO internalPersonDAO = new InternalEmployeeDAO();

        externalPersonDAO.saveToXml(createExternalEmployees());
        internalPersonDAO.saveToXml(createInternalEmployees());

    }

    private List<Person> createInternalEmployees() {
        List<Person> internalEmployees = new ArrayList<>();

        internalEmployees.add(new Person("4", "Connor", "McGregor", "333-111-4444", "connor.mcgregor@example.com", "33311144443"));
        internalEmployees.add(new Person("5", "Michael", "Smith", "222-555-7777", "michael.smith@example.com", "22255577776"));
        internalEmployees.add(new Person("6", "Micky", "Johnson", "444-888-9999", "micky.johnson@example.com", "44488899998"));

        return internalEmployees;
    }

    private List<Person> createExternalEmployees() {
        List<Person> externalEmployees = new ArrayList<>();

        externalEmployees.add(new Person("1", "John", "Doe", "123-456-7890", "john.doe@example.com", "12345678901"));
        externalEmployees.add(new Person("2", "Jon", "Jones", "987-654-3210", "jon.jones@example.com", "98765432109"));
        externalEmployees.add(new Person("3", "Bob", "Sapp", "555-123-4567", "bob.sapp@example.com", "55512345678"));

        return externalEmployees;
    }
}
