package ru.clevertec.collections;

import ru.clevertec.collections.myInterface.ICustomIterator;
import ru.clevertec.collections.myInterface.ICustomList;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Integer[] integer = {11, 12, 13, 14, 15, 16};
        List<String> strings = Arrays.asList("I", "love", "learning", "on", "JavaRush");
        List<Integer> list = Arrays.asList(20, 21, 22, 23, 24, 25);
        CustomArrayList<Integer> myList = new CustomArrayList<>();

        myList.setMaxSize(3);
        myList.add(2);
        myList.add(5);
        myList.add(10);
        System.out.println("Исходный массив. " + "MaxSize: " + myList.getMaxSize());
        System.out.println(myList);
        System.out.println();

        System.out.println("Добавление 1-го эл-та");
        myList.add(55);
        System.out.println(myList);

        System.out.println();
        myList.setMaxSize(50);
        System.out.println("MaxSize: " + myList.getMaxSize());
        System.out.println("Добавление элементов");
        myList.add(null);
        myList.add(1);
        myList.add(9);
        myList.add(15);
        System.out.println(myList);
        System.out.println();

        System.out.println("Удаление по index 4");
        System.out.println("Элемент: " + myList.remove(4));
        System.out.println(myList);

        System.out.println();
        System.out.println("Поиск элемента '9'");
        System.out.println("index: " + myList.find(9));

        System.out.println();
        System.out.println("Удаление 'null'");
        myList.trim();
        System.out.println(myList);

        System.out.println();
        System.out.println("Добавление массива");
        myList.addAll(integer);
        System.out.println(myList);

        System.out.println();
        System.out.println("Добавление collection");
        myList.addAll(list);
        System.out.println(myList);

        System.out.println();
        System.out.println("Изменить элемент массива");
        myList.set(1, 111);
        System.out.println(myList);

        System.out.println();
        System.out.println("Преобразование коллекции в массив");
        Object[] objects = myList.toArray(list);
        for (Object o : objects)
            System.out.print(" " + o);
        System.out.println();

        System.out.println();
        System.out.println("Инициализация через конструктор");
        System.out.println(" List<String> strings = Arrays.asList(\"I\"," +
                " \"love\", \"learning\", \"on\", \"JavaRush\");");
        ICustomList stringList = new CustomArrayList<>(strings);
        System.out.println(stringList);

// Итератор
        System.out.println();
        System.out.println("iterator");
        ICustomIterator<Integer> iterator = myList.getIterator();
        // Удаление
        System.out.println("Удаление");
        System.out.println(myList);
        iterator.removeIterator();
        System.out.println(myList);

        // Обрезание массива
        System.out.println();
        System.out.println("Обрезание массива");
        myList.setMaxSize(7);
        System.out.println(myList);

        myList.setMaxSize(9);
        // Добавление перед
        System.out.println();
        System.out.println("Добавление перед");
        int i = 0;
        while (iterator.hasNext()) {
            int a = iterator.next();
            if (a == 15 && i == 0) {
                i++;
                iterator.addBefore(333);
            }
        }
        System.out.println(myList);

        iterator = myList.getIterator();
        // Добавление после
        System.out.println();
        System.out.println("Добавление после");
        i = 0;
        while (iterator.hasNext()) {
            int a = iterator.next();
            if (a == 15 && i == 0) {
                i++;
                iterator.addAfter(333);
            }
        }
        System.out.println(myList);

        iterator = myList.getIterator();
        // Удаление с помощью iterator
        System.out.println();
        System.out.println("Удаление элемента");
        i = 0;
        while (iterator.hasNext()) {
            int a = iterator.next();
            if (a == 15 && i == 0) {
                i++;
                iterator.removeIterator();
            }
        }
        System.out.println(myList);
    }
}
