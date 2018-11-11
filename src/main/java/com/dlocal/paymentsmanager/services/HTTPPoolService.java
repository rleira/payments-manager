package com.dlocal.paymentsmanager.services;

import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class HTTPPoolService {

    private static int KEEP_ALIVE_TIME_MS = 10000;
    private CloseableHttpClient client;

    @Autowired
    private Environment env;

    public CloseableHttpClient getHTTPClient () {
        if (client == null) {
            PoolingHttpClientConnectionManager poolingConnManager = new PoolingHttpClientConnectionManager();
            int poolSize = Integer.parseInt(env.getProperty("http.pool.size"));
            poolingConnManager.setMaxTotal(poolSize);
            poolingConnManager.setDefaultMaxPerRoute(poolSize);
            client = HttpClients
                    .custom()
                    .setKeepAliveStrategy(buildKeepAliveStrategy())
                    .setConnectionManager(poolingConnManager)
                    .build();
        }
        return client;
    }

    private ConnectionKeepAliveStrategy buildKeepAliveStrategy() {
        return (response, context) -> {
            HeaderElementIterator it = new BasicHeaderElementIterator
                    (response.headerIterator(HTTP.CONN_KEEP_ALIVE));
            while (it.hasNext()) {
                HeaderElement he = it.nextElement();
                String param = he.getName();
                String value = he.getValue();
                if (value != null && param.equalsIgnoreCase("timeout")) {
                    return Long.parseLong(value) * 1000;
                }
            }
            return KEEP_ALIVE_TIME_MS;
        };
    }
}
