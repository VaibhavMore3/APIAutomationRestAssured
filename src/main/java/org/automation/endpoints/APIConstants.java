package org.automation.endpoints;

import org.automation.utils.PropertiesReader;

public class APIConstants {

//    public static String baseURL = FillowUtils.fetchDataFromXLSX("Sheet1", "BaseURL","Value");

    public static String baseURL;
    public static String authPath;
    public static String CREATE_BOOKING;
    public static String updateBooking;

    static {
        try {
            baseURL = PropertiesReader.readKey("baseURL");
            authPath = PropertiesReader.readKey("authPath");
            CREATE_BOOKING = PropertiesReader.readKey("CREATE_BOOKING");
            updateBooking = PropertiesReader.readKey("updateBooking");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}


