public class HashTable {
    private int[] arr;
    private boolean[] isSet;
    private boolean isLinear;
    private boolean hashAlg;
    int size = 0;

    public HashTable(boolean isLinear, boolean hashAlg) {
        byte l;
        if (hashAlg) {
            l = 7;
        } else {
            l = 10;
        }

        this.arr = new int[l];
        this.isSet = new boolean[l];

        for(int i = 0; i < this.isSet.length; ++i) {
            this.isSet[i] = false;
        }

        this.isLinear = isLinear;
        this.hashAlg = hashAlg;
    }

    private int hash(int key) {
        return hashAlg ? 7 - key % 7 : key % 10;
    }

    private void rehash() {
        if (size / 2 == arr.length / 2) {
            int[] temp = arr;
            boolean[] tempSet = isSet;
            isSet = new boolean[arr.length * 2];
            arr = new int[arr.length * 2];

            for(int i = 0; i < arr.length / 2; ++i) {
                if (tempSet[i]) {
                    insert(temp[i]);
                }
            }
        }

    }

    public void insert(int key) {
        rehash();
        int i;
        if (isLinear) {
            for(i = key; i < arr.length; ++i) {
                if (!isSet[hash(i)]) {
                    isSet[hash(i)] = true;
                    arr[hash(i)] = key;
                    ++size;
                    return;
                }
            }

            System.out.println("Could not insert Hash.");
        } else {
            for(i = 0; isSet[hash(i + key)]; i *= i) {
                ++i;
            }

            isSet[hash(i + key)] = true;
            arr[hash(i + key)] = key;
            ++size;
        }

    }

    public void delete(int key) {
        int i;
        if (isLinear) {
            for(i = key; i < arr.length && isSet[hash(i)]; ++i) {
                if (arr[hash(i)] == key) {
                    isSet[hash(i)] = false;
                    --size;
                    break;
                }
            }
        } else {
            for(i = 0; isSet[hash(i + key)]; i *= i) {
                if (arr[hash(i + key)] == key) {
                    isSet[hash(i + key)] = false;
                    --size;
                    break;
                }

                ++i;
            }
        }

    }

    public void output() {
        for(int i = 0; i < arr.length; ++i) {
            System.out.println(arr[i]);
        }

    }

    public boolean find(int key) {
        boolean k = false;
        int i;
        if (isLinear) {
            for(i = key; i < arr.length && isSet[hash(i)]; ++i) {
                if (arr[hash(i)] == key) {
                    k = true;
                    break;
                }
            }
        } else {
            for(i = 0; isSet[hash(i + key)]; i *= i) {
                if (arr[hash(i + key)] == key) {
                    k = true;
                    break;
                }

                ++i;
            }
        }

        return k;
    }
}