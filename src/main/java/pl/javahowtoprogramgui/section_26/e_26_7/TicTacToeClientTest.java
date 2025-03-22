package pl.javahowtoprogramgui.section_26.e_26_7;

import javax.swing.*;

public class TicTacToeClientTest {
    public static void main(String[] args) {
        TicTacToeClient application;
        if(args.length==0){
            application = new TicTacToeClient("127.0.0.1");
        }else {
            application = new TicTacToeClient(args[0]);
        }

        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
