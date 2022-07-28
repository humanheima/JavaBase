package com.hm.picture_of_patten.state.otherdemo;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        IPlayer player = new VideoPlayer();
        int i = -1;
        while ((i = sc.nextInt()) != -1) {
            player.request(i);
        }
    }
}
