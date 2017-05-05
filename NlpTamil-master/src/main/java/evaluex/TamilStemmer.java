package evaluex;

import static evaluex.TamilCharacters.*;

/**
 * Created by Anu on 2016-08-30.
 */
public class TamilStemmer {

   // public boolean found_vallinam_doubling = true;
    public boolean found_a_match = true;
    public boolean found_wrong_ending = true;
   // public boolean was_stripped  =true;
    public boolean found_vetrumai_urupu = true;
    public int min_length = 4;

    public String stem(String word){

        word =  fix_ending(word);
        word = remove_question_prefixes(word);
        word = remove_pronoun_prefixes(word);
        word = remove_question_suffixes(word);
        word = remove_um(word);
        word = remove_common_word_endings(word);
        word = remove_vetrumai_urupukal(word);
        word = remove_plural_suffix(word);
        word = remove_command_suffixes(word);
        word = remove_tense_suffixes(word);
        return word;
    }

    private boolean has_min_length(String word,int length){
        boolean status = false;
        if(word.length()> length) {
            status = true;
        }
        return status;
    }

    private String remove_question_suffixes(String word) {

        if(has_min_length(word,4)) {

            found_a_match = false;
            int length = word.length();

            if (word.charAt(length - 1) == (char) vs_oo || word.charAt(length - 1) == (char) vs_ee || word.charAt(length - 1) == (char) vs_aa) {
                word.replace(word.charAt(length - 1), (char) pulli);
            }
            found_a_match = true;
        }
        return fix_endings(word);

    }


    private String remove_plural_suffix(String word) {
        found_a_match = false;
        int length = word.length();
        if(length > 7) {
            if ((word.charAt(length - 2) == (char) pulli) && (word.charAt(length - 3) == (char) lla) && (word.charAt(length - 4) == (char) ka) &&
                    (word.charAt(length - 5) == (char) pulli) && (word.charAt(length - 6) == (char) nga) && (word.charAt(length - 7) == (char) vs_u)) {
                if (!(word.charAt(length - 1) == (char) ka || word.charAt(length - 1) == (char) ca || word.charAt(length - 1) == (char) tta ||
                        word.charAt(length - 1) == (char) tha || word.charAt(length - 1) == (char) pa || word.charAt(length - 1) == (char) rra)) {
                    word = new StringBuffer(word).replace(length - 7, length, Character.toString((char) pulli)).toString();
                }
            } else if ((word.charAt(length - 1) == (char) pulli) && (word.charAt(length - 2) == (char) lla) && (word.charAt(length - 3) == (char) ka) &&
                    (word.charAt(length - 4) == (char) pulli) && (word.charAt(length - 5) == (char) rra)) {
                String temp_word = new StringBuffer(word).delete(length - 5, length).toString();
                word = new StringBuffer(temp_word).append((char) la).append((char) pulli).toString();
            } else if ((word.charAt(length - 1) == (char) pulli) && (word.charAt(length - 2) == (char) lla) && (word.charAt(length - 3) == (char) ka) &&
                    (word.charAt(length - 4) == (char) pulli) && (word.charAt(length - 5) == (char) tta)) {
                String temp_word = new StringBuffer(word).delete(length - 5, length).toString();
                word = new StringBuffer(temp_word).append((char) lla).append((char) pulli).toString();
            } else if ((word.charAt(length - 1) == (char) pulli) && (word.charAt(length - 2) == (char) lla) && (word.charAt(length - 3) == (char) ka)) {
                word = new StringBuffer(word).delete(length - 3, length).toString();
            }
            found_a_match = true;
        }
        return word;
    }

    private String remove_question_prefixes(String word) {

        int length = word.length();
        if(length > 2) {
            if (word.charAt(0) == (char) e) {
                if (((word.charAt(1) == (char) ka) || (word.charAt(1) == (char) ca) || (word.charAt(1) == (char) tha) || (word.charAt(1) == (char) va) ||
                        (word.charAt(1) == (char) na) || (word.charAt(1) == (char) pa) || (word.charAt(1) == (char) ma) || (word.charAt(1) == (char) ya) ||
                        (word.charAt(1) == (char) nga) || (word.charAt(1) == (char) nya)) && (word.charAt(2) == (char) ka)) {
                    word = new StringBuffer(word).delete(0, 3).toString();
                }
            }
        }

        return fix_va_start(word);
    }

    private String remove_pronoun_prefixes(String word) {

        found_a_match = false;

        if ((word.charAt(2) == (char)pulli) && (word.charAt(1) == (char)ka || word.charAt(1) == (char)ca || word.charAt(1) == (char)tha ||
                word.charAt(1) == (char)va || word.charAt(1) == (char)na || word.charAt(1) == (char)pa || word.charAt(1) == (char)ma ||
                word.charAt(1) == (char)ya || word.charAt(1) == (char)nga || word.charAt(1) == (char)nya) && (word.charAt(0) == (char)a ||
                word.charAt(0) == (char)i || word.charAt(0) == (char)u)) {
            word = new StringBuffer(word).delete(0, 3).toString();
        }
        found_a_match = true;
        return fix_va_start(word);
    }

    private String remove_command_suffixes(String word) {

        if(has_min_length(word,4)) {

            found_a_match = false;
            int length = word.length();

            if ((word.charAt(length - 1) == (char) vs_i && word.charAt(length - 2) == (char) pa)
                    || (word.charAt(length - 1) == (char) vs_i && word.charAt(length - 2) == (char) va)) {
                word = new StringBuffer(word).delete(length - 2, length).toString();
            }

            found_a_match = true;
        }
        return word;
    }

