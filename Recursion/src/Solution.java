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
}
