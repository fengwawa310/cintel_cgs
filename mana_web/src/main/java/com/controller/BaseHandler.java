package com.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.common.enums.AjaxRes;



public class BaseHandler {
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	protected String servciesCode;
	
	protected String serviceUrl;
	
	public AjaxRes getAjaxRes(){
		return new AjaxRes();
	}

	public String getServciesCode() {
		return servciesCode;
	}

	public void setServciesCode(String servciesCode) {
		this.servciesCode = servciesCode;
	}

	public String getServiceUrl() {
		return serviceUrl;
	}

	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}
	
}
