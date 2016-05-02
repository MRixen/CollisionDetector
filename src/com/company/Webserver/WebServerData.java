package com.company.Webserver;



import com.company.DataSet;
import fi.iki.elonen.NanoHTTPD;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Manuel.Rixen on 28-Apr-16.
 */
public class WebServerData extends NanoHTTPD {

    private final DataSet dataSet;

    public WebServerData(DataSet dataSet) throws IOException {
        super(8085);
        this.dataSet = dataSet;
        start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
    }

    @Override
    public Response serve(IHTTPSession session) {

        String msg = dataSet.getHtmlCodeData();

        return newFixedLengthResponse(msg);
    }


}