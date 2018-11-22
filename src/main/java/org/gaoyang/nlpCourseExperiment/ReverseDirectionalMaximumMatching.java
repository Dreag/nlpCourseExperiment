package org.gaoyang.nlpCourseExperiment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class ReverseDirectionalMaximumMatching {

    public List<String> ReverseMatching(String Text, Set<String> dictionary, Integer MaxWordLength) {

        // 使用
        long starttime = System.nanoTime();
        List<String> results = new ArrayList<>();
        String subsequence;

        while (Text.length() > 0) {
            if (Text.length() < MaxWordLength) {
                subsequence = Text;
            } else {
                subsequence = Text.substring(Text.length() - MaxWordLength);
            }

            while (subsequence.length() > 0) {
                if (dictionary.contains(subsequence) || subsequence.length() == 1) {
                    results.add(subsequence);
                    Text = Text.substring(0, Text.length() - subsequence.length());
                    break;
                } else {
                    subsequence = subsequence.substring(1);
                }
            }
        }
        long endtime = System.nanoTime();
        System.out.println("Reverse.. Run Time: " + (endtime-starttime) + "ns");
        Collections.reverse(results);
        return results;
    }
}
