package com.emerson.cognizant.domain.services;

import com.emerson.cognizant.domain.interfaces.MatrixComparer;
import com.emerson.cognizant.domain.interfaces.MatrixRepository;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ExecutionException;

@Component
public class MatrixService implements MatrixComparer {

    private final MatrixRepository matrixRepository;

    public MatrixService(MatrixRepository matrixRepository) {
        this.matrixRepository = matrixRepository;
    }

    @Override
    public Map<Integer, Integer> getCountValuesForAppearanceOnMatrix(int[] entries) throws ExecutionException, InterruptedException {
        var matrix = matrixRepository.getMatrix();
        var result = matrix.countEntryAppearanceOnMatrix(entries);
        return result;
    }
}
