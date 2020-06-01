package Utilities;

import Entities.Video;
import Entities.User;

import java.util.ArrayList;
import java.util.List;

public class VideoFinderHelper {
    public static Video findVideo(List<Video> likedVideos, String videoTitle){
        for(Video video: likedVideos){
            String title = "Video : "+video.getTitle();
            if (title.equals(videoTitle))
                return video;
        }
        return null;
    }
    public static Video findVideoByID(String id,List<Video> videos){
        for (Video video: videos) {
            if(video.getId().equals(id)){
                return video;
            }
        }
        return null;
    }

    public static Video findVideoByTitle(String videoTitle,List<Video> videos){
        for(Video video: videos){
            String title = "Video : "+video.getTitle();
            if (title.equals(videoTitle))
                return video;
        }
        return null;
    }

    /**
     * @param user user who liked video
     * @return videos list
     */
    public static List<Video> findLikedVideosByUser(User user ,List<Video> videos){
        List<Video> likedVideos= new ArrayList<>();
        for(int i = 0; i < videos.size();i++){
            for(int j = 0 ; j < videos.get(i).getLikes().size();j++){
                if(user.getUserName().equals(videos.get(i).getLikes().get(j))){
                    likedVideos.add(videos.get(i));
                }
            }
        }
        return likedVideos;
    }

}
