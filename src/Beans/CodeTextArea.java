/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;


import javax.swing.*;
import org.fife.ui.rtextarea.*;
import org.fife.ui.rsyntaxtextarea.*;


public class CodeTextArea extends javax.swing.JPanel {

    /**
     * Creates new form NewJPanel
     */
    public CodeTextArea() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        //jScrollPane1 = new RTextScrollPane();
        textArea = new RSyntaxTextArea();
        textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        textArea.setCodeFoldingEnabled(true);
        jScrollPane1 = new RTextScrollPane(textArea);
        //textArea.setColumns(20);
        //textArea.setRows(5);
        
        //jScrollPane1.setViewportView(textArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>                        

    public void setText(String str) {
        textArea.setText(str);
    }
    
    public String getText() {
        return textArea.getText().toString();
    }
    
    public void setFont(int size) {
        textArea.setFont(new java.awt.Font("Segoe UI Semilight", 0, size));
    }
    
    public static void main(String args[]) {
        
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CodeTextArea().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify                     
    private RTextScrollPane jScrollPane1;
    private RSyntaxTextArea textArea;
    // End of variables declaration                   
}