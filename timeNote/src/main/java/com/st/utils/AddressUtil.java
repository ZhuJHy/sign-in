package com.st.utils;

import org.lionsoul.ip2region.xdb.Searcher;

public class AddressUtil {


    /**
     * 根据IP地址查询登录来源
     *
     * @param ip
     * @return
     */
    public static String getCityInfo(String ip) {
        String path = "src/main/resources/ip2region/ip2region.xdb";
        try {
            Searcher searcher = Searcher.newWithFileOnly(path);
            //开始查询
            return searcher.searchByStr(ip);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //默认返回空字符串
        return "";
    }

    public static void main(String[] args) {
        //204.16.111.255
        System.out.println(getCityInfo("101.43.172.228"));
    }
}