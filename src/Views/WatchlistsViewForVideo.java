package Views;

import Entities.User;
import Entities.Video;
import Models.UserModel;
import Models.VideoModel;
import Views.components.ButtonHelper;
import Views.components.FrameHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class WatchlistsViewForVideo implements Observer {
    private JFrame frame;
    private JPanel pane;
    private JButton[] buttons;
    private JButton backButton;
    private UserModel userModel;
    private VideoModel videoModel;
    private JButton createButton;

    public WatchlistsViewForVideo(UserModel userModel, VideoModel videoModel){
        this.userModel = userModel;
        this.videoModel = videoModel;
        videoModel.addObserver(this);
        userModel.addObserver(this);
        createAndShowGui();
    }

    public void renderList(){
        if(pane != null) pane.setVisible(false);
        pane = new JPanel();
        pane.setBackground(Color.DARK_GRAY);
        pane.setLayout(new GridLayout(20,1));

        buttons = new JButton[userModel.getLoggedInUser().getWatchLists().size()];

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = ButtonHelper.createAButton(i+"",Color.WHITE,16,false,new Color(47, 79, 79),0,0,0,0);
            pane.add(buttons[i]);
        }

        JButton space = new JButton();
        space.setBorderPainted(false);
        space.setBackground(Color.DARK_GRAY);
        pane.add(space);
        pane.add(createButton);
        pane.add(backButton);
        frame.add(pane, BorderLayout.CENTER);
    }


    public void createAndShowGui() {
        frame = FrameHelper.createAFrame(100, 100, 1080, 723);
        backButton =  ButtonHelper.createAButton("<< Back",Color.WHITE,16,false,new Color(47, 79, 79),0,0,0,0);


        createButton =  ButtonHelper.createAButton("Create",Color.WHITE,16,false,new Color(47, 79, 79),0,0,0,0);

        renderList();
        JPanel panel_1 = new JPanel();
        panel_1.setBounds(131, 162, 131, 96);
        panel_1.setForeground(Color.WHITE);
        panel_1.setFont(new Font("Arial", Font.PLAIN, 16));
        panel_1.setBackground(Color.DARK_GRAY);

        JLabel lblNewLabel = new JLabel("Watchlists by their indexes (press to see)");
        lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        lblNewLabel.setForeground(Color.WHITE);

        panel_1.add(lblNewLabel);

        //     JLabel jLabel =new JLabel("Subscriptions(press to unfollow)");
        frame.add(panel_1, BorderLayout.NORTH);

    }

    public void setVisible(){
        frame.setVisible(true);
    }

    public void close(){
        frame.setVisible(false);
    }

    public void clearListeners(){
        for (int i = 0; i < buttons.length; i++) {
            ButtonHelper.clearActionListenerAButton(buttons[i]);
        }
        ButtonHelper.clearActionListenerAButton(backButton);
        ButtonHelper.clearActionListenerAButton(createButton);
    }

    public void addWatchListListeners(ActionListener actionListener){
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].addActionListener(actionListener);
        }
    }
    public void addCreateWatchlistListener(ActionListener actionListener){
        createButton.addActionListener(actionListener);
    }

    public void addBackButtonListener(ActionListener actionListener){
        this.backButton.addActionListener(actionListener);
    }

    public void addCreateButtonListener(ActionListener actionListener){
        this.createButton.addActionListener(actionListener);
    }

    @Override
    public void update(Observable observable, Object o) {
        renderList();
    }
}
