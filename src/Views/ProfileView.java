package Views;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import Entities.User;
import Entities.Video;
import Models.UserModel;
import Models.VideoModel;
import Views.components.ButtonHelper;
import Views.components.FrameHelper;

public class ProfileView implements Observer {
    private JFrame frame;
    private JPanel contentPane;
    private UserModel userModel;
    private VideoModel videoModel;
    private JButton backButton;
    private JButton subscriptionsButton;
    private JButton btnSubscribers;
    private JTextPane useInformations;
    private JButton playlistsButton;

    public ProfileView(UserModel userModel, VideoModel videoModel){
        this.videoModel = videoModel;
        this.userModel = userModel;
        this.userModel.addObserver(this);
        createAndShowGuiForItself();

    }

    public void createAndShowGuiForItself() {


        frame = FrameHelper.createAFrame(100, 100, 1080, 723);
        contentPane = new JPanel();
        contentPane.setBackground(Color.DARK_GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        frame.setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(47, 79, 79));
        panel_1.setBounds(0, 0, 287, 697);
        contentPane.add(panel_1);
        panel_1.setLayout(null);

        useInformations = new JTextPane();
        useInformations.setBackground(new Color(47, 79, 79));
        useInformations.setForeground(new Color(255, 255, 255));
        useInformations.setText(userModel.getLoggedInUser().getUserName());
        useInformations.setFont(new Font("Arial", Font.BOLD, 16));
        useInformations.setBounds(77, 10, 225, 50);
        panel_1.add(useInformations);

        JButton profile = ButtonHelper.createAButton("",null,0,false,new Color(47, 79, 79),0, 10, 71, 36,new ImageIcon("profile.png"));
        panel_1.add(profile);

        JPanel panel = new JPanel();
        panel.setBounds(0, 379, 287, 318);
        panel_1.add(panel);
        panel.setBackground(new Color(0, 128, 128));
        panel.setLayout(null);


        playlistsButton = ButtonHelper.createAButton("Watchlists",Color.WHITE,16,false,new Color(47, 79, 79),10, 47, 267, 47);
        /*playlistsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    frame.dispose();
                    new WatchlistsView().createAndShowGui(userModel.getLoggedInUser(),videoModel.getVideos());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });*/
        panel.add(playlistsButton);


        subscriptionsButton = ButtonHelper.createAButton("Subscriptions",Color.WHITE,16,false,new Color(47, 79, 79),10, 104, 267, 47);
        panel.add(subscriptionsButton);

        btnSubscribers = ButtonHelper.createAButton("Subscribers",Color.WHITE,16,false,new Color(47, 79, 79),10, 161, 267, 47);
        /*btnSubscribers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    frame.dispose();
                    new Subscriber(userModel.getLoggedInUser());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });*/
        panel.add(btnSubscribers);

        backButton = ButtonHelper.createAButton("<< Back",Color.WHITE,16,false,new Color(47, 79, 79),10, 218, 267, 47);
        /*backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    frame.dispose();
                    TODO
                    //new MainView().createAndShowGui(user,videos);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });*/
        panel.add(backButton);


    }


    public void setVisible(){
        frame.setVisible(true);
    }

    public void close(){
        frame.setVisible(false);
    }

    public void addBackButton1Listener(ActionListener actionListener){
        this.backButton.addActionListener(actionListener);
    }

    public void addSubscriptionButtonListener(ActionListener actionListener) {
        this.subscriptionsButton.addActionListener(actionListener);
    }

    public void addbtnSubscribersListener(ActionListener actionListener){
        this.btnSubscribers.addActionListener(actionListener);
    }

    public void addWatchlistsButtonListener(ActionListener actionListener){
        this.playlistsButton.addActionListener(actionListener);
    }



    @Override
    public void update(Observable observable, Object o) {
        useInformations.setText(userModel.getLoggedInUser().getUserName());
    }
}
