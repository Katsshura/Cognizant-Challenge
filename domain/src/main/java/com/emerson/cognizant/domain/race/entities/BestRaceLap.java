package com.emerson.cognizant.domain.race.entities;

import lombok.Getter;

import java.time.LocalTime;

@Getter
public class BestRaceLap {
    private final int lap;
    private final String heroName;
    private final LocalTime lapTime;

    public BestRaceLap(int lap, String heroName, LocalTime lapTime) {
        this.lap = lap;
        this.heroName = heroName;
        this.lapTime = lapTime;
    }
}
