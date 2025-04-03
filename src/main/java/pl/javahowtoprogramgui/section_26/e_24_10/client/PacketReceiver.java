package pl.javahowtoprogramgui.section_26.e_24_10.client;

import pl.javahowtoprogramgui.section_26.e_24_10.server.MessageListener;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketTimeoutException;
import java.util.StringTokenizer;

import static pl.javahowtoprogramgui.section_26.e_24_10.server.SocketMessengerConstants.*;

public class PacketReceiver implements Runnable{
    private MessageListener messageListener;
    private MulticastSocket multicastSocket;
    private InetAddress multicastGroup;
    private boolean keepListening = true;

    public PacketReceiver(MessageListener messageListener) {
        this.messageListener = messageListener;
        try{
            multicastSocket = new MulticastSocket(MULTICAST_LISTENING_PORT);
            multicastGroup = InetAddress.getByName(MULTICAST_ADDRESS);
            multicastSocket.joinGroup(multicastGroup);
            multicastSocket.setSoTimeout(5000);
        }catch (IOException ioException){
            ioException.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (keepListening){
            byte[] buffer = new byte[MESSAGE_SIZE];
            DatagramPacket packet = new DatagramPacket(buffer,MESSAGE_SIZE);

            try{
                multicastSocket.receive(packet);
            }catch (SocketTimeoutException socketTimeoutException){
                continue;
            }catch (IOException ioException){
                ioException.printStackTrace();
                break;
            }

            String message = new String(packet.getData());
            message = message.trim();

            StringTokenizer tokenizer = new StringTokenizer(message,MESSAGE_SEPARATOR);

            if(tokenizer.countTokens()==2){
                messageListener.messageReceived(tokenizer.nextToken(),tokenizer.nextToken());
            }
        }

        try{
            multicastSocket.leaveGroup(multicastGroup);
            multicastSocket.close();
        }catch (IOException ioException){
            ioException.printStackTrace();
        }
    }

    public void stopListening(){
        keepListening = false;
    }
}
