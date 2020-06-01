package Models;

import DataIO.IVideoIO;
import DataIO.VideoIO;
import Entities.Comment;
import Entities.User;
import Entities.Video;
import Utilities.VideoFinderHelper;

import java.util.List;
import java.util.Observable;

public class VideoModel extends Observable {

    private List<Video> videos;
    private IVideoIO videoIO;
    private Video selectedVideo = new Video();


    public VideoModel() {
        this.videoIO = new VideoIO();
        this.videos = videoIO.readVideosFromJSON();
    }


    public List<Video> getVideos() {
        return videos;
    }

    public void setSelectedVideo(Video selectedVideo) {
        this.selectedVideo = selectedVideo;
        setChanged();
        notifyObservers();
    }

    /**
     * Increase like the selected video
     * @param user is username
     */
    public void increaseLike(String user){
        this.selectedVideo.increaseLike(user);
        setChanged();
        notifyObservers();
    }

    /**
     * Increase dislike the selected video
     * @param username is username
     */
    public void increaseDislike(String username){
        this.selectedVideo.increaseDislike(username);
        setChanged();
        notifyObservers();
    }

    /**
     * Add comment to selected video
     * @param content is content od comment
     * @param username is username of user who adds comment
     */
    public void addComment(String content, String username){
        Comment comment = new Comment(username, content);
        selectedVideo.addComment(comment);
        setChanged();
        notifyObservers();
    }


    public Video getSelectedVideo() {
        return selectedVideo;
    }

    public Video findVideoByTitle(String videoTitle){
        return VideoFinderHelper.findVideoByTitle(videoTitle,this.videos);

        /*for(Video video: videos){
            String title = "Video : "+video.getTitle();
            if (title.equals(videoTitle))
                return video;
        }
        return null;*/
    }

    /**
     * @param user user who liked video
     * @return videos list
     */
    public List<Video> findLikedVideosByUser(User user){
        return VideoFinderHelper.findLikedVideosByUser(user,this.videos);
        /*List<Video> likedVideos= new ArrayList<>();
        for(int i = 0; i < this.videos.size();i++){
            for(int j = 0 ; j < this.videos.get(i).getLikes().size();j++){
                if(user.getUserName().equals(this.videos.get(i).getLikes().get(j))){
                    likedVideos.add(this.videos.get(i));
                }
            }
        }
        return likedVideos;*/
    }
}
