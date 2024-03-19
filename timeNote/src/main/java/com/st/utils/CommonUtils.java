package com.st.utils;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: song
 * @Package: com.st.utils
 * @Project: timeNote
 * @name: CommonUtils
 * @Filename: CommonUtils
 * @Date: 2023-04-16 14:26
 */
public class CommonUtils {

    // 判断是否在双体签到
    public static boolean isAllowed(String ip){
        String regex = "^59.48.111.";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(ip);
        return matcher.find();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        System.out.println(isAllowed(s));
    }
}
