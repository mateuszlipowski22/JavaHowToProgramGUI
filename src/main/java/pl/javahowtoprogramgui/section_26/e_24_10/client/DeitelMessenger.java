package pl.javahowtoprogramgui.section_26.e_24_10.client;

public class DeitelMessenger {
    public static void main(String[] args) {
        MessageManager messageManager;
        if(args.length==0){
            messageManager = new SocketMessageManager("localhost");
        }else {
            messageManager = new SocketMessageManager(args[0]);
        }

        ClientGUI clientGUI = new ClientGUI(messageManager);
        clientGUI.setSize(300,400);
        clientGUI.setResizable(true);
        clientGUI.setVisible(true);
    }
}
