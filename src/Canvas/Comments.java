/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Canvas;

import java.awt.Graphics;

/**
 *
 * @author Shreyas
 */
public class Comments {
    String txt;
    public int x,y;
    public Graphics g;
    public Comments(String str,int x,int y){
        this.txt = str;
        this.x = x;
        this.y = y;
    }
    
    public void showHere(){
        if(g != null){
            g.drawString(this.txt, this.x, this.y);
        }
    }
}
