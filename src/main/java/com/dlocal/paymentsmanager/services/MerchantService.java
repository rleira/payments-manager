package com.dlocal.paymentsmanager.services;

import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.eclipse.jetty.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.io.IOException;

@Service
public class MerchantService {

    private static int KEEP_ALIVE_TIME_MS = 10000;
    private static String MERCHANT_URL = "";
    private CloseableHttpClient client;

    @Autowired
    private Environment env;

    public Boolean existsMerchant (String merchantId) throws IOException {
        HttpGet get = new HttpGet(buildGetMerchantURL(merchantId));
        HttpResponse response = getHTTPClient().execute(get);
        return response.getStatusLine().getStatusCode() == Response.Status.OK.getStatusCode();
    }

    private String buildGetMerchantURL (String merchantId) {
        return getMerchantURL() + merchantId;
    }

    private String getMerchantURL () {
        if (StringUtil.isBlank(MERCHANT_URL)) {
            buildMerchantURL();
        }
        return MERCHANT_URL;
    }

    private void buildMerchantURL () {
        MERCHANT_URL = new StringBuilder()
                .append("http://")
                .append(env.getProperty("merchant.url"))
                .append(":")
                .append(env.getProperty("merchant.port"))
                .append("/api/merchants/")
                .toString();
    }

    private CloseableHttpClient getHTTPClient () {
        if (client == null) {
            PoolingHttpClientConnectionManager poolingConnManager = new PoolingHttpClientConnectionManager();
            poolingConnManager.setMaxTotal(4);
            poolingConnManager.setDefaultMaxPerRoute(4);
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
