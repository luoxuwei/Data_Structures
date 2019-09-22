public class QuickUnion1 implements UF {
    int[] id;
    int[] sz;
    public QuickUnion1(int size) {
        id = new int[size];
        sz = new int[size];
        for (int i=0; i<id.length; i++)
            id[i] = i;
        for (int i=0; i<id.length; i++)
            sz[i] = 1;
    }

    @Override
    public int getSize() {
        return id.length;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    private int find(int q) {
        if (q<0 || q>=id.length)
            throw new IllegalArgumentException("");
        int cur = q;
        while (id[cur] != cur)
            cur = id[cur];
        return cur;
    }

    @Override
    public void unionElements(int p, int q) {
        int qid = find(q);
        int pid = find(p);

        if (qid == pid)
            return;

        if (sz[qid]<sz[pid]) {
            id[qid] = pid;
            sz[pid] = sz[pid] + sz[qid];
        } else {
            id[pid] = qid;
            sz[qid] = sz[pid] + sz[qid];
        }

    }
}
