package com.company.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class DimPoint {

    private final List<Double> params;

    public Double get(int index){
        return params.get(index);
    }

    public DimPoint set(int index, Double value){
        params.set(index, value);
        return this;
    }

    public DimPoint add(double other)
    {
        for (int i = 0; i < size(); i++)
        {
            set(i, get(i) + other);
        }
        return this;
    }

    public  DimPoint add(DimPoint other)
    {
        if(size()!= other.size())
        {
            throw new RuntimeException("DimPoints add :: this.Size()!= other.Size()");
        }
        for (int i = 0; i < other.size(); i++)
        {
            set(i, get(i) + other.get(i));
        }
        return this;
    }

    public DimPoint(DimPoint point){
        params = new ArrayList<>(point.params);
    }

    public DimPoint(int size){
        params = new ArrayList<>();
        for (int i = 0; i < size; ++i){
            params.add(0d);
        }
    }

    public DimPoint(Double x, Double y){
        params = new ArrayList<>();
        params.add(x);
        params.add(y);
    }

    public int size(){
        return params.size();
    }

    @Override
    public String toString(){
        return params.stream().map(el -> " ::" + el.toString()).reduce("", (partialString, element) -> partialString + element);
    }

    public  DimPoint minus(double other)
    {
        DimPoint res = new DimPoint(this);
        for (int i = 0; i < size(); i++)
        {
            res.set(i, get(i) - other);
        }
        return res;
    }

    public strictfp DimPoint minus(DimPoint point){
        if (this.size() != point.size())
        {
            new IllegalArgumentException().printStackTrace();
        }

        DimPoint res = new DimPoint(this);
        for (int i = 0; i < this.size(); i++)
        {
            res.set(i, this.get(i) - point.get(i));
        }
        return res;
    }

    public static DimPoint minus(double b, DimPoint a)
    {
        DimPoint res = new DimPoint(a);

        for(int i = 0; i < a.size(); i++)
        {
            res.set(i, b - a.get(i));
        }
        return  res;
    }
    
    public strictfp DimPoint plus(DimPoint point){
        if (this.size() != point.size())
        {
            new IllegalArgumentException().printStackTrace();
        }

        DimPoint res = new DimPoint(this);
        for (int i = 0; i < size(); i++)
        {
            res.set(i, this.get(i) + point.get(i));
        }
        return res;
    }

    strictfp public DimPoint times(Double val)
    {
        DimPoint res = new DimPoint(this);
        for (int i = 0; i < size(); i++)
        {
            res.set(i, this.get(i) * val);
        }
        return res;
    }


    public static DimPoint direction(DimPoint a, DimPoint b)
    {
        if (a.size() != b.size())
        {
            return a;
        }
        return (b.minus(a)).normalized();
    }

    public DimPoint normalized()
    {
        DimPoint v = new DimPoint(this);
        double inv_mag = 1.0f / v.magnitude();
        for (int i = 0; i < v.size(); i++)
        {
            v.set(i, v.get(i) * inv_mag);
        }
        return v;
    }

    public double magnitude()
    {
        return Math.sqrt(params.stream().mapToDouble(element -> element * element).sum());
    }

    public static DimPoint gradient(Function<DimPoint, Double> func, DimPoint x, double eps)
    {
        DimPoint x_l = new DimPoint(x);
        DimPoint x_r = new DimPoint(x);
        DimPoint df = new DimPoint(x.size());
        for (int i = 0; i < x.size(); i++)
        {
            x_l.set(i, x_l.get(i) - eps);//   [i] -= eps;
            x_r.set(i, x_r.get(i) + eps);

            df.set(i,(func.apply(x_r) - func.apply(x_l)) * (0.5 / eps));

            x_l.set(i, x_l.get(i) + eps);//   [i] -= eps;
            x_r.set(i, x_r.get(i) - eps);
        }
        return df;
    }

    private boolean isInRange(int index)
    {
        return index >= 0 && index < params.size();
    }


    public static double partial (Function<DimPoint, Double> func, DimPoint x, int index, double eps )
    {
        if (!x.isInRange(index))
        {
            throw new RuntimeException("Partial derivative index out of bounds!");
        }
        x.set(index,  x.get(index) + eps);//[index] += eps;
        double f_r = func.apply(x);
        x.set(index,  x.get(index) - 2.0 * eps);
        double f_l = func.apply(x);
        x.set(index,  x.get(index) +  eps);
        return (f_r - f_l) / eps * 0.5;
    }

    public static double partial2(Function<DimPoint, Double> func, DimPoint x, int index_1, int index_2, double eps)
    {
        if (!x.isInRange(index_2))
        {
            throw new RuntimeException("Partial derivative index out of bounds!");
        }
        x.set(index_2,  x.get(index_2) - eps);
        double f_l = partial(func, x, index_1, eps);
        x.set(index_2,  x.get(index_2) + 2.0 * eps);
        double f_r = partial(func, x, index_1, eps);
        x.set(index_2,  x.get(index_2) - eps);
        return (f_r - f_l) / eps * 0.5;
    }

    public double dot(DimPoint other)
    {
        if(size()!= other.size())
        {
            throw new RuntimeException("Dot product :: this.Size()!= other.Size()");
        }
        double dot = 0.0;

        for (int i = 0; i < other.size(); i++)
        {
            dot += this.get(i) * other.get(i);
        }
        return dot;
    }

    public static double dot(DimPoint a, DimPoint b)
    {
        return a.dot(b);
    }
}
