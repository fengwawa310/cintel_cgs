package utils;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.xpack.client.PreBuiltXPackTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class TransportClientGenerator {

    public static TransportClient generater() throws UnknownHostException {
        TransportClient client = null;
        Settings settings = Settings.builder().put("cluster.name", "my-application")
                .put("xpack.security.transport.ssl.enabled", false)
                .put("xpack.security.user", "elastic:bgavUKQZ5q5XtYwPfj9N")
                .put("client.transport.sniff", true).build();

        TransportAddress node = new TransportAddress(
                InetAddress.getByName("192.168.2.102"),
                9300 //tcp端口
        );
//        TransportAddress node1 = new TransportAddress(
//                InetAddress.getByName("192.168.2.103"),
//                9300 //tcp端口
//        );
//        TransportAddress node2 = new TransportAddress(
//                InetAddress.getByName("192.168.2.217"),
//                9300 //tcp端口
//        );
        client = new PreBuiltXPackTransportClient(settings);
        client.addTransportAddress(node);
//        client.addTransportAddress(node1);
//        client.addTransportAddress(node2);

        return client;
    }

}
