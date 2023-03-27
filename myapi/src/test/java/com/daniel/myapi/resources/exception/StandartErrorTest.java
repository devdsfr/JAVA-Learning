package com.daniel.myapi.resources.exception;

import org.junit.Test;
import static org.junit.Assert.*;

public class StandartErrorTest {

    @Test
    public void testConstructor() {
        Integer expectedStatus = 404;
        Long expectedTimestamp = System.currentTimeMillis();
        String expectedMessage = "Resource not found";
        StandartError error = new StandartError(expectedStatus, expectedTimestamp, expectedMessage);
        assertEquals(expectedStatus, error.getStatus());
        assertEquals(expectedTimestamp, error.getTimestamp());
        assertEquals(expectedMessage, error.getMessage());
    }
}