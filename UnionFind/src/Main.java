import java.util.Random;

public class Main {

    private static double test(UF uf, int m) {

        long begin = System.nanoTime();
        Random random = new Random();
        int a,b;
        for (int i=0; i<m; i++) {
            a = random.nextInt(m);
            b = random.nextInt(m);
            uf.unionElements(a, b);
        }

        for (int i=0; i<m; i++) {
            a = random.nextInt(m);
            b = random.nextInt(m);
            uf.isConnected(a, b);
        }
        return (System.nanoTime() - begin)/1000000000.0;
    }

    public static void main(String[] args) {
	// write your code here
        //size越大 quick find 实现的合并操作O（n）复杂度的劣势更明显，加大size 两种实现的差异更明显
        //m 越大 quick union 实现的find 操作O(h) 复杂度的劣势更明显，加大m 会导致更多的元素合并到一个集合h会变大
        //quick find 由于都是在连续空间遍历，还有语言平台底层优化的优势，quick union 需要在不同元素见跳转无法优化。加大m返回会导致 quick find 的耗时更小
        //有些算法复杂度更底的实现，在测试数据少的时候反而会耗时更多，size = 100000 是 路径压缩的实现耗时反而更高，当size = 1000000000 的规模时才体现初优势
        //路径压缩的优化是在rank 优化的基础上实现的，由于rank数组存的是集合的路径深度，理论上路径压缩后需要重新维护rank，但其实路径压缩对每个集合是等效的，所以
        //做完路径压缩后，虽然rank的数据不等于集合的深度，但不同集合的rank值的大小关系还是不变的，所以可以不重新更新rank数组，此时的rank不代表集合的深度，可以理解为
        //权重
        int size = 100000000;
        int m = 100000000;

        UF uf = new QuickFind(size);
//        System.out.println("quick find: "+test(uf, m));
//        uf = new QuickUnion(size);
//        System.out.println("quick union: "+test(uf, m));
        uf = new QuickUnion1(size);
        System.out.println("quick union 1: "+test(uf, m));
        uf = new QuickUnion2(size);
        System.out.println("quick union 2: "+test(uf, m));
        uf = new QuickUnion3(size);
        System.out.println("quick union 3: "+test(uf, m));
    }
}
