/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeNodes(ListNode head) {
        // Dummy node to simplify the new list creation
        ListNode newHead = new ListNode(0);
        ListNode newCurrent = newHead;

        // Skip the first zero node
        ListNode temp = head.next;

        while (temp != null) {
            int sum = 0;

            // Sum the values until the next zero node
            while (temp != null && temp.val != 0) {
                sum += temp.val;
                temp = temp.next;
            }

            // Create a new node with the sum and add it to the new list
            newCurrent.next = new ListNode(sum);
            newCurrent = newCurrent.next;

            // Move to the next segment
            if (temp != null) {
                temp = temp.next;
            }
        }

        // Return the head of the new list, skipping the initial dummy node
        return newHead.next;
    }
}
