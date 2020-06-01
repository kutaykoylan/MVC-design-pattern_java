package Views;

import Models.UserModel;
import Models.VideoModel;
import Views.components.ButtonHelper;
import Views.components.FrameHelper;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class WatchlistView implements Observer {
    private JFrame frame;
    private JPanel contentPane;
    private VideoModel videoModel;
    private UserModel userModel;
    private JButton[] buttons;
    private JTextPane useInformations;
    private JButton likedwatchListVideosButton;
    private JButton backButton;
    private JPanel videoPanel;


    
    public WatchlistView(UserModel userModel, VideoModel videoModel){
        this.videoModel = videoModel;
        this.userModel = userModel;
        this.userModel.addObserver(this);
        createAndShowGui();
    }

    private void renderList(){

        if(videoPanel != null) videoPanel.setVisible(false); // Unvisible before video panel
        videoPanel = new JPanel();
        videoPanel.setBackground(Color.DARK_GRAY);
        videoPanel.setBounds(30, 68, 994, 581);
        contentPane.add(videoPanel);

        JButton btnNewButton_4 = new JButton("Video");


        btnNewButton_4.setFont(new Font("Arial", Font.PLAIN, 18));
        if(!userModel.getSelectedWatchListsID().equals("")) {
            int watchlisID = Integer.parseInt(userModel.getSelectedWatchListsID());
            buttons = new JButton[userModel.getLoggedInUser().getWatchLists().get(watchlisID).size()];
            for (int i = 0; i < userModel.getLoggedInUser().getWatchLists().get(watchlisID).size(); i++) {
                buttons[i] = ButtonHelper.createAButton("Video : " + userModel.getLoggedInUser().getWatchLists().get(watchlisID).get(i).getTitle(), SystemColor.window, 20, false, new Color(47, 79, 79), 0, 0, 0, 0);
                buttons[i].setPreferredSize(new Dimension(40, 40));
                videoPanel.add(buttons[i]);
            }
        }
        videoPanel.setLayout(new GridLayout(4,4,10,10));
        //setting grid layout of 3 rows and 3 columns

        videoPanel.setVisible(true);
    }

    private void createAndShowGui() {
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

        likedwatchListVideosButton = ButtonHelper.createAButton("Watchlist ID : "+ userModel.getSelectedWatchListsID(),new Color(255, 255, 255),16,false,new Color(47, 79, 79),470, 10, 184, 43);
        /*likedwatchListVideosButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });*/
        panel.add(likedwatchListVideosButton);

        backButton = ButtonHelper.createAButton("<< Back",Color.WHITE,16,false,new Color(0, 128, 128),10, 10, 267, 47);
        /*backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                //new MainView().createAndShowGui(user,videos);
            }
        });*/
        panel.add(backButton);

        useInformations = new JTextPane();
        useInformations.setBackground(new Color(47, 79, 79));
        useInformations.setForeground(new Color(255, 255, 255));
        useInformations.setText(userModel.getLoggedInUser().getUserName());
        useInformations.setFont(new Font("Arial", Font.BOLD, 16));
        useInformations.setBounds(900, 10, 400, 50);
        panel.add(useInformations);

        renderList();


        //frame.setVisible(true);
    }

    public void addBackButtonListener(ActionListener actionListener){
        this.backButton.addActionListener(actionListener);
    }
    public void addWatchlistVideoButtonListener(ActionListener actionListener){
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].addActionListener(actionListener);
        }
    }

    public void setVisible(){
        frame.setVisible(true);
    }

    public void close(){
        frame.setVisible(false);
    }

    @Override
    public void update(Observable observable, Object o) {

        useInformations.setText(this.userModel.getLoggedInUser().getUserName());
      //  System.out.println(this.userModel.getLoggedInUser().getUserName());
        likedwatchListVideosButton.setText("Watchlist ID : " + this.userModel.getSelectedWatchListsID());
        renderList();
    }
}
