import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
//        int[] nums = new int[]{4,3,5,2,6,1,7};
//        BST<Integer> bst = new BST<>();
//        for (int i: nums)
//            bst.add(i);
//
//        bst.preOrder();
//        System.out.println("----------");
//        bst.preOrder1();
//        System.out.println("----------");
//        bst.levelOrder();
//        System.out.println("minimum: "+bst.minimum()+", maximum: "+bst.maximum());
//        System.out.println("----------");
//        System.out.println("minimumNR: "+bst.minimumNR()+", maximumNR: "+bst.maximumNR());
        Random random = new Random();
        BST<Integer> bst = new BST<>();
        for (int i=0; i<1000; i++) {
            bst.add(random.nextInt(10000));
        }
        ArrayList<Integer> nums = new ArrayList<>();
        while (!bst.isEmpty()) {
//            nums.add(bst.removeMinimum());
            nums.add(bst.removeMinimumNR());
        }
        System.out.println(nums);
        for(int i=1; i<nums.size(); i++) {
            if (nums.get(i-1)>nums.get(i))
                throw new IllegalArgumentException("Error");
        }
        System.out.println("removeMinimun test completed!");
        int[] tests = new int[]{4,3,5,2,6,1,7};
        bst = new BST<>();
        for (int i: tests)
            bst.add(i);

        bst.inOrderNR();

    }
}
