package com.alerts.factories;

import com.alerts.Alert;

public abstract class AlertFactory {
    /**
     * Creates an alert of a specific type based on the provided parameters.
     *
     * @param patientId the ID of the patient
     * @param condition the condition triggering the alert (e.g., "High Blood Pressure")
     * @param timestamp the time the alert was triggered
     * @return an Alert instance specific to the factory's type
     */
    public abstract Alert createAlert(String patientId, String condition, long timestamp);
}