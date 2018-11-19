package org.gaoyang.tfidfComputeExperiment;

import org.ansj.domain.Term;
import org.gaoyang.nlpCourseExperiment.AnsjMatching;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SplitText {

    public List<String> getFileContent(String corpusPath) throws Exception {
        ArrayList<File> files = new ArrayList<File>();
        File file = new File(corpusPath);
        File[] templist = file.listFiles();
        List<String> Text = new ArrayList<String>();

        // 获取语料库目录下所有的语料
        for (int i = 0; i < templist.length; i++) {
            if (templist[i].isFile()) {
                files.add(templist[i]);
            }
        }

        for (int i = 0; i < files.size(); i++) {
            File filePath = files.get(i);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "utf-8"));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Text.add(line);
            }
        }
//        System.out.println(splitText);
        return Text;
    }

    public List<List<String>> getSplitTextResult(String corpusPath) throws Exception {

        // result 中存放所有语料库中分词、提取字符串后的结果
        List<List<String>> result = new ArrayList<List<String>>();

        // 获取所有的语料库文件
        SplitText splitText = new SplitText();
        List<String> Text = splitText.getFileContent(corpusPath);

        AnsjMatching ansjMatching = new AnsjMatching();

        for (int i=0;i<Text.size();i++){
            List<Term> ansjResult = AnsjMatching.UseAnsjTool(Text.get(i));
            // splitwordresult存放分词后分割字符串的操作，比如"中国人民解放军信息工程大学/userDefine",提取出来"中国人民解放军信息工程大学"
            List<String> splitwordresult = new ArrayList<String>();
            // 对每一个词语进行提取"/"前面的字段
            for (int j =0;j<ansjResult.size();j++){
                splitwordresult.add(ansjResult.get(j).toString().split("/")[0]);
            }
            result.add(splitwordresult);
        }
        System.out.println("分词后的结果：");
        System.out.println(result.get(0));
        System.out.println(result.get(1));
        System.out.println(result.get(2));
        return result;
    }
}
