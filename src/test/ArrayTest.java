package test;

import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)

public class ArrayTest {
    private List<Integer> arrayList = new ArrayList<>();
    private List<Integer> arrayList_2;
    int end = 100000;

    @Setup
    public void setSize() {
        arrayList_2 = new ArrayList<>(end);
        for (int i = 0; i < end; i++) {
            arrayList_2.add(i);
        }
    }

    @Benchmark
    public void addFirst() {
        for (int i = 0; i < end; i++) {
            arrayList.add(0, i);
        }
    }

    @Benchmark
    public void addEnd() {
        for (int i = 0; i < end; i++) {
            arrayList.add(i);
        }
    }


    @Benchmark
    public void addMiddle() {
        for (int i = 0; i < end; i++) {
            arrayList_2.add(50000, i);
        }
    }

    // Удаление элемента
    @Benchmark
    public void removeFirst() {
        System.out.println("Размер массива в начале: " + arrayList_2.size());
        /*for (int i = 0; i < end; i++) {
            arrayList_2.remove(0);
        }*/
        while (arrayList_2.size() > 0)
            arrayList_2.remove(0);
        System.out.println("Размер массива в конце: " + arrayList_2.size());
    }

    @Benchmark
    public void removeEnd() {
        System.out.println("Размер массива в начале: " + arrayList_2.size());
       /* for (int i = 0; i < 1000; i++) {
            arrayList_2.remove(arrayList_2.size() - 1);
        }*/
        while (arrayList_2.size() > 0)
            arrayList_2.remove(arrayList_2.size() - 1);
        System.out.println("Размер массива в конце: " + arrayList_2.size());
    }

    @Benchmark
    public void removeMiddle() {
        System.out.println("Размер массива в начале: " + arrayList_2.size());
        /*for (int i = 0; i < 50000; i++) {
            arrayList_2.remove(49000);
        }*/
        while (arrayList_2.size() >= 50000)
            arrayList_2.remove(49000);
        System.out.println("Размер массива в конце: " + arrayList_2.size());
    }

    // Получение элемента
    @Benchmark
    public void getFirst() {
        for (int i = 0; i < end; i++) {
            arrayList_2.get(0);
        }
    }

    @Benchmark
    public void getEnd() {
        for (int i = 0; i < end; i++) {
            arrayList_2.get(arrayList_2.size() - 1);
        }
    }

    @Benchmark
    public void getMiddle() {
        for (int i = 0; i < end; i++) {
            arrayList_2.get(50000);
        }
    }

    @Benchmark
    public boolean containsElement() {
        return arrayList_2.contains(50000);
    }
}
