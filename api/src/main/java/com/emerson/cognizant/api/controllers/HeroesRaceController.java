package com.emerson.cognizant.api.controllers;

import com.emerson.cognizant.domain.race.interfaces.RaceAnalyzer;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;

@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Request processed with success"),
        @ApiResponse(code = 500, message = "Something went wrong in ours servers"),
})
@RestController
@RequestMapping("/heroes/race")
public class HeroesRaceController {

    private final RaceAnalyzer raceAnalyzer;

    public HeroesRaceController(RaceAnalyzer raceAnalyzer) {
        this.raceAnalyzer = raceAnalyzer;
    }

    @ApiOperation(value = "Returns a mapped value with the race result for each hero")
    @RequestMapping(method = RequestMethod.GET, produces="application/json", path = "/result")
    private ResponseEntity getRaceResult() {
        try {
            var result = raceAnalyzer.getRaceResult();
            return new ResponseEntity(result, HttpStatus.OK);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Returns the average speed on the race for each hero")
    @RequestMapping(method = RequestMethod.GET, produces="application/json", path = "/average/speed")
    private ResponseEntity getAverageSpeedPerHero() {
        try {
            var result = raceAnalyzer.getAverageSpeedPerHero();
            return new ResponseEntity(result, HttpStatus.OK);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Returns the best lap for each hero in the race")
    @RequestMapping(method = RequestMethod.GET, produces="application/json", path = "/individual/bestLap")
    private ResponseEntity getHeroesBestLap() {
        try {
            var result = raceAnalyzer.getHeroesBestLap();
            return new ResponseEntity(result, HttpStatus.OK);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Returns the best lap in the entire race")
    @RequestMapping(method = RequestMethod.GET, produces="application/json", path = "/bestLap")
    private ResponseEntity getBestRaceLap() {
        try {
            var result = raceAnalyzer.getBestRaceLap();
            return new ResponseEntity(result, HttpStatus.OK);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
