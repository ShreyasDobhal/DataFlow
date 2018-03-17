/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Canvas;

import java.awt.Color;

/**
 *
 * @author KALYAN
 */
public class rotatedSquare extends Shapes{
    private int allx[]=new int[4];
    private int ally[]=new int[4];

    public rotatedSquare(int x,int y,int width,int height){
        super.x = x;
        super.y = y;
        super.height = height;
        super.width = width;
        super.limitOfConnection = 2;
    }
    
    @Override
    public void setLocationAndDraw(int x,int y){
        super.x = x;
        super.y = y;
        
        allx[0] = x+width/2;
        allx[1] = x+width;
        allx[2] = x+width/2;
        allx[3] = x;
        
        ally[0] = y;
        ally[1] = y+height/2;
        ally[2] = y+height;
        ally[3] = y+height/2;
        super.g.fillPolygon(allx, ally,4);
        g.setColor(Color.WHITE);
        g.drawString("If : ",x+width/4,y+height/2);
        if(this.text != null){
            //System.out.println("text"+text);
            g.setColor(Color.WHITE);
            g.drawString("If : "+this.text,x+width/4,y+height/2);
        }
        Color color = new Color(52, 164, 209);
        g.setColor(color);
        
    }
}
