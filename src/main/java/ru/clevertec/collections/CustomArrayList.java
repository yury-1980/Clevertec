package ru.clevertec.collections;

import ru.clevertec.collections.myInterface.ICustomIterator;
import ru.clevertec.collections.myInterface.ICustomList;

import java.util.Collection;

public class CustomArrayList<E> implements ICustomList<E> {

    private int capacity = 1;
    private int size = 0;
    private int maxSize;
    private boolean maxS = false;
    private Object[] mas = new Object[capacity];
    private Object[] masNew;


    public CustomArrayList() {
    }

    public CustomArrayList(int size) {
        calcCapacity();
        this.mas = new Object[capacity];
        this.size = size;
    }

    CustomArrayList(Collection<? extends E> list) {
        addAll(list);
    }


    // Копирование массива
    private void masCopy() {
        masNew = mas.clone();
        mas = new Object[capacity];
        System.arraycopy(masNew, 0, mas, 0, size);
    }

    // Увеличение ёмкости массива
    private void calcCapacity() {
        if (size >= capacity) {
            capacity = Math.min((int) Math.ceil(size * 1.5),
                    Integer.MAX_VALUE);
            masCopy();
        }
    }

    @Override
    public void setMaxSize(Integer maxSize) {
        this.maxSize = maxSize;
        if (size > maxSize)
            this.size = maxSize;
        maxS = true;
    }

    public int getMaxSize() {
        return this.maxSize;
    }

    @Override
    public void add(E e) {
        if (size >= maxSize && maxS == true) {
            size = maxSize;
            System.out.println("IndexOutOfBoundsException " +
                    "maxSize = "
                    + maxSize);
        } else {
            calcCapacity();
            mas[size++] = e;
        }
    }

    public void addAll(Collection<? extends E> list) {

        for (E e : list)
            add(e);
    }

    @Override
    public void addAll(E[] c) {
        for (int i = 0; i < c.length; i++) {
            add(c[i]);
        }
    }

    @Override
    public E remove(int index) {
        checkOutMas(index);
        E oldElement = (E) mas[index];

        if (index < size - 1) {
            masNew = new Object[size];
            System.arraycopy(mas, 0, masNew, 0, index);
            System.arraycopy(mas, index + 1, masNew, index, size
                    - 1 - index);
            mas = new Object[capacity];
            System.arraycopy(masNew, 0, mas, 0, size);
        }
        mas[size--] = null;

        return oldElement;
    }

    @Override
    public void clear() {
        for (Object e : mas)
            e = null;
        size = 0;

    }

    @Override
    public int find(E o) {
        for (int i = 0; i < size; i++) {
            if (mas[i] == null) {
                continue;
            } else if (mas[i].equals(o))
                return i;
        }
        return -1;
    }


    // Вывод входящего индекса и реальный размер массва
    private String outMas(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    // Проверка выхода за пределы массива
    private void checkOutMas(int index) {
        if (index >= size || index < 0)
            throw new IndexOutOfBoundsException(outMas(index));
    }

    // Вывод элемента массива
    @Override
    public E get(int index) {
        checkOutMas(index);
        return (E) mas[index];
    }

    // Задать элемент массива
    @Override
    public E set(int index, E element) {
        checkOutMas(index);
        E oldElement = (E) mas[index];
        mas[index] = element;
        return oldElement;
    }

    @Override
    public <E> E[] toArray(Collection<? extends E> list) {
        int i = 0;
        for (E e : list)
            i++;
        masNew = new Object[i];

        i = 0;
        for (E e : list) {
            masNew[i++] = e;
        }

        return (E[]) masNew;
    }

    @Override
    public int size() {
        return size;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public void trim() {
        int sizeNew = 0;
        masNew = new Object[size];
        for (int i = 0; i < size; i++) {
            if (mas[i] != null) {
                masNew[sizeNew++] = mas[i];
            }
        }
        System.arraycopy(masNew, 0, mas, 0, sizeNew);
        size = sizeNew;
    }

    @Override
    public ICustomIterator<E> getIterator() {
        return new CustomIterator();
    }

    private String toStringMas() {
        for (int i = 0; i < size; i++) {
            System.out.print(" '" + mas[i] + "'");
        }
        return "}";
    }

    @Override
    public String toString() {
        System.out.print(String.format("CustomArrayList{(size=%d, " +
                        "capacity=%d):", size,
                capacity));
        return toStringMas();
    }

    // Итератор
    private class CustomIterator implements ICustomIterator<E> {
        private int index;

        @Override
        public boolean hasNext() {
            if (index < size)
                return true;
            return false;
        }

        @Override
        public E next() {
            return (E) mas[index++];
        }

        @Override
        public void removeIterator() {
            if (index - 1 < 0)
                index = 1;
            remove(index - 1);// Т.к. next() возвращает mas[index++]
        }

        @Override
        public void addBefore(E e) {
            if (index == 0)
                System.out.println("Нулевой элемент, добавление запрещено");
            else {
                masNew = new Object[size - index + 1];
                System.arraycopy(mas, index - 1, masNew, 0, size - index + 1);
                size++;
                calcCapacity();
                set(index - 1, e);
                System.arraycopy(masNew, 0, mas, index, masNew.length);
            }
        }

        @Override
        public void addAfter(E e) {
            if (index == size - 1)
                add(e);
            else {
                masNew = new Object[size - index];
                System.arraycopy(mas, index, masNew, 0, size - index);
                size++;
                calcCapacity();
                set(index, e);
                System.arraycopy(masNew, 0, mas, index + 1, masNew.length);
            }
        }
    }
}