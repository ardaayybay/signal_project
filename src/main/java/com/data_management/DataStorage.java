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

    public DataStorage() {
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

    /**
     * Example main method showing usage.
     */
    public static void main(String[] args) {
        DataStorage storage = new DataStorage();

        // Örnek veri ekleyelim:
        storage.addPatientData(1, 190, "HeartRate", System.currentTimeMillis());
        storage.addPatientData(1, 80, "Saturation", System.currentTimeMillis());

        // Kayıtları yazdıralım
        List<PatientRecord> records = storage.getRecords(1, 0, System.currentTimeMillis());
        for (PatientRecord record : records) {
            System.out.println("Record for Patient ID: " + record.getPatientId() +
                    ", Type: " + record.getRecordType() +
                    ", Data: " + record.getMeasurementValue() +
                    ", Timestamp: " + record.getTimestamp());
        }

        // AlertGenerator artık parametresiz
        AlertGenerator alertGenerator = new AlertGenerator();

        // Uyarı koşullarını kontrol edelim
        for (Patient patient : storage.getAllPatients()) {
            alertGenerator.evaluateData(patient);
        }
    }
}
