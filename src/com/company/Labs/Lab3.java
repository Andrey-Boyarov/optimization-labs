package com.company.Labs;

import com.company.Utils.DimPoint;

import java.util.function.Function;

public class Lab3 {

    public static DimPoint gradientDescend(Function<DimPoint, Double> f, DimPoint x_start, double eps, int max_iters)
    {
        DimPoint x_i = new DimPoint(x_start);

        DimPoint x_i_1 = new DimPoint(x_start); ;

        int cntr = 0;

        while (true)
        {
            cntr++;

            if (cntr == max_iters)
            {
                break;
            }

            x_i_1 = x_i.minus(DimPoint.gradient(f, x_i, eps));

            x_i_1 = Lab2.dichotomy(f, x_i, x_i_1, max_iters, eps);

            if (x_i_1.minus(x_i).magnitude() < eps)
            {
                break;
            }

            x_i = x_i_1;
        }

        System.out.println("gradient descend iterations number : " + cntr);

        System.out.println("GradientDescend        : " +
                x_i_1.plus(x_i).times(0.5));


        return x_i_1.plus(x_i).times(0.5);
    }
    public static DimPoint gradientDescend(Function<DimPoint, Double> f, DimPoint x_start, double eps)
    {
        return gradientDescend( f,  x_start,  eps, 1000);
    }
    public static DimPoint gradientDescend(Function<DimPoint, Double> f, DimPoint x_start)
    {
        return gradientDescend( f,  x_start,  1e-5, 1000);
    }
    public static DimPoint conjGradientDescend(Function<DimPoint, Double> f, DimPoint x_start, double eps , int max_iters)
    {
        DimPoint x_i = new DimPoint(x_start);

        DimPoint x_i_1 = new DimPoint(x_start);

        DimPoint s_i = DimPoint.gradient(f, x_start, eps).times(-1.0);
        DimPoint s_i_1;

        double omega;

        int cntr = 0;

        while (true)
        {
            cntr++;

            if (cntr == max_iters)
            {
                break;
            }

            x_i_1 = x_i.plus(s_i);

            x_i_1 = Lab2.dichotomy(f, x_i, x_i_1, max_iters, eps);

            if (x_i_1.minus(x_i).magnitude() < eps)
            {
                break;
            }

            s_i_1 = DimPoint.gradient(f, x_i_1, eps);

            omega = Math.pow((s_i_1).magnitude(), 2) / Math.pow((s_i).magnitude(), 2);

            s_i.times(omega).minus(s_i_1);

            x_i = x_i_1;
        }

        System.out.println("СonjGradientDescend    : " +
                x_i_1.plus(x_i).times(0.5));

        return x_i_1.plus(x_i).times(0.5);
    }
    public static DimPoint conjGradientDescend(Function<DimPoint, Double> f, DimPoint x_start, double eps )
    {
        return conjGradientDescend( f,  x_start,  eps, 1000);
    }
    public static DimPoint conjGradientDescend(Function<DimPoint, Double> f, DimPoint x_start)
    {
        return conjGradientDescend( f,  x_start,  1e-5, 1000);
    }
}
