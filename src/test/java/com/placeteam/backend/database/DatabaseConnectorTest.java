package com.placeteam.backend.database;

import com.placeteam.backend.database.impl.SQLiteDriver;
import com.placeteam.backend.model.Karte;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DatabaseConnectorTest {

    DatabaseConnector databaseConnector;
    @Before
    public void setUp() throws Exception {
        databaseConnector = new SQLiteDriver();
        databaseConnector.connect();
        databaseConnector.createTables(DefaultTables.getTables());
    }

    @Test
    @Order(1)
    public void testSetPixel() throws Exception {
        for (int i = 0; i < 10; i++) {
            for (int x = 0; x < 100; x++) {
                for (int y = 0; y < 100; y++) {
                    String color = String.format("#%06x", (int) (Math.random() * 0x1000000));
                    databaseConnector.setPixel("test", x, y, color);
                }
            }
        }
    }

    @Test
    @Order(2)
    public void testGetKarte() throws Exception {
        Karte karte = databaseConnector.getKarte();
        String[][] canvas = karte.getCanvasOfColour();
        for (int i = 0; i < 1000; i++) {
            assertEquals(canvas[i].length, 1000);
        }
    }

    @AfterAll
    public void cleanup() throws Exception {
        databaseConnector.close();
    }
}
