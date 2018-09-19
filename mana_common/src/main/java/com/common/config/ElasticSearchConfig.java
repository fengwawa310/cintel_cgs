package com.common.config;

import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.elasticsearch.xpack.client.PreBuiltXPackTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
public class ElasticSearchConfig {

    @Value("${elasticsearch.user}")
    private String user;
    @Value("${elasticsearch.password}")
    private String password;
    @Value("${elasticsearch.ip}")
    private String ip;
    @Value("${elasticsearch.cluster.name}")
    private String clusterName;

    @Bean
    public TransportClient client() throws UnknownHostException {
        Settings settings = Settings.builder().put("cluster.name", clusterName)
                .put("xpack.security.transport.ssl.enabled", false)
                .put("xpack.security.user", user + ":" + password)
                .put("client.transport.sniff", true).build();

        PreBuiltTransportClient client = new PreBuiltXPackTransportClient(settings);
        if(StringUtils.isNotBlank(ip)) {
            String[] ips = ip.split(",");
            for (String _ip : ips) {
                TransportAddress node = new TransportAddress(
                        InetAddress.getByName(_ip),
                        9300 //tcp端口
                );

                client.addTransportAddress(node);
            }
        }

        return client;
    }

}
