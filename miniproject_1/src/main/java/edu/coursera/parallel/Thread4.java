package edu.coursera.parallel;

import java.net.BindException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author yhlee
 * @created_at 2021-06-07 오후 10:30
 */

// fork join 병렬처리
public class Thread4 {
    public static void main(String[] args) {
        int size = 25;

        Long startTime = Calendar.getInstance().getTimeInMillis();
        final ForkJoinPool pool = new ForkJoinPool();
        List fibonacciSeries = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            FibonacciSeriesGeneratorTask task = new FibonacciSeriesGeneratorTask(i);
            fibonacciSeries.add(pool.invoke(task));
        }
        Long endTime = Calendar.getInstance().getTimeInMillis();
        System.out.println(endTime - startTime);
        dumpList(fibonacciSeries);
    }

    private static void dumpList(List list) {
        int index = 0;
        for (Object object : list) {
            System.out.printf("%d\t%d\n", index++, object);
        }
    }
}
class FibonacciSeriesGeneratorTask extends RecursiveTask {
    private static final long serialVersionUID = 1L;
    private Integer index = 0;

    public FibonacciSeriesGeneratorTask(Integer index) {
        super();
        this.index = index;
    }
    @Override
    protected Integer compute() {
        if (index == 0) {
            return 0;
        }
        if (index < 2) {
            return 1;
        }
        final FibonacciSeriesGeneratorTask worker1 = new FibonacciSeriesGeneratorTask(index - 1);
        worker1.fork();

        final FibonacciSeriesGeneratorTask worker2 = new FibonacciSeriesGeneratorTask(index - 2);
        return worker2.compute() + (Integer)worker1.join();
    }
}
