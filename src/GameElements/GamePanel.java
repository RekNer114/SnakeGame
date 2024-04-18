package GameElements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Random;


import PausePanel.*;
import ScoreSys.ScoreSystem;


public class GamePanel extends JPanel implements ActionListener {
    private static GamePanel instance;

    private final int SCREEN_WIDTH = 750;
    private final int SCREEN_HEIGHT = 750;

    private final int UNIT_SIZE = 30;
    private final int GAME_UNITS = (SCREEN_HEIGHT * SCREEN_WIDTH) / UNIT_SIZE;

    private int DELAY = 75;


    final int[] x = new int[GAME_UNITS];
    final int[] y = new int[GAME_UNITS];

    private int score = 0;
    private int bodyParts = 4;
    private int appleX;
    private int appleY;
    private char dir = 'R';
    private boolean running = false;
    private boolean isPaused = false;

    private Timer time;
    private Random rand;
    private final GameOverPanel ovPan;
   private final PausePanel pausePanel;

   private final JFrame WorkingFrame;





    //constructor
    private GamePanel(JFrame WorkingFrame) {
        this.WorkingFrame = WorkingFrame;
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setLayout(null);
        this.addKeyListener(new KeyAdapter());

        pausePanel = new PausePanel();
        pausePanel.setVisible(false);
        this.add(pausePanel);

        //panel when game over()
        ovPan = new GameOverPanel();
        ovPan.setBounds(0, 400, 750, 350);
        ovPan.setVisible(false);
        ovPan.checkVisibility(ovPan.isVisible());
        this.add(ovPan);

        //add ActionListener for buttons from pause panel
        pausePanel.con.addActionListener(this);
        pausePanel.restart.addActionListener(this);
        pausePanel.leaderboard.addActionListener(this);
        pausePanel.settings.addActionListener(this);
        pausePanel.exit.addActionListener(this);

        GameStart();
    }

