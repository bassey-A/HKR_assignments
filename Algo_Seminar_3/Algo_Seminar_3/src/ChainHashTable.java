
public class ChainHashTable {
    private HashLinkedList[] hl = new HashLinkedList[10];

    ChainHashTable() {
        for(int i = 0; i < this.hl.length; ++i) {
            this.hl[i] = new HashLinkedList();
        }

    }

    private int hash(int key) {
        return key % 10;
    }

    public void insert(int key) {
        hl[hash(key)].setKey(key);
    }

    public int getKey(int key) {
        return hl[hash(key)].getKey(key);
    }
}

