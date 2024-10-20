package com.thetestingacademy.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    //This class is used for Reading the data from Property files

    public static String readKey(String key){

//      Use Properties Legacy inbuilt class to fetch the properties file
        Properties properties = new Properties();

//     Fetch the properties file using File Input Stream which should be surrounded by try catch always

        try {
            FileInputStream file = new FileInputStream("C:\\Users\\Arshad\\IdeaProjects\\APIAutomationFramework7x\\src\\test\\resources\\data.properties");
            properties.load(file);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
//      key will be replaced by pur required value we provide and that value will fetched
        return properties.getProperty(key);
    }

//   Note : We are making key as String since most of the values we store in properties file will be STrig 90%
//    While using other datatype we can do Type conversions

}
