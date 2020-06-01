package App;

import Controllers.UserController;
import Controllers.VideoController;
import Models.UserModel;
import Models.VideoModel;
import Views.*;

public class Player {
    private LoginView loginView ;
    private LoggedIn loggedIn;
    private MainView mainView ;
    private VideoView videoView;
    private MainLikedVideos mainLikedVideos;
    private ProfileView profileView ;
    private Subscriber subscriber;
    private Subscription subscription;
    private WatchlistsView watchlistsView;
    private WatchlistView watchlistView ;
    private SubscriberProfileView subscriberProfileView;
    private WatchlistsViewForVideo watchlistsViewForVideo ;
    private UserModel userModel;
    private VideoModel videoModel;
    private VideoController videoController;
    private UserController userController;

    public Player(){
        ModelCreator modelCreator = new ModelCreator();
        userModel = modelCreator.getUserModel();
        videoModel = modelCreator.getVideoModel();
        ViewCreator viewCreator = new ViewCreator(userModel, videoModel);
        loginView = viewCreator.getLoginView();
        loggedIn = viewCreator.getLoggedIn();
        mainView = viewCreator.getMainView();
        videoView = viewCreator.getVideoView();
        mainLikedVideos = viewCreator.getMainLikedVideos();
        profileView = viewCreator.getProfileView();
        subscriber = viewCreator.getSubscriber();
        subscription = viewCreator.getSubscription();
        watchlistsView = viewCreator.getWatchlistsView();
        watchlistView = viewCreator.getWatchlistView();
        subscriberProfileView = viewCreator.getSubscriberProfileView();
        watchlistsViewForVideo = viewCreator.getWatchlistsViewForVideo();
        videoController = new VideoController(watchlistView, watchlistsViewForVideo, mainView, videoView, mainLikedVideos, userModel, videoModel);
        userController = new UserController(subscriberProfileView, videoView, userModel, videoModel, loginView, loggedIn, mainView, profileView, subscriber, subscription, watchlistsView, watchlistView);
    }
    public void start(){
        userController.showLoginView();
    }

}
