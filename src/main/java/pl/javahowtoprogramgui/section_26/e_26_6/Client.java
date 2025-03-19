package pl.javahowtoprogramgui.section_26.e_26_6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.*;

public class Client extends JFrame {
    private JTextField enterField;
    private JTextArea displayArea;
    private DatagramSocket socket;

    public Client() {
        super("Klient");

        enterField = new JTextField("Tu wpisz wiadomość");
        enterField.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try{
                            String message = e.getActionCommand();
                            displayArea.append("\nWysyłanie pakietu zawierającego: "+message+"\n");

                            byte[] data = message.getBytes();
                            DatagramPacket sendPacket = new DatagramPacket(data, data.length, InetAddress.getLocalHost(),5000);
                            socket.send(sendPacket);
                            displayArea.append("Pakiet wysłany\n");
                            displayArea.setCaretPosition(displayArea.getText().length());
                        } catch (IOException ex) {
                            displayMessage(ex +"\n");
                            ex.printStackTrace();
                        }
                    }
                }
        );

        add(enterField, BorderLayout.NORTH);

        displayArea = new JTextArea();
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        setSize(400,30);
        setVisible(true);

        try{
            socket = new DatagramSocket();
        }catch (SocketException socketException){
            socketException.printStackTrace();
            System.exit(1);
        }
    }

    public void waitForPackets(){
        while (true){
            try{
                byte[] data = new byte[100];
                DatagramPacket receiverPacket = new DatagramPacket(data, data.length);
                socket.receive(receiverPacket);

                displayMessage(String.format("\nOtrzymano pakiet:\nZ hosta : %s \nPort hosta : %s \nDługość : %s \nZawartośc : \n\t%s"
                        ,receiverPacket.getAddress(),
                        receiverPacket.getPort(),
                        receiverPacket.getLength(),
                        new String(receiverPacket.getData(),0, receiverPacket.getLength())));

            }catch (IOException ioException){
                displayMessage(ioException+"\n");
                ioException.printStackTrace();
            }
        }
    }

    private void displayMessage(final String messageToDisplay){
        SwingUtilities.invokeLater(
                () -> displayArea.append(messageToDisplay)
        );
    }
}
