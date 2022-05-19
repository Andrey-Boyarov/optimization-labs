package com.company;

import com.company.Labs.*;
import com.company.Utils.DimPoint;
import com.company.Utils.Matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;

public class Main {

    public static void main(String[] args) {


//        Lab1.dichotomy(x -> x * x + x ,-10,10,1e-8);
//        Lab1.goldenSection(x -> x * x + x,-10,10,1e-8);
//        Lab1.fibonacci(x -> x * x + x,-10,10,15);
//
//        Lab2.dichotomy(point -> (point.get(0) - 2) * (point.get(0) - 2) + (point.get(1) - 2) * (point.get(1) - 2), new DimPoint(0d, 0d), new DimPoint(5d, 5d), 100, 1e-8);
//
//        try {
//            Lab2.perCoordDescend(point -> (point.get(0) - 5) * point.get(0) + (point.get(1) - 3) * point.get(1), new DimPoint(-13d, 22d));
//        } catch (Exception exception) {
//            exception.printStackTrace();
//        }
//
//        Lab3.gradientDescend(point -> (point.get(0) - 2) * (point.get(0) - 4) + (point.get(1) - 2) * (point.get(1) - 2), new DimPoint(5d, 5d));
//        Lab3.conjGradientDescend(point -> (point.get(0) - 2) * (point.get(0) - 4) + (point.get(1) - 2) * (point.get(1) - 2), new DimPoint(5d, 5d));
//
//
//        Function<DimPoint, Double> f = arg -> (arg.get(0) - 5) * arg.get(0) + (arg.get(1) - 3) * arg.get(1);
//        Function<DimPoint, Double> fWithout = arg -> (arg.get(0) - 4) * (arg.get(0) - 4) + (arg.get(1) - 4) * (arg.get(1) - 4) + (arg.get(2) - 4) * (arg.get(2) - 4);
//        Function<DimPoint, Double> fWith = arg -> (arg.get(0) - 4) * (arg.get(0) - 4) + (arg.get(1) - 4) * (arg.get(1) - 4) + (arg.get(2) - 4) * (arg.get(2) - 4)
//                + 0.00001*(Math.sqrt(arg.get(0)) + Math.sqrt(arg.get(1)));
//
//        DimPoint threedPoint = new DimPoint(1d, 5d).addDim(4d);
//        DimPoint raph1 = Lab4.newtoneRaphson(fWithout, threedPoint);
//        DimPoint raph2 = Lab4.newtoneRaphson(fWith, threedPoint);
//
//        System.out.println("NewtoneRaphson  : " + raph1);
//        System.out.println("NewtoneRaphson with shtraf  : " + raph2);


        Simplex.showSimplexDebugLog = true;
        Simplex sym_0 = new Simplex(
                /*A*/new Matrix(new DimPoint( 1.0, -2.0),
                new DimPoint( -1.0, 1.0),
                new DimPoint( 1.0, -1.0)),
                /*c*/new DimPoint( 1.0,  -2.0),
                /*inequalities*/new ArrayList<>(Arrays.asList(Sign.Less, Sign.More, Sign.Less)),
                /*b*/new DimPoint( 6.0, 2.0, 4.0));
        sym_0.solve(SimplexProblemType.Min);

    }
}
