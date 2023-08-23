package GameElements;

import javax.swing.*;

public class GameFrame extends JFrame {

    public GameFrame(){
        this.add(new GamePanel(this));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }



}
