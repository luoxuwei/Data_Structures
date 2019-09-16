import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int testCount = 100000;
        System.out.println("array queue "+testQueue(new ArrayQueue<>(), testCount));
        System.out.println("loop queue "+testQueue(new LoopQueue<>(), testCount));
    }

    private static double testQueue(Queue<Integer> queue, int count) {
        long begin = System.nanoTime();
        Random random = new Random();
        for (int i=0; i<count; i++) {
            queue.enqueue(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i=0; i<count; i++) {
            queue.dequeue();
        }
        return (System.nanoTime() - begin)/1000000000.0;
    }
}
