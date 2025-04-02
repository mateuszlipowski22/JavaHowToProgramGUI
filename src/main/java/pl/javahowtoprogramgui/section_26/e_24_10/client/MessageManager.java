package pl.javahowtoprogramgui.section_26.e_24_10.client;

import pl.javahowtoprogramgui.section_26.e_24_10.server.MessageListener;

public interface MessageManager {
    public void connect(MessageListener listener);
    public void disconnect(MessageListener listener);
    public void sendMessage(String from, String message);
}
