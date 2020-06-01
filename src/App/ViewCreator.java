package App;

import Models.UserModel;
import Models.VideoModel;
import Views.*;

public class ViewCreator {
    private LoginView loginView;
    private LoggedIn loggedIn;
    private MainView mainView;
    private VideoView videoView;
    private MainLikedVideos mainLikedVideos;
    private ProfileView profileView;
    private Subscriber subscriber;
    private Subscription subscription;
    private WatchlistsView watchlistsView;
    private WatchlistView watchlistView;
    private SubscriberProfileView subscriberProfileView;
    private WatchlistsViewForVideo watchlistsViewForVideo;

    ViewCreator(UserModel userModel, VideoModel videoModel) {
        loginView = new LoginView();
        loggedIn = new LoggedIn();
        mainView = new MainView(userModel, videoModel.getVideos());
        videoView = new VideoView(videoModel, userModel);
        mainLikedVideos = new MainLikedVideos(userModel, videoModel);
        profileView = new ProfileView(userModel, videoModel);
        subscriber = new Subscriber(userModel);
        subscription = new Subscription(userModel);
        watchlistsView = new WatchlistsView(userModel, videoModel);
        watchlistView = new WatchlistView(userModel, videoModel);
        subscriberProfileView = new SubscriberProfileView(userModel);
        watchlistsViewForVideo = new WatchlistsViewForVideo(userModel, videoModel);
    }

    public LoginView getLoginView() {
        return loginView;
    }

    public LoggedIn getLoggedIn() {
        return loggedIn;
    }

    public MainView getMainView() {
        return mainView;
    }

    public VideoView getVideoView() {
        return videoView;
    }

    public MainLikedVideos getMainLikedVideos() {
        return mainLikedVideos;
    }

    public ProfileView getProfileView() {
        return profileView;
    }

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public WatchlistsView getWatchlistsView() {
        return watchlistsView;
    }

    public WatchlistView getWatchlistView() {
        return watchlistView;
    }

    public SubscriberProfileView getSubscriberProfileView() {
        return subscriberProfileView;
    }

    public WatchlistsViewForVideo getWatchlistsViewForVideo() {
        return watchlistsViewForVideo;
    }
}
