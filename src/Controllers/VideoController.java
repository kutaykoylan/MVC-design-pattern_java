package Controllers;

import Entities.User;
import Entities.Video;
import Models.UserModel;
import Models.VideoModel;
import Views.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VideoController {

    private UserModel userModel;
    private VideoModel videoModel;
    private MainView mainView;
    private VideoView videoView;
    private MainLikedVideos mainLikedVideos;
    private WatchlistsViewForVideo watchlistsViewForVideo;
    private WatchlistView watchlistView;

    public VideoController(WatchlistView watchlistView,WatchlistsViewForVideo watchlistsViewForVideo,MainView mainView, VideoView videoView, MainLikedVideos mainLikedVideos, UserModel userModel, VideoModel videoModel) {
        this.watchlistView = watchlistView;
        this.watchlistsViewForVideo=watchlistsViewForVideo;
        this.videoModel = videoModel;
        this.mainView = mainView;
        this.userModel = userModel;
        this.videoView = videoView;
        this.mainLikedVideos = mainLikedVideos;
        mainView.addVideoButtonListener(new MainViewVideoButtonListener()); // set the video action listeners
        mainView.addLikedVideosListener(new MainLikedVideosButtonListener());
    }

    class MainViewVideoButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Video video = videoModel.findVideoByTitle(e.getActionCommand());
            videoModel.setSelectedVideo(video);
            videoView.setVisible();
            videoView.addBackButtonListener(new VideoViewBackButtonListener());
            videoView.addDislikeButtonListener(new DislikeListener());
            videoView.addLikeButtonListener(new LikeListener());
            videoView.addAddCommentListener(new AddCommentListener());
            videoView.addSubscribeButtonListener(new VideoViewSubscribeButtonListener());
            videoView.addWatchlistButtonListener(new WatchListsButtonListener());
            mainView.close();
        }
    }

    class WatchListsButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            watchlistsViewForVideo.setVisible();
            watchlistsViewForVideo.addWatchListListeners(new AddWatchlistListener());
            watchlistsViewForVideo.addCreateButtonListener(new CreateWatchtListButton());
            watchlistsViewForVideo.addBackButtonListener(new BackWatchtListButton());
            watchlistView.close();
        }
    }
    class AddWatchlistListener implements  ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            userModel.setVideoToAddWatchlist(videoModel.getSelectedVideo() );
            userModel.addToNewWatchlist(e.getActionCommand());
        }
    }
    class CreateWatchtListButton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            userModel.addNewWatchlist();
            watchlistsViewForVideo.close();
            videoView.setVisible();
        }
    }
    class BackWatchtListButton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            watchlistsViewForVideo.close();
            videoView.setVisible();
        }
    }

    class VideoViewSubscribeButtonListener implements  ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            userModel.subscribeToVideoOwner(videoModel.getSelectedVideo().getOwnerUsername());
        }
    }


    class VideoViewBackButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            mainView.setVisible();
            videoView.close();
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

    class MainLikedVideosButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            mainLikedVideos.setVisible();
            mainView.close();
            mainLikedVideos.addBackListener(new MainLikedVideosBackListener());
        }
    }

    class MainLikedVideosBackListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            mainLikedVideos.close();
            mainView.setVisible();
        }
    }


}
