package com.company;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.model.request.InputFile;

import java.io.File;

/**
 * Created by Manuel.Rixen on 26-Apr-16.
 */
public class TeleBot {

    private TelegramBot bot;

    public TeleBot(){
        bot = TelegramBotAdapter.build("183181959:AAH0AyU-k8AZNjXRxz-MM36rYOIdQ_iZO5E");
    }

    public void sendMessageToChat(int chatId, String message){
        bot.sendMessage(chatId, message);
    }

    public void sendPhotoToChat(int chatId, String path){
        File imageFile = new File(path);
        bot.sendPhoto(chatId, InputFile.photo(imageFile), "caption", null, null);
    }
}
