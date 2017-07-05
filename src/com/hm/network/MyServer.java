package com.hm.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dumingwei on 2017/6/12.
 */
public class MyServer {

    public static List<Socket> socketList=new ArrayList<>();

    public static void main(String[] args) throws IOException {

        ServerSocket ss = new ServerSocket(30000);
        while (true) {
            Socket s = ss.accept();
            socketList.add(s);
            new Thread(new ServerThread(s)).start();
        }
    }
}

class ServerThread implements Runnable {

    Socket s = null;
    BufferedReader br = null;

    public ServerThread(Socket s) throws IOException {
        this.s = s;
        br = new BufferedReader(new InputStreamReader(s.getInputStream()));
    }

    @Override
    public void run() {
        String contnet = null;
        try {
            while ((contnet = readFromClient()) != null) {
                for (Socket socket : MyServer.socketList) {
                    PrintStream ps = new PrintStream(socket.getOutputStream());
                    ps.println(contnet);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readFromClient() {
        try {
            return br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            MyServer.socketList.remove(s);
        }
        return null;
    }

}
