package DataIO;
import Entities.Video;


import java.util.List;

public interface IVideoIO {
    void writeVideoToJSON(Video video);
    void updateVideoOnJSON(Video video);
    List<Video> readVideosFromJSON();
}
