package com.mana.ticket.util;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.xpack.client.PreBuiltXPackTransportClient;

import com.mana.ticket.App;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

public class TransportClientGenerator {

    public static TransportClient generater() throws UnknownHostException {
        TransportClient client = null;
        Settings settings = Settings.builder().put("cluster.name", App.prop.get("es.clusterName").toString())
                .put("xpack.security.transport.ssl.enabled", false)
                .put("xpack.security.user", App.prop.get("es.user") + ":" + App.prop.get("es.password"))
                .put("client.transport.sniff", true).build();

        TransportAddress node = new TransportAddress(
                InetAddress.getByName(String.valueOf(App.prop.get("es.ip"))),
                Integer.parseInt(String.valueOf(App.prop.get("es.port"))) //tcp端口
        );
        client = new PreBuiltXPackTransportClient(settings);
        client.addTransportAddress(node);

        return client;
    }

}
