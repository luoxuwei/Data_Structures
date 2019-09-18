//https://leetcode-cn.com/problems/remove-linked-list-elements/submissions/
public class Solution3 {
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        ListNode subList = removeElements(head.next, val);
        if (head.val == val) {
            return subList;
        } else {
            head.next = subList;
            return head;
        }
    }

    public static void main(String[] a) {
        ListNode test = new ListNode(new int[]{1,2,6,3,4,5,6});
        System.out.println(test);
        test = new Solution3().removeElements(test, 6);
        System.out.println(test);
    }
}
