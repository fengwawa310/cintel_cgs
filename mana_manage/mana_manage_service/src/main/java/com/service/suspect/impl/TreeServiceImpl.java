package com.service.suspect.impl;

import com.common.utils.StringHelp;
import com.entity.DicCommon;
import com.entity.DicUnit;
import com.entity.suspect.EtGang;
import com.entity.suspect.EtSuspect;
import com.entity.suspect.OperSuspectVo;
import com.mapper.suspect.EtGangMapper;
import com.mapper.suspect.EtSuspectMapper;
import com.mapper.suspect.RlSuspectGangMapper;
import com.service.suspect.TreeServiceInfc;
import com.vo.TreeNodeVo;
import com.vo.suspect.SuspectTreeNodeVo;
import com.vo.suspect.SuspectTreePo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import javax.annotation.Resource;

/**
 * Created by Auri on 2018/2/3.
 * Desc:
 */
@Service
@Transactional
public class TreeServiceImpl implements TreeServiceInfc {

    @Resource
    protected EtSuspectMapper etSuspectMapper;

    @Resource
    private EtGangMapper etGangMapper;

    @Resource
    private RlSuspectGangMapper rlSuspectGangMapper;

    @Override
    public List<SuspectTreeNodeVo> listTreeNodesByType(List<DicCommon> suspectClassDics, String paramStr,String userId,String entryUnit) {
        List<SuspectTreeNodeVo> nodes = new ArrayList<>();
        EtSuspect etParam = buildParam(paramStr);
        //查询人员 可看自己录的和别人授权给自己的
        etParam.setEntry(userId);
        if(!"".equals(entryUnit)){
            etParam.setEntryUnit(entryUnit);
        }
        List<SuspectTreePo> ets = etSuspectMapper.fuzzeyQuery(etParam);
        // 生成文件夹node
        String ortherId = String.valueOf(System.currentTimeMillis() / 1000);
        List<SuspectTreeNodeVo> roots = buildRootNodes(suspectClassDics, ortherId);
        // 生成子节点
        List<SuspectTreeNodeVo> subNodes = buildSuspectNodes(ets, roots, ortherId);
        nodes.addAll(roots);
        nodes.addAll(subNodes);
        return nodes;
    }

    @Override
    public List<SuspectTreeNodeVo> listTreeNodesByUnit(List<DicUnit> units, String paramStr,String userId,String entryUnit) {
        List<SuspectTreeNodeVo> nodes = new ArrayList<>();
        EtSuspect etParam = buildParam(paramStr);
        //查询人员 可看自己录的和别人授权给自己的
        etParam.setEntry(userId);
        if(!"".equals(entryUnit)){
            etParam.setEntryUnit(entryUnit);
        }
        List<SuspectTreePo> ets = etSuspectMapper.fuzzeyQuery(etParam);
        // 生成文件夹node
        String ortherId = String.valueOf(System.currentTimeMillis() / 1000);
        List<SuspectTreeNodeVo> roots = buildRootNodesII(units, ortherId);
        // 生成子节点
        List<SuspectTreeNodeVo> subNodes = buildSuspectNodesII(ets, roots, ortherId);
        nodes.addAll(roots);
        nodes.addAll(subNodes);
        return nodes;
    }

    @Override
    public List<SuspectTreeNodeVo> listTreeNodesByGang(List<EtGang> gangs, String paramStr, String userId) {
        List<SuspectTreeNodeVo> nodes = new ArrayList<>();
        //List<EtGang> gangs = etGangMapper.selectAll();
        gangs = etGangMapper.selectAll(paramStr);
        if(gangs.size() == 0 ){
        	gangs = null;
        }
        List<Map<String, Object>> rlGangs = rlSuspectGangMapper.fuzzeyQuery(paramStr,userId);
        // 生成文件夹node
        String ortherId = String.valueOf(System.currentTimeMillis() / 1000);
        List<SuspectTreeNodeVo> roots = buildRootNodesIII(gangs, ortherId);
        // 生成子节点
        List<SuspectTreeNodeVo> subNodes = buildSuspectNodesIII(rlGangs, roots, ortherId);
//        List<SuspectTreeNodeVo> subNodes = new ArrayList<>();
        nodes.addAll(roots);
        nodes.addAll(subNodes);
        return nodes;
    }

