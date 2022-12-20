package HashTable;

public class MyHashSet<E> {
    private int size = 0;
    private Object[] hashTable;

    public MyHashSet() {
        hashTable = new Object[10000];
    }

    public boolean add(E obj) {
        int location = obj.hashCode() % hashTable.length;
        if (hashTable[location] == null) {
            hashTable[location] = obj;
            size++;
            return true;
        }
        return false;
    }

    public void clear() {
        hashTable = new Object[100];
        size = 0;
    }

    public boolean contains(Object obj) {
        int location = obj.hashCode() % hashTable.length;
        // System.out.println("location: " + location);
        return (hashTable[location] != null);
    }

    public boolean remove(Object obj) {
        int location = obj.hashCode() % hashTable.length;
        if (hashTable[location] != null) {
            hashTable[location] = null;
            size--;
            return true;
        }
        return false;
    }

    public Object get(int index) {
        return hashTable[index];
    }

    public String toString() {
        String ret = "";
        for (int i = 0; i < hashTable.length; i++) {
            if (hashTable[i] != null) {
                ret += hashTable[i].toString();
                ret += "\n";
            }
        }
        return ret;
    }

    public String toString(int id) {
        String ret = "";
        ret += get(id).toString();
        return ret;
    }

    public int size() {
        return size;
    }

    @SuppressWarnings("unchecked")
    public MyArrayList<E> toArrayList() {
        MyArrayList<E> ret = new MyArrayList<E>();
        for (int i = 0; i < hashTable.length; i++) {
            if (hashTable[i] != null) {
                ret.add((E) hashTable[i]);
            }
        }
        return ret;
    }
}