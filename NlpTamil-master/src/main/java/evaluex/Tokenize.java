package evaluex;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.*;

import com.gtranslate.Language;
import com.gtranslate.Translator;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * Created by anutharsha on 8/15/16.
 */
public class Tokenize {


    public static void main(String[] args) throws ClassNotFoundException {

        long startTime = System.currentTimeMillis();
        Parameters.setStartTime(startTime);

        try {
            File file = new File("src/main/resources/Answer.xml");

            JAXBContext jaxbContext = JAXBContext.newInstance(Answers.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            Answers answers = (Answers) unmarshaller.unmarshal(file);
            Tokenizer(answers);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
       // Parameters parameters = new Parameters();
        /*try {
            // POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream("E:/FYP/implementation/test.xlsx"));
            FileInputStream inputStream = new FileInputStream("src/main/resources/Test2_split.xlsx");
            XSSFWorkbook wb = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = wb.getSheetAt(0);
            PrintWriter writer = new PrintWriter("result2/result1.txt");
           // PrintWriter writer1 = new PrintWriter("order35.txt");
            String newLine = System.getProperty("line.separator");
            List<Double> cosine = new ArrayList<>();
            List<Double> order = new ArrayList<>();
            XSSFRow row;
            XSSFCell cell;

            int rows;
            rows = sheet.getPhysicalNumberOfRows();
            int a  = sheet.getLastRowNum();

            int cols = 2;
            int tmp = 0;

           *//*  This trick ensures that we get the data properly even if it doesn't start from first few rows
            for(int i = 0; i < 10 || i < rows; i++) {
                row = sheet.getRow(i);
                if(row != null) {
                    tmp = sheet.getRow(i).getPhysicalNumberOfCells();
                    if(tmp > cols) cols = tmp;
                }
            }*//*

             *//*for(int r = 0; r <rows; r++) {
                 if(r!=0) {
                     order.add(Parameters.getFinalAns());
                 }
               row = sheet.getRow(r);
                if(row.getCell(0) != null) {
                    Parameters.setModelAnswer(row.getCell(0).toString());
                    Parameters.setStudentAnswer(row.getCell(1).toString());
                    Tokenizer();
                }
                else{
                    break;
                }
             }
             order.add(Parameters.getFinalAns());
            if(order.size() == rows) {
                for (int i = 0; i < order.size(); i++) {
                    writer.write(order.get(i) + newLine);
                }
            }
            writer.close();*//*
        } catch(Exception ioe) {
            ioe.printStackTrace();
        }*/

    }

    @SuppressWarnings("unchecked")
    private static void Tokenizer(Answers answers) throws ClassNotFoundException {

        WordSimilarity wordSimilarity = new WordSimilarity();
        if (answers != null) {
            Parameters.setModelAnswer(answers.getModelAnswer());
            Parameters.setStudentAnswer(answers.getStudentAnswer());
            String modelAnswer = Parameters.getModelAnswer();
            String studentAnswer = Parameters.getStudentAnswer();
            List<String> modelAnsTokens = new ArrayList();
            List<String> studentAnsTokens = new ArrayList();

            if (modelAnswer != null) {
                StringTokenizer tokenizer = new StringTokenizer(modelAnswer);
                while (tokenizer.hasMoreTokens()) {
                    String token = tokenizer.nextToken();
                    if (token.indexOf('.') >= 0) {
                        token = new StringBuffer(token).deleteCharAt(token.indexOf('.')).toString();
                    }
                    if(token.indexOf(',') >=0){
                        for(String word : Arrays.asList(token.split(","))){
                            modelAnsTokens.add(word);
                        }
                    }else {
                        modelAnsTokens.add(token);
                    }
                }
            }

            if (studentAnswer == null) {
            } else {
                StringTokenizer tokenizer = new StringTokenizer(studentAnswer);
                while (tokenizer.hasMoreTokens()) {
                    String token = tokenizer.nextToken();       //todo : check for other special characters too
                    if (token.indexOf('.') >= 0) {
                        token = new StringBuffer(token).deleteCharAt(token.indexOf('.')).toString();
                    }
                    if(token.indexOf(',') >=0){
                        for(String word : Arrays.asList(token.split(","))){
                            studentAnsTokens.add(word);
                        }
                    }else {
                        studentAnsTokens.add(token);
                    }
                }
            }

            Parameters.setModelAnsTokens(modelAnsTokens);
            Parameters.setStudentAnsTokens(studentAnsTokens);
            wordSimilarity.createJointWordList(modelAnsTokens, studentAnsTokens);
            //createJointWordSet(modelAnsTokens,studentAnsTokens);

            // }
            //   }

   /* private static void createJointWordSet(List modelAnsTokens,List studentAnsTokens) {
        Parameters parameters = new Parameters();
        TamilStemmer tamilStemmer = new TamilStemmer();
        List<String> numberList = new LinkedList<>();
        WordSimilarity wordSimilarity = new WordSimilarity();

        List jointWordList = new ArrayList();
        jointWordList.addAll(modelAnsTokens);
        for(Object stuAns : studentAnsTokens){
            if(!(jointWordList.contains(stuAns))){
                jointWordList.add(stuAns);
            }
        }

        parameters.setJointWordList(jointWordList);
        wordSimilarity.findWordSimilarity(jointWordList);


       *//* for(Object word : jointWordList){
            if(NumberUtils.isNumber(word.toString())){
                numberList.add(word.toString());
            }else{
                System.out.println(tamilStemmer.stem(word.toString()));
            }

        }

        parameters.setJointWordList(jointWordList);*//*
    }*/

        }
    }
}

