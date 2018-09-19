package com.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by weipc on 2018/2/6.
 */
public class JzMapUtil {

    private static Map<String, String> caseClass = new HashMap<>();
    private static Map<String, String> caseState = new HashMap<>();
    private static Map<String, String> gender = new HashMap<>();

    static {
        //案件类别
        caseClass.put("030807", "1900");//强迫交易案
        caseClass.put("050800", "1901");//敲诈勒索案
        caseClass.put("060137", "1902");//寻衅滋事案
        caseClass.put("060136", "1903");//聚众斗殴案
        caseClass.put("040109", "1904");//非法拘禁案
        caseClass.put("050900", "1905");//故意毁坏财物案
        caseClass.put("060801", "1906");//组织卖淫案
        caseClass.put("060810", "1907");//强迫卖淫案
        caseClass.put("060147", "1908");//开设赌场案
        caseClass.put("040103", "1909");//故意伤害案
        caseClass.put("051000", "1910");//破坏生产经营案
        caseClass.put("060132", "1911");//聚众扰乱社会秩序案
        caseClass.put("323202", "1912");//聚众扰乱公共场所秩序
        caseClass.put("3B0001", "1913");//聚众赌博，尚不构成犯罪
        caseClass.put("020511,310303", "1914");//非法持有枪支弹药案,非法携带管制刀具
        caseClass.put("372101,372103,372099", "1915");//非法持有毒品,吸毒,非法获得/携带/提供/使用毒品

        //案件状态
        caseState.put("00", "1800");//已处警
        caseState.put("01", "1801");//已受理
        caseState.put("02", "1802");//已立案
        caseState.put("03", "1803");//已破案
        caseState.put("04", "1804");//已结案
        caseState.put("05", "1805");//已销案
        caseState.put("06", "1806");//已不立
        caseState.put("07", "1807");//已移交
        caseState.put("08", "1808");//已破未结
        caseState.put("09", "1809");//撤案转行政处罚
        caseState.put("20", "1820");//审查中
        caseState.put("21", "1821");//已审查
        caseState.put("50", "1850");//不处理
        caseState.put("51", "1851");//已调解
        caseState.put("52", "1852");//已终止
        caseState.put("59", "1859");//已终结
        caseState.put("60", "1860");//已处罚
        caseState.put("61", "1861");//已受理未结
        caseState.put("62", "1862");//当场处罚
        caseState.put("63", "1863");//现场调解
        caseState.put("64", "1864");//不予处罚
        caseState.put("99", "1899");//其他

        // 人员性别
        gender.put("1", "1200");// 女
        gender.put("2", "1201");// 男
        gender.put("0", "1202");// 其他 未知性别
        gender.put("3", "1202");// 女性改为男性
        gender.put("4", "1202");// 男性改为女性
        gender.put("9", "1202");// 未说明的性别
    }

    public static String convertMap(String parentkey, String key) {
        String value = "";
        Map<String, String> map = null;
        if ("12".equals(parentkey)) {
            map = gender;
        } else if ("18".equals(parentkey)) {
            map = caseState;
        } else if ("19".equals(parentkey)) {
            map = caseClass;
        } else {
            return value;
        }
        for (String keys : map.keySet()) {
            if (keys.equals(key)) {
                value = map.get(keys);
            }
        }
        return value;
    }

    public static void main(String[] args) {
        String s1 = convertMap("18", "99");
        String s2 = convertMap("18", "01");
        System.out.println(s1);
        System.out.println(s2);
    }
}
