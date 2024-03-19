public class HashMap<K,V> {
    private static class Entry {
        Integer key;
        String value;
        Entry next;
        Entry prev;

        Entry(Integer key, String value) {
            this.key = key;
            this.value = value;
            this.next = null;
            this.prev = null;
        }
    }

    private Entry[] buckets;
    private int size;
    private int capacity;
    private final double loadFactor;

    public HashMap() {
        capacity = 11;
        loadFactor = 0.5;
        buckets = new Entry[capacity];
        size = 0;
    }

    public void put(Integer key, String value) {
        int index = hash(key, capacity);
        Entry current = buckets[index];

        if (current == null) {
            // Eğer bucket boşsa, yeni bir Entry ekleyin.
            buckets[index] = new Entry(key, value);
            size++;


        } else {
            // Eğer bucket doluysa, önce anahtarın zaten var olup olmadığını kontrol edin.
            while (true) {
                if (current.key.equals(key)) {
                    // Anahtar zaten varsa, değeri güncelleyin ve metottan çıkın.
                    current.value = value;
                    return;
                }
                if (current.next == null) {
                    // Anahtar yoksa ve listenin sonuna ulaşıldıysa, yeni bir Entry ekleyin.
                    current.next = new Entry(key, value);
                    size++;


                    return;
                }
                current = current.next;
            }
        }
        if ((1.0 * size) / capacity > loadFactor) {
            resize();
        }


    }

    // Yük faktörü kontrolü ve yeniden boyutlandırma.







    public String get(Integer key) {
        int index = hash(key,capacity);
        Entry current = buckets[index];
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    public void remove(Integer key) {
        int index = hash(key,capacity);
        Entry current = buckets[index];
        Entry previous = null;
        while (current != null) {
            if (current.key.equals(key)) {
                if (previous == null) {
                    buckets[index] = current.next;

                    if(buckets[index] == null) {


                    }
                } else {
                    previous.next = current.next;
                }



            }
            previous = current;
            current = current.next;
            size--;
        }
        resize();

    }

    public int capacity() {
        return capacity;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < capacity; i++) {
            sb.append("Bucket ").append(i).append(": ");
            Entry current = buckets[i];
            while (current != null) {
                sb.append("(").append(current.key).append(", ").append(current.value).append(") ");
                current = current.next;
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private int hash(Integer key, int capacity) {
        // Anahtarın hash kodunu hesapla. Bu hesaplama yeni kapasiteye göre yapılmalı.
        String keyString = key.toString();
        int hashCode = 0;
        for (int i = 0; i < keyString.length(); i++) {
            hashCode += (keyString.charAt(i) - '0') * (keyString.length() - i);
        }
        return hashCode % capacity;
    }

    private void resize() {
        System.out.println(capacity + "c" + size);

        int newCapacity = capacity;
        double loadRatio = (1.0 * size) / capacity;

        if (loadRatio > loadFactor) {
            newCapacity = (int)(capacity * 1.5);
        } else if (loadRatio < 0.25 ) {
            newCapacity = (int) Math.ceil(capacity / 2.0);
        }

        if (newCapacity != capacity) {
            Entry[] newBuckets = new Entry[newCapacity];

            for (Entry oldEntry : buckets) {
                while (oldEntry != null) {
                    int index = hash(oldEntry.key, newCapacity);
                    Entry nextEntry = oldEntry.next;

                    // Mevcut Entry'nin next referansını sıfırla
                    oldEntry.next = null;

                    if (newBuckets[index] == null) {
                        newBuckets[index] = oldEntry;
                    } else {
                        Entry current = newBuckets[index];
                        while (current.next != null) {
                            current = current.next;
                        }
                        current.next = oldEntry;
                    }

                    oldEntry = nextEntry;
                }
            }

            buckets = newBuckets;
            capacity = newCapacity;
            System.out.println(capacity + "c" + size);
        }
    }

}





