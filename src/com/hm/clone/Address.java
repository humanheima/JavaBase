package com.hm.clone;

/**
 * Created by dumingwei on 2017/6/6.
 */
public class Address implements Cloneable {

    private String address;

    @Override
    protected Object clone() {
        Address addr = null;
        try {
            addr = (Address) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return addr;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
