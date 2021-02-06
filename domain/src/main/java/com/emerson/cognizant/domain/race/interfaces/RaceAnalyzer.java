package com.emerson.cognizant.domain.race.interfaces;

import com.emerson.cognizant.domain.race.entities.AverageHeroSpeed;
import com.emerson.cognizant.domain.race.entities.BestRaceLap;
import com.emerson.cognizant.domain.race.entities.HeroRaceResult;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface RaceAnalyzer {
    List<HeroRaceResult> getRaceResult() throws IOException, URISyntaxException;
    List<AverageHeroSpeed> getAverageSpeedPerHero() throws IOException, URISyntaxException;
    List<BestRaceLap> getHeroesBestLap() throws IOException, URISyntaxException;
    BestRaceLap getBestRaceLap() throws IOException, URISyntaxException;
}
