package org.automation.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {

    public PropertiesReader() {

    }

    public static String readKey(String key) throws Exception {

        FileInputStream fileInputStream;
        fileInputStream = new FileInputStream(new File(System.getProperty("user.dir")+"/src/main/java/org/automation/resources/TestDataProperties.properties"));
        Properties p = new Properties();
        p.load(fileInputStream);

        if (p.getProperty(key)==null){
            throw new Exception("Not able to find the Key");
        }else {
            return p.getProperty(key);
        }

    }
}
