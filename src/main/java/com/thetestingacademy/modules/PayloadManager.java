package com.thetestingacademy.modules;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.thetestingacademy.pojos.*;

public class PayloadManager {

    //Here we manage/edit the payload from Pojo cLass as required for request
    // Using Serialization we can convert Json Object to String
    // Here we can also have different version of the same Payload

    Gson gson;

    public String PayloadForCreateBookingAsString(){

        Booking booking = new Booking();
        booking.setFirstname("Mohamed");
        booking.setLastname("Arshad");
        booking.setTotalprice(111);
        booking.setDepositpaid(true);

//  Setting the Inner object in JSON Payload
        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2024-10-15");
        bookingdates.setCheckout("2024-10-25");

//     Setting the Booking dates object inBooking
        booking.setBookingdates(bookingdates);

//     setting the final Booking attribute
        booking.setAdditionalneeds("Breakfast");

//    System.out.println(booking);
//    System.out.println(booking.getBookingdates());
//  Above both will print the object in 2 string type

//  Serialization - To convert the JAVA object to String for server we use GSON
         gson = new Gson();
        String JsonStringPayload = gson.toJson(booking);

        return JsonStringPayload;
    }

    public String PayloadForCreateBookingAsStringUsingFaker(){

        Faker faker = new Faker();

//      Instead of Hardcoded values we  are using Faker to set values
        Booking booking = new Booking();
        booking.setFirstname(faker.name().firstName());
        booking.setLastname(faker.name().lastName());
        booking.setTotalprice(faker.random().nextInt(1000));
        booking.setDepositpaid(true);

//  Setting the Inner object in JSON Payload
        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2024-10-15");
        bookingdates.setCheckout("2024-10-25");

//     Setting the Booking dates object inBooking
        booking.setBookingdates(bookingdates);

//     setting the final Booking attribute
        booking.setAdditionalneeds("Breakfast");

//    System.out.println(booking);
//    System.out.println(booking.getBookingdates());
//  Above both will print the object in 2 string type

//  Serialization - To convert the JAVA object to String for server we use GSON
        Gson gson = new Gson();
        String JsonStringPayload = gson.toJson(booking);

        return JsonStringPayload;
    }

    public BookingResponse bookingResponse(String responseString){
        //DeSerialization - Converting the String back to JSON Object
        //Below code will convert the response which received as a String to object and map it with Booking Response class created
        gson = new Gson();
        BookingResponse bookingResponse = gson.fromJson(responseString, BookingResponse.class);
        return bookingResponse;
    }

    public String PayloadForCreateToken(){

        AuthToken auth = new AuthToken();
        auth.setUsername("admin");
        auth.setPassword("password123");

        //  Serialization - To convert the JAVA object to String for server we use GSON
        gson = new Gson();
        String AuthTokenPayload = gson.toJson(auth);
        return AuthTokenPayload;
    }


    public String tokenResponse(String responseString){
        //DeSerialization - Converting the String back to JSON Object
        gson = new Gson();
        AuthTokenResponse tokenResponse = gson.fromJson(responseString, AuthTokenResponse.class);

//        We can fetch the token and return the same instead of sending object to base est and fetching there - this will avoid duplicacy in future
        return tokenResponse.getToken();
    }

    public Booking getBookingResponse(String responseString){
        gson = new Gson();
        Booking booking = gson.fromJson(responseString, Booking.class);
        return booking;
    }

    public String PayloadforUpdateBookignAsString(){

        Booking booking = new Booking();
        booking.setFirstname("Reema");
        booking.setLastname("Farhana");
        booking.setTotalprice(111);
        booking.setDepositpaid(true);

        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2024-10-15");
        bookingdates.setCheckout("2024-10-25");

        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Breakfast");

        gson = new Gson();
        return gson.toJson(booking);

    }


}
