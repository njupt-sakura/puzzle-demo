package com.github.fenghchuc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import org.junit.jupiter.api.Test;

public class AppTest {

  @Test
  public static void test() {
    JFrame jFrame = new JFrame();
    jFrame.setSize(550, 500);
    jFrame.setTitle("拼图游戏v1.0");
    jFrame.setLocationRelativeTo(null);
    jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jFrame.setLayout(null);

    JButton jbt = new JButton("点击");

    jbt.setBounds(0, 0, 100, 100);
    jbt.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            System.out.println("快点我");
          }
        });

    jFrame.getContentPane().add(jbt);

    jFrame.setVisible(true);
  }
}
