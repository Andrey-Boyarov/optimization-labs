package com.company.Utils;

import java.util.stream.Stream;

public class Utils {

    public static Long getFibNumByIndex(Integer index) {
        long a = 0L;
        long b = 1L;
        long c;

        if (index < 0) return null;
        if (index == 0) return 0L;
        if (index == 1) return 1L;

        for (int i = 2; i <= index; i++)
        {
            c = a + b;
            a = b;
            b = c;
        }

        return b;
    }
}
