public class Main {

    public static void main(String[] args) {
        int[] nums = new int[]{4,3,5,2,6,1,7};
        BST<Integer> bst = new BST<>();
        for (int i: nums)
            bst.add(i);

        bst.preOrder();
        System.out.println("----------");
        bst.preOrder1();
        System.out.println("----------");
        bst.levelOrder();
    }
}
