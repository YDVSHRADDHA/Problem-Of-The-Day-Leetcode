/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode swapPairs(ListNode head) {
        // Create a dummy node to simplify swapping the head
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode current = dummy;

        // Iterate through the list in pairs
        while (current.next != null && current.next.next != null) {
            ListNode first = current.next;
            ListNode second = current.next.next;

            // Swap the nodes
            first.next = second.next;
            second.next = first;
            current.next = second;

            // Move current pointer ahead
            current = first;
        }

        return dummy.next;
    }
}
