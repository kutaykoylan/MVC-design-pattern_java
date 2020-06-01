package Views.components;

import javax.swing.*;
import java.awt.*;

public class FrameHelper {

    public static JFrame createAFrame(int x,int y,int width,int height){
        JFrame temp = new JFrame();
        temp.setTitle("IztechTube");
        temp.setIconImage(Toolkit.getDefaultToolkit().getImage("icon.png"));
        temp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        temp.setBounds(x, y, width, height);

        return temp;
    }

}
