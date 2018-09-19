package com.controller.suspect;

import com.common.utils.StringHelp;
import com.entity.sys.SysUser;

/**
 * Created by Auri on 2018/1/16.
 * Desc:
 */
public class BaseSHandler {
    /**
     * 依据系统用户 识别分域编码字符 例如：广东省=44；深证市=4403；罗湖区=440303
     *
     * @param user
     * @return
     */
    protected String identifyRegionCode(SysUser user) {
        // 识别分域编码字符
        String lvStr = user.getLevel();
        if (StringHelp.isEmpty(lvStr)) {
            return null;
        }
        int lv = Integer.parseInt(String.valueOf(user.getLevel()));
        String regionCodeStr;
        switch (lv) {
            case 1: {
                regionCodeStr = subCode(2, user.getProvince());
                break;
            }
            case 2: {
                regionCodeStr = subCode(4, user.getCity());
                break;
            }
            case 3: {
                regionCodeStr = subCode(6, user.getArea());
                break;
            }
            case 4: {
                regionCodeStr = subCode(9, user.getPoliceStation());
                break;
            }
            default: {
                regionCodeStr = subCode(6, user.getArea());
            }
        }
        return regionCodeStr;
    }

    private String subCode(int endIndex, String srcStr) {
        String str;
        try {
            str = srcStr.substring(0, endIndex);
        } catch (Exception e) {
            str = null;
        }
        return str;
    }
}
