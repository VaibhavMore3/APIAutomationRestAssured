package org.automation.DDT;

import org.automation.utils.ExcelUtils;
import org.testng.annotations.Test;

public class LogIn {

    @Test (dataProvider = "getData",dataProviderClass = ExcelUtils.class)
    public void testLogIn(String username, String password){

        System.out.println("Username: "+ username);
        System.out.println("Password: "+ password);
    }
}
