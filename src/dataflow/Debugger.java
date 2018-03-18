/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataflow;

import Canvas.InputParallelogram;
import Canvas.OutputParallelogram;
import Canvas.OvalEnd;
import Canvas.Rectangle;
import Canvas.Shapes;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Shreyas
 */
public class Debugger {
    
    private MainWindow parent=null;
    private HashMap<String,String> variables=null;
    private HashMap<String,String> values=null;
    private int delayTime=500;
    private boolean nextStep=true;
    private boolean isPlay=true;
    private Queue<String> output;
    
    public Debugger(MainWindow parent) {
        this.parent=parent;
        variables=new HashMap<>();
        values=new HashMap<>();
        output=new LinkedList<>();
    }
    public void startDebugger(Shapes start) {
        Shapes block=start;
        while (!block.getClass().equals(OvalEnd.class)) {
            if (block.linkedShapes.isEmpty()) {
                System.out.println("Un expected interrupt in links");
                break;
            }
            else if (block.getClass().equals(Rectangle.class)) {
                String text=block.text;
                String var=text.substring(0,text.indexOf('='));
                String value=text.substring(text.indexOf('=')+1);
                variables.put(var,"int");
                values.put(var,value);
            }
            else if (block.getClass().equals(InputParallelogram.class)) {
                String text=block.text;
                String val=parent.txtDebugInput.getText();
                text=text.trim();
                values.put(text,val);
                parent.txtDebugInput.setText("");
            }
            else if (block.getClass().equals(OutputParallelogram.class)) {
                String text=block.text;
                if (variables.get(text)!=null) {
                    output.add(values.get(text));
                }
            }
            
            block=block.linkedShapes.get(0);
        }
        System.out.println(" ----------- Output ------");
        parent.txtDebugOutput.setText("");
        for (String s:output) {
            System.out.println(s);
            parent.txtDebugOutput.append(s);
        }
    }
    
}
