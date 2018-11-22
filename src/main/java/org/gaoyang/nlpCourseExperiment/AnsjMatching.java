package org.gaoyang.nlpCourseExperiment;


import love.cq.domain.Forest;
import org.ansj.domain.Term;
import org.ansj.library.UserDefineLibrary;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.ansj.util.recognition.NatureRecognition;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class AnsjMatching {

    public static List<Term> UseAnsjTool(String Text) throws Exception {

        Forest userForest = UserDefineLibrary.makeUserDefineForest(false, "library/userLibrary/userLibrary.dic");
        List<String> stopWord = new ArrayList<>();

        File filePath = new File("data/stopword.dic");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8));
        String subText, lineText;

        List<Term> terms = ToAnalysis.paser(Text, userForest);
        new NatureRecognition(terms).recognition();

        while ((lineText = bufferedReader.readLine()) != null) {
            stopWord.add(lineText);
        }

        // 对每一个词语进行提取"/"前面的字段,并删除停用词
        Iterator<Term> iterator = terms.iterator();
        while (iterator.hasNext()) {
            Term term = iterator.next();
            subText = term.toString().split("/")[0];
            if (stopWord.contains(subText)) {
                iterator.remove();
            }
        }

        return terms;
    }


}
