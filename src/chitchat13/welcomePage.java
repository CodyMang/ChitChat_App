/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chitchat13;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/**
 *
 * @author cthom
 */
public class welcomePage extends JFrame implements ActionListener {
    
    JFrame frame = new JFrame();
    JButton exit = new JButton("Exit");
    Label welcome = new Label("Welcome to Chit Chat!!!");
    welcomePage(){
        welcome.setBounds(50, 100, 400, 80);
        welcome.setFont(new Font("Arial",18,36));
        exit.setBounds(420, 420, 60, 25);
        exit.addActionListener(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(600,500);
        frame.add(exit);
        frame.add(welcome);
        frame.setVisible(true);
        
    }
      @Override
      public void actionPerformed(ActionEvent e){
          if(e.getSource() == exit)
          {
              frame.dispose();
          }
      }
      
}
