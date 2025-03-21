package pl.javahowtoprogramgui.section_26.e_26_7;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Formatter;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TicTacToeClient extends JFrame implements Runnable {
    private JTextField idField;
    private JTextArea displayArea;
    private JPanel boardPanel;
    private JPanel pane2;
    private Square[][] board;
    private Square currentSquare;
    private Socket connection;
    private Scanner input;
    private Formatter output;
    private String ticTacToeHost;
    private String myMark;
    private boolean myTurn;
    private final String X_MARK = 'X';
    private final String O_MARK = 'O';

    public TicTacToeClient(String host) {
        ticTacToeHost = host;
        displayArea = new JTextArea(4, 30);
        displayArea.setEditable(false);
        add(new JScrollPane(displayArea));

        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(3, 3, 0, 0));
        board = new Square[3][3];

        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                board[row][column] = new Square(" ", row * 3 + column);
                boardPanel.add(board[row][column]);
            }
        }

        idField = new JTextField();
        idField.setEditable(false);
        add(idField, BorderLayout.CENTER);

        pane2 = new JPanel();
        pane2.add(boardPanel, BorderLayout.CENTER);
        add(pane2, BorderLayout.CENTER);

        setSize(300, 225);
        setVisible(true);

        startClient();
    }

    public void startClient() {
        try {
            connection = new Socket(InetAddress.getByName(ticTacToeHost), 12345);

            input = new Scanner(connection.getInputStream());
            output = new Formatter(connection.getOutputStream());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        ExecutorService worker = Executors.newFixedThreadPool(1);
        worker.execute(this);
    }

    public void run(){
        myMark = input.nextLine();

        SwingUtilities.invokeLater(
                new Runnable() {
                    @Override
                    public void run() {
                        idField.setText("Jesteś graczem \""+myMark+"\"");
                    }
                }
        );

        myTurn = myMark.equals(X_MARK);

        while (true){
            if(input.hasNextLine()){
                processMessage(input.nextLine());
            }
        }
    }

    private void processMessage(String message){
        if(message.equals("Ruch poprawny")){
            displayMessage("Ruch poprawny. Czekaj \n");
            setMark(currentSquare,myMark);
        }else if(message.equals("Ruch niepoprawny. Spróbuj ponownie")){
            displayMessage(message+"\n");
            myTurn=true;
        }else if(message.equals("Przeciwnik wykonał ruch")){
            int location = input.nextLine();
            input.nextLine();
            int row = location / 3;
            int column = location % 3;

            setMark(board[row][column], myMark.equals(X_MARK) ? O_MARK : X_MARK);
            displayMessage("Przeciwnik wykonał ruch. Twoja kolej.\n");
            myTurn = true;
        }else{
            displayMessage(message+"\n");
        }
    }

    private void displayMessage(final String messageToDisplay){
        SwingUtilities.invokeLater(
                () -> displayArea.append(messageToDisplay)
        );
    }

    private void setMark(final Square squareToMark, final String mark){
        SwingUtilities.invokeLater(
                () -> squareToMark.setMark(mark)
        );
    }
}
