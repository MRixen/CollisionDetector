package com.company.Webserver;

import com.company.DataSet;
import fi.iki.elonen.NanoHTTPD;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Manuel.Rixen on 02-May-16.
 */
public class WebServerConfig extends NanoHTTPD {

    private final DataSet dataSet;
    private String mobileNumber;

    public WebServerConfig(DataSet dataSet) throws IOException {
        super(8084);
        this.dataSet = dataSet;
        start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
    }

    @Override
    public Response serve(IHTTPSession session) {
        Method method = session.getMethod();
        String uri = session.getUri();

        String msg = dataSet.getHtmlCodeConfig();
        Map<String, String> params = session.getParms();
        String mobileNumberTemp = params.get("username");
        String sollvalueTemp = params.get("sollvalue");
        String myButton = params.get("systemupdate");

        if(mobileNumberTemp != null) mobileNumber = params.get("username");
        if(sollvalueTemp != null) dataSet.setProduction_soll(params.get("sollvalue"));
        if(myButton != null) System.out.println("myButton");

        System.out.println("mobileNumber: " + mobileNumber);
        System.out.println("sollvalue: " + dataSet.getProduction_soll());

        return newFixedLengthResponse(msg);
    }
}


