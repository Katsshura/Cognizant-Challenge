package com.emerson.cognizant.infrastructure;

import com.emerson.cognizant.infrastructure.repository.RaceLogCsvReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RaceLogCsvReaderTest {

    @DisplayName("Should have 23 entries")
    @Test
    void testArrayEntry() throws IOException, URISyntaxException {
        var raceLog = new RaceLogCsvReader();
        var result = raceLog.getLog();
        assertEquals(23, result.size());
    }
}
