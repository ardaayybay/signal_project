package alert;

import com.alerts.Alert;
import com.alerts.decorator.PriorityAlertDecorator;
import com.alerts.decorator.RepeatedAlertDecorator;
import com.alerts.factories.TriggeredAlertFactory;
import com.alerts.strategies.CombinedAlertStrategy;
import com.data_management.DataStorage;
import com.data_management.MockDataReader;
import com.data_management.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class AlertUnitTests {
    private DataStorage dataStorage;

    @BeforeEach
    public void setup() throws IOException {
        dataStorage = DataStorage.getInstance();
        new MockDataReader().readData(dataStorage);
    }

    @Test
    public void testTriggeredAlertFactory() {
        TriggeredAlertFactory factory = new TriggeredAlertFactory();
        Alert alert = factory.createAlert("5", "Assistance Needed", System.currentTimeMillis());

        assertNotNull(alert);
        assertTrue(alert.getCondition().contains("Manual Trigger"));
        assertEquals("5", alert.getPatientId());
    }

    @Test
    public void testPriorityAlertDecoratorAddsPrefix() {
        Alert base = new Alert("1", "Low Oxygen", System.currentTimeMillis());
        Alert decorated = new PriorityAlertDecorator(base);

        assertTrue(decorated.getCondition().startsWith("PRIORITY: "));
    }

    @Test
    public void testRepeatedAlertDecoratorRetainsOriginalData() {
        Alert base = new Alert("1", "High Heart Rate", System.currentTimeMillis());
        Alert repeated = new RepeatedAlertDecorator(base);

        assertEquals(base.getCondition(), repeated.getCondition());
        assertEquals(base.getPatientId(), repeated.getPatientId());
    }

    @Test
    public void testCombinedAlertStrategyWithTriggeredPatient() {
        CombinedAlertStrategy strategy = new CombinedAlertStrategy(dataStorage);
        Alert alert = strategy.checkAlert("2", null);

        assertNotNull(alert);
        assertEquals("Hypotensive Hypoxemia", alert.getCondition());
    }

    @Test
    public void testCombinedAlertStrategyWithNormalPatient() {
        CombinedAlertStrategy strategy = new CombinedAlertStrategy(dataStorage);
        Alert alert = strategy.checkAlert("1", null);

        assertNull(alert);
    }

    @Test
    public void testDataLoadedCorrectlyFromMock() {
        Patient patient = dataStorage.getPatient(1);
        assertNotNull(patient);
        assertFalse(patient.getRecords(0, System.currentTimeMillis()).isEmpty());
    }
}

