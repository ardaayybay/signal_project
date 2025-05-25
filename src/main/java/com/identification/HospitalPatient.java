package com.identification;

import java.util.HashMap;
import java.util.Map;

// Represents patient details pulled from the hospital database
public class HospitalPatient {
    private final String patientId;
    private final String name;
    private final String birthDate;

    public HospitalPatient(String patientId, String name, String birthDate) {
        this.patientId = patientId;
        this.name = name;
        this.birthDate = birthDate;
    }

    public String getPatientId() { return patientId; }
    public String getName() { return name; }
    public String getBirthDate() { return birthDate; }
}
// Matches incoming signal patient IDs to hospital database
class PatientIdentifier {
    private final Map<String, HospitalPatient> hospitalDB;

    public PatientIdentifier() {
        this.hospitalDB = new HashMap<>();
    }

    public void registerPatient(HospitalPatient patient) {
        hospitalDB.put(patient.getPatientId(), patient);
    }

    public HospitalPatient identify(String incomingId) {
        return hospitalDB.get(incomingId);
    }
}

// Oversees integrity of identification and logs anomalies
class IdentityManager {
    private final PatientIdentifier identifier;

    public IdentityManager(PatientIdentifier identifier) {
        this.identifier = identifier;
    }

    public HospitalPatient resolvePatient(String incomingId) {
        HospitalPatient match = identifier.identify(incomingId);
        if (match == null) {
            System.err.println("[ERROR] Unknown patient ID: " + incomingId);
        }
        return match;
    }
}
