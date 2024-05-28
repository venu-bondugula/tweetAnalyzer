package com.tf.analytics.analyzers;

import com.tf.analytics.models.Tweet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MostTweetedUserAnalyzer implements Analyzer {
    private final int count;
    private final HashMap<String, Integer> tweetsPerUser = new HashMap<>();

    public MostTweetedUserAnalyzer(int count) {
        this.count = count;
    }

    @Override
    public void evaluate() {
        List<Map.Entry<String, Integer>> mostTweetedUsers = tweetsPerUser.entrySet()
                .stream()
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
                .limit(count)
//                .map(Map.Entry::getKey)
                .toList();
        System.out.println("Users with highest number of tweets");
        mostTweetedUsers.forEach(System.out::println);
        System.out.println("------------------");
    }

    @Override
    public void accept(Tweet tweet) {
        int tweetCount = 0;
        if (tweetsPerUser.containsKey(tweet.getUserName()))
            tweetCount = tweetsPerUser.get(tweet.getUserName());
        tweetsPerUser.put(tweet.getUserName(), tweetCount + 1);
    }
}
