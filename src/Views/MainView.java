package Views;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Entities.User;
import Entities.Video;
import Models.UserModel;
import Views.components.ButtonHelper;
import Views.components.FrameHelper;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class MainView implements Observer {

    private JFrame frame;
    private JPanel contentPane;
    private JButton likedVideosButton;
    private JButton profileButton;
    private JButton[] buttons;
    private List<Video> videos;
    private UserModel userModel;
    private JTextPane useInformations;

    public MainView(UserModel userModel, List<Video> videos) {
        this.videos = videos;
        this.userModel = userModel;
        userModel.addObserver(this);

        frame = FrameHelper.createAFrame(100, 100, 1066, 686);
        contentPane = new JPanel();
        contentPane.setBackground(Color.DARK_GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        frame.setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(47, 79, 79));
        panel.setBounds(0, 0, 1052, 63);
        contentPane.add(panel);
        panel.setLayout(null);
/**
 * TODO: liked videoları bulan methoda göre değiştirilcek
 */

        likedVideosButton = ButtonHelper.createAButton("Liked Videos",new Color(255, 255, 255),16,false,new Color(0, 128, 128),664, 10, 184, 43);

        panel.add(likedVideosButton);

        /*watchlistButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    frame.dispose();
                    new WatchlistsView().createAndShowGui(userModel.getLoggedInUser(),videos);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });*/
        profileButton = ButtonHelper.createAButton("Profile",Color.WHITE,16,false,new Color(0, 128, 128),858, 10, 184, 43);
        /*profileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    frame.dispose();
                    new ProfileView().createAndShowGuiForItself(userModel.getLoggedInUser(),videos);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });*/
        panel.add(profileButton);

        useInformations = new JTextPane();
        useInformations.setBackground(new Color(47, 79, 79));
        useInformations.setForeground(new Color(255, 255, 255));
        useInformations.setText(userModel.getLoggedInUser().getUserName());
        useInformations.setFont(new Font("Arial", Font.BOLD, 16));
        useInformations.setBounds(77, 10, 225, 50);
        panel.add(useInformations);
        JButton profile = new JButton("");
        profile.setBackground(new Color(47, 79, 79));
        profile.setBorderPainted(false);
        profile.setIcon(new ImageIcon("profile.png"));
        profile.setBounds(0, 10, 71, 36);
        panel.add(profile);

        JPanel videoPanel = new JPanel();
        videoPanel.setBackground(Color.DARK_GRAY);
        videoPanel.setBounds(30, 68, 994, 581);
        contentPane.add(videoPanel);

        JButton btnNewButton_4 = new JButton("Video");

        btnNewButton_4.setFont(new Font("Arial", Font.PLAIN, 18));
        buttons = new JButton[videos.size()];
        for(int i=0 ; i<videos.size() ; i++){
            buttons[i] = ButtonHelper.createAButton("Video : "+videos.get(i).getTitle(),SystemColor.window,20,false,new Color(47, 79, 79),0,0,0,0);

            buttons[i].setPreferredSize(new Dimension(40, 40));
            videoPanel.add(buttons[i]);


        }
        videoPanel.setLayout(new GridLayout(4,4,10,10));
            //setting grid layout of 3 rows and 3 columns

        videoPanel.setVisible(true);

    }

    public void close(){
        this.frame.setVisible(false);
    }

    public void setVisible(){
        this.frame.setVisible(true);
    }

    private Video findVideo(List<Video> videos,String videoTitle){
        for(Video video: videos){
            String title = "Video : "+video.getTitle();
            if (title.equals(videoTitle))
                return video;
        }
        return null;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }


    public void addVideoButtonListener(ActionListener actionListener){
        for(int i = 0; i < videos.size(); i++){
            buttons[i].addActionListener(actionListener);
        }
    }

    public void addLikedVideosListener(ActionListener actionListener){
        likedVideosButton.addActionListener(actionListener);
    }

    public void addProfileButtonListener(ActionListener actionListener){
        profileButton.addActionListener(actionListener);
    }

    @Override
    public void update(Observable observable, Object o) {
        useInformations.setText(this.userModel.getLoggedInUser().getUserName());
    }
}
