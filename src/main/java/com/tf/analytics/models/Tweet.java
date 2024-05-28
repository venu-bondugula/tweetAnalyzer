package com.tf.analytics.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
@ToString
public class Tweet {
    private String userName;
    private String tweetId;
    private long likeCount;
    private List<String> hashTags;
    private String conversationId;
}
