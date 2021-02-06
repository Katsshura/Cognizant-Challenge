package com.emerson.cognizant.domain;

import com.emerson.cognizant.domain.matrix.entities.Matrix;
import com.emerson.cognizant.domain.matrix.entities.MatrixLine;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MatrixTest {

    @DisplayName("Should return OK for valid values")
    @Test
    void testCountAppearanceTimes() throws ExecutionException, InterruptedException {
        var matrix = new Matrix(new MatrixLine[] {
                new MatrixLine(new int[] { 1, 1, 1 }),
                new MatrixLine(new int[] { 2, 2, 2 }),
                new MatrixLine(new int[] { 3, 1, 3 }),
                new MatrixLine(new int[] { 0, 4, 5 }),
        }, Executors.newCachedThreadPool());

        var elementsToCompare = new int[] {
                0, 1, 2, 3, 4, 5
        };

        var expectedResult = Map.of(
                0, 1,
                1, 4,
                2, 3,
                3, 2,
                4, 1,
                5, 1
        );

        var result = matrix.countEntryAppearanceOnMatrix(elementsToCompare);
        assertEquals(expectedResult, result);
    }
}
