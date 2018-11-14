package org.gaoyang.nlpCourseExperiment;

import javafx.scene.text.FontWeight;
import org.ansj.domain.Term;

import javax.xml.soap.Text;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) throws Exception{
        String Text = "中国人民解放军信息工程大学以原信息工程大学、外国语学院为基础重建，隶属战略支援部队";

        // 获取词典
        ForwardMaximunMatching ForwardMaximunMatching = new ForwardMaximunMatching();
        Set<String> dic = ForwardMaximunMatching.getDictonary();
        // 获取词典长度
        Integer MaxWordLength = ForwardMaximunMatching.getMaxWordLength(dic);

        // 打印FMM分词结果
        List<String> ForwardReslut = ForwardMaximunMatching.ForwardMatching(Text, dic, MaxWordLength);
        System.out.println("FMM分词结果: " + ForwardReslut);

        ReverseDirectionalMaximumMatching ReverseDirectionalMaximumMatching = new ReverseDirectionalMaximumMatching();
        List<String> ReverseResult = ReverseDirectionalMaximumMatching.ReverseMatching(Text, dic, MaxWordLength);
        System.out.println("RMM分词结果: " + ReverseResult);

        AnsjMatching AnsjMatching = new AnsjMatching();

        List<Term> AnsjResult = AnsjMatching.UseAnsjTool(Text);
        System.out.println("ANSJ分词结果：" + AnsjResult);
    }
}
