package pl.javahowtoprogramgui.section_26.e_26_6;


import javax.swing.*;

public class ClientTest {
    public static void main(String[] args) {
        Client application = new Client();
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.waitForPackets();
    }
}
