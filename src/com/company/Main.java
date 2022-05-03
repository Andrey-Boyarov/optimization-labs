package com.company;

import com.company.Labs.Lab1;
import com.company.Labs.Lab2;
import com.company.Labs.Lab3;
import com.company.Labs.Lab4;
import com.company.Utils.DimPoint;

import java.util.function.Function;

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

        Lab3.gradientDescend(point -> (point.get(0) - 2) * (point.get(0) - 4) + (point.get(1) - 2) * (point.get(1) - 2), new DimPoint(5d, 5d));
        Lab3.conjGradientDescend(point -> (point.get(0) - 2) * (point.get(0) - 4) + (point.get(1) - 2) * (point.get(1) - 2), new DimPoint(5d, 5d));


        Function<DimPoint, Double> f = arg -> (arg.get(0) - 5) * arg.get(0) + (arg.get(1) - 3) * arg.get(1);
        Function<DimPoint, Double> fWithout = arg -> (arg.get(0) - 4) * (arg.get(0) - 4) + (arg.get(1) - 4) * (arg.get(1) - 4);
        Function<DimPoint, Double> fWith = arg -> (arg.get(0) - 4) * (arg.get(0) - 4) + (arg.get(1) - 4) * (arg.get(1) - 4)
                + 0.00001*(Math.sqrt(arg.get(0)) + Math.sqrt(arg.get(1)));

        DimPoint raph1 = Lab4.newtoneRaphson(fWithout, new DimPoint(1d, 5d));
        DimPoint raph2 = Lab4.newtoneRaphson(fWith, new DimPoint(1d, 5d));

        System.out.println("NewtoneRaphson  : " + raph1);
        System.out.println("NewtoneRaphson with shtraf  : " + raph2);

    }
}
