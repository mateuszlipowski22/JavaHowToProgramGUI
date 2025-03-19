package pl.javahowtoprogramgui.section_26.e_26_6;

import javax.swing.*;

public class ServerTest {
    public static void main(String[] args) {
        Server application = new Server();
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.waitForPackets();
    }
}
