package org.gaoyang.tfidfComputeExperiment;

import org.ansj.domain.Term;
import org.gaoyang.nlpCourseExperiment.AnsjMatching;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class SplitText {

    private List<String> getFileContent(String corpusPath) throws Exception {
        ArrayList<File> files = new ArrayList<>();
        File file = new File(corpusPath);
        File[] templist = file.listFiles();
        List<String> Text = new ArrayList<>();

        // 获取语料库目录下所有的语料
        assert templist != null;

        for (File aTemplist : templist) {
            if (aTemplist.isFile()) {
                files.add(aTemplist);
            }
        }

        for (File filePath : files) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Text.add(line);
            }
            bufferedReader.close();
        }

//        System.out.println(splitText);
        return Text;
    }

    public List<List<String>> getSplitTextResult(String corpusPath) throws Exception {

        // result 中存放所有语料库中分词、提取字符串后的结果
        List<List<String>> result = new ArrayList<>();

        // 获取所有的语料库文件
        SplitText splitText = new SplitText();
        List<String> Text = splitText.getFileContent(corpusPath);


        /*
        这一步现在其实不需要了，因为在AnsjMatching中进行去停用词的时候已经把这个操作重写了。
         */
        for (String aText : Text) {
            List<Term> ansjResult = AnsjMatching.UseAnsjTool(aText);
            // splitwordresult存放分词后分割字符串的操作，比如"中国人民解放军信息工程大学/userDefine",提取出来"中国人民解放军信息工程大学"
            List<String> splitwordresult = new ArrayList<>();
            // 对每一个词语进行提取"/"前面的字段
            for (Term anAnsjResult : ansjResult) {
                splitwordresult.add(anAnsjResult.toString().split("/")[0]);
            }
            result.add(splitwordresult);
        }
//        System.out.println("分词后的结果：" + result.size());
        return result;
    }
}
