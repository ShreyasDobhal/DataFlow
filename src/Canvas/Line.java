/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Canvas;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author KALYAN
 */
public class Line {
    public String ynText;
    public Shapes endShapes[] = new Shapes[2];
    public Graphics g;
    public Line(){
        
    }
    
    public boolean isConnectedWith(Shapes shape){
        //it has exactly 2 elements
        if(this.endShapes.length == 2){
            if(endShapes[0].equals(shape) || endShapes[1].equals(shape)){
                return true;
            }
            return false;    
        }else{
            return false;
        }
    }
    
    public void setInitShape(Shapes shape){
        endShapes[0] = shape;
    } 
    public void setLastShape(Shapes shape){
        endShapes[1] = shape;
    } 
    
    public Shapes getFirstShape(){
        if(endShapes[0] != null)
            return endShapes[0];
        else
            return null;
    }
    
    public Shapes getLastShape(){
        if(endShapes[1] != null)
            return endShapes[1];
        else
            return null;
    }
    
    public void drawLineWhiledragging(int x,int y){
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(5));
        g2.drawLine(this.endShapes[0].getCenterX(), this.endShapes[0].getCenterY(), x, y);
    }
   
    public void drawLine(){
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(5));
        g2.drawLine(this.endShapes[0].getCenterX(), this.endShapes[0].getCenterY(), this.endShapes[1].getCenterX(), this.endShapes[1].getCenterY());
    }
    
    public Shapes getTheOtherEnd(Shapes shape){
        if(this.endShapes.length == 2){
            if(endShapes[0].equals(shape)){
                return endShapes[1];
            }else if(endShapes[1].equals(shape)){
                return endShapes[0];
            }else{
                return null;
            }
        }else{
            return null;
        }
    }
}
