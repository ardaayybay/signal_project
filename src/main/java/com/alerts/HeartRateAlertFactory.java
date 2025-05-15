package com.alerts;

public class HeartRateAlertFactory extends AlertFactory {
    @Override
    public Alert createAlert(String patientId, String condition, long timestamp) {
        if(condition == null || condition.isEmpty()) {
            condition = "Abnormal Heart Rate"; // Default condition
        }
        return new HeartRateAlert(patientId, condition, timestamp);
    }
    
}
