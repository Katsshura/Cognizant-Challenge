package com.emerson.cognizant.domain.matrix.entities;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

@Getter
public class Matrix {
    private final MatrixLine[] lines;
    private final ExecutorService executor;

    public Matrix(MatrixLine[] lines, ExecutorService executor) {
        this.lines = lines;
        this.executor = executor;
    }

    public Map<Integer, Integer> countEntryAppearanceOnMatrix(int[] entry) throws InterruptedException, ExecutionException {
        var tasks = new ArrayList<Callable<Map<Integer, Integer>>>();
        var result = new HashMap<Integer, Integer>();

        for (MatrixLine line : lines) {
            tasks.add(() -> line.countAppearanceTimeOnElements(entry));
        }

        var results = this.executor.invokeAll(tasks, 2, TimeUnit.MINUTES);

        for (var futureResult: results) {
            var res =  futureResult.get();
            res.forEach((k, v) -> this.mergeValuesIntoHashMap(k, v, result));
        }

        return result;
    }

    private void mergeValuesIntoHashMap(int key, int value, HashMap<Integer, Integer> map) {
        if(map.containsKey(key)) {
            var newValue = map.get(key) + value;
            map.replace(key, newValue);
        }else{
            map.put(key, value);
        }
    }
}
