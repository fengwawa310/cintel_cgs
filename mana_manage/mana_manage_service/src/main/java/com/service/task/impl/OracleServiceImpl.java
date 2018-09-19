package com.service.task.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import javax.annotation.Resource;

import com.common.consts.Global;
import com.common.utils.GetPropertiesUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.utils.ElasticSearchUtils;
import com.common.utils.IDGenerator;
import com.entity.task.EpAsjBl;
import com.entity.task.EpHotel;
import com.entity.task.EpHotelPerson;
import com.mapper.task.jingzong.OEpAsjBlMapper;
import com.mapper.task.jingzong.OEpHotelMapper;
import com.mapper.taskJingzong.EpAsjBlMapper;
import com.mapper.taskJingzong.EpHotelMapper;
import com.mapper.taskJingzong.EpHotelPersonMapper;
import com.mapper.taskgnlk.OEpHotelPersonMapper;
import com.service.task.OracleService;


/**
 * oracle数据库测试
 *
 * @author admin
 * @create 2017-12-06 16:26
 **/

@Service
@Transactional
public class OracleServiceImpl implements OracleService {

//    @Resource
//    private NiandudaocaojihuaDao niandudaocaojihuaDao;
    
    @Resource
    private OEpAsjBlMapper oEpAsjBlMapper;
    @Resource
    private OEpHotelMapper oEpHotelMapper;
    @Resource
    private OEpHotelPersonMapper oEpHotelPersonMapper;
    @Resource
    private EpAsjBlMapper epAsjBlMapper;
    @Resource
    private EpHotelMapper epHotelMapper;
    @Resource
    private EpHotelPersonMapper epHotelPersonMapper;
    
//    @Override
//    public List<NiandudaocaojihuaEntity> find() {
//        return niandudaocaojihuaDao.findDCJHData();
//    }
    
    private int eachSearch=1000;


