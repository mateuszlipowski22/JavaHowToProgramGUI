package pl.javahowtoprogramgui.section_26.e_27_7_2;// Rysunek 26.13. TicTacToeClient.java
// Część kliencka gry w kółko i krzyżyk
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.Socket;
import java.net.InetAddress;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.util.Formatter;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class TicTacToeClient extends JFrame implements Runnable 
{
   private JTextField idField; // Pole tekstowe do wyświetlenia znaku gracza
   private JTextArea displayArea; // Komponent JTextArea do wyświetlania komunikatów
   private JPanel boardPanel; // Panel dla tablicy do gry
   private JPanel panel2; // Panel zawierający tablicę
   private Square[][] board; // Tablica do gry
   private Square currentSquare; // Aktualne pole
   private Socket connection; // Połączenie z serwerem
   private Scanner input; // Dane z serwera
   private Formatter output; // Dane do serwera
   private String ticTacToeHost; // Nazwa hosta dla serwera
   private String myMark; // Znak klienta
   private boolean myTurn; // Określa, czy to ruch tego gracza
   private final String X_MARK = "X"; // Znacznik dla pierwszego klienta
   private final String O_MARK = "O"; // Znacznik dla drugiego klienta

   // Konfiguracja interfejsu użytkownika i tablicy
   public TicTacToeClient(String host)
   { 
      ticTacToeHost = host; // Ustaw nazwę serwera
      displayArea = new JTextArea(4, 30); // Skonfiguruj komponent JTextArea
      displayArea.setEditable(false);
      add(new JScrollPane(displayArea), BorderLayout.SOUTH);

      boardPanel = new JPanel(); // Ustaw panel dla pól tablicy
      boardPanel.setLayout(new GridLayout(3, 3, 0, 0));

      board = new Square[3][3]; // Utwórz tablicę

      // Przejdź przez wiersze tablicy
      for (int row = 0; row < board.length; row++) 
      {
         // Przejdź przez kolumny tablicy
         for (int column = 0; column < board[row].length; column++) 
         {
            // Utwórz pole.
            board[row][column] = new Square(" ", row * 3 + column);
            boardPanel.add(board[row][column]); // Dodaj pole.      
         }
      } 

      idField = new JTextField(); // Skonfiguruj pole tekstowe
      idField.setEditable(false);
      add(idField, BorderLayout.NORTH);
      
      panel2 = new JPanel(); // Skonfiguruj panel przechowujący boardPanel
      panel2.add(boardPanel, BorderLayout.CENTER); // Dodaje panel z tablicą
      add(panel2, BorderLayout.CENTER); // Dodaj do kontenera

      setSize(300, 225); // Ustaw rozmiar okna
      setVisible(true); // Wyświetl okno

      startClient();
   }

   // Uruchom wątek klienta
   public void startClient()
   {
      try // Połącz z serwerem i pobierz strumienie
      {
         // Połącz się z serwerem
         connection = new Socket(
            InetAddress.getByName(ticTacToeHost), 12345);

         // Pobierz strumienie wejściowe i wyjściowe
         input = new Scanner(connection.getInputStream());
         output = new Formatter(connection.getOutputStream());
      } 
      catch (IOException ioException)
      {
         ioException.printStackTrace();         
      } 

      // Utwórz i uruchom wątek roboczy dla tego klienta
      ExecutorService worker = Executors.newFixedThreadPool(1);
      worker.execute(this); // Uruchom klienta
   }

   // Wątek sterujący umożliwia stałą aktualizację obszaru displayArea
   public void run()
   {
      myMark = input.nextLine(); // Pobierz znak gracza (X lub O)

      SwingUtilities.invokeLater(
         new Runnable() 
         {         
            public void run()
            {
               // Wyświetl znak gracza
               idField.setText("Jesteś graczem \"" + myMark + "\"");
            } 
         } 
      ); 
            
      myTurn = (myMark.equals(X_MARK)); // Sprawdź, czy to ruch tego klienta

      // Pobierz komunikaty przesłane do klienta i je wyświetl
      while (true)
      {
         if (input.hasNextLine())
            processMessage(input.nextLine());
      } 
   }

   // Przetwórz komunikaty wysłane do klienta
   private void processMessage(String message)
   {
      // Wykonano poprawny ruch
      if (message.equals("Ruch poprawny.")) 
      {
         displayMessage("Ruch poprawny. Czekaj.\n");
         setMark(currentSquare, myMark); // Ustaw znak w polu
      } 
      else if (message.equals("Ruch niepoprawny. Spróbuj ponownie.")) 
      {
         displayMessage(message + "\n"); // Wyświetl niepoprawny ruch
         myTurn = true; // To nadal ruch tego klienta
      }  
      else if (message.equals("Przeciwnik wykonał ruch")) 
      {
         int location = input.nextInt(); // Pobierz położenie ruchu
         input.nextLine(); // Pomiń wiersz po wartości całkowitej
         int row = location / 3; // Oblicz wiersz
         int column = location % 3; // Oblicz kolumnę

         setMark(board[row][column], 
            (myMark.equals(X_MARK) ? O_MARK : X_MARK)); // Oznacz ruch              
         displayMessage("Przeciwnik wykonał ruch. Twoja kolej.\n");
         myTurn = true; // Teraz kolej tego klienta
      }  
      else
         displayMessage(message + "\n"); // Wyświetl komunikat
   }

   // Zmodyfikuj displayArea w wątku obsługi zdarzeń
   private void displayMessage(final String messageToDisplay)
   {
      SwingUtilities.invokeLater(
         new Runnable() 
         {
            public void run() 
            {
               displayArea.append(messageToDisplay); // Aktualizacja wartości
            } 
         } 
      ); 
   } 

   // Metoda pomocnicza do ustawienia znaku na tablicy w wątku obsługi zdarzeń
   private void setMark(final Square squareToMark, final String mark)
   {
      SwingUtilities.invokeLater(
         new Runnable() 
         {
            public void run()
            {
               squareToMark.setMark(mark); // Ustaw znak w polu
            } 
         } 
      ); 
   } 

   // Wyślij komunikat do serwera z informacją o klikniętym polu
   public void sendClickedSquare(int location)
   {
      // Jeśli to moja kolej
      if (myTurn) 
      {
         output.format("%d\n", location); // Wyślij położenie na serwer
         output.flush();
         myTurn = false; // To już nie jest moja tura
      } 
   }

   // Ustaw aktualne pole
   public void setCurrentSquare(Square square)
   {
      currentSquare = square; // Ustaw aktualne pole na argument
   }

   // Prywatna klasa wewnętrzna dla pól tablicy
   private class Square extends JPanel 
   {
      private String mark; // Znak do narysowania w polu
      private int location; // Położenie pola
   
      public Square(String squareMark, int squareLocation)
      {
         mark = squareMark; // Ustaw znakj pola
         location = squareLocation; // Ustaw położenie pola

         addMouseListener(
            new MouseAdapter() 
            {
               public void mouseReleased(MouseEvent e)
               {
                  setCurrentSquare(Square.this); // Ustaw aktualne pole

                  // UStaw położenie tego pola
                  sendClickedSquare(getSquareLocation());
               } 
            } 
         ); 
      } 

      // Zwróć preferowany rozmiar pola
      public Dimension getPreferredSize() 
      { 
         return new Dimension(30, 30); // Zwróć preferowany rozmiar
      }

      // Zwróć minimalny rozmiar pola
      public Dimension getMinimumSize() 
      {
         return getPreferredSize(); // Zwróć preferowany rozmiar
      }

      // Ustaw znak w polu
      public void setMark(String newMark) 
      { 
         mark = newMark; // Ustaw znak w polu
         repaint(); // Przerysuj pole
      }

      // Zwróć położenie pola
      public int getSquareLocation() 
      {
         return location; // Zwróć położenie pola
      }

      // Rysuj pole
      public void paintComponent(Graphics g)
      {
         super.paintComponent(g);

         g.drawRect(0, 0, 29, 29); // Rysuj pole
         g.drawString(mark, 11, 20); // Rysuj znak 
      } 
   }
}

/**************************************************************************
 * (C) Copyright 1992-2018 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/
