package GameElements;

import javax.swing.*;

public class GameFrame extends JFrame {

    //Frame constructor
    public GameFrame(){
        this.add(GamePanel.getInstance(this));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }



}
