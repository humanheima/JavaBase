package com.hm.pattern.proxy.gumball;

import java.rmi.*;

public class GumballMachineTestDrive {

    public static void main(String[] args) {
        GumballMachineRemote gumballMachine = null;
        int count=10;
        try {
            gumballMachine = new GumballMachine("127.0.0.1", count);
            Naming.rebind("//" + "127.0.0.1" + "/gumballmachine", gumballMachine);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
