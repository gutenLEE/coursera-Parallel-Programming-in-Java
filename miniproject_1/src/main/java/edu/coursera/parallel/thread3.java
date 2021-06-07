package edu.coursera.parallel;

import java.net.BindException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author yhlee
 * @created_at 2021-06-07 오후 10:11
 */
public class thread3 {

    public static void main(String[] args) {
        int size = 25;
        List<Integer> fibinacciSeries = new ArrayList<>();
        Long startTime = Calendar.getInstance().getTimeInMillis();
        for (int i = 0; i < size; i++) {
            fibinacciSeries.add(FibonacciGenerator.generate(i));
        }
        Long endTime = Calendar.getInstance().getTimeInMillis();
        System.out.println(endTime - startTime);
        dumpList(fibinacciSeries);

    }

    public static void dumpList(List list) {
        int index = 0;
        for (Object object : list) {
            System.out.printf("%d\t%d\n", index++, object);
        }
    }
}
class FibonacciGenerator{
    public static Integer generate(Integer index) {
        if (index == 0) {
            return 0;
        }
        if (index < 2) {
            return 1;
        }
        Integer result = generate(index - 1) + generate(index - 2);
        return result;
    }
}