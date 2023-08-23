package PausePanel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class PauseButton extends JButton {

    public PauseButton(String text){
        this.setPreferredSize(new Dimension(200, 50));
        this.setForeground(Color.WHITE);
        this.setFont(new Font("Comic Sans MS", Font.PLAIN, 32));
        this.setBorder(new LineBorder(Color.red));
        this.setBackground(Color.BLACK);
        this.setText(text);
        this.setFocusable(false);


    }
}
