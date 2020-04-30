package test;

import com.example.testFramework.Commons.ServiceImpl;
import com.example.testFramework.Helper.CommonServiceHelper;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

@Slf4j
public class RestAssuredTests {

    CommonServiceHelper commonServiceHelper = new CommonServiceHelper();

    @Test
    public void testSimple() {
        Response response = commonServiceHelper.getResponse();
        Assert.assertEquals(response.getStatusCode(), 200);
        log.info(response.getBody().asString());
        List<String> args = ServiceImpl.getPath(response.getBody().asString(), "headers");
        System.out.println(args);
    }

}
