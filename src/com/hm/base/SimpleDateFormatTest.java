package com.hm.base;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.*;

/**
 * Created by dmw on 2018/12/6.
 * Desc: SimpleDateFormat 注意不是线程安全的。
 */
public class SimpleDateFormatTest {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    private static ExecutorService pool = new ThreadPoolExecutor(5, 200, 0L,
            TimeUnit.MILLISECONDS, new LinkedBlockingDeque<Runnable>(1024), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r);
        }
    }, new ThreadPoolExecutor.AbortPolicy());

    private static CountDownLatch countDownLatch = new CountDownLatch(100);

    public static void main(String[] args) throws InterruptedException {
        //unsafeInvoke();
        //safeInvokeFirst();
        //safeInvokeSecond();
        //safeInvokeThird();
        //useDateTimeFormatter();
        parseTime();
    }

    /**
     * SimpleDateFormat 线程不安全的使用方法
     *
     * @throws InterruptedException
     */
    private static void unsafeInvoke() throws InterruptedException {
        //定义一个线程安全的HashSet
        Set<String> dates = Collections.synchronizedSet(new HashSet<String>());
        for (int i = 0; i < 100; i++) {
            //获取当前时间
            Calendar calendar = Calendar.getInstance();
            //时间增加
            int finalI = i;
            calendar.add(Calendar.DATE, finalI);
            pool.execute(() -> {
                //通过simpleDateFormat把时间转换成字符串
                String dateString = simpleDateFormat.format(calendar.getTime());
                //把字符串放入Set中
                dates.add(dateString);
                //countDown
                countDownLatch.countDown();
            });
        }
        //阻塞，直到countDown数量为0
        countDownLatch.await();
        //输出去重后的时间个数
        System.out.println(dates.size());
    }

    /**
     * SimpleDateFormat 安全的使用方法1，把SimpleDateFormat声明成局部变量
     *
     * @throws InterruptedException
     */
    private static void safeInvokeFirst() throws InterruptedException {
        //定义一个线程安全的HashSet
        Set<String> dates = Collections.synchronizedSet(new HashSet<String>());
        for (int i = 0; i < 100; i++) {
            //获取当前时间
            Calendar calendar = Calendar.getInstance();
            //时间增加
            int finalI = i;
            calendar.add(Calendar.DATE, finalI);
            pool.execute(() -> {
                // SimpleDateFormat声明成局部变量
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                //通过simpleDateFormat把时间转换成字符串
                String dateString = simpleDateFormat.format(calendar.getTime());
                //把字符串放入Set中
                dates.add(dateString);
                //countDown
                countDownLatch.countDown();
            });
        }
        //阻塞，直到countDown数量为0
        countDownLatch.await();
        //输出去重后的时间个数
        System.out.println(dates.size());
        pool.shutdown();
    }

    /**
     * SimpleDateFormat 安全的使用方法2，同步加锁
     *
     * @throws InterruptedException
     */
    private static void safeInvokeSecond() throws InterruptedException {
        //定义一个线程安全的HashSet
        Set<String> dates = Collections.synchronizedSet(new HashSet<String>());
        for (int i = 0; i < 100; i++) {
            //获取当前时间
            Calendar calendar = Calendar.getInstance();
            //时间增加
            int finalI = i;
            calendar.add(Calendar.DATE, finalI);
            pool.execute(() -> {
                String dateString;
                synchronized (simpleDateFormat) {
                    //通过simpleDateFormat把时间转换成字符串
                    dateString = simpleDateFormat.format(calendar.getTime());
                }
                //把字符串放入Set中
                dates.add(dateString);
                //countDown
                countDownLatch.countDown();
            });
        }
        //阻塞，直到countDown数量为0
        countDownLatch.await();
        //输出去重后的时间个数
        System.out.println(dates.size());
        pool.shutdown();
    }


    private static ThreadLocal<SimpleDateFormat> simpleDateFormatThreadLocal = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    /**
     * SimpleDateFormat 安全的使用方法3,使用ThreadLocal
     *
     * @throws InterruptedException
     */
    private static void safeInvokeThird() throws InterruptedException {
        //定义一个线程安全的HashSet
        Set<String> dates = Collections.synchronizedSet(new HashSet<>());
        for (int i = 0; i < 100; i++) {
            //获取当前时间
            Calendar calendar = Calendar.getInstance();
            //时间增加
            int finalI = i;
            calendar.add(Calendar.DATE, finalI);
            pool.execute(() -> {
                String dateString;

                //通过simpleDateFormat把时间转换成字符串
                dateString = simpleDateFormatThreadLocal.get().format(calendar.getTime());
                //把字符串放入Set中
                dates.add(dateString);
                //countDown
                countDownLatch.countDown();
            });
        }
        //阻塞，直到countDown数量为0
        countDownLatch.await();
        //输出去重后的时间个数
        System.out.println(dates.size());
        pool.shutdown();
    }

    /**
     * 使用Java8中的DateTimeFormatter类
     */
    private static void useDateTimeFormatter() {
        String now = "2018年12月06日";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
        LocalDate date = LocalDate.parse(now, formatter);
        System.out.println(date);

        //日期转换为字符串

        LocalDateTime nowTime = LocalDateTime.now();
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 hh:mm a");
        String nowStr = nowTime.format(formatter1);
        System.out.println(nowStr);

    }

    public static void parseTime() {
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("H", Locale.getDefault());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd:H", Locale.getDefault());
        //String time = simpleDateFormat.format(new Date(System.currentTimeMillis() - 3600L * 8 * 1000));
        String time = simpleDateFormat.format(new Date(System.currentTimeMillis()));
        System.out.println(time);
        System.out.println("15".compareTo("9"));


        System.out.println(Integer.parseInt("09"));

        /*System.out.println(Long.MAX_VALUE);
        long x = 3600L * 24 * 28 * 1000;
        System.out.println(x);*/

        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < 300; i++) {
            int day = calendar.get(Calendar.DAY_OF_YEAR);
            System.out.println(simpleDateFormat.format(calendar.getTime()));
            calendar.set(Calendar.DAY_OF_YEAR, day + 1);
        }


    }
}
