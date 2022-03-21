package com.company.Utils;

import java.util.ArrayList;
import java.util.List;

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
        params = point.params;
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

    public DimPoint minus(DimPoint point){
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

    public DimPoint plus(DimPoint point){
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

    public DimPoint times(Double val)
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
}
