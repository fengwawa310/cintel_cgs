package com.controller.task;

import com.common.utils.ElasticSearchUtils;
import com.common.utils.GetPropertiesUtil;
import com.common.utils.IDGenerator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by weipc on 2018/5/22.
 */
public class SynchronousTask implements Runnable {

    private Integer flag;

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public SynchronousTask() {
    }

    public SynchronousTask(Integer flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        if(flag==1)//同步 v_czrk_jbxx
        {
            v_czrk_jbxx_task();
        } else if(flag==2)//同步 v_czrkxx
        {
            v_czrkxx_task();
        } else if(flag==3)//同步 v_wfz_ajxxb
        {
            v_wfz_ajxxb_task();

        } else if(flag==4)//同步 v_wfz_fzxxb
        {
            v_wfz_fzxxb_task();
        }
    }

    public static String V_CZRK_JBXX_TIME = "";

    public void v_czrk_jbxx_task(){
        Map<String, String> prosMap = GetPropertiesUtil.findProperties("resource/task.properties");
        //是否同步es 1是
        Integer vczrkjbxxEsSwitch =Integer.parseInt(prosMap.get("vczrkjbxxEsSwitch"));
        //是否同步mysql 1是
        Integer vczrkjbxxMySqlSwitch = Integer.parseInt(prosMap.get("vczrkjbxxMySqlSwitch"));
        //上次入库时间
        String v_czrk_jbxx_time=prosMap.get("vczrkjbxxtime");
        String condition="";
        if(v_czrk_jbxx_time!=null&&!"".equals(v_czrk_jbxx_time)){
            condition="         AND RKSJ>'"+v_czrk_jbxx_time+"' \n";
        }

        if(vczrkjbxxEsSwitch==1||vczrkjbxxMySqlSwitch==1){
            String field="XLH,ZT,DQM,SLSJ,YWLX,RYID,GMSFHM,XM,CYM,XB,MZ,CSRQ,CSSJ,CSDGJ,CSDSSX,CSDXZ,JGGJ,JGSSX,DZBM,SSXQ,XZ,SJJZDZBM,SJJZDZSSX,SJJZDZXZ,WHCD,HYZK,ZY,ZYLB,FWCS,LXDH,ZJXY,BYZK,SG,XX,JHRYHM,JHRYXM,JHRYGX,JHREHM,JHREXM,JHREGX,FQHM,FQXM,MQHM,MQXM,POHM,POXM,HSLBD,HYLBD,HDLBSGJDQ,HDLBSSSXQ,HDLBSXZ,QCRQ,QCZXLB,QWDGJ,QWDSSX,QWDXZ,SWRQ,SWZXRQ,SWZXLB,XXJB,XPXLH,XJGAJGJGDM,XJGAJGJGMC,PCSJGDM,PCSJGMC,HH,HLX,YHZGX,RHFLBS,NZF,PCSDM,JMSFZXLH,JMSFZQFJGMC,JMSFZYXQXQSRQ,JMSFZYXQXJZRQ,JMSFZSLYY,JMYDZQFRQ,JMYDZYXQX,JMYDZQFJGMC,JMYDZSLYY,JMYDZJXYY,JLXDM,JLXMC,MLPH,MLXZ,JWQDM,JWQMC,ZRQDM,ZRQMC,XZJDDM,XZJDMC,SQJCWHDM,SQJCWHMC,JMZDM,JMZMC,XMPY,HYLB,JS,RKJBXXXLH,BDRQ,BDLB,QYGJ,QYSSXQ,QYXZ,QYPCSDM,QYPCSJGDM,QYPCSJGMC,QYXZJDDM,QYXZJDMC,QYSQJCWHDM,QYSQJCWHMC,QYJWQDM,QYJWQMC,QYZRQDM,QYZRQMC,QYJMZDM,QYJMZMC,QYJLXDM,QYJLXMC,QYMLPH,QYMLXZ,HKXZ,ZDRYBZ,JS2,WBHH,YCZRGX,JMSFZQFRQ,JGXZ,YHH,QYZBH,ZQZBH,CSZMBH,CSZQFRQ,SWZMBH,SBRGMSFHM,SBRXM,KGHK,SLDWJGDM,SLDWMC,SLRXM,RYBH,ZZBH,CBBS,QCTBZ,QCDGJDQ,JMSFZJXYY,QYRQ,QYYY,READFLAG,RKSJ,ZY_RKSJ";
            String[] split = field.split(",");
            int pageNum=0;
            int eachSearch=1000;
            while(true){
                try {
                    //查询sql
                    StringBuffer sql = new StringBuffer();
                    sql.append("SELECT "+field+" FROM ( \n");
                    sql.append("	SELECT ROWNUM rnum, A .* FROM ( \n");
                    sql.append("		SELECT "+field+"\n");
                    sql.append("		FROM V_CZRK_JBXX WHERE 1 = 1 \n");
                    sql.append(condition);
                    sql.append("        ORDER BY RKSJ ASC \n");
                    sql.append("	) A \n");
                    sql.append("	WHERE ROWNUM <= (("+pageNum+"+1)*"+eachSearch+") \n");
                    sql.append(") A1 \n");
                    sql.append("WHERE A1.rnum  > ("+pageNum+"*"+eachSearch+") ");
//                    System.out.println("sql = " + sql.toString());
                    List<Map<String, Object>> list = JdbcController.selectCIDRAC(sql.toString());
                    if(list==null||list.size()==0){
                        break;
                    }
                    //同步mysql
                    if(vczrkjbxxMySqlSwitch==1){
                        StringBuffer insertSql = new StringBuffer("INSERT INTO V_CZRK_JBXX ( ID, "+field+" ) VALUES \n");
                        for (int i=0;i<list.size();i++){
                            Map<String, Object> map = list.get(i);
                            String id = IDGenerator.getorderNo();
                            map.put("ID",id);
                            if(i==0){
                                insertSql.append(" ('"+ id +"'");
                            }else{
                                insertSql.append(" ,('"+ id +"'");
                            }
                            for (String key :split) {
                                if(map.containsKey(key)){
                                    String value=map.get(key).toString();
                                    insertSql.append(",'"+value.trim()+"'");
                                }else{
                                    insertSql.append(",null");
                                }
                            }
                            insertSql.append(")\n");
                        }
//                        System.out.println("insertSql = " + insertSql.toString());
                        JdbcController.insertMysql(insertSql.toString());
                    }
                    V_CZRK_JBXX_TIME=list.get(list.size()-1).get("RKSJ").toString();
                    //同步es
                    if(vczrkjbxxEsSwitch==1){
                        for (Map<String, Object> map:list) {
                            ElasticSearchUtils.insert("cgs_v_czrk_jbxx","cgs",map,map.get("ID").toString());
                            V_CZRK_JBXX_TIME=map.get("RKSJ").toString();
                        }
                    }
                    //把最近更新时间写入配置文件中
                    Map<String, String> pros=new HashMap<>();
                    pros.put("vczrkjbxxtime",V_CZRK_JBXX_TIME);
                    GetPropertiesUtil.updateProperties("resource/task.properties",pros);
                    if(list.size()<eachSearch){
                        break;
                    }
                    ++pageNum;
                } catch (Exception e) {
                    System.out.println("异常");
                    break;
                }
            }
        }
    }

