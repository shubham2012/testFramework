package test

import com.example.testFramework.Commons.ServiceImpl
import org.testng.Assert

class ServiceImplTest extends GroovyTestCase {

    ServiceImpl service = new ServiceImpl();
    void test_json_parser() {
        String str = "{ \"tool\": { \"jsonpath\": { \"creator\": { \"name\": \"Jayway Inc.\", \"location\": [ \"Malmo\", \"San Francisco\", \"Helsingborg\" ] } } }, \"book\": [ { \"title\": \"Beginning JSON\", \"price\": 49.99 }, { \"title\": \"JSON at Work\", \"price\": 29.99 } ] }";
        String element = service.getElement(str, "tool,jsonpath,creator,name");
        Assert.assertEquals("Jayway Inc.", element)
        System.out.println(element);
        List<String> path = service.getPath(str, "tool,jsonpath,creator,location");
        path.forEach(System.out.&println);
        List<String> book = service.getElementList(str, "book");
        System.out.println(book);
    }
}
