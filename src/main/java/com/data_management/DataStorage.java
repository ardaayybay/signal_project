package com.data_management;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alerts.AlertGenerator;

/**
 * Manages storage and retrieval of patient data within a healthcare monitoring
 * system.
 */
public class DataStorage {
    private Map<Integer, Patient> patientMap;
    // Singleton instance of DataStorage
    private static DataStorage instance = null;   
    private DataStorage() {
        this.patientMap = new HashMap<>();
    }

    /**
     * Adds or updates patient data in the storage.
     * If the patient doesn't exist, creates a new one.
     */
    public void addPatientData(int patientId, double measurementValue, String recordType, long timestamp) {
        Patient patient = patientMap.get(patientId);
        if (patient == null) {
            patient = new Patient(patientId);
            patientMap.put(patientId, patient);
        }
        patient.addRecord(measurementValue, recordType, timestamp);
    }
    /*
     * * Retrieves the singleton instance of the DataStorage class.
     * This method ensures that only one instance of DataStorage exists in the
     * system,
     */
    public static DataStorage getInstance() {
        if (instance == null) {
            instance = new DataStorage();
        }
        return instance;
    }

    /**
     * Retrieves patient records in the given time range.
     */
    public List<PatientRecord> getRecords(int patientId, long startTime, long endTime) {
        Patient patient = patientMap.get(patientId);
        if (patient != null) {
            return patient.getRecords(startTime, endTime);
        }
        return new ArrayList<>();
    }

    /**
     * Returns a list of all patients.
     */
    public List<Patient> getAllPatients() {
        return new ArrayList<>(patientMap.values());
    }
    public Patient getPatient(int patientId) {
        return patientMap.get(patientId);
    }


    /**
     * Example main method showing usage.
     */
    public static void main(String[] args) {
        System.out.println("DataStorage main method running...");
        
        DataStorage storage = DataStorage.getInstance();
        // Optional: Populate dummy data for testing
        storage.addPatientData(1, 75.0, "HeartRate", 1705000000000L);
        storage.addPatientData(1, 80.0, "HeartRate", 1706000000000L);
    
        List<PatientRecord> records = storage.getRecords(1, 1700000000000L, 1800000000000L);
        for (PatientRecord record : records) {
            System.out.println("Record for Patient ID: " + record.getPatientId() +
                    ", Type: " + record.getRecordType() +
                    ", Data: " + record.getMeasurementValue() +
                    ", Timestamp: " + record.getTimestamp());
        }
    
        AlertGenerator alertGenerator = new AlertGenerator(storage);
    
        for (Patient patient : storage.getAllPatients()) {
            alertGenerator.evaluateData(patient);
        }
    }
    
}
