class Solution {
    class TrieNode {
        TreeMap<String, TrieNode> children;
        boolean isEndOfDir;
        boolean deleted;
        
        public TrieNode() {
            children = new TreeMap<>();
            isEndOfDir = false;
            deleted = false;
        }
    }

    public void insert(TrieNode root, List<String> path) {
        TrieNode cur = root;
        
        for(int depth=0; depth<path.size(); depth++) {
            String ch = path.get(depth);
            
            if(!cur.children.containsKey(ch)) {
                cur.children.put(ch, new TrieNode());
            }
            
            cur = cur.children.get(ch);
        }
        
        cur.isEndOfDir = true;
        
    }

    TrieNode root;
    HashMap<String, ArrayList<TrieNode>> hm;
    List<List<String>> ansLst;
    
    public List<List<String>> deleteDuplicateFolder(List<List<String>> paths) {
        root = new TrieNode();
        hm = new HashMap<>();
        ansLst = new ArrayList<>();

        for(int i=0; i<paths.size(); i++) {
            insert(root, paths.get(i));
        }

        // Store all the child structures of Nodes in HashMap
        dfs(root);

        // If the node has the same child structure with other nodes,
        // make node.deleted = true.
        for(String key : hm.keySet()) {
            if(hm.get(key).size() > 1) {
                for(TrieNode node : hm.get(key)) {
                    node.deleted = true;
                }
            }
        }

        // Traverse to store survived Nodes in the Trie.
        helper(root, new ArrayList<>());

        return ansLst;   
    }

    public void helper(TrieNode root, ArrayList<String> lst) {
        if(root.deleted) return;

        for(String key: root.children.keySet()) {
            lst.add(key);
            helper(root.children.get(key), lst);
            lst.remove(lst.size()-1);
        }

        if(lst.size() > 0) {
            ArrayList<String> tmp = new ArrayList<>();
            for(String s : lst) {
                tmp.add(s);
            }
            ansLst.add(tmp);
        }
    }

    public String dfs(TrieNode root) {
        StringBuilder sb = new StringBuilder();
        
        for(String key: root.children.keySet()) {
            sb.append(key + "#");
        }

        for(String key: root.children.keySet()) {
            sb.append("$" + dfs(root.children.get(key)));
        }
                
        if(!hm.containsKey(sb.toString())) {
            hm.put(sb.toString(), new ArrayList<>());
        }

        if(sb.toString().length() > 0) {
            hm.get(sb.toString()).add(root);
        }

        return sb.toString();
    }
}