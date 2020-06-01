package DataIO;

import Entities.User;
import Entities.Video;
import Utilities.UserFinderHelper;
import Utilities.VideoFinderHelper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserIO implements IUserIO {

    @Override
    public List<User> readUsersFromXml(){
        try
        {
            Map<String,List<String>> userAndSubscribers = new HashMap<>();
            Map<String,List<String>> userAndSubcriptions = new HashMap<>();

            List<User> users = new ArrayList<User>();
            File file = new File("users.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("user");

            for (int itr = 0; itr < nodeList.getLength(); itr++)
            {
                Node node = nodeList.item(itr);
                if (node.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element userElement = (Element) node;
                    User tempUser = new User();

                    String username = userElement.getElementsByTagName("username").item(0).getTextContent();
                    tempUser.setUserName(username);

                    String password = userElement.getElementsByTagName("password").item(0).getTextContent();
                    tempUser.setPassword(password);

                    tempUser.setSubscribers(new ArrayList<User>());

                    tempUser.setSubscription(new ArrayList<User>());


                    List<String> userSubscribers = new ArrayList<>();
                    NodeList subscribers = userElement.getElementsByTagName("subscribers").item(0).getChildNodes();
                    if(subscribers.getLength() != 0){
                        for (int i = 0; i < subscribers.getLength(); i++) {
                            String subscriberx = subscribers.item(i).getTextContent();
                            userSubscribers.add(subscriberx);
                        }
                        userAndSubscribers.put(username,userSubscribers);
                    }

                    List<String> userSubscriptions = new ArrayList<>();


                    NodeList subscriptions = userElement.getElementsByTagName("subscriptions").item(0).getChildNodes();
                    if(subscriptions.getLength() != 0){
                        for (int i = 0; i < subscriptions.getLength(); i++) {
                            String subscriberx = subscriptions.item(i).getTextContent();

                            userSubscriptions.add(subscriberx);
                        }
                        userAndSubcriptions.put(username,userSubscriptions);
                    }



                    List<List<Video>> userWatchLists = new ArrayList<>();
                    NodeList watchListsNodes = userElement.getElementsByTagName("watchLists").item(0).getChildNodes();
                    Element watchListsElement = (Element) watchListsNodes;
                   // System.out.println("watch lists node list length : " + watchListsNodes.getLength());


                        for (int i = 0; i < watchListsNodes.getLength(); i++) {
                            NodeList watchListNodeList = watchListsElement.getElementsByTagName("watchList").item(i).getChildNodes();
                            Element watchListNodeListElement = (Element) watchListNodeList;
                            //System.out.println("watch list node list length : " + watchListNodeList.getLength());

                            List<Video> watchList = new ArrayList<>();

                            NodeList videosListNodes = watchListNodeListElement.getElementsByTagName("videos").item(0).getChildNodes();
                            Element videoListElement = (Element) videosListNodes;
                            for (int j = 0; j < videosListNodes.getLength(); j++) {
                                NodeList videoList = videoListElement.getElementsByTagName("video");
                                String video_id = videoList.item(j).getTextContent();
                                watchList.add(findVideoByID(video_id));
                            }



                            if(watchList.size() != 0){
                                userWatchLists.add(watchList);
                            }
                        }
                        userAndSubcriptions.put(username,userSubscriptions);

                    tempUser.setWatchLists(userWatchLists);



                    users.add(tempUser);
                }


            }

            // add subscribers to user
            for (User currentUser: users) {
                List<String> subscribers = userAndSubscribers.get(currentUser.getUserName());
                if(subscribers != null){
                    for (String subscriber: subscribers) {
                        User currentSubscriber = UserFinderHelper.findUserByUsername(users,subscriber);
                        if (currentSubscriber != null) {
                            currentUser.getSubscribers().add(currentSubscriber);
                        }
                    }
                }
            }

            // add subscriptions to user
            for (User currentUser: users) {
                List<String> subscriptions = userAndSubcriptions.get(currentUser.getUserName());
                if(subscriptions != null) {
                    for (String subscription : subscriptions) {
                        User currentSubscription = UserFinderHelper.findUserByUsername(users, subscription);
                        if (currentSubscription != null) {
                            currentUser.getSubscription().add(currentSubscription);
                        }
                    }
                }
            }
            return users;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void writeUsersToXml(List<User> users) {

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("users");
            doc.appendChild(rootElement);


            for (User user : users) {
                Element staff = doc.createElement("user");
                rootElement.appendChild(staff);
                Element username = doc.createElement("username");
                username.appendChild(doc.createTextNode(user.getUserName()));
                staff.appendChild(username);

                Element password = doc.createElement("password");
                password.appendChild(doc.createTextNode(user.getPassword()));
                staff.appendChild(password);

                // subscripton part

                Element subscribers = doc.createElement("subscribers");
                staff.appendChild(subscribers);
                for (User currentUser : user.getSubscribers()) {
                    Element temp = doc.createElement("subscriber");
                    temp.appendChild(doc.createTextNode(currentUser.getUserName()));
                    staff.appendChild(temp);
                    subscribers.appendChild(temp);
                }

                // subscriptions

                Element subscriptions = doc.createElement("subscriptions");
                staff.appendChild(subscriptions);
                for (User currentUser : user.getSubscription()) {
                    Element temp = doc.createElement("subscription");
                    temp.appendChild(doc.createTextNode(currentUser.getUserName()));
                    staff.appendChild(temp);
                    subscriptions.appendChild(temp);
                }

                // watchlist part

                Element watchLists = doc.createElement("watchLists");
                staff.appendChild(watchLists);

                for (List<Video> currentWatchList : user.getWatchLists()) {
                    Element tempWatchList = doc.createElement("watchList");


                    Element watchlistID = doc.createElement("id");
                    watchlistID.appendChild(doc.createTextNode("" + user.getWatchLists().indexOf(currentWatchList)));
                    tempWatchList.appendChild(watchlistID);


                    Element tempVideos = doc.createElement("videos");
                    tempWatchList.appendChild(tempVideos);
                    for (Video currentVideo : currentWatchList) {
                        Element tempVideo = doc.createElement("video");
                        tempVideo.appendChild(doc.createTextNode(currentVideo.getId()));
                        tempVideos.appendChild(tempVideo);
                    }
                    staff.appendChild(tempWatchList);
                    watchLists.appendChild(tempWatchList);
                }


            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            StreamResult result = new StreamResult(new File("users.xml"));

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);

            DOMSource source = new DOMSource(doc);
            transformer.transform(source, result);


        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }

    @Override
    public Video findVideoByID(String id){
        VideoIO videoIO = new VideoIO();
        List<Video> videos = videoIO.readVideosFromJSON();
        return VideoFinderHelper.findVideoByID(id,videos);
        /*List<Video> videos = videoIO.readVideosFromJSON();
        for (Video video: videos) {
            if(video.getId().equals(id)){
                return video;
            }
        }
        return null;*/
    }

}