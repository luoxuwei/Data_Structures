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
        int size = 100000;
        int m = 100000;

        UF uf = new QuickFind(size);
        System.out.println("quick find: "+test(uf, m));
        uf = new QuickUnion(size);
        System.out.println("quick union: "+test(uf, m));
        uf = new QuickUnion1(size);
        System.out.println("quick union 1: "+test(uf, m));
    }
}
