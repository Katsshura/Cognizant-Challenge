package com.emerson.cognizant.domain;

import com.emerson.cognizant.domain.entities.MatrixLine;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MatrixLineTest {

    @DisplayName("Should return OK for valid values")
    @Test
    void testCountAppearanceTimes() {
        var lineElements = new int[] {
                1, 1, 1,
                2, 2, 2,
                3, 3, 0
        };

        var elementsToCompare = new int[] {
                1, 2, 3
        };

        var expectedResult = Map.of(
                1, 3,
                2, 3,
                3, 2
        );

        var matrixLine = new MatrixLine(lineElements);

        var result = matrixLine.countAppearanceTimeOnElements(elementsToCompare);

        assertEquals(expectedResult, result);

    }
}
