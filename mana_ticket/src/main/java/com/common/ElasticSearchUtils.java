package com.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.common.utils.elasticsearch.TransportClientGenerator;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

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
        IndexResponse result = client.prepareIndex(index, type, id)
                .setSource(data)
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
     *
     * @param type
     * @param id
     * @param data
     * @return
     * @throws IOException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static ResponseEntity update(String index, String type, String id, Map<String, Object> data) throws IOException, ExecutionException, InterruptedException {
        /**
         * 封装update请求对象
         */
        UpdateRequest updateRequest = new UpdateRequest(index, type, id);

        XContentBuilder builder = XContentFactory.jsonBuilder()
                .startObject();
        data.forEach((k, v)-> {
            try {
                builder
                    .field(k, v.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        builder.endObject();

        updateRequest.doc(builder);

        /**
         * 发送update请求
         */
        UpdateResponse result = client.update(updateRequest).get();
        return new ResponseEntity(result.getResult().toString(), HttpStatus.OK);
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

}
