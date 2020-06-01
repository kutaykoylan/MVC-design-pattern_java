package Views;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Entities.Video;
import Models.UserModel;
import Models.VideoModel;
import Views.components.ButtonHelper;
import Views.components.FrameHelper;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class MainLikedVideos implements Observer {

    private UserModel userModel;
    private List<Video> likedVideosModels;
    private JFrame frame;
    private JPanel contentPane;
    private JPanel panel;
    private JButton likedlikedVideosButton;
    private JButton backButton;
    private JTextPane useInformations;
    private JPanel videoPanel;
    private JButton video;
    private VideoModel videoModel;

    /**
     *  ana sayfadaki videolar method olan haline veriliyordu kaldırıldı controllerda ekleniyor olarak planlandi
     */
    public MainLikedVideos(UserModel userModel, VideoModel videoModel) {
        this.userModel=userModel;
        this.videoModel = videoModel;

        this.userModel.addObserver(this);
        videoModel.addObserver(this);
        frame = FrameHelper.createAFrame(100, 100, 1066, 686);
        contentPane = new JPanel();
        contentPane.setBackground(Color.DARK_GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        frame.setContentPane(contentPane);
        contentPane.setLayout(null);

        panel = new JPanel();
        panel.setBackground(new Color(47, 79, 79));
        panel.setBounds(0, 0, 1052, 63);
        contentPane.add(panel);
        panel.setLayout(null);

        likedlikedVideosButton = ButtonHelper.createAButton("Liked Videos", new Color(255, 255, 255), 16, false, new Color(47, 79, 79), 470, 10, 184, 43);
        contentPane.add(likedlikedVideosButton);

        backButton = ButtonHelper.createAButton("<< Back", Color.WHITE, 16, false, new Color(0, 128, 128), 10, 10, 267, 47);
        panel.add(backButton);

        useInformations = new JTextPane();
        useInformations.setBackground(new Color(47, 79, 79));
        useInformations.setForeground(new Color(255, 255, 255));
        useInformations.setText(userModel.getLoggedInUser().getUserName());
        useInformations.setFont(new Font("Arial", Font.BOLD, 16));
        useInformations.setBounds(900, 10, 400, 50);
        panel.add(useInformations);

        renderList();


    }

    private void renderList(){
        this.likedVideosModels = videoModel.findLikedVideosByUser(userModel.getLoggedInUser());
        if(videoPanel != null) videoPanel.setVisible(false); // Unvisible before video panel
        videoPanel = new JPanel();
        videoPanel.setBackground(Color.DARK_GRAY);
        videoPanel.setBounds(30, 68, 994, 581);
        contentPane.add(videoPanel);

        JButton btnNewButton_4 = new JButton("Video");

        btnNewButton_4.setFont(new Font("Arial", Font.PLAIN, 18));
        for (int i = 0; i < likedVideosModels.size(); i++) {
            video = ButtonHelper.createAButton("Video : " + likedVideosModels.get(i).getTitle(), SystemColor.window, 20, false, new Color(47, 79, 79), 0, 0, 0, 0);
            video.setPreferredSize(new Dimension(40, 40));
            videoPanel.add(video);

        }
        videoPanel.setLayout(new GridLayout(4, 4, 10, 10));
        //setting grid layout of 3 rows and 3 columns

        videoPanel.setVisible(true);
    }

    public void setVisible(){
        frame.setVisible(true);
    }

    public void close(){
        frame.setVisible(false);
        ButtonHelper.clearActionListenerAButton(backButton); // Clear back button listeners
    }

    /**
     * Add action listener to back button
     * @param actionListener is action listener from controller
     */
    public void addBackListener(ActionListener actionListener) {
        backButton.addActionListener(actionListener);
    }

    /**
     * TODO: dogru çalışıyor mu bakılmalı
     * @param actionListener
     * @param :video: bu vardı ama sildim ama durumdan emin değilim!
     */
    public void addVideoActionListener(ActionListener actionListener) {
        video.addActionListener(actionListener);
    }



    @Override
    public void update(Observable observable, Object o) {
        renderList();
    }

    class videoListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                frame.dispose();
                /**
                 * TODO:
                 */
                //new VideoView(VideoFinderHelper.findVideo(likedVideosModels, e.getActionCommand()), userModel);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }
}
