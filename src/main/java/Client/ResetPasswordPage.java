package Client;
import javax.swing.*;
import Server.Database;

import java.awt.*;

public class ResetPasswordPage extends JFrame
{
    JLabel titleLabel;
    JLabel userNameLabel;
    JLabel newPassLabel;
    JLabel emailLabel;

    JTextField userTextField;
    JTextField emailTextField;
    JTextField newPassTextField;

    JButton cancelButton;
    JButton resetButton;

    public ResetPasswordPage()
    {
        setLayout(null);
        userNameLabel = new JLabel("Username :",JLabel.TRAILING);

        userTextField = new JTextField(10);
        userNameLabel.setLabelFor(userTextField);

        titleLabel = new JLabel("Reset Password", JLabel.CENTER);
        emailLabel = new JLabel("Email :",JLabel.TRAILING);
        emailTextField = new JTextField(10);
        emailLabel.setLabelFor(emailTextField);


        newPassLabel = new JLabel("New Password :",JLabel.TRAILING);
        newPassTextField = new JTextField(10);
        newPassLabel.setLabelFor(newPassTextField);

        JButton cancelButton = new JButton("Cancel");
        JButton resetButton = new JButton("Reset");

        titleLabel.setBounds(0,30,400,80);
        userNameLabel.setBounds(55,120,100,30);
        emailLabel.setBounds(55,160,100,30);
        newPassLabel.setBounds(55,200,100,30);

        titleLabel.setFont(new Font("Arial Italic",18,36));
        userTextField.setBounds(160,120,150,30);
        emailTextField.setBounds(160,160,150,30);
        newPassTextField.setBounds(160,200,150,30);

        cancelButton.setBounds(80,270,100,30);
        resetButton.setBounds(210,270,100,30);
        add(titleLabel);
        add(userNameLabel);
        add(emailLabel);
        add(newPassLabel);
        add(emailTextField);
        add(userTextField);
        add(newPassTextField);
        add(resetButton);
        add(cancelButton);
        setResizable(false);
        cancelButton.addActionListener(e -> dispose());
        resetButton.addActionListener(e ->
            {
                if(!emailTextField.getText().equals("")
                        && !userTextField.getText().equals("")
                        && !newPassTextField.getText().equals(""))
                {
                    if(Database.userEmailExist(userTextField.getText(),emailTextField.getText()))
                    {
                        Database.insertNewPassword(userTextField.getText(),newPassTextField.getText());
                        JOptionPane.showMessageDialog(null, "Password Updated");
                        dispose();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Username and/or email does not exist");
                    }
                }

            });

        setSize(400,400);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

}
