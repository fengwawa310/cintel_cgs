package com.service.suspect;

import com.entity.DicCommon;
import com.entity.DicUnit;
import com.entity.suspect.EtGang;
import com.vo.suspect.SuspectTreeNodeVo;

import java.util.List;
import java.util.Set;

/**
 * Created by Auri on 2018/2/3.
 * Desc: 树形结构展示功能接口
 */
public interface TreeServiceInfc {

//    /**
//     *
//     * @param paramStr
//     * @param treeType 1=按团伙；2=按所属单位；3=按重点人员类型SUSPECT_TYPE
//     * @return
//     */
//    Set<SuspectTreeNodeVo> listTreeNodes(String paramStr, int treeType);

    /**
     * 依据重点人员类型 获取人员树形结构列表
     * @param dics
     * @param paramStr
     * @return
     */
    List<SuspectTreeNodeVo> listTreeNodesByType(List<DicCommon> dics, String paramStr,String userId,String entryUnit);

    List<SuspectTreeNodeVo> listTreeNodesByUnit(List<DicUnit> units, String paramStr,String userId,String entryUnit);

    List<SuspectTreeNodeVo> listTreeNodesByGang(List<EtGang> gangs, String paramStr,String userId);

    /**
     * 依据系统用户ID 条件查询当前用户可查阅的重点人员档案信息
     * @param sysUserId
     * @param paramStr
     * @param suspectIds
     * @return
     */
    List<SuspectTreeNodeVo> listTreeNodesByPermission(String sysUserId, String paramStr, Set<String> suspectIds);
}
