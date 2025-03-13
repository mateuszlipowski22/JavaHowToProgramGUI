package pl.javahowtoprogramgui.section_26.e_26_2;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ReadServerFile extends JFrame {
    private JTextField enterField;
    private JEditorPane contentsArea;

    public ReadServerFile(){
        super("Prosta przeglądarka internetowa");

        enterField = new JTextField("Wpisz tu adres URL");
        enterField.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        getThePage(e.getActionCommand());
                    }
                }
        );

        add(enterField, BorderLayout.NORTH);

        contentsArea = new JEditorPane();
        contentsArea.setEditable(false);
        contentsArea.addHyperlinkListener(
                new HyperlinkListener() {
                    @Override
                    public void hyperlinkUpdate(HyperlinkEvent e) {
                        if(e.getEventType()==HyperlinkEvent.EventType.ACTIVATED){
                            getThePage(e.getURL().toString());
                        }
                    }
                }
        );

        add(new JScrollPane(contentsArea),BorderLayout.CENTER);
        setSize(400,300);
        setVisible(true);
    }

    private void getThePage(String location){
        try{
            contentsArea.setPage(location);
            enterField.setText(location);
        }catch (IOException e){
            JOptionPane.showMessageDialog(this,"Błąd pobierania wskazanego adresu URL","Niepoprawny URL", JOptionPane.ERROR_MESSAGE);
        }
    }

}
