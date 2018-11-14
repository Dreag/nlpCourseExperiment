package org.gaoyang.nlpCourseExperiment;


import love.cq.domain.Forest;
import org.ansj.domain.Term;
import org.ansj.library.UserDefineLibrary;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.ansj.util.recognition.NatureRecognition;

import java.util.List;

public class AnsjMatching {

    public List<Term> UseAnsjTool(String Text) throws Exception {

        Forest userForest = UserDefineLibrary.makeUserDefineForest(false,"data/userDefinedDict.dic");

        List<Term> terms = ToAnalysis.paser(Text, userForest);
        new NatureRecognition(terms).recognition();

//        filterStopWord("data/stopword.dic");
//        terms = FilterModifWord.modifResult(terms);

        return terms;
    }

}
