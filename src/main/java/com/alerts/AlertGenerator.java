package com.alerts;
import java.util.List;

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
    private DataStorage dataStorage;
    
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
        // Implementation goes here
        // For demonstration, we will assume that if the heart rate exceeds 100 bpm,
        // an alert is triggered
        List<PatientRecord> records = dataStorage.getRecords(patient.getPatientId(), 0, 1800000000000L);
        for (PatientRecord record : records) {
            if (record.getRecordType().equals("HeartRate") && record.getMeasurementValue() > 100) {
                Alert alert = new Alert(String.valueOf(patient.getPatientId()), "High Heart Rate", record.getTimestamp());
                triggerAlert(alert);
            }
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
        DataStorage dataStorage = new DataStorage();
        AlertGenerator alertGenerator = new AlertGenerator(dataStorage);

        // Create a patient and add some records
        Patient patient = new Patient(1);
        patient.addRecord(95.0, "HeartRate", System.currentTimeMillis() - 10000);
        patient.addRecord(105.0, "HeartRate", System.currentTimeMillis() - 5000);
        dataStorage.addPatientData(patient.getPatientId(), 105.0, "HeartRate", System.currentTimeMillis() - 5000);

        // Evaluate the patient's data for alerts
        alertGenerator.evaluateData(patient);
}
}
