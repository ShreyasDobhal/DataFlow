/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Canvas;


import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

/**
 *
 * @author KALYAN
 */
public abstract class Shapes {
    public int noOfConnectionAtPresent = 0;
    public int limitOfConnection;
    public int x,y,height,width;
    public String text;
    public Graphics g;
    public LinkedList<Shapes> linkedShapes = new LinkedList<Shapes>();
    public void setLocationAndDraw(int x,int y){ 
        if(this.text != null){
            System.out.println("text"+text);
            g.setColor(Color.WHITE);
            g.drawString(this.text,x+width/4,y+height/2);
            Color color = new Color(52, 164, 209);
            g.setColor(color);
        }
    }
    public int getCenterX(){
        return (this.x+this.width/2);
    }
    public int getCenterY(){
        return (this.y+this.height/2);
    }
    public void addShape(Shapes shape){
        this.linkedShapes.add(shape);
    }
    public void addText(String text){
        this.text = text;
    }
}
