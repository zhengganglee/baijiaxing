package org.justlee.bjx;

import com.alibaba.excel.EasyExcel;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 百家姓拼音
 */
public class PinyinUtils {


    public static void main(String[] args) throws Exception {
        System.out.println(nameFirstAlphabet("李世民"));
        System.out.println(nameFirstAlphabet("查理斯"));
        System.out.println(nameFirstAlphabet("单华雄"));
    }

    public static final List<LastNamePinyinItem> lastNamePinyinList;
    public static final Map<String, String> pinyinByLastName;

    static {
        try {
            InputStream inputStream = PinyinUtils.class.getClassLoader().getResourceAsStream("baijiaxing_pinyin.xlsx");
            lastNamePinyinList = EasyExcel.read(inputStream, LastNamePinyinItem.class, null).sheet().doReadSync();
            pinyinByLastName = lastNamePinyinList.stream().collect(Collectors.toMap(LastNamePinyinItem::getLastName, LastNamePinyinItem::getRightPinyin));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String nameFirstAlphabet(String name) {
        if (name == null || name.trim().isEmpty()) {
            return "#";
        }
        String pinyin = pinyinByLastName.get(name.substring(0, 1));
        System.out.println("pinyin = " + pinyin);
        return pinyin.substring(0, 1);
    }

    public static String[] toPinyin(char firstName) throws BadHanyuPinyinOutputFormatCombination {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();

        // UPPERCASE：大写  (ZHONG)
        // LOWERCASE：小写  (zhong)
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);//输出大写

        // WITHOUT_TONE：无音标  (zhong)
        // WITH_TONE_NUMBER：1-4数字表示音标  (zhong4)
        // WITH_TONE_MARK：直接用音标符（必须WITH_U_UNICODE否则异常）  (zhòng)
        format.setToneType(HanyuPinyinToneType.WITH_TONE_MARK);

        // WITH_V：用v表示ü  (nv)
        // WITH_U_AND_COLON：用"u:"表示ü  (nu:)
        // WITH_U_UNICODE：直接用ü (nü)
        format.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);

        return PinyinHelper.toHanyuPinyinStringArray(firstName, format);
    }

}