    private String remove_um(String word) {

        if(has_min_length(word,4)) {
            found_a_match = false;
            int length = word.length();

            if (word.charAt(length - 1) == (char) pulli || word.charAt(length - 2) == (char) ma || word.charAt(length - 3) == (char) vs_u) {
                word = new StringBuffer(word).replace(length - 3, length, Character.toString((char) pulli)).toString();
            }
            found_a_match = true;
        }
        return fix_ending(word);
    }

    private String remove_vetrumai_urupukal(String word) {

        found_a_match = false;
        found_vetrumai_urupu = false;
        int length = word.length();
        if(has_min_length(word,4)) {
            if ((word.charAt(length - 1) == (char) vs_ai) && (word.charAt(length - 2) == (char) nnna)) {
                word = new StringBuffer(word).delete(length - 2, length).toString();
            } else if (!(word.charAt(length - 1) == (char) ka && word.charAt(length - 1) == (char) ca && word.charAt(length - 1) == (char) tta &&
                    word.charAt(length - 1) == (char) tha && word.charAt(length - 1) == (char) pa && word.charAt(length - 1) == (char) rra) &&
                    word.charAt(length - 2) == (char) vs_ai && word.charAt(length - 3) == (char) nnna && word.charAt(length - 4) == (char) vs_i) {
                word = new StringBuffer(word).replace(length - 4, length, Character.toString((char) pulli)).toString();

            } else if (!(word.charAt(length - 1) == (char) ka && word.charAt(length - 1) == (char) ca && word.charAt(length - 1) == (char) tta &&
                    word.charAt(length - 1) == (char) tha && word.charAt(length - 1) == (char) pa && word.charAt(length - 1) == (char) rra) &&
                    word.charAt(length - 2) == (char) vs_ai) {
                word = new StringBuffer(word).replace(length - 2, length, Character.toString((char) pulli)).toString();

            } else if (word.charAt(length - 1) == (char) pulli && !(word.charAt(length - 2) == (char) ka && word.charAt(length - 2) == (char) ca && word.charAt(length - 2) == (char) tta &&
                    word.charAt(length - 2) == (char) tha && word.charAt(length - 2) == (char) pa && word.charAt(length - 2) == (char) rra) &&
                    word.charAt(length - 3) == (char) vs_ai) {
                word = new StringBuffer(word).replace(length - 3, length, Character.toString((char) pulli)).toString();

            } else if ((word.charAt(length - 1) == (char) vs_u && word.charAt(length - 2) == (char) tta && word.charAt(length - 3) == (char) vs_o) ||
                    (word.charAt(length - 1) == (char) vs_u && word.charAt(length - 2) == (char) tta && word.charAt(length - 3) == (char) vs_oo) ||
                    (word.charAt(length - 1) == (char) pulli && word.charAt(length - 2) == (char) la && word.charAt(length - 3) == (char) vs_i) ||
                    (word.charAt(length - 1) == (char) pulli && word.charAt(length - 2) == (char) rra && word.charAt(length - 3) == (char) vs_i) ||
                    (word.charAt(length - 1) == (char) tta && word.charAt(length - 2) == (char) vs_i && word.charAt(length - 3) == (char) va) ||
                    (word.charAt(length - 1) == (char) pulli && word.charAt(length - 2) == (char) la && word.charAt(length - 3) == (char) vs_aa) ||
                    (word.charAt(length - 1) == (char) vs_ai && word.charAt(length - 2) == (char) tta && word.charAt(length - 3) == (char) vs_u) ||
                    (word.charAt(length - 1) == (char) pulli && word.charAt(length - 2) == (char) lla && word.charAt(length - 3) == (char) vs_u)) {
                word = new StringBuffer(word).replace(length - 3, length, Character.toString((char) pulli)).toString();

            } else if ((word.charAt(length - 1) == (char) pulli && word.charAt(length - 2) == (char) ma && word.charAt(length - 3) == (char) tta &&
                    word.charAt(length - 4) == (char) vs_i) || (word.charAt(length - 1) == (char) pulli && word.charAt(length - 2) == (char) la && word.charAt(length - 3) == (char) ma &&
                    word.charAt(length - 4) == (char) vs_aa)) {
                word = new StringBuffer(word).replace(length - 4, length, Character.toString((char) pulli)).toString();

            } else if (word.charAt(length - 1) == (char) vs_u && word.charAt(length - 2) == (char) rra && word.charAt(length - 3) == (char) pulli &&
                    word.charAt(length - 4) == (char) nnna && word.charAt(length - 5) == (char) vs_i) {
                word = new StringBuffer(word).replace(length - 5, length, Character.toString((char) pulli)).toString();

            } else if (word.charAt(length - 1) == (char) vs_u && word.charAt(length - 2) == (char) ta && word.charAt(length - 3) == (char) pulli &&
                    word.charAt(length - 4) == (char) na && word.charAt(length - 5) == (char) vs_u && word.charAt(length - 6) == (char) ra && word.charAt(length - 7) == (char) vs_i) {
                word = new StringBuffer(word).replace(length - 7, length, Character.toString((char) pulli)).toString();
            } else if (!(word.charAt(length - 1) == (char) ma) && word.charAt(length - 2) == (char) pulli && word.charAt(length - 3) == (char) nnna &&
                    word.charAt(length - 4) == (char) vs_i) {
                word = new StringBuffer(word).replace(length - 4, length, Character.toString((char) pulli)).toString();

            } else if (!(word.charAt(length - 1) == (char) vs_aa || word.charAt(length - 1) == (char) vs_i || word.charAt(length - 1) == (char) vs_ii ||
                    word.charAt(length - 1) == (char) vs_e || word.charAt(length - 1) == (char) vs_ee || word.charAt(length - 1) == (char) vs_u ||
                    word.charAt(length - 1) == (char) vs_uu || word.charAt(length - 1) == (char) vs_ai) && word.charAt(length - 2) == (char) pulli &&
                    word.charAt(length - 3) == (char) la) {
                word = new StringBuffer(word).replace(length - 3, length, Character.toString((char) pulli)).toString();

            } else if (word.charAt(length - 1) == (char) pulli && word.charAt(length - 2) == (char) nna && word.charAt(length - 3) == (char) ka) {
                word = new StringBuffer(word).delete(length - 3, length).toString();

            } else if ((word.charAt(length - 1) == (char) pulli && word.charAt(length - 2) == (char) nnna && word.charAt(length - 3) == (char) vs_u &&
                    word.charAt(length - 4) == (char) ma) || (word.charAt(length - 1) == (char) pulli && word.charAt(length - 2) == (char) la && word.charAt(length - 3) == (char) vs_ee &&
                    word.charAt(length - 4) == (char) ma) || (word.charAt(length - 1) == (char) pulli && word.charAt(length - 2) == (char) rra && word.charAt(length - 3) == (char) vs_ee &&
                    word.charAt(length - 4) == (char) ma) || (word.charAt(length - 1) == (char) pulli && word.charAt(length - 2) == (char) llla && word.charAt(length - 3) == (char) vs_ii &&
                    word.charAt(length - 4) == (char) ka) || (word.charAt(length - 1) == (char) pulli && word.charAt(length - 2) == (char) nnna && word.charAt(length - 3) == (char) vs_i &&
                    word.charAt(length - 4) == (char) pa)) {
                word = new StringBuffer(word).delete(length - 4, length).toString();

            } else if (!(word.charAt(length - 1) == (char) vs_aa || word.charAt(length - 1) == (char) vs_i || word.charAt(length - 1) == (char) vs_ii ||
                    word.charAt(length - 1) == (char) vs_e || word.charAt(length - 1) == (char) vs_ee || word.charAt(length - 1) == (char) vs_u ||
                    word.charAt(length - 1) == (char) vs_uu || word.charAt(length - 1) == (char) vs_ai) && word.charAt(length - 2) == (char) vs_u &&
                    word.charAt(length - 3) == (char) ta) {
                word = new StringBuffer(word).delete(length - 3, length).toString();

            } else if (word.charAt(length - 1) == (char) vs_ii) {
                word = word.replace((char) vs_ii, (char) vs_i);

            }

            found_a_match = true;
            found_vetrumai_urupu = true;

            if(word.length()> 3) {

                if (word.charAt(length - 1) == (char) pulli && word.charAt(length - 2) == (char) nnna && word.charAt(length - 3) == (char) vs_i) {
                    word = new StringBuffer(word).replace(length - 3, length, Character.toString((char) pulli)).toString();
                }
            }
        }
        return fix_endings(word);
    }

