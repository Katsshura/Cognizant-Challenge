package com.emerson.cognizant.infrastructure.repository;

import com.emerson.cognizant.domain.matrix.entities.Matrix;
import com.emerson.cognizant.domain.matrix.entities.MatrixLine;
import com.emerson.cognizant.domain.matrix.interfaces.MatrixRepository;
import com.emerson.cognizant.infrastructure.config.MatrixGeneratorConfig;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.Executors;

@Component
public class MatrixManualGenerator implements MatrixRepository {
    private final MatrixGeneratorConfig config;
    private final Random random;

    public MatrixManualGenerator(MatrixGeneratorConfig config) {
        this.config = config;
        this.random = new Random();
    }

    @Override
    public Matrix getMatrix() {
        var numberOfColumns = this.getRandomColumnsNumber();
        var numbersOfLines = this.getRandomLinesNumber();
        var matrixLines = this.generateMatrixLines(numbersOfLines, numberOfColumns);
        return new Matrix(matrixLines, Executors.newCachedThreadPool());
    }

    private MatrixLine[] generateMatrixLines(int numbersOfLines, int numberOfColumns) {
        var lines = new MatrixLine[numbersOfLines];

        for (int i = 0; i < numbersOfLines; i++) {
            var res = this.generateRandomMatrixLine(numberOfColumns);
            lines[i] = res;
        }

        return lines;
    }

    private MatrixLine generateRandomMatrixLine(int numberOfColumns) {
        var elements = new int[numberOfColumns];

        for (int i = 0; i < numberOfColumns; i++) {
            elements[i] = this.getRandomElementNumber();
        }

        return new MatrixLine(elements);
    }

    private int getRandomColumnsNumber() {
        return this.random.nextInt(
                this.config.getMaxColumnsRangeBound() - this.config.getMinColumnsRangeBound()
        ) + this.config.getMinColumnsRangeBound();
    }

    private int getRandomLinesNumber() {
        return this.random.nextInt(
                this.config.getMaxLinesRangeBound() - this.config.getMinLinesRangeBound()
        ) + this.config.getMinLinesRangeBound();
    }

    private int getRandomElementNumber() {
        return this.random.nextInt(this.config.getMaxElementsRangeBound()) + 1;
    }
}
