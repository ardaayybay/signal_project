package com;

import com.alerts.AlertGenerator;
import com.data_management.DataStorage;
import com.network.WebSocketPatientClient;

public class Main {
    public static void main(String[] args) {
        try {
            // 1. Veri deposu ve alarm sistemi
            DataStorage storage = new DataStorage();
            AlertGenerator alertGenerator = new AlertGenerator();

            // 2. WebSocket istemcisi (port veya URL sunucuya göre ayarlanmalı)
            String serverUrl = "ws://localhost:8080"; // WebSocket sunucu adresi
            WebSocketPatientClient client = new WebSocketPatientClient(serverUrl, storage, alertGenerator);

            // 3. Bağlantıyı başlat
            client.start();

            // Uygulama kapanmasın diye sonsuz döngü (ctrl+c ile durdurulmalı)
            while (true) {
                Thread.sleep(1000);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
