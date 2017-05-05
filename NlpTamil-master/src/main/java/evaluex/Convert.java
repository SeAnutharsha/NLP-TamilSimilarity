package evaluex;
import static evaluex.TamilCharacters.*;

/**
 * Created by Anu on 1/6/2017.
 */
import java.util.*;
import java.lang.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Convert
{
    Map<String,String> ETMAP = new LinkedHashMap<>();
    public String eVowels[] = {"a","aa","i","ii","u","uu","e","ee","ai","o","oo","au"};
    public String eTamil_letters []= { "k","ka","kaa","ki","kii","ku","kuu","ke","kee","kai","ko","koo","kau",
            "ng","nga","ngaa","ngi","ngii","ngu","nguu","nge","ngee","ngai","ngo", "ngoo","ngau",
            "c","ca","caa","ci","cii","cu","cuu","ce","cee","cai",
            "co","coo","cau",
            "nj","nja","njaa","nji","njii","nju","njuu","nje","njee",
            "njai","njo","njoo","njau",
            "T","Ta","Taa","Ti","Tii","Tu","Tuu","Te","Tee","Tai","To","Too","Tau",
            "N","Na","Naa","Ni","Nii","Nu","Nuu","Ne","Nee","Nai","No","Noo","Nau",
            "t","ta","taa","ti","tii","tu","tuu","te","tee","tai","to","too","tau",
            "nd","nda","ndaa","ndi","ndii","ndu","nduu","nde","ndee","ndai","ndo","ndoo",
            "ndau",
            "p","pa","paa","pi","pii","pu","puu","pe","pee","pai","po","poo","pau",
            "m","ma","maa","mi","mii","mu","muu","me","mee","mai","mo","moo","mau",
            "y","ya","yaa","yi","yii","yu","yuu","ye","yee","yai","yo","yoo","yau",
            "r","ra","raa","ri","rii","ru","ruu","re","ree","rai","ro","roo","rau",
            "l","la","laa","li","lii","lu","luu","le","lee","lai","lo","loo","lau",
            "v","va","vaa","vi","vii","vu","vuu","ve","vee","vai","vo","voo","vau",
            "zh","zha","zhaa","zhi","zhii","zhu","zhuu","zhe","zhee","zhai","zho","zhoo",
            "zhau",
            "L","La","Laa","Li","Lii","Lu","Luu","Le","Lee","Lai","Lo","Loo","Lau",
            "R","Ra","Raa","Ri","Rii","Ru","Ruu","Re","Ree","Rai","Ro","Roo","Rau",
            "n","na","naa","ni","nii","nu","nuu","ne","nee","nai","no","noo","nau",
            "j","ja","jaa","ji","jii","ju","juu","je","jee","jai","jo","joo","jau",
            "s","sa","saa","si","sii","su","suu","se","see","sai","so","soo","sau",
            "Sh","Sha","Shaa","Shi","Shii","Shu","Shuu","She","Shee","Shai","Sho","Shoo",
            "Shau",
            "h","ha","haa","hi","hii","hu","huu","he","hee","hai","ho","hoo","hau",};

    public String tVowels[] = {"அ","ஆ","இ","ஈ","உ","ஊ","எ","ஏ","ஐ","ஒ","ஓ","ஔ"}; // TODO ADD "Q"
    public String tTamil_words [] = {"க்","க","கா","கி","கீ","கு","கூ","கெ","கே","கை","கொ","கோ","கௌ",
            "ங்","ங","ஙா","ஙி","ஙீ","ஙு","ஙூ","ஙெ","ஙே","ஙை","ஙொ","ஙோ","ஙௌ",
            "ச்","ச","சா","சி","சீ","சு","சூ","செ","சே","சை","சொ","சோ","சௌ",
            "ஞ்","ஞ","ஞா","ஞி","ஞீ","ஞு","ஞூ","ஞெ","ஞே","ஞை","ஞொ","ஞோ","ஞௌ",
            "ட்","ட","டா","டி","டீ","டு","டூ","டெ","டே","டை","டொ","டோ","டௌ",
            "ண்","ண","ணா","ணி","ணீ","ணு","ணூ","ணெ","ணே","ணை","ணொ","ணோ","ணௌ",
            "த்","த","தா","தி","தீ","து","தூ","தெ","தே","தை","தொ","தோ","தௌ",
            "ந்","ந","நா","நி","நீ","நு","நூ","நெ","நே","நை","நொ","நோ","நௌ",
            "ப்","ப","பா","பி","பீ","பு","பூ","பெ","பே","பை","பொ","போ","பௌ",
            "ம்","ம","மா","மி","மீ","மு","மூ","மெ","மே","மை","மொ","மோ","மௌ",
            "ய்","ய","யா","யி","யீ","யு","யூ","யெ","யே","யை","யொ","யோ","யௌ",
            "ர்","ர","ரா","ரி","ரீ","ரு","ரூ","ரெ","ரே","ரை","ரொ","ரோ","ரௌ",
            "ல்","ல","லா","லி","லீ","லு","லூ","லெ","லே","லை","லொ","லோ","லௌ",
            "வ்","வ","வா","வி","வீ","வு","வூ","வெ","வே","வை","வொ","வோ","வௌ",
            "ழ்","ழ","ழா","ழி","ழீ","ழு","ழூ","ழெ","ழே","ழை","ழொ","ழோ","ழௌ",
            "ள்","ள","ளா","ளி","ளீ","ளு","ளூ","ளெ","ளே","ளை","ளொ","ளோ","ளௌ",
            "ற்","ற","றா","றி","றீ","று","றூ","றெ","றே","றை","றொ","றோ","றௌ",
            "ன்","ன","னா","னி","னீ","னு","னூ","னெ","னே","னை","னொ","னோ","னௌ",
            "ஜ்","ஜ","ஜா","ஜி","ஜீ","ஜு","ஜூ","ஜெ","ஜே","ஜை","ஜொ","ஜோ","ஜௌ",
            "ஸ்","ஸ","ஸா","ஸி","ஸீ","ஸு","ஸூ","ஸெ","ஸே","ஸை","ஸொ","ஸோ","ஸௌ",
            "ஷ்","ஷ","ஷா","ஷி","ஷீ","ஷு","ஷூ","ஷெ","ஷே","ஷை","ஷொ","ஷோ","ஷௌ",
            "ஹ்","ஹ","ஹா","ஹி","ஹீ","ஹு","ஹூ","ஹெ","ஹே","ஹை","ஹொ","ஹோ","ஹௌ"
    };

    public void setETMap(){
        if(eVowels.length == tVowels.length){
            for(int i = 0; i<tVowels.length;i++){
                ETMAP.put(tVowels[i],eVowels[i]);
            }
        }
        if(eTamil_letters.length == tTamil_words.length){
            for(int i = 0; i<tTamil_words.length;i++){
                ETMAP.put(tTamil_words[i],eTamil_letters[i]);
            }
        }

    }

    public Map<String,String> getETMAP(){
        return ETMAP;
    }

    public String convertTE(String in,Map<String,String> map)
    {
        int length = in.length();
        List<String> tVowelsList = Arrays.asList(tVowels);
        String newWord = null;
        Pattern pattern = Pattern.compile(".*\\D.*");
        Pattern pattern1 = Pattern.compile("[<>+-=≡~≈<<>>>=<=*.×/÷—%∠⊥]");
        Matcher m = pattern.matcher(in);
        Matcher m1 = pattern1.matcher(in);
        boolean match = m.matches();
        boolean match1 = m1.matches();

        if(match) {
            if (!match1) {
                for (int i = 0; i < length; i++) {
                    char x = in.charAt(i);
                    if (i < length - 1) {
                        if (tVowelsList.contains(Character.toString(x))) {
                            if (newWord == null) {
                                newWord = map.get(Character.toString(x));
                            } else {
                                newWord = newWord.concat(map.get(Character.toString(x)));
                            }
                        } else if ((x == (char) ka || x == (char) nga || x == (char) ca || x == (char) nya || x == (char) tta ||
                                x == (char) nna || x == (char) ta || x == (char) na || x == (char) pa || x == (char) ma ||
                                x == (char) ya || x == (char) ra || x == (char) la || x == (char) va || x == (char) llla ||
                                x == (char) lla || x == (char) rra || x == (char) nnna || x == (char) ja || x == (char) sa ||
                                x == (char) ssa || x == (char) ha) && !(in.charAt(i + 1) == (char) pulli || in.charAt(i + 1) == (char) vs_aa ||
                                in.charAt(i + 1) == (char) vs_i || in.charAt(i + 1) == (char) vs_ii || in.charAt(i + 1) == (char) vs_u ||
                                in.charAt(i + 1) == (char) vs_uu || in.charAt(i + 1) == (char) vs_e || in.charAt(i + 1) == (char) vs_ee ||
                                in.charAt(i + 1) == (char) vs_ai || in.charAt(i + 1) == (char) vs_o || in.charAt(i + 1) == (char) vs_oo ||
                                in.charAt(i + 1) == (char) vs_au)) {
                            if (newWord == null) {
                                newWord = map.get(Character.toString(x));
                            } else {
                                newWord = newWord.concat(map.get(Character.toString(x)));
                            }

                        } else if ((x == (char) ka || x == (char) nga || x == (char) ca || x == (char) nya || x == (char) tta ||
                                x == (char) nna || x == (char) ta || x == (char) na || x == (char) pa || x == (char) ma ||
                                x == (char) ya || x == (char) ra || x == (char) la || x == (char) va || x == (char) llla ||
                                x == (char) lla || x == (char) rra || x == (char) nnna || x == (char) ja || x == (char) sa ||
                                x == (char) ssa || x == (char) ha) && (in.charAt(i + 1) == (char) pulli || in.charAt(i + 1) == (char) vs_aa ||
                                in.charAt(i + 1) == (char) vs_i || in.charAt(i + 1) == (char) vs_ii || in.charAt(i + 1) == (char) vs_u ||
                                in.charAt(i + 1) == (char) vs_uu || in.charAt(i + 1) == (char) vs_e || in.charAt(i + 1) == (char) vs_ee ||
                                in.charAt(i + 1) == (char) vs_ai || in.charAt(i + 1) == (char) vs_o || in.charAt(i + 1) == (char) vs_oo ||
                                in.charAt(i + 1) == (char) vs_au)) {
                            String word1 = new StringBuilder().append(x).append(in.charAt(i + 1)).toString();
                            if (newWord == null) {
                                newWord = map.get(word1);
                            } else {
                                newWord = newWord.concat(map.get(word1));
                            }

                        }
                    } else if (i == length - 1) {
                        if (tVowelsList.contains(Character.toString(x)) || (x == (char) ka || x == (char) nga || x == (char) ca || x == (char) nya || x == (char) tta ||
                                x == (char) nna || x == (char) ta || x == (char) na || x == (char) pa || x == (char) ma ||
                                x == (char) ya || x == (char) ra || x == (char) la || x == (char) va || x == (char) llla ||
                                x == (char) lla || x == (char) rra || x == (char) nnna || x == (char) ja || x == (char) sa ||
                                x == (char) ssa || x == (char) ha)) {
                            if (newWord == null) {
                                newWord = map.get(Character.toString(x));
                            } else {
                                newWord = newWord.concat(map.get(Character.toString(x)));
                            }
                        }
                    }
                }
            }
            else{
                newWord = in;
            }
        }
        else{
            newWord = in;
        }
        return newWord;
    }

}

