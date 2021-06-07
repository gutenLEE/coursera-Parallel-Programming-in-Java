package edu.coursera.parallel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @author yhlee
 * @created_at 2021-06-06 오후 11:19
 */
public class thread2 {
    public static void main(String[] args) {

        AtomicInteger atomicInteger = new AtomicInteger(0);
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        IntStream.range(0, 1000)
                .forEach(i -> executorService.submit(atomicInteger::incrementAndGet));
        System.out.println(atomicInteger.get());

        IntStream.range(0, 1000)
                .forEach(i -> {
                    Runnable task = () -> atomicInteger.updateAndGet(n -> n + 2);
                    executorService.submit(task);
                });
        System.out.println(atomicInteger.get());
    }
}
