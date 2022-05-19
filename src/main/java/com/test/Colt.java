package com.test;

import cern.colt.matrix.tdouble.DoubleFactory2D;
import cern.colt.matrix.tdouble.DoubleMatrix2D;
import cern.colt.matrix.tdouble.algo.DenseDoubleAlgebra;
import org.jblas.DoubleMatrix;
import org.ojalgo.matrix.Primitive64Matrix;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

/*      OutputTimeUnit:This is the default time unit in which the results will be listed in. It uses Java’s
        TimeUnit class and hence can specify any of the values from the TimeUnit enum.*/
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class Colt {

    @Benchmark
    /*fork:The JMH benchmark is run for a number of trials. Trials are also called as forks.*/
    @Fork(value = 2)


/*    Measurement:It is used to set the default measurement parameters for the benchmark.
    It allows to specify the number of iterations and the time for which each is to be executed.
    Example: @Measurement(iterations = 3, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
    We here specify 3 iterations each to be run for 1000 millisecond (1 second). The default time unit is seconds.*/

    @Measurement(iterations = 5, time = 1)
    /*Warmup:Warmup parameters are similar to that of Measurement except that it applies for the warmup runs.
            Example: @Warmup(iterations = 3, time = 2)
    This dedicates 3 full iterations as warmup each running for 2 seconds.*/
    @Warmup(iterations = 2, time = 1)
    public DoubleMatrix2D coltMatrixMultiplication(BigMatrixProvider matrixProvider) {
        DoubleFactory2D doubleFactory2D = DoubleFactory2D.dense;

        DoubleMatrix2D firstMatrix = doubleFactory2D.make(matrixProvider.getFirstMatrix());
        DoubleMatrix2D secondMatrix = doubleFactory2D.make(matrixProvider.getSecondMatrix());

        DenseDoubleAlgebra algebra = new DenseDoubleAlgebra();
        DoubleMatrix2D productMatrix = algebra.mult(firstMatrix, secondMatrix);
        return productMatrix;
    }


    @Benchmark
    @Fork(value = 2)
    @Measurement(iterations = 5, time = 1)
    @Warmup(iterations = 2, time = 1)
    public DoubleMatrix jblasMatrixMultiplication(BigMatrixProvider matrixProvider){
        DoubleMatrix M1 = new DoubleMatrix( matrixProvider.getFirstMatrix());
        DoubleMatrix a1 = new DoubleMatrix( matrixProvider.getSecondMatrix());

        DoubleMatrix c1= M1.mmul(a1);
        return c1;

    }

    @Benchmark
    @Fork(value = 2)
    @Measurement(iterations = 5, time = 1)
    @Warmup(iterations = 2, time = 1)
    public Primitive64Matrix ojAlgoMatrixMultiplication(BigMatrixProvider matrixProvider){
        Primitive64Matrix m= Primitive64Matrix.FACTORY.rows(matrixProvider.getFirstMatrix());
        Primitive64Matrix n= Primitive64Matrix.FACTORY.rows(matrixProvider.getSecondMatrix());
        return m.multiply(n);
    }




}

