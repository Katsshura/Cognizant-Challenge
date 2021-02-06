package com.emerson.cognizant.domain.race.entities;

import lombok.Getter;

@Getter
public class AverageHeroSpeed {
    private final String heroName;
    private final double averageSpeed;

    public AverageHeroSpeed(String heroName, double averageSpeed) {
        this.heroName = heroName;
        this.averageSpeed = averageSpeed;
    }
}
