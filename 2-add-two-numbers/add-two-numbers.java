class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0); // Dummy node to simplify result list creation
        ListNode current = dummy; 
        int carry = 0; // To store carry during addition

        while (l1 != null || l2 != null || carry != 0) {
            int sum = carry; // Start with carry from previous step

            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }

            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }

            carry = sum / 10; // Compute carry for next addition
            current.next = new ListNode(sum % 10); // Create new node for sum % 10
            current = current.next;
        }

        return dummy.next; // Return head of the result list (skip dummy node)
    }
}
