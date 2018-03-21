package com.hm.effective_java.chapter_three;

/**
 * Created by dumingwei on 2017/9/11.
 * {@link CaseInsentiveString#equals(Object)}覆盖equals违反了对称性
 */
public class TestCaseInsentiveString {

    public static void main(String[] args) {
        CaseInsentiveString cis = new CaseInsentiveString("Polish");
        String s = "polish";
        //true
        System.out.println(cis.equals(s));
        //false
        System.out.println(s.equals(cis));
    }
}
