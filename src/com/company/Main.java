package com.company;

import com.company.Labs.Lab1;

public class Main {

    public static void main(String[] args) {

        Lab1.dichotomy(x -> x * x + x ,-10,10,1e-8);
        Lab1.goldenSection(x -> x * x + x,-10,10,1e-8);
        Lab1.fibonacci(x -> x * x + x,-10,10,15);
    }
}
