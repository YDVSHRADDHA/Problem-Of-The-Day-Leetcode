import java.util.*;

class Twitter {
    private static int timestamp = 0; // Global timestamp to keep track of tweet times

    // Tweet class to hold tweetId and timestamp
    private class Tweet {
        int id;
        int time;

        public Tweet(int id, int time) {
            this.id = id;
            this.time = time;
        }
    }

    // Maps userId -> list of tweets posted by user
    private Map<Integer, List<Tweet>> tweetsByUser;

    // Maps followerId -> set of followeeIds they follow
    private Map<Integer, Set<Integer>> following;

    public Twitter() {
        tweetsByUser = new HashMap<>();
        following = new HashMap<>();
    }
    
    // User posts a tweet
    public void postTweet(int userId, int tweetId) {
        tweetsByUser.putIfAbsent(userId, new ArrayList<>());
        tweetsByUser.get(userId).add(new Tweet(tweetId, timestamp++));
    }
    
    // Retrieve the 10 most recent tweet ids in user's news feed (own + followed users)
    public List<Integer> getNewsFeed(int userId) {
        PriorityQueue<Tweet> maxHeap = new PriorityQueue<>((a, b) -> b.time - a.time);

        // Add user's own tweets
        if (tweetsByUser.containsKey(userId)) {
            maxHeap.addAll(tweetsByUser.get(userId));
        }

        // Add tweets from all followees
        Set<Integer> followees = following.getOrDefault(userId, new HashSet<>());
        for (int followeeId : followees) {
            if (tweetsByUser.containsKey(followeeId)) {
                maxHeap.addAll(tweetsByUser.get(followeeId));
            }
        }

        // Extract up to 10 most recent tweets
        List<Integer> newsFeed = new ArrayList<>();
        int count = 0;
        while (!maxHeap.isEmpty() && count < 10) {
            newsFeed.add(maxHeap.poll().id);
            count++;
        }

        return newsFeed;
    }
    
    // User followerId follows followeeId
    public void follow(int followerId, int followeeId) {
        if (followerId == followeeId) return; // cannot follow yourself
        following.putIfAbsent(followerId, new HashSet<>());
        following.get(followerId).add(followeeId);
    }
    
    // User followerId unfollows followeeId
    public void unfollow(int followerId, int followeeId) {
        if (following.containsKey(followerId)) {
            following.get(followerId).remove(followeeId);
        }
    }
}
