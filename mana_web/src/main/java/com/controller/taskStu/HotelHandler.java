package com.controller.taskStu;

import com.common.consts.Const;
import com.common.services.api.APIClientRequest;
import com.common.utils.DataSet;
import com.common.utils.DatatablesResponse;
import com.common.utils.GsonUtils;
import com.common.utils.PageHelpVO;
import com.entity.task.EpAsjBl;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 住宿信息hander
 *
 * @author admin
 * @create 2018-03-21 15:59
 **/
@RequestMapping("/hotel")
@Controller
public class HotelHandler {


    /**
     * 	/hotel/selectById
     * @param httpSession
     * @param request
     * @param id
     * @param userNoticId
     * @return
     */
    @RequestMapping(value="/selectById",produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public String selectById(HttpSession httpSession, HttpServletRequest request, String id, String userNoticId){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",id);
        String jsonStr = "";
        try {
            jsonStr = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_TASKSTU, "/hotel/selectHotelByPersonId", jsonObject);
            System.out.println(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonStr;
    }



}
