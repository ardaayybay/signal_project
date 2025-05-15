package com.alerts;

import com.data_management.Patient;

public class AlertGenerator {

    public AlertGenerator() {
        // Artık parametresiz constructor
    }

    public void evaluateData(Patient patient) {
        if (patient == null) return;

        if (patient.getHeartRate() > 180) {
            String patientId = String.valueOf(patient.getPatientId());
            Alert alert = new Alert(patientId, "Heart rate dangerously high", System.currentTimeMillis());
            triggerAlert(alert);
        }
    }

    private void triggerAlert(Alert alert) {
        System.out.println("🚨 ALERT for Patient " + alert.getPatientId() +
                " | Condition: " + alert.getCondition() +
                " | Time: " + alert.getTimestamp());
    }
}
