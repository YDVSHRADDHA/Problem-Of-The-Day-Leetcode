class Solution {
    public List<List<String>> mostPopularCreator(String[] creators, String[] ids, int[] views) {
        Map<String, Long> totalViews = new HashMap<>();
        Map<String, String> mostViewedId = new HashMap<>();
        Map<String, Integer> maxViews = new HashMap<>();
        
        int n = creators.length;

        for (int i = 0; i < n; i++) {
            String creator = creators[i];
            String id = ids[i];
            int view = views[i];
            
            // update total views
            totalViews.put(creator, totalViews.getOrDefault(creator, 0L) + view);
            
            // update most viewed id
            if (!maxViews.containsKey(creator) || 
                view > maxViews.get(creator) || 
                (view == maxViews.get(creator) && id.compareTo(mostViewedId.get(creator)) < 0)) {
                    
                maxViews.put(creator, view);
                mostViewedId.put(creator, id);
            }
        }

        // find max popularity
        long maxPopularity = Collections.max(totalViews.values());

        List<List<String>> result = new ArrayList<>();

        for (String creator : totalViews.keySet()) {
            if (totalViews.get(creator) == maxPopularity) {
                result.add(Arrays.asList(creator, mostViewedId.get(creator)));
            }
        }

        return result;
    }
}
