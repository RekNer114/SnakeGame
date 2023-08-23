package PausePanel;
import Music.Music;
import ScoreSys.ScoreSystem;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import User.User;



public class PausePanel extends JPanel implements ActionListener {


    //empty panels xD
    private final JPanel  empty_left;
    private final JPanel empty_right;
    private final JPanel empty_down;
    private final JPanel empty_up;

    //button panel
    private final JPanel centerPanel;

//buttons
    public JButton con;
    public JButton restart;
    public JButton leaderboard;
    public  JButton settings;
    public JButton exit;

    //buttons to back from  settings and leaderboard;
    private final JButton backButt;

    //settings buttons
      JCheckBox music;
      public  JLabel UserName;
      public JLabel UserNameHeader;
      public JLabel musicHeader;
      JButton changeSong;
      JSlider changeVolume;
      JLabel volume;

    public JLabel[] scores = new JLabel[10];

    public PausePanel(){

        this.setBounds(0,0, 750, 745);
        this.setBackground(new Color(0, 0, 0, 0));
        this.setPreferredSize(new Dimension(750, 750));
        this.setLayout(new BorderLayout());

//panel sex
        empty_left = new JPanel();
        empty_left.setPreferredSize(new Dimension(200, 200));
        empty_left.setBackground(new Color(0, 0, 0, 100));

        empty_right = new JPanel();
        empty_right.setPreferredSize(new Dimension( 200, 200));
        empty_right.setBackground(new Color(0, 0, 0, 100));

        empty_up = new JPanel();
        empty_up.setPreferredSize(new Dimension(200, 100));
        empty_up.setBackground(new Color(0, 0, 0, 100));

        empty_down = new JPanel();
        empty_down.setPreferredSize(new Dimension(200, 100));
        empty_down.setBackground(new Color(0, 0, 0, 100));

        centerPanel = new JPanel();
        centerPanel.setBackground(Color.BLACK);
        centerPanel.setBorder(new LineBorder(Color.red));
        centerPanel.setLayout(null);


//butt sex lmao))))0)0(0 (for main pause)

        //continue button
        con = new PauseButton("Continue");
        con.setBounds(80, 50, 200, 50);
        //con.addActionListener(this);



        //restart button
        restart = new PauseButton("Restart");
        restart.setBounds(80, 150, 200, 50);
        restart.addActionListener(this);

        //LeaderBoard button
        leaderboard = new PauseButton("Leaderboard");
        leaderboard.setBounds(80, 250, 200, 50);
        leaderboard.addActionListener(this);

        //backButton for settings and leaderboard page
        backButt = new JButton();
        backButt.setBackground(Color.BLACK);
        backButt.setBorder(new LineBorder(Color.red));
        backButt.setForeground(Color.WHITE);
        backButt.setFont(new Font("Comic Sans Ms", Font.PLAIN, 32));
        backButt.setText("Back");
        backButt.setBounds(2,2, 100, 50);
        backButt.addActionListener(this);
        backButt.setVisible(false);



        //settings button
        settings = new PauseButton("Settings");
        settings.setBounds(80, 350, 200, 50);
        settings.addActionListener(this);

        //exit butt
        exit = new PauseButton("Exit");
        exit.setBounds(80, 450, 200, 50);
        exit.addActionListener(this);

//scoreboard
        for(int i = 0; i<scores.length; i++){
            scores[i] = new JLabel();
            scores[i].setVisible(false);
            scores[i].setForeground(Color.WHITE);
            scores[i].setFont(new Font("Comic Sans MS", Font.PLAIN, 32));
            scores[i].setBounds(50, 100+(32*i), 400, 100);
            scores[i].setText(new ScoreSystem().printLeaderBoard(i));
            centerPanel.add(scores[i]);
        }

//Settings "panel"
        //header UserName
        UserNameHeader = new JLabel();
        UserNameHeader.setText("Username");
        UserNameHeader.setForeground(Color.RED);
        UserNameHeader.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
        //трошка насрав
        UserNameHeader.setBounds(80, 100, 300, 39);
        UserNameHeader.setVisible(false);

        //Label where you press mouse to change username
        UserName = new JLabel();
        UserName.setText("Current: " + User.UserName);
        UserName.setForeground(Color.WHITE);
        UserName.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        UserName.setBounds(50, 180, 300, 33);
        UserName.setVisible(false);
        //
        UserName.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
              User.changeNickName(UserName, centerPanel);
            }

            @Override
            public void mouseEntered(MouseEvent e){
                UserName.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });
