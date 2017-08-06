package com.hm.anno;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by dumingwei on 2017/8/6.
 */
public class AnnotionTest {

    private JFrame mainWin = new JFrame("使用注解绑定监听事件");
    @ActionListenerFor(listener = OkListener.class)
    private JButton ok = new JButton("确定");
    @ActionListenerFor(listener = CancelListener.class)
    private JButton cancel = new JButton("取消");

    public static void main(String[] args) {
        new AnnotionTest().init();
    }

    public void init() {
        JPanel jp = new JPanel();
        jp.add(ok);
        jp.add(cancel);
        mainWin.add(jp);
        ActionListenerInstaller.processAnnotions(this);
        mainWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWin.pack();
        mainWin.setVisible(true);
    }


    public static class OkListener implements ActionListener {

        public OkListener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "点击确认按钮");
        }
    }

    public static class CancelListener implements ActionListener {

        public CancelListener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "点击取消按钮");
        }
    }
}
