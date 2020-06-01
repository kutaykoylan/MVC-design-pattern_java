package Views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Entities.User;
import Entities.Video;
import Views.components.ButtonHelper;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Unfollowed  implements Observer {
    private JDialog dialog;
    private final JPanel contentPanel = new JPanel();
    private JButton okButton;
    private JButton cancelButton;
    public void createAndShowGui(User currentUser, User unfollowedUser, List<Video> videos){
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
            JTextPane unfollowedSuccessfully = new JTextPane();
            unfollowedSuccessfully.setBackground(Color.DARK_GRAY);
            unfollowedSuccessfully.setForeground(Color.WHITE);
            unfollowedSuccessfully.setFont(new Font("Arial", Font.BOLD, 15));
            unfollowedSuccessfully.setBounds(93, 65, 198, 64);
            unfollowedSuccessfully.setText(unfollowedUser.getUserName()+" is unfollowed successfully");
            contentPanel.add(unfollowedSuccessfully);
        }
        {
            okButton = ButtonHelper.createAButton("OK",new Color(255, 255, 255),12,false,new Color(0, 128, 128),38, 152, 127, 23);
            okButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        dialog.dispose();
                        //new Subscription(currentUser,videos);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
            contentPanel.add(okButton);
            okButton.setActionCommand("OK");
            dialog.getRootPane().setDefaultButton(okButton);
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setBackground(Color.DARK_GRAY);
            dialog.getContentPane().add(buttonPane, BorderLayout.SOUTH);
            buttonPane.setLayout(new CardLayout(0, 0));
            dialog.setVisible(true);
        }
    }

    @Override
    public void update(Observable observable, Object o) {

    }
}
