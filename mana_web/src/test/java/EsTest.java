import com.common.utils.elasticsearch.TransportClientGenerator;
import com.entity.caseInfo.EtCase;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * @author admin
 * @create 2018-03-12 13:50
 **/
public class EsTest {
    private static TransportClient client;
    static{
        try {
            client = TransportClientGenerator.generater();
        } catch (UnknownHostException e) {
//            logger.error(e.getMessage(), e);
        }
    }
    /**
     * 添加警情数据
     * @throws IOException
     */
    @Test
    public void testIndex1() throws Exception {
        ArrayList<EtCase> etCases = new ArrayList<>();
        EtCase e = new EtCase();
        e.setId("111111");
        e.setCaseNo("111111");
        e.setCaseDesc("我只是一个测试");
        etCases.add(e);
        BulkRequestBuilder bulkRequest = client.prepareBulk();
        for(EtCase etCase: etCases) {
            //指定id，使用es自己id
            //bulkRequest.add(client.prepareIndex("et_case", "cgs","1")
            //不指定id，使用es默认id
            bulkRequest.add(client.prepareIndex("et_case", "cgs")
                    .setSource(XContentFactory.jsonBuilder()
                            .startObject()
                            .field("ID", etCase.getId())
                            .field("CASE_NO",etCase.getCaseNo() )
                            .field("ALARM_NO", etCase.getAlarmNo())
                            .field("CASE_TITLE", etCase.getCaseTitle())
                            .field("CASE_STATE", etCase.getCaseState())
                            .field("CASE_CLASS", etCase.getCaseClass())
                            .field("CASE_SOURCE", etCase.getCaseSource())
                            .field("SOURCE_TYPE", etCase.getSourceType())
                            .field("CASE_REMARKS", etCase.getCaseRemarks())
                            .field("CASE_DESC", etCase.getCaseDesc())
                            .field("DETAL_ADDRESS", etCase.getDetalAddress())
                            .field("HAPPEN_TIME_UP", etCase.getHappenTimeUp())
                            .field("HAPPEN_TIME_DOWN", etCase.getHappenTimeDown())
                            .field("ZONE_CODE", etCase.getZoneCode())
                            .field("ZONE_NAME", etCase.getZoneName())
                            .field("ACCEPT_UNIT_CODE", etCase.getAcceptUnitCode())
                            .field("ACCEPT_UNIT_NAME", etCase.getAcceptUnitName())
                            .field("ACCEPT_TIME", etCase.getAcceptTime())
                            .field("HOST_UNIT_CODE", etCase.getHostUnitCode())
                            .field("HOST_UNIT_NAME", etCase.getHostUnitName())
                            .field("SPONSOR_CODE", etCase.getSponsorCode())
                            .field("SPONSOR_NAME", etCase.getSponsorName())
                            .field("SPONSOR_PHONE", etCase.getSponsorPhone())
                            .field("ASSIST_UNIT_CODE", etCase.getAssistUnitCode())
                            .field("ASSIST_UNIT_NAME", etCase.getAssistUnitName())
                            .field("ASSISTANT_CODE", etCase.getAssistantCode())
                            .field("ASSISTANT_NAME", etCase.getAssistantName())
                            .field("ASSISTANT_PHONE", etCase.getAssistantPhone())
                            .field("INPUT_UNIT_CODE", etCase.getInputUnitCode())
                            .field("INPUT_UNIT_NAME", etCase.getInputUnitName())
                            .field("INPUTER_CODE", etCase.getInputerCode())
                            .field("INPUTER_NAME", etCase.getInputerName())
                            .field("IS_FOLLOW", etCase.getIsFollow())
                            .field("IS_APPROVAL", etCase.getIsApproval())
                            .field("IS_ARCHIVE", etCase.getIsArchive())
                            .field("ARCHIVE_DESC",etCase.getArchiveDesc() )
                            .field("IS_ABANDON", etCase.getIsAbandon())
                            .field("ABANDON_DESC", etCase.getAbandonDesc())
                            .field("CREAT_TIME", etCase.getCreatTime())
                            .field("MODIFY_TIME", etCase.getModifyTime())
                            .field("RESERVE_A", etCase.getReserveA())
                            .field("RESERVE_B", etCase.getReserveB())
                            .field("RESERVE_C", etCase.getReserveC())
                            .field("RESERVE_D", etCase.getReserveD())
                            .endObject()));
        }
        BulkResponse response = bulkRequest.get();
        System.out.println(response.getItems());

    }

    @Test
    public void testsearch1() throws Exception {
//        String searchString = "我只是一个测试";
        String searchString = "备注市局";
        SearchResponse response = client.prepareSearch("et_case","phonebook")
                .setTypes("cgs","records")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.multiMatchQuery(searchString,
                        "ID","CASE_NO","ALARM_NO","CASE_TITLE","CASE_STATE","CASE_CLASS","CASE_SOURCE","SOURCE_TYPE","CASE_REMARKS",
                        "CASE_DESC","DETAL_ADDRESS",/*"HAPPEN_TIME_UP","HAPPEN_TIME_DOWN",*/"ZONE_CODE","ZONE_NAME","ACCEPT_UNIT_CODE",
                        /*"ACCEPT_UNIT_NAME","ACCEPT_TIME",*/"HOST_UNIT_CODE","HOST_UNIT_NAME","SPONSOR_CODE","SPONSOR_NAME","SPONSOR_PHONE",
                        "ASSIST_UNIT_CODE","ASSIST_UNIT_NAME","ASSISTANT_CODE","ASSISTANT_NAME","ASSISTANT_PHONE","INPUT_UNIT_CODE",
                        /*"INPUT_UNIT_NAME",*/"INPUTER_CODE",/*"INPUTER_NAME",*/"IS_FOLLOW","IS_APPROVAL","IS_ARCHIVE","ARCHIVE_DESC","IS_ABANDON",
                        "ABANDON_DESC",/*"CREAT_TIME","MODIFY_TIME",*/"RESERVE_A","RESERVE_B","RESERVE_C","RESERVE_D"))                 // Query
                .get();
        System.out.println(response);
        System.out.println(response.getHits());
    }

}
