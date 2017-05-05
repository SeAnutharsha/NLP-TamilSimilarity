package evaluex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Anu on 2016-09-13.
 */
public final class Parameters {

    private static String modelAnswer;
    private static String studentAnswer;
    private static List modelAnsTokens ;
    private static List studentAnsTokens;
    private static List jointWordList;
    private static long startTime;
    private static Map<String,String> modelHashMap = new HashMap<>();
    private static Map<String,String> studentHashMap = new HashMap<>();
    private static Map<String,String> modelRootMap = new HashMap<>();
    private static Map<String,String> studentRootMap = new HashMap<>();
    private static double cosineSimilarity;
    private static double orderSimilarity;
    private static double finalAns;


    public static String getModelAnswer() {
        return modelAnswer;
    }

    public static void setModelAnswer(String modelAnswer) {
        Parameters.modelAnswer = modelAnswer;
    }

    public static String getStudentAnswer() {
        return studentAnswer;
    }

    public static void setStudentAnswer(String studentAnswer) {
        Parameters.studentAnswer = studentAnswer;
    }

    public static List getModelAnsTokens() {
        return modelAnsTokens;
    }

    public static void setModelAnsTokens(List modelAnsTokens) {
        Parameters.modelAnsTokens = modelAnsTokens;
    }

    public static List getStudentAnsTokens() {
        return studentAnsTokens;
    }

    public static void setStudentAnsTokens(List studentAnsTokens) {
        Parameters.studentAnsTokens = studentAnsTokens;
    }

    public static List getJointWordList() {
        return jointWordList;
    }

    public static void setJointWordList(List jointWordList) {
        Parameters.jointWordList = jointWordList;
    }

    public static long getStartTime() {
        return startTime;
    }

    public static void setStartTime(long startTime) {
        Parameters.startTime = startTime;
    }

    public static Map<String, String> getModelHashMap() {
        return modelHashMap;
    }

    public static void setModelHashMap(Map<String, String> modelHashMap) {
        Parameters.modelHashMap = modelHashMap;
    }

    public static Map<String, String> getStudentHashMap() {
        return studentHashMap;
    }

    public static void setStudentHashMap(Map<String, String> studentHashMap) {
        Parameters.studentHashMap = studentHashMap;
    }

    public static Map<String, String> getModelRootMap() {
        return modelRootMap;
    }

    public static void setModelRootMap(Map<String, String> modelRootMap) {
        Parameters.modelRootMap = modelRootMap;
    }

    public static Map<String, String> getStudentRootMap() {
        return studentRootMap;
    }

    public static void setStudentRootMap(Map<String, String> studentRootMap) {
        Parameters.studentRootMap = studentRootMap;
    }

    public static double getCosineSimilarity() {
        return cosineSimilarity;
    }

    public static void setCosineSimilarity(double cosineSimilarity) {
        Parameters.cosineSimilarity = cosineSimilarity;
    }

    public static double getOrderSimilarity() {
        return orderSimilarity;
    }

    public static void setOrderSimilarity(double orderSimilarity) {
        Parameters.orderSimilarity = orderSimilarity;
    }

    public static double getFinalAns() {
        return finalAns;
    }

    public static void setFinalAns(double finalAns) {
        Parameters.finalAns = finalAns;
    }
}
