package com.hm.base;

/**
 * Created by dumingwei on 2017/10/11.
 */
public class BreakTest {

    public static void main(String[] args) {

        first:
        {
            second:
            {
                third:
                {
                    System.out.println("Before the break");
                    for (int i = 0; i < 10; i++) {
                        System.out.println("i==" + i);
                        if (i == 3) {
                            break second;// break out of second block
                            //break;
                        }

                    }
                }
                System.out.println("This won't execute");
            }
            System.out.println("This is after second block.");
        }
    }
}
