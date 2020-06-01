package Entities;

import java.util.Observable;
;
public class Comment{

    private String  content;
    private String owner;

    public Comment() {}

    public Comment(Comment comment){
        this.owner = comment.owner;
        this.content = comment.getContent();
    }

    public Comment(String owner, String content) {
        setOwner(owner);
        setContent(content);
    }


    public String getContent() {
        return content;
    }

    public String getOwner() {
        return owner;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
