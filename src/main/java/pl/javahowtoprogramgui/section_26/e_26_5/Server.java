package pl.javahowtoprogramgui.section_26.e_26_5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends JFrame {

    private JTextField enterField;
    private JTextArea displayArea;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private ServerSocket server;
    private Socket connection;

    private int counter = 1;

    public Server(){
        super("Serwer");

        enterField = new JTextField();
        enterField.setEditable(false);
        enterField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendData(e.getActionCommand());
                enterField.setText("");
            }
        });

        add(enterField, BorderLayout.NORTH);

        displayArea = new JTextArea();
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        setSize(300,150);
        setVisible(true);

    }

    public void runServer(){
        try{
            server = new ServerSocket(12345, 100);

            while (true){
                try{
                    waitForConnection();
                    getStreams();
                    processConnection();
                }catch (EOFException eofException){
                    displayMessage("\nPołączenie zakończone przez serwer");
                }finally {
                    closeConnection();
                    ++counter;
                }
            }
        }catch (IOException ioException){
            ioException.printStackTrace();
        }
    }

    private void waitForConnection() throws IOException {
        displayMessage("Oczekiwanie na połączenie\n");
        connection = server.accept();
        displayMessage("Połączenie numer "+counter+" odebrane od: "+connection.getInetAddress().getHostAddress());
    }

    private void getStreams() throws IOException {
        output = new ObjectOutputStream(connection.getOutputStream());
        output.flush();

        input = new ObjectInputStream(connection.getInputStream());
        displayMessage("\nOtrzymano strumienie wejĂciowy i wyjĂciowy\n");
    }

    private void processConnection() throws IOException {
        String message = "Połączenie udane";
        sendData(message);

        setTextFieldEditable(true);

        do {
            try{
                message = (String) input.readObject();
                displayMessage("\n"+message);

            }catch (ClassNotFoundException classNotFoundException){
                displayMessage("\nOtrzymano nieznany typ obiektu");
            }
        }while (!message.equals("KLIENT>>>PRZERWIJ"));
    }

    private void closeConnection(){
        displayMessage("\nKończenie połączenie\n");
        setTextFieldEditable(false);

        try{
            output.close();
            input.close();
            connection.close();
        }catch (IOException ioException){
            ioException.printStackTrace();
        }
    }

    private void sendData(String message){
        try{
            output.writeObject("SERWER>>> "+message);
            output.flush();
            displayMessage("\nSERWER>>> "+message);
        }catch (IOException ioException){
            displayArea.append("\nBłąd zapisu obiektu");
        }
    }

    private void displayMessage(final String messageToDisplay){
        SwingUtilities.invokeLater(
                new Runnable() {
                    @Override
                    public void run() {
                        displayArea.append(messageToDisplay);
                    }
                }
        );
    }

    private void setTextFieldEditable(final boolean editable){
        SwingUtilities.invokeLater(
                new Runnable() {
                    @Override
                    public void run() {
                        enterField.setEditable(editable);
                    }
                }
        );
    }
}
