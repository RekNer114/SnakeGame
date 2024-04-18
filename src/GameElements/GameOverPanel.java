package GameElements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GameOverPanel extends JPanel implements ActionListener {

    Timer timer;


    int x = 0;
    int y = 32;
    int xVelocity = 2;
    int yVelocity =2 ;
    Random rand;
    int strWidth;

    int red = 255;
    int green = 0;
    int blue = 0;



    GameOverPanel(){
        rand = new Random();
        this.setBackground(new Color(0,0,0));
        this.setPreferredSize(new Dimension(750, 200));
        timer = new Timer(10, this);
        timer.start();
        FontMetrics met = getFontMetrics(new Font("Comic Sans MS", Font.ITALIC, 32));
        strWidth = met.stringWidth("Press SPACE to restart");
    }

    public void checkVisibility(boolean visible){
        if(!visible){
            timer.stop();

        }else{
            timer.start();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(new Color(red, green, blue));
        g.setFont(new Font("Comic Sans MS", Font.ITALIC, 32));
        g.drawString("Press SPACE to restart", x, y);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        red = rand.nextInt(255);
        green = rand.nextInt(255);
        blue = rand.nextInt(255);

        //текст короче літає як заставка дівіді))))))

        if(x >= this.getWidth() - strWidth || x<0 ){
            xVelocity *= -1;

        }
        x = x+xVelocity;

        if(y >= this.getHeight() - 32 || y<32){
            yVelocity *= -1;

        }
            y = y + yVelocity;

        repaint();
    }
}
