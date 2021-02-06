package com.emerson.cognizant.domain.services;

import com.emerson.cognizant.domain.race.entities.AverageHeroSpeed;
import com.emerson.cognizant.domain.race.entities.BestRaceLap;
import com.emerson.cognizant.domain.race.entities.HeroRaceResult;
import com.emerson.cognizant.domain.race.entities.RaceLapResultEntryLog;
import com.emerson.cognizant.domain.race.interfaces.RaceAnalyzer;
import com.emerson.cognizant.domain.race.interfaces.RaceLogsRepository;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RaceService  implements RaceAnalyzer {

    private final RaceLogsRepository raceLogsRepository;

    public RaceService(RaceLogsRepository raceLogsRepository) {
        this.raceLogsRepository = raceLogsRepository;
    }

    @Override
    public List<HeroRaceResult> getRaceResult() throws IOException, URISyntaxException {
        var logs = raceLogsRepository.getLog();
        var result = this.getMappedRaceResult(logs, HeroRaceResult.class);
        return result;
    }

    @Override
    public List<AverageHeroSpeed> getAverageSpeedPerHero() throws IOException, URISyntaxException {
        var logs = raceLogsRepository.getLog();
        var result = this.getMappedRaceResult(logs, AverageHeroSpeed.class);
        return result;
    }

    @Override
    public List<BestRaceLap> getHeroesBestLap() throws IOException, URISyntaxException {
        var logs = raceLogsRepository.getLog();
        var result = this.getMappedRaceResult(logs, BestRaceLap.class);
        return result;
    }

    @Override
    public BestRaceLap getBestRaceLap() throws IOException, URISyntaxException {
        var logs = raceLogsRepository.getLog();
        var bestRaceLap = this.getBestLap(logs);
        return bestRaceLap;
    }

    private <T> List<T> getMappedRaceResult(List<RaceLapResultEntryLog> list, Class<T> returnType) {
        var uniqueIds = getUniqueHeroIds(list);
        var results = new ArrayList<T>();
        for (var id: uniqueIds) {

            if (returnType.equals(HeroRaceResult.class)) {
                results.add((T) mapHeroRaceResult(list, id));
            }else if(returnType.equals(AverageHeroSpeed.class)){
                results.add((T) mapHeroAverageSpeed(list, id));
            }else if(returnType.equals(BestRaceLap.class)) {
                results.add((T) getHeroesBestLaps(list, id));
            }
        }
        return results;
    }

    private HeroRaceResult mapHeroRaceResult(List<RaceLapResultEntryLog> list, int id) {
        var heroLogs = getHeroLogs(list, id);

        var position = 0;
        var index = 0;

        var totalRaceTime = heroLogs.stream().mapToLong((h) -> h.getLapTime().toNanoOfDay()).sum();
        var lap = heroLogs.stream().mapToInt(RaceLapResultEntryLog::getLap).max().getAsInt();
        var heroName = heroLogs.stream().findFirst().get().getHeroName();

        for ( var raceLapLog : list ) {
            index++;
            if(raceLapLog.getLap() == 4 && raceLapLog.getHeroId() == id) {
                position++;
                break;
            }else if (raceLapLog.getLap() == 4){
                position++;
            }else if (index == list.size() - 1) {
                position++;
            }
        }

        return new HeroRaceResult(position, id, heroName, lap, LocalTime.ofNanoOfDay(totalRaceTime));
    }

    private AverageHeroSpeed mapHeroAverageSpeed(List<RaceLapResultEntryLog> list, int id) {
        var heroLogs = this.getHeroLogs(list, id);

        var averageSpeed = heroLogs.stream()
                .mapToDouble(RaceLapResultEntryLog::getAverageLapSpeed).average().getAsDouble();

        var heroName = heroLogs.stream()
                .findFirst().get().getHeroName();

        return new AverageHeroSpeed(heroName, averageSpeed);
    }

    private BestRaceLap getHeroesBestLaps(List<RaceLapResultEntryLog> list, int id) {
        var heroLogs = this.getHeroLogs(list, id);
        return this.getBestLap(heroLogs);
    }

    private BestRaceLap getBestLap(List<RaceLapResultEntryLog> list) {
        var bestLapTime = list.stream().mapToLong((h) -> h.getLapTime().toNanoOfDay()).min().getAsLong();
        var lapInfo = list.stream()
                .filter(e -> e.getLapTime().toNanoOfDay() == bestLapTime)
                .findFirst().get();

        return new BestRaceLap(lapInfo.getLap(), lapInfo.getHeroName(), lapInfo.getLapTime());
    }

    private List<Integer> getUniqueHeroIds(List<RaceLapResultEntryLog> list) {
        return list.stream()
                .map(RaceLapResultEntryLog::getHeroId)
                .distinct()
                .collect(Collectors.toList());
    }

    private List<RaceLapResultEntryLog> getHeroLogs(List<RaceLapResultEntryLog> list, int id) {
        return list.stream()
                .filter(raceLapResultEntryLog -> raceLapResultEntryLog.getHeroId() == id)
                .collect(Collectors.toList());
    }
}
