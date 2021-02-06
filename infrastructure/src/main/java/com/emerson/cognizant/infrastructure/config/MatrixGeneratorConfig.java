package com.emerson.cognizant.infrastructure.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class MatrixGeneratorConfig {
    private final int maxElementsRangeBound;
    private final int minLinesRangeBound;
    private final int maxLinesRangeBound;
    private final int minColumnsRangeBound;
    private final int maxColumnsRangeBound;

    public MatrixGeneratorConfig(
            @Value("${spring.matrix.generator.config.maxElementsRangeBound}") int maxElementsRangeBound,
            @Value("${spring.matrix.generator.config.minLinesRangeBound}") int minLinesRangeBound,
            @Value("${spring.matrix.generator.config.maxLinesRangeBound}") int maxLinesRangeBound,
            @Value("${spring.matrix.generator.config.minColumnsRangeBound}") int minColumnsRangeBound,
            @Value("${spring.matrix.generator.config.maxColumnsRangeBound}") int maxColumnsRangeBound)
    {
        this.maxElementsRangeBound = maxElementsRangeBound;
        this.minLinesRangeBound = minLinesRangeBound;
        this.maxLinesRangeBound = maxLinesRangeBound;
        this.maxColumnsRangeBound = maxColumnsRangeBound;
        this.minColumnsRangeBound = minColumnsRangeBound;
    }
}
