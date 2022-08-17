package Passes.client;
import Passes.adt.*;

public class test {
    public static void main(String[] args) {
        DoublyLinkedList<Integer> dll = new DoublyLinkedList<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8});
        dll.remove(2);
        for (int i = 0; i < dll.size(); i++) {
            System.out.println(dll.get(i));
        }
    }


}