    public static String V_CZRKXX_TIME = "";

    public void v_czrkxx_task(){
        Map<String, String> prosMap = GetPropertiesUtil.findProperties("resource/task.properties");
        //是否同步es 1是
        Integer v_czrkxxEsSwitch =Integer.parseInt(prosMap.get("vczrkxxEsSwitch"));
        //是否同步mysql 1是
        Integer v_czrkxxMySqlSwitch = Integer.parseInt(prosMap.get("vczrkxxMySqlSwitch"));
        //上次入库时间
        String v_czrkxx_time=prosMap.get("vczrkxxtime");
        String condition="";
        if(v_czrkxx_time!=null&&!"".equals(v_czrkxx_time)){
            condition="         AND to_number(编号)>'"+v_czrkxx_time+"' \n";
        }
        if(v_czrkxxEsSwitch==1||v_czrkxxMySqlSwitch==1){
            String field="编号,户号,公民身份号码,姓名,性别,民族,出生日期,出生地省市县区,住址派出所,住址详址,服务处所,文化程度,婚姻状况,籍贯省市县区,变动原因,住址省市县区,与户主关系,职业类别,监护人一公民身份号码,监护人一姓名,监护人一监护关系,监护人二公民身份号码,监护人二姓名,监护人二监护关系,联系电话";
            String[] split = field.split(",");
            int pageNum=0;
            int eachSearch=1000;
            while(true){
                try {
                    //查询sql
                    StringBuffer sql = new StringBuffer();
                    sql.append("SELECT "+field+" FROM ( \n");
                    sql.append("	SELECT ROWNUM rnum, A .* FROM ( \n");
                    sql.append("		SELECT "+field+"\n");
                    sql.append("		FROM V_CZRKXX WHERE 1 = 1 \n");
                    sql.append(condition);
                    sql.append("        ORDER BY to_number(编号) ASC \n");
                    sql.append("	) A \n");
                    sql.append("	WHERE ROWNUM <= (("+pageNum+"+1)*"+eachSearch+") \n");
                    sql.append(") A1 \n");
                    sql.append("WHERE A1.rnum  > ("+pageNum+"*"+eachSearch+") ");
                    System.out.println("sql = " + sql.toString());
                    List<Map<String, Object>> list = JdbcController.selectCIDRAC(sql.toString());
                    if(list==null||list.size()==0){
                        break;
                    }
                    //同步mysql
                    if(v_czrkxxMySqlSwitch==1){
                        StringBuffer insertSql = new StringBuffer("INSERT INTO V_CZRKXX ( ID, "+field+" ) VALUES \n");
                        for (int i=0;i<list.size();i++){
                            Map<String, Object> map = list.get(i);
                            String id = IDGenerator.getorderNo();
                            map.put("ID",id);
                            if(i==0){
                                insertSql.append(" ('"+ id +"'");
                            }else{
                                insertSql.append(" ,('"+ id +"'");
                            }
                            for (String key :split) {
                                if(map.containsKey(key)){
                                    String value=map.get(key).toString();
                                    insertSql.append(",'"+value.trim()+"'");
                                }else{
                                    insertSql.append(",null");
                                }
                            }
                            insertSql.append(")\n");
                        }
//                        System.out.println("insertSql = " + insertSql.toString());
                        JdbcController.insertMysql(insertSql.toString());
                    }
                    V_CZRKXX_TIME=list.get(list.size()-1).get("编号").toString();
                    //同步es
                    if(v_czrkxxEsSwitch==1){
                        for (Map<String, Object> map:list) {
                            ElasticSearchUtils.insert("cgs_v_czrkxx","cgs",map,map.get("ID").toString());
                            V_CZRKXX_TIME=map.get("编号").toString();
                        }
                    }
                    //把最近更新时间写入配置文件中
                    Map<String, String> pros=new HashMap<>();
                    pros.put("vczrkxxtime",V_CZRKXX_TIME);
                    GetPropertiesUtil.updateProperties("resource/task.properties",pros);
                    if(list.size()<eachSearch){
                        break;
                    }
                    ++pageNum;
                } catch (Exception e) {
                    System.out.println("异常");
                    break;
                }
            }
        }
    }

