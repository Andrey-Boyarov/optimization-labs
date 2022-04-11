package com.company.Labs;

import com.company.Utils.DimPoint;

import java.util.function.Function;

public class Lab2 {

    /**
     * Dichotomy method
     *
     * @return Double
     */
    public static strictfp DimPoint dichotomy(Function<DimPoint, Double> f, DimPoint x_0, DimPoint x_1, Integer max_iters, Double eps)
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
            x_c = x_1.plus(x_0).times(0.5);

            if (f.apply(x_c.plus(dir.times(eps))) > f.apply(x_c.minus(dir.times(eps))))
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

    public static DimPoint perCoordDescend(Function<DimPoint, Double> f, DimPoint x_start, double eps, int max_iters)throws Exception
    {
        DimPoint x_0 = new DimPoint(x_start);

        DimPoint x_1 = new DimPoint(x_start);

        double step = 1.0;

        double x_i, y_1, y_0;

        int opt_coord_n = 0, coord_id;

        int i = 0;

        for (i = 0; i < max_iters; i++)
        {
            coord_id = i % x_0.size();

            x_1.set(coord_id, x_1.get(coord_id) - eps);

            y_0 = f.apply(x_1);

            x_1.set(coord_id, x_1.get(coord_id) + 2 * eps);

            y_1 = f.apply(x_1);

            x_1.set(coord_id, x_1.get(coord_id) - eps);

            x_1.set(coord_id,y_0 > y_1 ? x_1.get(coord_id) + step : x_1.get(coord_id) - step);

            x_i = x_0.get(coord_id);

            x_1 = dichotomy(f, x_0, x_1, max_iters, eps);

            x_0 = new DimPoint(x_1);

            if (Math.abs(x_1.get(coord_id) - x_i) < eps)
            {
                opt_coord_n++;

                if (opt_coord_n == x_1.size())
                {
                    return x_0;
                }
                continue;
            }
            opt_coord_n = 0;
        }

        return x_0;
    }
    public static DimPoint perCoordDescend(Function<DimPoint, Double> f, DimPoint x_start, double eps)throws Exception
    {
        return     perCoordDescend( f,  x_start,  eps, 1000);
    }
    public static DimPoint perCoordDescend(Function<DimPoint, Double> f, DimPoint x_start)throws Exception
    {
        return     perCoordDescend( f,  x_start,  1e-5,1000);
    }
}
