package evaluex;

/**
 * Created by Anu on 2016-08-30.
 */
public class TamilCharacters {

    public final static int aytham = 0x0B83;

    /* Uyir - independent vowels */
    public final static int a = 0x0B85;
    public final static int aa = 0x0B86;
    public final static int i = 0x0B87;
    public final static int ii = 0x0B88;
    public final static int u = 0x0B89;
    public final static int uu = 0x0B8A;
    public final static int e = 0x0B8E;
    public final static int ee = 0x0B8F;
    public final static int ai = 0x0B90;
    public final static int o = 0x0B92;
    public final static int oo = 0x0B93;
    public final static int au = 0x0B94;

    /* Constants */
    public final static int ka = 0x0B95;
    public final static int nga = 0x0B99;
    public final static int ca = 0x0B9A;
    public final static int ja = 0x0B9C;
    public final static int nya = 0x0B9E;
    public final static int tta = 0x0B9F;
    public final static int nna = 0x0BA3;
    public final static int ta = 0x0BA4;
    public final static int tha = 0x0BA4;
    public final static int na = 0x0BA8;
    public final static int nnna = 0x0BA9;
    public final static int pa = 0x0BAA;
    public final static int ma = 0x0BAE;
    public final static int ya = 0x0BAF;
    public final static int ra = 0x0BB0;
    public final static int rra = 0x0BB1;
    public final static int la = 0x0BB2;
    public final static int lla = 0x0BB3;
    public final static int llla = 0x0BB4;
    public final static int zha = 0x0BB4;
    public final static int va = 0x0BB5;

    /* Vatamozi - borrowed */
    public final static int sha = 0x0BB6;
    public final static int ssa = 0x0BB7;
    public final static int sa = 0x0BB8;
    public final static int ha = 0x0BB9;


    /* Dependent vowel signs (kombu etc.) */
    public final static int vs_aa = 0x0BBE;     //ா
    public final static int vs_i = 0x0BBF;      //ி
    public final static int vs_ii = 0x0BC0;     //ீ
    public final static int vs_u = 0x0BC1;      //ி
    public final static int vs_uu = 0x0BC2;     //ு
    public final static int vs_e = 0x0BC6;      //ெ
    public final static int vs_ee = 0x0BC7;     //ே
    public final static int vs_ai = 0x0BC8;     //ை
    public final static int vs_o = 0x0BCA;      //ொ
    public final static int vs_oo = 0x0BCB;     //ோ
    public final static int vs_au = 0x0BCC;     //ௌ

    /* Pulli */
    public final static int pulli = 0x0BCD;     //்

    /* AU length markk */
    public final static int au_lmark = 0x0BD7;

    /* tamil currency symbol*/
    public final static int currency = 0x0BF9;

    public enum consonants {ka,nga,ca,nya,tta,nna,ta,na,pa,ma,ya,ra,la,va,llla,lla,rra,nnna,ja,sa,ssa,ha}

    public final static int[] q_suffixes = {vs_aa, vs_oo, vs_ee};
    public final static int[] q_prefixes = {e}; /* not clear about {ya}{vs_aa} and {ee} */
    public final static int[] word_starter = {ka, ca, tha, va, na, pa, ma, ya, nga, nya};
    public final static int[] suttezhuthu = {a, i, u};
    public final static int[] vallinam = {ka, ca, tta, tha, pa, rra};
    public final static int[] mellinam = {nga, nya, nna, na, ma, nnna};
    public final static int[] itaiyinam = {ya, ra, la, va, zha, lla};
    public final static int[] vowel_signs = {vs_aa, vs_i, vs_ii, vs_e, vs_ee, vs_u, vs_uu, vs_ai};
    public final static int[] uyir = {a, aa, i, ii, u, uu, e, ee, ai, o, oo, au};

}
