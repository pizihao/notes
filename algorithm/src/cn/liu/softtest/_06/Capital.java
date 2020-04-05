package cn.liu.softtest._06;

/**
 * @author shidacaizi
 * @date 2020/4/4 10:16
 */

public class Capital {
    private final String[] pattern = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
    private final String[] cPattern = {"", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿"};
    private final String[] cfPattern = {"", "角", "分"};
    private final String ZEOR = "零";

    public Capital() {
    }

    public String format(String moneyString) {
        int dotPoint = moneyString.indexOf(".");
        String moneyStr;
        if (dotPoint != -1) {
            moneyStr = moneyString.substring(0, moneyString.indexOf("."));
        } else {
            moneyStr = moneyString;
        }
        StringBuffer fraction = null;
        StringBuffer ms = new StringBuffer();
        for (int i = 0; i < moneyStr.length(); i++) {
            ms.append(pattern[moneyStr.charAt(i) - 48]);
        }
        int cpCursor = 1;
        for (int j = moneyStr.length() - 1; j > 0; j--) {
            ms.insert(j, cPattern[cpCursor]);
            cpCursor = cpCursor == 8 ? 1 : cpCursor + 1;
        }
        while (ms.indexOf("零拾") != -1) {
            ms.replace(ms.indexOf("零拾"), ms.indexOf("零拾") + 2, ZEOR);
        }
        while (ms.indexOf("零佰") != -1) {
            ms.replace(ms.indexOf("零佰"), ms.indexOf("零佰") + 2, ZEOR);
        }
        while (ms.indexOf("零仟") != -1) {
            ms.replace(ms.indexOf("零仟"), ms.indexOf("零仟") + 2, ZEOR);
        }
        while (ms.indexOf("零万") != -1) {
            ms.replace(ms.indexOf("零万"), ms.indexOf("零万") + 2, "万");
        }
        while (ms.indexOf("零亿") != -1) {
            ms.replace(ms.indexOf("零亿"), ms.indexOf("零亿") + 2, "亿");
        }
        while (ms.indexOf("零零") != -1) {
            ms.replace(ms.indexOf("零零"), ms.indexOf("零零") + 2, ZEOR);
        }
        while (ms.indexOf("亿万") != -1) {
            ms.replace(ms.indexOf("亿万"), ms.indexOf("亿万") + 2, "亿");
        }
        while (ms.lastIndexOf("零") == ms.length() - 1) {
            ms.delete(ms.lastIndexOf("零"), ms.lastIndexOf("零") + 1);
        }
        int end;
        if ((dotPoint = moneyString.indexOf(".")) != -1) {
            String fs = moneyString.substring(dotPoint + 1);
            if (fs.indexOf("00") == -1 || fs.indexOf("00") >= 2) {
                end = fs.length() > 2 ? 2 : fs.length();
                fraction = new StringBuffer(fs.substring(0, end));
                for (int j = 0; j < fraction.length(); j++) {
                    fraction.replace(j, j + 1, this.pattern[fraction.charAt(j) - 48]);
                }
                for (int i = fraction.length(); i > 0; i--) {
                    fraction.insert(i, cfPattern[i]);
                }
                fraction.insert(0, "元");
            } else {
                fraction = new StringBuffer("元整");
            }
        } else {
            fraction = new StringBuffer("元整");
        }
        ms.append(fraction);
        return ms.toString();
    }
}
