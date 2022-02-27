package com.company.Labs;

import com.company.Utils.Utils;

import java.util.function.Function;

public class Lab1 {

    private static final Double FI1 = (1 + Math.sqrt(5)) / 2;

    /**
     * Dichotomy method
     *
     * @return Double
     */
    public static Double dichotomy(
            Function<Double, Double> f,
            double x0,
            double x1,
            double _eps) {
        System.out.println("Dichotomy method started");

        double y1, y2, delta;

        int iteration = 1;

        do {
            ++iteration;

            delta = (x0 + x1) / 2;

            y1 = f.apply(delta - _eps);
            y2 = f.apply(delta + _eps);

            if (y1 < y2) x1 = delta - _eps;
            else x0 = delta + _eps;

        } while (!(Math.abs(x1 - x0) < 2 * _eps));

        System.out.println("Made in " + iteration + " iterations");
        System.out.println("Result is: " + delta + "\n");

        return delta;
    }

    /**
     * Golden-section search
     *
     * @return Double
     */
    public static double goldenSection(
            Function<Double, Double> f,
            double x0,
            double x1,
            double _eps) {

        System.out.println("Golden-section search started");

        double y1, y2, delta;
        int iteration = 1;

        do {
            ++iteration;

            delta = (x1 - x0) / FI1;

            y1 = f.apply(x0 + delta);
            y2 = f.apply(x1 - delta);

            if (y1 > y2) x1 = x0 + delta;
            else x0 = x1 - delta;

        } while (!(Math.abs(x1 - x0) < 2 * _eps));

        System.out.println("Made in " + iteration + " iterations");
        System.out.println("Result is: " + (x1 + x0) / 2 + "\n");

        return (x0 + x1) / 2;
    }

    /**
     * Fibonacci search
     *
     * @return Double
     */
    public static Double fibonacci(
            Function<Double, Double> f,
            double x0,
            double x1,
            int N) {

        if (N < 2) {
            System.out.println("Incorrect N input, method stopped");
            return Double.NaN;
        }

        System.out.println("Fibonacci search started");

        double y1, y2;
        int iteration = 1;
        double firstArg = x0 + (x1 - x0) * ((double) Utils.getFibNumByIndex(N-2) / Utils.getFibNumByIndex(N));
        double secondArg = x0 + (x1 - x0) * ((double) Utils.getFibNumByIndex(N - 1) / Utils.getFibNumByIndex(N));

        for (; iteration < N - 2; ++iteration) {

            y1 = f.apply(firstArg);
            y2 = f.apply(secondArg);

            if (y2 > y1) {
                x1 = secondArg;
                secondArg = firstArg;
                firstArg = x0 + (x1 - x0) * ((double) Utils.getFibNumByIndex(N - iteration - 2) / Utils.getFibNumByIndex(N - iteration));
                }
            else {
                x0 = firstArg;
                firstArg = secondArg;
                secondArg = x0 + (x1 - x0) * ((double) Utils.getFibNumByIndex(N - iteration - 1) / Utils.getFibNumByIndex(N - iteration));
            }
        }

        System.out.println("Made in " + iteration + " iterations");
        System.out.println("Result is: " + (x1 + x0) / 2 + "\n");

        return (x0 + x1) / 2;
    }
}