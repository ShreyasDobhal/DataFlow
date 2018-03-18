/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Canvas;

/**
 *
 * @author KALYAN
 */
public class Parallelogram extends Shapes{
     private int allx[]=new int[4];
    private int ally[]=new int[4];

    public Parallelogram(int x,int y,int width,int height){
        super.x = x;
        super.y = y;
        super.height = height;
        super.width = width;
        super.limitOfConnection = 1;
    }
    
    @Override
    public void setLocationAndDraw(int x,int y){
        super.x = x;
        super.y = y;
        
        allx[0] = (int) (x+ (double)height/Math.sqrt(3));
        allx[1] = x+width;
        allx[2] = (int)(x+width-(double)height/Math.sqrt(3));
        allx[3] = x;
        
        ally[0] = y;
        ally[1] = y;
        ally[2] = y+height;
        ally[3] = y+height;
        super.g.fillPolygon(allx, ally,4);
        super.setLocationAndDraw(x, y);
    }
}
