import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * Date: 2021/9/23
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class PinyinTest {


    public static void main(String[] args) throws BadHanyuPinyinOutputFormatCombination {
        //1.创建一个格式化输出对象
        HanyuPinyinOutputFormat outputF = new HanyuPinyinOutputFormat();
        //2.设置好格式
        outputF.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        outputF.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        char ch = '间';
        String zifu = "振宇";
        String str[] = PinyinHelper.toHanyuPinyinStringArray(ch, outputF);
        //3.打印输出
        System.out.println(Arrays.toString(str));
        //结果为:[jian, jian],因为间有多音,但是我们选择的音调类型是WITHOUT_TONE,所以拼音数组后面也没有对应音调数字

        Set<String> result = getPinyin(zifu, false);
        System.out.println(result);
    }

    /**
     * getPinyin:(获取汉字拼音). <br/>
     *
     * @param src 汉字
     * @param isFullSpell 是否全拼,如果为true：全拼，false:首字全拼
     */
    public static Set<String> getPinyin(String src, boolean isFullSpell) {
        if (src != null && !src.trim().equalsIgnoreCase("")) {
            char[] srcChar;
            srcChar = src.toCharArray();
            // 汉语拼音格式输出类
            HanyuPinyinOutputFormat hanYuPinOutputFormat = new HanyuPinyinOutputFormat();

            // 输出设置，大小写，音标方式等
            hanYuPinOutputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
            hanYuPinOutputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
            hanYuPinOutputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);

            String[][] temp = new String[src.length()][];
            for (int i = 0; i < srcChar.length; i++) {
                char c = srcChar[i];
                if (String.valueOf(c).matches("[\\u4E00-\\u9FA5]+")) {//中文
                    try {
                        temp[i] = PinyinHelper.toHanyuPinyinStringArray(
                                srcChar[i], hanYuPinOutputFormat);
                        if (!isFullSpell) {
                            if (i == 0) {
                                temp[i] = temp[i];
                            } else {
                                String[] tTemps = new String[temp[i].length];
                                for (int j = 0; j < temp[i].length; j++) {
                                    char t = temp[i][j].charAt(0);
                                    tTemps[j] = Character.toString(t);
                                }
                                temp[i] = tTemps;
                            }
                        }
                    } catch (BadHanyuPinyinOutputFormatCombination e) {
                        e.printStackTrace();
                    }
                } else if (((int) c >= 65 && (int) c <= 90)
                        || ((int) c >= 97 && (int) c <= 122)) {//英文
                    temp[i] = new String[]{String.valueOf(srcChar[i])};
                } else {
                    temp[i] = new String[]{""};
                }
            }
            String[] pingyinArray = exchange(temp);
            Set<String> pinyinSet = new HashSet<String>();
            for (int i = 0; i < pingyinArray.length; i++) {
                pinyinSet.add(pingyinArray[i]);
            }
            return pinyinSet;
        }
        return null;
    }

    /**
     * 递归
     */
    public static String[] exchange(String[][] strJaggedArray) {
        String[][] temp = doExchange(strJaggedArray);
        return temp[0];
    }

    /**
     * 递归
     */
    private static String[][] doExchange(String[][] strJaggedArray) {
        int len = strJaggedArray.length;
        if (len >= 2) {
            int len1 = strJaggedArray[0].length;
            int len2 = strJaggedArray[1].length;
            int newlen = len1 * len2;
            String[] temp = new String[newlen];
            int Index = 0;
            for (int i = 0; i < len1; i++) {
                for (int j = 0; j < len2; j++) {
                    temp[Index] = strJaggedArray[0][i] + strJaggedArray[1][j];
                    Index++;
                }
            }
            String[][] newArray = new String[len - 1][];
            for (int i = 2; i < len; i++) {
                newArray[i - 1] = strJaggedArray[i];
            }
            newArray[0] = temp;
            return doExchange(newArray);
        } else {
            return strJaggedArray;
        }
    }

}