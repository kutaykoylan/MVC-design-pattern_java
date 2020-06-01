package Views.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ButtonHelper {

    public static JButton createAButton(String title,Color fontColor,int fontSize,boolean isBorderPainted,Color backgroundColor,int x,int y,int width,int height){

        JButton temp = new JButton(title);
        temp.setForeground(fontColor);
        temp.setFont(new Font("Arial", Font.PLAIN, fontSize));
        temp.setBorderPainted(isBorderPainted);
        temp.setBackground(backgroundColor);
        temp.setOpaque(true);
        if(!(x==0 && y==0 && width==0 && height ==0))
            temp.setBounds(x,y,width,height);

        return temp;
    }
    public static JButton createAButton(String title,Color fontColor,int fontSize,boolean isBorderPainted,Color backgroundColor,int x,int y,int width,int height,ImageIcon ımageIcon){

        JButton temp = new JButton(title);
        temp.setForeground(fontColor);
        temp.setFont(new Font("Arial", Font.PLAIN, fontSize));
        temp.setBorderPainted(isBorderPainted);
        temp.setBackground(backgroundColor);
        temp.setOpaque(true);
        temp.setBounds(x,y,width,height);
        temp.setIcon(ımageIcon);
        return temp;
    }

    /**
     * Remove action listener from specific button
     * @param currentButton is button that want to remove listeners
     */
    public static void clearActionListenerAButton(JButton currentButton){
        for( ActionListener al : currentButton.getActionListeners() ) {
            currentButton.removeActionListener( al );
        }
    }
}
