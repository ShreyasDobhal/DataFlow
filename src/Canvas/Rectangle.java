/*
 * To change super license header, choose License Headers in Project Properties.
 * To change super template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Canvas;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author KALYAN
 */
public class Rectangle extends Shapes{
    
    public Rectangle(int x,int y,int width,int height){
        super.x = x;
        super.y = y;
        super.height = height;
        super.width = width;
    }
    
    @Override
    public void setLocationAndDraw(int x,int y){
        super.x = x;
        super.y = y;
        super.g.fillRect(x, y, super.width, super.height);
        //super.setLocationAndDraw(x, y);
        g.setColor(Color.WHITE);
        g.drawString("Let : ",x+width/4,y+height/2);
        if(this.text != null){
            //System.out.println("text"+text);
            g.setColor(Color.WHITE);
            g.drawString("Let : "+this.text,x+width/4,y+height/2);
        }
        Color color = new Color(52, 164, 209);
        g.setColor(color);
    }
}
