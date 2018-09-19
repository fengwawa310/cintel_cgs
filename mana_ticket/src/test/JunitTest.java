import com.common.ElasticSearchUtils;
import com.google.common.collect.Maps;
import com.service.ElasticSearchHelper;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.elasticsearch.xpack.client.PreBuiltXPackTransportClient;
import org.junit.jupiter.api.BeforeAll;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.TransportClientGenerator;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class JunitTest {

    @Test
    public void calledTop10() throws UnknownHostException {
//        TransportClient client = TransportClientGenerator.generater();
//
//        ElasticSearchHelper elasticSearchHelper = new ElasticSearchHelper();
//        Map<String, Long> result = elasticSearchHelper.frequencyOrCallingTimeCount(
//                "phonebooks", "callrecord"
//                , "calling", "1554209",
//                "startTime", "yyyy-MM-dd HH:mm:ss", "2018-03-09 00:00:33", "2018-03-09 21:13:33"
//                , "test", "called"
//                , client);
//
//        result.forEach((k, v) -> {
//            System.err.println(k + ":" + v);
//        });
    }

    @Test
    public void testInsert() throws UnknownHostException {
        TransportClient client = TransportClientGenerator.generater();

        Map<String, Object> objectObjectHashMap = Maps.newHashMap();
        objectObjectHashMap.put("calling", "1554209");
        objectObjectHashMap.put("called", "15140730781");
        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        objectObjectHashMap.put("startTime", currentTime);
        objectObjectHashMap.put("callingTime", 279);
        objectObjectHashMap.put("callingPlace", "测试1");
        ElasticSearchUtils.insert("phonebooks", "callrecord", objectObjectHashMap, "556");
    }

    @Test
    public void testCallingTimeTop10() throws ExecutionException, InterruptedException {
        ElasticSearchHelper.callingTimeCount("phonebooks", "callrecord", "15542091867");
    }

}
