package com.data_management;

import java.io.IOException;

public interface MockDataReader {
    void readData(DataStorage dataStorage) throws IOException;
}
