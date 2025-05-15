package com.alerts;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.data_management.DataStorage;
import com.data_management.Patient;
import com.data_management.PatientRecord;

/**
 * The {@code AlertGenerator} class is responsible for monitoring patient data
 * and generating alerts when certain predefined conditions are met. This class
 * relies on a {@link DataStorage} instance to access patient data and evaluate
 * it against specific health criteria.
 */
public class AlertGenerator {
   private final DataStorage dataStorage;
    private final Map<String, AlertFactory> alertFactories;
    
    /**
     * Constructs an {@code AlertGenerator} with a specified {@code DataStorage}.
     * The {@code DataStorage} is used to retrieve patient data that this class
     * will monitor and evaluate.
     *
     * @param dataStorage the data storage system that provides access to patient
     *                    data
     */
    public AlertGenerator(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
        this.alertFactories = new HashMap<>();
        // Register alert factories by record type
        alertFactories.put("HeartRate", new HeartRateAlertFactory());
        alertFactories.put("BloodPressure", new BloodPressureAlertFactory());
        alertFactories.put("BloodSaturation", new BloodOxygenAlertFactory());
        alertFactories.put("ECG", new ECGAlertFactory());
    }

    /**
     * Evaluates the specified patient's data to determine if any alert conditions
     * are met. If a condition is met, an alert is triggered via the
     * {@link #triggerAlert}
     * method. This method should define the specific conditions under which an
     * alert
     * will be triggered.
     *
     * @param patient the patient data to evaluate for alert conditions
     */
    public void evaluateData(Patient patient) {
        List<PatientRecord> records = dataStorage.getRecords(patient.getPatientId(), 0, System.currentTimeMillis());
        for (PatientRecord record : records) {
            String recordType = record.getRecordType();
            AlertFactory factory = alertFactories.get(recordType);
            if (factory != null) {
                String condition = conditionHelper(record);
                if (condition != null) {
                    Alert alert = factory.createAlert(String.valueOf(patient.getPatientId()), condition, record.getTimestamp());
                    triggerAlert(alert);
                }
            }
        }

       
    }
    private String conditionHelper(PatientRecord record){
        switch (record.getRecordType()) {
            case "HeartRate":
                return record.getMeasurementValue() > 100 ? "High Heart Rate" : null;
            case "BloodPressure":
                return record.getMeasurementValue() > 140 ? "High Blood Pressure" : null;
            case "BloodSaturation":
                return record.getMeasurementValue() < 90 ? "Low Blood Saturation" : null;
            case "ECG":
                return record.getMeasurementValue() > 120 ? "Irregular ECG" : null;
            default:
                return null;
        }
    }

    /**
     * Triggers an alert for the monitoring system. This method can be extended to
     * notify medical staff, log the alert, or perform other actions. The method
     * currently assumes that the alert information is fully formed when passed as
     * an argument.
     *
     * @param alert the alert object containing details about the alert condition
     */
    private void triggerAlert(Alert alert) {
        // Implementation might involve logging the alert or notifying staff
        // For demonstration, we will just print the alert details to the console
        System.out.println("Alert triggered for patient " + alert.getPatientId() +
                " with condition " + alert.getCondition() +
                " at timestamp " + alert.getTimestamp());
    }
    /**
     * Main method for testing the AlertGenerator class. This method creates a
     * DataStorage instance, adds a patient with some records, and evaluates the
     * data to trigger alerts.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        DataStorage dataStorage = DataStorage.getInstance();
        AlertGenerator alertGenerator = new AlertGenerator(dataStorage);

        // Create a patient and add some records
        Patient patient = new Patient(1);
        patient.addRecord(95.0, "HeartRate", System.currentTimeMillis() - 10000);
        patient.addRecord(105.0, "HeartRate", System.currentTimeMillis() - 5000);
        patient.addRecord(150.0, "BloodPressure", System.currentTimeMillis() - 20000);
        patient.addRecord(130.0, "ECG", System.currentTimeMillis() - 15000);
        patient.addRecord(85.0, "HypotensiveHypoxemia", System.currentTimeMillis() - 12000);
        patient.addRecord(88.0, "BloodSaturation", System.currentTimeMillis() - 8000);

        dataStorage.addPatientData(patient.getPatientId(), 105.0, "HeartRate", System.currentTimeMillis() - 5000);
        dataStorage.addPatientData(patient.getPatientId(), 150.0, "BloodPressure", System.currentTimeMillis() - 20000);
        dataStorage.addPatientData(patient.getPatientId(), 130.0, "ECG", System.currentTimeMillis() - 15000);
        dataStorage.addPatientData(patient.getPatientId(), 85.0, "HypotensiveHypoxemia", System.currentTimeMillis() - 12000);
        dataStorage.addPatientData(patient.getPatientId(), 88.0, "BloodSaturation", System.currentTimeMillis() - 8000);

        // Evaluate the patient's data for alerts
        alertGenerator.evaluateData(patient);
}
}
