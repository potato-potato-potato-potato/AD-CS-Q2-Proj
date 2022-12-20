package HashTable;

public class MyArrayList<E> {
    private Object[] array;
    private int size = 10;// default size
    private int itemNum;// index of the next item to be added

    public MyArrayList() {
        this.array = new Object[size];
        itemNum = 0;
    }

    public boolean arrayAtCompacity() {
        if (itemNum >= size) {
            return true;
        } else {
            return false;
        }
    }

    public boolean add(E obj) {
        try {
            if (arrayAtCompacity()) {
                Object[] temp = new Object[size + 10];
                size += 10;
                for (int i = 0; i < itemNum; i++) {
                    temp[i] = array[i];
                }
                array = temp;

            }

            array[itemNum] = obj;
            itemNum++;

            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        if (index > size) {
            return null;
        } else {
            return (E) array[index];
        }
    }

    @SuppressWarnings("unchecked")
    public E remove(int index) {
        Object temp = array[index];
        array[index] = null;
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        itemNum--;
        return (E) temp;
    }

    @SuppressWarnings("unchecked")
    public E remove(E obj) {
        Object temp;
        int search;
        for (search = 0; search < itemNum; search++) {
            if (array[search] != null && array[search].equals(obj)) {
                temp = array[search];
                array[search] = null;
                for (int i = search; i < size - 1; i++) {
                    array[i] = array[i + 1];
                }
                itemNum--;
                return (E) temp;
            }
        }

        itemNum--;
        return null;
    }

    public void set(int index, E obj) {
        if (array[index] == null) {
            itemNum++;
        }
        array[index] = obj;
    }

    public String toString() {
        String ret = "";
        for (int i = 0; i < size; i++) {
            if (array[i] == null) {
                ret += "null, ";
            } else {
                ret += array[i] + ", ";
            }

        }
        return ret;
    }

    public int size() {
        return itemNum;
    }

}
