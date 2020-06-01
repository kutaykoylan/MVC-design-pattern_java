package Views;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import Entities.Comment;
import Entities.User;
import Entities.Video;
import Models.UserModel;
import Models.VideoModel;
import Views.components.ButtonHelper;
import Views.components.FrameHelper;

public class VideoView implements Observer {

    private JFrame frame;
    private VideoModel videoModel;
    private User user;
    private JPanel contentPane;
    private JTextField commentContent;
    private JButton backButton;
    private JPanel panel_1;
    private JTextPane useInformations;
    private JPanel panel;
    private JButton videoPhoto;
    private JButton likeButton;
    private JButton watchListButton;
    private JButton subscribeButton;
    private JButton dislikeButton;
    private JLabel numberOfLikesLabel;
    private JLabel numberOfDislikesLabel;
    private JLabel numberOfLikes;
    private JLabel numberOfDislikes;
    private JLabel videoTitle;
    private JButton addComment;
    private JLabel intendedAudience;
    private JLabel intendedAudienceText;
    private JLabel date;
    private JLabel dateText;
    private JPanel commentPanel;
    private JButton[] commentsButtons;

    public VideoView(VideoModel videoModel, UserModel userModel) {

        this.videoModel = videoModel;
        this.user = userModel.getLoggedInUser();

        videoModel.addObserver(this);
        userModel.addObserver(this);
        frame = FrameHelper.createAFrame(100, 100, 1080, 723);
        contentPane = new JPanel();
        contentPane.setBackground(Color.DARK_GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        frame.setContentPane(contentPane);
        contentPane.setLayout(null);

        panel_1 = new JPanel();
        panel_1.setBackground(new Color(47, 79, 79));
        panel_1.setBounds(0, 0, 1066, 51);
        contentPane.add(panel_1);
        panel_1.setLayout(null);

        useInformations = new JTextPane();
        useInformations.setBackground(new Color(47, 79, 79));
        useInformations.setForeground(new Color(255, 255, 255));
        useInformations.setText(this.user.getUserName());
        useInformations.setFont(new Font("Arial", Font.BOLD, 16));
        useInformations.setBounds(37, 10, 225, 50);
        panel_1.add(useInformations);

        panel = new JPanel();
        panel.setBounds(0, 590, 1066, 66);
        contentPane.add(panel);
        panel.setBackground(new Color(0, 128, 128));
        panel.setLayout(null);

        backButton = ButtonHelper.createAButton("<< Back", Color.WHITE, 16, false, new Color(47, 79, 79), 10, 10, 267, 47);
        panel.add(backButton);

        videoPhoto = ButtonHelper.createAButton("", null, 0, false, null, 28, 89, 663, 415, new ImageIcon("download.png"));
        contentPane.add(videoPhoto);

        likeButton = ButtonHelper.createAButton("Like", Color.WHITE, 16, false, new Color(47, 79, 79), 28, 528, 75, 47);
        contentPane.add(likeButton);

        watchListButton = ButtonHelper.createAButton("(+) Watchlist", Color.WHITE, 16, false, new Color(47, 79, 79), 210, 528, 267, 47);
        contentPane.add(watchListButton);

        dislikeButton = ButtonHelper.createAButton("Dislike", Color.WHITE, 16, false, new Color(47, 79, 79), 113, 528, 87, 47);
        contentPane.add(dislikeButton);

        numberOfLikesLabel = new JLabel("Number of Likes : ");
        numberOfLikesLabel.setFont(new Font("Arial", Font.BOLD, 14));
        numberOfLikesLabel.setForeground(SystemColor.textHighlightText);
        numberOfLikesLabel.setBounds(526, 535, 134, 34);
        contentPane.add(numberOfLikesLabel);

        numberOfDislikesLabel = new JLabel("Number of Dislikes : ");
        numberOfDislikesLabel.setFont(new Font("Arial", Font.BOLD, 14));
        numberOfDislikesLabel.setForeground(SystemColor.textHighlightText);
        numberOfDislikesLabel.setBounds(739, 535, 185, 34);
        contentPane.add(numberOfDislikesLabel);



        numberOfLikes = new JLabel(videoModel.getSelectedVideo().getLikes().size() + "");
        numberOfLikes.setForeground(Color.WHITE);
        numberOfLikes.setFont(new Font("Arial", Font.BOLD, 14));
        numberOfLikes.setBounds(652, 535, 58, 34);
        contentPane.add(numberOfLikes);

        numberOfDislikes = new JLabel(videoModel.getSelectedVideo().getDislikes().size() + "");
        numberOfDislikes.setForeground(Color.WHITE);
        numberOfDislikes.setFont(new Font("Arial", Font.BOLD, 14));
        numberOfDislikes.setBounds(885, 535, 58, 34);
        contentPane.add(numberOfDislikes);

        commentContent = new JTextField();
        //commentContent.setBackground(SystemColor.controlDkShadow);
        commentContent.setFont(new Font("Arial", Font.PLAIN, 14));
        commentContent.setBounds(701, 89, 355, 77);
        contentPane.add(commentContent);
        commentContent.setColumns(10);
        commentContent.setOpaque(true);

        videoTitle = new JLabel(videoModel.getSelectedVideo().getTitle());
        videoTitle.setFont(new Font("Arial", Font.BOLD, 16));
        videoTitle.setForeground(Color.WHITE);
        videoTitle.setBounds(28, 55, 367, 24);
        contentPane.add(videoTitle);

        addComment = ButtonHelper.createAButton("Add Comment", Color.WHITE, 16, false, new Color(47, 79, 79), 922, 176, 134, 47);
        contentPane.add(addComment);

        subscribeButton = ButtonHelper.createAButton("user", Color.WHITE, 16, false, new Color(47, 79, 79), 728, 176, 184, 47);
        contentPane.add(subscribeButton);

        intendedAudience = new JLabel("Intended Audience :");
        intendedAudience.setForeground(Color.WHITE);
        intendedAudience.setBounds(633, 43, 178, 47);
        contentPane.add(intendedAudience);
        intendedAudience.setBackground(Color.DARK_GRAY);
        intendedAudience.setFont(new Font("Arial", Font.PLAIN, 16));

        intendedAudienceText = new JLabel(videoModel.getSelectedVideo().getIntendedAudience());
        intendedAudienceText.setForeground(Color.WHITE);
        intendedAudienceText.setFont(new Font("Arial", Font.PLAIN, 16));
        intendedAudienceText.setBackground(Color.DARK_GRAY);
        intendedAudienceText.setBounds(821, 43, 178, 47);
        contentPane.add(intendedAudienceText);

        date = new JLabel("Date :");
        date.setForeground(Color.WHITE);
        date.setBounds(300, 43, 178, 47);
        contentPane.add(date);
        date.setBackground(Color.DARK_GRAY);
        date.setFont(new Font("Arial", Font.PLAIN, 16));
//videoModel.getSelectedVideo().getIntendedAudience()

        dateText = new JLabel(dateConverter(videoModel.getSelectedVideo().getDate()));
        dateText.setForeground(Color.WHITE);
        dateText.setFont(new Font("Arial", Font.PLAIN, 16));
        dateText.setBackground(Color.DARK_GRAY);
        dateText.setBounds(366, 43, 250, 47);
        contentPane.add(dateText);

        renderComments();
    }

    public void setVisible(){
        frame.setVisible(true);
    }

    public void close(){
        frame.setVisible(false);
        ButtonHelper.clearActionListenerAButton(addComment);
        ButtonHelper.clearActionListenerAButton(likeButton);
        ButtonHelper.clearActionListenerAButton(dislikeButton);
        ButtonHelper.clearActionListenerAButton(watchListButton);
    }

    public void setVisibleWatchListButton() {
        watchListButton.setVisible(true);
    }
    public void setInVisibleWatchListButton() {
        watchListButton.setVisible(false);
    }

    /**
     * Add the listener to back button
     * @param actionListener is action listener from controller
     */
    public void addBackButtonListener(ActionListener actionListener) {
        backButton.addActionListener(actionListener);
    }

    /**
     * Add the listener from controller to like button
     * @param actionListener is action listener
     */
    public void addLikeButtonListener(ActionListener actionListener) {
        likeButton.addActionListener(actionListener);
    }

    /**
     * TODO:
     * watchListButton.addActionListener(new ActionListener() {
     * public void actionPerformed(ActionEvent e) {
     * }
     * });
     *
     * @param actionListener
     */
    public void addWatchlistButtonListener(ActionListener actionListener) {
        watchListButton.addActionListener(actionListener);
    }

    /**
     * Add the action listener to dislike button
     * @param actionListener is action listener from controller
     */
    public void addDislikeButtonListener(ActionListener actionListener) {
        dislikeButton.addActionListener(actionListener);
    }

    /**
     * Add action listener to add comment button
     * @param actionListener
     */
    public void addAddCommentListener(ActionListener actionListener) {
        addComment.addActionListener(actionListener);
    }

    public void addSubscribeButtonListener(ActionListener actionListener) {
        subscribeButton.addActionListener(actionListener);
    }
    public void clearBackButtonListener(){
        ButtonHelper.clearActionListenerAButton(backButton);
    }

    public void renderComments(){
        if(commentPanel != null) commentPanel.setVisible(false);
        commentPanel = new JPanel();
        commentPanel.setBackground(new Color(0, 128, 128));
        commentPanel.setBounds(701, 240, 355, 264);
        contentPane.add(commentPanel);
        List<Comment> comments = videoModel.getSelectedVideo().getComments();
        commentsButtons = new JButton[comments.size()];
        for (int i = 0; i < comments.size(); i++) {
            commentsButtons[i] = ButtonHelper.createAButton(comments.get(i).getOwner() + " : " + comments.get(i).getContent(), Color.WHITE, 16, false, new Color(47, 79, 79), 0, 0, 0, 0);
            commentsButtons[i].setPreferredSize(new Dimension(40, 20));
            commentPanel.add(commentsButtons[i]);
        }
        commentPanel.setLayout(new GridLayout(8, 4, 10, 5));
        //setting grid layout of 3 rows and 3 columns

        commentPanel.setVisible(true);
    }

    public JTextField getCommentContent() {
        return commentContent;
    }

    private String dateConverter(Date date){
        return date.getDate()+"/"+date.getMonth()+"/"+(date.getYear()-120+2020);
    }

    @Override
    public void update(Observable observable, Object o) {
        //System.out.println(videoModel.getSelectedVideo().getTitle());
        videoTitle.setText(videoModel.getSelectedVideo().getTitle());
        intendedAudienceText.setText(videoModel.getSelectedVideo().getIntendedAudience());
        dateText.setText(dateConverter(videoModel.getSelectedVideo().getDate()));
        subscribeButton.setText("Subscribe to : "+videoModel.getSelectedVideo().getOwnerUsername());
        numberOfLikes.setText("" + videoModel.getSelectedVideo().getLikes().size());
        numberOfDislikes.setText("" + videoModel.getSelectedVideo().getDislikes().size());
        renderComments();
    }



}


