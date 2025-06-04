package com.data_management;

import java.io.IOException;
import java.time.Instant;

public class MockDataReader implements DataReader {
    @Override
     public void readData(DataStorage dataStorage) throws IOException {
        long now = Instant.now().toEpochMilli();

        // Patient 1
        dataStorage.addPatientData(1, 75.0, "HeartRate", now - 10000);
        dataStorage.addPatientData(1, 80.0, "HeartRate", now - 5000);
        dataStorage.addPatientData(1, 95.0, "BloodPressure", now - 12000);
        dataStorage.addPatientData(1, 85.0, "BloodSaturation", now - 3000);

        // Patient 2 - triggering hypotensive hypoxemia
        dataStorage.addPatientData(2, 88.0, "BloodPressure", now - 10000);
        dataStorage.addPatientData(2, 89.0, "BloodPressure", now - 8000);
        dataStorage.addPatientData(2, 90.0, "BloodPressure", now - 6000);
        dataStorage.addPatientData(2, 91.0, "BloodSaturation", now - 7000);
        dataStorage.addPatientData(2, 90.0, "BloodSaturation", now - 4000);

        // Patient 3 - edge case ECG
        dataStorage.addPatientData(3, 0.3, "ECG", now - 15000);
        dataStorage.addPatientData(3, 2.1, "ECG", now - 14000); // Peak
        dataStorage.addPatientData(3, 0.25, "ECG", now - 13000);

        System.out.println("Mock data loaded into DataStorage.");
    }
} 
