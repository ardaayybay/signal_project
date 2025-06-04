package com.alerts.strategies;

import com.alerts.Alert;
import com.alerts.HeartRateAlert;
import com.data_management.PatientRecord;

public class HeartRateStrategy implements AlertStrategy {
    private static final double HIGH_THRESHOLD = 100.0; // bpm, tachycardia
    private static final double LOW_THRESHOLD = 60.0;   // bpm, bradycardia

    /**
     * Checks if a heart rate alert should be triggered based on the patient record.
     *
     * @param patientId The ID of the patient.
     * @param record    The patient record containing heart rate data.
     * @return A HeartRateAlert if an alert condition is met, otherwise null.
     */
    @Override
    public Alert checkAlert(String patientId, PatientRecord record) {
        if (!record.getRecordType().equals("HeartRate")) {
            return null;
        }

        double value = record.getMeasurementValue();
        if (value > HIGH_THRESHOLD) {
            return new HeartRateAlert(patientId, "High Heart Rate (Tachycardia)", record.getTimestamp());
        } else if (value < LOW_THRESHOLD) {
            return new HeartRateAlert(patientId, "Low Heart Rate (Bradycardia)", record.getTimestamp());
        }
        return null;
    }
}