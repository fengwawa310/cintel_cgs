package com.service.ticket;

import java.lang.reflect.Method;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.bucket.histogram.Histogram;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.tophits.TopHits;
import org.elasticsearch.search.aggregations.metrics.tophits.TopHitsAggregationBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.common.utils.elasticsearch.TransportClientGenerator;
import com.entity.ticket.ESTicket;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.vo.ticket.TicketVo;

/**
 * 封装为static方法是为了便于测试，测试完成需要去掉static
 */
public class ElasticSearchHelper {
	private static Logger logger = LoggerFactory.getLogger("ElasticSearchUtils");
	private static TransportClient client;

	static {
		try {
			client = TransportClientGenerator.generater();
		} catch (UnknownHostException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 通话时长top10统计
	 * 
	 * @param index
	 * @param type
	 * @param number
	 * @return
	 */
	public static List<ESTicket> callingTimeCount(String index, String type, String rangeField, String format,
			String startTime, String endTime // range
			, String number,String prevNumber) {
		
		BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery().must(QueryBuilders.multiMatchQuery(number, "CALLING", "CALLED"))
				.must(QueryBuilders.matchPhraseQuery("CALLING_TYPE", "叫"))
				.mustNot(QueryBuilders.multiMatchQuery("10001", "CALLING", "CALLED"))
				.filter(QueryBuilders.rangeQuery(rangeField).format(format).gte(startTime).lte(endTime));
		
		if(StringUtils.isNotBlank(prevNumber)){// 过滤掉父级号码
			queryBuilder.mustNot(QueryBuilders.multiMatchQuery(prevNumber, "CALLING", "CALLED"));
		}
		
		SearchRequestBuilder searchRequestBuilder = client.prepareSearch(index).setTypes(type)
				.setQuery(queryBuilder)
				.addSort("CALLING_TIME", SortOrder.DESC).setSize(10);

		SearchResponse result = searchRequestBuilder.execute().actionGet();
		SearchHit[] hits = result.getHits().getHits();
		List<ESTicket> resultList = Lists.newArrayList();
		for (SearchHit hit : hits) {
			Map<String, Object> sourceAsMap = hit.getSourceAsMap();
			ESTicket esTicket = new ESTicket();
			Method[] methods = esTicket.getClass().getMethods();
			for (Method method : methods) {
				if (method.getName().startsWith("set")) {
					try {
						String methodName = StringUtils.uncapitalize(method.getName().substring(3));
						method.invoke(esTicket, sourceAsMap.get(methodName.toUpperCase()));
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
					}
				}
			}

			resultList.add(esTicket);
		}

		return resultList;
	}

	/**
	 * 频次top10统计
	 * 
	 * @param index
	 * @param type
	 * @param matchField
	 * @param text
	 * @param termsName
	 * @param termsField
	 * @return 有序Map key为号码，value为doc_count
	 */
	public static Map<String, Long> frequencyCount(String index, String type, String matchField, String text // match_phrase
			, String rangeField, String format, String startTime, String endTime // range
			, String termsName, String termsField,String prevNumber) {
		
		// 设置query信息
		BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery().must(QueryBuilders.matchPhraseQuery(matchField, text))
				.must(QueryBuilders.matchPhraseQuery("CALLING_TYPE", "叫"))
				.filter(QueryBuilders.rangeQuery(rangeField).format(format).gte(startTime).lte(endTime))
				.mustNot(QueryBuilders.matchPhraseQuery(termsField, "10001"));// 过滤掉特殊号码
		
		
		if(StringUtils.isNotBlank(prevNumber)){// 过滤掉父级号码
			queryBuilder.mustNot(QueryBuilders.matchPhraseQuery(termsField, prevNumber));
		}

		SearchRequestBuilder searchRequestBuilder = client.prepareSearch(index).setTypes(type)
				.setQuery(queryBuilder);
		// 设置分组信息
		TermsAggregationBuilder builder = AggregationBuilders.terms(termsName).field(termsField) // 分组的字段
				.order(BucketOrder.count(false)) // 降序
				.size(10);

		// 添加分组信息
		searchRequestBuilder.addAggregation(builder);
		// 执行搜索
		SearchResponse result = searchRequestBuilder.execute().actionGet();

		Map<String, Aggregation> asMap = result.getAggregations().getAsMap();

		Aggregation group_by_course = asMap.get(termsName);
		StringTerms s = (StringTerms) group_by_course;
		List<StringTerms.Bucket> buckets = s.getBuckets();

		LinkedHashMap<String, Long> resultMap = Maps.newLinkedHashMap();
		buckets.forEach(bucket -> {
			resultMap.put(bucket.getKeyAsString(), bucket.getDocCount());
		});

		return resultMap;
	}

	/**
	 * 合并两个top10,取一个top10
	 * 
	 * @param map1
	 * @param map2
	 * @return
	 */
	public static Map<String, Long> combineTwoMaps(Map<String, Long> map1, Map<String, Long> map2) {
		Map<String, Long> resultMap = Maps.newHashMap();

		resultMap.putAll(map1);
		resultMap.putAll(map2);

		Map<String, Long> sortedMap = Maps.newLinkedHashMap();
		resultMap.entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().reversed())
				.forEachOrdered(e -> sortedMap.put(e.getKey(), e.getValue()));

		Map<String, Long> finalMap = Maps.newLinkedHashMap();
		sortedMap.forEach((k, v) -> {
			if (finalMap.size() < 10) {
				finalMap.put(k, v);
			}
		});

		return finalMap;
	}

	/**
	 * 将top10map的数据添加到JsonArray中
	 * 
	 * @param map
	 * @param list
	 * @param parentNumber
	 */
	public static void addMapToArray(Map<String, Long> map, List<Map<String, Object>> list, String parentNumber) {
		Integer id = 0;
		Set<Entry<String, Long>> entrySet = map.entrySet();
		for (Entry<String, Long> entry : entrySet) {
			Map<String, Object> map1 = new HashMap<>();
			map1.put("id", id++);
			map1.put("phoneNo", entry.getKey());
			map1.put("parentNo", parentNumber);
			map1.put("counts", entry.getValue());
			map1.put("name", "--");
			list.add(map1);
		}
	}

	/**
	 * 话单对象转json
	 * 
	 * @param esTicket
	 * @param parentNumber
	 * @return
	 */
	public static JSONObject esTicketToJSON(ESTicket esTicket, String parentNumber, Integer id) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", id);
		if (!esTicket.getCalling().equalsIgnoreCase(parentNumber)) {
			jsonObject.put("phoneNo", esTicket.getCalling());
		} else {
			jsonObject.put("phoneNo", esTicket.getCalled());
		}

		jsonObject.put("parentNo", parentNumber);
		jsonObject.put("counts", esTicket.getCalling_Time());
		jsonObject.put("name", "--");

		return jsonObject;
	}

