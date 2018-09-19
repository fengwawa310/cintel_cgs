package com.service.ticket;

import com.common.utils.PageVO;
import com.entity.ticket.ESTicket;
import com.vo.ticket.TicketVo;

import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHits;

/**
 * 话单业务接口
 */
public interface EsTicketService {

    /**
     * 频次top100统计
     * @param ticketVo
     */
    public List<Map<String, Object>> frequencyCount(TicketVo ticketVo);

    /**
     * 时长top10统计
     * @param ticketVo
     */
    public List<Map<String, Object>> callingTimeCount(TicketVo ticketVo) throws ExecutionException, InterruptedException;
    /**
     * 话单查询
     * @param param
     * @param fieldList
     * @param pageVO
     * @return
     */
    public SearchHits ticketQuery(String param,List<String> fieldList,PageVO pageVO);
  
   
    /**
     * 异常通话查询
     * @param param
     * @param list
     * @param pageVO
     * @return
     */
    public SearchHits abnormalCallQuery(String param, List<String> list, PageVO pageVO);

    public JSONObject mutualContactsAnalyze(HashMap<String, Object> paramMap);



}
