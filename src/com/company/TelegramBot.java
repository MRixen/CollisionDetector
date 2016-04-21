package com.company;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static java.awt.SystemColor.text;

/**
 * Created by Manuel.Rixen on 21-Apr-16.
 */
public class TelegramBot {

    private final String endpoint = "https://api.telegram.org/bot";
    private final String token = "183181959:AAH0AyU-k8AZNjXRxz-MM36rYOIdQ_iZO5E";
    int last_update_id = 0; // last processed command

    HttpResponse<JsonNode> response;


    public HttpResponse<JsonNode> sendMessage(String chatId, String text) throws UnirestException {
        return Unirest.post(endpoint + token + "/sendMessage")
                .field("chat_id", chatId)
                .field("text", text)
                .asJson();
    }

    public HttpResponse<JsonNode> sendMessage(int chatId, String text) throws UnirestException {
        return Unirest.post(endpoint + token + "/sendMessage")
                .field("chat_id", chatId)
                .field("text", text)
                .asJson();
    }

    public HttpResponse<JsonNode> getUpdates(Integer offset) throws UnirestException {
        return Unirest.post(endpoint + token + "/getUpdates")
                .field("offset", offset)
                .asJson();
    }

    public void sendMessageToChat() throws UnirestException {
        //while (true) {
            response = getUpdates(last_update_id++);

            if (response.getStatus() == 200) {
                JSONArray responses = response.getBody().getObject().getJSONArray("result");
                if (responses.isNull(0)) ;//continue;
                else last_update_id = responses
                        .getJSONObject(responses.length() - 1)
                        .getInt("update_id") + 1;

               // for (int i = 0; i < responses.length(); i++) {
/*                    JSONObject message = responses
                            .getJSONObject(i)
                            .getJSONObject("message");*/
/*
                    int chat_id = message
                            .getJSONObject("chat")
                            .getInt("id");*/


/*                    String username = message
                            .getJSONObject("user")
                            .getString("username");*/

/*                    String text = message
                            .getString("text");*/

                   // if (text.contains("/start")) {
                        String messageToSend = "Collision detected at camera belt.";
                        //sendMessage(chat_id, messageToSend);
                        sendMessage(45587174, messageToSend);
/*                    } else if (text.contains("/echo")) {
                        sendMessage(chat_id, "Received " + text);
                    } else if (text.contains("/toupper")) {
                        String param = text.substring("/toupper".length(), text.length());
                        sendMessage(chat_id, param.toUpperCase());
                    }*/
               // }
            //}
        }
    }
}


