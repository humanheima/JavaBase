package com.hm.picture_of_patten.proxy;

/**
 * Created by dumingwei on 2022/5/11
 *
 * Desc:
 */

public interface Printable {

    public abstract void setPrinterName(String name);   // 设置名字
    public abstract String getPrinterName();            // 获取名字
    public abstract void print(String string);          // 显示文字（打印输出）

}
