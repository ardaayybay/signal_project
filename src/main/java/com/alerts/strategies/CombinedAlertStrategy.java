package com.alerts.strategies;

import com.alerts.Alert;
import com.alerts.BasicAlert;
import com.data_management.DataStorage;
import com.data_management.Patient;
import com.data_management.PatientRecord;

import java.util.List;

public class CombinedAlertStrategy implements AlertStrategy {
    private final DataStorage dataStorage;

    public CombinedAlertStrategy(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }

    @Override
    public Alert checkAlert(String patientId, PatientRecord ignored) {
        Patient patient = dataStorage.getPatient(Integer.parseInt(patientId));
        if (patient == null) return null;

        long now = System.currentTimeMillis();
        List<PatientRecord> bpRecords = patient.getRecords(now - 10 * 60 * 1000, now).stream()
                .filter(r -> r.getRecordType().equalsIgnoreCase("BloodPressure"))
                .toList();

        List<PatientRecord> o2Records = patient.getRecords(now - 10 * 60 * 1000, now).stream()
                .filter(r -> r.getRecordType().equalsIgnoreCase("BloodSaturation"))
                .toList();

        boolean lowBP = bpRecords.stream().anyMatch(r -> r.getMeasurementValue() < 90);
        boolean lowO2 = o2Records.stream().anyMatch(r -> r.getMeasurementValue() < 92);

        if (lowBP && lowO2) {
            return new BasicAlert(patientId, "Hypotensive Hypoxemia", now);
        }

        return null;
    }
}
