package com.common.utils;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.common.consts.Global;
import com.common.utils.elasticsearch.TransportClientGenerator;
import com.ticket.model.CallRecord;

/**
 * elasticsearch工具类
 */
public class ElasticSearchUtils {
    private static Logger logger = LoggerFactory.getLogger("ElasticSearchUtils");
    private static TransportClient client;

    static{
        try {
            client = TransportClientGenerator.generater();
        } catch (UnknownHostException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     *
     * @param type 类型
     * @param data 数据
     * @param id 指定文档id
     * @return 返回 id
     */
    public static ResponseEntity insert(String index, String type, Map<String, Object> data, String id) {
        Map<String, Object> map = TimeUtil.toDateMap(data);
        IndexResponse result = client.prepareIndex(index, type, id)
                .setSource(map)
                .get();

        return new ResponseEntity(result.getId(), HttpStatus.OK);
    }

    /**
     *
     * @param type 类型
     * @param data 数据
     */
    public static ResponseEntity insert(String index, String type, Map<String, Object> data) {
        IndexResponse result = client.prepareIndex(index, type)
                .setSource(data)
                .get();

        return new ResponseEntity(result.getId(), HttpStatus.OK);
    }

    /**
     *
     * @param type
     * @param builder
     * @param id
     * @return 返回id
     */
    public static ResponseEntity insert(String index, String type, XContentBuilder builder, String id) {
        IndexResponse result = client.prepareIndex(index, type, id)
                .setSource(builder)
                .get();
        return new ResponseEntity(result.getId(), HttpStatus.OK);
    }

    /**
     * 随机生成文档id
     * @param type
     * @param builder
     * @return 返回id
     */
    public static ResponseEntity insert(String index, String type, XContentBuilder builder) {
        IndexResponse result = client.prepareIndex(index, type)
                .setSource(builder)
                .get();
        return new ResponseEntity(result.getId(), HttpStatus.OK);
    }

    /**
     * 根据id查询
     * @param type
     * @param id
     * @return
     */
    public static ResponseEntity get(String index, String type, String id) {
        GetResponse result = client.prepareGet(index, type, id)
                .get();
        if(!result.isExists()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(result.getSource(), HttpStatus.OK);
    }



    /**
     * 根据id删除文档
     * @param type
     * @param id
     * @return
     */
    public static ResponseEntity delete(String index, String type, String id) {
        DeleteResponse result = client.prepareDelete(index, type, id)
                .get();
        return new ResponseEntity(result.getId(), HttpStatus.OK);
    }

    /**
     * 闭区间查询
     * @param type
     * @param format
     * @param min
     * @param max
     * @param <T>
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static <T> List<T> rangeEquals(String index, String type, String field, String format, String min, String max) throws ExecutionException, InterruptedException {
        SearchRequestBuilder searchBuilder = client.prepareSearch(index)
                .setTypes(type)
                .setQuery(QueryBuilders
                        .rangeQuery(field)
                        .format(format)
                        .gte(min)
                        .lte(max));
        SearchResponse result = searchBuilder.execute().get();
        SearchHit[] hits = result.getHits().getHits();

        List<T> returnList = new ArrayList<>();
        for (SearchHit hit : hits) {
            returnList.add(JSON.parseObject(hit.getSourceAsString(), new TypeReference<T>(){}));
        }

        return returnList;
    }

    /**
     * 开区间查询
     * @param index
     * @param type
     * @param field
     * @param format
     * @param min
     * @param max
     * @param <T>
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static <T> List<T> range(String index, String type, String field, String format, String min, String max) throws ExecutionException, InterruptedException {
        SearchRequestBuilder searchBuilder = client.prepareSearch(index)
                .setTypes(type)
                .setQuery(QueryBuilders
                        .rangeQuery(field)
                        .format(format)
                        .gt(min)
                        .lt(max));
        SearchResponse result = searchBuilder.execute().get();
        SearchHit[] hits = result.getHits().getHits();

        List<T> returnList = new ArrayList<>();
        for (SearchHit hit : hits) {
            returnList.add(JSON.parseObject(hit.getSourceAsString(), new TypeReference<T>(){}));
        }

        return returnList;
    }

    public static SearchResponse getSearch(String param,List<String> field, int page, int pagesize, String index, String type ){
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        StringBuilder sb=new StringBuilder();
        int j=0;
        for (int i=0;i<field.size();i++) {
            j++;
            if (j<field.size()){
                sb.append('"').append(field.get(i)).append('"').append(",");
            }else {
                sb.append('"').append(field.get(i)).append('"');
            }
        }
        String s = sb.toString();
//        String join = String.join(",", field);
        System.out.println("看字段..."+s);
        System.out.println("看参数..."+param);
        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(param, s);
//        QueryBuilder queryBuilder = QueryBuilders.multiMatchQuery(param,builder.toString());//搜索name中或interest中包含有music的文档（必须与music一致）
        SearchRequestBuilder searchRequestBuilder = client.prepareSearch(index).setTypes(type)
                .setQuery(multiMatchQueryBuilder);
//                .addHighlightedField("*")/*星号表示在所有字段都高亮*/.setHighlighterRequireFieldMatch(false)//配置高亮显示搜索结果
//                .setHighlighterPreTags("<高亮前缀标签>").setHighlighterPostTags("<高亮后缀标签>");//配置高亮显示搜索结果

        searchRequestBuilder = searchRequestBuilder.addAggregation(AggregationBuilders.terms("agg1(聚类返回时根据此key获取聚类结果)")
                .size(1000)/*返回1000条聚类结果*/.field("要在文档中聚类的字段，如果是嵌套的则用点连接父子字段，如【person.company.name】"));

        SearchResponse searchResponse = searchRequestBuilder.setFrom((page - 1) * pagesize)//分页起始位置（跳过开始的n个）
                .setSize(pagesize)//本次返回的文档数量
                .execute().actionGet();//执行搜索
        return searchResponse;
    }

    public static SearchResponse getSearch(String param, int page, int pagesize, String index, String type, String... fields){
        SearchRequestBuilder searchRequestBuilder = client.prepareSearch(index).setTypes(type)
                .setQuery(QueryBuilders.multiMatchQuery(param, fields));
//                .addHighlightedField("*")/*星号表示在所有字段都高亮*/.setHighlighterRequireFieldMatch(false)//配置高亮显示搜索结果
//                .setHighlighterPreTags("<高亮前缀标签>").setHighlighterPostTags("<高亮后缀标签>");//配置高亮显示搜索结果

        searchRequestBuilder = searchRequestBuilder.addAggregation(AggregationBuilders.terms("agg1(聚类返回时根据此key获取聚类结果)")
                .size(1000)/*返回1000条聚类结果*/.field("要在文档中聚类的字段，如果是嵌套的则用点连接父子字段，如【person.company.name】"));

        SearchResponse searchResponse = searchRequestBuilder.setFrom((page - 1) * pagesize)//分页起始位置（跳过开始的n个）
                .setSize(pagesize)//本次返回的文档数量
                .execute().actionGet();//执行搜索
        return searchResponse;
    }

    public static SearchResponse getSearchs(String index, String type,QueryBuilder query){
        SearchResponse response = client.prepareSearch(index).setTypes(type).setQuery(query).setSize(10000).execute().actionGet();
        return response;
    }

    public static List<HashMap<String, Object>> getSearchList(String index, String type,QueryBuilder query){
        SearchResponse response = client.prepareSearch(index).setTypes(type).setQuery(query)
                .setSize(1000)
                //这个游标维持多长时间
                .setScroll(TimeValue.timeValueMinutes(8))
                .execute().actionGet();
        List<HashMap<String, Object>> list = new LinkedList<>();
        while(true){
            SearchHits hits = response.getHits();
            SearchHit[] hits1 = hits.getHits();
            int length = hits1.length;
            for(int i=0;i<length;i++){
                list.add((HashMap<String, Object>) hits1[i].getSourceAsMap());
            }
//            for (SearchHit hit : response.getHits()) {
//                list.add((HashMap<String, Object>) hit.getSourceAsMap());
//            }
            response = client.prepareSearchScroll(response.getScrollId())
                    .setScroll(TimeValue.timeValueMinutes(8))
                    .execute().actionGet();
            if (response.getHits().getHits().length == 0) {
                break;
            }
        }
        return list;
    }
    
    /**
     * 通联人、共同关系人、话单统计功能图形页面，弹框展示话单详情列表
     * @param index
     * @param type
     * @param query
     * @return
     */
    public static SearchResponse getCallDetailsList(int page, int pagesize,String index, String type,QueryBuilder query){
//    	SearchResponse searchResponse = client.prepareSearch(index).setTypes(type).setQuery(query)
//    			.setFrom((page - 1) * pagesize)
//                .setSize(pagesize)
//    			//这个游标维持多长时间
//    			.setScroll(TimeValue.timeValueMinutes(8))
//    			.execute().actionGet();
    	
    	SearchRequestBuilder searchRequestBuilder = client.prepareSearch(index).setTypes(type)
                .setQuery(query);

        searchRequestBuilder = searchRequestBuilder.addAggregation(AggregationBuilders.terms("agg1(聚类返回时根据此key获取聚类结果)")
                .size(pagesize)
                .field("要在文档中聚类的字段，如果是嵌套的则用点连接父子字段，如【person.company.name】"));

        SearchResponse searchResponse = searchRequestBuilder.setFrom((page - 1) * pagesize)//分页起始位置（跳过开始的n个）
                .setSize(pagesize).addSort("START_TIME", SortOrder.DESC)//本次返回的文档数量
                .execute().actionGet();//执行搜索
        return searchResponse;
    }
    
    
    public static void bulkInsertTicke(List<CallRecord> callRecords) {
    	if(callRecords == null || callRecords.isEmpty())
    		return;
    	
    	BulkRequestBuilder bulkRequest=client.prepareBulk();
    	String ticketIndex = "cgs_call_record";
    	String ticketType = "cgs";
    	String ticketId;
		try {
			for (int i = 0; i < callRecords.size(); i++) {
				CallRecord callRecord = callRecords.get(i);
				if (callRecord != null && StringUtils.isNotBlank(callRecord.getCalled())) {
					
					ticketId = MD5Util.getMD5(callRecord.getCalling()+callRecord.getCalled()+callRecord.getCalling_type()+callRecord.getStart_time());
					
					XContentBuilder xcb = callRecord.getXContentBuilder();
					
					//upsert 如果文档不存在则创建新的索引
					IndexRequest indexRequest = new IndexRequest(ticketIndex, ticketType, ticketId).source(xcb);

//					System.out.println(callRecord.getXContentBuilder().string());
					
		            UpdateRequest uRequest2 = new UpdateRequest(ticketIndex, ticketType, ticketId).doc(xcb).upsert(indexRequest);

					bulkRequest.add(uRequest2);

					// ElasticSearchUtils.insert(
					// App.prop.get("ticket.elasticsearch.index").toString()
					// , App.prop.get("ticket.elasticsearch.type").toString()
					// , callRecord.getXContentBuilder());
					logger.debug("主叫号码为" + callRecord.getCalling() + "插入成功!");
				}
			}
			//
			bulkRequest.execute().get();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
    }



}