package com.service.suspect;

import com.entity.suspect.EtCtrl;
import com.vo.ctrl.PageVoCtrl;
import com.service.BaseService;
import com.vo.suspect.CtrlDetailVo;
import com.vo.suspect.CtrlListItemVo;

import java.util.List;

/**
 * Created by Administrator on 2018/1/5.
 */
public interface CtrlService extends BaseService<EtCtrl,String> {


    /**
     * 保存一条有效的布控任务，保存成功后自动添加重点人员关键字监测任务
     * @param etCtrl
     * @return
     */
    int addCtrlAndCtrlKey(EtCtrl etCtrl);

    /**
     * 依据布控任务编号，取消一条布控任务，同时取消对应的关键字监测任务
     * @param ctrlNo
     * @return
     */
    int cancelCtrlAndCtrlKey(String ctrlNo);
    
    int cancelCtrlAndCtrlKeyByIdCardNum(String idCardNum);

    List<CtrlListItemVo> findByPage(PageVoCtrl pageVoCtrl);

    long countTotal(PageVoCtrl pageVoCtrl);

    EtCtrl findByNo(String no);

    CtrlDetailVo findDetailById(String id);
}
