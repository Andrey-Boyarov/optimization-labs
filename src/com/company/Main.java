package com.company;

import com.company.Labs.Lab1;
import com.company.Labs.Lab2;
import com.company.Labs.Lab3;
import com.company.Utils.DimPoint;

public class Main {

    public static void main(String[] args) {


        Lab1.dichotomy(x -> x * x + x ,-10,10,1e-8);
        Lab1.goldenSection(x -> x * x + x,-10,10,1e-8);
        Lab1.fibonacci(x -> x * x + x,-10,10,15);

        Lab2.dichotomy(point -> (point.get(0) - 2) * (point.get(0) - 2) + (point.get(1) - 2) * (point.get(1) - 2), new DimPoint(0d, 0d), new DimPoint(5d, 5d), 100, 1e-8);

        try {
            Lab2.perCoordDescend(point -> (point.get(0) - 5) * point.get(0) + (point.get(1) - 3) * point.get(1), new DimPoint(-13d, 22d));
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        Lab3.gradientDescend(point -> (point.get(0) - 2) * (point.get(0) - 2) + (point.get(1) - 2) * (point.get(1) - 2), new DimPoint(5d, 5d));
        Lab3.conjGradientDescend(point -> (point.get(0) - 2) * (point.get(0) - 2) + (point.get(1) - 2) * (point.get(1) - 2), new DimPoint(5d, 5d));
    }
}
