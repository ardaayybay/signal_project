package com.alerts.factories;

import com.alerts.Alert;
import com.alerts.AlertFactory;
import com.alerts.BloodPressureAlert;

public class BloodPressureAlertFactory extends AlertFactory {
    /**
     * Creates a BloodPressureAlert instance.
     *
     * @param patientId The ID of the patient.
     * @param condition The condition that triggered the alert.
     * @param timestamp The timestamp when the alert was created.
     * @return A new instance of BloodPressureAlert.
     */
    @Override
    public Alert createAlert(String patientId, String condition, long timestamp) {
        // Validate or normalize condition if needed
        if (condition == null || condition.isEmpty()) {
            condition = "High Blood Pressure"; // Default condition
        }
        return new BloodPressureAlert(patientId, condition, timestamp);
    }
}