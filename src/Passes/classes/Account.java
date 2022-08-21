package Passes.classes;
import Passes.adt.DoublyLinkedList;

public class Account {
    private Person user;
    private DoublyLinkedList<VisitPass> passes;
    private int id;

    public Account() {
        // empty constructor
    }

    public Account(Person user, DoublyLinkedList<VisitPass> passes, int id) {
        this.user = user;
        this.passes = passes;
        this.id = id;
    }

    public Person getUser() {
        return user;
    }

    public void setUser(Person user) {
        this.user = user;
    }

    public DoublyLinkedList<VisitPass> getPasses() {
        return passes;
    }

    public void setPasses(DoublyLinkedList<VisitPass> passes) {
        this.passes = passes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
