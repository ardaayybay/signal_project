package factory;

import com.alerts.Alert;
import com.alerts.factories.AlertFactory;
import com.alerts.factories.BloodOxygenAlertFactory;
import com.alerts.factories.BloodPressureAlertFactory;
import com.alerts.factories.ECGAlertFactory;
import com.alerts.factories.TriggeredAlertFactory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FactoryTests {

    private final String patientId = "100";
    private final long now = System.currentTimeMillis();

    @Test
    public void testBloodPressureAlertFactory() {
        AlertFactory factory = new BloodPressureAlertFactory();
        Alert alert = factory.createAlert(patientId, "Critical BP", now);

        assertNotNull(alert);
        assertEquals("Critical BP", alert.getCondition());
    }

    @Test
    public void testBloodOxygenAlertFactory() {
        AlertFactory factory = new BloodOxygenAlertFactory();
        Alert alert = factory.createAlert(patientId, "Low SpO2", now);

        assertNotNull(alert);
        assertEquals("Low SpO2", alert.getCondition());
    }

    @Test
    public void testECGAlertFactory() {
        AlertFactory factory = new ECGAlertFactory();
        Alert alert = factory.createAlert(patientId, "ECG Irregularity", now);

        assertNotNull(alert);
        assertTrue(alert.getCondition().contains("ECG"));
    }

    @Test
    public void testTriggeredAlertFactory() {
        AlertFactory factory = new TriggeredAlertFactory();
        Alert alert = factory.createAlert(patientId, "Assistance Requested", now);

        assertNotNull(alert);
        assertTrue(alert.getCondition().contains("Manual Trigger"));
    }
}
