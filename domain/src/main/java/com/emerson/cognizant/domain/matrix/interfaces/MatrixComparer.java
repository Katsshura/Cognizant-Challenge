package com.emerson.cognizant.domain.matrix.interfaces;

import java.util.Map;
import java.util.concurrent.ExecutionException;

public interface MatrixComparer {
    Map<Integer, Integer> getCountValuesForAppearanceOnMatrix(int[] entries) throws ExecutionException, InterruptedException;
}
