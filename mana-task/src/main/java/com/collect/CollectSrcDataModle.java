package com.collect;

import com.collect.ANJQ.task.*;
import com.collect.cnf.CTCnf;
import com.collect.cnf.CTTaskCnf;
import com.common.CommonUtils;
import com.common.XMLUtil;
import com.common.utils.LogUtils;
import com.controller.task.DockingController;
import com.service.task.CollectSrcDataServiceInfc;
import org.apache.commons.lang.StringUtils;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/9.
 */
public class CollectSrcDataModle {

    protected CollectSrcDataServiceInfc collectSrcDataService;

//    /**
//     * 采集组件
//     */
//    private List<CollectAJ> cjList=new ArrayList<>();

//    /**
//     * 配置集合
//     */
//    private List<CTCnf> cnfList =new ArrayList<>();

    private CTCnf ajCnf;
    private CTCnf jqCnf;
    private CTCnf ryqkCnf;
    private CTCnf xyrCnf;

    private AJTask ajTask;
    private JQTask jqTask;
    private RYQKTask ryqkTask;
    private XYRTask xyrTask;

    public CollectSrcDataModle(CollectSrcDataServiceInfc collectSrcDataService) {
        this.collectSrcDataService = collectSrcDataService;
    }

    /**
     * 读取Xml配置文件
     *
     * @return
     */
    public CTTaskCnf buildCnf() {
        CTTaskCnf taskCnf = null;
        String rootPath = CommonUtils.getWEBINFPath();
        String cnfXmlPath = rootPath + File.separator + "classes" + File.separator + CTTaskCnf.CNF_FILENAME;
        try {
            taskCnf = XMLUtil.convertXmlFileToObject(CTTaskCnf.class, cnfXmlPath);
        } catch (JAXBException e) {
            e.printStackTrace();
            LogUtils.error(e);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            LogUtils.error(e);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            LogUtils.error(e);
        }
        return taskCnf;
    }

    /**
     * 定制流程 采集具备一定关联关系的原始数据
     *
     * @param isManual 是否手动触发 true：手动触发
     */
    public void collectNewSrcData(boolean isManual, boolean needReload) {
        CTTaskCnf taskCnf = buildCnf();
        long buildTasks=0;
        long procCollectSrcData=0;
        if (taskCnf == null) {
            LogUtils.error("CT 根配置文件解析出错！");
            return;
        }
        // 初始化任务配置
        initCnfs(taskCnf, isManual, needReload);
        	long begin = System.currentTimeMillis();
        buildTasks();
     	long end = System.currentTimeMillis();
     	buildTasks=buildTasks+(end-begin);
     	if ("info".equals(DockingController.logLevel)){
            LogUtils.info("buildTasks:"+buildTasks);
            LogUtils.info("CT开始 本次数据采集任务 ************************");
        }
     	long begin1 = System.currentTimeMillis();
        procCollectSrcData();
     	long end1 = System.currentTimeMillis();
     	procCollectSrcData=procCollectSrcData+(end1-begin1);
     	
     	if ("info".equals(DockingController.logLevel)){
            LogUtils.info("procCollectSrcData:"+procCollectSrcData);
            LogUtils.info("CT结束 本次数据采集任务 ------------------------");
        }
    }

    public void reviseSrcData(boolean isManual, boolean needReload) {
    		long buildTasks=0;
        long procCollectSrcData=0;
        CTTaskCnf taskCnf = buildCnf();
        if (taskCnf == null) {
            LogUtils.error("RT 根配置文件解析出错！");
            return;
        }
        initCnfs(taskCnf, isManual, needReload);
        long begin = System.currentTimeMillis();
        buildTasks();
        long end = System.currentTimeMillis();
     	buildTasks=buildTasks+(end-begin);
     	if ("info".equals(DockingController.logLevel)){
            LogUtils.info("REbuildTasks:"+buildTasks);
            LogUtils.info("RT开始 本次数据采集任务 ************************");
        }
        long begin1 = System.currentTimeMillis();
        proceReviseSrcData();
        
        
        long end1 = System.currentTimeMillis();
     	procCollectSrcData=procCollectSrcData+(end1-begin1);

        if ("info".equals(DockingController.logLevel)){
            LogUtils.info("procCollectSrcData:"+procCollectSrcData);
            LogUtils.info("RT结束 本次数据采集任务 ------------------------");
        }
    }

    /**
     * 生成采集任务模型
     */
    private void buildTasks() {
        ajTask = new AJTask(collectSrcDataService,ajCnf);
        ajTask.prepareToCollect(ajCnf);
        jqTask = new JQTask(collectSrcDataService,jqCnf);
        jqTask.prepareToCollect(jqCnf);
        ryqkTask = new RYQKTask(collectSrcDataService,ryqkCnf);
        ryqkTask.prepareToCollect(ryqkCnf);
        xyrTask = new XYRTask(collectSrcDataService,xyrCnf);
        xyrTask.prepareToCollect(xyrCnf);
    }

