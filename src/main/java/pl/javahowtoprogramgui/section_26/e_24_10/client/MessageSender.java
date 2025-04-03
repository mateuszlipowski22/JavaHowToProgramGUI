package pl.javahowtoprogramgui.section_26.e_24_10.client;

import java.io.IOException;
import java.net.Socket;
import java.util.Formatter;

import static pl.javahowtoprogramgui.section_26.e_24_10.server.SocketMessengerConstants.*;

public class MessageSender implements Runnable{
    private Socket clientSocket;
    private String messageToSend;

    public MessageSender(Socket clientSocket,String userName, String messageToSend) {
        this.clientSocket = clientSocket;
        this.messageToSend = userName+MESSAGE_SEPARATOR+messageToSend;
    }

    @Override
    public void run() {
        try{
            Formatter output = new Formatter(clientSocket.getOutputStream());
            output.format("%s\n",messageToSend);
            output.flush();
        }catch (IOException ioException){
            ioException.printStackTrace();
        }
    }
}
