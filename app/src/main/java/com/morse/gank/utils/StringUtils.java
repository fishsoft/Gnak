package com.morse.gank.utils;

/**
 * 作者：Morse on 2015/11/11 13:47
 * 邮箱：zm902485jgsurjgc@163.com
 */
public class StringUtils {
    public static String parseTime(String string){
        if (string.contains("T"))
            string = string.replaceAll("T"," ");
        if (string.contains("Z"))
            string = string.replaceAll("Z","");
        return string.substring(0,string.indexOf("."));
    }
}
