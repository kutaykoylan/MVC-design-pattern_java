package DataIO;

import Entities.User;
import Entities.Video;

import java.util.List;

public interface IUserIO {
    List<User> readUsersFromXml();
    void writeUsersToXml(List<User> users);
    Video findVideoByID(String id);
}
