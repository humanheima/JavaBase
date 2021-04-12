package com.hm.generic.blog_demo;

import java.util.Date;

class DateInter extends Pair<Date> {

    @Override
    public Date getValue() {
        return super.getValue();
    }

    @Override
    public void setValue(Date value) {
        super.setValue(value);
    }

    @Override
    public void test() {
        System.out.println("child test");
    }
}