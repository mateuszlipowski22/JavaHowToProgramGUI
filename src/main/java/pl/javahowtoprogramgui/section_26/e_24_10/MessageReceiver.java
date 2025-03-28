package pl.javahowtoprogramgui.section_26.e_24_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Objects;
import java.util.StringTokenizer;

import static pl.javahowtoprogramgui.section_26.e_24_10.SocketMessengerConstants.*;

public class MessageReceiver implements Runnable{
    private BufferedReader input;
    private MessageListener messageListener;
    private boolean keepListening;

    public MessageReceiver(MessageListener listener, Socket clientSocket){
        messageListener = listener;
        try{
            clientSocket.setSoTimeout(5000);
            input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        }catch (IOException ioException){
            ioException.printStackTrace();
        }

    }

    @Override
    public void run() {
        String message;

        while(keepListening) {
            try {
                message = input.readLine();
            } catch (SocketTimeoutException socketTimeoutException) {
                continue;
            } catch (IOException ioException) {
                ioException.printStackTrace();
                break;
            }

            if (Objects.nonNull(message)) {
                StringTokenizer tokenizer = new StringTokenizer(message, MESSAGE_SEPARATOR);

                if (tokenizer.countTokens() == 2) {
                    messageListener.messageReceived(tokenizer.nextToken(), tokenizer.nextToken());
                } else {
                    if (message.equalsIgnoreCase(MESSAGE_SEPARATOR + DISCONECT_STRING)) {
                        stopListening();
                    }
                }

            }
        }

        try{
            input.close();
        }catch (IOException ioException){
            ioException.printStackTrace();
        }
    }

    public void stopListening(){
        keepListening=false;
    }
}
