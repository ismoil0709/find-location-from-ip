package org.pdp.vpnapp.utils;

import org.pdp.vpnapp.model.Information;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.*;

public class IpAddressUtils {
    public static Map<Long,Map<String,String>> links = new HashMap<>();
    public static String generateLink(Long chatId,String link){
        byte[] encode = Base64.getEncoder().encode(chatId.toString().getBytes(StandardCharsets.UTF_8));
        String newLink = new String(encode);
        links.put(chatId,Map.of(newLink,link));
        return newLink;
    }

    public static Information getData(String ip){
        String KEY = "cff5773a7df27edb57be9afc6b96ea37";
        String uri  = "http://api.ipstack.com/" + ip + "?access_key=" + KEY;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<>("parameters",headers);
        return restTemplate.exchange(uri, HttpMethod.GET,entity, Information.class).getBody();
    }
}
