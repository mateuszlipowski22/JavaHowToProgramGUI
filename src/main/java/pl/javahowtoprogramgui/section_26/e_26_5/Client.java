package pl.javahowtoprogramgui.section_26.e_26_5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends JFrame {
    private JTextField enterField;
    private JTextArea displayArea;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private String message = "";
    private String chatServer;
    private Socket client;

    public Client(String host) {
        super("Klient");

        chatServer = host;

        enterField = new JTextField();
        enterField.setEditable(false);
        enterField.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        sendData(e.getActionCommand());
                        enterField.setText("");
                    }
                }
        );

        add(enterField, BorderLayout.NORTH);

        displayArea = new JTextArea();
        add(displayArea, BorderLayout.CENTER);

        setSize(300,150);
        setVisible(true);
    }

    public void runClient(){
        try{
            connectToServer();
            getStreams();
            processConnection();
        }catch (EOFException eofException){
            displayMessage("\nPołączenie zakończone przez klienta");
        }catch (IOException ioException){
            ioException.printStackTrace();
        }finally {
            closeConnection();
        }
    }

    private void connectToServer() throws IOException {
        displayMessage("Próba połączenia\n");
        client = new Socket(InetAddress.getByName(chatServer),12345);
        displayMessage("Połączono z: "+ client.getInetAddress().getHostAddress());
    }

    private void getStreams() throws IOException {
        output = new ObjectOutputStream(client.getOutputStream());
        output.flush();

        input = new ObjectInputStream(client.getInputStream());
        displayMessage("\nOtrzymano strumienie wejĂciowy i wyjĂciowy\n");
    }

    private void processConnection() throws IOException {
        setTextFieldEditable(true);
        do {
            try{
                message = (String) input.readObject();
                displayMessage("\n"+message);

            }catch (ClassNotFoundException classNotFoundException){
                displayMessage("\nOtrzymano nieznany typ obiektu");
            }
        }while (!message.equals("SERWER>>>PRZERWIJ"));
    }

    private void closeConnection(){
        displayMessage("\nKończenie połączenie\n");
        setTextFieldEditable(false);

        try{
            output.close();
            input.close();
            client.close();
        }catch (IOException ioException){
            ioException.printStackTrace();
        }
    }

    private void sendData(String message){
        try{
            output.writeObject("KLIENT>>> "+message);
            output.flush();
            displayMessage("\nKLIENT>>> "+message);
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