    private void initCnfs(CTTaskCnf taskCnf, boolean isManual, boolean needReload) {
        List<CTCnf> cnfs = taskCnf.getCnfs(); // 识别对应任务的配置对象
        if (needReload) {
            ajCnf = findCnfByName(CTTaskCnf.CT_AJ, cnfs);
            jqCnf = findCnfByName(CTTaskCnf.CT_JQ, cnfs);
            ryqkCnf = findCnfByName(CTTaskCnf.CT_RYQK, cnfs);
            xyrCnf = findCnfByName(CTTaskCnf.CT_XYR, cnfs);
        }
        ajCnf.setManual(isManual);
        jqCnf.setManual(isManual);
        ryqkCnf.setManual(isManual);
        xyrCnf.setManual(isManual);
    }

    private CTCnf findCnfByName(String name, List<CTCnf> cnfs) {
        if (cnfs == null || cnfs.isEmpty() || StringUtils.isEmpty(name)) {
            return null;
        }
        for (CTCnf cnf : cnfs) {
            if (name.equals(cnf.getName())) {
                return cnf;
            }
        }
        return null;
    }

    private void procCollectSrcData() {

        // 由于新数据采集主要围绕案件进行 故在一次案件分页采集后，立即对其余数据进行采集，减少系统压力
        ajTask.pageCollectByCnf(ajCnf, null, new CollectTaskLisnr() {
            @Override
            public void whenPageCollectFinish(Map<String, List<String>> paramMap) {
                List<String> jqbhs = paramMap.get(AJTask.KEY_JQBHARR);
                
                jqTask.pageCollectByCnf(jqCnf, jqbhs, null);
                ryqkTask.pageCollectByCnf(ryqkCnf, jqbhs, new CollectTaskLisnr() {
                    @Override
                    public void whenPageCollectFinish(Map<String, List<String>> params) {
                        // 嫌疑人数据的采集 依赖嫌疑人关联关系完成
                        List<String> rybhs = params.get(AJTask.KEY_RYBHARR);
                        xyrTask.pageCollectByCnf(xyrCnf, rybhs, null);
                    }
                });
                List<String> ajbhs = paramMap.get(AJTask.KEY_JQBHARR);
                ryqkTask.pageCollectByCnf(ryqkCnf, ajbhs, new CollectTaskLisnr() {
                    @Override
                    public void whenPageCollectFinish(Map<String, List<String>> params) {
                        // 嫌疑人数据的采集 依赖嫌疑人关联关系完成
                        List<String> rybhs = params.get(AJTask.KEY_RYBHARR);
                        xyrTask.pageCollectByCnf(xyrCnf, rybhs, null);
                    }
                });
            }
        });
        whenTaskFinish("CT TOTAL");
    }

    private void proceReviseSrcData() {
//        caj.pageReviseByCnf(ajCnf, null); // 案件数据不再修订范围内
//        cjq.pageReviseByCnf(jqCnf, null); // 警情无法通过最后修改时间来确认有变动的数据 故暂时不开启

        //我注释的
    /*    cbl.pageReviseByCnf(blCnf, null);
        cwp.pageReviseByCnf(wpCnf, null);
        cryqk.pageReviseByCnf(ryqkCnf, new CollectModel.PageCollectLisnr() {
            @Override
            public void whenPageCollectFinish(List<String> rybhs) {
                // 先依据已修订的关联关系 采集一次xyr数据 保证数据范围的完整
                cxyr.pageCollectByCnf(xyrCnf, rybhs, null);
            }
        });
        // 在修订一次最新的 xyr 数据 保证数据正确性
        cxyr.pageReviseByCnf(xyrCnf, null);
        whenTaskFinish("RT TOTAL");*/
    }

    private void whenTaskFinish(String tName) {
//        for (int i = 0; i < cjList.size(); i++) {
//            cjList.get(i).forceMV();
//            CollectRecord cjCR = cjList.get(i).getCollectRecord();
//            LogUtils.info(tName + " " + cnfList.get(i).getName() + ": " + cjCR.toString());
//        }
        ajTask.forceMV();
        if ("info".equals(DockingController.logLevel)){
            LogUtils.info(tName + " AJ: " + ajTask.getCollectRecord().toString());
        }
        collectSrcDataService.recordTaslLog(ajTask.getCollectRecord());
        jqTask.forceMV();
        if ("info".equals(DockingController.logLevel)){
            LogUtils.info(tName + " JQ: " + jqTask.getCollectRecord().toString());
        }
//        collectSrcDataService.recordTaslLog(jqTask.getCollectRecord());
        ryqkTask.forceMV();
        if ("info".equals(DockingController.logLevel)){
            LogUtils.info(tName + " RYQK: " + ryqkTask.getCollectRecord().toString());
        }
//        collectSrcDataService.recordTaslLog(ryqkTask.getCollectRecord());
        xyrTask.forceMV();
        if ("info".equals(DockingController.logLevel)){
            LogUtils.info(tName + " XYR: " + xyrTask.getCollectRecord().toString());
        }
//        collectSrcDataService.recordTaslLog(xyrTask.getCollectRecord());
    }


}
