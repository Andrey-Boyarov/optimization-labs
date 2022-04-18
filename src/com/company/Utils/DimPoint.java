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
}
