package com.controller.taskStu;

import com.common.consts.Const;
import com.common.services.api.APIClientRequest;
import com.common.utils.DataSet;
import com.common.utils.DatatablesResponse;
import com.common.utils.GsonUtils;
import com.common.utils.PageHelpVO;
import com.entity.task.EpAsjBl;
import com.entity.task.EpHotelPerson;
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
@RequestMapping("/hotelPerson")
@Controller
public class HotelPersonHandler {


    /**
     * 	/hotelPerson/selectById
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
            jsonStr = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_TASKSTU, "/hotelPerson/selectById", jsonObject);
            System.out.println(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonStr;
    }


    /**     /hotelPerson/findHotelPersonList
     *
     查询系统公告列表
     */
    @RequestMapping(value="/findHotelPersonList",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public DatatablesResponse findNotesList(HttpSession httpSession, HttpServletRequest request, @RequestParam(value = "search[value]") String search, @RequestParam(required=true,defaultValue="1")Integer start,
                                            @RequestParam(required=false,defaultValue="10")Integer length){

        /*查询条件*/
        Map map = new HashMap<>();
        map.put("start",""+start);
        map.put("length",""+length);
        map.put("name",request.getParameter("name"));//笔录对象姓名
        map.put("idcode",request.getParameter("idcode"));//笔录对象证件号码
        PageHelpVO pageHelpVO = null;
        try {
            String jsonStr = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_TASKSTU, "/hotelPerson/findHotelPersonList", map);
            JSONObject jsonObject = JSONObject.fromObject(jsonStr);
            JSONObject respContent = (JSONObject) jsonObject.get("data");
            pageHelpVO = GsonUtils.toBean(respContent.toString(), PageHelpVO.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        DataSet dataSet = new DataSet(pageHelpVO.getList(),pageHelpVO.getTotal(),pageHelpVO.getTotal());
        DatatablesResponse<EpHotelPerson> resp = DatatablesResponse.build(dataSet);
        return resp;
    }
}
