/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Canvas.InputParallelogram;
import Canvas.OutputParallelogram;
import Canvas.OvalEnd;
import Canvas.OvalStart;
import Canvas.Parallelogram;
import Canvas.Rectangle;
import Canvas.RectangleTool;
import Canvas.Shapes;
import Canvas.rotatedSquare;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author KALYAN
 */

/*
    

*/
public class ToolBar extends javax.swing.JPanel implements MouseListener{

    private ArrayList<Shapes> shapes;
    public static char selectedObject = 'o';
    private Shapes selectedShape;
    /**
     * Creates new form ToolBar
     */
    
    
    RectangleTool mainRect,insideRect,compoRect,curRect,dividerRect,horRect1,horRect2,whiteRect;
    Shapes curShape = new OvalStart(20,((int)((getHeight() - 40)*2.0/3))+26,(getWidth() - 40)/2,(((int)((getHeight()-40)*1.0/3))-3)/2);
    Color col;
    public ToolBar() {
        initComponents();
        
        addMouseListener(this);
        shapes = new ArrayList<Shapes>();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        whiteRect= new RectangleTool(0 , 0, getWidth(),getHeight());
        whiteRect.g = g;
        col = new Color(255,255,255);
        g.setColor(col);
        whiteRect.setLocationAndDraw(0,0);
        
        mainRect = new RectangleTool(0, 0, getWidth(), getHeight());
        mainRect.g = g;
        col = new Color(187,222,251);
        g.setColor(col);
        mainRect.setLocationAndDraw(0,0);

        insideRect = new RectangleTool(0, 0, getWidth() - 20, getHeight()-20);
        insideRect.g = g;
        col = new Color(13,71,161);
        g.setColor(col);
        insideRect.setLocationAndDraw(10,10);
        
        compoRect = new RectangleTool(0, 0, getWidth() - 40, ((int)((getHeight()-40)*2.0/3)) - 3);
        compoRect.g = g;
        col = new Color(227,242,253);
        g.setColor(col);
        compoRect.setLocationAndDraw(20,20);
        
        curRect = new RectangleTool(0, 0, getWidth() - 40,(((int)((getHeight()-40)*1.0/3))-3));
        curRect.g = g;
        col = new Color(227,242,253);
        g.setColor(col);
        curRect.setLocationAndDraw(20,((int)((getHeight() - 40)*2.0/3))+26);
        
        
        dividerRect = new RectangleTool(0, 0, 6,((int)((getHeight()-40)*2.0/3)) - 3);
        dividerRect.g = g;
        col = new Color(13,71,161);
        g.setColor(col);
        dividerRect.setLocationAndDraw(getWidth()/2-3,20);
        
        horRect1 = new RectangleTool(0, 0,getWidth() - 40,6);
        horRect1.g = g;
        col = new Color(13,71,161);
        g.setColor(col);
        horRect1.setLocationAndDraw(20,20 + (int)((((int)((getHeight()-40)*2.0/3))-3)*1.0/3) - 3);
                
        horRect2 = new RectangleTool(0, 0,getWidth() - 40,6);
        horRect2.g = g;
        col = new Color(13,71,161);
        g.setColor(col);
        horRect2.setLocationAndDraw(20,20 + (int)((((int)((getHeight()-40)*2.0/3))-3)*2.0/3) - 3);
        
        
        int sizeX=((getWidth()/2-3)-20);
        int sizeY=((int)((((int)((getHeight()-40)*2.0/3))-3)*1.0/3) - 3);
        
        OvalStart ovalStart = new OvalStart(20+sizeX/4,20+sizeY/4,sizeX/2,sizeY/2);
        shapes.add(ovalStart);
        selectedShape = ovalStart;
        Rectangle rect = new Rectangle(getWidth()/2+3+sizeX/6,20+sizeY/4,sizeX*2/3,sizeY/2);
        shapes.add(rect);
        rotatedSquare rotSqr = 
                new rotatedSquare(20+sizeX/4, 20 + +sizeY/4+(int)((((int)((getHeight()-40)*2.0/3))-3)*1.0/3)+3,sizeX/2,sizeY/2);
        shapes.add(rotSqr);
        InputParallelogram para = new InputParallelogram(getWidth()/2+3+sizeX/4, 20 +sizeY/4+ (int)((((int)((getHeight()-40)*2.0/3))-3)*1.0/3)+3,sizeX/2,sizeY/2);
        shapes.add(para);
        OutputParallelogram opara = new OutputParallelogram(20+sizeX/4,20 +sizeY/4+ (int)((((int)((getHeight()-40)*2.0/3))-3)*2.0/3) + 3,sizeX/2,sizeY/2);
        shapes.add(opara);
        OvalEnd ovalEnd = new OvalEnd(getWidth()/2+3+sizeX/4,20 +sizeY/4+ (int)((((int)((getHeight()-40)*2.0/3))-3)*2.0/3) + 3,sizeX/2,sizeY/2);
        shapes.add(ovalEnd);
        
        
       
        Color color = new Color(52, 164, 209);
        g.setColor(color);
        for(Shapes shape:shapes){
            if(shape != null)
                shape.g = g;
            shape.setLocationAndDraw(shape.x,shape.y);
        } 

        curShape.g = g;
        g.setColor(color);
        curShape.setLocationAndDraw(20 + (getWidth() - 40)/4, ((int)((getHeight() - 40)*2.0/3))+26 +(((int)((getHeight()-40)*1.0/3))-3)/4);
        
    }
    
//    public static void main(String arg[]){
//        ToolBar toolbar = new ToolBar();
//        JFrame jf = new JFrame();
//        jf.setTitle("ToolBar");
//        jf.setSize(400,600);
//        jf.setVisible(true);
//        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        jf.add(toolbar);
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        selectedShape = null;
        for(Shapes shape:shapes){
            if (shape.x+shape.width>x && shape.x<x && shape.y+shape.height>y && shape.y<y) {
                selectedShape = shape;
                break;
            }
        }
        if(selectedShape != null){
            if(selectedShape.getClass().toString().toLowerCase().contains("ovalstart")){
                selectedObject = 'o';
                curShape = new OvalStart(20,((int)((getHeight() - 40)*2.0/3))+26,(getWidth() - 40)/2,(((int)((getHeight()-40)*1.0/3))-3)/2);
            }else if(selectedShape.getClass().toString().toLowerCase().contains("rect")){
                selectedObject = 'r';
                curShape = new Rectangle(20,((int)((getHeight() - 40)*2.0/3))+26,(getWidth() - 40)/2,(((int)((getHeight()-40)*1.0/3))-3)/2);
            }else if(selectedShape.getClass().toString().toLowerCase().contains("square")){
                selectedObject = 's';
                curShape = new rotatedSquare(20,((int)((getHeight() - 40)*2.0/3))+26,(getWidth() - 40)/2,(((int)((getHeight()-40)*1.0/3))-3)/2);
            }else if(selectedShape.getClass().toString().toLowerCase().contains("inputpara")){
                selectedObject = 'p';
                curShape = new InputParallelogram(20,((int)((getHeight() - 40)*2.0/3))+26,(getWidth() - 40)/2,(((int)((getHeight()-40)*1.0/3))-3)/2);
            }else if(selectedShape.getClass().toString().toLowerCase().contains("outputpara")){
                selectedObject = 'c';
                curShape = new OutputParallelogram(20,((int)((getHeight() - 40)*2.0/3))+26,(getWidth() - 40)/2,(((int)((getHeight()-40)*1.0/3))-3)/2);
            }else if(selectedShape.getClass().toString().toLowerCase().contains("ovalend")){
                selectedObject = 'e';
                curShape = new OvalEnd(20,((int)((getHeight() - 40)*2.0/3))+26,(getWidth() - 40)/2,(((int)((getHeight()-40)*1.0/3))-3)/2);
            }
            System.out.println(selectedShape.getClass().toString().toLowerCase());
            
            repaint();
        }
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
