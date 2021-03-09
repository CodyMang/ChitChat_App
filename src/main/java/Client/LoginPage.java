/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

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

import Server.Database;

/**
 *
 * @author cthom
 */
public class LoginPage extends JFrame implements ActionListener {

    boolean resetPageOpen;

    JButton signIn = new JButton("Sign In");
    JButton signUP = new JButton("Sign Up");
    JTextField userInfo = new JTextField();
    JPasswordField passInfo = new JPasswordField();
    JLabel userName = new JLabel("Username: "); 
    JLabel passWord = new JLabel("Password: ");
    Label appName = new Label("Welcome to Chit Chat");
    Label message = new Label("Username and/or password is incorrect");
    JButton resetPasswordButton = new JButton("Reset Password");
    public LoginPage(){
        
        signIn.setBounds(180,300,100,35);
        signIn.addActionListener(this);
        
        signUP.setBounds(320,300,100,35);
        signUP.addActionListener(this);

        resetPasswordButton.setBounds(230,355,140,25);
        resetPasswordButton.addActionListener(this);

        userInfo.setBounds(150,150,300,30);
        passInfo.setBounds(150, 200, 300, 30);
        userName.setBounds(80,150,90,30);
        passWord.setBounds(80,200,80,30);
        appName.setFont(new Font("Arial Italic" ,18,36));

        appName.setBounds(115,30,400,80);
        message.setBounds(150,130,300,25);
        message.setForeground(Color.red);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600,500);
        this.setLayout(null);

        this.add(resetPasswordButton);
        this.add(signIn);
        this.add(signUP);
        this.add(userInfo);
        this.add(passInfo);
        this.add(userName);
        this.add(passWord);
        this.add(appName);
        this.setVisible(true);
        
    }
    
    public static CurrentUser userFromLoginInfo(String userName, String pass)
    {
        if(!userName.equals("") && !pass.equals(""))
        {
            if(Database.userExists(userName,pass))
            {
                return Database.getUserInformation(userName);
            }
        }
       return null;
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==signIn){

            if(userFromLoginInfo(userInfo.getText(), passInfo.getText())!= null){
                new MainChatPage();
                this.dispose();
            }
            else {
                this.add(message);
            }
        
        }
        if(e.getSource()==signUP)
        {
            signUp sign = new signUp();
            this.dispose();
        }

        if(e.getSource()==resetPasswordButton)
        {
            if(!resetPageOpen)
            {
                resetPageOpen = true;
                new ResetPasswordPage();
            }
        }
    }
   
    
}
