package com.alerts.factories;

import com.alerts.Alert;

public class TriggeredAlertFactory extends AlertFactory {
    @Override
    public Alert createAlert(String patientId, String condition, long timestamp) {
        return new Alert(patientId, "Manual Trigger - " + condition, timestamp);
    }
}
