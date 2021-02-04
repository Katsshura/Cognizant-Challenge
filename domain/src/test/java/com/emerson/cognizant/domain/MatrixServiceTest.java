package com.emerson.cognizant.domain;

import com.emerson.cognizant.domain.entities.Matrix;
import com.emerson.cognizant.domain.entities.MatrixLine;
import com.emerson.cognizant.domain.interfaces.MatrixComparer;
import com.emerson.cognizant.domain.interfaces.MatrixRepository;
import com.emerson.cognizant.domain.services.MatrixService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MatrixServiceTest {

    @DisplayName("Should return OK for valid values")
    @Test
    void testMatrixComparerWithManualImplementation() throws ExecutionException, InterruptedException {
        MatrixRepository repository = () -> {
            var matrix = new Matrix(new MatrixLine[] {
                    new MatrixLine(new int[] { 1, 1, 1 }),
                    new MatrixLine(new int[] { 2, 2, 2 }),
                    new MatrixLine(new int[] { 3, 1, 3 }),
                    new MatrixLine(new int[] { 0, 4, 5 }),
            }, Executors.newCachedThreadPool());
            return matrix;
        };

        var elementsToCompare = new int[] {
                0, 1, 2, 3, 4, 5, 6
        };

        var expectedResult = Map.of(
                0, 1,
                1, 4,
                2, 3,
                3, 2,
                4, 1,
                5, 1,
                6, 0
        );

        MatrixComparer matrixComparer = new MatrixService(repository);

        var result = matrixComparer.getCountValuesForAppearanceOnMatrix(elementsToCompare);
        assertEquals(expectedResult, result);
    }
}
