/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataflow;

import Canvas.InputParallelogram;
import Canvas.OutputParallelogram;
import Canvas.OvalEnd;
import Canvas.OvalStart;
import Canvas.Rectangle;
//import static Canvas.OvalStart;
import Canvas.Shapes;
import Canvas.rotatedSquare;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**
 *
 * @author Shreyas
 */
public class CodeGenerator {
    
    Queue<Shapes> input=new LinkedList<>();
    Set<String> variables;
    ArrayList<String> code;
    HashMap<String,String> varDataType;
    
    private final String importC="#include<stdio.h> \n#include<stdlib.h> \n";
    private final String importJava="import java.io.*; \nimport java.util.*; \n";
    private final HashMap<String,String> printSpecifier;
    private int cursor=0;
    private int nestedLvl=0;
    
    public CodeGenerator(Queue<Shapes> input) {
        this.input=input;
        variables=new HashSet<>();
        code=new ArrayList<>();
        varDataType=new HashMap<>();
        printSpecifier=new HashMap<>();
        addSpecifier();
    }
    
    private void addSpecifier() {
        printSpecifier.put("int","%d");
        printSpecifier.put("char","%c");
        printSpecifier.put("string","%s");
        printSpecifier.put("double","%lf");
    }
    
    public void getJavaCode() {
        for (Shapes block:input) {
            if (block.getClass().equals(OvalStart.class)) {
                
            }
        }
    }
    public ArrayList<String> getCCode() {
        for (Shapes block:input) {
            if (block.getClass().equals(OvalStart.class)) {
                // Start Block
                code.add(cursor++,importC+" \n");
                code.add(cursor++,"int main() { \n");
                code.add(cursor++,"return 0; \n} \n");
                cursor--;
                nestedLvl++;
            }
            else if (block.getClass().equals(Rectangle.class)) {
                // Let Block
                String text=block.text;
                String var;
                String value;
                if (text.indexOf('=')!=-1) {
                    var=text.substring(0,text.indexOf('='));
                    value=text.substring(text.indexOf('=')+1);
                     
                    if (!variables.contains(var)) {
                        // variable declaration
                        variables.add(var);
                        if (value.indexOf('\"')!=-1) {
                            varDataType.put(var,"char[1000]");
                        }
                        else if (value.indexOf('.')!=-1) {
                            varDataType.put(var,"double");
                        }
                        else if (value.indexOf('\'')!=-1) {
                            varDataType.put(var,"char");
                        }
                        else  {
                            varDataType.put(var,"int");
                        }
                        code.add(cursor++,varDataType.get(var)+" "+var+" = "+value+" ; \n");
                    }
                    else {
                        // variable updation
                        code.add(cursor++,var+" = "+value+" ; \n");
                    }
                    
                }
                else {
                    // Variable Declaration with no initialization;
                    var=text;
                    variables.add(var);
                    varDataType.put(var,"int");
                    code.add(cursor++,varDataType.get(var)+" "+var+" ; \n");
                }
            }
            else if (block.getClass().equals(OutputParallelogram.class)) {
                // Print Block
                String text=block.text;
                text=text.trim();
                if (variables.contains(text)) {
                    code.add(cursor++,"printf( \""+ printSpecifier.get(varDataType.get(text)) +"\", "+ text +"); \n");
                }
                else {
                    code.add(cursor++,"printf( \""+text+"\" ); \n");
                }
            }
            else if (block.getClass().equals(InputParallelogram.class)) {
                // Input Block
                String text=block.text;
                text=text.trim();
                if (variables.contains(text)) {
                    code.add(cursor++,"scanf( \""+ printSpecifier.get(varDataType.get(text)) +"\", &"+ text +"); \n");
                }
                else {
                    variables.add(text);
                    varDataType.put(text,"int");
                    code.add(cursor++,"scanf( \""+ printSpecifier.get(varDataType.get(text)) +"\", &"+ text +"); \n");
                }
            }
            else if (block.getClass().equals(OvalEnd.class)) {
                // Stop Block
                if (nestedLvl==1) {
                    // simply exit the main;
                    cursor++;
                }
            }
        }
        return code;
    }
    
