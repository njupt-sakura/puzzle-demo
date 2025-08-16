package com.github.fenghchuc.UI;

import com.github.fenghchuc.utils.SelfJlabel;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.BevelBorder;

public class GameJframe extends JFrame implements KeyListener, MouseListener, ActionListener {

  private ArrayList<SelfJlabel> jlabs = new ArrayList();
  int place = -1;
  int num = 0;

  // 创建功能选项中的条目：更换图片 重新游戏 重新登录 关闭游戏
  JMenuItem ChangImage = new JMenuItem("更换图片");
  JMenuItem replay = new JMenuItem("重新游戏");
  JMenuItem reload = new JMenuItem("重新登录");
  JMenuItem close = new JMenuItem("关闭游戏");

  // 创建 关于我们 中的条目：帮助
  JMenuItem help = new JMenuItem("帮助");

  public GameJframe() {}

  public static void bootstrap() {
    var instance = new GameJframe();
    instance.initJFrame().initJMenu().massImage().loadImage().loadBK();

    instance.setVisible(true);
  }

  private boolean win() {

    for (int i = 1; i <= 16; i++) {
      if (jlabs.get(i - 1).index == -1) {
        break;
      }
      if (jlabs.get(i - 1).index != i) {
        return false;
      }
    }
    place = -2;
    this.getContentPane().removeAll();
    JLabel winner = new JLabel(new ImageIcon("./image/win.jpg"));
    winner.setBounds(30, 20, 480, 360);
    this.getContentPane().add(winner);

    loadBK();
    this.getContentPane().repaint();

    return true;
  }

  private boolean isNeighboured(int i) {
    int up = this.place - 4 < 0 ? -1 : this.place - 4;
    int down = this.place + 4 > 15 ? -1 : this.place + 4;
    int left = (this.place - 1) / 4 != (this.place / 4) ? -1 : this.place - 1;
    int right = (this.place + 1) / 4 != (this.place / 4) ? -1 : this.place + 1;
    if (i == up || i == down || i == left || i == right) {
      return true;
    }
    return false;
  }

  private GameJframe loadImage() {

    this.getContentPane().removeAll();

    int x = 20, y = 30;
    int width = 120, height = 90;
    for (int i = 1; i <= 16; i++) {

      jlabs.get(i - 1).j.setBounds(x, y, width, height);

      this.getContentPane().add(jlabs.get(i - 1).j);

      x += width + 5;
      if (i % 4 == 0) {
        y += height + 5;
        x = 20;
      }
    }

    JLabel stepcount = new JLabel("步数:" + num);
    stepcount.setBounds(20, 0, 120, 40);
    this.getContentPane().add(stepcount);

    this.getContentPane().repaint();

    return this;
  }

  private GameJframe massImage() {
    int[] tempArr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
    Random r = new Random();
    for (int i = 0; i < tempArr.length; i++) {
      int index = r.nextInt(tempArr.length);
      int temp = tempArr[i];
      tempArr[i] = tempArr[index];
      tempArr[index] = temp;
    }

    for (int i = 1; i <= 16; i++) {
      SelfJlabel jl = new SelfJlabel();
      String filename = "./image/1_shisa/split_" + tempArr[i - 1] + ".png";
      ImageIcon icon = new ImageIcon(filename);

      jl.j = new JLabel(icon);
      jl.index = tempArr[i - 1];
      jlabs.add(jl);
    }

    return this;
  }

  private GameJframe loadBK() {
    // 加载背景图片
    ImageIcon ic = new ImageIcon("./image/background.jpg");
    JLabel jl = new JLabel(ic);
    jl.setBounds(0, 0, 550, 500);
    jl.setBorder(new BevelBorder(1));
    this.getContentPane().add(jl);
    return this;
  }

  private GameJframe initJMenu() {
    // 初始化菜单
    JMenuBar JmenuBar = new JMenuBar();

    // 创建菜单上的两个选项:功能 关于我们
    JMenu FuncJmenu = new JMenu("功能");
    JMenu AboutJmenu = new JMenu("关于");

    FuncJmenu.add(ChangImage);
    FuncJmenu.add(replay);
    FuncJmenu.add(reload);
    FuncJmenu.add(close);

    AboutJmenu.add(help);

    JmenuBar.add(FuncJmenu);
    JmenuBar.add(AboutJmenu);

    this.setJMenuBar(JmenuBar);

    replay.addActionListener(this);
    close.addActionListener(this);
    help.addActionListener(this);

    return this;
  }

  private GameJframe initJFrame() {
    this.setSize(550, 500);
    this.setTitle("拼图游戏v1.0");
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // 取消默认的居中放置
    this.setLayout(null);

    this.addMouseListener(this);
    this.addKeyListener(this);

    return this;
  }

  @Override
  public void keyTyped(KeyEvent e) {}

  @Override
  public void keyPressed(KeyEvent e) {
    int code = e.getKeyCode();
    if (code == 65) {
      this.getContentPane().removeAll();
      JLabel all = new JLabel(new ImageIcon("./image/1_shisa/all.jpg"));
      all.setBounds(30, 20, 480, 360);
      this.getContentPane().add(all);

      loadBK();
      this.getContentPane().repaint();
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    int code = e.getKeyCode();
    if (code == 65) {
      loadImage();
      loadBK();
    }
    if (code == 87) {
      jlabs.get(0).index = -1;
      win();
    }
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    int x = e.getX();
    int y = e.getY();

    if (x < 20 || x > 520 || y < 85 || y > 465 || place == -2) {
      return;
    }

    int indexx = (y - 85) / 95;
    int indexy = (x - 20) / 125;
    int index = indexx * 4 + indexy;
    if (place == -1) {
      place = index;
      return;
    }
    SelfJlabel secondClick = jlabs.get(index);
    if (isNeighboured(index)) {
      num++;
      SelfJlabel tempJLabel = jlabs.get(index);
      jlabs.set(index, jlabs.get(place));
      jlabs.set(place, tempJLabel);
      loadImage().loadBK();
      place = -1;
      if (win()) {
        return;
      }
    } else {
      System.out.println("不相邻");
      place = -1;
    }
  }

  @Override
  public void mousePressed(MouseEvent e) {}

  @Override
  public void mouseReleased(MouseEvent e) {}

  @Override
  public void mouseEntered(MouseEvent e) {}

  @Override
  public void mouseExited(MouseEvent e) {}

  // 菜单事件
  @Override
  public void actionPerformed(ActionEvent e) {
    Object source = e.getSource();
    if (source == replay) {
      place = -1;
      num = 0;
      if (jlabs.size() != 0) {
        jlabs.clear();
      }
      massImage().loadImage().loadBK();
    } else if (source == close) {
      System.exit(0);
    } else if (source == help) {
      new HelpJframe();
    }
  }
}
