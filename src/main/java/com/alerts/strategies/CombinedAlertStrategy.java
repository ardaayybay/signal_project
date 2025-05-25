package com.alerts.strategies;

import com.alerts.Alert;
import com.alerts.BasicAlert;
import com.data_management.DataStorage;
import com.data_management.Patient;
import com.data_management.PatientRecord;

import java.util.List;
// Strategy class to detect a combined condition from multiple vital signals
public class CombinedAlertStrategy implements AlertStrategy {
    private final DataStorage dataStorage;

    public CombinedAlertStrategy(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }

    @Override
    public Alert checkAlert(String patientId, PatientRecord ignored) {
        // Fetch patient object from storage.
        Patient patient = dataStorage.getPatient(Integer.parseInt(patientId));
        if (patient == null) return null;
        // Define time window: last 10 minutes.
        long now = System.currentTimeMillis();
         // Filter for blood pressure records in the time window
        List<PatientRecord> bpRecords = patient.getRecords(now - 10 * 60 * 1000, now).stream()
                .filter(r -> r.getRecordType().equalsIgnoreCase("BloodPressure"))
                .toList();
        // Filter for oxygen saturation records.
        List<PatientRecord> o2Records = patient.getRecords(now - 10 * 60 * 1000, now).stream()
                .filter(r -> r.getRecordType().equalsIgnoreCase("BloodSaturation"))
                .toList();
        // Check if any BP reading is critically low.
        boolean lowBP = bpRecords.stream().anyMatch(r -> r.getMeasurementValue() < 90);
        boolean lowO2 = o2Records.stream().anyMatch(r -> r.getMeasurementValue() < 92);
         // Trigger alert only if both are critically low.
        if (lowBP && lowO2) {
            return new BasicAlert(patientId, "Hypotensive Hypoxemia", now);
        }

        return null;
    }
}
