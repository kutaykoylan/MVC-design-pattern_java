package Views;

import Entities.User;
import Models.UserModel;
import Views.components.ButtonHelper;
import Views.components.FrameHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;


public class Subscriber implements Observer {
    private JFrame frame;
    private JPanel pane;
    private JButton[] buttons;
    private JButton backButton;
    private JPanel panel_1;
    private JLabel pressToSeeLabel;
    private UserModel userModel;

    public Subscriber(UserModel userModel) {
        this.userModel = userModel;
        userModel.addObserver(this);
    }

    /**
     * ana sayfadaki videolar method olan haline veriliyordu kaldırıldı controllerda ekleniyor olarak planlandi
     */
    public void renderSubscriber() {
        frame = FrameHelper.createAFrame(100, 100, 1080, 723);
        if (pane != null) {
            pane.setVisible(false);
        }
        pane = new JPanel();
        pane.setBackground(Color.DARK_GRAY);
        pane.setLayout(new GridLayout(20, 1));

        buttons = new JButton[userModel.getLoggedInUser().getSubscribers().size()];
        JButton space = new JButton();
        space.setBorderPainted(false);
        space.setBackground(Color.DARK_GRAY);
        User subscribers[] = userModel.getLoggedInUser().getSubscribers().toArray(new User[0]);

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = ButtonHelper.createAButton(subscribers[i].getUserName(), Color.WHITE, 16, false, new Color(47, 79, 79), 0, 0, 0, 0);
            //addSubscriberListener();
            /**
             * TODO : line 40da commmente alınmış method alınıp içine diğer örneklerdeki gibi o class koncak
             */
            pane.add(buttons[i]);
        }

        backButton = ButtonHelper.createAButton("<< Back", Color.WHITE, 16, false, new Color(47, 79, 79), 0, 0, 0, 0);
        //addBackListener();
        /**TODO: aynı şeyler :D
         */

        pane.add(space);
        pane.add(backButton);

        panel_1 = new JPanel();
        panel_1.setBounds(131, 162, 131, 96);
        panel_1.setForeground(Color.WHITE);
        panel_1.setFont(new Font("Arial", Font.PLAIN, 16));
        panel_1.setBackground(Color.DARK_GRAY);

        pressToSeeLabel = new JLabel("Subscribers(press to see)");
        pressToSeeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        pressToSeeLabel.setForeground(Color.WHITE);

        panel_1.add(pressToSeeLabel);
        frame.add(panel_1, BorderLayout.NORTH);
        frame.add(pane, BorderLayout.CENTER);

    }

    public void setVisible(){
        frame.setVisible(true);
    }

    public void close(){
        frame.setVisible(false);
    }

    /**
     * TODO: bu diğer örneklrde oldugu gibi uygylancak!
     * public void actionPerformed(ActionEvent e) {
     * for(int i=0;i<subscriptions.length;i++){
     * if (e.getActionCommand().equals(subscriptions[i].getUserName())) {
     * try {
     * frame.dispose();
     * new ProfileView().createAndShowGuiForSubscriber(subscriptions[i],currentUser,videos);
     * } catch (Exception ex) {
     * ex.printStackTrace();
     * }
     * }
     * }
     * }
     */
    public void addSubscriberListener(ActionListener actionListener) {
       for(int i=0; i< buttons.length;i++){
            buttons[i].addActionListener(actionListener);
        }
    }

    /**
     * TODO:
     * backButton.addActionListener(new ActionListener() {
     * public void actionPerformed(ActionEvent e) {
     * try {
     * frame.dispose();
     * new ProfileView().createAndShowGuiForItself(currentUser, videos);
     * } catch (Exception ex) {
     * ex.printStackTrace();
     * }
     * }
     * });
     *
     * @param actionListener
     */
    public void addBackListener(ActionListener actionListener) {
        backButton.addActionListener(actionListener);
    }

    @Override
    public void update(Observable observable, Object o) {
        renderSubscriber();
    }
}