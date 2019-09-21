import java.util.ArrayList;
import java.util.Random;

public class MaxHeap<E extends Comparable<E>> {
    private Array<E> data;
    public MaxHeap(int capacity) {
        data = new Array<>(capacity);
    }

    public MaxHeap() {
        this(10);
    }

    public int size() {
        return data.getSize();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public int parent(int index) {
        if (index == 0)
            throw new IllegalArgumentException();

        return (index-10)/2;
    }

    public int leftChild(int index) {
        return index*2 + 1;
    }

    public int rightChild(int index) {
        return index*2 + 2;
    }

    public void add(E e) {
        data.addLast(e);
    }

    private void siftUp(int index) {
        int p = parent(index);
        E temp;
        while (index>0 && data.get(p).compareTo(data.get(index))<0) {
            temp = data.get(p);
            data.set(p, data.get(index));
            data.set(index, temp);
            index = p;
            p = parent(index);
        }
    }

    public E extractMax() {

        E res = data.getFirst();
        E last = data.removeLast();
        if (!data.isEmpty()) {
            data.set(0, last);
            siftDown(0);
        }

        return res;
    }

    private void siftDown(int index) {
        int l = leftChild(index), r = rightChild(index);
        int max = 0;
        if (l >= data.getSize()) {
            return;
        }
        if (r < data.getSize()) {
            max = data.get(l).compareTo(data.get(r))>0?l:r;
        } else {
            max = l;
        }
        E temp;
        while (l < data.getSize() && data.get(max).compareTo(data.get(index)) > 0 ) {
            temp = data.get(index);
            data.set(index, data.get(max));
            data.set(max, temp);
            index = max;
            l = leftChild(index);
            r = rightChild(index);
            if (r < data.getSize()) {
                max = data.get(l).compareTo(data.get(r))>0?l:r;
            } else {
                max = l;
            }
        }
    }

    public static void main(String[] args) {
        Random random = new Random();
        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        for (int i=0; i<1000; i++) {
            maxHeap.add(random.nextInt(10000));
        }

        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i=0; i<1000; i++)
            arrayList.add(maxHeap.extractMax());

        for (int i=1; i<1000; i++) {
            if (arrayList.get(i-1)<arrayList.get(i)) {
                throw new IllegalArgumentException("Error");
            }
        }

        System.out.println("Test maxheap completed.");

    }

}
