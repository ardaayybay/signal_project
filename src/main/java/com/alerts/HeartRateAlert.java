package com.alerts;

public class HeartRateAlert extends Alert {
    /**
     * Constructor for HeartRateAlert.
     *
     * @param patientId The ID of the patient.
     * @param condition The condition that triggered the alert.
     * @param timestamp The timestamp when the alert was created.
     */
    public HeartRateAlert(String patientId, String condition, long timestamp) {
        super(patientId, condition, timestamp);
    }
    
}
