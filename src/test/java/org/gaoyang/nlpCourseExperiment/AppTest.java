package org.gaoyang.nlpCourseExperiment;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.*;

/**
 * Unit test for simple App.
 */
public class AppTest
        extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
        assertTrue(true);
    }

    public static void main(String[] args) {
        TreeMap<String, Double> treeMap = new TreeMap<>();
        treeMap.put("s", 2.3);
        treeMap.put("w", 5.6);
        treeMap.put("d", 1.4);
        treeMap.put("f", 0.4);
        treeMap.put("h", 9.9);
        treeMap.put("q", 22.3);
        treeMap.put("a", 25.1);
        //按照value排序
        System.out.println(treeMap);
        List<Map.Entry<String, Double>> entryArrayList = new ArrayList<>(treeMap.entrySet());


        // 定义treeMap按照value的降序排序
        Collections.sort(entryArrayList, Comparator.comparing(Map.Entry::getValue, new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                return o2.compareTo(o1);
            }
        }));
        System.out.println("----------------------*------------------------------");
        for (Map.Entry<String, Double> entry : entryArrayList) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
        System.out.println("----------------------*------------------------------");

    }
}
