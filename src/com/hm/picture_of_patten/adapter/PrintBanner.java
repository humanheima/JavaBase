package com.hm.picture_of_patten.adapter;

/**
 * Created by dumingwei on 2022/3/27.
 * <p>
 * Desc:
 */
public class PrintBanner extends Banner implements Print {

    public PrintBanner(String string) {
        super(string);
    }

    @Override
    public void printWeak() {
        showWithParen();
    }

    @Override
    public void printStrong() {

        showWithAster();

    }
}
