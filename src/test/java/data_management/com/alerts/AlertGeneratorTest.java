package com.alerts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.data_management.Patient;

public class AlertGeneratorTest {
    private AlertGenerator alertGenerator;
    private Patient mockPatient;

    @BeforeEach
    public void setup() {
        alertGenerator = new AlertGenerator();
        mockPatient = mock(Patient.class);
    }

    @Test
    public void testEvaluateData_WhenHeartRateTooHigh_ShouldTriggerAlert() {
        when(mockPatient.getHeartRate()).thenReturn(200.0);
        when(mockPatient.getPatientId()).thenReturn(1);

        alertGenerator.evaluateData(mockPatient);
    }
}
