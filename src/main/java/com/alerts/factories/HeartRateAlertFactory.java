package com.alerts.factories;

import com.alerts.Alert;
import com.alerts.HeartRateAlert;
// Factory class for creating HeartRateAlert instances.
public class HeartRateAlertFactory extends AlertFactory {
    @Override
    public Alert createAlert(String patientId, String condition, long timestamp) {
        if(condition == null || condition.isEmpty()) {
            condition = "Abnormal Heart Rate"; // Default condition
        }
        return new HeartRateAlert(patientId, condition, timestamp);
    }
    
}
