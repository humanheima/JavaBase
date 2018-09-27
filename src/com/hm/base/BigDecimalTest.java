package com.hm.base;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class BigDecimalTest {

    DecimalFormat decimalFormat = new DecimalFormat("0.00");

    public static void main(String[] args) {
        BigDecimal decimal = new BigDecimal(2.35);
        //保留两位小数
        BigDecimal bigDecimal = decimal.setScale(2, BigDecimal.ROUND_DOWN);
        System.out.println(bigDecimal.toString());
        System.out.println(decimal.setScale(1, BigDecimal.ROUND_DOWN));
        System.out.println(decimal.setScale(1, BigDecimal.ROUND_UP));
        System.out.println(decimal.setScale(1, BigDecimal.ROUND_HALF_UP));
        System.out.println(decimal.setScale(1, BigDecimal.ROUND_HALF_DOWN));
        System.out.println(decimal.setScale(1, BigDecimal.ROUND_CEILING));
        System.out.println(decimal.setScale(1, BigDecimal.ROUND_FLOOR));
        System.out.println(decimal.setScale(1, BigDecimal.ROUND_HALF_EVEN));
    }
}
