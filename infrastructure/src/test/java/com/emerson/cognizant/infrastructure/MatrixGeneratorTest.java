package com.emerson.cognizant.infrastructure;

import com.emerson.cognizant.domain.interfaces.MatrixRepository;
import com.emerson.cognizant.infrastructure.config.MatrixGeneratorConfig;
import com.emerson.cognizant.infrastructure.repository.MatrixManualGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class MatrixGeneratorTest {
    private final int minElementValue = 0;
    private final int maxElementValue = 15;
    private final int minLines = 5;
    private final int maxLines = 20;
    private final int minColumns = 5;
    private final int maxColumns = 20;

    @DisplayName("Should Assert True for valid values")
    @Test
    void testWithValidConfig() {
        var config = new MatrixGeneratorConfig(15, 5, 20, 5, 20);
        MatrixRepository matrixGenerator = new MatrixManualGenerator(config);

        var result = matrixGenerator.getMatrix();
        var lines = result.getLines();

        assertAll(
                () -> assertTrue(lines.length >= this.minLines && lines.length <= this.maxLines),
                () -> assertTrue(Arrays.stream(lines).allMatch((line) -> line.getElements().length >= this.minColumns && line.getElements().length <= this.maxColumns)),
                () -> assertTrue(() -> {
                    var res = true;

                    for (var line: lines) {
                        res &= Arrays.stream(line.getElements()).allMatch((e) -> e >= this.minElementValue && e <= this.maxElementValue);
                    }
                    return res;
                })
        );
    }

    @DisplayName("Should Assert False for invalid values (Line)")
    @Test
    void testWithInvalidLineConfig() {
        var config = new MatrixGeneratorConfig(15, 0, 60, 5, 20);
        MatrixRepository matrixGenerator = new MatrixManualGenerator(config);

        var result = matrixGenerator.getMatrix();
        var lines = result.getLines();

        assertAll(
                () -> assertFalse(lines.length >= this.minLines && lines.length <= this.maxLines),
                () -> assertTrue(Arrays.stream(lines).allMatch((line) -> line.getElements().length >= this.minColumns && line.getElements().length <= this.maxColumns)),
                () -> assertTrue(() -> {
                    var res = true;

                    for (var line: lines) {
                        res &= Arrays.stream(line.getElements()).allMatch((e) -> e >= this.minElementValue && e <= this.maxElementValue);
                    }
                    return res;
                })
        );
    }

    @DisplayName("Should Assert False for invalid values (Column)")
    @Test
    void testWithInvalidColumnConfig() {
        var config = new MatrixGeneratorConfig(15, 5, 20, 0, 60);
        MatrixRepository matrixGenerator = new MatrixManualGenerator(config);

        var result = matrixGenerator.getMatrix();
        var lines = result.getLines();

        assertAll(
                () -> assertTrue(lines.length >= this.minLines && lines.length <= this.maxLines),
                () -> assertFalse(Arrays.stream(lines).allMatch((line) -> line.getElements().length >= this.minColumns && line.getElements().length <= this.maxColumns)),
                () -> assertTrue(() -> {
                    var res = true;

                    for (var line: lines) {
                        res &= Arrays.stream(line.getElements()).allMatch((e) -> e >= this.minElementValue && e <= this.maxElementValue);
                    }
                    return res;
                })
        );
    }

    @DisplayName("Should Assert False for invalid values (Element Value)")
    @Test
    void testWithInvalidElementValueConfig() {
        var config = new MatrixGeneratorConfig(100, 5, 20, 5, 20);
        MatrixRepository matrixGenerator = new MatrixManualGenerator(config);

        var result = matrixGenerator.getMatrix();
        var lines = result.getLines();

        assertAll(
                () -> assertTrue(lines.length >= this.minLines && lines.length <= this.maxLines),
                () -> assertTrue(Arrays.stream(lines).allMatch((line) -> line.getElements().length >= this.minColumns && line.getElements().length <= this.maxColumns)),
                () -> assertFalse(() -> {
                    var res = true;

                    for (var line: lines) {
                        res &= Arrays.stream(line.getElements()).allMatch((e) -> e >= this.minElementValue && e <= this.maxElementValue);
                    }
                    return res;
                })
        );
    }
}
