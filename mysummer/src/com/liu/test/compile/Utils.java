package com.liu.test.compile;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shidacaizi
 * @date 2020/5/8 22:29
 */
public class Utils {

    static String yaong(String strings) {
        List<String> list = new ArrayList<>();
        String[] split = strings.split(" ");
        for (String value : split) {
            if (!"".equals(value)) {
                list.add(value.trim());
            }
        }
        StringBuilder stringBuilder = new StringBuilder("#");
        list.forEach(s -> stringBuilder.append(s).append("#"));
        return stringBuilder.toString();
    }

    /**
     * 去掉空行
     */
    static String delKongHang(String str) {
        if (!str.trim().equals("")) {
            return str;
        } else {
            return "";
        }
    }

    /**
     * 去空
     */
    static String delKong(String str) {
        String target = str.replaceAll("[^a-zA-Z0-9]", " $0 ");
        return Utils.yaong(target);
    }

    /**
     * 去掉行后注释
     * //和/*和<!--
     */
    static String delHangHouZhuShi(String str) {
        if (str.contains("//")) {
            return str.substring(0, str.indexOf("//"));
        } else if (str.contains("/*")) {
            return str.substring(0, str.indexOf("/*"));
        } else if (str.contains("*")) {
            return str.substring(0, str.indexOf("*"));
        } else {
            return str;
        }
    }

    static String read(String file) {
        FileInputStream fis;
        InputStreamReader isr;
        BufferedReader raf = null;
        String tempString = null;
        try {
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis);
            raf = new BufferedReader(isr);
            String str;
            StringBuilder s = new StringBuilder();
            while ((str = raf.readLine()) != null) {
                tempString = str;
                tempString = Utils.delKongHang(tempString);
                tempString = Utils.delHangHouZhuShi(tempString);
                s.append(tempString);
            }
            tempString = Utils.delKong(s.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                assert raf != null;
                raf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return tempString;
    }
}
