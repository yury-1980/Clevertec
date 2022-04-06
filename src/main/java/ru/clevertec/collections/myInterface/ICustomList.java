package ru.clevertec.collections.myInterface;

import java.util.Collection;

public interface ICustomList<E> {

    ICustomIterator<E> getIterator();

    void setMaxSize(Integer i);

    void add(E e);

    void addAll(Collection<? extends E> list);

    void addAll(E[] c);

    Object set(int index, E element);

    Object remove(int index);

    void clear();

    int find(E o);

    Object get(int index);

   <E> E[] toArray(Collection<? extends E> list);

    int size();

    void trim();
}
