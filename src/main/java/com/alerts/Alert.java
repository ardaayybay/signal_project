package com.alerts;

// Represents a basic alert structure for a patient.
public class Alert {
    // The ID of the patient associated with the alert.
    private String patientId;
    // The condition or issue that triggered the alert.
    private String condition;
     // The time the alert was generated (in milliseconds).
    private long timestamp;
    // Constructor to initialize all alert fields.
    public Alert(String patientId, String condition, long timestamp) {
        this.patientId = patientId;
        this.condition = condition;
        this.timestamp = timestamp;
    }
    // Returns the ID of the patient.
    public String getPatientId() {
        return patientId;
    }
    // Returns the condition that triggered the alert.
    public String getCondition() {
        return condition;
    }
    // Returns the timestamp of the alert.
    public long getTimestamp() {
        return timestamp;
    }
}
