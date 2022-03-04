package com.company.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Utils {

    private static List<Long> fibNums;

    /**
     * Get fibonacci number by index
     *
     * @return Long
     */
    public static Long getFibNumByIndex(Integer index) {

        checkLegal(index);

        initialize();

        while (!isCalculated(index)){
            setNumber(getNumber(size() - 1) + getNumber(size() - 2));
        }

        return getNumber(index);
    }

    private static void checkLegal(Integer index){
        try{
            getNumber(index);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private static boolean isCalculated(Integer index){
        return size() - 1 >= index;
    }

    private static void setNumber(Long number){
        fibNums.add(number);
    }

    private static Long getNumber(Integer index){
        return fibNums.get(index);
    }

    private static Integer size(){
        return fibNums.size();
    }

    private static void initialize(){
        if (fibNums == null){
            fibNums = new ArrayList<>();
            setNumber(0L);
            setNumber(1L);
            setNumber(1L);
        }
    }
}
