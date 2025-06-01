class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // Create a dummy node to start the merged list
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;

        // Compare nodes and build the sorted list
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }

        // Attach the remaining part
        current.next = (list1 != null) ? list1 : list2;

        return dummy.next;
    }
}
