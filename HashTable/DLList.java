package HashTable;

public class DLList<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public DLList() {
        size = 0;
        head = new Node<E>(null);
        tail = new Node<E>(null);
        head.setNext(tail);
        tail.setPrev(head);

    }

    public Node<E> getNode(int inedx) {
        Node<E> currentNode = head;
        for (int i = 0; i <= inedx; i++) {
            currentNode = currentNode.next();
        }
        return currentNode;

    }

    public boolean add(E obj) {
        Node<E> current = tail.prev();
        Node<E> n = new Node<E>(obj);
        n.setNext(tail);
        n.setPrev(current);
        tail.setPrev(n);
        current.setNext(n);
        size++;
        return true;

    }

    public void add(int index, E obj) {
        Node<E> current = head;
        Node<E> n = new Node<E>(obj);
        for (int i = 0; i < index; i++) {
            current = current.next();
        }
        n.setNext(current.next());
        n.setPrev(current);
        current.next().setPrev(n);
        current.setNext(n);
        size++;
    }

    public E get(int index) {
        Node<E> current = head.next();
        for (int i = 0; i < index; i++) {
            current = current.next();
        }
        return current.get();

    }

    public void set(int index, E obj) {
        Node<E> current = head.next();
        Node<E> n = new Node<E>(obj);
        for (int i = 0; i < index; i++) {
            current = current.next();
        }
        n.setNext(current.next());
        n.setPrev(current.prev());
        current.next().setPrev(n);
        current.prev().setNext(n);
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        Node<E> current = head.next();
        for (int i = 0; i < index; i++) {
            current = current.next();
        }
        current.prev().setNext(current.next());
        current.next().setPrev(current.prev());
        size--;

    }

    public void remove(E obj) {
        Node<E> current = head.next();
        for (int i = 0; i < size; i++) {
            if (current.get().equals(obj)) {
                current.prev().setNext(current.next());
                current.next().setPrev(current.prev());
                size--;
            }
            current = current.next();
        }
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            remove(i);
        }

    }

    public int size() {
        return size;
    }

    public String toString() {
        String s = "Students:";
        Node<E> current = head.next();
        while (current.get() != null) {
            s += "[" + current.get() + "]";
            current = current.next();
            s += "";
        }
        return s;

    }

}
