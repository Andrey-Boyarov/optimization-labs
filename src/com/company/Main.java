package com.company;

import com.company.Labs.Lab1;
import com.company.Labs.Lab2;
import com.company.Utils.DimPoint;

public class Main {

    public static void main(String[] args) {


        Lab1.dichotomy(x -> x * x + x ,-10,10,1e-8);
        Lab1.goldenSection(x -> x * x + x,-10,10,1e-8);
        Lab1.fibonacci(x -> x * x + x,-10,10,15);

        Lab2.dichotomy(point -> (point.get(0) - 2) * (point.get(0) - 2) + (point.get(1) + 3) * (point.get(1) + 3), new DimPoint(0d, -5d), new DimPoint(5d, 5d), 10, 1e-8);
    }
}
