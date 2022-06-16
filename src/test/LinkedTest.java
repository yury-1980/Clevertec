package test;

import org.openjdk.jmh.annotations.*;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)

public class LinkedTest {
    private LinkedList<Integer> linkedList = new LinkedList<>();
    private LinkedList<Integer> linkedList_2;
    int end = 100000;

    @Setup
    public void setSize() {
        linkedList_2 = new LinkedList<>();
        for (int i = 0; i < end; i++) {
            linkedList_2.addFirst(i);
            ;
        }
    }

    @Benchmark
    public void addFirst() {
        for (int i = 0; i < end; i++) {
            linkedList.addFirst(i);
        }
    }

    @Benchmark
    public void addEnd() {
        for (int i = 0; i < end; i++) {
            linkedList.addLast(i);
        }
    }


    @Benchmark
    public void addMiddle() {
        for (int i = 0; i < end; i++) {
            linkedList_2.add(50000, i);
        }
    }

    // Удаление элемента
    @Benchmark
    public void removeFirst() {
        System.out.println("Размер массива в начале: " + linkedList_2.size());
        /*for (int i = 0; i < end; i++) {
            linkedList_2.removeFirst();
        }*/
        while (linkedList_2.size() > 0)
            linkedList_2.removeFirst();
        System.out.println("Размер массива в конце: " + linkedList_2.size());
    }

    @Benchmark
    public void removeEnd() {
        System.out.println("Размер массива в начале: " + linkedList_2.size());
       /* for (int i = 0; i < 1000; i++) {
            linkedList_2.remove(linkedList_2.size() - 1);
        }*/
        while (linkedList_2.size() > 0)
            linkedList_2.removeLast();
        System.out.println("Размер массива в конце: " + linkedList_2.size());
    }

    @Benchmark
    public void removeMiddle() {
        System.out.println("Размер массива в начале: " + linkedList_2.size());
        /*for (int i = 0; i < 50000; i++) {
            linkedList_2.remove(49000);
        }*/
        while (linkedList_2.size() >= 50000)
            linkedList_2.remove(49000);
        System.out.println("Размер массива в конце: " + linkedList_2.size());
    }

    // Получение элемента
    @Benchmark
    public void getFirst() {
        for (int i = 0; i < end; i++) {
            linkedList_2.getFirst();
        }
    }

    @Benchmark
    public void getEnd() {
        for (int i = 0; i < end; i++) {
            linkedList_2.getLast();
        }
    }

    @Benchmark
    public void getMiddle() {
        for (int i = 0; i < end; i++) {
            linkedList_2.get(50000);
        }
    }

    @Benchmark
    public boolean containsElement() {
        return linkedList_2.contains(50000);
    }
}
