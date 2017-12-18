package com.hm.generic;

/**
 * Created by dumingwei on 2017/12/13 0013.
 */
public class MultiInterfaceVariants {

    interface Payable<T> {
    }

    class Employee implements Payable {

    }

    class Hourly extends Employee implements Payable {

    }


}
