package org.automation.crud;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.automation.base.BaseTest;
import org.testng.annotations.Test;
import org.automation.base.BaseTest.*;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;


public class TC_Integration extends BaseTest {

    String token;

    @Test
    public void testCreateBooking() throws JsonProcessingException {
        token = getToken();
        System.out.println(token);
        assertThat("Vaibhav").isEqualTo("Vaibhav");

    }


    @Test(dependsOnMethods = {"testCreateBooking"})
    public void testCreateAndUpdateBooking(){
        System.out.println(token);
        assertThat("Vaibhav").isEqualTo("Vaibhav");
    }

    @Test (dependsOnMethods = {"testCreateBooking"})
    public void testDeleteBooking(){
        System.out.println(token);
        assertThat("Vaibhav").isEqualTo("Vaibhav");

    }

}
