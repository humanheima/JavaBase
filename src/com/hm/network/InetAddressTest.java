package com.hm.network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by dumingwei on 2017/6/11.
 */
public class InetAddressTest {

    public static void main(String[] args) {
        try {
            InetAddress ip=InetAddress.getByName("www.crazyit.org");
            System.out.println("crazyit是否可达："+ip.isReachable(2000));
            System.out.println(ip.getHostAddress());
            InetAddress local=InetAddress.getByAddress(new byte[]{127,0,0,1});
            System.out.println("本机是否可达："+local.isReachable(2000));
            System.out.println(local.getCanonicalHostName());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
