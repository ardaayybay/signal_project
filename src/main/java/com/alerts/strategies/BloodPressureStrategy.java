package com.alerts.strategies;

import com.alerts.Alert;
import com.data_management.PatientRecord;

/**
 * Strategy to monitor blood pressure for trends and critical thresholds.
 */
public class BloodPressureStrategy implements AlertStrategy {
    private static final double SYSTOLIC_THRESHOLD = 140.0; // Critical systolic threshold
    private static final double DIASTOLIC_THRESHOLD = 90.0; // Critical diastolic threshold

    @Override
    public Alert checkAlert(String patientId, PatientRecord record) {
        if (!record.getRecordType().equals("BloodPressure")) {
            return null;
        }

        double value = record.getMeasurementValue();
        // Assuming the value is systolic (for simplicity; adjust if diastolic is separate)
        if (value > SYSTOLIC_THRESHOLD || value > DIASTOLIC_THRESHOLD) {
            return new Alert(patientId, "High Blood Pressure", record.getTimestamp());
        }

        // Simple trend check (e.g., last 3 readings increasing)
        // This requires access to historical data; for now, we'll skip trend analysis
        // To implement trend, integrate with DataStorage to fetch recent records
        return null;
    }
}