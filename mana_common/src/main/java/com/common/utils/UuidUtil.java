package com.common.utils;

import java.util.UUID;


public class UuidUtil {

	//生产UUID
	public static String uuid(){
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.replace("-", "");
		return uuid;
	}
	
}
