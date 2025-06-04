package com.alerts;

public class BloodPressureAlert extends Alert {
    /**
     * Constructor for BloodPressureAlert.
     *
     * @param patientId The ID of the patient.
     * @param condition The condition that triggered the alert.
     * @param timestamp The timestamp when the alert was created.
     */
    public BloodPressureAlert(String patientId, String condition, long timestamp) {
        super(patientId, condition, timestamp);
    }
}