package com.alerts.factories;

import com.alerts.Alert;
import com.alerts.ECGAlert;
// Concrete factory class to create ECGAlert objects.
public class ECGAlertFactory extends AlertFactory {
    /**
     * Creates an ECG alert for a patient.
     *
     * @param patientId The ID of the patient.
     * @param condition The condition that triggered the alert.
     * @param timestamp The timestamp when the alert was created.
     * @return An instance of ECGAlert.
     */
    @Override
    public Alert createAlert(String patientId, String condition, long timestamp) {
        if (condition == null || condition.isEmpty()) {
            condition = "Irregular ECG";
        }
        // Instantiate and return an ECG-specific alert.
        return new ECGAlert(patientId, condition, timestamp);
    }
}