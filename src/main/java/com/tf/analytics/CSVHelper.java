package com.tf.analytics;

import com.opencsv.CSVReader;
import com.tf.analytics.models.Tweet;

import java.io.FileReader;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CSVHelper {
    private final String CSV_FILE_PATH = "chatgpt1.csv";

    public List<Tweet> loadCSV() {
        ArrayList<Tweet> tweets = new ArrayList<>();
        try {
            URL resource = getClass().getClassLoader().getResource(CSV_FILE_PATH);
            FileReader filereader = null;
            if (resource != null) {
                filereader = new FileReader(resource.getFile());
            }

            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;

            csvReader.readNext();
            while (((nextRecord = csvReader.readNext()) != null)) {
                tweets.add(toTweet(nextRecord));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tweets;
    }

    private Tweet toTweet(String[] nextRecord) {
        // 0. DateTime
        // 1. Tweet Id
        // 2. Text
        // 3. Username
        // 4. Permalink
        // 5. User
        // 6. Outlinks
        // 7. CountLinks
        // 8. ReplyCount
        // 9. RetweetCount
        // 10. LikeCount
        // 11. QuoteCount
        // 12. ConversationId
        // 13. Language
        // 14. Source
        // 15Media
        // 16 QuotedTweet
        // 17. MentionedUsers
        // 18. hashtag
        // 19. hastag_counts

        return Tweet.builder().conversationId(nextRecord[12])
                .tweetId(nextRecord[1])
                .likeCount(Integer.parseInt(nextRecord[10]))
                .userName(nextRecord[3])
                .hashTags(toHashTags(nextRecord[18]))
                .build();

    }

    private List<String> toHashTags(String hashTagString) {
        if (hashTagString.length() == 2)
            return Collections.emptyList();
        hashTagString = hashTagString.substring(1, hashTagString.length() - 1);
        hashTagString = hashTagString.replaceAll("\"", "");
        hashTagString = hashTagString.replaceAll("'", "");
        hashTagString = hashTagString.replaceAll(",", "");
        return Arrays.stream(hashTagString.split("#")).filter(s -> !s.isEmpty()).toList();
        //["#","#","#"]
    }
}
