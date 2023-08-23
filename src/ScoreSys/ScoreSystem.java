package ScoreSys;

import User.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ScoreSystem {

    File scoreBoard;
    private final String[] userNames = new String[10];
    private final int[] scores = new int[10];

    Scanner scan;
    FileWriter writeScores;

    public ScoreSystem() {
        scoreBoard = new File("leaderboard.txt");
        fillArrays();

    }

    private void fillArrays() {

        try {
            scan = new Scanner(scoreBoard);
            int i = 0;

            while (scan.hasNext()) {
                userNames[i] = scan.next();
                scores[i] = Integer.parseInt(scan.next());

                i++;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public ScoreSystem(int score) {

        //it would be start name
        //******************************************************************************
        scoreBoard = new File("leaderboard.txt");
        calculateScore(score, User.UserName);

    }

    private void calculateScore(int score, String userName) {
        try {
            scan = new Scanner(scoreBoard);
            int i = 0;

            while (scan.hasNext()) {
                userNames[i] = scan.next();
                scores[i] = Integer.parseInt(scan.next());

                if (i == 9 && scores[i] < score) {
                    scores[i] = score;
                    userNames[i] = userName;
                    sort(scores, userNames);
                }
                i++;
            }




            writeScores = new FileWriter(scoreBoard);
            for (int j = 0; j<scores.length; j++) {
                writeScores.write(userNames[j] + " " + scores[j] + "\n");
            }

            writeScores.close();


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public String printLeaderBoard(int index) {
        String str = userNames[index] + " ------ " + scores[index];
        System.out.println();
        return str;
    }



    private static void sort(int scores[], String[] userNames){
        for(int j = scores.length-1; j>0; j--){
            if(scores[j]>scores[j-1]){
            int temp = scores[j-1];
            scores[j-1] = scores[j];
            scores[j] = temp;
            String strTemp = userNames[j-1];
            userNames[j-1] = userNames[j];
            userNames[j] = strTemp;
            }else{break;}
        }
    }

}