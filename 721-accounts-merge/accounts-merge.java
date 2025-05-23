class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> emailToName = new HashMap<>();
        Map<String, String> parent = new HashMap<>();

        // Step 1: Initialize each email's parent and map email to name
        for (List<String> account : accounts) {
            String name = account.get(0);
            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);
                parent.put(email, email);
                emailToName.put(email, name);
            }
        }

        // Step 2: Union emails in the same account
        for (List<String> account : accounts) {
            String baseEmail = account.get(1);
            for (int i = 2; i < account.size(); i++) {
                union(parent, baseEmail, account.get(i));
            }
        }

        // Step 3: Group emails by their root parent
        Map<String, TreeSet<String>> unions = new HashMap<>();
        for (String email : parent.keySet()) {
            String root = find(parent, email);
            unions.computeIfAbsent(root, x -> new TreeSet<>()).add(email);
        }

        // Step 4: Build the final result
        List<List<String>> result = new ArrayList<>();
        for (String root : unions.keySet()) {
            List<String> emails = new ArrayList<>(unions.get(root));
            emails.add(0, emailToName.get(root));
            result.add(emails);
        }

        return result;
    }

    private String find(Map<String, String> parent, String email) {
        if (!parent.get(email).equals(email)) {
            parent.put(email, find(parent, parent.get(email))); // Path compression
        }
        return parent.get(email);
    }

    private void union(Map<String, String> parent, String email1, String email2) {
        String root1 = find(parent, email1);
        String root2 = find(parent, email2);
        if (!root1.equals(root2)) {
            parent.put(root2, root1);
        }
    }
}
