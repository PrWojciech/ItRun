package DAO;

import model.Person;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO {

    private final String xmlFilePath;

    public PersonDAO(String xmlFilePath) {
        this.xmlFilePath = xmlFilePath;
    }

    public void saveToXml(List<Person> people) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            Element root = doc.createElement("people");
            doc.appendChild(root);

            for (Person person : people) {
                Element personElement = doc.createElement("person");
                root.appendChild(personElement);

                createElement(doc, personElement, "personId", person.getPersonId());
                createElement(doc, personElement, "firstName", person.getFirstName());
                createElement(doc, personElement, "lastName", person.getLastName());
                createElement(doc, personElement, "mobile", person.getMobile());
                createElement(doc, personElement, "email", person.getEmail());
                createElement(doc, personElement, "pesel", person.getPesel());
            }


            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(xmlFilePath));
            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createElement(Document doc, Element parentElement, String name, String value) {
        Element element = doc.createElement(name);
        element.appendChild(doc.createTextNode(value));
        parentElement.appendChild(element);
    }

    public List<Person> loadFromXml() {
        List<Person> people = new ArrayList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File(xmlFilePath));

            doc.getDocumentElement().normalize();

            Element root = doc.getDocumentElement();
            NodeList nodeList = root.getElementsByTagName("person");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String personId = element.getElementsByTagName("personId").item(0).getTextContent();
                    String firstName = element.getElementsByTagName("firstName").item(0).getTextContent();
                    String lastName = element.getElementsByTagName("lastName").item(0).getTextContent();
                    String mobile = element.getElementsByTagName("mobile").item(0).getTextContent();
                    String email = element.getElementsByTagName("email").item(0).getTextContent();
                    String pesel = element.getElementsByTagName("pesel").item(0).getTextContent();

                    Person person = new Person(personId, firstName, lastName, mobile, email, pesel);
                    people.add(person);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return people;
    }

    public List<Person> getAllEmployees(){
        return loadFromXml();
    }

    public Person find(String firstName, String lastName, String mobile) {
        List<Person> people = loadFromXml();
        for (Person person : people) {
            if (person.getFirstName().equals(firstName) &&
                    person.getLastName().equals(lastName) &&
                    person.getMobile().equals(mobile)) {
                return person;
            }
        }
        return null;
    }

    public void create(Person person) {
        List<Person> people = loadFromXml();
        people.add(person);
        saveToXml(people);
    }

    public boolean remove(String personId) {
        List<Person> people = loadFromXml();
        for (Person person : people) {
            if (person.getPersonId().equals(personId)) {
                people.remove(person);
                saveToXml(people);
                return true;
            }
        }
        return false;
    }

    public void modify(Person person) {
        List<Person> people = loadFromXml();
        for (int i = 0; i < people.size(); i++) {
            if (people.get(i).getPersonId().equals(person.getPersonId())) {
                people.set(i, person);
                saveToXml(people);
                break;
            }
        }
    }

}
