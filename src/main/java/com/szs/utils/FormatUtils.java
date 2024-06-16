package com.szs.utils;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.DecimalFormat;

@Component
public class FormatUtils {

    public String bigDecimalFormatting(BigDecimal number) {
        DecimalFormat format = new DecimalFormat("###,###,###");
        return format.format(number);
    }
}
