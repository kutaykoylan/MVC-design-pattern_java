package Views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Entities.User;
import Entities.Video;
import Views.components.ButtonHelper;
import Views.components.FrameHelper;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Window.Type;
import java.awt.Cursor;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class LoginView implements Observer {

    private JPanel contentPane;
    private JTextField username;
    private JPasswordField password;
    private JLabel passwordText;
    private JLabel usernameText;
    private JFrame frame;
    private JButton loginButton;

    public JFrame getFrame() {
        return frame;
    }

    public void setVisible() {
        frame.setVisible(true);
    }

    public LoginView() {
        frame = FrameHelper.createAFrame(100, 100, 609, 359);
        frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        frame.setForeground(new Color(255, 255, 255));
        frame.setType(Type.POPUP);

        contentPane = new JPanel();
        contentPane.setBackground(Color.DARK_GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        frame.setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.DARK_GRAY);
        panel.setBounds(118, 24, 477, 249);
        contentPane.add(panel);
        panel.setLayout(null);

        panel.add(getUserNameField());
        panel.add(getPasswordField());
        panel.add(getUserNameLabel());
        panel.add(getPasswordLabel());

        loginButton = ButtonHelper.createAButton("SIGN IN", Color.WHITE, 13, false, new Color(47, 79, 79), 120, 201, 120, 21);
        panel.add(loginButton);

    }

    private JTextField getUserNameField() {
        username = new JTextField();
        username.setFont(new Font("Arial", Font.PLAIN, 14));
        username.setBounds(151, 44, 139, 36);
        username.setColumns(10);
        return username;
    }

    private JTextField getPasswordField() {
        password = new JPasswordField();
        password.setFont(new Font("Arial", Font.PLAIN, 14));
        password.setBounds(151, 121, 139, 36);
        return password;
    }

    private JLabel getUserNameLabel() {
        usernameText = new JLabel();
        usernameText.setForeground(Color.WHITE);
        usernameText.setBackground(Color.DARK_GRAY);
        usernameText.setFont(new Font("Arial", Font.PLAIN, 18));
        usernameText.setText("username : ");
        usernameText.setBounds(26, 44, 115, 36);
        return usernameText;
    }

    private JLabel getPasswordLabel() {
        passwordText = new JLabel();
        passwordText.setForeground(Color.WHITE);
        passwordText.setText("password : ");
        passwordText.setFont(new Font("Arial", Font.PLAIN, 18));
        passwordText.setBackground(Color.DARK_GRAY);
        passwordText.setBounds(26, 121, 115, 36);
        return passwordText;
    }

    @Override
    public void update(Observable observable, Object o) {

    }

    /**
     * Close this frame
     */
    public void close() {
        frame.dispose();
    }

    public void addLoginButtonListener(ActionListener actionListener) {

        loginButton.addActionListener(actionListener);
    }


    public JTextField getUsername() {
        return username;
    }

    public JPasswordField getPassword() {
        return password;
    }
}
