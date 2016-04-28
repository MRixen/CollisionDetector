package com.company.Receiver;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.model.request.InputFile;
import com.pengrad.telegrambot.model.request.InputFileBytes;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by Manuel.Rixen on 26-Apr-16.
 */
public class TeleBot {

    private TelegramBot bot;

    public TeleBot(String teleBotToken){
        bot = TelegramBotAdapter.build(teleBotToken);
    }

    public void sendMessageToChat(int chatId, String message){
        bot.sendMessage(chatId, message);
    }

    public void sendPhotoToChat(int chatId, String path, String caption){
        File imageFile = new File(path);
        bot.sendPhoto(chatId, InputFile.photo(imageFile), caption, null, null);
    }

    public void sendPhotoToChat(int chatId, byte[] bytes, String caption){
        bot.sendPhoto(chatId, InputFileBytes.photo(bytes), caption, null, null);
    }
}