    public ArrayList<String> generateCode(Shapes start) {
        //Set<Shapes> visited = new HashSet<>();
        Stack<Shapes> stack=new Stack<>();
        Stack<String> nestedBlocks=new Stack<>();
        //visited.add(start);
        stack.push(start);
        nestedLvl=0;
        boolean ifBlock=false;
        Shapes ifComponent=null;
        int ifIndex=0;
        
        while (!stack.isEmpty()) {
            Shapes block=stack.pop();
            
            if (block.getClass().equals(OvalStart.class)) {
                // Start Block
                code.add(cursor++,importC+" \n");
                code.add(cursor++,"int main() { \n");
                code.add(cursor++,"return 0; \n} \n");
                cursor--;
                nestedBlocks.push("main");
                nestedLvl++;
            }
            else if (block.getClass().equals(Rectangle.class)) {
                // Let Block
                String text=block.text;
                String var;
                String value;
                if (text.indexOf('=')!=-1) {
                    var=text.substring(0,text.indexOf('='));
                    value=text.substring(text.indexOf('=')+1);
                     
                    if (!variables.contains(var)) {
                        // variable declaration
                        variables.add(var);
                        if (value.indexOf('\"')!=-1) {
                            varDataType.put(var,"char[1000]");
                        }
                        else if (value.indexOf('.')!=-1) {
                            varDataType.put(var,"double");
                        }
                        else if (value.indexOf('\'')!=-1) {
                            varDataType.put(var,"char");
                        }
                        else  {
                            varDataType.put(var,"int");
                        }
                        code.add(cursor++,varDataType.get(var)+" "+var+" = "+value+" ; \n");
                    }
                    else {
                        // variable updation
                        code.add(cursor++,var+" = "+value+" ; \n");
                    }
                    
                }
                else {
                    // Variable Declaration with no initialization;
                    var=text;
                    variables.add(var);
                    varDataType.put(var,"int");
                    code.add(cursor++,varDataType.get(var)+" "+var+" ; \n");
                }
            }
            else if (block.getClass().equals(OutputParallelogram.class)) {
                // Print Block
                String text=block.text;
                text=text.trim();
                if (variables.contains(text)) {
                    code.add(cursor++,"printf( \""+ printSpecifier.get(varDataType.get(text)) +"\", "+ text +"); \n");
                }
                else {
                    code.add(cursor++,"printf( \""+text+"\" ); \n");
                }
            }
            else if (block.getClass().equals(InputParallelogram.class)) {
                // Input Block
                String text=block.text;
                text=text.trim();
                if (variables.contains(text)) {
                    code.add(cursor++,"scanf( \""+ printSpecifier.get(varDataType.get(text)) +"\", &"+ text +"); \n");
                }
                else {
                    variables.add(text);
                    varDataType.put(text,"int");
                    code.add(cursor++,"scanf( \""+ printSpecifier.get(varDataType.get(text)) +"\", &"+ text +"); \n");
                }
            }
            else if (block.getClass().equals(OvalEnd.class)) {
                // Stop Block
                if (nestedLvl==1) {
                    // simply exit the main;
                    cursor++;
                    nestedLvl--;
                    nestedBlocks.pop();
                }
                else {
                    nestedLvl--;
                    cursor++;
                    if (nestedBlocks.pop().equals("ifelse")) {
                        code.add(cursor++,"else { \n");
                        code.add(cursor++,"} \n");
                        cursor--;
                        nestedLvl++;
                        nestedBlocks.push("else");
                    }
                }
                
                if (nestedLvl==0 || stack.isEmpty()) {
                    break;
                }
            }
            
            
            if (block.getClass().equals(rotatedSquare.class)) {
                boolean skip=false;
                // If Block
                //ifBlock=true;
                if (ifComponent==null) {
                    System.out.println("Initially null now its not");
                    ifComponent=block;
                    ifIndex=cursor;
                }
                else {
                    if (ifComponent.equals(block)) {
                        String ifLoop = code.get(ifIndex);
                        ifLoop=ifLoop.replace("if","while");
                        code.remove(ifIndex);
                        code.add(ifIndex,ifLoop);
                        nestedLvl--;
                        cursor++;
                        nestedBlocks.pop();
                        skip=true;
                        //stack.push(block.linkedShapes.get(1));
                    }
                }
                
                if (!skip) {
                    //System.out.println("size of if block "+block.linkedShapes.size());
                    if (block.linkedShapes.size()==1) {
                        // only yes available
                        String text=block.text;
                        code.add(cursor++,"if ( "+text+" ) { \n");
                        code.add(cursor++,"} \n");
                        cursor--;
                        nestedLvl++;
                        stack.push(block.linkedShapes.get(0));
                        nestedBlocks.push("if");
                    }
                    else /*if (block.linkedShapes.size()==2)*/ {
                        String text=block.text;
                        code.add(cursor++,"if ( "+text+" ) { \n");
                        code.add(cursor++,"} \n");
                        cursor--;
                        nestedLvl++;
                        stack.push(block.linkedShapes.get(1));
                        stack.push(block.linkedShapes.get(0));
                        nestedBlocks.push("ifelse");
                    }
                }
                
                
            }
            else if (!block.getClass().equals(OvalEnd.class)){
                for (Shapes neighbor:block.linkedShapes) {
                    stack.push(neighbor);
                }
            }
            
            
        }
        return code;
    }
    
}
