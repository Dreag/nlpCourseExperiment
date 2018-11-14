package org.gaoyang.nlpCourseExperiment;

import java.io.*;
import java.util.*;

public class ForwardMaximunMatching {

    public Set<String> getDictonary() {

        // 使用hashset来存放字典中的词
        Set<String> dictionary = new HashSet<String>();
        File DictionaryPath = new File("data/userDefinedDict.dic");
        // 使用List来存放字典中的词
        //List<String> list = new ArrayList<String>();

        try {
            // 按照 utf-8 编码读入文件
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(DictionaryPath), "utf-8"));
                String lineText = null;
                try {
                    while (((lineText = bufferedReader.readLine()) != null)) {
                        // 按照空格切分，只读取第二部分,这里不能设置为“”，否则split会将其按字分片
                        String[] str = lineText.split(" ");
                        lineText = str[0];
                        dictionary.add(lineText);
                    }
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return dictionary;
    }

    public Integer getMaxWordLength(Set<String> dictionary){
        Iterator<String> item = dictionary.iterator();
        Integer MaxWordLength = 0;
        while (item.hasNext()){
            String word = item.next();
            if (MaxWordLength < word.length()){
                MaxWordLength = word.length();
            }
        }
        return MaxWordLength;
    }

    public List<String> ForwardMatching(String sentence, Set<String> dictionary, Integer MaxWordLength) {

        long starttime = System.nanoTime();
        List<String> results = new ArrayList<String>();
        String inputString = sentence;

        while (inputString.length() > 0) {
            String subSquence;
            if (inputString.length() < MaxWordLength) {
                subSquence = inputString;
            } else {
                subSquence = inputString.substring(0, MaxWordLength);
            }
            while (subSquence.length() > 0) {
                // 如果字典中含有该子串或者子串粒度为1，则子串匹配成功
                 if (dictionary.contains(subSquence) || subSquence.length() == 1){
                     results.add(subSquence);
                     inputString = inputString.substring(subSquence.length());
                     break;
                 } else {
                     subSquence = subSquence.substring(0, subSquence.length()-1);
                 }
            }
        }
        long endtime = System.nanoTime();
        System.out.println("Forward.. Run Time：" + (endtime - starttime) + "ns");

        return results;
    }
}
