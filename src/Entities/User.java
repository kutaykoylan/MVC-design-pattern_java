package Entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class User {
    private String username;
    private String password;
    private List<User> subscribers;
    private List<User> subscription;
    private List<List<Video>> watchLists;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("username='").append(username).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public User(String username, String password, List<User> subscribers, List<User> subscription, List<List<Video>> watchLists) {
        setUserName(username);
        setPassword(password);
        setSubscribers(subscribers);
        setSubscription(subscription);
        setWatchLists(watchLists);
    }

    public User() {
        this.username = "";
        this.password  ="";
        this.subscribers = new ArrayList<>();
        this.subscription = new ArrayList<>();
        this.watchLists = new ArrayList<>();;
    }

    public List<User> getSubscribers() {
        return subscribers;
    }

    public List<User> getSubscription() {
        return subscription;
    }

    public String getUserName() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public List<List<Video>> getWatchLists() {
        return watchLists;
    }

    public void setUserName(String username) {
        this.username = username;

    }

    public void setPassword(String password) {
        this.password = password;

    }

    public void setSubscribers(List<User> subscribers) {
        this.subscribers = subscribers;

    }

    public void setSubscription(List<User> subscription) {
        this.subscription = subscription;

    }

    public void setWatchLists(List<List<Video>> watchLists) {
        this.watchLists = watchLists;

    }

}