    public static String V_WFZ_AJXXB_TIME = "";

    public void v_wfz_ajxxb_task(){
        Map<String, String> prosMap = GetPropertiesUtil.findProperties("resource/task.properties");
        //是否同步es 1是
        Integer vwfzajxxbEsSwitch =Integer.parseInt(prosMap.get("vwfzajxxbEsSwitch"));
        //是否同步mysql 1是
        Integer vwfzajxxbMySqlSwitch = Integer.parseInt(prosMap.get("vwfzajxxbMySqlSwitch"));
        //上次入库时间
        String v_wfz_ajxxb_time=prosMap.get("vwfzajxxbtime");
        String condition="";
        if(v_wfz_ajxxb_time!=null&&!"".equals(v_wfz_ajxxb_time)){
            condition="         AND to_date(JADATE,'yyyy-MM-dd')>to_date('"+v_wfz_ajxxb_time+"','yyyy-MM-dd') \n";
        }
        if(vwfzajxxbEsSwitch==1||vwfzajxxbMySqlSwitch==1){
            String field="AJID,AH,CBDEPT,CBR,SJY,SZDATE,LADATE,LAAY,AYZS,JADATE,JAAY,JAFS,SXRQ,SFBL,AJZT,JAZS";
            String[] split = field.split(",");
            int pageNum=0;
            int eachSearch=1000;
            while(true){
                try {
                    //查询sql
                    StringBuffer sql = new StringBuffer();
                    sql.append("SELECT "+field+" FROM ( \n");
                    sql.append("	SELECT ROWNUM rnum, A .* FROM ( \n");
                    sql.append("		SELECT "+field+"\n");
                    sql.append("		FROM V_WFZ_AJXXB WHERE 1 = 1 \n");
                    sql.append(condition);
                    sql.append("        ORDER BY to_date(JADATE,'yyyy-MM-dd') ASC ");
                    sql.append("	) A \n");
                    sql.append("	WHERE ROWNUM <= (("+pageNum+"+1)*"+eachSearch+") \n");
                    sql.append(") A1 \n");
                    sql.append("WHERE A1.rnum  > ("+pageNum+"*"+eachSearch+") ");
//                    System.out.println("sql = " + sql.toString());
                    List<Map<String, Object>> list = JdbcController.selectCIDRAC(sql.toString());
                    if(list==null||list.size()==0){
                        break;
                    }
                    //同步mysql
                    if(vwfzajxxbMySqlSwitch==1){
                        StringBuffer insertSql = new StringBuffer("INSERT INTO V_WFZ_AJXXB ( ID, "+field+" ) VALUES \n");
                        for (int i=0;i<list.size();i++){
                            Map<String, Object> map = list.get(i);
                            String id = IDGenerator.getorderNo();
                            map.put("ID",id);
                            if(i==0){
                                insertSql.append(" ('"+ id +"'");
                            }else{
                                insertSql.append(" ,('"+ id +"'");
                            }
                            for (String key :split) {
                                if(map.containsKey(key)){
                                    String value=map.get(key).toString();
                                    insertSql.append(",'"+value.trim()+"'");
                                }else{
                                    insertSql.append(",null");
                                }
                            }
                            insertSql.append(")\n");
                        }
//                        System.out.println("insertSql = " + insertSql.toString());
                        JdbcController.insertMysql(insertSql.toString());
                    }
                    V_WFZ_AJXXB_TIME=list.get(list.size()-1).get("JADATE").toString();
                    //同步es
                    if(vwfzajxxbEsSwitch==1){
                        for (Map<String, Object> map:list) {
                            ElasticSearchUtils.insert("cgs_v_wfz_ajxxb","cgs",map,map.get("ID").toString());
                            V_WFZ_AJXXB_TIME=map.get("JADATE").toString();
                        }
                    }
                    //把最近更新时间写入配置文件中
                    Map<String, String> pros=new HashMap<>();
                    pros.put("vwfzajxxbtime",V_WFZ_AJXXB_TIME);
                    GetPropertiesUtil.updateProperties("resource/task.properties",pros);
                    if(list.size()<eachSearch){
                        break;
                    }
                    ++pageNum;
                } catch (Exception e) {
                    System.out.println("异常");
                    break;
                }
            }
        }
    }

