package HashTable;

public class MyHashTable<K, V> {
    private MyHashSet<K> keySet;
    private MyArrayList<V>[] table;

    @SuppressWarnings("unchecked")
    public MyHashTable() {
        keySet = new MyHashSet<K>();
        table = new MyArrayList[10000];
    }

    public void put(K key, V value) {
        if (keySet.contains(key)) {
            int index = key.hashCode() % table.length;
            table[index].add(value);
        } else {
            keySet.add(key);
            int index = key.hashCode() % table.length;
            table[index] = new MyArrayList<V>();
            table[index].add(value);
        }

    }

    public void put(K key, V value, int Arrayindex) {
        if (keySet.contains(key)) {
            int index = key.hashCode() % table.length;
            table[index].set(Arrayindex, value);
        } else {
            keySet.add(key);
            int index = key.hashCode() % table.length;
            table[index] = new MyArrayList<V>();
            table[index].set(Arrayindex, value);
        }

    }

    public MyArrayList<V> get(K key) {
        int index = key.hashCode() % table.length;
        return table[index];
    }

    public MyHashSet<K> keySet() {
        return keySet;
    }

    public void remove(K key, V value) {
        if (table[key.hashCode() % table.length] == null) {
            remove(key);
        } else {
            table[key.hashCode() % table.length].remove(value);
        }
    }

    public void remove(K key, int index) {
        if (table[key.hashCode() % table.length] == null) {
            remove(key);
        } else {
            table[key.hashCode() % table.length].remove(index);
        }
    }

    public void remove(K key) {
        int index = key.hashCode() % table.length;
        table[index] = null;
        keySet.remove(key);
    }

    public String toString() {
        String ret = "";
        MyArrayList<K> keyList = keySet.toArrayList();
        for (int i = 0; i < keyList.size(); i++) {
            ret += "bucket :" + keyList.get(i).hashCode() % table.length + " " + keyList.get(i).toString() + "\n";
        }
        return ret;
    }

}
