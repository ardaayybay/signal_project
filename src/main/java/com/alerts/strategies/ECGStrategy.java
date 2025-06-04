package com.alerts.strategies;

import com.alerts.Alert;
import com.alerts.ECGAlert;
import com.data_management.PatientRecord;
// Strategy class for detecting abnormal ECG readings.
public class ECGStrategy implements AlertStrategy {
    // Threshold for abnormal QRS amplitude (in millivolts).
    private static final double QRS_THRESHOLD = 1.5; // mV, for abnormal QRS amplitude

    @Override
    public Alert checkAlert(String patientId, PatientRecord record) {
        if (!record.getRecordType().equals("ECG")) {
            return null;
        }

        double value = record.getMeasurementValue();
        // Trigger alert if QRS amplitude exceeds threshold.
        if (Math.abs(value) > QRS_THRESHOLD) {
            return new ECGAlert(patientId, "Abnormal QRS Amplitude", record.getTimestamp());
        }
        // No alert if within normal range.
        return null;
    }
}