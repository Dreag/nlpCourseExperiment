package org.gaoyang.nlpCourseExperiment;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ForwardMaximunMatching {

    public Set<String> getDictonary() throws Exception {

        // 使用hashset来存放字典中的词
        Set<String> dictionary = new HashSet<>();
        File DictionaryPath = new File("library/userDefinedDict.dic");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(DictionaryPath), StandardCharsets.UTF_8));
        String lineText;

        while (((lineText = bufferedReader.readLine()) != null)) {
            // 按照空格切分，只读取第二部分,这里不能设置为“”，否则split会将其按字分片
            String[] str = lineText.split(" ");
            lineText = str[0];
            dictionary.add(lineText);
        }
        bufferedReader.close();

        return dictionary;
    }

    Integer getMaxWordLength(Set<String> dictionary) {
        /*
        寻找用户字典中的最长子串，并返回字串长度，时间复杂度为O（n）
         */
        Iterator<String> item = dictionary.iterator();
        int MaxWordLength = 0;
        while (item.hasNext()) {
            String word = item.next();
            if (MaxWordLength < word.length()) {
                MaxWordLength = word.length();
            }
        }
        return MaxWordLength;
    }

    public List<String> ForwardMatching(String Text, Set<String> dictionary, Integer MaxWordLength) {

        long starttime = System.nanoTime();
        List<String> results = new ArrayList<>();
        String inputString = Text;

        while (inputString.length() > 0) {
            String subSquence;
            if (inputString.length() < MaxWordLength) {
                subSquence = inputString;
            } else {
                subSquence = inputString.substring(0, MaxWordLength);
            }
            while (subSquence.length() > 0) {
                // 如果字典中含有该子串或者子串粒度为1，则子串匹配成功
                if (dictionary.contains(subSquence) || subSquence.length() == 1) {
                    results.add(subSquence);
                    inputString = inputString.substring(subSquence.length());
                    break;
                } else {
                    subSquence = subSquence.substring(0, subSquence.length() - 1);
                }
            }
        }
        long endtime = System.nanoTime();
        System.out.println("Forward.. Run Time：" + (endtime - starttime) + "ns");

        return results;
    }
}
