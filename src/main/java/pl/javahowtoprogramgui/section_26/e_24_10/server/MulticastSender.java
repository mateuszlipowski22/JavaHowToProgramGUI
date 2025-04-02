package pl.javahowtoprogramgui.section_26.e_24_10.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import static pl.javahowtoprogramgui.section_26.e_24_10.server.SocketMessengerConstants.*;

public class MulticastSender implements Runnable{
    private byte[] messageBytes;

    public MulticastSender(byte[] bytes) {
        this.messageBytes = bytes;
    }

    @Override
    public void run() {
        try{
            DatagramSocket socket = new DatagramSocket(MULTICAST_SENDING_PORT);
            InetAddress group = InetAddress.getByName(MULTICAST_ADDRESS);
            DatagramPacket packet = new DatagramPacket(messageBytes, messageBytes.length, group, MULTICAST_LISTENING_PORT);
            socket.send(packet);
            socket.close();
        }catch (IOException ioException){
            ioException.printStackTrace();
        }
    }
}
