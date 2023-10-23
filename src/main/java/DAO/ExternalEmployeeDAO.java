package DAO;


public class ExternalPersonDAO extends PersonDAO{

    public ExternalPersonDAO() {
        super("src/main/resources/external_employees.xml");
    }
}