    private String fix_va_start(String word) {

        if(word.indexOf((char)va) == 0){
            if(word.indexOf((char)vs_oo) == 1){
                word.replace(word.substring(0,1),Character.toString((char)oo));
            }else if(word.indexOf((char)vs_o) == 1){
                word.replace(word.substring(0,1),Character.toString((char)o));
            }else if(word.indexOf((char)vs_u) == 1){
                word.replace(word.substring(0,1),Character.toString((char)u));
            }else if(word.indexOf((char)vs_uu) == 1){
                word.replace(word.substring(0,1),Character.toString((char)uu));
            }
        }
        return word;

    }

    @SuppressWarnings("unchecked")
    private String fix_ending(String word) {

        found_wrong_ending = false;
        int length = word.length();
        if(has_min_length(word,3)) {
            String newWord = null;

            if (word.charAt(length - 1) == (char) pulli && word.charAt(length - 2) == na) {
                newWord = new StringBuffer(word).delete(length - 2, length).toString();
            } else if (word.charAt(length - 1) == (char) ta && word.charAt(length - 2) == (char) pulli && word.charAt(length - 3) == (char) na) {
                newWord = new StringBuffer(word).delete(length - 3, length).toString();
            } else if (word.charAt(length - 1) == (char) pulli && word.charAt(length - 2) == (char) ta && word.charAt(length - 3) == (char) pulli && word.charAt(length - 4) == (char) na) {
                newWord = new StringBuffer(word).delete(length - 4, length).toString();
            } else if (word.charAt(length - 2) == (char) pulli && word.charAt(length - 3) == (char) na) {
                if (word.charAt(length - 1) == (char) vs_ai || word.charAt(length - 1) == (char) vs_i || word.charAt(length - 1) == (char) vs_ii) {
                    newWord = new StringBuffer(word).delete(length - 3, length).toString();
                }
            } else if ((word.charAt(length - 1) == (char) pulli && word.charAt(length - 2) == (char) pa && word.charAt(length - 3) == (char) pulli && word.charAt(length - 4) == (char) tta)
                    || (word.charAt(length - 1) == (char) pulli && word.charAt(length - 2) == (char) ka && word.charAt(length - 3) == (char) pulli && word.charAt(length - 4) == (char) tta)) {
                String tempWord = new StringBuffer(word).delete(length - 4, length).toString();
                newWord = new StringBuffer(tempWord).append((char) lla).append((char) pulli).toString();
            } else if (word.charAt(length - 1) == (char) pulli && word.charAt(length - 2) == (char) rra && word.charAt(length - 3) == (char) pulli && word.charAt(length - 4) == (char) nnna) {
                String tempWord = new StringBuffer(word).delete(length - 4, length).toString();
                newWord = new StringBuffer(tempWord).append((char) la).append((char) pulli).toString();
            } else if (word.charAt(length - 1) == (char) pulli && word.charAt(length - 2) == (char) ka && word.charAt(length - 3) == (char) pulli && word.charAt(length - 4) == (char) rra) {
                String tempWord = new StringBuffer(word).delete(length - 4, length).toString();
                newWord = new StringBuffer(tempWord).append((char) la).append((char) pulli).toString();
            } else if (word.charAt(length - 1) == (char) pulli && word.charAt(length - 2) == (char) tta && word.charAt(length - 3) == (char) pulli && word.charAt(length - 4) == (char) tta) {
                String tempWord = new StringBuffer(word).delete(length - 4, length).toString();
                newWord = new StringBuffer(tempWord).append((char) tta).append((char) vs_u).toString();
            } else if (found_vetrumai_urupu) {
                if (word.charAt(length - 2) == (char) pulli && word.charAt(length - 3) == (char) rra && word.charAt(length - 4) == (char) pulli && word.charAt(length - 5) == (char) nnna &&
                        !(word.charAt(length - 1) == (char) vs_ai)) {
                    String tempWord = new StringBuffer(word).delete(length - 5, length).toString();
                    newWord = new StringBuffer(tempWord).append((char) ma).append((char) pulli).toString();
                }
            } else if (word.charAt(length - 1) == (char) pulli && word.charAt(length - 2) == (char) ka && word.charAt(length - 3) == (char) vs_u) {
                newWord = new StringBuffer(word).replace(length - 3, length, Character.toString((char) pulli)).toString();
            } else if (word.charAt(length - 1) == (char) pulli && word.charAt(length - 2) == (char) ka && word.charAt(length - 3) == (char) pulli &&
                    word.charAt(length - 4) == (char) ka && word.charAt(length - 5) == (char) vs_u) {
                newWord = new StringBuffer(word).replace(length - 5, length, Character.toString((char) pulli)).toString();
            } else if ((word.charAt(length - 1) == (char) ka || word.charAt(length - 1) == (char) ca || word.charAt(length - 1) == (char) tta ||      // TODO: 2016-09-07 check the order
                    word.charAt(length - 1) == (char) tha || word.charAt(length - 1) == (char) pa || word.charAt(length - 1) == (char) rra) &&
                    word.charAt(length - 2) == (char) pulli && (word.charAt(length - 3) == (char) ka || word.charAt(length - 3) == (char) ca ||
                    word.charAt(length - 3) == (char) tta || word.charAt(length - 3) == (char) tha || word.charAt(length - 3) == (char) pa ||
                    word.charAt(length - 3) == (char) rra) && word.charAt(length - 4) == (char) pulli) {
                newWord = new StringBuffer(word).delete(length - 4, length).toString();
            } else if (word.charAt(length - 1) == (char) pulli && word.charAt(length - 2) == (char) ka && word.charAt(length - 3) == (char) vs_u) {
                newWord = new StringBuffer(word).replace(length - 3, length, Character.toString((char) pulli)).toString();
            } else if (word.charAt(length - 1) == (char) pulli && (word.charAt(length - 2) == (char) ka || word.charAt(length - 2) == (char) ca ||
                    word.charAt(length - 2) == (char) tta || word.charAt(length - 2) == (char) tha || word.charAt(length - 2) == (char) rra)) {
                newWord = new StringBuffer(word).delete(length - 2, length).toString();
            } else if (word.charAt(length - 1) == (char) pulli && (word.charAt(length - 2) == (char) ya || word.charAt(length - 2) == (char) ra ||
                    word.charAt(length - 2) == (char) la || word.charAt(length - 2) == (char) va || word.charAt(length - 2) == (char) zha ||
                    word.charAt(length - 2) == (char) lla || word.charAt(length - 2) == (char) nga || word.charAt(length - 2) == (char) nya ||
                    word.charAt(length - 2) == (char) nna || word.charAt(length - 2) == (char) na || word.charAt(length - 2) == (char) ma ||
                    word.charAt(length - 2) == (char) nnna) && word.charAt(length - 3) == (char) pulli) {
                newWord = new StringBuffer(word).replace(length - 3, length, Character.toString((char) pulli)).toString();
            } else if (word.charAt(length - 1) == (char) va || word.charAt(length - 1) == (char) ya) {
                newWord = new StringBuffer(word).deleteCharAt(length - 1).toString();
            } else if (word.charAt(length - 1) == (char) pulli && word.charAt(length - 2) == (char) va) {
                newWord = new StringBuffer(word).delete(length - 2, length).toString();
            } else if (!(word.charAt(length - 1) == (char) vs_aa || word.charAt(length - 1) == (char) vs_i || word.charAt(length - 1) == (char) vs_ii ||
                    word.charAt(length - 1) == (char) vs_e || word.charAt(length - 1) == (char) vs_ee || word.charAt(length - 1) == (char) vs_u ||
                    word.charAt(length - 1) == (char) vs_uu || word.charAt(length - 1) == (char) vs_ai) && word.charAt(length - 2) == (char) vs_u && word.charAt(length - 3) == (char) nnna) {
                newWord = new StringBuffer(word).delete(length - 3, length).toString();
            } else if (!(word.charAt(length - 1) == (char) vs_ai) && word.charAt(length - 2) == (char) pulli && word.charAt(length - 3) == (char) nga) {
                String tempWord = new StringBuffer(word).delete(length - 3, length).toString();
                newWord = new StringBuffer(tempWord).append((char) ma).append((char) pulli).toString();
            } else if (word.charAt(length - 1) == (char) pulli && word.charAt(length - 2) == (char) nga) {
                newWord = new StringBuffer(word).delete(length - 2, length).toString();
            } else if ((word.charAt(length - 1) == (char) vs_aa || word.charAt(length - 1) == (char) vs_i || word.charAt(length - 1) == (char) vs_ii ||
                    word.charAt(length - 1) == (char) vs_e || word.charAt(length - 1) == (char) vs_ee || word.charAt(length - 1) == (char) vs_u ||
                    word.charAt(length - 1) == (char) vs_uu || word.charAt(length - 1) == (char) vs_ai || word.charAt(length - 1) == (char) pulli) && word.charAt(length - 2) == (char) pulli) {
                newWord = new StringBuffer(word).delete(length - 2, length).toString();
            }
            if (newWord != null) {
                if (!(word.equalsIgnoreCase(newWord))) {
                    found_wrong_ending = true;
                    word = newWord;
                }
            }
        }

        return word;

    }

