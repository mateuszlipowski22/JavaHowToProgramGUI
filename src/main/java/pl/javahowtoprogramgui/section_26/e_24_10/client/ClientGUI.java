package pl.javahowtoprogramgui.section_26.e_24_10.client;

import pl.javahowtoprogramgui.section_26.e_24_10.server.MessageListener;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ClientGUI extends JFrame {
    private JMenu serverMenu;
    private JTextArea messageArea;
    private JTextArea inputArea;
    private JButton connectButton;
    private JMenuItem connectMenuItem;
    private JButton disconnectButton;
    private JMenuItem disconnectMenuItem;
    private JButton sendButton;
    private JLabel statusBar;
    private String userName;
    private MessageManager messageManager;
    private MessageListener messageListener;

    public ClientGUI(MessageManager messageManager){
        super("Deitel Messenger");
        this.messageManager=messageManager;
        this.messageListener = new MyMessageListener();

        serverMenu = new JMenu("Server");
        serverMenu.setMnemonic('S');
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(serverMenu);
        setJMenuBar(menuBar);

        Icon connectIcon = new ImageIcon(getClass().getResource("images/Connect.gif"));

        connectButton = new JButton("Connect", connectIcon);
        connectMenuItem = new JMenuItem("Connect", connectIcon);
        connectMenuItem.setMnemonic('C');

        ActionListener connectListener = new ConnectLisetener();
        connectButton.addActionListener(connectListener);
        connectMenuItem.addActionListener(connectListener);

        Icon disconnectIcon = new ImageIcon(getClass().getResource("images/Disconnect.gif"));
        disconnectButton = new JButton("Disconnect", disconnectIcon);
        disconnectMenuItem = new JMenuItem("Disconnect", disconnectIcon);
        disconnectMenuItem.setMnemonic('D');

        disconnectButton.setEnabled(false);
        disconnectMenuItem.setEnabled(false);

        ActionListener disconnectListener = new DisconnectListener();
        disconnectButton.addActionListener(disconnectListener);
        disconnectMenuItem.addActionListener(disconnectListener);

        serverMenu.add(connectMenuItem);
        serverMenu.add(disconnectMenuItem);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(connectButton);
        buttonPanel.add(disconnectButton);

        messageArea = new JTextArea();
        messageArea.setEditable(false);
        messageArea.setWrapStyleWord(true);
        messageArea.setLineWrap(true);

        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new BorderLayout(10,10));
        messagePanel.add(new JScrollPane(messageArea),BorderLayout.CENTER);

        inputArea = new JTextArea();
        inputArea.setEditable(false);
        inputArea.setWrapStyleWord(true);
        inputArea.setLineWrap(true);

        Icon sendIcon = new ImageIcon(getClass().getResource("images/Send.gif"));
        sendButton = new JButton("Send", sendIcon);
        sendButton.setEnabled(false);
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                messageManager.sendMessage(userName, inputArea.getText());
                inputArea.setText("");
            }
        });

        Box box = new Box(BoxLayout.X_AXIS);
        box.add(new JScrollPane(inputArea));
        box.add(sendButton);
        messagePanel.add(box);

        statusBar = new JLabel("Not Connected");
        statusBar.setBorder(new BevelBorder(BevelBorder.LOWERED));

        add(buttonPanel, BorderLayout.NORTH);
        add(messagePanel, BorderLayout.CENTER);
        add(statusBar, BorderLayout.SOUTH);

        addWindowListener(
                new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        messageManager.disconnect(messageListener);
                        System.exit(0);
                    }
                }
        );
    }
}
