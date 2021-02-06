package com.emerson.cognizant.domain.race.interfaces;

import com.emerson.cognizant.domain.race.entities.RaceLapResultEntryLog;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface RaceLogsRepository {
    List<RaceLapResultEntryLog> getLog() throws IOException, URISyntaxException;
}
