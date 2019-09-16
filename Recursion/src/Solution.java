//https://leetcode-cn.com/problems/remove-linked-list-elements/submissions/
public class Solution {
    public ListNode removeElements(ListNode head, int val) {
        while (head != null && head.val == val) {
            head = head.next;
        }
        ListNode prv = head;
        while (prv != null && prv.next != null) {

            if (prv.next.val == val) {
                prv.next = prv.next.next;
            } else {
                prv = prv.next;
            }

        }
        return head;
    }

    public static void main(String[] a) {
        ListNode test = new ListNode(new int[]{1,2,6,3,4,5,6});
        System.out.println(test);
        test = new Solution().removeElements(test, 6);
        System.out.println(test);
    }
}
