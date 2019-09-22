//路径压缩进一步优化，每次将集合的路径压缩为2，但并不比上个版本耗时少，是因为，上个版本在不断查找（压缩）之后也会将集合的路径压缩为最小2，另外这个版本的实现用到了递归
//递归调用也会更加耗时
public class QuickUnion4 implements UF {
    int[] id;
    int[] rank;
    public QuickUnion4(int size) {
        id = new int[size];
        rank = new int[size];
        for (int i=0; i<id.length; i++)
            id[i] = i;
        for (int i=0; i<id.length; i++)
            rank[i] = 1;
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
        if (id[cur] != cur) {
            id[cur] = find(id[cur]);
        }
        return id[cur];
    }

    @Override
    public void unionElements(int p, int q) {
        int qid = find(q);
        int pid = find(p);

        if (qid == pid)
            return;

        if (rank[qid]< rank[pid]) {
            id[qid] = pid;
        } else if (rank[pid] < rank[pid]) {
            id[pid] = qid;
        } else {
            id[pid] = qid;
            rank[qid] = rank[qid] + 1;
        }

    }
}
