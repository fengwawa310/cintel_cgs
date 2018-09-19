package com.common.utils.elasticsearch;

import com.common.consts.Global;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.xpack.client.PreBuiltXPackTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class TransportClientGenerator {

	public static TransportClient generater() throws UnknownHostException {
		String ipstr=Global.getEs("es.ip");
		//tcp端口
		int port = Integer.parseInt(Global.getEs("es.port"));
		TransportClient client = null;
        Settings settings = Settings.builder().put("cluster.name", Global.getEs("es.clusterName"))
                .put("xpack.security.transport.ssl.enabled", false)
                .put("xpack.security.user", Global.getEs("es.user") + ":" +Global.getEs("es.password"))
                .put("client.transport.sniff", true).build();
        client = new PreBuiltXPackTransportClient(settings);
        if(StringUtils.isNoneBlank(ipstr)) {
        	if(ipstr.contains(",")) {
        		String[] ips = ipstr.split(",");
        		for (String ip : ips) {
        	        TransportAddress node = new TransportAddress(
        	                InetAddress.getByName(ip),
							port //tcp端口
        	        );

        	        client.addTransportAddress(node);
				}
        	}
        }
        return client;
    }

}
