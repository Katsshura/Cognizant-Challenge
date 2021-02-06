package com.emerson.cognizant.infrastructure.repository;

import com.emerson.cognizant.domain.race.entities.RaceLapResultEntryLog;
import com.emerson.cognizant.domain.race.interfaces.RaceLogsRepository;
import com.opencsv.CSVReader;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class RaceLogCsvReader implements RaceLogsRepository {

    @Override
    public List<RaceLapResultEntryLog> getLog() throws IOException, URISyntaxException {

        var raceResultList = new ArrayList<RaceLapResultEntryLog>();
        var urlPath = getClass().getClassLoader().getResource("logs.csv");
        var file = Paths.get(urlPath.toURI()).toFile();
        String absolutePath = file.getAbsolutePath();

        var fileReader = new FileReader(absolutePath);
        var csvReader = new CSVReader(fileReader, ';');
        String[] nextRecord;

        while ((nextRecord = csvReader.readNext()) != null) {
            var res = this.mapToEntity(nextRecord);
            raceResultList.add(res);
        }

        return raceResultList;
    }

    private RaceLapResultEntryLog mapToEntity(String[] record) {

        var createdAt = LocalTime.parse(record[0]);
        var heroInfo = record[1].split("–");
        var heroId = Integer.parseInt(heroInfo[0]);
        var heroName = heroInfo[1];
        var lap = Integer.parseInt(record[2]);
        var lapTime = LocalTime.parse("00:0" + record[3]);
        var averageLapSpeed = Double.parseDouble(record[4].replace(',', '.'));

        return new RaceLapResultEntryLog(createdAt, heroId, heroName, lap, lapTime, averageLapSpeed);
    }
}
