package org.automation.modules;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.automation.payloads.request.Auth;
import org.automation.payloads.request.Booking;
import org.automation.payloads.request.Bookingdates;
import org.automation.payloads.response.BookingResponse;

public class PayloadManager {

    ObjectMapper objectMapper;

    public String createPayload() throws JsonProcessingException {
        objectMapper = new ObjectMapper();

        Booking booking = new Booking();
        booking.setFirstname("Vaibhav");
        booking.setLastname("More");
        booking.setAdditionalneeds("Lunch");
        booking.setDepositpaid(true);
        booking.setTotalprice(200);

        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2018-01-01");
        bookingdates.setCheckout("2018-01-02");

        booking.setBookingdates(bookingdates);

        String payload = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(booking);
        return payload;
    }

    public BookingResponse JsonToObject(String jsonString) throws JsonProcessingException {
        objectMapper = new ObjectMapper();
        BookingResponse bookingResponse = objectMapper.readValue(jsonString,BookingResponse.class);
        return bookingResponse;

    }

    public String setToken() throws JsonProcessingException {
        objectMapper = new ObjectMapper();
        Auth auth = new Auth();
        auth.setUsername("admin");
        auth.setPassword("password123");
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(auth);
    }

}
