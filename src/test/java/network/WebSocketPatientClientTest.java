package network;

import com.alerts.AlertGenerator;
import com.data_management.DataStorage;
import com.data_management.Patient;
import com.network.WebSocketPatientClient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

public class WebSocketPatientClientTest {

    private WebSocketPatientClient client;
    private DataStorage storage;
    private AlertGenerator alertGenerator;

    @BeforeEach
    public void setup() throws URISyntaxException {
        storage = DataStorage.getInstance();
        alertGenerator = new AlertGenerator(storage);
        client = new WebSocketPatientClient("ws://localhost:12345", storage, alertGenerator);
    }

    @Test
    public void testMessageParsingAndStorage() {
        String mockMessage = "{\"patientId\":1,\"measurementType\":\"HeartRate\",\"measurementValue\":85.5,\"timestamp\":1705000000000}";
        client.onMessage(mockMessage);

        Patient patient = storage.getPatient(1);
        assertNotNull(patient);
        assertFalse(patient.getRecords(1704999999000L, 1705000001000L).isEmpty());
    }

    @Test
    public void testInvalidMessageDoesNotCrash() {
        assertDoesNotThrow(() -> {
            client.onMessage("invalid_json:123,broken message");
        });
    }

    @Test
    public void testMissingFieldsHandledGracefully() {
        String partialMessage = "{\"patientId\":2,\"measurementType\":\"BloodPressure\"}";
        assertDoesNotThrow(() -> {
            client.onMessage(partialMessage);
        });
    }
}