//

        musicHeader = new JLabel();
        musicHeader.setText("Music");
        musicHeader.setForeground(Color.RED);
        musicHeader.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
        musicHeader.setBounds(125, 230, 300, 39);
        musicHeader.setVisible(false);

        music = new JCheckBox("On");
        music.setSelected(true);
        music.setIcon(new ImageIcon("Sprites/apple/RedApple.png"));
        music.setSelectedIcon(new ImageIcon("Sprites/apple/Apple.png"));
        music.setFocusable(false);
        music.setBounds(50, 310, 100, 35);
        music.setForeground(Color.WHITE);
        music.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        music.setBackground(Color.BLACK);
        music.setVisible(false);
        music.addActionListener((e) ->{
            if(music.isSelected()){
                music.setText("On");
                Music.MusicStart();

            }else{
                music.setText("Off");
                Music.MusicStop();
            }

        } );
            changeSong = new JButton("Choose song");
            changeSong.setFocusable(false);
            changeSong.setToolTipText("* - Sorry, you can choose only wav files");
            changeSong.setForeground(Color.WHITE);
            changeSong.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
            changeSong.setBackground(Color.BLACK);
            changeSong.setBorder(new LineBorder(Color.RED, 2));
            changeSong.setBounds(165, 310, 150, 50 );
            changeSong.addActionListener((e) ->{
                JFileChooser chooser = new JFileChooser();
                chooser.showOpenDialog(centerPanel);
                System.out.println(chooser.getSelectedFile().getPath());
                Music.changeSong(chooser.getSelectedFile().getPath());
            });
            changeSong.setVisible(false);


            changeVolume = new JSlider(-40, 6);
            changeVolume.setVisible(false);
            changeVolume.setBackground(Color.BLACK);
            changeVolume.setValue((int) Music.getVolume());
            changeVolume.setBounds(165, 390, 150, 50);
            changeVolume.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Music.VolumeChange(changeVolume);
                System.out.println(changeVolume.getValue());
            }
        });
            volume = new JLabel("Volume");
            volume.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
            volume.setForeground(Color.WHITE);
            volume.setBounds(50, 390, 200, 35);
            volume.setVisible(false);

        centerPanel.add(con);
        centerPanel.add(restart);
        centerPanel.add(leaderboard);
        centerPanel.add(settings);
        centerPanel.add(exit);
        centerPanel.add(backButt);
        centerPanel.add(UserName);
        centerPanel.add(UserNameHeader);
        centerPanel.add(music);
        centerPanel.add(musicHeader);
        centerPanel.add(changeSong);
        centerPanel.add(changeVolume);
        centerPanel.add(volume);

        this.add(empty_left, BorderLayout.EAST);
        this.add(empty_right, BorderLayout.WEST);
        this.add(empty_up, BorderLayout.NORTH);
        this.add(empty_down, BorderLayout.SOUTH);
        this.add(centerPanel);

    }

    //hide buttons in main pause panel
    public void HideMainButtons(){
        con.setVisible(false);
        restart.setVisible(false);
        leaderboard.setVisible(false);
        settings.setVisible(false);
        exit.setVisible(false);
        backButt.setVisible(true);

    }


    //when user press leaderboard button in pause menu
    public void leaderBoard(){
        HideMainButtons();
        for (JLabel score : scores) {
            score.setVisible(true);
        }

    }


    //same as leaderboard
    public void Settings(){
        HideMainButtons();
        UserName.setVisible(true);
        UserNameHeader.setVisible(true);
        music.setVisible(true);
        musicHeader.setVisible(true);
        changeSong.setVisible(true);
        changeVolume.setVisible(true);
        volume.setVisible(true);


    }

    public void updateLeaderBoard(){
        for(int i = 0; i<scores.length; i++){
            scores[i].setText(new ScoreSystem().printLeaderBoard(i));
        }

    }

    //clear center panel when we press esc button
    public void clear(){
        backButt.setVisible(false);
        con.setVisible(true);
        restart.setVisible(true);
        leaderboard.setVisible(true);
        settings.setVisible(true);
        exit.setVisible(true);
        UserName.setVisible(false);
        UserNameHeader.setVisible(false);
        music.setVisible(false);
        changeSong.setVisible(false);
        musicHeader.setVisible(false);
        changeVolume.setVisible(false);
        volume.setVisible(false);
        for (JLabel score : scores) {
            score.setVisible(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backButt){
            clear();
        }
    }
}
