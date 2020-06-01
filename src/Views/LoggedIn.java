package Views;

import Entities.User;
import Entities.Video;
import Views.components.ButtonHelper;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class LoggedIn implements Observer {

    private final JPanel contentPanel = new JPanel();
    private JButton okButton;
    private JButton cancelButton;
    private JDialog dialog;



    public void setVisible() {
        dialog.setVisible(true);
    }

    public LoggedIn() {
        dialog = new JDialog();
        dialog.setTitle("IztechTube");
        dialog.setIconImage(Toolkit.getDefaultToolkit().getImage("icon.png"));
        dialog.getContentPane().setBackground(Color.DARK_GRAY);
        dialog.setBounds(100, 100, 374, 245);
        dialog.getContentPane().setLayout(new BorderLayout());
        contentPanel.setBackground(Color.DARK_GRAY);
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        dialog.getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        {
            JLabel txtpnLoggedInSuccesfully = new JLabel();
            txtpnLoggedInSuccesfully.setBackground(Color.DARK_GRAY);
            txtpnLoggedInSuccesfully.setForeground(Color.WHITE);
            txtpnLoggedInSuccesfully.setFont(new Font("Arial", Font.BOLD, 15));
            txtpnLoggedInSuccesfully.setBounds(93, 65, 198, 64);
            txtpnLoggedInSuccesfully.setText("Logged in succesfully!");
            contentPanel.add(txtpnLoggedInSuccesfully);
        }
        {
            okButton = ButtonHelper.createAButton("OK", new Color(255, 255, 255), 12, false, new Color(0, 128, 128), 38, 152, 127, 23);

            new JButton("OK");

            contentPanel.add(okButton);
            okButton.setActionCommand("OK");
            dialog.getRootPane().setDefaultButton(okButton);
        }
        {
            cancelButton = ButtonHelper.createAButton("CANCEL", Color.WHITE, 12, false, new Color(0, 128, 128), 198, 152, 127, 23);
            contentPanel.add(cancelButton);

            cancelButton.setActionCommand("Cancel");
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setBackground(Color.DARK_GRAY);
            dialog.getContentPane().add(buttonPane, BorderLayout.SOUTH);
            buttonPane.setLayout(new CardLayout(0, 0));
        }
    }

    /**
     * Add the action listener to ok button
     * @param actionListener is an action listener from controller
     */
    public void addOkButtonListener(ActionListener actionListener){
        okButton.addActionListener(actionListener);
    }

    /**
     * Add action listener to cancel button
     * @param actionListener is an action listener from controller
     */
    public void addCancelButtonListener(ActionListener actionListener){
        this.cancelButton.addActionListener(actionListener);
    }

    /**
     * Close the view
     */
    public void close(){
        try {
            dialog.dispose();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Observable observable, Object o) {

    }
}
