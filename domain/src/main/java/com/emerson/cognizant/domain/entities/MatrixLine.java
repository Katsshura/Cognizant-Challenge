package com.emerson.cognizant.domain.entities;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class MatrixLine {
    private final int[] elements;

    public MatrixLine(int[] elements) {
        this.elements = elements;
    }

    public Map<Integer, Integer> countAppearanceTimeOnElements(int[] comparative) {
        var record = new HashMap<Integer, Integer>();

        for (int element: comparative ) {
            var result = this.countIndividualAppearanceTime(element);
            record.put(element, result);
        }

        return record;
    }

    private int countIndividualAppearanceTime(int comparative) {
        var count = 0;

        for (int element: elements) {
            if (element == comparative) {
                count++;
            }
        }

        return count;
    }
}
