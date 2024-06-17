package com.szs.utils;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.DecimalFormat;

@Component
public class FormatUtils {

    public String bigDecimalFormatting(BigDecimal number) {
        // decialFormat은 Thread safe하지 않아요 요거 전역으로 처리하면 안되요 synchronized 를 쓰던지 로직내에서 개별로 선언하던지 해야해요
        // 그리고 참고로 ###,### 와 ###,###,### 동일함 ###,### 만으로 충분
        // 블로그 참고 https://zorba91.tistory.com/365
        DecimalFormat format = new DecimalFormat("###,###,###");
        return format.format(number);
    }
}
