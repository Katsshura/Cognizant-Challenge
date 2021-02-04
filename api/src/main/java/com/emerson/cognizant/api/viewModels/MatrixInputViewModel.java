package com.emerson.cognizant.api.viewModels;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.util.Arrays;

@Getter
public class MatrixInputViewModel {
    @ApiModelProperty(value = "Array with all the values to compare to matrix. Must be between 0 and 15!")
    private int[] values;

    public boolean isValid() {
        return Arrays.stream(values).allMatch((e) -> e >= 0 && e <= 15);
    }
}
