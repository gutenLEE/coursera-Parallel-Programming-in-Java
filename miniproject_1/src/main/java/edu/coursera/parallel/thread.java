package edu.coursera.parallel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author yhlee
 * @created_at 2021-06-06 오후 11:07
 */
public class thread {
    public static void main(String[] args) {
        System.out.println("1");
        // Prepare list of 'Callable' students
        List<Student> students = new ArrayList<Student>();
        students.add(new Student("Bob", 66, 80, 95));
        students.add(new Student("Tom", 78, 97, 43));
        students.add(new Student("Joy", 65, 98, 33));
        students.add(new Student("Milis", 100 ,32, 76));

        // create Executor service with 3 threads in a pool
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        // ask executor to invoke all of the operations
        List<Future<Float>> futureList = null;
        try {
            futureList = executorService.invokeAll(students);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("2");
        for (Future<Float> future : futureList) {
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println("3");
    }
}

class Student implements Callable<Float> {
    String name;
    int a;
    int b;
    int c;
    public Student(String name, int a, int b, int c) {
        super();
        this.name = name;
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public Float call() throws Exception {
        return (a + b + c) / 3.0f;
    }
}
