package com.controller.suspect;

import com.common.utils.StringHelp;
import com.entity.sys.SysUser;
import net.sf.json.JSONObject;

public class BaseControlller {

    protected JSONObject buildSuccessMsg(String msgStr) {
        JSONObject result = new JSONObject();
        result.put("flag", true);
        result.put("msg", msgStr);
        return result;
    }

    protected JSONObject buildFailMsg(String msgStr) {
        JSONObject result = new JSONObject();
        result.put("flag", false);
        result.put("msg", msgStr);
        return result;
    }
}
