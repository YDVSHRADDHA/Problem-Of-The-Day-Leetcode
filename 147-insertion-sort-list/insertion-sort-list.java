class Solution {
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode dummy = new ListNode(0); // New sorted list starts from here
        ListNode curr = head;             // Current node being inserted

        while (curr != null) {
            ListNode prev = dummy;
            ListNode next = curr.next;

            // Step 1: Find correct position in the sorted part
            while (prev.next != null && prev.next.val < curr.val) {
                prev = prev.next;
            }

            // Step 2: Insert current between prev and prev.next
            curr.next = prev.next;
            prev.next = curr;

            // Step 3: Move to the next node
            curr = next;
        }

        return dummy.next;
    }
}
