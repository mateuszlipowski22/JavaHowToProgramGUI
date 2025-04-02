package pl.javahowtoprogramgui.section_26.e_24_10.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static pl.javahowtoprogramgui.section_26.e_24_10.server.SocketMessengerConstants.*;

public class DeitelMessengerServer implements MessageListener{

    private ExecutorService serverExecutor;

    public void startServer(){
        serverExecutor = Executors.newCachedThreadPool();

        try{
            ServerSocket serverSocket = new ServerSocket(SERVER_PORT, 100);
            System.out.printf("%s%d%s","Server listening on port ", SERVER_PORT);

            while(true){
                Socket clientSocket = serverSocket.accept();
                serverExecutor.execute(new MessageReceiver(this, clientSocket));
                System.out.println("Connection received from: "+clientSocket.getInetAddress());

            }

        }catch (IOException ioException){
            ioException.printStackTrace();
        }
    }

    @Override
    public void messageReceived(String from, String message) {
        String completeMessage = from + MESSAGE_SEPARATOR + message;
        serverExecutor.execute(new MulticastSender(completeMessage.getBytes()));
    }
}
