package strategy;
import com.alerts.strategies.CombinedAlertStrategy;
import com.data_management.DataStorage;
import com.data_management.MockDataReader;
import com.data_management.PatientRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class CombinedAlertStrategyTest {
    private DataStorage dataStorage;

    @BeforeEach
    public void setup() throws IOException {
        dataStorage = DataStorage.getInstance();
        new MockDataReader().readData(dataStorage);
    }

    @Test
    public void testHypotensiveHypoxemiaTriggersAlert() {
        CombinedAlertStrategy strategy = new CombinedAlertStrategy(dataStorage);
        // Patient 2 was crafted to trigger the combined condition
        var alert = strategy.checkAlert("2", null);
        assertNotNull(alert, "Expected an alert for Hypotensive Hypoxemia");
        assertEquals("Hypotensive Hypoxemia", alert.getCondition());
    }

    @Test
    public void testNoAlertForNormalPatient() {
        CombinedAlertStrategy strategy = new CombinedAlertStrategy(dataStorage);
        var alert = strategy.checkAlert("1", null);
        assertNull(alert, "Did not expect an alert for normal patient");
    }
}
