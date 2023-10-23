package DAO;

public class InternalPersonDAO extends PersonDAO {

    public InternalPersonDAO() {
        super("src/main/resources/internal_employees.xml");
    }
}
