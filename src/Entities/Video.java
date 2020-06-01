package Entities;

import DataIO.IVideoIO;
import DataIO.VideoIO;

import java.util.*;

public class Video{

    private String id;
    private String ownerUsername;
    private String title;
    private String content;
    private String intendedAudience;
    private Date date;
    private List<Comment> comments;
    private List<String> likes;
    private List<String> dislikes;
    private IVideoIO videoIO = new VideoIO();

    public Video(){
        this.title = "";
        this.content = "";
        this.intendedAudience = "";
        this.date = new Date();
        this.comments = new ArrayList<>();
        this.likes = new ArrayList<>();
        this.dislikes = new ArrayList<>();
        this.ownerUsername = "";
    }

    public Video (Video video){
        this.title = video.title;
        this.content = video.content;
        this.intendedAudience = video.intendedAudience;
        this.likes = video.likes;
        this.dislikes = video.dislikes;
        this.date = video.date;
        this.ownerUsername = video.getOwnerUsername();
    }

    public Video(String id, String ownerUsername, String title, String content, String intendedAudience, List<Comment> comments, List<String> likes, List<String> dislikes,Date date) {
        setTitle(title);
        setContent(content);
        setIntendedAudience(intendedAudience);
        setId(id);
        setComments(comments);
        setLikes(likes);
        setDislikes(dislikes);
        setDate(date);
        setOwnerUsername(ownerUsername);
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }

    public String getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getIntendedAudience() {
        return intendedAudience;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setIntendedAudience(String intendedAudience) {
        this.intendedAudience = intendedAudience;
    }

    public void addComment(Comment newComment){
        this.comments.add(newComment);
        videoIO.updateVideoOnJSON(this);
    }


    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Video video = (Video) o;
        return Objects.equals(id, video.id) &&
                Objects.equals(title, video.title) &&
                Objects.equals(content, video.content) &&
                Objects.equals(intendedAudience, video.intendedAudience) &&
                Objects.equals(date, video.date) &&
                Objects.equals(comments, video.comments) &&
                Objects.equals(likes, video.likes) &&
                Objects.equals(dislikes, video.dislikes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, intendedAudience, date, comments, likes, dislikes);
    }

    public List<String> getLikes() {
        return likes;
    }

    public void setLikes(List<String> likes) {
        this.likes = likes;
    }

    public List<String> getDislikes() {
        return dislikes;
    }

    public void setDislikes(List<String> dislikes) {
        this.dislikes = dislikes;
    }

    public void increaseLike(String user){
        if(!this.getLikes().contains(user)){
            this.getLikes().add(user);
            videoIO.updateVideoOnJSON(this);
        }
    }
    public void increaseDislike(String user){
        if(!this.getDislikes().contains(user)){
            this.getDislikes().add(user);
            videoIO.updateVideoOnJSON(this);
        }
    }

    @Override
    public String toString() {
        return "Video{" +
                "id='" + id + '\'' +
                ", ownerUsername='" + ownerUsername + '\'' +
                '}';
    }
}
