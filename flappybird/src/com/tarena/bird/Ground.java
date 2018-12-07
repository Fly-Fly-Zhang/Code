package com.tarena.bird;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: GAOBO
 * Date: 2018-10-26
 * Time: 15:26
 */

public class Ground {
    BufferedImage image;

    int x;
    int y;
    int width;
    int height;
    public Ground(int y) throws IOException {
        image = ImageIO.read(getClass().getResource("ground.png"));
        this. y = y;
        width = image.getWidth();
        height = image.getHeight();
        x = 0;
    }

    public void step(){
        x--;
        if(x<= -(width-358)){
            x=0;
        }
    }

    public void paint(Graphics g) {  //绘图
        g.drawImage(image, x, y, null);
    }
}

