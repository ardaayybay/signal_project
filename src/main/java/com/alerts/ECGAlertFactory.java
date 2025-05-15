package com.alerts;

import com.data_management.Patient;
import com.data_management.PatientRecord;

public class ECGAlertFactory extends AlertFactory {
    @Override
    public Alert createAlert(Patient patient, PatientRecord record) {
        if (record.getRecordType().equals("ECG") && record.getMeasurementValue() > 120) {
            return new Alert(String.valueOf(patient.getPatientId()), "Irregular ECG", record.getTimestamp());
        }
        return null;
    }

    @Override
    public String getRecordType() {
        return "ECG";
    }
}
