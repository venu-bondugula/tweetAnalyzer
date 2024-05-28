package com.tf.analytics.analyzers;

import com.tf.analytics.models.Tweet;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class MostLikedTweetsAnalyzer implements Analyzer {
    private final PriorityQueue<Tweet> tweets;
    private final int count;

    public MostLikedTweetsAnalyzer(int count) {
        this.count = count;
        tweets = new PriorityQueue<>(count, Comparator.comparingLong(Tweet::getLikeCount));
    }

    @Override
    public void evaluate() {
        ArrayList<Tweet> mostLikedTweets = new ArrayList<>();
        while (!tweets.isEmpty()) {
            mostLikedTweets.add(0, tweets.poll());
        }

        System.out.println("Tweets with highest number of likes");
        mostLikedTweets.forEach(System.out::println);
        System.out.println("------------------");
    }

    @Override
    public void accept(Tweet tweet) {
        if (tweets.size() < count) {
            tweets.add(tweet);
            return;
        }
        if (tweets.peek().getLikeCount() > tweet.getLikeCount())
            return;

        tweets.poll();
        tweets.add(tweet);
    }
}
