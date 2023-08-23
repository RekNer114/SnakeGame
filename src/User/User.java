package User;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class User {
    public static String UserName = "Guest";

        //save username
        public static void Save(){
            try {
                FileOutputStream saveData = new FileOutputStream("UserName.ser");
                ObjectOutputStream out = new ObjectOutputStream(saveData);
                out.writeObject(UserName);
                out.close();
                saveData.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

        //load username
        public static void Load(){

            try {
                FileInputStream loadName = new FileInputStream("UserName.ser");
                ObjectInputStream in = new ObjectInputStream(loadName);
                UserName = (String) in.readObject();
                in.close();
                loadName.close();
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }
    public static void changeNickName(JLabel userLabel, Component parentComp){
        UserName = JOptionPane.showInputDialog(parentComp, "Please, enter new UserName");
        if(!UserName.isBlank() && UserName.length()<8){
            User.Save();
            userLabel.setText("Username: " + UserName);
        }else if(UserName.isBlank()){
            JOptionPane.showMessageDialog(parentComp, "Username can't be empty!", "Wrong name!", JOptionPane.ERROR_MESSAGE);
        } else if (UserName.length()>7) {
            JOptionPane.showMessageDialog(parentComp, "Username length should be less than 7", "Wrong name!", JOptionPane.ERROR_MESSAGE);
        }
    }


}
