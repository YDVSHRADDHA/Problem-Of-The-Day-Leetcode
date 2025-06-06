class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return null;

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prev = dummy;
        ListNode current = head;

        while (current != null) {
            // Check if current node has duplicates
            if (current.next != null && current.val == current.next.val) {
                // Skip all nodes with this duplicate value
                while (current.next != null && current.val == current.next.val) {
                    current = current.next;
                }
                // Remove all duplicates
                prev.next = current.next;
            } else {
                // No duplicates; move prev pointer
                prev = prev.next;
            }
            current = current.next;
        }

        return dummy.next;
    }
}
