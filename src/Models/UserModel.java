package Models;

import DataIO.IUserIO;
import DataIO.UserIO;
import Entities.User;
import Entities.Video;
import Utilities.UserFinderHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class UserModel extends Observable {

   private List<User> users;
   private IUserIO userIO;
   private User loggedInUser = new User();
   private String selectedWatchListsID;
   private User viewedUser;
   private Video videoToAddWatchlist;

    public void setVideoToAddWatchlist(Video videoToAddWatchlist) {
        this.videoToAddWatchlist = videoToAddWatchlist;
    }

    public UserModel() {
        this.userIO = new UserIO();
        this.users = userIO.readUsersFromXml();
        this.selectedWatchListsID = "";
        this.viewedUser = new User();
    }

    public User findUserByUsername(String username){
        return UserFinderHelper.findUserByUsername(username,this.users);
        /*for (User user: this.users) {
            if (user.getUserName().equals(username))
                return user;
        }
        return null;*/
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
        setChanged();
        notifyObservers();
    }

    public User checkUserIsExist(String username, String password){
        return UserFinderHelper.checkUserIsExist(username,password,this.users);
        /*for (User user: users) {
            if(user.getUserName().equals(username) && user.getPassword().equals(password)){
                return user;
            }
        }
        return null;*/
    }
    public void addNewWatchlist(){
        getLoggedInUser().getWatchLists().add(new ArrayList<>());
        userIO.writeUsersToXml(users);
        notifyObserver();
    }
    public void addToNewWatchlist(String selectedWatchListsID){
        getLoggedInUser().getWatchLists().get(Integer.parseInt(selectedWatchListsID)).add(videoToAddWatchlist);
        userIO.writeUsersToXml(users);
        notifyObserver();
    }

    /**
     * Get the selected video watch list id
     * @return
     */
    public String getSelectedWatchListsID() {
        return selectedWatchListsID;
    }

    /**
     * Set the selected watch list id call for every watch list clicked
     * @param selectedWatchListsID
     */
    public void setSelectedWatchListsID(String selectedWatchListsID) {
        this.selectedWatchListsID = selectedWatchListsID;
        notifyObserver();
    }

    /**
     * Delete user from my subscription
     * @param username is the name of subscription to be deleted
     */
    public void deleteSubscription(String username) {
        List<User> subscriptions = loggedInUser.getSubscription();
        User userToBeDeleted = findUserByUsername(username);
        subscriptions.remove(userToBeDeleted);
        userIO.writeUsersToXml(users);
        notifyObserver();
    }

    public User getViewedUser() {
        return viewedUser;
    }

    /**
     * @param userName
     */
    public void subscribeToVideoOwner(String userName){
        User subscribedUser = findUserByUsername(userName);
        //System.out.println("video sahibi : "+subscribedUser.getUserName());
        if(!userName.equals(getLoggedInUser()) && !getLoggedInUser().getSubscription().contains(subscribedUser)){
            getLoggedInUser().getSubscription().add(subscribedUser);
        }
        userIO.writeUsersToXml(users);
        notifyObserver();
    }
    public void setViewedUser(String viewedUser) {
        this.viewedUser = findUserByUsername(viewedUser);
        notifyObserver();
    }
    private void notifyObserver(){
        setChanged();
        notifyObservers();
    }
}
