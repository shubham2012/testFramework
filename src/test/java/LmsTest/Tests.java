package LmsTest;

import com.example.testFramework.Helper.PostmanService;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

@Slf4j
public class Tests {

    PostmanService postmanService = new PostmanService();
    @Test
    public void testSimple() {
        String response = postmanService.getResponse();
        log.info(response);
    }

}
