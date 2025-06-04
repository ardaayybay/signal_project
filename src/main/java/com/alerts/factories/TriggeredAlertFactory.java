package com.alerts.factories;

import com.alerts.Alert;
/**
 * Factory class for creating alerts that are manually triggered by healthcare professionals.
 * This class extends the AlertFactory and provides a specific implementation for creating
 */
public class TriggeredAlertFactory extends AlertFactory {
    @Override
    public Alert createAlert(String patientId, String condition, long timestamp) {
        return new Alert(patientId, "Manual Trigger - " + condition, timestamp);
    }
}
