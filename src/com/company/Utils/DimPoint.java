package com.company.Utils;

import java.util.ArrayList;
import java.util.Arrays;
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

    public DimPoint addDim(double d){
        params.add(d);
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

    public DimPoint(Double... args) {
        params = new ArrayList<>();
        params.addAll(Arrays.asList(args));
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

    public  DimPoint mul(double other)
    {
        for (int i = 0; i < size(); i++)
        {
            set(i, get(i) * other);
        }
        return this;
    }

    public static DimPoint sub(DimPoint a, DimPoint b)
    {
        if(a.size()!= b.size())
        {
            throw new RuntimeException("Vectors sub :: this.Size()!= other.Size()");
        }
        DimPoint res = new DimPoint(a);
        return res.sub(b);
    }

    public static DimPoint sub(DimPoint a, double b)
    {
        DimPoint res = new DimPoint(a);
        return res.sub(b);
    }

    public static DimPoint sub(double b, DimPoint a)
    {
        DimPoint res = new DimPoint(a);

        for(int i = 0; i < a.size(); i++)
        {
            res.set(i, b - a.get(i));
        }
        return  res;
    }
    
    public  DimPoint sub(DimPoint other)
    {
        if(size()!= other.size())
        {
            throw new RuntimeException("Vectors sub :: this.Size()!= other.Size()");
        }
        for (int i = 0; i < other.size(); i++)
        {
            set(i, get(i) - other.get(i));
        }
        return this;
    }

    public  DimPoint sub(double other)
    {
        for (int i = 0; i < size(); i++)
        {
            set(i, get(i) - other);
        }
        return this;
    }
    
    public static DimPoint mul(DimPoint a,double b)
    {
        DimPoint res = new DimPoint(a);
        return  res.mul(b);
    }

    public static DimPoint mul(double b,DimPoint a)
    {
        return  mul(a,b);
    }

    public static String toRationalStr(DimPoint value)
    {
        return toRationalStr(value,  true);
    }

    public static String toRationalStr(DimPoint value, boolean fullRational)
    {
        StringBuilder str = new StringBuilder("{ ");
        for (int i = 0; i < value.size() - 1; i++)
        {
            str.append(toRationalStr(value.get(i), fullRational));
            str.append(", ");
        }
        str.append(toRationalStr(value.get(value.size() - 1), fullRational));

        str.append(" }");
        return str.toString();
    }

    public static String toRationalStr(double value, boolean fullRational)
    {
        int[] number =  decimalToRational(value);
        if (number[1] == 0)
        {
            return String.valueOf(number[0]);
        }
        if (number[0] == 0)
        {
            return String.valueOf(number[1]) + "/" + String.valueOf(number[2]);
        }

        if (fullRational)
        {
            return String.valueOf((number[1] + Math.abs(number[0]) * number[2]) * (number[0] >= 0 ? 1 : -1)) + "/" + String.valueOf(number[2]);
        }
        return String.valueOf(number[0]) + " " + String.valueOf(number[1]) + "/" + String.valueOf(number[2]);
    }

    public static String toRationalStr(double value)
    {
        return toRationalStr(value, true);
    }

    public static int[] decimalToRational(double value)
    {
        return decimalToRational(value,1000);
    }

    public static int[] decimalToRational(double value, int max_den)
    {
        long m00 = 1;
        long m01 = 0;
        long m10 = 0;
        long m11 = 1;

        int[] number = new int[3];

        long ai;

        double x;

        int sign = value >= 0 ? 1 : -1;

        x = Math.abs(value);

        long t;

        while (m10 * (ai = (long)x) + m11 <= max_den)
        {
            t =m00 * ai + m01;

            m01 = m00;
            m00 = t;

            t = m10 * ai + m11;

            m11 = m10;
            m10 = t;
            if (x == (double)ai)
            {
                break;
            }   // AF: division by zero
            x = 1 / (x - (double)ai);
            if (x > (double)0x7FFFFFFF)
            {
                break;
            }  // AF: representation failure
        }

        if ((number[0] = (int)(m00 / m10)) != 0)
        {
            number[1] = (int)(m00 - number[0] * m10);

            number[0] *= sign;

            number[2] = (int)m10;

            return number;
        }
        number[0]  = 0;

        number[1]  = (int)(sign * m00);

        number[2]  = (int)m10;

        return number;
    }
}
