package com.test;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.util.Random;

@State(Scope.Benchmark)
public class BigMatrixProvider {
    private double[][] firstMatrix;
    private double[][] secondMatrix;


    public BigMatrixProvider() {
        int  matrixSize = 5000;
        firstMatrix = createMatrix(matrixSize);
        secondMatrix = createMatrix(matrixSize);
    }

    private double[][] createMatrix(int matrixSize) {
        Random random = new Random();

        double[][] result = new double[matrixSize][matrixSize];
        for (int row = 0; row < result.length; row++) {
            for (int col = 0; col < result[row].length; col++) {
                result[row][col] = random.nextDouble();
            }
        }
        return result;
    }

    public double[][] getFirstMatrix() {
        return firstMatrix;
    }

    public double[][] getSecondMatrix() {
        return secondMatrix;
    }
}