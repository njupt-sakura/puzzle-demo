package com.github.fenghchuc.UI;

import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class HelpJframe extends JFrame {
  public HelpJframe() {
    JFrame frame = new JFrame("帮助文档");
    frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    frame.setSize(480, 200);

    JTextArea textArea =
        new JTextArea(
            "操作：点击两个相邻的图片来调换位置，直到成功拼成\n\n指令：\n长按a键显示完整图片\n\n" + "注：目前只开放了重新游戏和关闭游戏功能！", 10, 35);
    textArea.setEditable(false);

    // 设置字体：字体名称为 "Serif"，样式为 BOLD（粗体），大小为 16
    Font customFont = new Font("Serif", Font.BOLD, 16);
    textArea.setFont(customFont);

    JScrollPane scrollPane = new JScrollPane(textArea);

    frame.setLocationRelativeTo(null);
    frame.setLayout(new java.awt.FlowLayout());
    frame.add(scrollPane);

    frame.setVisible(true);
  }
}
