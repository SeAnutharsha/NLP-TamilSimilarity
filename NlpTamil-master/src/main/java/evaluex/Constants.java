package evaluex;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Anu on 12/2/2016.
 */
public class Constants {

    Map<String,String> symbolMap = new LinkedHashMap<>();

    public Constants(){
        setMap();
    }

    class symbol{

        public static final String EQUAL = "=";
        public static final String UNEQUAL = "≠";
        public static final String EQUALENCE = "≡";
        public static final String APPROXIMATELY = "~";
        public static final String APPROXIMATELY1 = "≈";
        public static final String GREATER_THAN = ">";
        public static final String LESS_THAN = "<";
        public static final String VERY_MUCH_LESS_THAN = "<<";
        public static final String VERY_MUCH_GREATER_THAN = ">>";
        public static final String GREATER_EQUAL = ">=";
        public static final String LESS_EQUAL = "<=";
        public static final String PLUS = "+";
        public static final String MINUS = "-";
        public static final String MULTIPLICATION_1 = "*";
        public static final String MULTIPLICATION_2 = ".";
        public static final String MULTIPLICATION_3 = "×";
        public static final String DIVISION_1 = "/";
        public static final String DIVISION_2 = "÷";
        public static final String DIVISION_3 = "—";
        public static final String PERCENTAGE = "%";
        public static final String ANGLE = "∠";
        public static final String PERPENDICULAR = "⊥";

    }

    public void setMap(){

        symbolMap.put(symbol.EQUAL,"சமன்");
        symbolMap.put(symbol.UNEQUAL,"சமனில்லை");
        symbolMap.put(symbol.APPROXIMATELY,"அண்ணளவு");
        symbolMap.put(symbol.APPROXIMATELY1,"அண்ணளவு");
        symbolMap.put(symbol.EQUALENCE,"சர்வசமன்");
        symbolMap.put(symbol.GREATER_THAN,"பெரிது");
        symbolMap.put(symbol.LESS_THAN,"சிறிது");
        symbolMap.put(symbol.VERY_MUCH_LESS_THAN,"மிகவும் சிறிது");
        symbolMap.put(symbol.VERY_MUCH_GREATER_THAN,"மிகவும் பெரிது");
         symbolMap.put(symbol.GREATER_EQUAL,"பெரிது சமன்");
        symbolMap.put(symbol.LESS_EQUAL,"சிறிது சமன்");
        symbolMap.put(symbol.PLUS,"கூட்டல்");
        symbolMap.put(symbol.MINUS,"கழித்தல்");
        symbolMap.put(symbol.MULTIPLICATION_1,"பெருக்கல்");
        symbolMap.put(symbol.MULTIPLICATION_2,"பெருக்கல்");
        symbolMap.put(symbol.MULTIPLICATION_3,"பெருக்கல்");
        symbolMap.put(symbol.DIVISION_1,"வகுத்தல்");
        symbolMap.put(symbol.DIVISION_2,"வகுத்தல்");
        symbolMap.put(symbol.DIVISION_3,"வகுத்தல்");
        symbolMap.put(symbol.PERCENTAGE,"சதவீதம்");
        symbolMap.put(symbol.ANGLE,"கோணம்");
        symbolMap.put(symbol.PERPENDICULAR,"செங்குத்து");
    }

    public Map<String,String> getMap(){
        return symbolMap;
    }

}

