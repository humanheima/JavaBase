package com.hm.effective_java.chapter_three;

/**
 * Created by dumingwei on 2017/9/11.
 */
public class CaseInsentiveString {

    private final String s;

    public CaseInsentiveString(String s) {
        this.s = s;
    }

   /* //错误的覆盖equals方法
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CaseInsentiveString) {
            return s.equalsIgnoreCase((((CaseInsentiveString) obj).s));
        }
        if (obj instanceof String) {
            return s.equalsIgnoreCase((String) obj);
        }
        return false;
    }*/

    @Override
    public boolean equals(Object obj) {
        return obj instanceof CaseInsentiveString && ((CaseInsentiveString) obj).s.equalsIgnoreCase(s);
    }
}
