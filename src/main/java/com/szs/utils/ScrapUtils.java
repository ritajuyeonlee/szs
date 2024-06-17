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

    // throw 하는거보다 오류 자체를 해당 util에서 처리 하는게 좋아보여요
    // 오류를 처리 하고 정의되어있는 commonError로 throw 한다던지?
    public ScrapResponseDto szsScrap() throws IOException {
        JsonObject obj = new JsonObject(); // 변수명을 obj 보단 jsonObject 로 해주는게 좋을거 같아요
        obj.addProperty("name", "동탁");
        obj.addProperty("regNo", "921108-1582816");

        Connection.Response response = Jsoup.connect("https://codetest-v4.3o3.co.kr/scrap")
                .requestBody(obj.toString())
                .header("X-API-KEY", "aXC8zK6puHIf9l53L8TiQg==") // key는 yaml로 별도 관리하는게 좋아 보여요
                .header("Content-Type", "application/json")
                .ignoreContentType(true)
                .timeout(300000) // 요거도 yaml로 관리
                .method(Connection.Method.POST)
                .execute();


        JsonObject jsonObject = new Gson().fromJson(response.body(), JsonObject.class).get("data").getAsJsonObject();

        BigDecimal totalIncome = jsonObject.get("종합소득금액").getAsBigDecimal();

        BigDecimal nationalPensionTotal = BigDecimal.ZERO;
        // 소득공제, 국민염금, 신용카드속득공제 month 데이터들은 데이터를 꺼내서 계산 하는 로직이 반복되고 있는거 같아요 요거 메소드로 분리 가능할듯?
        // 매개변수로 소득공제,국민연금 같은 값들을 받아서 계산하도록 분리 가능해보여요
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
