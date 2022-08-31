package Passes.adt;

import java.util.NoSuchElementException;

public class DoublyLinkedList<E>
        implements List<E>, Cloneable
{
    private int size = 0;
    /**
     * Pointer to first node.
     */
    private Node<E> head;

    /**
     * Pointer to last node.
     */
    private Node<E> tail;

    public DoublyLinkedList() {
    }

    public DoublyLinkedList(E[] array) {
        this();
        addAll(array);
    }

    private void linkHead(E e) {
        final Node<E> f = head;
        final Node<E> newNode = new Node<>(null, e, f);
        head = newNode;
        if (f == null)
            tail = newNode;
        else
            f.prev = newNode;
        size++;
    }

    void linkTail(E e) {
        final Node<E> l = tail;
        final Node<E> newNode = new Node<>(l, e, null);
        tail = newNode;
        if (l == null)
            head = newNode;
        else
            l.next = newNode;
        size++;
    }

    void linkBefore(E e, Node<E> succ) {
        final Node<E> pred = succ.prev;
        final Node<E> newNode = new Node<>(pred, e, succ);
        succ.prev = newNode;
        if (pred == null)
            head = newNode;
        else
            pred.next = newNode;
        size++;
    }

    private E unlinkHead(Node<E> f) {
        final E element = f.item;
        final Node<E> next = f.next;
        f.item = null;
        f.next = null; // help GC
        head = next;
        if (next == null)
            tail = null;
        else
            next.prev = null;
        size--;
        return element;
    }

    private E unlinkTail(Node<E> l) {
        final E element = l.item;
        final Node<E> prev = l.prev;
        l.item = null;
        l.prev = null; // help GC
        tail = prev;
        if (prev == null)
            head = null;
        else
            prev.next = null;
        size--;
        return element;
    }

    E unlink(Node<E> x) {
        // assert x != null;
        final E element = x.item;
        final Node<E> next = x.next;
        final Node<E> prev = x.prev;

        if (prev == null) {
            head = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            tail = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.item = null;
        size--;
        return element;
    }

    public E getHead() {
        final Node<E> f = head;
        if (f == null)
            throw new NoSuchElementException();
        return f.item;
    }

    public E getTail() {
        final Node<E> l = tail;
        if (l == null)
            throw new NoSuchElementException();
        return l.item;
    }

    public E removeHead() {
        final Node<E> f = head;
        if (f == null)
            throw new NoSuchElementException();
        return unlinkHead(f);
    }

    public E removeTail() {
        final Node<E> l = tail;
        if (l == null)
            throw new NoSuchElementException();
        return unlinkTail(l);
    }

    public void addHead(E e) {
        linkHead(e);
    }

    public void addTail(E e) {
        linkTail(e);
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean add(E e) {
        linkTail(e);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (Node<E> x = head; x != null; x = x.next) {
                if (x.item == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node<E> x = head; x != null; x = x.next) {
                if (o.equals(x.item)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(E[] array) {
        for (E e : array) {
            if (!contains(e))
                return false;
        }
        return true;
    }

    @Override
    public boolean addAll(E[] array) {
        return addAll(size, array);
    }

    @Override
    public boolean addAll(int index, E[] array) {
        checkPositionIndex(index);

        int numNew = array.length;
        if (numNew == 0)
            return false;

        Node<E> pred, succ;
        if (index == size) {
            succ = null;
            pred = tail;
        } else {
            succ = node(index);
            pred = succ.prev;
        }

        for (E o : array) {
            Node<E> newNode = new Node<>(pred, o, null);
            if (pred == null)
                head = newNode;
            else
                pred.next = newNode;
            pred = newNode;
        }

        if (succ == null) {
            tail = pred;
        } else {
            pred.next = succ;
            succ.prev = pred;
        }

        size += numNew;
        return true;
    }

    @Override
    public boolean removeAll(E[] array) {
        for (E e : array) {
            remove(e);
        }
        return true;
    }

    @Override
    public void clear() {
        for (Node<E> x = head; x != null; ) {
            Node<E> next = x.next;
            x.item = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        head = tail = null;
        size = 0;
    }

    // Positional Access Operations

    @Override
    public E get(int index) {
        checkElementIndex(index);
        return node(index).item;
    }

    @Override
    public E set(int index, E element) {
        checkElementIndex(index);
        Node<E> x = node(index);
        E oldVal = x.item;
        x.item = element;
        return oldVal;
    }

    @Override
    public void add(int index, E element) {
        checkPositionIndex(index);

        if (index == size)
            linkTail(element);
        else
            linkBefore(element, node(index));
    }

    @Override
    public E remove(int index) {
        checkElementIndex(index);
        return unlink(node(index));
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    Node<E> node(int index) {

        Node<E> x;
        if (index < (size >> 1)) {
            x = head;
            for (int i = 0; i < index; i++)
                x = x.next;
        } else {
            x = tail;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
        }
        return x;
    }

    // Search Operations
    @Override
    public int indexOf(Object o) {
        int index = 0;
        if (o == null) {
            for (Node<E> x = head; x != null; x = x.next) {
                if (x.item == null)
                    return index;
                index++;
            }
        } else {
            for (Node<E> x = head; x != null; x = x.next) {
                if (o.equals(x.item))
                    return index;
                index++;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index = size;
        if (o == null) {
            for (Node<E> x = tail; x != null; x = x.prev) {
                index--;
                if (x.item == null)
                    return index;
            }
        } else {
            for (Node<E> x = tail; x != null; x = x.prev) {
                index--;
                if (o.equals(x.item))
                    return index;
            }
        }
        return -1;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        checkPositionIndex(fromIndex);
        checkPositionIndex(toIndex);
        List<E> list = new DoublyLinkedList<>();
        for (int x = fromIndex; x <= toIndex; x++) {
            list.add(get(x));
        }
        return list;
    }

    public E peekHead() {
        final Node<E> f = head;
        return (f == null) ? null : f.item;
    }

    public E peekTail() {
        final Node<E> l = tail;
        return (l == null) ? null : l.item;
    }

    public E pollHead() {
        final Node<E> f = head;
        return (f == null) ? null : unlinkHead(f);
    }

    public E pollTail() {
        final Node<E> l = tail;
        return (l == null) ? null : unlinkTail(l);
    }

    public void push(E e) {
        addTail(e);
    }

    public E pop() {
        return removeTail();
    }

    public boolean removeFirstOccurrence(Object o) {
        return remove(o);
    }

    public boolean removeLastOccurrence(Object o) {
        if (o == null) {
            for (Node<E> x = tail; x != null; x = x.prev) {
                if (x.item == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node<E> x = tail; x != null; x = x.prev) {
                if (o.equals(x.item)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    @SuppressWarnings("unchecked")
    private DoublyLinkedList<E> superClone() {
        try {
            return (DoublyLinkedList<E>) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }

    @Override
    public Object clone() {
        DoublyLinkedList<E> clone = superClone();

        clone.head = clone.tail = null;
        clone.size = 0;

        for (Node<E> x = head; x != null; x = x.next)
            clone.add(x.item);

        return clone;
    }

    @Override
    public String toString() {
        if (size == 0)
            return "null";

        int iMax = size - 1;

        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; ; i++) {
            b.append(get(i));
            if (i == iMax)
                return b.append(']').toString();
            b.append(", ");
        }
    }
}