    public static String V_WFZ_FZXXB_TIME = "";

    public void v_wfz_fzxxb_task(){
        Map<String, String> prosMap = GetPropertiesUtil.findProperties("resource/task.properties");
        //是否同步es 1是
        Integer vwfzfzxxbEsSwitch =Integer.parseInt(prosMap.get("vwfzfzxxbEsSwitch"));
        //是否同步mysql 1是
        Integer vwfzfzxxbMySqlSwitch = Integer.parseInt(prosMap.get("vwfzfzxxbMySqlSwitch"));
        //上次入库时间
        String v_wfz_fzxxb_time=prosMap.get("vwfzfzxxbtime");
        String condition="";
        if(v_wfz_fzxxb_time!=null&&!"".equals(v_wfz_fzxxb_time)){
            condition="         AND RKSJ>'"+v_wfz_fzxxb_time+"' \n";
        }
        if(vwfzfzxxbEsSwitch==1||vwfzfzxxbMySqlSwitch==1){
            String field="XXID,XM,BMCH,ZJLX,XB,ZJHM,CSRQ,HJDZ,AJMC,PJZM,ZSSPJG,ZX,ZXQ,ZXQY,FJX,PJSWH,PJRQ,SXRQ,DACFD,CJR,CJSJ,CJDW,SHR,CJDD,BZXX,RKSJ,SCBJ,HJDXZ,SJLY,FZSSFCN";
            String[] split = field.split(",");
            int pageNum=0;
            int eachSearch=1000;
            while(true){
                try {
                    //查询sql
                    StringBuffer sql = new StringBuffer();
                    sql.append("SELECT "+field+" FROM ( \n");
                    sql.append("	SELECT ROWNUM rnum, A .* FROM ( \n");
                    sql.append("		SELECT "+field+"\n");
                    sql.append("		FROM V_WFZ_FZXXB WHERE 1 = 1 \n");
                    sql.append(condition);
                    sql.append("        ORDER BY RKSJ ASC \n");
                    sql.append("	) A \n");
                    sql.append("	WHERE ROWNUM <= (("+pageNum+"+1)*"+eachSearch+") \n");
                    sql.append(") A1 \n");
                    sql.append("WHERE A1.rnum  > ("+pageNum+"*"+eachSearch+") ");
//                    System.out.println("sql = " + sql.toString());
                    List<Map<String, Object>> list = JdbcController.selectCIDRAC(sql.toString());
                    if(list==null||list.size()==0){
                        break;
                    }
                    //同步mysql
                    if(vwfzfzxxbMySqlSwitch==1){
                        StringBuffer insertSql = new StringBuffer("INSERT INTO V_WFZ_FZXXB ( ID, "+field+" ) VALUES \n");
                        for (int i=0;i<list.size();i++){
                            Map<String, Object> map = list.get(i);
                            String id = IDGenerator.getorderNo();
                            map.put("ID",id);
                            if(i==0){
                                insertSql.append(" ('"+ id +"'");
                            }else{
                                insertSql.append(" ,('"+ id +"'");
                            }
                            for (String key :split) {
                                if(map.containsKey(key)){
                                    String value=map.get(key).toString();
                                    insertSql.append(",'"+value.trim()+"'");
                                }else{
                                    insertSql.append(",null");
                                }
                            }
                            insertSql.append(")\n");
                        }
//                        System.out.println("insertSql = " + insertSql.toString());
                        JdbcController.insertMysql(insertSql.toString());
                    }
                    V_WFZ_FZXXB_TIME=list.get(list.size()-1).get("RKSJ").toString();
                    //同步es
                    if(vwfzfzxxbEsSwitch==1){
                        for (Map<String, Object> map:list) {
                            ElasticSearchUtils.insert("cgs_v_wfz_fzxxb","cgs",map,map.get("ID").toString());
                            V_WFZ_FZXXB_TIME=map.get("RKSJ").toString();
                        }
                    }
                    //把最近更新时间写入配置文件中
                    Map<String, String> pros=new HashMap<>();
                    pros.put("vwfzfzxxbtime",V_WFZ_FZXXB_TIME);
                    GetPropertiesUtil.updateProperties("resource/task.properties",pros);
                    if(list.size()<eachSearch){
                        break;
                    }
                    ++pageNum;
                } catch (Exception e) {
                    System.out.println("异常");
                    break;
                }
            }
        }
    }
}
