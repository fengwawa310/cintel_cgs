package com.service.task;

import java.util.Map;

public interface OracleService {
//    List<NiandudaocaojihuaEntity> find();

	public void findBlDataAndinsert(Map<String,String> map);

	public void findHotelDataAndInsert(Map<String,String> map);

	public void hotelDistanceTimeCron();

}
