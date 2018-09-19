package com.text.uie;

import com.collect.cnf.CTTaskCnf;
import com.common.XMLUtil;
import com.common.utils.LogUtils;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * Created by Administrator on 2017/12/9.
 */
public class XmlReadTest {

    @Test
    public void test0() {
        CTTaskCnf taskCnf = null;
        try {
            taskCnf = XMLUtil.convertXmlFileToObject(CTTaskCnf.class, "src\\main\\resources\\CollectTaskCnf.xml");
            System.out.println(taskCnf.getCnfs().get(0).getName());
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1() {
        String str0 = "/E:/code/ShenZhen_Trunk/XiaoAn/mana-task/target/cintel-portal/WEB-INF/classes/com/controller/task/";
        int aa = str0.indexOf("WEB-INF");
        String str1 = str0.substring(0, aa + 7);
        System.out.print(str1);
    }

    @Test
    public void test2() {
        LogUtils.error("测试");
        /*int i = 0;
        SrcAlarmEntity sd = new SrcAlarmEntity();
        sd.setAjbh("" + 1 + "你好棒啊“”");
        sd.setSljjdw("" + i + "_" + Math.max(1, 100));
        sd.setAbAjZ("" + i + "_" + Math.max(1, 100));
//        sd.setAjbh("" + i + "_" + Math.max(1, 100));
        sd.setAjlxIs("" + i + "_" + Math.max(1, 100));
        sd.setAjmc("" + i + "_" + Math.max(1, 100));
        sd.setDepartmentcodeName("" + i + "_" + Math.max(1, 100));
        sd.setFadd("" + i + "_" + Math.max(1, 100));
        sd.setFasjcz(new Date());
        sd.setFasjzz(new Date());
        sd.setJqxz("" + i + "_" + Math.max(1, 100));
        sd.setSlAjclqk("" + i + "_" + Math.max(1, 100));
        sd.setSlBjslh("" + i + "_" + Math.max(1, 100));
        sd.setSlCjryName("" + i + "_" + Math.max(1, 100));
        sd.setSljjdw("" + i + "_" + Math.max(1, 100));
        sd.setSljjdwFj("" + i + "_" + Math.max(1, 100));
        sd.setSljjdwName("" + i + "_" + Math.max(1, 100));
        sd.setSlJjfs("" + i + "_" + Math.max(1, 100));
        sd.setSljjryName("" + i + "_" + Math.max(1, 100));
        sd.setSljjsj(new Date());
        sd.setSljjsjY("" + i + "_" + Math.max(1, 100));
        sd.setSljjsjM("" + i + "_" + Math.max(1, 100));
        sd.setSljsdwFj("" + i + "_" + Math.max(1, 100));
        sd.setSljsdwName("" + i + "_" + Math.max(1, 100));
        sd.setSlLrrName("" + i + "_" + Math.max(1, 100));
        sd.setYqbAsjJq("" + i + "_" + Math.max(1, 100));
        sd.setZyaq("" + i + "_" + Math.max(1, 100) + "/n");

        JzToSrcHelper<JZAlarmEntity, SrcAlarmEntity> helper = new JzToSrcHelper<JZAlarmEntity, SrcAlarmEntity>() {
            @Override
            public JZAlarmEntity whenTransOver(JZAlarmEntity objT, SrcAlarmEntity objK) {
                return objT;
            }
        };
        JZAlarmEntity jz = helper.transTheJzToSrc(sd,JZAlarmEntity.class);
        System.out.print(jz.getAjbh());*/
    }

    @Test
    public void test03()
    {
        String abc = "";
        abc = abc.substring(0,abc.length() - 1);
        System.out.println(abc);
    }

}
