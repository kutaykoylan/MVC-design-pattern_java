package Utilities;

import Entities.User;

import java.util.List;

public class UserFinderHelper {

    public static User findUserByUsername(String username,List<User> users){
        for (User user: users) {
            if (user.getUserName().equals(username))
                return user;
        }
        return null;
    }
    public static User checkUserIsExist(String username, String password,List<User> users){
        for (User user: users) {
            if(user.getUserName().equals(username) && user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }
    public static User findUserByUsername(List<User> users, String username){
        for (User user: users) {
            if (user.getUserName().equals(username))
                return user;
        }
        return null;
    }

}
