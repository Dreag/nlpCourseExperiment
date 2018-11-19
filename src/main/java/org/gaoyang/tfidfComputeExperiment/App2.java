package org.gaoyang.tfidfComputeExperiment;

import java.util.List;

public class App2 {

    public static void main(String[] args) throws Exception{
        String filepath = "data/corpus";

        // 获取分词结果
        SplitText splitText = new SplitText();
        List<List<String>> reslut = splitText.getSplitTextResult(filepath);

        // 计算tfidf值
        ValueCompute ValueCompute = new ValueCompute();
        ValueCompute.app(reslut);
    }
}
