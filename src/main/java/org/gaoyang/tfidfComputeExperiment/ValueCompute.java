package org.gaoyang.tfidfComputeExperiment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ValueCompute {

    public static List<Map> ComputeTfValue(List<List<String>> splitResult) throws Exception {

        List<Map> tfValue = new ArrayList<>();
        // 计算tf值
        for (int i = 0; i < splitResult.size(); i++) {
            // 语料库中的每一个语料为一个corpusText。词语的最大长度为 MaxWordLength
            List<String> corpusText = splitResult.get(i);
            Map<String, Float> newOneTfValue = new HashMap<>();
            // 获取本条语料总词数
            Integer WordSum = corpusText.size();

            Map<String, Long> OneTfValue = corpusText.stream().collect(Collectors.groupingBy(p -> p, Collectors.counting()));
            // 打印map中的单词计数
            // OneTfValue.forEach((k, v) -> System.out.print(k + ":" + v + "  "));
            for (String key : OneTfValue.keySet()) {
                Long value = OneTfValue.get(key);
                newOneTfValue.put(key, value / ((float) WordSum));
            }
            tfValue.add(newOneTfValue);
        }
        return tfValue;
    }

    public static List<Map> ComputeIdfValue(List<List<String>> splitResult) {

        // 存放idf计算的值
        List<Map> idfValue = new ArrayList<>();
        Integer CorpusSum = splitResult.size();

        for (int i = 0; i < splitResult.size(); i++) {
            Map<String, Float> idfMap = new HashMap<>();
            // corpus为语料库中的每一个语料
            List<String> corpusText = splitResult.get(i);
            // 遍历语料中的每个词，统计每个词在语料库中出现的个数，并写入Map中
            for (int j = 0; j < corpusText.size(); j++) {
                String everyWord = corpusText.get(j);

                Integer ExistCorpusSum = 0;
                for (Integer k = 0; k < CorpusSum; k++) {
                    if (splitResult.get(k).contains(everyWord)) {
                        ExistCorpusSum++;
                    }
                }
                idfMap.put(everyWord, (float) Math.log((double) (CorpusSum) / ExistCorpusSum));
            }
            idfValue.add(idfMap);
        }
//        System.out.println(idfValue);
        return idfValue;
    }

    public static List<Map> ComputeTfIdf(List<Map> tfValue, List<Map> idfValue) throws Exception{
        List<Map> TfIdfList = new ArrayList<>();
        for (int i = 0; i < tfValue.size(); i++) {
            Map<String, Float> IfIdfValue = new HashMap<>();

            Map<String,Float> tfcorpus = tfValue.get(i);
            Map<String,Float> idfcorpus = idfValue.get(i);
            for (String key: tfcorpus.keySet()){
                IfIdfValue.put(key,tfcorpus.get(key)*idfcorpus.get(key)*100);
            }

            TfIdfList.add(IfIdfValue);
        }
        return TfIdfList;
    }

    public void app(List<List<String>> splitResult) throws Exception{
        ValueCompute valueCompute = new ValueCompute();
        List<Map> tfValue = ValueCompute.ComputeTfValue(splitResult);
        List<Map> idfValue = ValueCompute.ComputeIdfValue(splitResult);
        List<Map> TfidfValue = ValueCompute.ComputeTfIdf(tfValue,idfValue);
        for (int i=0;i<TfidfValue.size();i++){
            System.out.println(TfidfValue.get(i));
        }
    }
}
