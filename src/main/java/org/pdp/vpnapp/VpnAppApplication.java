package org.pdp.vpnapp;

import lombok.SneakyThrows;
import org.pdp.vpnapp.bot.TelegramBot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
public class VpnAppApplication {

    @SneakyThrows
    public static void main(String[] args) {
        SpringApplication.run(VpnAppApplication.class, args);
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(new TelegramBot());
    }

}
