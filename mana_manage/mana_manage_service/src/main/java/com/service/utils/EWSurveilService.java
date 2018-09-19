package com.service.utils;

import com.entity.suspect.EtCtrl;
import com.entity.suspect.EtWarning;

import java.util.List;

public interface EWSurveilService {

	public List<String> insertIntoWarning(List<EtWarning> etWList);

	public EtCtrl selectCtrlIdByICN(String bCtrlIdcardNum);

}
