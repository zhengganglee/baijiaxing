package org.justlee.bjx;

import com.alibaba.excel.EasyExcel;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 生成项目文件
 */
public class ProjectGen {

    public static void main(String[] args) throws Exception {
        String pathname = "/Users/zhengganglee/work/myproject/baijiaxing/README.md";
        writeReadme(PinyinUtils.lastNamePinyinList, pathname);

//        InputStream inputStream = PinyinUtils.class.getClassLoader().getResourceAsStream("baijiaxing_pinyin.xlsx");
//        List<LastNamePinyinItem> rowList = EasyExcel.read(file1, LastNamePinyinItem.class, null).sheet().doReadSync();

        for (LastNamePinyinItem item : PinyinUtils.lastNamePinyinList) {
            item.setFirstAlphabet(item.getRightPinyin().substring(0, 1));
            item.setAllPinyin(allPinyin(item.getLastName()));
        }
        FileOutputStream outputStream = new FileOutputStream("/Users/zhengganglee/work/myproject/baijiaxing/百家姓拼音表格.xlsx");
        EasyExcel.write(outputStream, LastNamePinyinItem.class).sheet("百家姓拼音表格").doWrite(PinyinUtils.lastNamePinyinList);

    }

    private static void writeReadme(List<LastNamePinyinItem> rowList, String pathname) throws Exception {
        List<String> lines = new ArrayList<>();
        lines.add("# baijiaxing");
        lines.add("百家姓正确的拼音、姓名首字母");
        lines.add("|  姓氏  |  首字母  |  正确拼音  |  全部拼音  |");
        lines.add("|  ----  |  ----  |  ----  |  ----  |");
        for (LastNamePinyinItem item : rowList) {
            String allPinyin = allPinyin(item.getLastName());
            lines.add(String.format("|  %s  |  %s  |  %s  |  %s  |", item.getLastName(), item.getRightPinyin().substring(0, 1), item.getRightPinyin(), allPinyin));
        }
        FileUtils.writeLines(new File(pathname), lines);
    }

    private static String allPinyin(String lastName) throws BadHanyuPinyinOutputFormatCombination {
        return String.join(",", PinyinUtils.toPinyin(lastName.charAt(0)));
    }

}
