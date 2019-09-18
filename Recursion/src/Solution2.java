//https://leetcode-cn.com/problems/remove-linked-list-elements/submissions/
public class Solution2 {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode prv = dummyHead;

        while (prv.next != null) {
            if (prv.next.val == val) {
                prv.next = prv.next.next;
            } else {
                prv = prv.next;
            }

        }
        return dummyHead.next;
    }

    public static void main(String[] a) {
        ListNode test = new ListNode(new int[]{1,2,6,3,4,5,6});
        System.out.println(test);
        test = new Solution2().removeElements(test, 6);
        System.out.println(test);
    }
}
