package com.hm.picture_of_patten.decorator;

/**
 * Created by dumingwei on 2022/5/7
 *
 * Desc:
 */
public class Main {

    public static void main(String[] args) {
        Display b1 = new StringDisplay("Hello, world.");

        Display d1=new FullBorder(b1);
        d1.show();

//        Display b2 = new SideBorder(b1, '#');
//        Display b3 = new FullBorder(b2);
//        b1.show();
//        b2.show();
//        b3.show();
//        Display b4 =
//                    new SideBorder(
//                        new FullBorder(
//                            new FullBorder(
//                                new SideBorder(
//                                    new FullBorder(
//                                        new StringDisplay("你好，世界。")
//                                    ),
//                                    '*'
//                                )
//                            )
//                        ),
//                        '/'
//                    );
//        b4.show();
    }
}
