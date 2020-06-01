package App;

import Models.UserModel;
import Models.VideoModel;

public class ModelCreator {
    private UserModel userModel;
    private VideoModel videoModel;

    public ModelCreator(){
        this.userModel = new UserModel();
        this.videoModel = new VideoModel();
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public VideoModel getVideoModel() {
        return videoModel;
    }
}
