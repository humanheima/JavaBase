package com.hm.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Created by dumingwei on 2017/6/12.
 */
public class MyClient {

    public static void main(String[] args) throws IOException {
        Socket s = new Socket("127.0.0.1", 30000);
        new Thread(new ClientThread(s)).start();
        PrintStream ps = new PrintStream(s.getOutputStream());
        String line = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while ((line = br.readLine()) != null) {
            ps.println(line);
        }
    }
}

class ClientThread implements Runnable {

    private Socket s;
    private BufferedReader br = null;

    public ClientThread(Socket s) {
        this.s = s;
        try {
            br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String content = null;
        try {
            while ((content = br.readLine()) != null) {
                System.out.println(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
