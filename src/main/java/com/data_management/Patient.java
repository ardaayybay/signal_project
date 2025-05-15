package com.data_management;

import java.util.ArrayList;
import java.util.List;

public class Patient {
    private int patientId;
    private List<PatientRecord> patientRecords;

    public Patient(int patientId) {
        this.patientId = patientId;
        this.patientRecords = new ArrayList<>();
    }

    public int getPatientId() {
        return this.patientId;
    }

    public double getHeartRate() {
    return this.patientRecords.stream()
        .filter(record -> record.getRecordType().equalsIgnoreCase("HeartRate"))
        .mapToDouble(PatientRecord::getMeasurementValue)
        .max()
        .orElse(-1);
}

    public List<PatientRecord> getRecords(long startTime, long endTime) {
    return this.patientRecords.stream()
            .filter(record -> record.getTimestamp() >= startTime && record.getTimestamp() <= endTime)
            .toList();
}

    public void addRecord(double value, String type, long timestamp) {
        patientRecords.add(new PatientRecord(this.patientId, value, type, timestamp));
    }
}
