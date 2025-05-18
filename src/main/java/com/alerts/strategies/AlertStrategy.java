package com.alerts.strategies;

import com.alerts.Alert;
import com.data_management.PatientRecord;

/**
 * Interface for alert generation strategies, defining the method to check for alerts.
 */
public interface AlertStrategy {
    /**
     * Checks if an alert should be triggered based on the patient record.
     *
     * @param patientId the ID of the patient
     * @param record the patient record to evaluate
     * @return an Alert if an alert should be triggered, null otherwise
     */
    Alert checkAlert(String patientId, PatientRecord record);
}