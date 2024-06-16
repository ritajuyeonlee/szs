package com.szs.unit;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;

public class test {
    @Test
    public void test() throws IOException {
        HashMap<String, String> map = new HashMap<>();
                map.put("name", "동탁");
                map.put("regNo", "921108-1582816");

                HashMap<String, String> headerMap = new HashMap<>();
                headerMap.put("X-API-KEY", "aXC8zK6puHIf9l53L8TiQg==");

        System.out.println(Jsoup.connect("https://codetest-v4.3o3.co.kr/scrap")
                                .method(Connection.Method.POST)
                                .header("X-API-KEY", "aXC8zK6puHIf9l53L8TiQg==")
                                .data(map));
    }
}
