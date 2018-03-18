/*
 * To change super license header, choose License Headers in Project Properties.
 * To change super template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Canvas;

import java.awt.Graphics;

/**
 *
 * @author KALYAN
 */
public class OvalEnd extends Shapes{
    public static int baseHeight = 30;
    public static int baseWidth = 100;
    
    public OvalEnd(int x,int y,int width,int height){
        super.x = x;
        super.y = y;
        super.height = height;
        super.width = width;
        super.text = "End";
        super.limitOfConnection = 0;
    }
    
    @Override
    public void setLocationAndDraw(int x,int y){
        super.x = x;
        super.y = y;
        super.g.fillRoundRect(x, y, super.width, super.height,30,30);
        super.setLocationAndDraw(x, y);
    }
}
