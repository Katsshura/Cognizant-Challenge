package com.emerson.cognizant.domain;

import com.emerson.cognizant.domain.race.entities.AverageHeroSpeed;
import com.emerson.cognizant.domain.race.entities.BestRaceLap;
import com.emerson.cognizant.domain.race.entities.HeroRaceResult;
import com.emerson.cognizant.domain.race.entities.RaceLapResultEntryLog;
import com.emerson.cognizant.domain.race.interfaces.RaceAnalyzer;
import com.emerson.cognizant.domain.race.interfaces.RaceLogsRepository;
import com.emerson.cognizant.domain.services.RaceService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RaceServiceTest {

    @DisplayName("Should return valid HeroRaceResult List - getRaceResult()")
    @Test
    void testGetRaceResult() throws IOException, URISyntaxException {
        RaceLogsRepository repository = this::getRaceLapResultEntryLogs;

        var expectedResult = Arrays.asList(
                new HeroRaceResult(1, 38, "Superman", 4, LocalTime.parse("00:04:11.578")),
                new HeroRaceResult(2, 33, "Flash", 4, LocalTime.parse("00:04:16.080")),
                new HeroRaceResult(3, 2, "Mercúrio", 4, LocalTime.parse("00:04:15.153")),
                new HeroRaceResult(4, 23, "Sonic", 4, LocalTime.parse("00:04:17.722")),
                new HeroRaceResult(5, 15, "PAPALÉGUA", 4, LocalTime.parse("00:04:54.221")),
                new HeroRaceResult(6, 11, "GATOAJATO", 3, LocalTime.parse("00:06:27.276"))
        );

        RaceAnalyzer analyzer = new RaceService(repository);
        var result = analyzer.getRaceResult();

        assertAll(() -> {
            assertEquals(expectedResult.get(0).getArrivalPosition(), result.get(0).getArrivalPosition());
            assertEquals(expectedResult.get(1).getArrivalPosition(), result.get(1).getArrivalPosition());
            assertEquals(expectedResult.get(2).getArrivalPosition(), result.get(2).getArrivalPosition());
            assertEquals(expectedResult.get(3).getArrivalPosition(), result.get(3).getArrivalPosition());
            assertEquals(expectedResult.get(4).getArrivalPosition(), result.get(4).getArrivalPosition());
            assertEquals(expectedResult.get(5).getArrivalPosition(), result.get(5).getArrivalPosition());

            assertEquals(expectedResult.get(0).getCompletedLaps(), result.get(0).getCompletedLaps());
            assertEquals(expectedResult.get(1).getCompletedLaps(), result.get(1).getCompletedLaps());
            assertEquals(expectedResult.get(2).getCompletedLaps(), result.get(2).getCompletedLaps());
            assertEquals(expectedResult.get(3).getCompletedLaps(), result.get(3).getCompletedLaps());
            assertEquals(expectedResult.get(4).getCompletedLaps(), result.get(4).getCompletedLaps());
            assertEquals(expectedResult.get(5).getCompletedLaps(), result.get(5).getCompletedLaps());


            assertEquals(expectedResult.get(0).getTotalRaceTime(), result.get(0).getTotalRaceTime());
            assertEquals(expectedResult.get(1).getTotalRaceTime(), result.get(1).getTotalRaceTime());
            assertEquals(expectedResult.get(2).getTotalRaceTime(), result.get(2).getTotalRaceTime());
            assertEquals(expectedResult.get(3).getTotalRaceTime(), result.get(3).getTotalRaceTime());
            assertEquals(expectedResult.get(4).getTotalRaceTime(), result.get(4).getTotalRaceTime());
            assertEquals(expectedResult.get(5).getTotalRaceTime(), result.get(5).getTotalRaceTime());
        });
    }


    @DisplayName("Should return valid HeroRaceResult List - getAverageSpeedPerHero()")
    @Test
    void testGetAverageHeroResult() throws IOException, URISyntaxException {
        RaceLogsRepository repository = this::getRaceLapResultEntryLogs;

        var expectedResult = Arrays.asList(
                new AverageHeroSpeed("Superman", 44.24575),
                new AverageHeroSpeed("Flash", 43.467999999999996),
                new AverageHeroSpeed("Mercúrio", 43.627250000000004),
                new AverageHeroSpeed("Sonic", 43.19125),
                new AverageHeroSpeed("PAPALÉGUA", 38.06625),
                new AverageHeroSpeed("GATOAJATO", 25.745666666666665)
        );

        RaceAnalyzer analyzer = new RaceService(repository);
        var result = analyzer.getAverageSpeedPerHero();

        assertAll(() -> {
            assertEquals(expectedResult.get(0).getHeroName(), result.get(0).getHeroName());
            assertEquals(expectedResult.get(1).getHeroName(), result.get(1).getHeroName());
            assertEquals(expectedResult.get(2).getHeroName(), result.get(2).getHeroName());
            assertEquals(expectedResult.get(3).getHeroName(), result.get(3).getHeroName());
            assertEquals(expectedResult.get(4).getHeroName(), result.get(4).getHeroName());
            assertEquals(expectedResult.get(5).getHeroName(), result.get(5).getHeroName());


            assertEquals(expectedResult.get(0).getAverageSpeed(), result.get(0).getAverageSpeed());
            assertEquals(expectedResult.get(1).getAverageSpeed(), result.get(1).getAverageSpeed());
            assertEquals(expectedResult.get(2).getAverageSpeed(), result.get(2).getAverageSpeed());
            assertEquals(expectedResult.get(3).getAverageSpeed(), result.get(3).getAverageSpeed());
            assertEquals(expectedResult.get(4).getAverageSpeed(), result.get(4).getAverageSpeed());
            assertEquals(expectedResult.get(5).getAverageSpeed(), result.get(5).getAverageSpeed());
        });
    }

    @DisplayName("Should return valid HeroRaceResult List - getBestRaceLap()")
    @Test
    void testGetRaceBestLap() throws IOException, URISyntaxException {
        RaceLogsRepository repository = this::getRaceLapResultEntryLogs;

        var expectedResult = new BestRaceLap(3, "Superman", LocalTime.parse("00:01:02.769"));
        RaceAnalyzer analyzer = new RaceService(repository);
        var result = analyzer.getBestRaceLap();

        assertAll(() -> {
            assertEquals(expectedResult.getHeroName(), result.getHeroName());
            assertEquals(expectedResult.getLap(), result.getLap());
            assertEquals(expectedResult.getLapTime(), result.getLapTime());
        });
    }

    @DisplayName("Should return valid HeroRaceResult List - getHeroesBestLap()")
    @Test
    void testGetHeroesRaceBestLap() throws IOException, URISyntaxException {
        RaceLogsRepository repository = this::getRaceLapResultEntryLogs;

        var expectedResult = Arrays.asList(
                new BestRaceLap(3, "Superman", LocalTime.parse("00:01:02.769")),
                new BestRaceLap(3, "Flash", LocalTime.parse("00:01:03.716")),
                new BestRaceLap(4, "Mercúrio", LocalTime.parse("00:01:03.076")),
                new BestRaceLap(4, "Sonic", LocalTime.parse("00:01:04.216")),
                new BestRaceLap(2, "PAPALÉGUA", LocalTime.parse("00:01:07.011")),
                new BestRaceLap(3, "GATOAJATO", LocalTime.parse("00:01:18.097"))
        );

        RaceAnalyzer analyzer = new RaceService(repository);
        var result = analyzer.getHeroesBestLap();

        assertAll(() -> {
            assertEquals(expectedResult.get(0).getHeroName(), result.get(0).getHeroName());
            assertEquals(expectedResult.get(1).getHeroName(), result.get(1).getHeroName());
            assertEquals(expectedResult.get(2).getHeroName(), result.get(2).getHeroName());
            assertEquals(expectedResult.get(3).getHeroName(), result.get(3).getHeroName());
            assertEquals(expectedResult.get(4).getHeroName(), result.get(4).getHeroName());
            assertEquals(expectedResult.get(5).getHeroName(), result.get(5).getHeroName());


            assertEquals(expectedResult.get(0).getLap(), result.get(0).getLap());
            assertEquals(expectedResult.get(1).getLap(), result.get(1).getLap());
            assertEquals(expectedResult.get(2).getLap(), result.get(2).getLap());
            assertEquals(expectedResult.get(3).getLap(), result.get(3).getLap());
            assertEquals(expectedResult.get(4).getLap(), result.get(4).getLap());
            assertEquals(expectedResult.get(5).getLap(), result.get(5).getLap());

            assertEquals(expectedResult.get(0).getLapTime(), result.get(0).getLapTime());
            assertEquals(expectedResult.get(1).getLapTime(), result.get(1).getLapTime());
            assertEquals(expectedResult.get(2).getLapTime(), result.get(2).getLapTime());
            assertEquals(expectedResult.get(3).getLapTime(), result.get(3).getLapTime());
            assertEquals(expectedResult.get(4).getLapTime(), result.get(4).getLapTime());
            assertEquals(expectedResult.get(5).getLapTime(), result.get(5).getLapTime());
        });
    }

    private List<RaceLapResultEntryLog> getRaceLapResultEntryLogs() {
        return Arrays.asList(
                new RaceLapResultEntryLog(LocalTime.parse("23:49:08.277"), 38, "Superman", 1, LocalTime.parse("00:01:02.852"), 44.275),
                new RaceLapResultEntryLog(LocalTime.parse("23:49:10.858"), 33, "Flash", 1, LocalTime.parse("00:01:04.352"), 43.243),
                new RaceLapResultEntryLog(LocalTime.parse("23:49:11.075"), 2, "Mercúrio", 1, LocalTime.parse("00:01:04.108"), 43.408),
                new RaceLapResultEntryLog(LocalTime.parse("23:49:12.667"), 23, "Sonic", 1, LocalTime.parse("00:01:04.414"), 43.202),
                new RaceLapResultEntryLog(LocalTime.parse("23:49:30.976"), 15, "PAPALÉGUA", 1, LocalTime.parse("00:01:18.456"), 35.47),
                new RaceLapResultEntryLog(LocalTime.parse("23:50:11.447"), 38, "Superman", 2, LocalTime.parse("00:01:03.170"), 44.053),
                new RaceLapResultEntryLog(LocalTime.parse("23:50:14.860"), 33, "Flash", 2, LocalTime.parse("00:01:04.002"), 43.48),
                new RaceLapResultEntryLog(LocalTime.parse("23:50:15.057"), 2, "Mercúrio", 2, LocalTime.parse("00:01:03.982"), 43.493),
                new RaceLapResultEntryLog(LocalTime.parse("23:50:17.472"), 23, "Sonic", 2, LocalTime.parse("00:01:04.805"), 42.941),
                new RaceLapResultEntryLog(LocalTime.parse("23:50:37.987"), 15, "PAPALÉGUA", 2, LocalTime.parse("00:01:07.011"), 41.528),
                new RaceLapResultEntryLog(LocalTime.parse("23:51:14.216"), 38, "Superman", 3, LocalTime.parse("00:01:02.769"), 44.334),
                new RaceLapResultEntryLog(LocalTime.parse("23:51:18.576"), 33, "Flash", 3, LocalTime.parse("00:01:03.716"), 43.675),
                new RaceLapResultEntryLog(LocalTime.parse("23:51:19.044"), 2, "Mercúrio", 3, LocalTime.parse("00:01:03.987"), 43.49),
                new RaceLapResultEntryLog(LocalTime.parse("23:51:21.759"), 23, "Sonic", 3, LocalTime.parse("00:01:04.287"), 43.287),
                new RaceLapResultEntryLog(LocalTime.parse("23:51:46.691"), 15, "PAPALÉGU", 3, LocalTime.parse("00:01:08.704"), 40.504),
                new RaceLapResultEntryLog(LocalTime.parse("23:52:01.796"), 11, "GATOAJATO", 1, LocalTime.parse("00:03:31.315"), 13.169),
                new RaceLapResultEntryLog(LocalTime.parse("23:52:17.003"), 38, "Superman", 4, LocalTime.parse("00:01:02.787"), 44.321),
                new RaceLapResultEntryLog(LocalTime.parse("23:52:22.586"), 33, "Flash", 4, LocalTime.parse("00:01:04.010"), 43.474),
                new RaceLapResultEntryLog(LocalTime.parse("23:52:22.120"), 2, "Mercúrio", 4, LocalTime.parse("00:01:03.076"), 44.118),
                new RaceLapResultEntryLog(LocalTime.parse("23:52:25.975"), 23, "Sonic", 4, LocalTime.parse("00:01:04.216"), 43.335),
                new RaceLapResultEntryLog(LocalTime.parse("23:53:06.741"), 15, "PAPALÉGUA", 4, LocalTime.parse("00:01:20.050"), 34.763),
                new RaceLapResultEntryLog(LocalTime.parse("23:53:39.660"), 11, "GATOAJATO", 2, LocalTime.parse("00:01:37.864"), 28.435),
                new RaceLapResultEntryLog(LocalTime.parse("23:54:57.757"), 11, "GATOAJATO", 3, LocalTime.parse("00:01:18.097"), 35.633)
        );
    }
}
