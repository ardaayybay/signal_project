package com.network;

import java.net.URI;
import java.net.URISyntaxException;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import com.alerts.AlertGenerator;
import com.data_management.DataStorage;
import com.data_management.Patient;

public class WebSocketPatientClient extends WebSocketClient implements StreamingDataReader {
    private DataStorage storage;
    private AlertGenerator alertGenerator;

    public WebSocketPatientClient(String serverUri, DataStorage storage, AlertGenerator alertGenerator) throws URISyntaxException {
        super(new URI(serverUri));
        this.storage = storage;
        this.alertGenerator = alertGenerator;
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("Connected to WebSocket Server");
    }

   @Override
public void onMessage(String message) {
    try {
        String[] parts = message.replaceAll("[{}\"]", "").split(",");

        int patientId = 0;
        String type = "";
        double value = 0.0;
        long timestamp = 0L;

        for (String part : parts) {
            String[] pair = part.split(":");
            switch (pair[0].trim()) {
                case "patientId" -> patientId = Integer.parseInt(pair[1].trim());
                case "measurementType" -> type = pair[1].trim();
                case "measurementValue" -> value = Double.parseDouble(pair[1].trim());
                case "timestamp" -> timestamp = Long.parseLong(pair[1].trim());
            }
        }

        // ✅ Veriyi sakla
        storage.addPatientData(patientId, value, type, timestamp);

        // ✅ Hasta objesini bulup alarmları tetikle
        final int finalPatientId = patientId;  // 👈 Burada final yapınca lambda şikayet etmiyor
        Patient patient = storage.getAllPatients().stream()
                .filter(p -> p.getPatientId() == finalPatientId)
                .findFirst().orElse(null);

        if (patient != null) {
            alertGenerator.evaluateData(patient);
        }

    } catch (Exception e) {
        System.err.println("Error parsing message: " + message);
        e.printStackTrace();
    }
}


    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("Disconnected from WebSocket Server");
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
    }

    // StreamingDataReader interface
    @Override
    public void start() {
        this.connect();
    }

    @Override
    public void stop() {
        this.close();
    }
}
