package DAO;


public class ExternalEmployeeDAO extends PersonDAO{

    public ExternalEmployeeDAO() {
        super("src/main/resources/external_employees.xml");
    }
}
