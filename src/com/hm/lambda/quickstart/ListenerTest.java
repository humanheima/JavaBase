package com.hm.lambda.quickstart;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by dumingwei on 2017/3/11.
 */
public class ListenerTest {

    public static void main(String[] args) {

        JButton button = new JButton("button");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("button");
            }
        });
        button.addActionListener(e -> System.out.println("button"));
    }
}
