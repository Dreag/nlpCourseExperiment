package org.gaoyang.tfidfComputeExperiment;

import org.ansj.domain.Term;
import org.gaoyang.nlpCourseExperiment.AnsjMatching;

import javax.xml.soap.Text;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class App2 {

    public static void main(String[] args) throws Exception{
        String filepath = "data/corpus";
//        String Text = "中国人民解放军信息工程大学以原信息工程大学、外国语学院为基础重建，隶属战略支援部队";
//        AnsjMatching ansjMatching = new AnsjMatching();
//        List<Term> SplitWordResult = ansjMatching.UseAnsjTool(Text);
//        System.out.println(SplitWordResult.get(0).toString().split("/")[0]);
        SplitText splitText = new SplitText();
        List<List<String>> reslut = splitText.getSplitTextResult(filepath);
    }
}
