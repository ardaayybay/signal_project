package com.network;

public interface StreamingDataReader {
    void start();   // WebSocket bağlantısını başlatır
    void stop();    // WebSocket bağlantısını durdurur
}