package Controllers;

import Entities.User;
import Entities.Video;
import Models.UserModel;
import Models.VideoModel;
import Views.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UserController {

    private UserModel userModel;
    private LoginView loginView;
    private LoggedIn loggedIn;
    private VideoModel videoModel;
    private MainView mainView;
    private ProfileView profileView;
    private Subscriber subscriber;
    private Subscription subscription;
    private WatchlistsView watchlistsView;
    private WatchlistView watchlistView;
    private VideoView videoView;
    private SubscriberProfileView subscriberProfileView;

    public UserController(SubscriberProfileView subscriberProfileView, VideoView videoView, UserModel userModel, VideoModel videoModel, LoginView loginView, LoggedIn loggedIn, MainView mainView, ProfileView profileView, Subscriber subscriber, Subscription subscription, WatchlistsView watchlistsView, WatchlistView watchlistView) {
        this.subscriberProfileView = subscriberProfileView;
        this.videoView = videoView;
        this.userModel = userModel;
        this.loginView = loginView;
        this.loggedIn = loggedIn;
        this.videoModel = videoModel;
        this.mainView = mainView;
        this.profileView = profileView;
        this.subscriber = subscriber;
        this.subscription = subscription;
        this.watchlistsView = watchlistsView;
        this.watchlistView = watchlistView;
        mainView.addProfileButtonListener(new MainProfileButtonListener());
    }

    public void login(String userName, String password) {
        User user = userModel.checkUserIsExist(userName, password);
        if (user != null) {
            this.loginView.close();
            this.loggedIn.setVisible();
            this.loggedIn.addOkButtonListener(new LoggedInOkButtonListener());
            this.loggedIn.addCancelButtonListener(new LoggedInCancelButtonListener());
            this.userModel.setLoggedInUser(user);
        } else {
            this.showLoginView();
        }
    }

    public void showLoginView() {
        loginView.setVisible();
        loginView.addLoginButtonListener(new LoginButtonListener());
    }


    public void setMainView(MainView mainView) {
        this.mainView = mainView;
    }


    class LoginButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = loginView.getUsername().getText();
            String password = loginView.getPassword().getText();
            login(username, password);
        }
    }

    class LoggedInOkButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Video> videos = videoModel.getVideos();
            loggedIn.close();
            mainView.setVideos(videos);
            mainView.setVisible();
        }
    }

    class LoggedInCancelButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            loggedIn.close();
            loginView.setVisible();
        }
    }

    class MainProfileButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            mainView.close();
            profileView.addBackButton1Listener(new ProfileBackButtonListener());
            profileView.addbtnSubscribersListener(new ProfileBtnSubscribersListener());
            profileView.addSubscriptionButtonListener(new SubscriptionButtonListener());
            profileView.addWatchlistsButtonListener(new ProfileWatchListButtonListener());
            profileView.setVisible();
        }
    }

    class ProfileBackButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            profileView.close();
            mainView.setVisible();
        }
    }

    class ProfileBtnSubscribersListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            profileView.close();
            subscriber.addBackListener(new SubscriberBackButtonListener());
            subscriber.addSubscriberListener(new SubscriberButtonForSubscribersListener());
            subscriber.setVisible();
        }
    }


    class SubscriptionUnFollowListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            userModel.deleteSubscription(e.getActionCommand());
            subscription.addBackButtonListener(new SubscriptionBackButtonListener());
            subscription.addUnFollowButtonListener(new SubscriptionUnFollowListener());
        }
    }

    class SubscriptionBackButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            profileView.setVisible();
            subscription.close();
        }
    }

    class SubscriptionButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            subscription.setVisible();
            subscription.addBackButtonListener(new SubscriptionBackButtonListener());
            subscription.addUnFollowButtonListener(new SubscriptionUnFollowListener());
            profileView.close();

        }
    }

    class SubscriberBackButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            profileView.setVisible();
            subscriber.close();
        }
    }

    class SubscriberButtonForSubscribersListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            subscriberProfileView.setVisible();
            subscriber.close();
            userModel.setViewedUser(e.getActionCommand());
            subscriberProfileView.addBackButtonListener(new BackButtonListenerForSubscriberProfile());
        }
    }

    /**
     * TODO:
     */
    class BackButtonListenerForSubscriberProfile implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            subscriberProfileView.close();
            subscriber.addBackListener(new SubscriberBackButtonListener());
            subscriber.addSubscriberListener(new SubscriberButtonForSubscribersListener());
            subscriber.setVisible();
        }
    }


    class WatchListBackButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            watchlistsView.setVisible();
            watchlistsView.addWatchListListeners(new WatchListsButtonListener());
            watchlistView.close();
        }
    }


    class WatchListsBackButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            watchlistsView.close();
            watchlistsView.clearListeners(); // Clear the button listeners
            profileView.setVisible();
        }
    }

    class ProfileWatchListButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            profileView.close();
            watchlistsView.addBackButtonListener(new WatchListsBackButtonListener());
            watchlistsView.addWatchListListeners(new WatchListsButtonListener());
            watchlistsView.addCreateButtonListener(new CreateWatchtListButton());
            watchlistsView.setVisible();
        }
    }
    class CreateWatchtListButton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            userModel.addNewWatchlist();
            watchlistsView.close();
            profileView.setVisible();
        }
    }

    class WatchListsButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            userModel.setSelectedWatchListsID(e.getActionCommand()); // Set the selected watch list
            watchlistView.addBackButtonListener(new WatchListBackButtonListener());
            watchlistView.addWatchlistVideoButtonListener(new VideoButtonListenerForWatchlist());
            watchlistView.setVisible();
            watchlistsView.close();
        }
    }

    class VideoButtonListenerForWatchlist implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            videoView.setVisible();
            Video video = videoModel.findVideoByTitle(e.getActionCommand());
            videoModel.setSelectedVideo(video);
            videoView.clearBackButtonListener();
            videoView.addBackButtonListener(new VideoViewBackButtonListenerForWatchlist());
            videoView.addDislikeButtonListener(new DislikeListener());
            videoView.addLikeButtonListener(new LikeListener());
            videoView.addAddCommentListener(new AddCommentListener());
            videoView.setInVisibleWatchListButton();
            watchlistView.addWatchlistVideoButtonListener(new VideoButtonListenerForWatchlist());
            videoView.addSubscribeButtonListener(new VideoViewSubscribeButtonListener());
            watchlistView.close();
        }
    }

    class VideoViewSubscribeButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            userModel.subscribeToVideoOwner(videoModel.getSelectedVideo().getOwnerUsername());
        }
    }

    class DislikeListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            videoModel.increaseDislike(userModel.getLoggedInUser().getUserName());
        }
    }

    class LikeListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            videoModel.increaseLike(userModel.getLoggedInUser().getUserName());
        }
    }

    class AddCommentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String comment = videoView.getCommentContent().getText();
            videoModel.addComment(comment, userModel.getLoggedInUser().getUserName());
        }
    }

    class VideoViewBackButtonListenerForWatchlist implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            videoView.close();
            videoView.setVisibleWatchListButton();
            watchlistsView.setVisible();
        }

    }

}
