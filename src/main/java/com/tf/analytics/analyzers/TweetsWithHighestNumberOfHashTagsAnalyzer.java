package com.tf.analytics.analyzers;

import com.tf.analytics.models.Tweet;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class TweetsWithHighestNumberOfHashTagsAnalyzer implements Analyzer {
    private final PriorityQueue<Tweet> tweets;
    private final int count;

    public TweetsWithHighestNumberOfHashTagsAnalyzer(int count) {
        this.count = count;
        tweets = new PriorityQueue<>(count, Comparator.comparingInt(o -> o.getHashTags().size()));
    }

    @Override
    public void evaluate() {
        ArrayList<Tweet> tweetsWithHighestNumberOfHashTags = new ArrayList<>();
        while (!tweets.isEmpty()) {
            tweetsWithHighestNumberOfHashTags.add(0, tweets.poll());
        }

        System.out.println("Tweets with highest number of hashTags");
        tweetsWithHighestNumberOfHashTags.forEach(System.out::println);
        System.out.println("------------------");
    }

    @Override
    public void accept(Tweet tweet) {
        if (tweets.size() < count) {
            tweets.add(tweet);
            return;
        }
        if (tweets.peek().getHashTags().size() > tweet.getHashTags().size())
            return;

        tweets.poll();
        tweets.add(tweet);
    }
}
