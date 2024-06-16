package com.szs.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class NumberUtils {

    public static String bigDecimalFormatting(BigDecimal number) {
        DecimalFormat format = new DecimalFormat("###,###,###");
        return format.format(number);

    }


}