    @Override
    public List<SuspectTreeNodeVo> listTreeNodesByPermission(String sysUserId, String paramStr, Set<String> defaultSuspectIds) {
        defaultSuspectIds = defaultSuspectIds == null ? new HashSet<String>() : defaultSuspectIds;
        List<SuspectTreeNodeVo> nodes = new ArrayList<>();
        Map<String, String> params = new HashMap<>();
        params.put("userId", sysUserId);
        params.put("paramStr", paramStr);
        List<OperSuspectVo> ets = etSuspectMapper.fuzzeyQueryWithPermission(params);
        // 生成子节点
        int len = ets.size();
        int index = 0;
        while (index < len) {
            OperSuspectVo et = ets.get(index);
            if (!defaultSuspectIds.contains(et.getSuspectId())) // 已选择的人员不再出现
            {
                SuspectTreeNodeVo stNode = buildNode(et.getSuspectId(), "00", et.getSuspectId(), et.getName(), false, TreeNodeVo.TYPE_NODE, et.getUserId(), et.getPermissionCode());
                nodes.add(stNode);
            }
            index++;
        }
        Collections.sort(nodes, new Comparator<SuspectTreeNodeVo>() {
            @Override
            public int compare(SuspectTreeNodeVo o1, SuspectTreeNodeVo o2) {
                int temp = o1.getId().compareTo(o2.getId()) > 0 ? 1 : -1;
                return temp;
            }
        });
        return nodes;
    }

    private List<SuspectTreeNodeVo> buildSuspectNodesIII(List<Map<String, Object>> rls, List<SuspectTreeNodeVo> rootNodes, String ortherId) {
        List<SuspectTreeNodeVo> nodes = new ArrayList<>();
        if (rls == null || rls.isEmpty()) {
            return nodes;
        }
        int len = rls.size();
        int index = 0;
        while (index < len) {
            Map<String, Object> rl = rls.get(index);
            String gangIdStr = String.valueOf(rl.get("gangId"));
            String userId = String.valueOf(rl.get("userId"));
//            String permissionCodeStr = String.valueOf(rl.get("permissionCode"));
            int permissionCode =0;// StringHelp.isEmpty(permissionCodeStr) ? -1 : Integer.valueOf(permissionCodeStr);
            SuspectTreeNodeVo stNode = null;
            // 依据团伙ID 分配结点所在目录
            if (StringHelp.isNotEmpty(gangIdStr)) {
                for (SuspectTreeNodeVo rootNode : rootNodes) {
                    String idStr = rootNode.getId();
                    if (gangIdStr.contains(idStr)) {
                        String suspectId = String.valueOf(rl.get("suspectId"));
                        String byName=String.valueOf(rl.get("byName"));
                        if(byName!=null&&!"".equals(byName)){
                        	byName=" ("+byName+")";
                        }else{
                        	byName="";
                        }
                        stNode = buildNode(suspectId, idStr, suspectId, String.valueOf(rl.get("name"))+byName, false, TreeNodeVo.TYPE_NODE, userId, permissionCode);
                        break;
                    }
                }
            }
            // 无对应团伙 分配至“其他”目录下
            if (stNode == null) {
                String suspectId = String.valueOf(rl.get("suspectId"));
                String byName=String.valueOf(rl.get("byName"));
                if(byName!=null&&!"".equals(byName)){
                	byName=" ("+byName+")";
                }else{
                	byName="";
                }
                stNode = buildNode(suspectId, ortherId, suspectId, String.valueOf(rl.get("name"))+byName, false, TreeNodeVo.TYPE_NODE, userId, permissionCode);
            }
            nodes.add(stNode);
            index++;
        }
        Collections.sort(nodes, new Comparator<SuspectTreeNodeVo>() {
            @Override
            public int compare(SuspectTreeNodeVo o1, SuspectTreeNodeVo o2) {
//                int temp = o1.getId().compareTo(o2.getId()) > 0 ? 1 : -1;
//                return temp;
                return o1.getId().compareTo(o2.getId());
            }
        });
        return nodes;
    }

