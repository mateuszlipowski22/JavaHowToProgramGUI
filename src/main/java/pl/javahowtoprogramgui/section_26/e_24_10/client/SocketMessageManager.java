package pl.javahowtoprogramgui.section_26.e_24_10.client;

import pl.javahowtoprogramgui.section_26.e_24_10.server.MessageListener;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static pl.javahowtoprogramgui.section_26.e_24_10.server.SocketMessengerConstants.*;

public class SocketMessageManager implements MessageManager{
    private Socket clientSocket;
    private String serverAddress;
    private PacketReceiver receiver;
    private boolean connected = false;
    private ExecutorService serverExecutor;

    public SocketMessageManager(String serverAddress) {
        this.serverAddress = serverAddress;
        serverExecutor = Executors.newCachedThreadPool();
    }

    @Override
    public void connect(MessageListener listener) {
        if(connected){
            return;
        }

        try{
            clientSocket = new Socket(InetAddress.getByName(serverAddress),SERVER_PORT);
            receiver = new PacketReceiver(listener);
            serverExecutor.execute(receiver);
            connected = true;
        }catch (IOException ioException){
            ioException.printStackTrace();
        }
    }

    @Override
    public void disconnect(MessageListener listener) {
        if(!connected){
            return;
        }

        try{
            Runnable disconnecter = new MessageSender(clientSocket,"",DISCONECT_STRING);
            Future disconnecting = serverExecutor.submit(disconnecter);
            disconnecting.get();
            receiver.stopListening();
            clientSocket.close();
        }catch (ExecutionException executionException){
            executionException.printStackTrace();
        }catch (InterruptedException interruptedException){
            interruptedException.printStackTrace();
        }catch (IOException ioException){
            ioException.printStackTrace();
        }
        connected = false;
    }

    @Override
    public void sendMessage(String from, String message) {
        if(!connected){
            return;
        }
        serverExecutor.execute(new MessageSender(clientSocket,from,message));
    }
}
