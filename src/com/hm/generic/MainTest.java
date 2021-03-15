package com.hm.generic;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by dumingwei on 2020/10/16.
 * <p>
 * Desc:
 */
public class MainTest {


    public static void main(String[] args) {


        /*Erasure<String> erasure = new Erasure<>("hello");
        Class eclz = erasure.getClass();
        System.out.println("erasure class is:" + eclz.getName());

        Field[] fs = eclz.getDeclaredFields();
        for (Field f : fs) {
            System.out.println("Field name " + f.getName() + " type:" + f.getType().getName());
        }

        System.out.println();

        Method[] methods = eclz.getDeclaredMethods();
        for (Method m : methods) {
            System.out.println(" method:" + m.toString());
        }

        System.out.println();


        Erasure1<String> erasure1 = new Erasure1<>("hello");
        Class eclz1 = erasure1.getClass();

        System.out.println("erasure1 class is:" + eclz1.getName());

        Field[] fs1 = eclz1.getDeclaredFields();
        for (Field f : fs1) {
            System.out.println("Field name " + f.getName() + " type:" + f.getType().getName());
        }*/

        System.out.println(
                getFriendlyTimeStr(1607344845000L)
        );
    }


    public static String getFriendlyTimeStr(long l) {
        long curTime = System.currentTimeMillis();
        if (l <= 0 || curTime < l) {
            return countTimeNew(l);
        }
        long between = (curTime - l) / 1000;// 除以1000是为了转换成秒
        if (between < 60) {
            return "刚刚";
        }

        long month = between / (24 * 3600 * 30);
        long day = between / (24 * 3600);
        long hour = between / 3600;
        long minute = between / 60;

        if (month >= 1) {
            //大于等于6个月
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            return sdf.format(new Date(l));
        }/*else if(month >= 1){
			//小于6个月大于等于1个月
			return month+"月前";
		}*/ else if (day >= 1) {
            //小于1个月大于等于1天
            return day + "天前";
        } else if (hour >= 1) {
            //小于1天大于等于1个小时
            return hour + "小时前";
        } else if (minute >= 1) {
            //小于1个小时大于等于1分钟
            return minute + "分钟前";
        } else {
            return "刚刚";
        }
    }

    public static String countTimeNew(long t) {
        if (t <= 0) {
            return "";
        } else {
            return new SimpleDateFormat("yy-MM").format(new Date(t));
        }
    }

}

class Base {
}

class Sub extends Base {
}

class Erasure<T> {
    T value;

    public Erasure(T object) {
        this.value = object;
    }

    public void add(T object) {

    }

}

class Erasure1<T extends String> {
    T value;

    public Erasure1(T object) {
        this.value = object;
    }

}




