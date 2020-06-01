package DataIO;

import Entities.Comment;
import Entities.Video;

import Utilities.DateFormatHelper;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class VideoIO implements IVideoIO {

    /**
     * Write thw given video as JSON file
     * @param video is video object
     */
    @Override
    public void writeVideoToJSON(Video video){
        List<Video> videoList = readVideosFromJSON();
        for(int i = 0; i < videoList.size(); i++){
            if(videoList.get(i).getId().equals(video.getId())){
                videoList.remove(i);
                break;
            }
        }
        videoList.add(video);
        String jsonFormat = convertVideoArrayToJsonFormat(videoList);
        FileOperation.writeFile(jsonFormat, "videos.json");

    }

    /**
     * Replaces the old video with the modified one
     * @param video is new video
     */
    @Override
    public void updateVideoOnJSON(Video video){
        writeVideoToJSON(video);
    }


    /**
     * Get the videos from json file
     * @return  the list of videos
     */
     @Override
    public List<Video> readVideosFromJSON(){
        JSONParser parser = new JSONParser();
        List<Video> videoList = new ArrayList<>();
        try {
            Object obj = parser.parse(new FileReader("videos.json"));

            // A JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
            JSONArray videos = (JSONArray) obj;


            Iterator<JSONObject> iterator = videos.iterator();
            while (iterator.hasNext()) {
                JSONObject jsonObject = iterator.next();
                List<String> likesList = convertUsernamelistsToArrayList((JSONArray) jsonObject.get("likes"));
                List<String> dislikesList = convertUsernamelistsToArrayList((JSONArray) jsonObject.get("dislikes"));

                List<Comment> comments = convertCommentsFromJSONToObject((JSONArray) jsonObject.get("comments"));
                Video video = new Video((String)jsonObject.get("id"),(String)jsonObject.get("owner"), (String)jsonObject.get("title"), (String)jsonObject.get("content"),
                        (String)jsonObject.get("intendedAudience"), comments, likesList, dislikesList, DateFormatHelper.dateFormatter((String) jsonObject.get("date")));
                videoList.add(video);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return videoList;
    }

    /**
     * Convert JSON format to object
     * @param JSONComments
     * @return
     */
    private List<Comment> convertCommentsFromJSONToObject(JSONArray JSONComments){
        JSONParser parser = new JSONParser();
        List<Comment> comments = new ArrayList<>();
        try {

            Iterator<JSONObject> iterator = JSONComments.iterator();
            while (iterator.hasNext()) {
                JSONObject jsonObject = iterator.next();
                String username = (String)jsonObject.get("user");
                Comment newComment = new Comment(username, (String)jsonObject.get("content"));
                comments.add(newComment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return comments;
    }

    /**
     * Convert the json usename array to arraylist
     * @param usernames is username json array
     * @return username list
     */
    private List<String> convertUsernamelistsToArrayList(JSONArray usernames){
        List<String> users = new ArrayList<>();
        try {

            Iterator<String> iterator = usernames.iterator();
            while (iterator.hasNext()) {
                String username = iterator.next();
                users.add(username);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    /**
     * Convert the given video list to json format
     * @param videoList is converted list
     * @return is json format string
     */
    private String convertVideoArrayToJsonFormat(List<Video> videoList){
        String jsonFormat = "[\n";
        for(int i = 0; i < videoList.size(); i++){
            Video temp = videoList.get(i);
            jsonFormat += "{\n\"id\":" +  "\"" + temp.getId() + "\",\n"
                    + "\"owner\":" + "\""+  temp.getOwnerUsername() + "\",\n"
                    + "\"title\":" + "\""+  temp.getTitle() + "\",\n" +
                    "\"content\": " + "\"" + temp.getContent() + "\",\n" +
                    "\"intendedAudience\": "  + "\"" +temp.getIntendedAudience() + "\",\n" +
                    "\"likes\": " + convertUserListToJSONFormat(temp.getLikes()) + ",\n" +
                    "\"dislikes\": " + convertUserListToJSONFormat(temp.getDislikes()) + ",\n" +
                    "\"date\":" + "\""+  temp.getDate() + "\",\n" +
                    "\"comments\": " + convertCommentToJSONFormat(temp.getComments());

            jsonFormat += "\n}";
            if(i != videoList.size() - 1)
                jsonFormat += ",";
            jsonFormat += "\n";
        }
        jsonFormat += "]";
        return jsonFormat;
    }

    /**
     * Convert a comment to JSON format
     * @param comments
     * @return
     */
    private String convertCommentToJSONFormat(List<Comment> comments){
        String jsonFormat = "[";
        for(int i = 0; i < comments.size(); i++){
            JSONObject obj = new JSONObject();
            obj.put("user", comments.get(i).getOwner());
            obj.put("content", comments.get(i).getContent());
            StringWriter out = new StringWriter();
            try{
                obj.writeJSONString(out);
            }catch (IOException e){
                System.err.println(e);
            }
            jsonFormat += out.toString();
            if(i != comments.size() - 1)
                jsonFormat += ",";
        }
        jsonFormat += "]";
        return jsonFormat;
    }

    /**
     * Convert user list
     * @param usernames is username of users
     */
    private String convertUserListToJSONFormat(List<String> usernames){
        String jsonFormat = "[";
        for(int i = 0; i < usernames.size(); i++){
            jsonFormat += "\"" + usernames.get(i) + "\"";
            if(i != usernames.size() - 1)
                jsonFormat += ",";
        }
        jsonFormat += "]";
        return jsonFormat;
    }
    /*
    private Date dateFormatter(String date) throws ParseException {
        return new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy", Locale.ENGLISH).parse(date);
    }*/

}