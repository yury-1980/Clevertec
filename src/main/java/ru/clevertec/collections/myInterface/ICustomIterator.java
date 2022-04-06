package ru.clevertec.collections.myInterface;

public interface ICustomIterator<E> {

    E next();

    boolean hasNext();

    void removeIterator();

    void addBefore(E e);

    void addAfter(E e);
}
