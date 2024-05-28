package com.tf.analytics.analyzers;

import com.tf.analytics.models.Tweet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LongestConverstaionAnalyzer implements Analyzer {
    private final int count;
    private final HashMap<String, Integer> tweetsPerConversation = new HashMap<>();


    public LongestConverstaionAnalyzer(int count) {
        this.count = count;
    }

    @Override
    public void evaluate() {
        List<Map.Entry<String, Integer>> mostTweetedUsers = tweetsPerConversation.entrySet()
                .stream()
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
                .limit(count)
//                .map(Map.Entry::getKey)
                .toList();
        System.out.println("IDs of the longest conversations");
        mostTweetedUsers.forEach(System.out::println);
        System.out.println("------------------");
    }

    @Override
    public void accept(Tweet tweet) {
        int tweetCount = 0;
        if (tweetsPerConversation.containsKey(tweet.getConversationId()))
            tweetCount = tweetsPerConversation.get(tweet.getConversationId());
        tweetsPerConversation.put(tweet.getConversationId(), tweetCount + 1);
    }
}
