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
        Map<String, String> parms = session.getParms();
        if ((parms.get("username") == null) ) {
            msg += "<form action='?' method='get'>\n" + "  <p>Enter your mobile number and hit enter: <input type='text' name='username'></p>\n" + "</form>\n";
        }
        if ((parms.get("sollvalue") == null) ) {
            msg += "<form action='?' method='get'>\n" + "  <p>Enter the soll value hit enter: <input type='text' name='sollvalue'></p>\n" + "</form>\n";
        }
        msg += "</body></html>\n";

            //msg += "<p>You entered: " + parms.get("username") + "</p>";
            mobileNumber = parms.get("username");
            //dataSet.setProduction_soll(parms.get("sollvalue"));

            System.out.println("mobileNumber: " + mobileNumber);
            //System.out.println("Production_soll: " + dataSet.getProduction_soll());



        return newFixedLengthResponse(msg);
    }
}


