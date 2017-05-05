package evaluex;

import com.mysql.fabric.xmlrpc.base.Param;
import org.apache.commons.lang.ArrayUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.PrintCellComments;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Math.round;

/**
 * Created by Anu on 1/5/2017.
 */
public class WordSimilarity {

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String JDBC_URL_lexical = "jdbc:mysql://localhost:3306/lexical_database?autoReconnect=true&useSSL=false";
    private static final String JDBC_URL_CORPUS = "jdbc:mysql://localhost:3306/Tamil_corpus?autoReconnect=true&useSSL=false";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PWD = "Tharsha1992*";

    public void createJointWordList(List<String> modelAnsList,List<String> studentAnsList) throws ClassNotFoundException {

        Convert convert = new Convert();
        Constants constants = new Constants();
        convert.setETMap();
        Map<String,String> map = convert.getETMAP();
        List<String> newStudentAnsList = new LinkedList<>();
        List<String> newModelAnsList = new LinkedList<>();
        List<String> newJointWordList = new LinkedList<>();
        List<String> stuRootWordList = new LinkedList<>();
        List<String> modelRootWordList = new LinkedList<>();
        Map<String,String> modelHashMap = new LinkedHashMap<>();
        Map<String,String> studentHashMap = new LinkedHashMap<>();
        Map<String,String> modelRootMap = new LinkedHashMap<>();
        Map<String,String> studentRootMap = new LinkedHashMap<>();
        List<String > modelResultOperations = new LinkedList<>();
        List<String> studentResultOperations = new LinkedList<>();
        List<Integer> modelnumbers = new LinkedList<>();
        List<Integer> studentNumbers = new LinkedList<>();

        for(Object word : modelAnsList){
            String convertedWord = convert.convertTE(word.toString(),map);
            newModelAnsList.add(convertedWord);
            modelHashMap.put(word.toString(),convertedWord);
        }
        Parameters.setModelHashMap(modelHashMap);
        for(Object word : studentAnsList){
            String convertedWord = convert.convertTE(word.toString(),map);
            newStudentAnsList.add(convertedWord);
            studentHashMap.put(word.toString(),convertedWord);
        }
        Parameters.setStudentHashMap(studentHashMap);
        try{
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL_lexical,JDBC_USER,JDBC_PWD);
            PreparedStatement prep;
            int modelCount = 0;
            int studentCount = 0;
            for(Object word : newModelAnsList) {
                String rootWord = null;
                Pattern pattern = Pattern.compile(".*\\D.*");
                Pattern pattern1 = Pattern.compile("[<>+-=≡~≈<<>>>=<=*.×/÷—%∠⊥]");
                Pattern pattern2 = Pattern.compile("[0-9]*");
                Matcher m = pattern.matcher(word.toString());
                Matcher m1 = pattern1.matcher(word.toString());
                Matcher m2 = pattern2.matcher(word.toString());
                boolean match = m.matches();
                boolean match1 = m1.matches();
                boolean match2 = m2.matches();
                if(match2){
                    modelCount++;
                }
                if (match) {
                    if (match1) {
                        rootWord = constants.getMap().get(word.toString());
                        modelRootWordList.add(rootWord);
                    } else {

                        String query = "select * from morphtable where inflated_word = ?";
                        try {
                            prep = connection.prepareStatement(query);
                            prep.setString(1, word.toString());
                            ResultSet rs = prep.executeQuery();
                            if (!rs.isBeforeFirst()) {
                                rootWord = word.toString();
                                modelRootWordList.add(rootWord);
                            } else {
                                while (rs.next()) {
                                    rootWord = rs.getString(2);
                                    modelRootWordList.add(rootWord);
                                }
                            }
                            rs.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }else{
                    rootWord = word.toString();
                    modelRootWordList.add(rootWord);
                }
                modelRootMap.put(word.toString(),rootWord);
            }
            if(modelCount == 2){
                int sub,sum,multi,divide,divide2;
                for(String modelWord : modelRootWordList){
                    try{
                        int num = Integer.parseInt(modelWord);
                        modelnumbers.add(num);
                    }catch (Exception e){
                        continue;
                    }
                }
                if(modelnumbers.get(0) > modelnumbers.get(1)){
                    sub = modelnumbers.get(0) - modelnumbers.get(1);
                    modelResultOperations.add(String.valueOf(sub));
                }else{
                    sub = modelnumbers.get(1) - modelnumbers.get(0);
                    modelResultOperations.add(String.valueOf(sub));
                }
                sum = modelnumbers.get(0)+modelnumbers.get(1);
                multi = modelnumbers.get(0)*modelnumbers.get(1);
                if(modelnumbers.get(1)!=0) {
                    divide = modelnumbers.get(0) / modelnumbers.get(1);
                    modelResultOperations.add(String.valueOf(divide));
                }
                if(modelnumbers.get(0) !=0) {
                    divide2 = modelnumbers.get(1) / modelnumbers.get(0);
                    modelResultOperations.add(String.valueOf(divide2));
                }
                modelResultOperations.add(String.valueOf(sum));
                modelResultOperations.add(String.valueOf(multi));
            }else{
                for(String modelWord : modelRootWordList){
                    try{
                        int num = Integer.parseInt(modelWord);
                        modelnumbers.add(num);
                    }catch (Exception e){
                        continue;
                    }
                }
            }
            Parameters.setModelRootMap(modelRootMap);
            for(Object word : newStudentAnsList){
                String rootWord = null;
                Pattern pattern = Pattern.compile(".*\\D.*");
                Pattern pattern1 = Pattern.compile("[<>+-=≡~≈<<>>>=<=*.×/÷—%∠⊥]");
                Pattern pattern2 = Pattern.compile("[0-9]*");
                Matcher m = pattern.matcher(word.toString());
                Matcher m1 = pattern1.matcher(word.toString());
                Matcher m2 = pattern2.matcher(word.toString());
                boolean match = m.matches();
                boolean match1 = m1.matches();
                boolean match2 = m2.matches();
                if(match2){
                    studentCount++;
                }
                if (match) {
                    if (match1) {
                        rootWord = constants.getMap().get(word.toString());
                        modelRootWordList.add(rootWord);
                    } else {
                        String query = "select * from morphtable where inflated_word = ?";
                        try {
                            prep = connection.prepareStatement(query);
                            prep.setString(1, word.toString());
                            ResultSet rs = prep.executeQuery();
                            if (!rs.isBeforeFirst()) {
                                rootWord = word.toString();
                                stuRootWordList.add(rootWord);
                            } else {
                                while (rs.next()) {
                                    rootWord = rs.getString(2);
                                    stuRootWordList.add(rootWord);
                                }
                            }
                            rs.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }else{
                    rootWord = word.toString();
                   stuRootWordList.add(rootWord);
                }
                studentRootMap.put(word.toString(),rootWord);
            }
            if(studentCount == 2){
                int sub,sum,multi,divide,divide2;
                for(String studentWord : stuRootWordList){
                    try{
                        int num = Integer.parseInt(studentWord);
                        studentNumbers.add(num);
                    }catch (Exception e){
                        continue;
                    }
                }
                if(studentNumbers.get(0) > studentNumbers.get(1)){
                    sub = studentNumbers.get(0) - studentNumbers.get(1);
                    studentResultOperations.add(String.valueOf(sub));
                }else{
                    sub = studentNumbers.get(1) - studentNumbers.get(0);
                    studentResultOperations.add(String.valueOf(sub));
                }
                sum = studentNumbers.get(0)+studentNumbers.get(1);
                multi = studentNumbers.get(0)*studentNumbers.get(1);
                if(studentNumbers.get(1)!=0) {
                    divide = studentNumbers.get(0) / studentNumbers.get(1);
                    studentResultOperations.add(String.valueOf(divide));
                }
                if(studentNumbers.get(0) !=0) {
                    divide2 = studentNumbers.get(1) / studentNumbers.get(0);
                    studentResultOperations.add(String.valueOf(divide2));
                }
                studentResultOperations.add(String.valueOf(sum));
                studentResultOperations.add(String.valueOf(multi));
            }else{
                for(String studentWord : stuRootWordList){
                    try{
                        int num = Integer.parseInt(studentWord);
                        studentNumbers.add(num);
                    }catch (Exception e){
                        continue;
                    }
                }
            }
            Parameters.setStudentRootMap(studentRootMap);
            if(!(modelCount == 2 && studentCount == 2)){
                if(modelCount == 2){
                    String removeLiteral = null;
                    if(!Collections.disjoint(stuRootWordList,modelResultOperations)){
                        for(String result : modelResultOperations){
                            if(stuRootWordList.contains(result)){
                                removeLiteral = result;
                            }
                        }
                        modelRootWordList.add(modelRootWordList.indexOf(modelnumbers.get(0).toString()),removeLiteral);
                        modelRootWordList.remove(modelnumbers.get(0).toString());
                        modelRootWordList.remove(modelnumbers.get(1).toString());
                        newJointWordList.add(removeLiteral);
                    }else{
                        System.out.println("Marks score 0.0 out of 1");
                        System.exit(0);
                    }
                }
                if(studentCount == 2){
                    String removeLiteral = null;
                    if(!Collections.disjoint(modelRootWordList,studentResultOperations)){
                        for(String result : studentResultOperations){
                            if(modelRootWordList.contains(result)){
                                removeLiteral = result;
                            }
                        }
                        stuRootWordList.remove(modelnumbers.get(0).toString());
                        stuRootWordList.remove(modelnumbers.get(1).toString());
                        newJointWordList.add(removeLiteral);
                    }else{
                        System.out.println("Marks score 0.0 out of 1");
                        System.exit(0);
                    }
                }
                if(modelCount >0 && studentCount >0) {
                    if (modelCount == studentCount) {
                        if (Collections.disjoint(modelnumbers, studentNumbers)) {
                            System.out.println("Marks score 0.0 out of 1");
                            System.exit(0);
                        }
                    }
                }
            }
            for(String word : modelRootWordList){
                if(!newJointWordList.contains(word)){
                    newJointWordList.add(word);
                }
            }
            for(String stuAns : stuRootWordList){
                if(!(newJointWordList.contains(stuAns))){
                    newJointWordList.add(stuAns);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        findWordSimilarity(modelRootWordList,stuRootWordList, newJointWordList);
    }
    @SuppressWarnings("Unchecked")
    public void findWordSimilarity(List<String> modelRootWordList, List<String> stuRootWordList, List<String> newJointWordList) throws ClassNotFoundException {
        int vectorLength = newJointWordList.size();
        Vector<Integer> model = new Vector<Integer>(vectorLength);
        Vector<Integer> student = new Vector<Integer>(vectorLength);
        Map<String,Integer> modelIndexMap = new LinkedHashMap<>(vectorLength);
        Map<String,Integer> studentIndexMap = new LinkedHashMap<>(vectorLength);
        List<String> synword = new LinkedList<>();
        List<String> a = new LinkedList<>();
        Map<String,Integer> index = new LinkedHashMap<>(vectorLength);
        Vector<Integer> modelIndex = new Vector<Integer>(vectorLength);
        Vector<Integer> studentIndex = new Vector<Integer>(vectorLength);
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL_lexical,JDBC_USER,JDBC_PWD);
            PreparedStatement prep;
            for(int i = 1 ; i<=newJointWordList.size(); i++){
                index.put(newJointWordList.get(i-1),i);
            }
            for (String word : newJointWordList) {
                Pattern pattern1 = Pattern.compile("[a-zA-Z_0-9]*");
                Matcher m1 = pattern1.matcher(word);
                boolean match1 = m1.matches();
                if (!match1) {
                    List<Integer> id = new LinkedList<>();
                    String result = null;
                    List<String> b = new LinkedList<>();
                    List<String> wordList = new LinkedList<>();
                    String tempQuery = "select id from glossary.mathematics where word like '%" + word + "%'";
                    prep = connection.prepareStatement(tempQuery);
                    ResultSet rs = prep.executeQuery();
                    while (rs.next()) {
                        id.add(rs.getInt(1));
                    }
                    for(int newId:id) {
                        String query = "select word from glossary.mathematics where id = ?";
                        prep = connection.prepareStatement(query);
                        prep.setInt(1, newId);
                        ResultSet resultSet = prep.executeQuery();
                        while (rs.next()) {
                            result = resultSet.getString(1);
                            if (result.indexOf('-') >= 0) {
                                b = Arrays.asList(result.split("-"));
                                for (int i = 0; i < b.size(); i++) {
                                    wordList.add(b.get(i));
                                }
                            }
                        }
                    }
                    if (!Collections.disjoint(modelRootWordList, wordList)) {
                        model.add(1);
                        modelIndexMap.put(word, index.get(word));
                    } else {
                        model.add(0);
                        modelIndexMap.put(word, 0);
                    }
                    if (!Collections.disjoint(stuRootWordList, wordList)) {
                        student.add(1);
                        studentIndexMap.put(word, index.get(word));
                    } else {
                        student.add(0);
                        studentIndexMap.put(word, 0);
                    }
                } else {
                    String hyper = "";
                    List<String> hypernym = new ArrayList<String>();
                    String query = "select * from sense where label = ?";
                    prep = connection.prepareStatement(query);
                    prep.setString(1, word);
                    ResultSet rs = prep.executeQuery();
                    if (!rs.isBeforeFirst()) {
                        if (modelRootWordList.contains(word)) {
                            model.add(1);
                            modelIndexMap.put(word, index.get(word));
                        } else {
                            model.add(0);
                            modelIndexMap.put(word, 0);
                        }
                        if (stuRootWordList.contains(word)) {
                            student.add(1);
                            studentIndexMap.put(word, index.get(word));
                        } else {
                            student.add(0);
                            studentIndexMap.put(word, 0);
                        }
                    } else {
                        while (rs.next()) {
                            hyper = rs.getString(4);
                            if (hyper != null) {
                                a = Arrays.asList(hyper.split(":"));
                                for (int i = 0; i < a.size(); i++) {
                                    hypernym.add(a.get(i));
                                }
                            }
                        }
                        for (int i = 0; i < hypernym.size(); i++) {
                            String temp = hypernym.get(i);
                            String q1 = "select relation,label from twn where nodeindex= ?";
                            String rel = "";
                            String synqry = "";
                            String sword = "";
                            int position = temp.lastIndexOf(",");
                            String tempr = temp.substring(0, position);
                            if (position > 1) {
                                position = tempr.lastIndexOf(",");
                            }
                            String prev = tempr.substring(0, position);
                            prep = connection.prepareStatement(q1);
                            prep.setString(1, temp);
                            rs = prep.executeQuery();
                            while (rs.next()) {
                                rel = rs.getString(1);
                                sword = rs.getString(2);
                            }
                            int k = Integer.parseInt(rel);
                            if (k != 4) {
                                synqry = "(select label from twn where nodeindex like '" + temp + "%' and (relation ='4' or relation = '3') and label not like binary '" + sword + "')";

                            } else {
                                synqry = "(select label from twn where nodeindex like '" + prev + "' and label not like binary '" + sword + "') union (select label from twn where nodeindex like '" + prev + "%' and relation ='4' and nodeindex != '" + temp + "' and label not like binary '" + sword + "' and relation ='4')";
                            }
                            prep = connection.prepareStatement(synqry);
                            rs = prep.executeQuery();
                            while (rs.next()) {
                                synword.add(rs.getString(1));
                            }
                        }
                        if (modelRootWordList.contains(word) || !Collections.disjoint(modelRootWordList, synword)) {
                            model.add(1);
                            modelIndexMap.put(word, index.get(word));
                        } else {
                            model.add(0);
                            modelIndexMap.put(word, 0);
                        }
                        if (stuRootWordList.contains(word) || !Collections.disjoint(stuRootWordList, synword)) {
                            student.add(1);
                            studentIndexMap.put(word, index.get(word));
                        } else {
                            student.add(0);
                            studentIndexMap.put(word, 0);
                        }
                    }
                }

                }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        double cosineSimilarity = findSentenceSemanticSimilarity(model,student);
        double orderSimilarity = findOrderSimilarity(modelIndexMap,studentIndexMap);
        findSentenceSimilarity(cosineSimilarity,orderSimilarity);


    }
    @SuppressWarnings("Unchecked")
    public double findSentenceSemanticSimilarity(Vector<Integer> model,Vector<Integer> student){

        List<String> JointWordList = new LinkedList<>();
        List<Double> inforConJoint = new LinkedList<>();
        Vector<Double> modelAns = new Vector<>();
        Vector<Double> studentAns = new Vector<>();
        Map<String,String> modelHashMap = new LinkedHashMap<>();
        Map<String,String> studentHashMap = new LinkedHashMap<>();
        Map<String,String> modelRootMap = new LinkedHashMap<>();
        Map<String,String> studentRootMap = new LinkedHashMap<>();
        double cosineSimilarity = 0.0;
        DecimalFormat df = new DecimalFormat("#.###");

        modelHashMap = Parameters.getModelHashMap();
        studentHashMap = Parameters.getStudentHashMap();
        modelRootMap = Parameters.getModelRootMap();
        studentRootMap = Parameters.getStudentRootMap();
        for(String modelWord : modelRootMap.values()){
            for(Map.Entry<String,String> e :modelHashMap.entrySet()){
                String value = e.getValue();
                if(value.equals(modelWord)){
                    JointWordList.add(e.getKey());
                    break;
                }
            }
        }
        for(String studentWord : studentRootMap.values()){
            for(Map.Entry<String,String> e :studentHashMap.entrySet()){
                String value = e.getValue();
                if(value.equals(studentWord)){
                    if(!(modelRootMap.containsValue(studentRootMap.get(studentWord)))){
                        if(!JointWordList.contains(e.getKey())){
                            JointWordList.add(e.getKey());
                            break;
                        }
                    }
                }

            }
        }
        int total_count = countTotalWords();

        for(int i = 0; i<JointWordList.size();i++){
            inforConJoint.add(calculateInformationContent(JointWordList.get(i),total_count));
        }
        for(int i = 0; i<JointWordList.size();i++){
                if(model.get(i) == 0 ) {
                    modelAns.add(0.0);
                }else if(model.get(i) == 1){
                    modelAns.add(1*inforConJoint.get(i)*inforConJoint.get(i));
                }
        }
        for(int i = 0; i<JointWordList.size();i++){
            if(student.get(i) == 0 ) {
                studentAns.add(0.0);
            }else if(student.get(i) == 1){
                studentAns.add(1*inforConJoint.get(i)*inforConJoint.get(i));
            }
        }

        cosineSimilarity = findSemanticSimilarity(modelAns,studentAns);
        return Double.valueOf(df.format(cosineSimilarity));
    }

    public double calculateInformationContent(String word, int total_count){

        int count = getFrequency(word);
        double informationContent = 0.0;

        informationContent = 1 - Math.log(count+1)/Math.log(total_count+1);

        return informationContent;

    }

    public double findSemanticSimilarity(Vector<Double> model,Vector<Double> student){

        double dotProduct = 0.0;
        double magnitude1 = 0.0;
        double magnitude2 = 0.0;
        double cosineSimilarity = 0.0;
        DecimalFormat df = new DecimalFormat("#.###");

        for (int i = 0; i < model.size(); i++) //model and student must be of same length
        {
            dotProduct += model.get(i) * student.get(i);  //a.b
            magnitude1 += Math.pow(model.get(i), 2);  //(a^2)
            magnitude2 += Math.pow(student.get(i), 2); //(b^2)
        }

        magnitude1 = Math.sqrt(magnitude1);//sqrt(a^2)
        magnitude2 = Math.sqrt(magnitude2);//sqrt(b^2)

        if (magnitude1 != 0.0 && magnitude2 != 0.0)
        {
            cosineSimilarity = dotProduct / (magnitude1 * magnitude2);
        }
        else
        {
            cosineSimilarity = 0.0;
        }

        return Double.valueOf(df.format(cosineSimilarity));

    }

    public double findOrderSimilarity(Map<String,Integer> modelIndexMap,Map<String,Integer> studentIndexMap){

        Vector<Integer> model = new Vector<Integer>();
        Vector<Integer> student = new Vector<Integer>();
        int addition, sub;
        double orderSimilarity = 0.0;
        double subMagnitude = 0.0;
        double addMagnitude  =0.0;
        DecimalFormat df = new DecimalFormat("#.###");

        for(String word : modelIndexMap.keySet()){
            model.add(modelIndexMap.get(word));
        }
        for(String word : studentIndexMap.keySet()){
            student.add(studentIndexMap.get(word));
        }

        if(model.size() == student.size()){
            for(int i = 0 ; i<model.size();i++){
                addition = model.get(i)+student.get(i);
                sub = model.get(i)+student.get(i);
                subMagnitude +=Math.pow(sub,2);
                addMagnitude +=Math.pow(addition,2);
            }
            orderSimilarity = Math.sqrt(subMagnitude)/Math.sqrt(addMagnitude);
        }
        return Double.valueOf(df.format(orderSimilarity));
    }

    public void findSentenceSimilarity(double cosineSimilarity,double orderSimilarity){

            DecimalFormat df = new DecimalFormat("#.###");
            double t = 0.8285;
            double finalVal = Double.valueOf(df.format(t * cosineSimilarity + (1-t) * orderSimilarity));
            Parameters.setFinalAns(finalVal);

         System.out.println("Marks score " + finalVal + " out of 1");

    }

    public int getFrequency(String word){

        int count = 0;

        try{
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL_CORPUS,JDBC_USER,JDBC_PWD);
            PreparedStatement prep;
            String query = "SELECT COUNT(sentence) AS count FROM tamil WHERE sentence LIKE '%" + word + "%'";
            prep = connection.prepareStatement(query);
            ResultSet rs = prep.executeQuery();
            while (rs.next()){
                count = Integer.parseInt(rs.getString(1));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;

    }

    public int countTotalWords(){

        int total_count = 0;

        try{
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL_CORPUS,JDBC_USER,JDBC_PWD);
            PreparedStatement prep;
            Statement s = connection.createStatement();
            String query = "SELECT SUM(LENGTH(sentence) - LENGTH(REPLACE(sentence, ' ', '')) + 1) as total_count FROM tamil";
            prep = connection.prepareStatement(query);
            ResultSet rs = prep.executeQuery();
            while (rs.next()){
                total_count = Integer.parseInt(rs.getString(1));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total_count;
    }

}
