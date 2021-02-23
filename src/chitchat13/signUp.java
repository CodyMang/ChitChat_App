/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chitchat13;
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
public class signUp implements ActionListener{
    HashMap<String,String> userInfo =new HashMap<String,String>();
    String [] info = new String[100];
    
    JButton submit = new JButton("Submit");
    JButton cancel = new JButton("Cancel");
    JButton reset = new JButton("Reset");
    JFrame frame = new JFrame();
    JTextField firstName = new JTextField();
    JTextField lastName = new JTextField();
    JTextField email = new JTextField();
    JTextField userNameField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    Label personal = new Label("Personal Information");
    JLabel first = new JLabel("First:");
    JLabel last = new JLabel("Last:");
    JLabel url = new JLabel("Email:");
    JLabel userID = new JLabel("User ID:");
    JLabel passW = new JLabel("Password:");
   // Label welcome = new Label("Welcome to Chit Chat");
   
    
    signUp(){
       
        submit.setBounds(230,420,80,30);
        submit.addActionListener(this);
        reset.setBounds(310,420,80,30);
        reset.addActionListener(this);
        cancel.setBounds(390,420,80,30);
        cancel.addActionListener(this);
        personal.setBounds(10,15,205,25);
        personal.setFont(new Font("Arial",18,22));
        firstName.setBounds(80,65,300,25);
        lastName.setBounds(80,105,300, 25);
        email.setBounds(80,145,300, 25);
        userNameField.setBounds(80,185,300,25);
        passwordField.setBounds(80,225,300,25);
        first.setBounds(10,65,60,25);
        last.setBounds(10,105,60,25);
        url.setBounds(10,145,60 , 25);
        userID.setBounds(10, 185, 300, 25);
        passW.setBounds(10, 225, 300, 25);
       // welcome.setBounds(0, 0, 0, 0);
        //welcome.setFont(new Font("Arial",));
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.add(submit);
        frame.add(reset);
        frame.add(cancel);
        frame.add(firstName);
        frame.add(first);
        frame.add(lastName);
        frame.add(last);
        frame.add(personal);
        frame.add(url);
        frame.add(email);
        frame.add(userID);
        frame.add(userNameField);
        frame.add(passW);
        frame.add(passwordField);
        frame.setLayout(null);
        frame.setVisible(true);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent evt){
        if(evt.getSource()==submit){
            String ID = userNameField.getText();
            String pw = String.valueOf(passwordField.getPassword());
            userInfo.put(ID, pw);
            store save = new store(userInfo);
            new loginPage();
            frame.dispose();
        }
        else if(evt.getSource()==reset){
            firstName.setText("");
            lastName.setText("");
            email.setText("");
            userNameField.setText("");
            passwordField.setText("");
        }
        else{
            new loginPage();
            frame.dispose();
        }                
            
        
        
    }
}
