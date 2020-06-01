package Views;

import Entities.User;
import Entities.Video;
import Models.UserModel;
import Views.components.ButtonHelper;
import Views.components.FrameHelper;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class SubscriberProfileView  implements Observer {

    private JFrame frame;
    private JPanel contentPane;
    private JButton backButton;
    private UserModel userModel;
    private JLabel userLabel;
    private JLabel userSubscriberLabel;
    private JLabel userSubscriptionLabel;
    private JPanel panel_1;
    private JPanel panel;

    public SubscriberProfileView(UserModel userModel) {
        this.userModel = userModel;
        userModel.addObserver(this);
        createAndShowGuiForSubscriber(this.userModel.getViewedUser());
    }

    public void createAndShowGuiForSubscriber(User user) {
        frame = FrameHelper.createAFrame(100, 100, 1080, 723);
        contentPane = new JPanel();
        contentPane.setBackground(Color.DARK_GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        frame.setContentPane(contentPane);
        contentPane.setLayout(null);

        panel_1 = new JPanel();
        panel_1.setBackground(new Color(47, 79, 79));
        panel_1.setBounds(0, 0, 1066, 676);
        contentPane.add(panel_1);
        panel_1.setLayout(null);

        panel = new JPanel();
        panel.setBounds(0, 445, 1066, 68);
        panel_1.add(panel);
        panel.setBackground(new Color(0, 128, 128));
        panel.setLayout(null);

        arrangeLabels(panel_1,user);
        backButton = ButtonHelper.createAButton("<< Back",Color.WHITE,16,false,new Color(47, 79, 79),0, 10, 267, 47);
        panel.add(backButton);
    }
    public void setVisible(){
        frame.setVisible(true);
    }
    public void close(){
        frame.setVisible(false);
    }

    private void arrangeLabels(JPanel panel_1,User user){
        userLabel = new JLabel(user.getUserName());
        userLabel.setForeground(Color.WHITE);
        userLabel.setFont(new Font("Arial", Font.BOLD, 20));
        userLabel.setBounds(10, 36, 318, 104);
        panel_1.add(userLabel);

        JLabel lblNewLabel_1 = new JLabel("Number of Subscribers :");
        lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 16));
        lblNewLabel_1.setForeground(new Color(255, 255, 255));
        lblNewLabel_1.setBounds(10, 237, 184, 47);
        panel_1.add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("Number of Subscriptions :");
        lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 16));
        lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
        lblNewLabel_1_1.setBounds(273, 237, 212, 47);
        panel_1.add(lblNewLabel_1_1);

        userSubscriberLabel = new JLabel(""+user.getSubscribers().size());
        userSubscriberLabel.setForeground(Color.WHITE);
        userSubscriberLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        userSubscriberLabel.setBounds(197, 237, 42, 47);
        panel_1.add(userSubscriberLabel);

        userSubscriptionLabel = new JLabel(""+user.getSubscription().size());
        userSubscriptionLabel.setForeground(Color.WHITE);
        userSubscriptionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        userSubscriptionLabel.setBounds(463, 237, 42, 47);
        panel_1.add(userSubscriptionLabel);
    }
    public void addBackButtonListener(ActionListener actionListener){
        backButton.addActionListener(actionListener);

    }

    @Override
    public void update(Observable observable, Object o) {
        //System.out.println(userModel.getViewedUser().getUserName());
        userLabel.setText(userModel.getViewedUser().getUserName());
        userSubscriberLabel.setText("" + userModel.getViewedUser().getSubscribers().size());
        userSubscriptionLabel.setText("" + userModel.getViewedUser().getSubscription().size());

    }
}
