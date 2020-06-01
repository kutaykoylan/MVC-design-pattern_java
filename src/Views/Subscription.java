package Views;

import Entities.User;
import Entities.Video;
import Models.UserModel;
import Models.VideoModel;
import Views.components.ButtonHelper;
import Views.components.FrameHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Observable;
import java.util.Observer;


public class Subscription implements Observer {
    private JFrame frame;
    private JPanel pane;
    private JButton[] buttons;
    private UserModel userModel;
    private User[] subscriptions;
    private JPanel panel_1;
    private JLabel pressToUnfollow;
    private JButton backButton;



    public Subscription(UserModel userModel) {
        this.userModel = userModel;
        userModel.addObserver(this);
        renderSubscription();
    }

    public void render() {
        subscriptions = userModel.getLoggedInUser().getSubscription().toArray(new User[0]);

        if (pane != null) {
            pane.setVisible(false);
        }
        pane = new JPanel();
        pane.setBackground(Color.DARK_GRAY);
        pane.setLayout(new GridLayout(20, 1));

        buttons = new JButton[subscriptions.length];
        JButton space = new JButton();
        space.setBorderPainted(false);
        space.setBackground(Color.DARK_GRAY);

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = ButtonHelper.createAButton(subscriptions[i].getUserName(), Color.WHITE, 16, false, new Color(47, 79, 79), 0, 0, 0, 0);
            //addUnfollowButtonListener(..., i);
            pane.add(buttons[i]);

        }
        backButton = ButtonHelper.createAButton("<< Back", Color.WHITE, 16, false, new Color(47, 79, 79), 0, 0, 0, 0);
        //addBackButtonListener(...); TODO:

        pane.add(space);

        pane.add(backButton);
        frame.add(pane, BorderLayout.CENTER);
        pane.setVisible(true);
    }


    /**
     * videos dışardan veriliyor
     *
     * @param
     */

    public void renderSubscription() {

        frame = FrameHelper.createAFrame(100, 100, 1080, 723);

        render();

        panel_1 = new JPanel();
        panel_1.setBounds(131, 162, 131, 96);
        panel_1.setForeground(Color.WHITE);
        panel_1.setFont(new Font("Arial", Font.PLAIN, 16));
        panel_1.setBackground(Color.DARK_GRAY);

        pressToUnfollow = new JLabel("Subscriptions(press to unfollow)");
        pressToUnfollow.setFont(new Font("Arial", Font.PLAIN, 16));
        pressToUnfollow.setForeground(Color.WHITE);

        panel_1.add(pressToUnfollow);

        //     JLabel jLabel =new JLabel("Subscriptions(press to unfollow)");
        frame.add(panel_1, BorderLayout.NORTH);
        frame.add(pane, BorderLayout.CENTER);
    }

    /**
     *  public void actionPerformed(ActionEvent e) {
     *                     User unfollowedUser = findUser(userModel.getSubscription(), e.getActionCommand());
     *                     try {
     *                         frame.dispose();
     *                         userModel.getSubscription().remove(unfollowedUser);
     *                         new Unfollowed().createAndShowGui(userModel, unfollowedUser, videos);
     *                     } catch (Exception ex) {
     *                         ex.printStackTrace();
     *                     }
     *
     *                      TODO:user will be removed
     *
     *
             *
}
     * @param actionListener
     * @param  : index in for loop
     */
    public void addUnFollowButtonListener(ActionListener actionListener){
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].addActionListener(actionListener);
        }
    }

    /**
     * TODO:
     *  backButton.addActionListener(new ActionListener() {
     *             public void actionPerformed(ActionEvent e) {
     *                 try {
     *                     frame.dispose();
     *                     new ProfileView().createAndShowGuiForItself(userModel, videos);
     *                 } catch (Exception ex) {
     *                     ex.printStackTrace();
     *                 }
     *             }
     *         });
     * @param actionListener
     */
    public void addBackButtonListener(ActionListener actionListener){
        backButton.addActionListener(actionListener);
    }


    private User findUser(List<User> subscription, String username) {

        for (User user : subscription) {
            if (user.getUserName().equals(username))
                return user;
        }
        return null;
    }

    public void close() {
        frame.setVisible(false);
        for (int i = 0; i < buttons.length ; i++) {
            ButtonHelper.clearActionListenerAButton(buttons[i]);
        }
    }

    public void setVisible() {
        frame.setVisible(true);
    }

    @Override
    public void update(Observable observable, Object o) {
        render();
    }
}