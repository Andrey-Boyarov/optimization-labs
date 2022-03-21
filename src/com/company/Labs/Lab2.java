package com.company.Labs;

import com.company.Utils.DimPoint;

import java.util.function.Function;

public class Lab2 {

    /**
     * Dichotomy method
     *
     * @return Double
     */
    public static DimPoint dichotomy(Function<DimPoint, Double> f, DimPoint x_0, DimPoint x_1, Integer max_iters, Double eps)
    {
        if (eps == null) eps = 1e-6d;
        if (max_iters == null) max_iters = 1000;

        DimPoint x_c, dir;

        dir = DimPoint.direction(x_0, x_1);

        int cntr = 0;

        for (; cntr != max_iters; cntr++)
        {
            if ((x_1.minus(x_0)).magnitude() < eps)
            {
                break;
            }
            x_c = (x_1.plus(x_0)).times(0.5);

            if (f.apply(x_c.plus(dir).times(eps)) > f.apply(x_c.minus(dir).times(eps)))
            {
                x_1 = x_c;
                continue;
            }
            x_0 = x_c;
        }

        System.out.println("Two-dimensional dichotomy started");
        System.out.println("Result is: " + x_1.plus(x_0).times(0.5));

        return x_1.plus(x_0).times(0.5);
    }
}
