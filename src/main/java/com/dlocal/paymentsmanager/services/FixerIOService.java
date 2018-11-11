package com.dlocal.paymentsmanager.services;

import com.dlocal.paymentsmanager.datastore.models.FixerIO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.eclipse.jetty.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class FixerIOService {
    private static String FIXER_IO_URL = "";

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private Environment env;

    @Autowired
    private HTTPPoolService httpPoolService;

    public FixerIO getCurrencyFromFixerIO() throws IOException {
        HttpGet get = new HttpGet(getFixerIOURL());
        HttpResponse response = httpPoolService.getHTTPClient().execute(get);
        FixerIO fixerIOResponse = objectMapper.readValue(response.getEntity().getContent(), FixerIO.class);
        return fixerIOResponse;
    }

    private String getFixerIOURL () {
        if (StringUtil.isBlank(FIXER_IO_URL)) {
            buildFixerIOURL();
        }
        return FIXER_IO_URL;
    }

    private void buildFixerIOURL () {
        FIXER_IO_URL = new StringBuilder()
                .append(env.getProperty("fixerio.api.url"))
                .append("?access_key=")
                .append(env.getProperty("fixerio.api.key"))
                .toString();
    }
}