    private void GameStart() {
        running = true;
        newApple();
        time = new Timer(DELAY, this);
        time.start();
        ovPan.setVisible(false);
        ovPan.checkVisibility(ovPan.isVisible());



    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (running) {
            //draw apple
            g.drawImage(new ImageIcon("Sprites/apple/Apple.png").getImage(), appleX, appleY, null);

            //grid for make it easier
            for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
                g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
                g.drawLine(0, i * UNIT_SIZE, SCREEN_HEIGHT, i * UNIT_SIZE);
            }

            //draw snake body
            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    switch (dir){
                        case 'R':
                            g.drawImage(new ImageIcon("Sprites/snake/snakeHeadRIGHT.png").getImage(),
                                    x[i], y[i], null);
                            break;

                        case 'L':
                            g.drawImage(new ImageIcon("Sprites/snake/snakeHeadLEFT.png").getImage(),
                                    x[i], y[i], null);
                            break;
                        case 'U':
                            g.drawImage(new ImageIcon("Sprites/snake/snakeHeadUP.png").getImage(),
                                    x[i], y[i], null);
                            break;
                        case 'D':
                            g.drawImage(new ImageIcon("Sprites/snake/snakeHeadDOWN.png").getImage(),
                                    x[i], y[i], null);
                            break;
                    }

                } else {
                    g.setColor(new Color(132, 65, 184));
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }
            g.setColor(Color.RED);
            g.setFont(new Font("Comic Sans MS", Font.PLAIN, 32));
            FontMetrics met = getFontMetrics(g.getFont());
            g.drawString("Score: " + score, SCREEN_WIDTH - met.stringWidth("Score: " + score), g.getFont().getSize());

        }else{
            DrawGameOver(g);
        }
    }



    private void newApple() {

        rand = new Random();
        appleX = rand.nextInt((SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        appleY = rand.nextInt((SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
        //snake coordinates have to be different from snake coordinates
        for (int j=0; j<bodyParts; j++) {
            for (int i = 0; i<bodyParts; i++) {
                if (y[i] == appleX && x[i] == appleY) {
                    //System.out.println("PENIS");
                    newApple();
                }
            }
        }

    }


    public void move() {
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];

        }
        switch (dir) {
            case 'U':
                //snakeHead = new ImageIcon("snakeParts/snakeHeadUP.png").getImage();
                y[0] = y[0] - UNIT_SIZE;
                break;

            case 'D':
                //snakeHead = new ImageIcon("snakeParts/snakeHeadDOWN.png").getImage();

                y[0] = y[0] + UNIT_SIZE;
                break;

            case 'L':
                //snakeHead = new ImageIcon("snakeParts/snakeHeadLEFT.png").getImage();
                x[0] = x[0] - UNIT_SIZE;
                break;

            case 'R':
                //snakeHead = new ImageIcon("snakeParts/snakeHeadRIGHT.png").getImage();
                x[0] = x[0] + UNIT_SIZE;
                break;

        }


    }

    private void checkCollision() {
        if (x[0] == SCREEN_WIDTH || SCREEN_HEIGHT == y[0] || x[0]<0 || y[0] < 0) {
            GameOver();
        }
        for (int i = bodyParts; i > 0; i--) {
            if ((x[0] == x[i]) && (y[0] == y[i])) {
                GameOver();
            }
        }
    }


    private void GameOver() {
        running = false;
        isPaused = false;
        time.stop();
        new ScoreSystem(score);
        pausePanel.updateLeaderBoard();

    }
    private void DrawGameOver(Graphics g) {

        g.setColor(Color.RED);
        g.setFont(new Font("Comic Sans MS", Font.PLAIN, 90));
        FontMetrics met = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_WIDTH - met.stringWidth("Game Over")) / 2,
                (SCREEN_HEIGHT / 2));

        ovPan.setVisible(true);
        ovPan.checkVisibility(ovPan.isVisible());

    }


    //LOGIC
    //check if snake eat apple
    private void checkApple() {
        if (x[0] == appleX && y[0] == appleY) {
            score++;
            bodyParts++;
            newApple();
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == pausePanel.con){
            time.start();
            isPaused =false;
            running = true;
            pausePanel.setVisible(false);
        }
        if(e.getSource() == pausePanel.restart){
            pausePanel.setVisible(false);
            isPaused = false;
            GameRestart();
        }


        if(e.getSource() == pausePanel.leaderboard){
                pausePanel.leaderBoard();
        }
        if(e.getSource() == pausePanel.settings){
            pausePanel.Settings();
        }
        if(e.getSource()==pausePanel.exit){
            WorkingFrame.dispose();
        }
        if (running && !isPaused) {
            move();
            checkCollision();
            checkApple();
        }
        repaint();
    }


    public void GameRestart(){
        bodyParts = 4;
        score = 0;
        Arrays.fill(x, 0);
        Arrays.fill(y, 0);
        dir = 'R';
        GameStart();
    }

    public static GamePanel getInstance(JFrame WorkingFrame)
    {
        if (instance == null) {
            instance = new GamePanel(WorkingFrame);
        }
        return instance;
    }
    public class KeyAdapter extends java.awt.event.KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
                if(!isPaused && running){
                    pausePanel.setVisible(true);
                    isPaused = true;
                    //running = false;
                    time.stop();
                }else if (isPaused && running){
                    pausePanel.clear();
                    pausePanel.setVisible(false);
                    isPaused = false;
                    time.start();

                }
            }
            if (!running) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    ovPan.setVisible(false);
                    GameRestart();
                }
            } else {
                switch (e.getKeyCode()) {
                    //wasd control
                    case KeyEvent.VK_W:
                        if (dir != 'D') {
                            dir = 'U';
                        }
                        break;
                    case KeyEvent.VK_S:
                        if (dir != 'U') {
                            dir = 'D';
                        }
                        break;
                    case KeyEvent.VK_D:
                        if (dir != 'L') {
                            dir = 'R';
                        }
                        break;
                    case KeyEvent.VK_A:
                        if (dir != 'R') {
                            dir = 'L';
                        }
                        break;
                }

                //arrows control
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        if (dir != 'D') {
                            dir = 'U';
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        if (dir != 'U') {
                            dir = 'D';
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (dir != 'L') {
                            dir = 'R';
                        }
                        break;
                    case KeyEvent.VK_LEFT:
                        if (dir != 'R') {
                            dir = 'L';
                        }
                        break;
                }
            }
        }
    }



}
