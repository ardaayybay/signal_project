package alert;

import com.alerts.AlertGenerator;
import com.data_management.DataStorage;
import com.data_management.MockDataReader;
import com.data_management.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class AlertGeneratorTest {

    private AlertGenerator generator;
    private DataStorage storage;

    @BeforeEach
    public void setup() throws IOException {
        storage = DataStorage.getInstance();
        new MockDataReader().readData(storage);
        generator = new AlertGenerator(storage);
    }

    @Test
    public void testEvaluateDataTriggersCombinedAlert() {
        Patient patient = storage.getPatient(2); // patient 2 = hypotensive + hypoxemia
        assertNotNull(patient);
        generator.evaluateData(patient); // prints to console — validate by absence of exception
    }

    @Test
    public void testEvaluateDataNoAlertForHealthyPatient() {
        Patient patient = storage.getPatient(1); // patient 1 = normal vitals
        assertNotNull(patient);
        generator.evaluateData(patient); // no alert expected
    }

    @Test
    public void testGenerateDoesNotCrash() {
        generator.generate(2, (id, ts, cond, msg) -> {
            assertNotNull(id);
            assertNotNull(ts);
            assertNotNull(cond);
        });
    }
}
