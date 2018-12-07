package com.tarena.bird;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: GAOBO
 * Date: 2018-10-26
 * Time: 15:25
 */

public class Bird {
    /** 鸟飞行位置, 这个位置是鸟的中心位置 */
    int x;	int y;
    /** 飞行角度 */
    double angle;
    /** 动画帧 */
    BufferedImage[] images;
    /** 当前图片 */
    BufferedImage image;
    /** 当前图片序号 */
    int index = 0;
    /** 重力加速度 */
    final double g;
    /** 时间间隔秒 */
    final double t;
    /** 初始上抛速度 */
    final double v0;
    /** 当前上抛速度 */
    double speed;
    /** 移动距离 */
    double s;
    /** 鸟的范围, 鸟的范围是一个正方形区域, 中心点是x,y */
    int size = 26;

    public Bird() throws Exception { //捕获异常
        this.g = 4; //重力加速度
        this.t = 0.25; //每次计算的间隔时间
        this.v0 = 20; //初始上抛时间
        x = 100; //鸟的初始位置
        y = 270; //鸟的初始位置
        //加载动画帧
        images = new BufferedImage[3];
        images[0] = ImageIO.read(getClass().getResource("0.png"));
        images[1] = ImageIO.read(getClass().getResource("1.png"));
        images[2] = ImageIO.read(getClass().getResource("2.png"));
        image = images[0];
    }

    /** 飞行一步
     * 竖直上抛位移计算
     *  (1) 上抛速度计算 V=Vo-gt
     (2) 上抛距离计算 S=Vot-1/2gt^2
     * */
    public void step(){
        //V0 是本次
        double v0 = speed;
        //计算垂直上抛运动, 经过时间t以后的速度,
        double v = v0 - g*t;
        //作为下次计算的初始速度
        speed = v;
        //计算垂直上抛运动的运行距离
        s = v0*t - 0.5 * g * t * t;
        //将计算的距离 换算为 y坐标的变化
        y = y - (int)s;
        //计算小鸟的仰角,
        angle = -Math.atan(s/8);
        //更换小鸟的动画帧图片, 其中/4 是为了调整动画更新的频率
        index++;
        image = images[(index/8)%3];
    }
    /** 小鸟向上飞扬 */
    public void flappy(){
        //每次小鸟上抛跳跃, 就是将小鸟在当前点重新以初始速度 V0 向上抛
        speed = v0;
    }
    //绘制时并发执行的, 要同步避免 旋转坐标系对其他绘制方法的影响
    public synchronized void paint(Graphics g){  //线程安全
        //g.drawRect(x-size/2, y-size/2, size, size);
        Graphics2D g2 = (Graphics2D)g;
        //旋转绘图坐标系, 绘制
        g2.rotate(angle, this.x, this.y);
        //以x,y 为中心绘制图片
        int x = this.x-image.getWidth()/2;
        int y = this.y-image.getHeight()/2;
        g.drawImage(image, x, y, null);
        //旋转回来
        g2.rotate(-angle, this.x, this.y);
    }

    @Override
    public String toString() {
        return "Bird [x=" + x + ", y=" + y + ", g=" + g + ", t=" + t + ", v0="
                + v0 + ", speed=" + speed + ",s="+s+"]";
    }
    /** 判断鸟是否通过柱子 */
    public boolean pass(Column col1, Column col2) {
        return col1.x==x || col2.x==x;
    }
    /** 判断鸟是否与柱子和地发生碰撞 */
    public boolean hit(Column column1, Column column2, Ground ground) {
        //碰到地面
        if(y-size/2 >= ground.y){
            return true;
        }
        //碰到柱子
        return hit(column1) || hit(column2);
    }
    /** 检查当前鸟是否碰到柱子 */
    public boolean hit(Column col){
        //如果鸟碰到柱子: 鸟的中心点x坐标在 柱子宽度 + 鸟的一半
        if( x>col.x-col.width/2-size/2 && x<col.x+col.width/2+size/2){
            if(y>col.y-col.gap/2+size/2 && y<col.y+col.gap/2-size/2 ){
                return false;
            }
            return true;
        }
        return false;
    }

}