	@Override
	public void findBlDataAndinsert(Map<String,String> mapCon) {
//		开始查询oracle数据库的笔录数据
		System.out.println("开始查询oracle数据库的笔录数据");
		String blIntoMySqlSwitch=mapCon.get("blIntoMySqlSwitch");
		Map<String,String> map= new HashMap<>();
		long epAsjBlCount=0;
		try{
//			查找最新的一条数据的LastUpdateTime数据
			String nearlyLastUpDateTime=epAsjBlMapper.findNearlyDateTime();
			if(nearlyLastUpDateTime!=null&&!"".equals(nearlyLastUpDateTime)){
				map.put("lastUpDateTime", nearlyLastUpDateTime);
			}
			map.put("lastUpDateTime", nearlyLastUpDateTime);
			epAsjBlCount=oEpAsjBlMapper.findBlDataCount(map);
		}catch(Exception e){
			e.printStackTrace();
		}
//		获取到的笔录数据条数
		System.out.println("本次需要同步的笔录数据条数:"+epAsjBlCount);
		
		System.out.println("开始写入mysql数据库");
		int result=0;
		if(epAsjBlCount>0){
			int searchNum=(int) (epAsjBlCount%eachSearch==0?epAsjBlCount/eachSearch:epAsjBlCount/eachSearch+1);
			for(int i=0;i<searchNum;i++){
				try{
					List<EpAsjBl> epAsjBl=null;
					map.put("pageNum", i+"");
					map.put("eachSearch", eachSearch+"");
//					根据最新的时间查询大于当前最新事件的数据
					epAsjBl=oEpAsjBlMapper.findBlData(map);
					if(epAsjBl.size()>0){
						if("1".equals(blIntoMySqlSwitch)){
							//存入数据库
							result=epAsjBlMapper.insertEpAsjBlList(epAsjBl);
							System.out.println("笔录存储到MYSQL");
						}
						//存入ES
						for(EpAsjBl eap:epAsjBl){
							Map<String,Object> rowData = new HashMap<>();//声明Map
							Field[] field = eap.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组 
							for(int j=0 ; j<field.length ; j++){     //遍历所有属性
								String name = field[j].getName();    //获取属性的名字
								name=name.substring(0, 1).toUpperCase() + name.substring(1); 
								Method m = eap.getClass().getMethod("get"+name);
								if(m.invoke(eap)!=null&&!"".equals(m.invoke(eap))){
									rowData.put(name.toUpperCase(), m.invoke(eap));
								}
							}
							ElasticSearchUtils.insert("cgs_ep_asj_bl","cgs",rowData,eap.getSystemid());
							System.out.println("笔录存储到ES");
						}
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}

		
	}
	@Override
	public void findHotelDataAndInsert(Map<String,String> map) {
		findAndInsertHotelData(map);
//		findAndInsertHotelPersonData(map);
	}
	/*
	 * 获取oracle中ZHSJ_JDMC的数据
	 * 插入mysql数据库ep_hotel
	 */
	public void findAndInsertHotelData(Map<String,String> mapCon){
		Map<String,String> map = new HashMap<>();
		String zyRksj=epHotelMapper.findLastZyRksj();
		if(zyRksj!=null && !"".equals(zyRksj)){
			map.put("zyRksj", zyRksj);
		}
		int result=100;
		long ehlistCount =oEpHotelMapper.findEpHotelCount(map);
		if(ehlistCount>0){
			int searchNum=(int) (ehlistCount%eachSearch==0?ehlistCount/eachSearch:ehlistCount/eachSearch+1);
			for(int i=0;i<searchNum;i++){
				List<EpHotel> ehlist=null;
				map.put("pageNum", i+"");
				map.put("eachSearch", eachSearch+"");
				ehlist =oEpHotelMapper.findEpHotelData(map);
				for(EpHotel e: ehlist){
					e.setId(IDGenerator.getorderNo());
				}
				try{
					if(ehlist.size()>0){
						result=epHotelMapper.insertEpHotelList(ehlist);
						System.out.println("酒店存储到MYSQL");
						//存入ES
//						for(EpHotel eh:ehlist){
//							Map<String,Object> rowData = new HashMap<>();//声明Map
//							Field[] field = eh.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组 
//							for(int j=0 ; j<field.length ; j++){     //遍历所有属性
//								String name = field[j].getName();    //获取属性的名字
//								name=name.substring(0, 1).toUpperCase() + name.substring(1); 
//								Method m = eh.getClass().getMethod("get"+name);
//								if(m.invoke(eh)!=null&&!"".equals(m.invoke(eh))){
//									rowData.put(name.toUpperCase(), m.invoke(eh));
//								}
//							}
//							ElasticSearchUtils.insert("cgs_ep_hotel","cgs",rowData,eh.getHnohotel());
//						}
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}

	}

	private static String rksj="";
	/*
	 * 获取oracle中ZHSJ_GNLK的数据
	 * 插入mysql数据库ep_hotel_person
	 */
//	public void findAndInsertHotelPersonData(Map<String,String> mapCon) {
//
//		String hotelIntoMySqlSwitch=mapCon.get("hotelIntoMySqlSwitch");
//
//		Map<String,String> map= new HashMap<>();
////		String rksj=epHotelPersonMapper.findLastRksj();
////		if(rksj!=null && !"".equals(rksj)){
////			map.put("rksj", rksj);
////		}
//		int j=0;
//		while(true){
//			try {
//				map.put("pageNum", j+"");
//				map.put("eachSearch", eachSearch+"");
//				Integer columnCount= insertElasticSearch(map);
//				if(columnCount<eachSearch){
//					break;
//				}
//				++j;
//			} catch (Exception e) {
//				break;
//			}
//		}
//
//
////		long ehplistCount=oEpHotelPersonMapper.findEpHotelPersonCount(map);
////		long ehplistCount=360753818;
////		int result=0;
////		if(ehplistCount>0){
////			int searchNum=(int) (ehplistCount%eachSearch==0?ehplistCount/eachSearch:ehplistCount/eachSearch+1);
////			for(int i=0;i<searchNum;i++){
////				List<EpHotelPerson> ehplist=null;
////				map.put("pageNum", i+"");
////				map.put("eachSearch", eachSearch+"");
//
////				Runnable rn=
//
////				ehplist =oEpHotelPersonMapper.findEpHotelPersonData(map);
//
////				for(EpHotelPerson e: ehplist){
////					e.setId(IDGenerator.getorderNo());
////				}
////				try{
////					if(ehplist.size()>0){
//////						if("2".equals(hotelIntoMySqlSwitch)){
//////							result=epHotelPersonMapper.insertEpHotelPersonList(ehplist);
//////							System.out.println("住宿存储到MYSQL");
//////						}
////						//存入ES
////						for(EpHotelPerson ehp:ehplist){
////							Map<String,Object> rowData = new HashMap<>();//声明Map
////							Field[] field = ehp.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
////							for(int j=0 ; j<field.length ; j++){     //遍历所有属性
////								String name = field[j].getName();    //获取属性的名字
////								name=name.substring(0, 1).toUpperCase() + name.substring(1);
////								Method m = ehp.getClass().getMethod("get"+name);
////								if(m.invoke(ehp)!=null&&!"".equals(m.invoke(ehp))){
////									rowData.put(name.toUpperCase(), m.invoke(ehp));
////								}
////							}
////							ElasticSearchUtils.insert("cgs_ep_hotel_person","cgs",rowData,ehp.getId());
////							System.out.println("住宿数据存储成功");
////						}
////					}
////				}catch(Exception e){
////					e.printStackTrace();
////				}
////			}
////		}
//
//	}
	@Override
	public void hotelDistanceTimeCron() {
		
		try{
			epHotelMapper.delHotelDistanceData();
			epHotelMapper.calculationAndStorage();
		}catch(Exception e){
			e.printStackTrace();
		}

		
	}


}
