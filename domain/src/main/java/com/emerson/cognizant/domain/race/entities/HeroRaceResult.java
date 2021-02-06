package com.emerson.cognizant.domain.race.entities;

import lombok.Getter;

import java.time.LocalTime;

@Getter
public class HeroRaceResult {
    private final int arrivalPosition;
    private final int heroCode;
    private final String heroName;
    private final int completedLaps;
    private final LocalTime totalRaceTime;

    public HeroRaceResult(int arrivalPosition, int heroCode, String heroName, int completedLaps, LocalTime totalRaceTime) {
        this.arrivalPosition = arrivalPosition;
        this.heroCode = heroCode;
        this.heroName = heroName;
        this.completedLaps = completedLaps;
        this.totalRaceTime = totalRaceTime;
    }
}
