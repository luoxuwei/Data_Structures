public class Main {

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4,5,6,7};
        BST<Integer> bst = new BST<>();
        for (int i: nums)
            bst.add(i);

        bst.preOrder();
    }
}
