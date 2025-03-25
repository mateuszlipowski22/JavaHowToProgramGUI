package pl.javahowtoprogramgui.section_26.e_27_7_2;// Rysunek 26.11. TicTacToeServer.java
// Część serwerowa gry w kółko i krzyżyk
import java.awt.BorderLayout;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.util.Formatter;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class TicTacToeServer extends JFrame 
{
   private String[] board = new String[9]; // Tablica
   private JTextArea outputArea; // Wyświetlanie ruchów
   private Player[] players; // Tablica obiektów Players
   private ServerSocket server; // Gniazdo serwerowe do nawiązywania połączeń przez klientów
   private int currentPlayer; // Śledzi gracza, który aktualnie wykonuje ruch
   private final static int PLAYER_X = 0; // Stała dla pierwszego gracza
   private final static int PLAYER_O = 1; // Stała dla drugiego gracza
   private final static String[] MARKS = {"X", "O"}; // Tablica oznaczeń
   private ExecutorService runGame; // Uruchamia graczy
   private Lock gameLock; // Blokada gry w celu synchronizacji
   private Condition otherPlayerConnected; // W celu oczekiwania na innego gracza
   private Condition otherPlayerTurn; // W celu oczekiwania na ruch innego gracza

   // Konfiguracja serwera gry i interfejsu graficznego do wyświetlania komunikatów
   public TicTacToeServer()
   {
      super("Serwer gry w kółko i krzyżyk"); // Konfiguracja tytułu okna

      // Utworzenie ExecutorService z wątkami dla każdego gracza
      runGame = Executors.newFixedThreadPool(2);
      gameLock = new ReentrantLock(); // Utworzenie blokady dla gry

      // Warunek pozwalający czekać na połączenie obu graczy
      otherPlayerConnected = gameLock.newCondition();

      // Warunek dla kolei innego gracza.
      otherPlayerTurn = gameLock.newCondition();      

      for (int i = 0; i < 9; i++)
         board[i] = new String(""); // Utworzenie tablicy do gry
      players = new Player[2]; // Utworzenie tablicy graczy
      currentPlayer = PLAYER_X; // Niech aktualnym graczem będzie pierwszy gracz
 
      try
      {
         server = new ServerSocket(12345, 2); // Konfiguracja ServerSocket
      } 
      catch (IOException ioException) 
      {
         ioException.printStackTrace();
         System.exit(1);
      } 

      outputArea = new JTextArea(); // Utworzenie JTextArea wyświetlającego komunikaty
      add(outputArea, BorderLayout.CENTER);
      outputArea.setText("Serwer czeka na połączenia\n");

      setSize(300, 300); // Ustaw rozmiar okna
      setVisible(true); // Wyświetl okno
   }

   // Czekaj na dwa połączenia, aby móc rozpocząć grę
   public void execute()
   {
      // Czekaj na połączenie każdego z graczy
      for (int i = 0; i < players.length; i++) 
      {
         try // Czekaj na połączenie, utwórz obiekt Player i rozpocznij jego wykonywanie
         {
            players[i] = new Player(server.accept(), i);
            runGame.execute(players[i]); // Rozpoczęcie wykonywania obiektu Player
         } 
         catch (IOException ioException) 
         {
            ioException.printStackTrace();
            System.exit(1);
         } 
      }

      gameLock.lock(); // Zablokuj grę, aby poinformować wątek gracza X

      try
      {
         players[PLAYER_X].setSuspended(false); // Wznów gracza X
         otherPlayerConnected.signal(); // Obudź wątek gracza X
      } 
      finally
      {
         gameLock.unlock(); // Odblokuj grę po poinformowaniu gracza X
      } 
   }

   // Wyświetl komunikat w outputArea.
   private void displayMessage(final String messageToDisplay)
   {
      // Wyświetl komunikat z poziomu wątku obsługi zdarzeń
      SwingUtilities.invokeLater(
         new Runnable() 
         {
            public void run() // Uaktualnij outputArea
            {
               outputArea.append(messageToDisplay); // Dodaj komunikat
            } 
         } 
      ); 
   } 

   // Sprawdź, czy ruch jest poprawny
   public boolean validateAndMove(int location, int player)
   {
      // Jeśli to nie aktualny gracz, musi poczekać na swoją kolei
      while (player != currentPlayer) 
      {
         gameLock.lock(); // Zablokuj grę w oczekiwaniu na ruch drugiego gracza

         try 
         {
            otherPlayerTurn.await(); // Zaczekaj na kolei gracza
         } 
         catch (InterruptedException exception)
         {
            exception.printStackTrace();
         } 
         finally
         {
            gameLock.unlock(); // Odblokuj grę po oczekiwaniu
         } 
      } 

      // Jeśli pole nie jest zajęte, wykonaj ruch
      if (!isOccupied(location))
      {
         board[location] = MARKS[currentPlayer]; // Ustaw ruch na tablicy
         currentPlayer = (currentPlayer + 1) % 2; // Zmień gracza

         // Poinformuj nowego aktualnego gracza o wykonanym ruchu
         players[currentPlayer].otherPlayerMoved(location);

         gameLock.lock(); // Zablokuj grę, aby poinformować innego gracza o ruchu

         try 
         {
            otherPlayerTurn.signal(); // Poinformuj innego gracza, aby kontynuował
         } 
         finally
         {
            gameLock.unlock(); // Odblokuj grę po poinformowaniu
         } 

         return true; // Poinformuj gracza, że ruch był poprawny
      } 
      else // Ruch nie był poprawny.
         return false; // Poinformuj gracza, że ruch nie był poprawny
   }

   // Sprawdź, czy pole jest zajęte
   public boolean isOccupied(int location)
   {
      if (board[location].equals(MARKS[PLAYER_X]) || 
         board [location].equals(MARKS[PLAYER_O]))
         return true; // Pole jest zajęte
      else
         return false; // Pole jest wolne
   }

   // Umieść tu kod sprawdzający zakończenie gry
   public boolean isGameOver()
   {
      return false; // Pozostawiamy to jako ćwiczenie
   }

   // Prywatna klasa wewnętrzna zarządza każdym graczem jako obiektem wykonywalnym
   private class Player implements Runnable 
   {
      private Socket connection; // Połączenie z klientem
      private Scanner input; // Dane od klienta
      private Formatter output; // Dane do klienta
      private int playerNumber; // Przechowuje informację o numerze gracza
      private String mark; // Znak tego gracza
      private boolean suspended = true; // Informacja, czy wątek jest zawieszony

      // Skonfiguruj wątek gracza.
      public Player(Socket socket, int number)
      {
         playerNumber = number; // Zapamiętaj numer tego gracza
         mark = MARKS[playerNumber]; // Określ znak gracza
         connection = socket; // Zapamiętaj gniazdo klienta
         
         try // Pobierz strumienie z obiektu Socket
         {
            input = new Scanner(connection.getInputStream());
            output = new Formatter(connection.getOutputStream());
         } 
         catch (IOException ioException) 
         {
            ioException.printStackTrace();
            System.exit(1);
         } 
      }

      // Wyślij informację, że drugi gracz wykonał ruch
      public void otherPlayerMoved(int location)
      {
         output.format("Przeciwnik wykonał ruch\n");
         output.format("%d\n", location); // Wyślij położenie ruchu
         output.flush(); // Wymuś wysłanie danych
      }

      // Sterowanie wykonywaniem wątku
      public void run()
      {
         // Wyślij klientowi jego znak (X lub O) i przetwórz wiadomości od klienta
         try 
         {
            displayMessage("Gracz " + mark + " dołączył do gry\n");
            output.format("%s\n", mark); // Wyślij znak gracza
            output.flush(); // Wymuś wysłanie danych

            // Jeśli to gracz X, zaczekaj na pojawienie się drugiego gracza
            if (playerNumber == PLAYER_X) 
            {
               output.format("%s\n%s", "Gracz X dołączył do gry",
                  "Czekam na drugiego gracza\n");
               output.flush(); // Wymuś wysłanie danych

               gameLock.lock(); // Zablokuj grę w celu oczekiwania na drugiego gracza

               try 
               {
                  while(suspended)
                  {
                     otherPlayerConnected.await(); // Oczekiwanie na gracza O
                  } 
               }  
               catch (InterruptedException exception) 
               {
                  exception.printStackTrace();
               } 
               finally
               {
                  gameLock.unlock(); // Odblokuj grę po dołączeniu drugiego gracza
               } 

               // Wyślij informację, że dołączył drugi gracz.
               output.format("Drugi gracz dołączył do gry. Twój ruch.\n");
               output.flush(); // Wymuś wysłanie danych
            } 
            else
            {
               output.format("Gracz O dołączył do gry, Czekaj.\n");
               output.flush(); // Wymuś wysłanie danych
            } 

            // Gdy gra się nie skończyła
            while (!isGameOver()) 
            {
               int location = 0; // Inicjalizacja położenia ruchu

               if (input.hasNext())
                  location = input.nextInt(); // Pobierz położenie ruchu

               // Sprawdź poprawność ruchu
               if (validateAndMove(location, playerNumber)) 
               {
                  displayMessage("\nPołożenie: " + location);
                  output.format("Ruch poprawny.\n"); // Poinformuj klienta
                  output.flush(); // Wymuś wysłanie danych
               } 
               else // Ruch nie jest poprawny
               {
                  output.format("Ruch niepoprawny. Spróbuj ponownie.\n");
                  output.flush(); // Wymuś wysłanie danych
               } 
            } 
         } 
         finally
         {
            try
            {
               connection.close(); // Zamknij połączenie z klientem
            } 
            catch (IOException ioException) 
            {
               ioException.printStackTrace();
               System.exit(1);
            } 
         } 
      }

      // Ustaw, czy wątek jest zawieszony lub nie
      public void setSuspended(boolean status)
      {
         suspended = status; // Ustaw wartość zawieszenia
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
