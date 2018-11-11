package com.dlocal.paymentsmanager.services;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.eclipse.jetty.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.io.IOException;

@Service
public class MerchantService {

    private static String MERCHANT_URL = "";

    @Autowired
    private Environment env;

    @Autowired
    private HTTPPoolService httpPoolService;

    public Boolean existsMerchant (String merchantId) throws IOException {
        HttpResponse response = null;
        try {
            HttpGet get = new HttpGet(buildGetMerchantURL(merchantId));
            response = httpPoolService.getHTTPClient().execute(get);
        } catch (IOException ioe) {
            return false;
        }
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
}
