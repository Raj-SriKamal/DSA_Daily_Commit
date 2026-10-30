
package DesignDataStructure;

import java.util.*;

public class Twitter {

    // Each tweet is a node in a linked list
    class Tweet {
        int tweetId;
        long time;
        Tweet next;

        Tweet(int tweetId, long time) {
            this.tweetId = tweetId;
            this.time = time;
        }
    }

    // User ID -> Latest tweet
    Map<Integer, Tweet> tweets;

    // Follower ID -> Set of followee IDs
    Map<Integer, Set<Integer>> following;

    // Global timestamp
    long timestamp;

    public Twitter() {
        tweets = new HashMap<>();
        following = new HashMap<>();
        timestamp = 0;
    }

    // Post a tweet
    public void postTweet(int userId, int tweetId) {
        Tweet newTweet = new Tweet(tweetId, timestamp++);

        newTweet.next = tweets.get(userId);
        tweets.put(userId, newTweet);
    }

    // Get 10 most recent tweets
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> result = new ArrayList<>();

        // Max Heap based on timestamp
        PriorityQueue<Tweet> pq =
            new PriorityQueue<>(
                (a, b) -> Long.compare(b.time, a.time)
            );

        // Include user's own tweets
        if (tweets.containsKey(userId)) {
            pq.offer(tweets.get(userId));
        }

        // Include tweets of followed users
        Set<Integer> users = following.get(userId);

        if (users != null) {
            for (int followeeId : users) {
                if (tweets.containsKey(followeeId)) {
                    pq.offer(tweets.get(followeeId));
                }
            }
        }

        // Extract latest 10 tweets
        while (!pq.isEmpty() && result.size() < 10) {
            Tweet current = pq.poll();
            result.add(current.tweetId);

            // Add next older tweet from same user
            if (current.next != null) {
                pq.offer(current.next);
            }
        }

        return result;
    }

    // Follow another user
    public void follow(int followerId, int followeeId) {
        if (followerId == followeeId) {
            return;
        }

        following.computeIfAbsent(
            followerId,
            k -> new HashSet<>()
        ).add(followeeId);
    }

    // Unfollow another user
    public void unfollow(int followerId, int followeeId) {
        if (followerId == followeeId) {
            return;
        }

        Set<Integer> users = following.get(followerId);

        if (users != null) {
            users.remove(followeeId);
        }
    }

    public static void main(String[] args) {
        Twitter twitter = new Twitter();

        twitter.postTweet(1, 5);
        System.out.println(twitter.getNewsFeed(1));

        twitter.follow(1, 2);
        twitter.postTweet(2, 6);

        System.out.println(twitter.getNewsFeed(1));

        twitter.unfollow(1, 2);
        System.out.println(twitter.getNewsFeed(1));
    }
}
