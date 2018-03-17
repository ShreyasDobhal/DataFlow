/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataflow;

import Beans.NewTab;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStream;
import javax.swing.JOptionPane;

/**
 *
 * @author Shreyas
 */
public class CodeCompiler {
    
    public final String javaDir="C:\\Program Files\\Java\\jdk1.8.0_111\\bin";
    public final String gccDir="E:\\Dev C++\\Dev-Cpp\\MinGW64\\bin";
    private MainWindow parent=null;
    private boolean isCompiling=false;
    private boolean hasParent=false;
    private boolean isRunning=false;
    public boolean terminateProcess=false;
    
    
    public CodeCompiler() {
        
    }
    
    public CodeCompiler(MainWindow parent) {
        this.parent=parent;
        hasParent=true;
    }
    
    private void startCompiling() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    double complete=0.0;
                    while (isCompiling) {
                        complete+=0.1;
                        parent.progressBar.setValue((int)complete);
                        Thread.sleep(10);
                    }
                    parent.progressBar.setValue(100);
                    Thread.sleep(500);
                    parent.progressBar.setValue(0);
                }
                catch (Exception e) {
                    
                }
            }
        }).start();
    }
    
    public void CompileJava(String dir,String file) {
        Thread compiler = new Thread(new Runnable() {
            @Override
            public void run() {
                String inFile=file+".java";
                String filePath=dir+"\\"+inFile;
                System.out.println("Started Compiling");
                isCompiling=true;
                startCompiling();
                NewTab curTab=null;
                if (hasParent) {
                    curTab = (NewTab)parent.fileTabPane.getSelectedComponent();
                    curTab.txtConsole.setText("");
                    curTab.txtConsole.append("Compiling . . . \n");
                }
                try {
                    ProcessBuilder pb;
                    pb = new ProcessBuilder("cmd","/C","javac "+filePath);
                    Process p = pb.start();
                    p.waitFor();
                    int x = p.exitValue();
                    if (x == 0) {
                        System.out.println("Done Compiling");
                        if (hasParent) {
                           curTab.txtConsole.append("Done compiling");
                       }
                    }
                    else
                    {
                        BufferedReader r = new BufferedReader(new InputStreamReader(p.getErrorStream()));
                        String out;
                        while ((out = r.readLine()) != null)
                        {
                            String msg=out + System.getProperty("line.separator");
                            System.out.println(msg);
                            System.out.println("Compiler : "+out);
                            if (hasParent) {
                                curTab.txtConsole.append(msg);
                            }
                        }
                        long t2=System.currentTimeMillis();
                        System.out.println("Done Compiling");
                        if (hasParent) {
                            curTab.txtConsole.append("Done compiling");
                        }
                    }
                }
                catch (Exception e) {
                    System.out.println("Error ");
                    e.printStackTrace();
                    if (hasParent) {
                        curTab.txtConsole.append(e.toString());
                    }
                }
                isCompiling=false;
            }
        });
        compiler.start();
    }
    
    public void CompileC(String dir,String file) {
        Thread compiler = new Thread(new Runnable() {
            @Override
            public void run() {
                String inFile=file+".cpp";
                String filePath=dir+"\\"+inFile;
                System.out.println("Started Compiling");
                isCompiling=true;
                startCompiling();
                NewTab curTab=null;
                if (hasParent) {
                    curTab = (NewTab)parent.fileTabPane.getSelectedComponent();
                    curTab.txtConsole.setText("");
                    curTab.txtConsole.append("Compiling . . . \n");
                }
                try {
                    ProcessBuilder pb;
                    pb = new ProcessBuilder("cmd", "/C", "gcc " + "\"" + dir + "\\" +inFile+ "\"" + " -o \"" + dir + "\\" + file+"\"");
                    pb.directory(new File(gccDir));
                    Process p = pb.start();
                    p.waitFor();
                    int x = p.exitValue();
                    if (x == 0) {
                        System.out.println("Done Compiling");
                        if (hasParent) {
                           curTab.txtConsole.append("Done compiling");
                       }
                    }
                    else
                    {
                        BufferedReader r = new BufferedReader(new InputStreamReader(p.getErrorStream()));
                        String out;
                        while ((out = r.readLine()) != null)
                        {
                            String msg=out + System.getProperty("line.separator");
                            System.out.println(msg);
                            System.out.println("Compiler : "+out);
                            if (hasParent) {
                                curTab.txtConsole.append(msg);
                            }
                        }
                        long t2=System.currentTimeMillis();
                        System.out.println("Done Compiling");
                        if (hasParent) {
                            curTab.txtConsole.append("Done compiling");
                        }
                    }
                }
                catch (Exception e) {
                    System.out.println("Error ");
                    e.printStackTrace();
                    if (hasParent) {
                        curTab.txtConsole.append(e.toString());
                    }
                }
                isCompiling=false;
            }
        });
        compiler.start();
    }
    
    
    // true for C false for python
    public void runProg(String dir,String file,boolean cpp) {
        Thread compileRun = new Thread(new Runnable(){
            @Override
            public void run() {
                
                String inFile=file+".java";
                String filePath=dir+"\\"+inFile;
                String outFile=file;
                String fileDir=dir;
                System.out.println("Started Running");
                isCompiling=true;
                startCompiling();
                NewTab curTab=null;
                if (hasParent) {
                    curTab = (NewTab)parent.fileTabPane.getSelectedComponent();
                    curTab.txtConsole.setText("");
                }
                
                try {
                    ProcessBuilder pb;
                    if (cpp) {
                        pb = new ProcessBuilder("cmd","/c",outFile);
                        pb.directory(new File(fileDir));
                    }
                    else {
                        pb = new ProcessBuilder("cmd","/c","java "+outFile);
                        pb.directory(new File(fileDir));
                    }
                    Process p = pb.start();
                    BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
                    String inp="";//txtInput.getText()+"\n";
                    if (hasParent) {
                        inp=curTab.txtInput.getText()+"\n";
                    }
                    else {
                        inp="5";
                    }
                    
                    
                    byte buffer[] = inp.getBytes();
                    OutputStream os =(p.getOutputStream());
                    os.write(buffer,0,buffer.length);
                    os.flush();
                    String str;
                    while ((str=br.readLine())!=null) {
                        System.out.println(str);
                        if (hasParent) {
                            curTab.txtConsole.append(str+"\n");
                        }
                        if (terminateProcess) {
                            break;
                        }
                        try {
                            ;//Thread.sleep(10);
                        } catch (Exception e) {
                            ;
                        }
                    }
                    terminateProcess=false;
                    if (hasParent) {
                        curTab.txtConsole.append("\nRun complete\n");
                    }
                    p.waitFor();
                    int x = p.exitValue();
                    if (x == 0) {
                        ;//JOptionPane.showMessageDialog(null,"Run Completed !");
                    }
                    else
                    {
                        System.out.println("Displaying something");
                        BufferedReader r = new BufferedReader(new InputStreamReader(p.getErrorStream()));
                        String out;
                        while ((out = r.readLine()) != null)
                        {
                            String msg=out + System.getProperty("line.separator");
                            System.out.println(msg);
                            System.out.println("Compiler : "+out);
                            if (hasParent) {
                                curTab.txtConsole.append(out+"\n");
                            }
                        }
                    }
                }
                catch (Exception e) {
                    System.out.println("Error ");
                }
                isCompiling=false;
            }
        });
        compileRun.start();
    }
    
    public static void main(String args[]) {
        CodeCompiler code=new CodeCompiler();
        code.CompileJava("C:\\Users\\Shreyas\\Desktop","Hello");
    }
}
