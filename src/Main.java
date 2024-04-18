import GameElements.GameFrame;

import User.User;
import Music.Music;

public class Main {
    public static Music music;

    public static void main(String[] args) {

        User.Load();

        music = new Music();
        music.start();
        new GameFrame();


    }

}