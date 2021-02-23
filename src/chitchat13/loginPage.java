/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chitchat13;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Label;
import java.util.HashMap;

/**
 *
 * @author cthom
 */
public class loginPage implements ActionListener {
    HashMap<String,String> user = new HashMap<String,String>();
   
    
    JFrame frame = new JFrame();
    JButton signIn = new JButton("Sign In");
    JButton signUP = new JButton("Sign Up");
    JTextField userInfo = new JTextField();
    JPasswordField passInfo = new JPasswordField();
    JLabel userName = new JLabel("Username: "); 
    JLabel passWord = new JLabel("Password: ");
    Label appName = new Label("Welcome to Chit Chat");
    Label message = new Label("Username and/or password is incorrect");
    public loginPage(){
        
        signIn.setBounds(200,300,80,35);
        signIn.addActionListener(this);
        
        signUP.setBounds(300,300,80,35);
        signUP.addActionListener(this);
        
        userInfo.setBounds(150,150,300,30);
        passInfo.setBounds(150, 200, 300, 30);
        userName.setBounds(80,150,90,30);
        passWord.setBounds(80,200,80,30);
        appName.setFont(new Font("Arial Italic" ,18,36));
        appName.setBounds(115,30,400,80);
        message.setBounds(150,130,300,25);
        message.setForeground(Color.red);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,500);
        frame.setLayout(null);
       
        frame.add(signIn);
        frame.add(signUP);
        frame.add(userInfo);
        frame.add(passInfo);
        frame.add(userName);
        frame.add(passWord);
        frame.add(appName);
        frame.setVisible(true);
        
    }
    
      
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==signIn){
            String userID = userInfo.getText();
            String pw = String.valueOf(passInfo.getPassword());
           
            
            store stored = new store();
            if(stored.getstoredInfo().containsKey(userID)){
                if(stored.getstoredInfo().get(userID).equals(pw)){
                   new welcomePage();
                    frame.dispose();
                }
                
            }
          
                
            else{
                frame.add(message);
            }
        
        }
        if(e.getSource()==signUP){
            signUp sign = new signUp();
            frame.dispose();
           
        }
        else{
         
        }
    }
   
    
}
