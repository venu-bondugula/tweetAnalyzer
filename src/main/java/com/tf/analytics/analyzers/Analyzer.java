package com.tf.analytics.analyzers;

import com.tf.analytics.models.Tweet;

import java.util.function.Consumer;

public interface Analyzer extends Consumer<Tweet> {

    void evaluate();
}
