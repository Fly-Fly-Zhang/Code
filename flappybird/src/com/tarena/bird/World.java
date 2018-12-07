package com.tarena.bird;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class World extends JPanel {

    Column column1;
    Column column2;
    Bird bird;
    Ground ground;
    BufferedImage background;
    BufferedImage gameoverImg;
    BufferedImage startImg;
    BufferedImage revival0Img;

    boolean start;
    int score;  //得分
    boolean gameOver;
    int n;
    int revival;
    boolean revival0;
    String str;
    String [] str1={"滴滴！","过一个","再过一个","冲鸭","垃圾uzi","hjmisdidi","满血+1"};
    public World() throws IOException { //异常检查
        background = ImageIO.read(getClass().getResource("bg.png"));
        gameoverImg = ImageIO.read(getClass().getResource("gameover.png"));
        startImg = ImageIO.read(getClass().getResource("start.png"));
        start();
    }
    public void start(){
        try {
            start = false;
            gameOver = false;
            bird = new Bird();
            ground = new Ground(400);
            column1 = new Column(320 + 100);
            column2 = new Column(320 + 100 + 180);
            score = 0;
            n=0;
            revival=10000;
            str="";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void action() throws Exception{
        addMouseListener(new MouseAdapter() {  //鼠标接口
            @Override
            public void mousePressed(MouseEvent e) {
                if (gameOver) {
                    start();
                    return;
                }
                start = true;
               bird.flappy();
            }
        });
        requestFocus(); //获取焦点
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {  //按下某个键时调用此方法
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    if(gameOver){
                        start();
                        return;
                    }
                    start = true;
                    bird.flappy();
                }
            }
        });
        //主循环, 时间间隔是 1/60 秒
         while(true){
             if(start && !gameOver){
                bird.step();//鸟开始运动
                column1.step();
                column2.step();
                //检查是否通过柱子了
                if(bird.pass(column1, column2)){
                    str=str1[score%7];
                    System.out.println(str);
                    score++; //分数加1
                    n++;//通过柱子数加1
                    System.out.println("n"+n);
                    if(n==6){
                        revival+=1;
                        System.out.println("re"+revival);
                        n=0;
                    }
                }
                if(bird.hit(column1, column2, ground)){ //如果发生碰撞
                        if(revival==0){
                            System.out.println("死");
                            start = false;
                            gameOver = true;
                        }else {

                            revival--;
                            System.out.println("revival="+revival);
                            bird.y=column1.y+5;
                            column1.x=bird.x-40;
                            column2.x=bird.x-40+180;
                        }
                }
            }
             ground.step();
             repaint();//重绘
             Thread.sleep(1000/60);//让线程暂停一段时间
         }

    }
    @Override
    public void paint(Graphics g) {
        //抗锯齿代码
        Graphics2D g2 = (Graphics2D)g;
        RenderingHints qualityHints = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        qualityHints.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHints(qualityHints);
        //绘制背景
        g.drawImage(background, 0, 0, null);
        //绘制柱子
        column1.paint(g);
        column2.paint(g);
        //绘制地面
        ground.paint(g);
        //绘制分数
        Font font = new Font(Font.MONOSPACED, Font.BOLD, 30);
        g.setFont(font);
        g.setColor(Color.white);
        g.drawString(score+"", 20, 50);
        //绘制生命
        Font font2 = new Font(Font.MONOSPACED, Font.BOLD, 30);
        g.setFont(font2);
        g.setColor(Color.red);
        g.drawString(revival+"", 50, 50);
        //绘制语言
        Font font1 = new Font(Font.MONOSPACED, Font.BOLD, 25);
        g.setFont(font1);
        g.setColor(Color.green);
        g.drawString(str+"", 30, 90);
        //绘制小鸟
        bird.paint(g);
        //绘制结束状态
        if(gameOver){
            //g.drawString("Game Over!", 70 , 190);
            g.drawImage(gameoverImg, 0, 0, null);
            return;
        }
        if(! start){
            //g.drawString("Start >>>", bird.x+35, bird.y);
            g.drawImage(startImg, 0, 0, null);
        }
    }
    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame("飞扬小鸟");
        World world = new World();
        frame.add(world);
        frame.setSize(325, 505);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//结束窗口所在的应用程序。在窗口被关闭的时候会退出JVM。
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        world.action();
    }
}
