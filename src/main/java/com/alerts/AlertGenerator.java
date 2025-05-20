package com.alerts;

import com.alerts.strategies.AlertStrategy;
import com.alerts.strategies.BloodPressureStrategy;
import com.alerts.strategies.ECGStrategy;
import com.alerts.strategies.HeartRateStrategy;
import com.alerts.strategies.OxygenSaturationStrategy;
import com.cardio_generator.outputs.OutputStrategy;
import com.data_management.DataStorage;
import com.data_management.Patient;
import com.data_management.PatientRecord;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The {@code AlertGenerator} class monitors patient data and generates alerts using the Strategy Pattern.
 */
public class AlertGenerator {
    private final DataStorage dataStorage;
    private final Map<String, AlertStrategy> strategies;

    /**
     * Constructs an {@code AlertGenerator} with a specified {@code DataStorage}.
     * Initializes the map of alert strategies by record type.
     *
     * @param dataStorage the data storage system that provides access to patient data
     */
    public AlertGenerator(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
        this.strategies = new HashMap<>();
        // Register strategies by record type
        strategies.put("HeartRate", new HeartRateStrategy());
        strategies.put("BloodPressure", new BloodPressureStrategy());
        strategies.put("BloodSaturation", new OxygenSaturationStrategy());
        strategies.put("ECG", new ECGStrategy());
    }

    /**
     * Evaluates the specified patient's data to determine if any alert conditions are met.
     * Delegates to the appropriate strategy for each record.
     *
     * @param patient the patient data to evaluate for alert conditions
     */
    public void evaluateData(Patient patient) {
        List<PatientRecord> records = dataStorage.getRecords(patient.getPatientId(), 0, System.currentTimeMillis());
        for (PatientRecord record : records) {
            String recordType = record.getRecordType();
            AlertStrategy strategy = strategies.get(recordType);
            if (strategy != null) {
                Alert alert = strategy.checkAlert(String.valueOf(patient.getPatientId()), record);
                if (alert != null) {
                    triggerAlert(alert);
                }
            }
        }
    }

    /**
     * Generates an alert for a specific patient and outputs it using the provided strategy.
     * Used by HealthDataSimulator.
     *
     * @param patientId      the ID of the patient
     * @param outputStrategy the strategy to output the alert
     */
    public void generate(int patientId, OutputStrategy outputStrategy) {
        Patient patient = dataStorage.getPatient(patientId);
        if (patient != null) {
            List<PatientRecord> records = dataStorage.getRecords(patientId, 0, System.currentTimeMillis());
            for (PatientRecord record : records) {
                String recordType = record.getRecordType();
                AlertStrategy strategy = strategies.get(recordType);
                if (strategy != null) {
                    Alert alert = strategy.checkAlert(String.valueOf(patientId), record);
                    if (alert != null) {
                        outputStrategy.output(patientId, alert.getTimestamp(), alert.getCondition(), alert.getCondition());
                    }
                }
            }
        }
    }

    /**
     * Triggers an alert by logging it to the console.
     *
     * @param alert the alert object containing details about the alert condition
     */
    private void triggerAlert(Alert alert) {
        System.out.println("Alert triggered for patient " + alert.getPatientId() +
                " with condition " + alert.getCondition() +
                " at timestamp " + alert.getTimestamp());
    }

    /**
     * Main method for testing the AlertGenerator class.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        DataStorage dataStorage = DataStorage.getInstance();
        AlertGenerator alertGenerator = new AlertGenerator(dataStorage);

        // Create a patient and add some records
        Patient patient = new Patient(1);
        // Add HeartRate records
        patient.addRecord(95.0, "HeartRate", System.currentTimeMillis() - 10000);
        patient.addRecord(105.0, "HeartRate", System.currentTimeMillis() - 5000);
        // Add ECG records
        patient.addRecord(1.6, "ECG", System.currentTimeMillis() - 8000); // High QRS for alert
        patient.addRecord(0.2, "ECG", System.currentTimeMillis() - 7000); // Normal ECG value
        // Add other records
        patient.addRecord(150.0, "BloodPressure", System.currentTimeMillis() - 20000);
        patient.addRecord(88.0, "BloodSaturation", System.currentTimeMillis() - 6000);

        // Add records to DataStorage
        dataStorage.addPatientData(patient.getPatientId(), 105.0, "HeartRate", System.currentTimeMillis() - 5000);
        dataStorage.addPatientData(patient.getPatientId(), 1.6, "ECG", System.currentTimeMillis() - 8000);
        dataStorage.addPatientData(patient.getPatientId(), 0.2, "ECG", System.currentTimeMillis() - 7000);
        dataStorage.addPatientData(patient.getPatientId(), 150.0, "BloodPressure", System.currentTimeMillis() - 20000);
        dataStorage.addPatientData(patient.getPatientId(), 88.0, "BloodSaturation", System.currentTimeMillis() - 6000);

        // Evaluate the patient's data for alerts
        alertGenerator.evaluateData(patient);
    }
}