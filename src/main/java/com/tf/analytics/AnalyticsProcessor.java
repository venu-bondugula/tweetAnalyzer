package com.tf.analytics;

import com.tf.analytics.analyzers.*;
import com.tf.analytics.models.Tweet;

import java.util.List;

public class AnalyticsProcessor {
    /*
    Top 5 authors by tweet count
Top 5 liked tweets
Top 5 hashtags
Top 5 conversations
     */
    private final List<Analyzer> analyzers;

    public AnalyticsProcessor() {
        analyzers = List.of(
                new MostTweetedUserAnalyzer(5),
                new MostLikedTweetsAnalyzer(5),
                new TweetsWithHighestNumberOfHashTagsAnalyzer(5),
                new LongestConverstaionAnalyzer(5)
        );
    }

    public void performAnalytics() {
        CSVHelper csvHelper = new CSVHelper();
        List<Tweet> tweets = csvHelper.loadCSV();

        tweets.forEach(tweet -> analyzers.forEach(analyzer -> analyzer.accept(tweet)));
        analyzers.forEach(Analyzer::evaluate);
    }
}
