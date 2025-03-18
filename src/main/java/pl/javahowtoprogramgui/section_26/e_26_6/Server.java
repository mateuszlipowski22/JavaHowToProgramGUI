package pl.javahowtoprogramgui.section_26.e_26_6;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Server extends JFrame {
    private JTextArea displayArea;
    private DatagramSocket socket;

    public Server() {
        super("Server");

        displayArea = new JTextArea();
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        setSize(400,30);
        setVisible(true);

        try{
            socket = new DatagramSocket(5000);
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

    private void sendPackageToClient(DatagramPacket receiverPacket) throws IOException {
        displayMessage("\n\nWysyłanie kopii danych do klienta...");
        DatagramPacket sendPacket = new DatagramPacket(receiverPacket.getData(),
                receiverPacket.getLength(),
                receiverPacket.getAddress(),receiverPacket.getPort());

        socket.send(sendPacket);
        displayMessage("\nPakiet wysłany");

    }

    private void displayMessage(final String messageToDisplay){
        SwingUtilities.invokeLater(
                () -> displayArea.append(messageToDisplay)
        );
    }
}
