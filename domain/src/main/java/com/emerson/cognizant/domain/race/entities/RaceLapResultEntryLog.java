package com.emerson.cognizant.domain.race.entities;

import lombok.Getter;

import java.time.LocalTime;

@Getter
public class RaceLapResultEntryLog {
    private final LocalTime createdAt;
    private final int heroId;
    private final String heroName;
    private final int lap;
    private final LocalTime lapTime;
    private final double averageLapSpeed;

    public RaceLapResultEntryLog(LocalTime createdAt, int heroId, String heroName, int lap, LocalTime lapTime, double averageLapTime) {
        this.createdAt = createdAt;
        this.heroId = heroId;
        this.heroName = heroName;
        this.lap = lap;
        this.lapTime = lapTime;
        this.averageLapSpeed = averageLapTime;
    }
}
