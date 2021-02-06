package com.emerson.cognizant.infrastructure.repository;

import com.emerson.cognizant.domain.race.entities.RaceLapResultEntryLog;
import com.emerson.cognizant.domain.race.interfaces.RaceLogsRepository;
import com.opencsv.CSVReader;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;


import java.io.File;
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
        var inputStream = getClass().getClassLoader().getResourceAsStream("logs.csv");
        var tempFile = File.createTempFile("logs", ".csv");
        FileUtils.copyInputStreamToFile(inputStream, tempFile);


        var fileReader = new FileReader(tempFile);
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
