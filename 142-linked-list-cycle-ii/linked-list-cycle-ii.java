/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) return null;

        ListNode slow = head;
        ListNode fast = head;

        // Step 1: Detect if a cycle exists
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            // Cycle detected
            if (slow == fast) {
                // Step 2: Move one pointer to head, keep the other at meeting point
                // Move both one step at a time; the node where they meet is the start of the cycle
                ListNode entry = head;
                while (entry != slow) {
                    entry = entry.next;
                    slow = slow.next;
                }
                return entry; // Start of the cycle
            }
        }

        return null; // No cycle
    }
}
