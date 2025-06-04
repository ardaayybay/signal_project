package com.alerts;

public class BasicAlert extends Alert {
    /**
     * Constructor for BasicAlert.
     *
     * @param patientId The ID of the patient.
     * @param condition The condition that triggered the alert.
     * @param timestamp The timestamp when the alert was created.
     */
    public BasicAlert(String patientId, String condition, long timestamp) {
        super(patientId, condition, timestamp);
    }
}
