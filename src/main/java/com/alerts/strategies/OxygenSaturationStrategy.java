package com.alerts.strategies;

import com.alerts.Alert;
import com.data_management.PatientRecord;

/**
 * Strategy to monitor oxygen saturation for critical drops.
 */
public class OxygenSaturationStrategy implements AlertStrategy {
    private static final double CRITICAL_THRESHOLD = 90.0; // SpO2 percentage

    @Override
    public Alert checkAlert(String patientId, PatientRecord record) {
        if (!record.getRecordType().equals("BloodSaturation")) {
            return null;
        }

        double value = record.getMeasurementValue();
        if (value < CRITICAL_THRESHOLD) {
            return new Alert(patientId, "Low Blood Saturation", record.getTimestamp());
        }
        return null;
    }
}