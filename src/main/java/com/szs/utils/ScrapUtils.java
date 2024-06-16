package com.szs.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.szs.domain.scrap.dto.ScrapResponseDto;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Set;

@Component
public class ScrapUtils {

    public ScrapResponseDto szsScrap() throws IOException {
        JsonObject obj = new JsonObject();
        obj.addProperty("name", "동탁");
        obj.addProperty("regNo", "921108-1582816");

        Connection.Response response = Jsoup.connect("https://codetest-v4.3o3.co.kr/scrap")
                .requestBody(obj.toString())
                .header("X-API-KEY", "aXC8zK6puHIf9l53L8TiQg==")
                .header("Content-Type", "application/json")
                .ignoreContentType(true)
                .timeout(300000)
                .method(Connection.Method.POST)
                .execute();


        JsonObject jsonObject = new Gson().fromJson(response.body(), JsonObject.class).get("data").getAsJsonObject();

        BigDecimal totalIncome = jsonObject.get("종합소득금액").getAsBigDecimal();

        BigDecimal nationalPensionTotal = BigDecimal.ZERO;
        JsonArray nationalPensionArray = jsonObject
                .get("소득공제")
                .getAsJsonObject()
                .get("국민연금")
                .getAsJsonArray();

        for (JsonElement it : nationalPensionArray) {
            BigDecimal bigdecimal = new BigDecimal(it.getAsJsonObject().get("공제액").getAsString().replace(",", ""));
            nationalPensionTotal = nationalPensionTotal.add(bigdecimal);
        }



        BigDecimal cardTotal = BigDecimal.ZERO;
        JsonArray cardArray = jsonObject
                .get("소득공제")
                .getAsJsonObject()
                .get("신용카드소득공제")
                .getAsJsonObject()
                .get("month")
                .getAsJsonArray();

        for (JsonElement it : cardArray) {
            Set<String> keys = it.getAsJsonObject().keySet();

            String key = keys.toArray(String[]::new)[0];
            BigDecimal bigdecimal = new BigDecimal(it.getAsJsonObject().get(key).getAsString().replace(",", ""));
            cardTotal = cardTotal.add(bigdecimal);
        }

        String taxCredit = jsonObject
                .get("소득공제")
                .getAsJsonObject()
                .get("세액공제")
                .getAsString()
                .replace(",", "");


        return new ScrapResponseDto(totalIncome,nationalPensionTotal.add(cardTotal),new BigDecimal(taxCredit));
    }

}
