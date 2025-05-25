package strategy;

import com.alerts.Alert;
import com.alerts.strategies.*;
import com.data_management.PatientRecord;
import com.data_management.DataStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StrategyTests {

    private final long now = System.currentTimeMillis();
    private DataStorage storage;

    @BeforeEach
    public void setup() {
        storage = DataStorage.getInstance();
    }

    @Test
    public void testHeartRateStrategyTriggers() {
        AlertStrategy strategy = new HeartRateStrategy();
        PatientRecord high = new PatientRecord(1, 120.0, "HeartRate", now);
        Alert alert = strategy.checkAlert("1", high);
        assertNotNull(alert);
        assertEquals("High Heart Rate (Tachycardia)", alert.getCondition());
    }

    @Test
    public void testOxygenStrategyNoAlert() {
        AlertStrategy strategy = new OxygenSaturationStrategy();
        PatientRecord normal = new PatientRecord(1, 98.0, "BloodSaturation", now);
        Alert alert = strategy.checkAlert("1", normal);
        assertNull(alert);
    }

    @Test
    public void testBloodPressureStrategyLow() {
        AlertStrategy strategy = new BloodPressureStrategy();
        PatientRecord criticalHigh = new PatientRecord(1, 190.0, "BloodPressure", now);
        Alert alert = strategy.checkAlert("1", criticalHigh);
        assertNotNull(alert);
        assertEquals("High Blood Pressure", alert.getCondition());
    }

    @Test
    public void testECGStrategyDetectsPeak() {
        AlertStrategy strategy = new ECGStrategy();
        PatientRecord peak = new PatientRecord(1, 2.5, "ECG", now);
        Alert alert = strategy.checkAlert("1", peak);
        assertNotNull(alert); // Just check that it doesn't return null
    }
} 