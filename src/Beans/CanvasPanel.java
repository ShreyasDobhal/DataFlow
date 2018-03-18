/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Canvas.Comments;
import Canvas.InputParallelogram;
import Canvas.Line;
import Canvas.OutputParallelogram;
import Canvas.OvalEnd;
import Canvas.OvalStart;
import Canvas.Parallelogram;
import Canvas.Rectangle;
import Canvas.Shapes;
import Canvas.rotatedSquare;
import dataflow.MainWindow;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author KALYAN
 */
public class CanvasPanel extends JPanel implements MouseMotionListener,MouseListener{
       
    private ArrayList<Shapes> shapes;
    private ArrayList<Line> lines;
    private Line curLine;
    private boolean isDragged = false;
    private static boolean dragState = true;
    public Graphics graphics;
   
    private int curLineX,curLineY;
    public static  CanvasPanel drawingPannel;
    private static boolean deleteState=false;
    private boolean isStartPresent=false;
    public Shapes startNode=null;
    public ArrayList<Comments> comments;
    
    
    public CanvasPanel(){
        addMouseMotionListener(this);
        addMouseListener(this);
        drawingPannel = this;
        shapes = new ArrayList<Shapes>();
        lines = new ArrayList<Line>();
        comments = new ArrayList<Comments>();

//         Oval oval = new Oval(50,50,100,25);
//         shapes.add(oval);

       //System.out.println(para.linkedShapes.get(0).toString()); 
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
         // draw entire screen white
        
        ///graphics =  g;
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());

        Color color = new Color(52, 164, 209);
        g.setColor(color);
        
        for(Line line:lines){
            if(line != null){
                graphics = g;
                line.g = g;
            }
            if(line.endShapes[1] != null){
                //when the line is fully perfect
                line.drawLine();
                if(!isEmpty(line.ynText)){
                    g.drawString(line.ynText, (line.endShapes[0].getCenterX()+line.endShapes[1].getCenterX())/2
                            , (line.endShapes[0].getCenterY()+line.endShapes[1].getCenterY())/2);
                }
            }
            else if(line.endShapes[0] != null){
                //when the line is but while dragging
                line.drawLineWhiledragging(curLineX, curLineY);
            }
        }
        for(Shapes shape:shapes){
            if(shape != null)
                graphics = g;
                shape.g = g;
            shape.setLocationAndDraw(shape.x,shape.y);
        } 
        
        Color col = new Color(0,0,0);
        g.setColor(col);
        
        for(Comments comment:comments){
            if(comment != null){
                comment.g = g;
                comment.showHere();
            }
        } 
//        System.out.println("paintComponent is called");
    }
