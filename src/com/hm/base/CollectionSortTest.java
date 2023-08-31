package com.hm.base;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by dumingwei on 2022/4/18.
 * 测试集合排序
 */
public class CollectionSortTest {


    public static void main(String[] args) throws UnsupportedEncodingException {
        String text =  "<p style=\"font-family:&quot;font-size:14px;\"><span style=\"font-family:NSimSun;font-size:14px;color:#313033;line-height:2;\">我们将通过</span><span style=\"font-family:NSimSun;font-size:14px;color:#313033;line-height:2;\"><a href=\"https://ptxxh5.yuewen.com/h5/about/lawAgreement?agreementId=93\">《软件许可及服务协议》</a></span></b><span style=\"font-family:NSimSun;font-size:14px;color:#313033;line-height:2;\">帮助你了解我们收集、使用、存储和共享个人信息的情况，特别是我们所采集的个人信息类型及其用途。同时，你还可以了解到你所享有的相关权利和实现途径以及我们为了保护好你的个人信息安全所采用的相关策略。如你同意，可以点击“同意”开始接受我们的服务。</span></p>\n";
        String encode = URLEncoder.encode(text, "utf-8");

        System.out.println(encode);
        String decode = URLDecoder.decode("%3Cp%20style%3D%22font-family%3A%26quot%3Bfont-size%3A14px%3B%22%3E%3Cspan%20style%3D%22font-family%3ANSimSun%3Bfont-size%3A14px%3Bcolor%3A%23313033%3Bline-height%3A2%3B%22%3E%E6%88%91%E4%BB%AC%E5%B0%86%E9%80%9A%E8%BF%87%3C%2Fspan%3E%3Cspan%20style%3D%22font-family%3ANSimSun%3Bfont-size%3A14px%3Bcolor%3A%23313033%3Bline-height%3A2%3B%22%3E%3Ca%20href%3D%22https%3A%2F%2Fptxxh5.yuewen.com%2Fh5%2Fabout%2FlawAgreement%3FagreementId%3D93%22%3E%E3%80%8A%E8%BD%AF%E4%BB%B6%E8%AE%B8%E5%8F%AF%E5%8F%8A%E6%9C%8D%E5%8A%A1%E5%8D%8F%E8%AE%AE%E3%80%8B%3C%2Fa%3E%3C%2Fspan%3E%3C%2Fb%3E%3Cspan%20style%3D%22font-family%3ANSimSun%3Bfont-size%3A14px%3Bcolor%3A%23313033%3Bline-height%3A2%3B%22%3E%E5%B8%AE%E5%8A%A9%E4%BD%A0%E4%BA%86%E8%A7%A3%E6%88%91%E4%BB%AC%E6%94%B6%E9%9B%86%E3%80%81%E4%BD%BF%E7%94%A8%E3%80%81%E5%AD%98%E5%82%A8%E5%92%8C%E5%85%B1%E4%BA%AB%E4%B8%AA%E4%BA%BA%E4%BF%A1%E6%81%AF%E7%9A%84%E6%83%85%E5%86%B5%EF%BC%8C%E7%89%B9%E5%88%AB%E6%98%AF%E6%88%91%E4%BB%AC%E6%89%80%E9%87%87%E9%9B%86%E7%9A%84%E4%B8%AA%E4%BA%BA%E4%BF%A1%E6%81%AF%E7%B1%BB%E5%9E%8B%E5%8F%8A%E5%85%B6%E7%94%A8%E9%80%94%E3%80%82%E5%90%8C%E6%97%B6%EF%BC%8C%E4%BD%A0%E8%BF%98%E5%8F%AF%E4%BB%A5%E4%BA%86%E8%A7%A3%E5%88%B0%E4%BD%A0%E6%89%80%E4%BA%AB%E6%9C%89%E7%9A%84%E7%9B%B8%E5%85%B3%E6%9D%83%E5%88%A9%E5%92%8C%E5%AE%9E%E7%8E%B0%E9%80%94%E5%BE%84%E4%BB%A5%E5%8F%8A%E6%88%91%E4%BB%AC%E4%B8%BA%E4%BA%86%E4%BF%9D%E6%8A%A4%E5%A5%BD%E4%BD%A0%E7%9A%84%E4%B8%AA%E4%BA%BA%E4%BF%A1%E6%81%AF%E5%AE%89%E5%85%A8%E6%89%80%E9%87%87%E7%94%A8%E7%9A%84%E7%9B%B8%E5%85%B3%E7%AD%96%E7%95%A5%E3%80%82%E5%A6%82%E4%BD%A0%E5%90%8C%E6%84%8F%EF%BC%8C%E5%8F%AF%E4%BB%A5%E7%82%B9%E5%87%BB%E2%80%9C%E5%90%8C%E6%84%8F%E2%80%9D%E5%BC%80%E5%A7%8B%E6%8E%A5%E5%8F%97%E6%88%91%E4%BB%AC%E7%9A%84%E6%9C%8D%E5%8A%A1%E3%80%82%3C%2Fspan%3E%3C%2Fp%3E", "utf-8");
        System.out.println(decode);
        System.out.println(decode.equals(text));
//        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
//        list.add("hello");
//        list.add("world");
//        list.add("java");
//        list.add("python");
//        list.add("c++");
//        list.add("c");
//        list.add("c#");
//        list.add("php");
//        list.add("javascript");
//
//        Iterator<String> iterator = list.iterator();
//        while (iterator.hasNext()) {
//            String next = iterator.next();
//            if (next.equals("java")) {
//                iterator.remove();
//            }
//        }

    }

    /**
     * 测试排序
     */
    public static void testSort() {
        List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(2);
        list.add(5);
        list.add(6);
        list.add(1);

        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o2 > o1) {
                    return 1;

                } else if (o1.equals(o2)) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });

        System.out.println(list);


    }
}
