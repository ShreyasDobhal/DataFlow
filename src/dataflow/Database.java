/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataflow;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Set;
import javax.swing.JOptionPane;

/**
 *
 * @author Shreyas
 */
public class Database {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    boolean remember=false;
    String pass="";
    boolean autoFill=false;
    
    public Database() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:users.db");
            System.out.println("Connection Established");
            //displayTable();
        }
        catch (Exception e) {
        }
    }
    
    public void displayTable() {
        try {
            String sql="select * from emp;";
            ps=conn.prepareStatement(sql);
            ps.clearParameters();
            rs=ps.executeQuery();
            while (rs.next()) {
                int id=rs.getInt(1);
                String name=rs.getString("name");
                String username=rs.getString("username");
                String password=rs.getString("password");
                System.out.println(id+"\t\t"+name+"\t\t"+username+"\t\t"+password);
            }
        }
        catch (Exception e) {
        }
        finally {
            try {
                rs.close();
                ps.close();
            }
            catch (Exception e) {
            }
        }
    }
    
    
    public boolean loginRequest(String name,String pass) { 
        String sql="select * from emp where username=? and password=?";
        boolean status=false;
        try {
            ps=conn.prepareStatement(sql);
            ps.setString(1,name);
            ps.setString(2,pass);
            rs=ps.executeQuery();
            if (rs.next()) {
                int tmp=rs.getInt("id");
                rs.close();
                ps.close();
                System.out.println("Login Successful");
                status= true;
            }
            else {
                System.out.println("Login Failed");
                status= false;
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            status= false;
        }
        finally {
            try {
                rs.close();
                ps.close();
            }
            catch (Exception e) {
                
            }
        }
        return status;
    } 
    public static void main(String args[]) {
        Database db=new Database();
        db.loginRequest("shreyasdobhal@gmail.com", "5077");
    }
}