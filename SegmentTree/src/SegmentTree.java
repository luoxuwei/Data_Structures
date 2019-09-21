public class SegmentTree<E> {

    E[] data;
    E[] tree;
    Merger<E> merger;
    public SegmentTree(E[] arr, Merger<E> merger) {
        data = (E[]) new Object[arr.length];
        for (int i=0; i<arr.length; i++) {
            data[i] = arr[i];
        }
        tree = (E[]) new Object[4*arr.length];
        this.merger = merger;
        buildSegmentTree(0, 0, data.length-1);
    }

    private void buildSegmentTree(int treeIndex, int l, int r) {
        if (l == r) {
            tree[treeIndex] = data[l];
            return;
        }

        int lTree = leftChild(treeIndex);
        int rTree = rightChild(treeIndex);
        int mid = l + (r - l)/2;
        buildSegmentTree(lTree, l, mid);
        buildSegmentTree(rTree, mid+1, r);
        tree[treeIndex] = merger.merge(tree[lTree], tree[rTree]);
    }

    public int getSize() {
        return data.length;
    }

    public E get(int index) {
        if (index < 0 || index >= data.length)
            throw new IllegalArgumentException("index out of bound!");
        return data[index];
    }

    public E query(int l, int r) {
        return query(0, 0, data.length-1, l, r);
    }

    private E query(int treeIndex, int l, int r, int ql, int qr) {
        if (l == ql && r == qr) {
            return tree[treeIndex];
        }

        int mid = l + (r - l)/2;
        int lt = leftChild(treeIndex);
        int rt = rightChild(treeIndex);
        if (qr<=mid)
            return query(lt, l, mid, ql, qr);
        else if (ql >mid)
            return query(rt, mid+1, r, ql, qr);

        return merger.merge(query(lt, l, mid, ql, mid), query(rt, mid+1, r,mid+1, qr));
    }

    public void set(int index , E e) {
        data[index] = e;
        update(0, 0, data.length-1, index);
    }

    public void update(int treeIndex, int l, int r, int index) {
        if (l == r) {
            tree[treeIndex] = data[index];
            return ;
        }
        int mid = l + (r-l)/2;
        int lt = leftChild(treeIndex);
        int rt = rightChild(treeIndex);
        if (index<=mid) {
            update(lt, l, mid, index);
        } else {
            update(rt, mid+1, r, index);
        }
        tree[treeIndex] = merger.merge(tree[lt], tree[rt]);
    }

    public int leftChild(int index) {
        return 2*index + 1;
    }

    public int rightChild(int index) {
        return 2*index + 2;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append('[');
        for(int i = 0 ; i < tree.length ; i ++){
            if(tree[i] != null)
                res.append(tree[i]);
            else
                res.append("null");

            if(i != tree.length - 1)
                res.append(", ");
        }
        res.append(']');
        return res.toString();
    }

    public static void main(String[] args) {

        Integer[] nums = {-2, 0, 3, -5, 2, -1};

        SegmentTree<Integer> segTree = new SegmentTree<>(nums,
                (a, b) -> a + b);
        System.out.println(segTree);

        System.out.println(segTree.query(0, 2));
        System.out.println(segTree.query(2, 5));
        System.out.println(segTree.query(0, 5));
    }
}