    private List<SuspectTreeNodeVo> buildSuspectNodesII(List<SuspectTreePo> ets, List<SuspectTreeNodeVo> rootNodes, String ortherId) {
        List<SuspectTreeNodeVo> nodes = new ArrayList<>();
        if (ets == null || ets.isEmpty()) {
            return nodes;
        }
        int len = ets.size();
        int index = 0;
        while (index < len) {
            SuspectTreePo et = ets.get(index);
            String entryUnitStr = String.valueOf(et.getEntryUnit());
            SuspectTreeNodeVo stNode = null;
            // 依据重点人员录入单位 分配结点所在目录
            if (StringHelp.isNotEmpty(entryUnitStr)) {
                for (SuspectTreeNodeVo rootNode : rootNodes) {
                    String idStr = rootNode.getId();
                    if (entryUnitStr.contains(idStr)) {
                        stNode = buildNode(et.getSuspectId(), idStr, et.getSuspectId(), et.getName(), false, TreeNodeVo.TYPE_NODE, et.getUserId(), et.getPermissionCode());
                        break;
                    }
                }
            }
            // 无对应单位 分配至“其他”目录下
            if (stNode == null) {
                stNode = buildNode(et.getSuspectId(), ortherId, et.getSuspectId(), et.getName(), false, TreeNodeVo.TYPE_NODE, et.getUserId(), et.getPermissionCode());
            }
            nodes.add(stNode);
            index++;
        }
        Collections.sort(nodes, new Comparator<SuspectTreeNodeVo>() {
            @Override
            public int compare(SuspectTreeNodeVo o1, SuspectTreeNodeVo o2) {

//                int temp = o1.getId().compareTo(o2.getId()) > 0 ? 1 : -1;
//                return temp;
                return o1.getId().compareTo(o2.getId());
            }
        });
        return nodes;
    }

    /**
     * 依据一级目录 构建子级目录
     *
     * @param ets
     * @param rootNodes
     * @return
     */
    private List<SuspectTreeNodeVo> buildSuspectNodes(List<SuspectTreePo> ets, List<SuspectTreeNodeVo> rootNodes, String ortherId) {
        List<SuspectTreeNodeVo> nodes = new ArrayList<>();
        if (ets == null || ets.isEmpty()) {
            return nodes;
        }
        int len = ets.size();
        int index = 0;
        while (index < len) {
            SuspectTreePo et = ets.get(index);
            String suspectType = String.valueOf(et.getSuspectType());
            SuspectTreeNodeVo stNode = null;
            // 依据重点人员类型 分配结点所在目录
            if (StringHelp.isNotEmpty(suspectType)) {
                for (SuspectTreeNodeVo rootNode : rootNodes) {
                    String idStr = rootNode.getId();
                    if (suspectType.contains(idStr)) {
                        stNode = buildNode(et.getSuspectId(), idStr, et.getSuspectId(), et.getName(), false, TreeNodeVo.TYPE_NODE, et.getUserId(), et.getPermissionCode());
                        break;
                    }
                }
            }
            // 无对应重点人员类型 分配至“其他”目录下
            if (stNode == null) {
                stNode = buildNode(et.getSuspectId(), ortherId, et.getSuspectId(), et.getName(), false, TreeNodeVo.TYPE_NODE, et.getUserId(), et.getPermissionCode());
            }
            nodes.add(stNode);
            index++;
        }
        Collections.sort(nodes, new Comparator<SuspectTreeNodeVo>() {
            @Override
            public int compare(SuspectTreeNodeVo o1, SuspectTreeNodeVo o2) {
                int temp = o1.getId().compareTo(o2.getId()) > 0 ? 1 : -1;
                return temp;
            }
        });
        return nodes;
    }

    private EtSuspect buildParam(String paramStr) {
        EtSuspect etSuspect = new EtSuspect();
        if (StringHelp.isEmpty(paramStr)) {
            paramStr = "";
        }
        etSuspect.setName(paramStr);
        etSuspect.setByname(paramStr);
        etSuspect.setIdcardNum(paramStr);
        return etSuspect;
    }

