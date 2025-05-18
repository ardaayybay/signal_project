package com.alerts.strategies;

import com.alerts.Alert;
import com.data_management.PatientRecord;

/**
 * Strategy to monitor heart rate for abnormal values.
 */
public class HeartRateStrategy implements AlertStrategy {
    private static final double HIGH_THRESHOLD = 100.0; // bpm
    private static final double LOW_THRESHOLD = 60.0;   // bpm

    @Override
    public Alert checkAlert(String patientId, PatientRecord record) {
        if (!record.getRecordType().equals("HeartRate")) {
            return null;
        }

        double value = record.getMeasurementValue();
        if (value > HIGH_THRESHOLD) {
            return new Alert(patientId, "High Heart Rate", record.getTimestamp());
        } else if (value < LOW_THRESHOLD) {
            return new Alert(patientId, "Low Heart Rate", record.getTimestamp());
        }
        return null;
    }
}