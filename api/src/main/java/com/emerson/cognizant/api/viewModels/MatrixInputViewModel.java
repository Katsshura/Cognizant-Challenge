package com.emerson.cognizant.api.viewModels;

import lombok.Getter;

import java.util.Arrays;

@Getter
public class MatrixInputViewModel {
    private int[] values;

    public boolean isValid() {
        return Arrays.stream(values).allMatch((e) -> e >= 0 && e <= 15);
    }
}
