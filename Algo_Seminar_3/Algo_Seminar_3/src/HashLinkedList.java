public class HashLinkedList {
    HashLinkedList left;
    HashLinkedList right;
    int key;
    boolean isSet;

    HashLinkedList() {
        this.left = null;
        this.right = null;
        this.isSet = false;
    }

    HashLinkedList(HashLinkedList l) {
        this.left = l;
        this.right = null;
        this.isSet = false;
    }

    public void setKey(int key) {
        if (this.isSet && key != this.key) {
            if (this.right == null) {
                this.right = new HashLinkedList(this);
                this.right.setKey(key);
            } else {
                this.right.setKey(key);
            }
        } else {
            this.key = key;
            this.isSet = true;
        }

    }

    public int getKey(int key) {
        if (this.key == key) {
            return key;
        } else {
            return this.right != null ? this.right.getKey(key) : -1;
        }
    }
}