	/**
	 * 添加首结点
	 * 
	 * @param ticketVo
	 * @param list
	 */
	public static void addHeadElementToArray(TicketVo ticketVo, List<Map<String, Object>> list) {
		Map<String, Object> jsonObject = new HashMap<>();
		jsonObject.put("phoneNo", ticketVo.getPhoneNumber());
		jsonObject.put("parentNo", "");
		jsonObject.put("counts", "");
		jsonObject.put("name", "--");

		list.add(jsonObject);
	}

	/**
	 * 获取合适的时间格式
	 * 
	 * @param ticketVo
	 * @return
	 */
	public static void formatTime(TicketVo ticketVo) {
		if (ticketVo.getStartTime().trim().length() == 10) {
			ticketVo.setStartTime(ticketVo.getStartTime() + " 00:00:00");
		}

		if (ticketVo.getFinishTime().trim().length() == 10) {
			ticketVo.setFinishTime(ticketVo.getFinishTime() + " 23:59:59");
		}
	}

	/**
	 * 按天排序取一天的最早两条和最后两条
	 * @param param
	 * @param fieldList
	 * @param start
	 * @param length
	 * @param index
	 * @param type
	 * @return
	 * @throws InterruptedException
	 * @throws ExecutionException
	 * @throws ParseException
	 */
    public static SearchResponse abnormalCallQuery(String param, List<String> fieldList, Integer start, Integer length,
            String index, String type) throws InterruptedException, ExecutionException, ParseException {
        //截取数量
        int num=2;
        // 设置query信息
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery().
                must(QueryBuilders.multiMatchQuery(param, fieldList.get(0), fieldList.get(1))).
                must(QueryBuilders.matchPhraseQuery("CALLING_TYPE", "叫"));
        
        SearchRequestBuilder searchRequestBuilder = client.prepareSearch(index).setTypes(type).setQuery(queryBuilder);
        // 设置分组信息
        DateHistogramAggregationBuilder dateHistogram = 
                AggregationBuilders.dateHistogram("group_day").field("START_TIME").
                dateHistogramInterval(DateHistogramInterval.DAY).format("yyyy-MM-dd").minDocCount(1).order(BucketOrder.key(false));
        
        TopHitsAggregationBuilder top = AggregationBuilders.topHits("top").sort("START_TIME", SortOrder.DESC).size(num);
        TopHitsAggregationBuilder bottom = AggregationBuilders.topHits("bottom").sort("START_TIME", SortOrder.ASC).size(num);

        dateHistogram.subAggregation(bottom).subAggregation(top);
        // 添加分组信息
        SearchRequestBuilder addAggregation = searchRequestBuilder.addAggregation(dateHistogram);
        // 执行搜索
        SearchResponse searchResponse = searchRequestBuilder.execute().get();
        
        Aggregation aggregation = searchResponse.getAggregations().get("group_day");
        Histogram histogram=(Histogram)aggregation;
        
        Map<String, Long> resultMap = Maps.newHashMap();
        ArrayList<String> ids = new ArrayList<String>();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String id =null;
        String start_time=null;
        for (Histogram.Bucket entry : histogram.getBuckets()) {
           // Long key = (Long) entry.getKey(); // bucket key
            Aggregations aggregations = entry.getAggregations();
            //long docCount = entry.getDocCount(); // Doc count
            TopHits topRs = aggregations.get("top");
            TopHits bottomRs = aggregations.get("bottom");
            for (SearchHit hit : topRs.getHits().getHits()) {
                id = hit.getId();
                start_time=(String)hit.getSourceAsMap().get("START_TIME");
                resultMap.put(id, sf.parse(start_time).getTime());
            }
            for (SearchHit hit : bottomRs.getHits().getHits()) {
                id = hit.getId();
                start_time=(String)hit.getSourceAsMap().get("START_TIME");
                resultMap.put(id, sf.parse(start_time).getTime());
            }
        }
      //排序
        resultMap.entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue())
        .forEachOrdered(e -> ids.add(e.getKey()));
        int startLocation=(start-1)*length;
        int endLocation=startLocation+10;
        int total=ids.size();
        if(endLocation>total){
            endLocation=total;
        }
        List<String> pageIds= ids.subList(startLocation, endLocation);
        