    /**
     * 构建一级目录
     *
     * @param gangs
     * @return
     */
    private List<SuspectTreeNodeVo> buildRootNodesIII(List<EtGang> gangs, String ortherId) {
        List<SuspectTreeNodeVo> roots = new ArrayList<>();
        if (gangs == null || gangs.isEmpty()) {
            return roots;
        }
        for (int i = 0; i < gangs.size(); i++) {
            boolean isOpen = false;
            if (i == 0) {
                isOpen = true;
            }
            EtGang gang = gangs.get(i);
            SuspectTreeNodeVo temp = buildNode(gang.getId(), "00", "", gang.getGangName(), isOpen, TreeNodeVo.TYPE_DIR, null, -1);
            roots.add(temp);
        }
        Collections.sort(roots, new Comparator<SuspectTreeNodeVo>() {
            @Override
            public int compare(SuspectTreeNodeVo o1, SuspectTreeNodeVo o2) {
                int temp = o1.getId().compareTo(o2.getId()) > 0 ? 1 : -1;
                return temp;
            }
        });
        SuspectTreeNodeVo orderN = buildOtherDicNode(ortherId);
        roots.add(orderN);
        return roots;
    }

    /**
     * 构建一级目录
     *
     * @param units
     * @return
     */
    private List<SuspectTreeNodeVo> buildRootNodesII(List<DicUnit> units, String ortherId) {
        List<SuspectTreeNodeVo> roots = new ArrayList<>();
        if (units == null || units.isEmpty()) {
            return roots;
        }
        for (int i = 0; i < units.size(); i++) {
            boolean isOpen = false;
            if (i == 0) {
                isOpen = true;
            }
            DicUnit dic = units.get(i);
            SuspectTreeNodeVo temp = buildNode(dic.getCode(), "00", "", dic.getName(), isOpen, TreeNodeVo.TYPE_DIR, null, -1);
            roots.add(temp);
        }
        Collections.sort(roots, new Comparator<SuspectTreeNodeVo>() {
            @Override
            public int compare(SuspectTreeNodeVo o1, SuspectTreeNodeVo o2) {
                int temp = o1.getId().compareTo(o2.getId()) > 0 ? 1 : -1;
                return temp;
            }
        });
        SuspectTreeNodeVo orderN = buildOtherDicNode(ortherId);
        roots.add(orderN);
        return roots;
    }

    /**
     * 构建一级目录
     *
     * @param suspectClassDics
     * @return
     */
    private List<SuspectTreeNodeVo> buildRootNodes(List<DicCommon> suspectClassDics, String ortherDicId) {
        List<SuspectTreeNodeVo> roots = new ArrayList<>();
        if (suspectClassDics == null || suspectClassDics.isEmpty()) {
            return roots;
        }
        for (int i = 0; i < suspectClassDics.size(); i++) {
            boolean isOpen = false;
            if (i == 0) {
                isOpen = true;
            }
            DicCommon dic = suspectClassDics.get(i);
            SuspectTreeNodeVo temp = buildNode(dic.getDicCode(), "00", "", dic.getDicValue(), isOpen, TreeNodeVo.TYPE_DIR, null, -1);
            roots.add(temp);
        }
        Collections.sort(roots, new Comparator<SuspectTreeNodeVo>() {
            @Override
            public int compare(SuspectTreeNodeVo o1, SuspectTreeNodeVo o2) {
                int temp = o1.getId().compareTo(o2.getId()) > 0 ? 1 : -1;
                return temp;
            }
        });
        SuspectTreeNodeVo orderN = buildOtherDicNode(ortherDicId);
        roots.add(orderN);
        return roots;
    }


    private SuspectTreeNodeVo buildOtherDicNode(String otherId) {
        return buildNode(otherId, "00", "", "其他", false, TreeNodeVo.TYPE_DIR, null, -1);
    }

    private SuspectTreeNodeVo buildNode(String id, String pId, String suspectNo, String name, boolean isOpen, int nodeType, String userId, int permissionCode) {
        id = StringHelp.isEmpty(id) ? "" : id;
        SuspectTreeNodeVo node = new SuspectTreeNodeVo();
        node.setId(id);
        node.setpId(pId);
        node.setName(name);
        node.setOpen(isOpen);
        node.setType(nodeType);
        node.setSuspectNo(suspectNo);
        node.setUserId(userId);
        node.setPermissionCode(permissionCode);
        if (nodeType == TreeNodeVo.TYPE_DIR) {
//            node.setIconOpen("view/assets/images/gallery/file_open.png");
            node.setIcon("view/assets/images/gallery/file_close.png");
        } else {
            node.setIcon("view/assets/images/gallery/person_open.png");
        }
        return node;
    }
}
