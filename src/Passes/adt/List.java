package Passes.adt;

public interface List<E> {

    int size();

    boolean isEmpty();

    boolean contains(Object o);

    boolean add(E e);

    boolean remove(Object o);

    boolean containsAll(E[] array);

    boolean addAll(E[] array);

    boolean addAll(int index, E[] array);

    boolean removeAll(E[] array);

    void clear();

    boolean equals(Object o);

    E get(int index);

    E set(int index, E element);

    void add(int index, E element);

    E remove(int index);

    int indexOf(Object o);

    int lastIndexOf(Object o);

    List<E> subList(int fromIndex, int toIndex);
}

