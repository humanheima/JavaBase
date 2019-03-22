package com.hm.base;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class BigDecimalTest {

    DecimalFormat decimalFormat = new DecimalFormat("0.00");

    public static void main(String[] args) {

        //useDouble();

        System.out.println(new BigDecimal(0.1).toString());
        System.out.println(new BigDecimal("0.1").toString());
        useBigDecimal();
    }

    private static void useDouble() {
        System.out.println(0.06 + 0.01);
        System.out.println(1.0 - 0.42);
        System.out.println(4.015 * 100);
        System.out.println(303.1 / 1000);
    }

    private static void useBigDecimal() {
        BigDecimal decimal1 = new BigDecimal("0.06");
        BigDecimal decimal2 = new BigDecimal("0.01");
        System.out.println(decimal1.add(decimal2));
        System.out.println(new BigDecimal(Double.toString(1.0)).subtract(new BigDecimal(Double.toString(0.42))));
        System.out.println(new BigDecimal(Double.toString(4.015)).multiply(new BigDecimal(Double.toString(100))));
        System.out.println(new BigDecimal(Double.toString(303.1)).divide(new BigDecimal(Double.toString(1000))));

        BigDecimal decimal = new BigDecimal(2.035);
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
