package com.company.Labs;

import com.company.Utils.DimPoint;
import com.company.Utils.Matrix;

import java.util.function.Function;

public class Lab4 {
    public static DimPoint newtoneRaphson(Function<DimPoint, Double> f, DimPoint x_start, double eps, int max_iters)
    {
        DimPoint x_i = new DimPoint(x_start);

        DimPoint x_i_1 = new DimPoint(x_start);

        int cntr = -1;

        while (true)
        {
            cntr++;

            if (cntr == max_iters)
            {
                break;
            }

            x_i_1 = (x_i.minus(Matrix.mul(Matrix.invert(Matrix.hessian(f, x_i, eps)),DimPoint.gradient(f, x_i, eps))));

            ///Метод работает, но условие снизу не отрабатывает
            if (x_i_1.minus(x_i).magnitude() < eps)
            {
                break;
            }
            x_i = x_i_1;
        }

        System.out.println("Newtone - Raphson iterations number : " + cntr);

        return x_i_1.plus(x_i).times(0.5);
    }
    public static DimPoint newtoneRaphson(Function<DimPoint, Double> f, DimPoint x_start, double eps)
    {
        return newtoneRaphson(f, x_start,  eps, 100);
    }
    public static DimPoint newtoneRaphson(Function<DimPoint, Double> f, DimPoint x_start)
    {
        return newtoneRaphson(f, x_start,  1e-6, 100);
    }
}
