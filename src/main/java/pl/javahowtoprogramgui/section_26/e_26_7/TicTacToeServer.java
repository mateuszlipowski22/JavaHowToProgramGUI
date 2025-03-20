package pl.javahowtoprogramgui.section_26.e_26_7;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Formatter;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class TicTacToeServer extends JFrame {
    private String[] board = new String[9];
    private JTextArea outputArea;
    private Player[] players;
    private ServerSocket server;

    private int currentPlayer;
    private final static int PLAYER_X = 0;
    private final static int PLAYER_0 = 1;
    private final static String[] MARKS = {"X", "Y"};
    private ExecutorService runGame;
    private Lock gameLock;
    private Condition otherPlayerConnected;
    private Condition otherPlayerTurn;

    public TicTacToeServer() {
        super("Serwer gry kólko i krzyżyk");

        runGame = Executors.newFixedThreadPool(2);
        gameLock = new ReentrantLock();

        otherPlayerConnected = gameLock.newCondition();

        otherPlayerTurn = gameLock.newCondition();

        for (int i = 0; i < 9; i++) {
            board[i] = new String("");
        }

        player = new Player[2];
        currentPlayer = PLAYER_X;

        try {
            server = new ServerSocket(12345, 2);
        } catch (IOException ioException) {
            ioException.printStackTrace();
            System.exit(1);
        }

        outputArea = new JTextArea();

        add(outputArea, BorderLayout.CENTER);
        outputArea.setText("Serwer czeka na połączenia\n");

        setSize(300, 300);
        setVisible(true);
    }


    public void execute() {
        for(int i=0;i<players.length;i++){
            try{
                players[i] = new Player(server.accept(),i);
                runGame.execute(players[i]);
            }catch (IOException ioException){
                ioException.printStackTrace();
                System.exit(1);
            }
        }

        gameLock.lock();

        try{
            players[PLAYER_X].setSuspended(false);
            otherPlayerConnected.signal();
        }finally {
            gameLock.unlock();
        }

    }

    private void displayMessage(final String messageToDisplay){
        SwingUtilities.invokeLater(
                () -> outputArea.append(messageToDisplay)
        );
    }

    public boolean validateAndMove(int location, int player){
        while(player!=currentPlayer){
            gameLock.lock();
            try{
                otherPlayerTurn.await();
            }catch (InterruptedException exception){
                exception.printStackTrace();
            }finally {
                gameLock.unlock();
            }
        }

        if(!isOccupied(location)){
            board[location] = MARKS[currentPlayer];
            currentPlayer = (currentPlayer+1)%2;

            players[currentPlayer].otherPlayerMoved(location);

            gameLock.lock();

            try{
                otherPlayerTurn.signal();
            }finally {
                gameLock.unlock();
            }
            return true;
        }else {
            return false;
        }
    }

    public boolean isOccupied(int location){
        if(board[location].equals(MARKS[PLAYER_X]) || board[location].equals(MARKS[PLAYER_0])){
            return true;
        }else {
            return false;
        }
    }

    public boolean isGameOver(){
        return false;
    }

    private class Player implements Runnable{
        private Socket connection;
        private Scanner input;
        private Formatter output;
        private int playerNumber;
        private String mark;
        private boolean suspended = true;

        public Player(Socket socket, int number){
            playerNumber = number;
            mark = MARKS[playerNumber];
            connection = socket;

            try{
                input = new Scanner(connection.getInputStream());
                output = new Formatter(connection.getOutputStream());
            }catch (IOException ioException){
                ioException.printStackTrace();
                System.exit(1);
            }
        }

        public void otherPlayerMoved(int location){
            output.format("Przeciwnik wykonał rych\n");
            output.format("%d\n",location);
            output.flush();
        }


    }
}
