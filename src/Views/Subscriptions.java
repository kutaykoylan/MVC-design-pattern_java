package Views;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import Entities.User;
import Views.components.ButtonHelper;
import Views.components.FrameHelper;

import javax.swing.JTextPane;
import javax.swing.ImageIcon;
import javax.swing.JList;

public class Subscriptions implements Observer {

    private JPanel contentPane;
    private User userModel;
    private JFrame frame;
    private JPanel panel_1;
    private JTextPane userInformations;
    private JButton profile;
    private JPanel panel;
    private JButton videosButton;
    private JButton playlistsButton;
    private JButton subscriptionsButton;
    private JButton subscribersButton;
    private User[] subscriptions;
    private JList list;

    public Subscriptions(User userModel) {
        this.userModel=userModel;
        frame = FrameHelper.createAFrame(100, 100, 1080, 723);
        contentPane = new JPanel();
        contentPane.setBackground(Color.DARK_GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        frame.setContentPane(contentPane);
        contentPane.setLayout(null);

        panel_1 = new JPanel();
        panel_1.setBackground(new Color(47, 79, 79));
        panel_1.setBounds(0, 0, 287, 697);
        contentPane.add(panel_1);
        panel_1.setLayout(null);

        userInformations = new JTextPane();
        userInformations.setBackground(new Color(47, 79, 79));
        userInformations.setForeground(new Color(255, 255, 255));
        userInformations.setText(userModel.getUserName());
        userInformations.setFont(new Font("Arial", Font.BOLD, 16));
        userInformations.setBounds(77, 10, 225, 50);
        panel_1.add(userInformations);

        profile = new JButton("");
        profile.setBackground(new Color(47, 79, 79));
        profile.setBorderPainted(false);
        profile.setIcon(new ImageIcon("profile.png"));
        profile.setBounds(0, 10, 71, 36);
        panel_1.add(profile);

        panel = new JPanel();
        panel.setBounds(0, 445, 287, 252);
        panel_1.add(panel);
        panel.setBackground(new Color(0, 128, 128));
        panel.setLayout(null);

        videosButton = ButtonHelper.createAButton("Videos", Color.WHITE, 16, false, new Color(47, 79, 79), 10, 10, 267, 47);
       // videosButton.addActionListener(...);TODO:
        panel.add(videosButton);

       playlistsButton = ButtonHelper.createAButton("Playlists", Color.WHITE, 16, false, new Color(47, 79, 79), 10, 67, 267, 47);
      // playlistsButton.addActionListener(...);TODO:
        panel.add(playlistsButton);

        subscriptionsButton = ButtonHelper.createAButton("Subscriptions", Color.WHITE, 16, false, new Color(47, 79, 79), 10, 124, 267, 47);
     // subscriptionsButton.addActionListener(...); TODO:
        panel.add(subscriptionsButton);

        subscribersButton = ButtonHelper.createAButton("Subscribers", Color.WHITE, 16, false, new Color(47, 79, 79), 10, 181, 267, 47);
      //  subscribersButton.addActionListener(...);TODO:
        panel.add(subscribersButton);
        subscriptions = userModel.getSubscription().toArray(new User[0]);
        list = new JList(subscriptions);
        list.setBounds(297, 10, 759, 643);
        contentPane.add(list);

        frame.setVisible(true);
    }

    /**
     * TODO: Bu buttonlar ayarlancak hazır değildi önceki halinde de
     * @param actionListener
     */
    public void addVideosButtonListener(ActionListener actionListener){
        videosButton.addActionListener(actionListener);
    }
    public void addPlaylistsButtonListener(ActionListener actionListener){
        playlistsButton.addActionListener(actionListener);
    }
    public void addSubscriptionsButtonListener(ActionListener actionListener){
        subscriptionsButton.addActionListener(actionListener);
    }
    public void addSubscribersButtonListener(ActionListener actionListener){
        subscribersButton.addActionListener(actionListener);
    }


    @Override
    public void update(Observable observable, Object o) {

    }
}