//    public static void main(String[] args){
//        Tutorials t = new Tutorials();
//        
//        JFrame jf = new JFrame();
//        jf.setTitle("Graphics");
//        jf.setSize(1200,800);
//        jf.setVisible(true);
//        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        jf.add(t);
//    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int x=e.getX();
        int y=e.getY();
        if(dragState && !deleteState ){
            for(Shapes shape:shapes){
                if (shape.x+shape.width>x && shape.x<x && shape.y+shape.height>y && shape.y<y) {
                    shape.x=x-shape.width/2;
                    shape.y=y-shape.height/2; 
                    break;
                }
            }
            isDragged = true;
        }else{
            //here i m drawing the lines between the objects
            curLineX = x;
            curLineY = y;
        }
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
       
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        int x=e.getX();
        int y=e.getY();
       if (e.getClickCount() == 1 && dragState) {
            //here we will add the text to the corresponding component
            System.out.println("single-click");
            for(Shapes shape:shapes){
                if (shape.x+shape.width>x && shape.x<x && shape.y+shape.height>y && shape.y<y) {
                    //System.out.println(shape.getClass().toString()+"");
                    //we will write something to the text of the corresponding shape if it is not a startOval or an endOval
                    if(!shape.getClass().toString().toLowerCase().contains("oval")){
                        System.out.println("shape pressed in canvas panel");
                        PopupTextField pop = new PopupTextField(MainWindow.MainFrame, true);
                        pop.setLocation(e.getXOnScreen(), e.getYOnScreen());
                        pop.saveTextToShape(shape);
                        System.out.println("shape passed to saveTextToShape "+shape.getClass().toString());
                        if(!isEmpty(shape.text)){
                            pop.txtInput.setText(shape.text);
                        }
                        pop.setVisible(true);
                        break;
                    }
                }
            }
        }
        //To change body of generated methods, choose Tools | Templates.
        //System.out.println("Kalyan is a good boy.");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //The starting shape is selected
        int x=e.getX();
        int y=e.getY();
        if(dragState){
        }else if (!deleteState){
            curLine = new Line();
            curLine.g = graphics;
            lines.add(curLine);
            for(Shapes shape:shapes){
                if (shape.x+shape.width>x && shape.x<x && shape.y+shape.height>y && shape.y<y) {
                    curLine.setInitShape(shape);
                    System.out.println("curLine 1st shape set for" + curLine.toString());
                    break;
                }
            }
            
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int x=e.getX();
        int y=e.getY();
        if (e.getClickCount() == 2) {
            //this block is executed when double click happens
            if(dragState){
                if(!isDragged){
                    //i have to check for that ki only one start should be present on the canvas
                    if(ToolBar.selectedObject == 'o' && !isStartPresent){
                        addComponents(ToolBar.selectedObject,x,y);
                        isStartPresent = true;
                    }else if(ToolBar.selectedObject != 'o'){
                        //if the object is other than start then directlly place it on the canvas
                        addComponents(ToolBar.selectedObject,x,y);
                    }
                }
                isDragged = false;
            }
            System.out.println("isStartPresent : " + isStartPresent);
        }else if(!dragState && !deleteState){
            //set the second shape here           
            for(Shapes shape:shapes){
                if (shape.x+shape.width>x && shape.x<x && shape.y+shape.height>y && shape.y<y) {
                    //check wheather the 2nd object is not the firstobject
                    if(shape != curLine.getFirstShape()){
                        if(curLine.getFirstShape().getClass().toString().toLowerCase().contains("square") 
                            && curLine.getFirstShape().noOfConnectionAtPresent < curLine.getFirstShape().limitOfConnection){
                            System.out.println("inside square start");
                        //that means the first shape for the line is the if block
                            curLine.getFirstShape().noOfConnectionAtPresent++;
                            curLine.setLastShape(shape);
                            //add the next shape where the link is connected to the linkedlist of the firstShape
                            System.out.println("inside square adding");
                            //curLine.getFirstShape().addShape(shape);
                            
                            IfPopup pop = new IfPopup(MainWindow.MainFrame, true);
                           pop.setLocation(x,y);
                                   //curLine.endShapes[0].getCenterX()+curLine.endShapes[1].getCenterX(), curLine.endShapes[0].getCenterY()+curLine.endShapes[1].getCenterY());
                            pop.setYesNo(curLine.getFirstShape(),shape,curLine);
                            pop.setVisible(true);
                            System.out.println("inside square end");
                            break;
                        }else if(!curLine.getFirstShape().getClass().toString().toLowerCase().contains("square")
                                && curLine.getFirstShape().noOfConnectionAtPresent < curLine.getFirstShape().limitOfConnection){
                            System.out.println("not inside square");
                            curLine.getFirstShape().noOfConnectionAtPresent++;
                            curLine.setLastShape(shape);
                            //add the next shape where the link is connected to the linkedlist of the firstShape
                            System.out.println("not inside square adding");
                            curLine.getFirstShape().addShape(shape);
                            System.out.println("not inside square end");
                            break;
                        }
                    }
                }
            }
            if(curLine.getLastShape() == null){
                lines.remove(curLine);
            }
            
            repaint();
            printConnections();
        }else if(deleteState){
            //here i can delete objects
            if(dragState){
                //only the shapes canbe deleted
                System.err.println("Delete a object here");
                for(int i = 0;shapes.size() > i;i++){
                    if (shapes.get(i).x+shapes.get(i).width>x && shapes.get(i).x<x 
                            && shapes.get(i).y+shapes.get(i).height>y && shapes.get(i).y<y) {
                        Shapes shapeTobeDeleted = shapes.get(i);
                        //if i m deleting the start then i should make the isStartPresent to false again
                        if(shapeTobeDeleted.getClass().toString().toLowerCase().trim().contains("ovalstart")){
                            isStartPresent = false;
                        }
                        shapes.remove(shapeTobeDeleted);
                        for(int j = 0;lines.size() > j;j++){
                            if(lines.get(j).isConnectedWith(shapeTobeDeleted)){
                                Shapes otherEnd = lines.get(j).getTheOtherEnd(shapeTobeDeleted);
                                otherEnd.noOfConnectionAtPresent--;
                                otherEnd.linkedShapes.remove(shapeTobeDeleted);
                                lines.remove(lines.get(j));
                                j--;
                            }
                        }  
                        break;
                    }
                }
                repaint();
                System.out.println("size of shapes in mouse" + shapes.size());
                printConnections();
            }else{
                //only the lines can be deleted
                 System.err.println("Delete a line here");
                 x = e.getX();
                 y = e.getY();
                 
                   for(int i = lines.size()-1; i>=0;i--){
                
                     curLine = lines.get(i);
                     
                     int r = 10;
                     Shapes startShape = curLine.endShapes[0];
                     Shapes endShape = curLine.endShapes[1];
                     int allXOfPoly[] = {startShape.x-r,endShape.x-r,endShape.x+r,startShape.x+r};
                     int allYOfPoly[] = {-1*startShape.y,-1*endShape.y,-1*endShape.y,-1*startShape.y};
                     
                        System.out.println(startShape.getClass().toString());
                        System.out.println(endShape.getClass().toString());
                        System.err.println("The line to be deleted is " + curLine.getClass().toString());
                        System.out.println("Deleting line number : "+i);
                        lines.remove(curLine);
                        //remove the shapes
                        startShape.linkedShapes.remove(endShape);
                        endShape.linkedShapes.remove(startShape);
                        startShape.noOfConnectionAtPresent--;
                        endShape.noOfConnectionAtPresent--;
                        if(startShape.getClass().toString().toLowerCase().contains("square")){
                            startShape.noOfConnectionAtPresent--;
                        }
                        repaint();
                       
                        break;
                 }
            }  
        }
    }

    public boolean isInside(Shapes start,Shapes end,int x,int y){
        int err = 5;
        if(start.x != end.x){
            double m = (end.y-start.y)*1.0/(end.x - start.x);
            if(Math.abs(y - m*x + m*start.x - start.y) < err){
                return true;
            }else{
                return false;
            }
        }else{
            if(y > Math.min(start.y,end.y) && y < Math.max(start.y,end.y)){
                if(Math.abs(x - start.x)<err){
                    return true;
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    
    public static void changeState(boolean state){
        dragState = state;
    }
    
    public static void changeDeleteState(boolean state) {
        deleteState=state;
    }
    
    public void printConnections(){
        System.err.println("Printing all connections");
        System.out.println("size of shapes " + shapes.size());
        int size = shapes.size();
        if(size > 0){
            
            int i = 0;
            while(size > i){
                Shapes s = shapes.get(i);
                LinkedList list = s.linkedShapes;
                int j = 0;
                while(list.size() > j){
                   System.out.println(s.getClass()+" is connected to " + list.get(j).getClass());   
                   j++;
                }
                i++;
            }
        }
        //System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
    }
    
    public void addComponents(char name,int x,int y){
    
        switch(name){
            case 'o':
                OvalStart ovalStart = new OvalStart(50,50,100,50);//(50,50,100,25);
                shapes.add(ovalStart);
                startNode=ovalStart;
                break;
            case 'p':
                InputParallelogram para = new InputParallelogram(50, 200, 175, 60);//(50, 200, 100, 30);
                shapes.add(para);
                break;
            case 'c':
                OutputParallelogram opara = new OutputParallelogram(50, 200, 175, 60);//(50, 200, 100, 30);
                shapes.add(opara);
                break;
            case 'r':
                Rectangle rect = new Rectangle(200,50,150,50);//(200,50,100,25);
                shapes.add(rect);
                break;
            case 's':
                rotatedSquare rotSqr = new rotatedSquare(200, 200, 150, 120);//(200, 200, 100, 80);
                shapes.add(rotSqr);
                break;
            case 'e':
                OvalEnd ovalEnd = new OvalEnd(50,50,100,50);//(50,50,100,25);
                shapes.add(ovalEnd);
                break;
        }
        shapes.get(shapes.size()-1).x = x - shapes.get(shapes.size()-1).width/2;
        shapes.get(shapes.size()-1).y = y - shapes.get(shapes.size()-1).height/2;
        repaint();
    }
    
     private boolean isEmpty(String str){
        if(str == null || str.trim().equals(""))
            return true;
        else
            return false;
    }
     
     public void addComment(String cmnt,int x,int y){
         
     } 
}