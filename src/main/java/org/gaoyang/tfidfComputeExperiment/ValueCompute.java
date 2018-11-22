package org.gaoyang.tfidfComputeExperiment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class ValueCompute {


    private static List<Map> ComputeTfValue(List<List<String>> splitResult) {

        List<Map> tfValue = new ArrayList<>();
        // 计算tf值
        for (List<String> corpusText : splitResult) {
            // 语料库中的每一个语料为一个corpusText。词语的最大长度为 MaxWordLength
            Map<String, Float> newOneTfValue = new TreeMap<>();
            // 获取本条语料总词数
            int WordSum = corpusText.size();

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

    private static List<Map> ComputeIdfValue(List<List<String>> splitResult) {

        // 存放idf计算的值
        List<Map> idfValue = new ArrayList<>();
        int CorpusSum = splitResult.size();

        for (List<String> aSplitResult : splitResult) {
            Map<String, Float> idfMap = new TreeMap<>();

            // 遍历语料中的每个词，统计每个词在语料库中出现的个数，并写入Map中
            for (String everyWord : aSplitResult) {

                int ExistCorpusSum = 0;
                for (List<String> aSplitResult1 : splitResult) {
                    if (aSplitResult1.contains(everyWord)) {
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

    private static List<Map> ComputeTfIdf(List<Map> tfValue, List<Map> idfValue) throws Exception {
        List<Map> TfIdfList = new ArrayList<>();
        // 计算ifidf值
        for (int i = 0; i < tfValue.size(); i++) {
            Map<String, Double> IfIdfValue = new HashMap<>();

            @SuppressWarnings("unchecked")
            Map<String, Float> tfcorpus = tfValue.get(i);
            Map<String, Float> idfcorpus = idfValue.get(i);
            for (String key : tfcorpus.keySet()) {
                IfIdfValue.put(key, (double) (tfcorpus.get(key) * idfcorpus.get(key)));
            }

            TfIdfList.add(IfIdfValue);
        }
        return TfIdfList;
    }

    public void app(List<List<String>> splitResult) throws Exception {

        List<Map> tfValue = ValueCompute.ComputeTfValue(splitResult);
        List<Map> idfValue = ValueCompute.ComputeIdfValue(splitResult);
        List<Map> TfidfValue = ValueCompute.ComputeTfIdf(tfValue, idfValue);

        File OutputFile = new File("data/tfIdfValue.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(OutputFile), StandardCharsets.UTF_8));

        for (int i = 0; i < TfidfValue.size(); i++) {

            List<Map.Entry<String, Double>> entryArrayList = new ArrayList<>(TfidfValue.get(i).entrySet());

            // 定义treeMap按照value的降序排序
            entryArrayList.sort(Comparator.comparing(Map.Entry::getValue, (o1, o2) -> o2.compareTo(o1)));
            // 将计算的结果写入到文件中
            bufferedWriter.write(entryArrayList.toString() + "\n");

            System.out.println("----------------------CORPUS NUM: " + i + "------------------------------");
            for (Map.Entry entry : entryArrayList.subList(0, 5)) {
                System.out.println(entry.getKey() + " - " + entry.getValue());
            }

        }
        bufferedWriter.flush();
        bufferedWriter.close();
    }
}
