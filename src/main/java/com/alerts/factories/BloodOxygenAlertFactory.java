package com.alerts.factories;

import com.alerts.Alert;
import com.alerts.BloodOxygenAlert;
// Concrete factory that creates BloodOxygenAlert objects.
public class BloodOxygenAlertFactory extends AlertFactory {
    /**
     * Creates a BloodOxygenAlert.
     *
     * @param patientId The ID of the patient.
     * @param condition The condition that triggered the alert.
     * @param timestamp The timestamp when the alert was created.
     * @return A new instance of BloodOxygenAlert.
     */
    @Override
    public Alert createAlert(String patientId, String condition, long timestamp) {
        if (condition == null || condition.isEmpty()) {
            condition = "Low Blood Saturation";
        }
        // Returns a specialized alert instance.
        return new BloodOxygenAlert(patientId, condition, timestamp);
    }
}