    private String fix_endings(String word) {

        found_wrong_ending = true;
        while(found_wrong_ending){
             word = fix_ending(word);
        }
        return word;
    }

    private String remove_tense_suffix(String word) {

        found_a_match = false;

        int length = word.length();
        if(has_min_length(word,4)) {
            String newWord = null;

            if (word.charAt(length - 1) == (char) pulli && word.charAt(length - 2) == (char) ra && word.charAt(length - 3) == (char) vs_i &&
                    word.charAt(length - 4) == (char) tta && word.charAt(length - 5) == (char) pulli && word.charAt(length - 6) == (char) nna &&
                    word.charAt(length - 7) == (char) vs_o && word.charAt(length - 8) == (char) ka) {
                newWord = new StringBuffer(word).delete(length - 8, length).toString();
            } else if (word.charAt(length - 1) == (char) vs_u && word.charAt(length - 2) == (char) tta && word.charAt(length - 3) == (char) pa) {
                newWord = new StringBuffer(word).delete(length - 3, length).toString();
            } else if (word.charAt(length - 1) == (char) nnna && word.charAt(length - 1) == (char) pa && word.charAt(length - 1) == (char) ka &&
                    word.charAt(length - 1) == (char) ta && word.charAt(length - 1) == (char) ya) {
                newWord = new StringBuffer(word).deleteCharAt(length - 1).toString();

            } else if ((word.charAt(length - 1) == (char) vs_ai && word.charAt(length - 2) == (char) nnna) ||
                    (word.charAt(length - 1) == (char) vs_ai && word.charAt(length - 2) == (char) va)) {
                newWord = new StringBuffer(word).delete(length - 2, length).toString();
            } else if ((word.charAt(length - 1) == (char) pulli && word.charAt(length - 2) == (char) nnna && word.charAt(length - 3) == (char) nnna) ||
                    (word.charAt(length - 1) == (char) pulli && word.charAt(length - 2) == (char) lla && word.charAt(length - 3) == (char) nnna) ||
                    (word.charAt(length - 1) == (char) pulli && word.charAt(length - 2) == (char) lla && word.charAt(length - 3) == (char) va) ||
                    (word.charAt(length - 1) == (char) pulli && word.charAt(length - 2) == (char) ra && word.charAt(length - 3) == (char) nnna) ||
                    (word.charAt(length - 1) == (char) pulli && word.charAt(length - 2) == (char) ra && word.charAt(length - 3) == (char) va) ||
                    (word.charAt(length - 1) == (char) pulli && word.charAt(length - 2) == (char) nnna && word.charAt(length - 3) == (char) pa) ||
                    (word.charAt(length - 1) == (char) pulli && word.charAt(length - 2) == (char) lla && word.charAt(length - 3) == (char) pa) ||
                    (word.charAt(length - 1) == (char) pulli && word.charAt(length - 2) == (char) ra && word.charAt(length - 3) == (char) pa) ||
                    (word.charAt(length - 1) == (char) pulli && word.charAt(length - 2) == (char) ma && word.charAt(length - 3) == (char) pa) ||
                    (word.charAt(length - 1) == (char) pulli && word.charAt(length - 2) == (char) ma && word.charAt(length - 3) == (char) nnna)) {
                newWord = new StringBuffer(word).delete(length - 3, length).toString();

            } else if ((word.charAt(length - 1) == (char) pulli && word.charAt(length - 2) == (char) ra && word.charAt(length - 3) == (char) vs_aa &&
                    word.charAt(length - 4) == (char) ma) || (word.charAt(length - 1) == (char) pulli && word.charAt(length - 2) == (char) nnna && word.charAt(length - 3) == (char) vs_i &&
                    word.charAt(length - 4) == (char) ma) || (word.charAt(length - 1) == (char) pulli && word.charAt(length - 2) == (char) nnna && word.charAt(length - 3) == (char) vs_aa &&
                    word.charAt(length - 4) == (char) nnna) || (word.charAt(length - 1) == (char) pulli && word.charAt(length - 2) == (char) lla && word.charAt(length - 3) == (char) vs_aa &&
                    word.charAt(length - 4) == (char) nnna) || (word.charAt(length - 1) == (char) pulli && word.charAt(length - 2) == (char) ra && word.charAt(length - 3) == (char) vs_aa &&
                    word.charAt(length - 4) == (char) nnna) || (word.charAt(length - 1) == (char) pulli && word.charAt(length - 2) == (char) ma && word.charAt(length - 3) == (char) vs_u &&
                    word.charAt(length - 4) == (char) ta) || (word.charAt(length - 1) == (char) pulli && word.charAt(length - 2) == (char) ma && word.charAt(length - 3) == (char) vs_u &&
                    word.charAt(length - 4) == (char) rra) || (word.charAt(length - 1) == (char) pulli && word.charAt(length - 2) == (char) ma && word.charAt(length - 3) == (char) vs_u &&
                    word.charAt(length - 4) == (char) ka) || (word.charAt(length - 1) == (char) pulli && word.charAt(length - 2) == (char) nnna && word.charAt(length - 3) == (char) vs_e &&
                    word.charAt(length - 4) == (char) nnna)) {
                newWord = new StringBuffer(word).delete(length - 4, length).toString();

            } else if (word.charAt(length - 1) == (char) vs_u && word.charAt(length - 2) == (char) rra && word.charAt(length - 3) == (char) pulli && word.charAt(length - 4) == (char) rra &&
                    word.charAt(length - 5) == (char) vs_i) {
                newWord = new StringBuffer(word).delete(length - 5, length).toString();
            } else if (!(word.charAt(length - 1) == (char) vs_aa || word.charAt(length - 1) == (char) vs_i || word.charAt(length - 1) == (char) vs_ii ||
                    word.charAt(length - 1) == (char) vs_e || word.charAt(length - 1) == (char) vs_ee || word.charAt(length - 1) == (char) vs_u ||
                    word.charAt(length - 1) == (char) vs_uu || word.charAt(length - 1) == (char) vs_ai) && word.charAt(length - 2) == (char) vs_u &&
                    word.charAt(length - 3) == (char) ta) {
                newWord = new StringBuffer(word).delete(length - 3, length).toString();

            } else if ((!(word.charAt(length - 1) == (char) a) || !(word.charAt(length - 1) == (char) aa) || !(word.charAt(length - 1) == (char) i) ||
                    !(word.charAt(length - 1) == (char) ii) || !(word.charAt(length - 1) == (char) u) || !(word.charAt(length - 1) == (char) uu) ||
                    !(word.charAt(length - 1) == (char) e) || !(word.charAt(length - 1) == (char) ee) || !(word.charAt(length - 1) == (char) ai) ||
                    !(word.charAt(length - 1) == (char) o) || !(word.charAt(length - 1) == (char) oo) || !(word.charAt(length - 1) == (char) au))
                    && word.charAt(length - 2) == (char) pulli && word.charAt(length - 3) == (char) nnna && word.charAt(length - 4) == (char) va) {
                newWord = new StringBuffer(word).delete(length - 4, length).toString();

            } else if (word.charAt(length - 1) == (char) vs_aa) {
                newWord = word.replace((char) vs_aa, (char) pulli);
            } else if ((word.charAt(length - 1) == (char) pulli && word.charAt(length - 2) == (char) lla && word.charAt(length - 3) == (char) vs_aa) ||
                    (word.charAt(length - 1) == (char) pulli && word.charAt(length - 2) == (char) ra && word.charAt(length - 3) == (char) vs_aa) ||
                    (word.charAt(length - 1) == (char) pulli && word.charAt(length - 2) == (char) nnna && word.charAt(length - 3) == (char) vs_ee) ||
                    (word.charAt(length - 1) == (char) pulli && word.charAt(length - 2) == (char) ma && word.charAt(length - 3) == (char) vs_aa) ||
                    (word.charAt(length - 1) == (char) pulli && word.charAt(length - 2) == (char) ma && word.charAt(length - 3) == (char) vs_e) ||
                    (word.charAt(length - 1) == (char) pulli && word.charAt(length - 2) == (char) ma && word.charAt(length - 3) == (char) vs_ee) ||
                    (word.charAt(length - 1) == (char) pulli && word.charAt(length - 2) == (char) ma && word.charAt(length - 3) == (char) vs_oo) ||
                    (word.charAt(length - 1) == (char) pulli && word.charAt(length - 2) == (char) ya && word.charAt(length - 3) == (char) vs_aa) ||
                    (word.charAt(length - 1) == (char) pulli && word.charAt(length - 2) == (char) ra && word.charAt(length - 3) == (char) vs_ii)) {
                newWord = new StringBuffer(word).replace(length - 3, length, Character.toString((char) pulli)).toString();

            } else if ((word.charAt(length - 1) == (char) pulli && word.charAt(length - 2) == (char) ma && word.charAt(length - 3) == (char) vs_u && word.charAt(length - 4) == (char) ka) ||
                    (word.charAt(length - 1) == (char) pulli && word.charAt(length - 2) == (char) ma && word.charAt(length - 3) == (char) vs_u && word.charAt(length - 4) == (char) ta) ||
                    (word.charAt(length - 1) == (char) pulli && word.charAt(length - 2) == (char) ma && word.charAt(length - 3) == (char) vs_u && word.charAt(length - 4) == (char) tta) ||
                    (word.charAt(length - 1) == (char) pulli && word.charAt(length - 2) == (char) ma && word.charAt(length - 3) == (char) vs_u && word.charAt(length - 4) == (char) rra) ||
                    (word.charAt(length - 1) == (char) pulli && word.charAt(length - 2) == (char) nnna && word.charAt(length - 3) == (char) vs_e && word.charAt(length - 4) == (char) nnna) ||
                    (word.charAt(length - 1) == (char) pulli && word.charAt(length - 2) == (char) ra && word.charAt(length - 3) == (char) vs_i && word.charAt(length - 4) == (char) nnna) ||
                    (word.charAt(length - 1) == (char) pulli && word.charAt(length - 2) == (char) ra && word.charAt(length - 3) == (char) ya && word.charAt(length - 4) == (char) vs_ii)) {
                newWord = new StringBuffer(word).replace(length - 4, length, Character.toString((char) pulli)).toString();

            } else if (word.charAt(length - 2) == (char) pulli && word.charAt(length - 3) == (char) nnna && word.charAt(length - 4) == (char) vs_aa && !(word.charAt(length - 1) == (char) ca)) {
                newWord = new StringBuffer(word).replace(length - 4, length, Character.toString((char) pulli)).toString();
            } else if (word.charAt(length - 1) == (char) pulli && ((word.charAt(length - 2) == (char) vs_u && word.charAt(length - 3) == (char) ka) ||
                    (word.charAt(length - 2) == (char) vs_u && word.charAt(length - 3) == (char) ta))) {
                newWord = new StringBuffer(word).delete(length - 3, length).toString();
            }

            if (newWord != null) {
                if (!(word.equalsIgnoreCase(newWord))) {
                    found_a_match = true;
                    word = newWord;
                }
            }

            if (word.charAt(length - 1) == (char) rra && word.charAt(length - 2) == (char) vs_i && word.charAt(length - 3) == (char) ka) {
                newWord = new StringBuffer(word).delete(length - 3, length).toString();
            } else if (word.charAt(length - 1) == (char) pulli && word.charAt(length - 2) == (char) rra && word.charAt(length - 3) == (char) vs_i && word.charAt(length - 4) == (char) ka) {
                newWord = new StringBuffer(word).delete(length - 4, length).toString();
            } else if (word.charAt(length - 1) == (char) rra && word.charAt(length - 2) == (char) pulli && word.charAt(length - 3) == (char) nnna && word.charAt(length - 4) == (char) vs_i &&
                    word.charAt(length - 5) == (char) ka) {
                newWord = new StringBuffer(word).delete(length - 5, length).toString();
            } else if ((word.charAt(length - 1) == (char) rra && word.charAt(length - 2) == (char) pulli && word.charAt(length - 3) == (char) nnna && word.charAt(length - 4) == (char) vs_i &&
                    word.charAt(length - 5) == (char) na && word.charAt(length - 6) == (char) vs_aa) || (word.charAt(length - 1) == (char) pulli && word.charAt(length - 2) == (char) rra &&
                    word.charAt(length - 3) == (char) pulli && word.charAt(length - 4) == (char) nnna && word.charAt(length - 5) == (char) vs_i && word.charAt(length - 6) == (char) ka)) {
                newWord = new StringBuffer(word).delete(length - 6, length).toString();
            } else if (word.charAt(length - 1) == (char) pulli && word.charAt(length - 2) == (char) rra && word.charAt(length - 3) == (char) pulli && word.charAt(length - 4) == (char) nnna &&
                    word.charAt(length - 5) == (char) vs_i && word.charAt(length - 6) == (char) na && word.charAt(length - 7) == (char) vs_aa) {
                newWord = new StringBuffer(word).delete(length - 7, length).toString();
            }

            if (newWord != null) {
                if (!(word.equalsIgnoreCase(newWord))) {
                    found_a_match = true;
                    word = newWord;
                }
            }
        }

        return fix_endings(word);
    }
    private String remove_tense_suffixes(String word) {

        found_a_match = true;

        while(found_a_match){
            word = remove_tense_suffix(word);
        }
        return word;
    }

