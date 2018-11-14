package org.gaoyang.nlpCourseExperiment;


import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.library.DicLibrary;
import org.ansj.recognition.impl.NatureRecognition;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.nlpcn.commons.lang.tire.domain.Forest;
import org.nlpcn.commons.lang.tire.library.Library;


public class AnsjMatching {

    public List<Term> UseAnsjTool(String Text) throws Exception {

        Forest userForest = Library.makeForest("data/userLibrary.dic");
        userForest = new Forest();

        Result terms = ToAnalysis.parse(Text);

        return terms.getTerms();
    }

}
