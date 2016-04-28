package com.company.Webserver;



import com.company.DataSet;
import fi.iki.elonen.NanoHTTPD;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Manuel.Rixen on 28-Apr-16.
 */
public class WebServer extends NanoHTTPD {

    private final DataSet dataSet;

    public WebServer(DataSet dataSet) throws IOException {
        super(80);
        this.dataSet = dataSet;
        start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
    }

    @Override
    public Response serve(IHTTPSession session) {
        String msg = dataSet.getHtmlCode();
        Map<String, String> params = session.getParms();
        if (params.get("mobileNumber") == null) {

        } else {
            System.out.println("Mobile number added");
        }
        return newFixedLengthResponse(msg);
    }
}