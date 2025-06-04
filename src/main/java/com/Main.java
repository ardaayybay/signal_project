package com;

import java.io.IOException;
import java.util.Arrays;

import com.cardio_generator.HealthDataSimulator;
import com.data_management.DataStorage;
/*
 * running commands:
 * mvn clean package
 * java -jar target\cardio_generator-1.0-SNAPSHOT.jar DataStorage
 * below will run the data Storage class
 * java -jar target\cardio_generator-1.0-SNAPSHOT.jar --patient-count 10 --output (strategy)  
 * below will run the HealthDataSimulator class
 */
public class Main {
    public static void main(String[] args) {
    System.out.println("Main started with args: " + Arrays.toString(args));

    try {
        if (args.length > 0 && args[0].equals("DataStorage")) {
            System.out.println("Running DataStorage...");
            DataStorage.main(new String[]{});
        } else {
            System.out.println("Running HealthDataSimulator...");
            HealthDataSimulator.main(args);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}
}