    private String remove_common_word_endings(String word) {
        found_a_match = false;
        int length = word.length();
        if(has_min_length(word,4)) {

            if ((word.charAt(length - 1) == (char) pulli && word.charAt(length - 2) == (char) nnna && word.charAt(length - 3) == (char) tta && word.charAt(length - 4) == (char) vs_u) ||
                    (word.charAt(length - 1) == (char) pulli && word.charAt(length - 2) == (char) ma && word.charAt(length - 3) == (char) tta && word.charAt(length - 4) == (char) vs_i) ||
                    (word.charAt(length - 1) == (char) ya && word.charAt(length - 2) == (char) vs_i && word.charAt(length - 3) == (char) ka && word.charAt(length - 4) == (char) vs_aa) ||
                    (word.charAt(length - 1) == (char) lla && word.charAt(length - 2) == (char) pulli && word.charAt(length - 3) == (char) lla && word.charAt(length - 4) == (char) vs_u) ||
                    (word.charAt(length - 1) == (char) ya && word.charAt(length - 2) == (char) vs_ai && word.charAt(length - 3) == (char) tta && word.charAt(length - 4) == (char) vs_u) ||
                    (word.charAt(length - 1) == (char) tta && word.charAt(length - 2) == (char) pulli && word.charAt(length - 3) == (char) tta && word.charAt(length - 4) == (char) pa) ||
                    (word.charAt(length - 1) == (char) vs_u && word.charAt(length - 2) == (char) tta && word.charAt(length - 3) == (char) vs_i && word.charAt(length - 4) == (char) va)) {
                word = new StringBuffer(word).replace(length - 4, length, Character.toString((char) pulli)).toString();

            } else if ((word.charAt(length - 1) == (char) vs_ai && word.charAt(length - 2) == (char) la && word.charAt(length - 3) == (char) pulli && word.charAt(length - 4) == (char) la &&
                    word.charAt(length - 5) == (char) vs_i) || (word.charAt(length - 1) == (char) vs_i && word.charAt(length - 2) == (char) rra && word.charAt(length - 3) == (char) pulli && word.charAt(length - 4) == (char) nnna &&
                    word.charAt(length - 5) == (char) vs_i) || (word.charAt(length - 1) == (char) vs_u && word.charAt(length - 2) == (char) rra && word.charAt(length - 3) == (char) pulli && word.charAt(length - 4) == (char) nnna &&
                    word.charAt(length - 5) == (char) vs_e) || (word.charAt(length - 1) == (char) pulli && word.charAt(length - 2) == (char) ma && word.charAt(length - 3) == (char) vs_u && word.charAt(length - 4) == (char) nnna &&
                    word.charAt(length - 5) == (char) vs_e) || (word.charAt(length - 1) == (char) vs_u && word.charAt(length - 2) == (char) tta && word.charAt(length - 3) == (char) pulli && word.charAt(length - 4) == (char) tta &&
                    word.charAt(length - 5) == (char) pa) || (word.charAt(length - 1) == (char) nna && word.charAt(length - 2) == (char) tta && word.charAt(length - 3) == (char) pulli && word.charAt(length - 4) == (char) tta &&
                    word.charAt(length - 5) == (char) pa) || (word.charAt(length - 1) == (char) ya && word.charAt(length - 2) == (char) vs_i && word.charAt(length - 3) == (char) ra && word.charAt(length - 4) == (char) vs_u &&
                    word.charAt(length - 5) == (char) ka) || (word.charAt(length - 1) == (char) ya && word.charAt(length - 2) == (char) vs_i && word.charAt(length - 3) == (char) ra && word.charAt(length - 4) == (char) vs_u &&
                    word.charAt(length - 5) == (char) ka) || (word.charAt(length - 1) == (char) vs_u && word.charAt(length - 2) == (char) rra && word.charAt(length - 3) == (char) pulli && word.charAt(length - 4) == (char) rra &&
                    word.charAt(length - 5) == (char) pa)) {
                word = new StringBuffer(word).replace(length - 5, length, Character.toString((char) pulli)).toString();

            } else if ((word.charAt(length - 1) == (char) vs_u && word.charAt(length - 2) == (char) ta && word.charAt(length - 3) == (char) tta && word.charAt(length - 4) == (char) pulli &&
                    word.charAt(length - 5) == (char) tta && word.charAt(length - 6) == (char) pa) || (word.charAt(length - 1) == (char) vs_u && word.charAt(length - 2) == (char) tta &&
                    word.charAt(length - 3) == (char) pulli && word.charAt(length - 4) == (char) tta && word.charAt(length - 5) == (char) vs_i && word.charAt(length - 6) == (char) va) ||
                    (word.charAt(length - 1) == (char) nnna && word.charAt(length - 2) == (char) vs_aa && word.charAt(length - 3) == (char) ta && word.charAt(length - 4) == (char) vs_i &&
                            word.charAt(length - 5) == (char) tta && word.charAt(length - 6) == (char) pa)) {
                word = new StringBuffer(word).delete(length - 6, length).toString();

            } else if (word.charAt(length - 1) == (char) pulli && word.charAt(length - 2) == (char) ma && word.charAt(length - 3) == (char) vs_aa && word.charAt(length - 4) == (char) la &&
                    word.charAt(length - 5) == (char) pulli && word.charAt(length - 6) == (char) la && word.charAt(length - 7) == (char) vs_e) {
                word = new StringBuffer(word).delete(length - 7, length).toString();
            } else if ((word.charAt(length - 1) == (char) vs_i && word.charAt(length - 2) == (char) ka && word.charAt(length - 3) == (char) vs_aa) ||
                    (word.charAt(length - 1) == (char) vs_ai && word.charAt(length - 2) == (char) tta && word.charAt(length - 3) == (char) vs_u) ||
                    (word.charAt(length - 1) == (char) vs_u && word.charAt(length - 2) == (char) tta && word.charAt(length - 3) == (char) pa) ||
                    (word.charAt(length - 1) == (char) vs_i && word.charAt(length - 2) == (char) tta && word.charAt(length - 3) == (char) pa) ||
                    (word.charAt(length - 1) == (char) nnna && word.charAt(length - 2) == (char) vs_aa && word.charAt(length - 3) == (char) ta)) {
                word = new StringBuffer(word).replace(length - 3, length, Character.toString((char) pulli)).toString();

            } else if (word.charAt(length - 1) == (char) nnna && word.charAt(length - 2) == (char) vs_e) {
                word = new StringBuffer(word).replace(length - 2, length, Character.toString((char) pulli)).toString();

            } else if ((!(word.charAt(length - 1) == (char) vs_aa) || !(word.charAt(length - 1) == (char) vs_i) || !(word.charAt(length - 1) == (char) vs_ii) ||
                    !(word.charAt(length - 1) == (char) vs_e) || !(word.charAt(length - 1) == (char) vs_ee) || !(word.charAt(length - 1) == (char) vs_u) ||
                    !(word.charAt(length - 1) == (char) vs_uu) || !(word.charAt(length - 1) == (char) vs_ai)) && word.charAt(length - 2) == la && word.charAt(length - 3) == pulli && word.charAt(length - 4) == la) {
                word = new StringBuffer(word).replace(length - 4, length, Character.toString((char) pulli)).toString();

            }
        }

        found_a_match = true;
        return fix_endings(word);
    }
}
