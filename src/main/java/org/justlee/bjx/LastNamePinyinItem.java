package org.justlee.bjx;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;

public class LastNamePinyinItem {
    @ColumnWidth(20)
    @ExcelProperty("姓氏")
    private String lastName;

    @ColumnWidth(20)
    @ExcelProperty("首字母")
    private String firstAlphabet;

    @ColumnWidth(20)
    @ExcelProperty("正确拼音")
    private String rightPinyin;

    @ColumnWidth(50)
    @ExcelProperty("全部拼音")
    private String allPinyin;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstAlphabet() {
        return firstAlphabet;
    }

    public void setFirstAlphabet(String firstAlphabet) {
        this.firstAlphabet = firstAlphabet;
    }

    public String getRightPinyin() {
        return rightPinyin;
    }

    public void setRightPinyin(String rightPinyin) {
        this.rightPinyin = rightPinyin;
    }

    public String getAllPinyin() {
        return allPinyin;
    }

    public void setAllPinyin(String allPinyin) {
        this.allPinyin = allPinyin;
    }
}