        SearchRequestBuilder searchRequestBuilder2 = client.prepareSearch(index).setTypes(type).setQuery(QueryBuilders.idsQuery().addIds(pageIds.get(0),
                pageIds.get(1),
                pageIds.get(2),
                pageIds.get(3),
                pageIds.get(4),
                pageIds.get(5),
                pageIds.get(6),
                pageIds.get(7),
                pageIds.get(8),
                pageIds.get(9))).addSort("START_TIME", SortOrder.DESC);//addSort(SortBuilders.fieldSort("START_TIME").order(SortOrder.DESC));
                
                /*setIndices(
                pageIds.get(0),
                pageIds.get(1),
                pageIds.get(2),
                pageIds.get(3),
                pageIds.get(4),
                pageIds.get(5),
                pageIds.get(6),
                pageIds.get(7),
                pageIds.get(8),
                pageIds.get(9));*/
        System.out.println(searchRequestBuilder2.toString());
        searchResponse = searchRequestBuilder2.execute().actionGet();//执行搜索
        
        return searchResponse;
    }
    /**
     * 
     * @param index
     * @param type
     * @param query
     * @return
     */
    public static Set<ESTicket> getSearchList(String index, String type,QueryBuilder query){
        SearchResponse response = client.prepareSearch(index).setTypes(type).setQuery(query)
                .setSize(1000)
                //这个游标维持多长时间
                .setScroll(TimeValue.timeValueMinutes(8))
                .execute().actionGet();
        HashSet<ESTicket>  list= new HashSet<ESTicket>();
        long begin = System.currentTimeMillis();
        while(true){
            SearchHits hits = response.getHits();
            SearchHit[] hits1 = hits.getHits();
            int length = hits1.length;
            System.out.println("查出多少条："+length);
            for(int i=0;i<length;i++){
                Map<String, Object> sourceAsMap =hits1[i].getSourceAsMap();
                ESTicket esTicket = new ESTicket();
                Method[] methods = esTicket.getClass().getMethods();
                for (Method method : methods) {
                    if (method.getName().startsWith("set")) {
                        try {
                            String methodName = StringUtils.uncapitalize(method.getName().substring(3));
                            Object object =sourceAsMap.get(methodName.toUpperCase());;
                            if(methodName.equalsIgnoreCase("calling")||methodName.equalsIgnoreCase("called")){
                                String a=(String)sourceAsMap.get(methodName.toUpperCase());
                                if(a.length()!=11){
                                }
                               object=((String)sourceAsMap.get(methodName.toUpperCase())).replaceAll("[^0-9]+", "");
                            }
                            method.invoke(esTicket,object);
                        } catch (Exception e) {
                            logger.error(e.getMessage(), e);
                        }
                    }
                }
                list.add(esTicket);
            }
            System.out.println(list);
            response = client.prepareSearchScroll(response.getScrollId())
                    .setScroll(TimeValue.timeValueMinutes(8))
                    .execute().actionGet();
            if (response.getHits().getHits().length == 0) {
                break;
            }
        }
        System.out.println("time "+(System.currentTimeMillis()-begin));
        System.out.println("去除后剩余："+list.size());
        return list;
    }
   /* 
    public static void test(){
        SearchRequestBuilder searchRequestBuilder = client.prepareSearch("cgs_call_record").setTypes("cgs").setQuery(QueryBuilders.idsQuery().addIds("20dfc28236a87297e24867ee716a1694", 
                "b7abcc543d15f2b6962cedcac04f318b", "866e05853004300e547741644cee87f1", 
                "67639ee57a70c867f3e542770cff5004", "c6981fc497a03c1d85b646d1da6f946f", "c50c3eeabbaa80dbfe3aba25d01d4a7b", 
                "429c280d6a0ecd8127ac25dd2d04a63b", "ace888799aea0df320cf2d75d35cdd2f", 
                "0007fc4d620096d1fea97676552e2064", "58cf19f6179db4cf7f7f393437870e69"));
                
        SearchResponse actionGet = searchRequestBuilder.execute().actionGet();//执行搜索
    }*/
}
