package decorator;

import com.alerts.Alert;
import com.alerts.decorator.PriorityAlertDecorator;
import com.alerts.decorator.RepeatedAlertDecorator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DecoratorTests {

    @Test
    public void testPriorityDecoratorWrapsCorrectly() {
        Alert base = new Alert("1", "High Heart Rate", System.currentTimeMillis());
        Alert decorated = new PriorityAlertDecorator(base);

        assertEquals("1", decorated.getPatientId());
        assertTrue(decorated.getCondition().startsWith("PRIORITY:"));
        assertEquals(base.getTimestamp(), decorated.getTimestamp());
    }

    @Test
    public void testRepeatedDecoratorPassesValues() {
        Alert base = new Alert("42", "Low SpO2", System.currentTimeMillis());
        Alert repeated = new RepeatedAlertDecorator(base);

        assertEquals("42", repeated.getPatientId());
        assertEquals("Low SpO2", repeated.getCondition());
        assertEquals(base.getTimestamp(), repeated.getTimestamp());
    }

    @Test
    public void testNestedDecorators() {
        Alert base = new Alert("999", "ECG Anomaly", System.currentTimeMillis());
        Alert priorityRepeated = new PriorityAlertDecorator(new RepeatedAlertDecorator(base));

        assertEquals("999", priorityRepeated.getPatientId());
        assertTrue(priorityRepeated.getCondition().startsWith("PRIORITY:"));
    }
}