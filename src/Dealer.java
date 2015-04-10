// Modified Fig. 27.5: Multi-threaded Chat Server.java
// Server portion of a client/server stream-socket connection. 

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Dealer extends JFrame implements Runnable {

    private JButton Deal;
    private Deck newdeck;
    private JTextArea displayArea;
    private ExecutorService executor; // will run players
    private ServerSocket server; // server socket
    private SockServer[] sockServer; // Array of objects to be threaded
    private int counter = 0; // counter of number of connections
    private String dealerCard1, dealerCard2;
    private ArrayList<CardHeader> players;
    private CardHeader dealerCards;
    private int playersleft;
    private boolean roundover = true;
    private int winnerScore = 0;
    private boolean isWaiting = true;

    // set up GUI
    public Dealer() {

        super("Dealer");


        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        players = new ArrayList();
        sockServer = new SockServer[100];
        executor = Executors.newFixedThreadPool(100);


        Deal = new JButton("Deal Cards");

        Deal.addActionListener(
                new ActionListener() {
                    // send message to client
                    public void actionPerformed(ActionEvent event) {
                        Deal.setEnabled(false);
                        newdeck = new Deck();
                        roundover = false;
                        DealCards();
                        displayMessage("\n\nCARDS DEALT\n\n");
                        isWaiting = false;
                    } // end method actionPerformed
                }
        );


        add(Deal, BorderLayout.SOUTH);
        setLocationRelativeTo(null);
        displayArea = new JTextArea(); // create displayArea
        displayArea.setEditable(false);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        setSize(300, 300);
        setVisible(true);
    } // end Server constructor

    @Override
    public void run() {
        try // set up server to receive connections; process connections
        {
            server = new ServerSocket(23555, 100); // create ServerSocket

            while (isWaiting) {
                try {
                    sockServer[counter] = new SockServer(counter);
                    sockServer[counter].waitForConnection();
                    executor.execute(sockServer[counter]);
                    // then, continue to create another object and wait (loop)

                }
                catch (EOFException eofException) {
                    displayMessage("\nServer terminated connection");
                }
                finally {
                    ++counter;
                }
            }
        }
        catch (IOException ioException) {
        }
    }

    // manipulates displayArea in the event-dispatch thread
    private void displayMessage(final String messageToDisplay) {
        SwingUtilities.invokeLater(
                new Runnable() {
                    public void run() // updates displayArea
                    {
                        displayArea.append(messageToDisplay); // append message
                    } // end method run
                }
        );
    }

    private void DealCards() {

        try {
            playersleft = counter;
            newdeck.shuffle();
            dealerCard1 = newdeck.dealCard();
            dealerCard2 = newdeck.dealCard();
            displayMessage("\n\n" + dealerCard1 + " " + dealerCard2);

            for (int i = 0; i < counter; i++) {
                String playerCard1, playerCard2;
                playerCard1 = newdeck.dealCard();
                playerCard2 = newdeck.dealCard();
                CardHeader p = new CardHeader(playerCard1, playerCard2);
                players.add(p);
                sockServer[i].sendData("You were Dealt:\n " + playerCard1 + " " + playerCard2);
                sockServer[i].sendData("Your Total: " + p.GetCardTotal());

            }
        } catch (NullPointerException n) {
        }
    }

    private void Results() {

        try {
            for (int i = 0; i < counter; i++) {
                sockServer[i].sendData("Dealer has " + dealerCards.GetCardTotal());
                if (!dealerCards.CheckBust() && !players.get(i).CheckBust()){
                    if (isWinningScore(i) && dealerCards.GetCardTotal() < players.get(i).GetCardTotal()){
                        if (checkTie(i))
                            sockServer[i].sendData("\nTie!");
                        else
                            sockServer[i].sendData("\nWin!");
                    }
                    else if (isWinningScore(i) && dealerCards.GetCardTotal() == players.get(i).GetCardTotal()){
                        sockServer[i].sendData("\nTie!");
                    }
                    else if ((isWinningScore(i) && dealerCards.GetCardTotal() > players.get(i).GetCardTotal()) || (!isWinningScore(i))){
                        sockServer[i].sendData("\nLose!");
                    }
                }

                else if (dealerCards.CheckBust() && !players.get(i).CheckBust()) {
                    if (isWinningScore(i)) {
                        if (checkTie(i))
                            sockServer[i].sendData("\nTie!");
                        else
                            sockServer[i].sendData("\nWin!");
                    }
                    else {
                        sockServer[i].sendData("\nLose!");
                    }
                }

                else if ((!dealerCards.CheckBust() || dealerCards.CheckBust()) && players.get(i).CheckBust()){
                    sockServer[i].sendData("\nLose!");
                }
            }
        } catch (NullPointerException n) {
        }
    }

    public boolean isWinningScore(int user){
        int winningScore = 0;
        for (int i = 0; i < counter; i++){
            if (winningScore < players.get(i).GetCardTotal() && players.get(i).GetCardTotal() <= 21)
                winningScore = players.get(i).GetCardTotal();
        }
        if (winningScore == players.get(user).GetCardTotal())
            return true;
        else
            return false;
    }

    public boolean checkTie(int user){
        int score = players.get(user).GetCardTotal();
        for (int i = 0 ; i < counter && i != user ; i++){
            if (score == players.get(i).GetCardTotal())
                return true;
        }
        return false;
    }

    private class SockServer implements Runnable {
        private ObjectOutputStream output;
        private ObjectInputStream input;
        private Socket connection;
        private int myConID;

        public SockServer(int counterIn) {
            myConID = counterIn;
        }

        public void run() {
            try {
                try {
                    getStreams();
                    processConnection();

                } catch (EOFException eofException) {
                    displayMessage("\nServer" + myConID + " terminated connection");
                } finally {
                    closeConnection();
                }
            } catch (IOException ioException) {
            }
        }


        private void waitForConnection() throws IOException {
            displayMessage("\nWaiting for connection " + myConID + "\n");
            connection = server.accept();
            displayMessage("Connection " + myConID + " received from: " +
                    connection.getInetAddress().getHostName() + "\n");
        }

        private void getStreams() throws IOException {

            output = new ObjectOutputStream(connection.getOutputStream());
            output.flush();

            input = new ObjectInputStream(connection.getInputStream());

            displayMessage("\nGot I/O streams\n");
        }

        private void processConnection() throws IOException {
            if (isWaiting) {
                String message = "Connection " + myConID + " successful";
                sendData(message);

                do {
                    try {
                        if (message.contains("hit")) {
                            cardhit();
                        }

                        if (message.contains("stay")) {
                            this.sendData("Please Wait");
                            playersleft--;
                            CheckDone();
                        }


                        message = (String) input.readObject(); // read new message

                    } catch (ClassNotFoundException classNotFoundException) {
                        displayMessage("\nUnknown object type received");
                    }

                } while (!message.equals("CLIENT>>> TERMINATE"));
            }
            else {
                sendData("Game was beginning!");
                closeConnection();
                counter--;
            }
        }


        private void DealerGo() {
            dealerCards = new CardHeader(dealerCard1, dealerCard2);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (dealerCards.GetCardTotal() < 16) {
                while (dealerCards.GetCardTotal() < 16) {
                    String card1 = newdeck.dealCard();
                    dealerCards.CardHit(card1);
                    displayMessage("Dealer hits..." + card1 + "\n" + "Total:" + dealerCards.GetCardTotal() + "\n");
                }
            }
            if (dealerCards.CheckBust()) {
                displayMessage("Dealer Busts!");
            } else {
                displayMessage("Dealer has" + " " + dealerCards.GetCardTotal());
            }

            Results();
        }

        private void cardhit() {

            String nextc = newdeck.dealCard();
            sendData(nextc);
            players.get(this.myConID ).CardHit(nextc);
            sendData("Your Total: " + players.get(this.myConID).GetCardTotal());
            if (players.get(this.myConID).CheckBust()) {
                sendData("Bust! To more cards!\n");
                playersleft--;
                if (playersleft == 0) {
                    DealerGo();
                }
            }

        }


        private void CheckDone() {

            if (playersleft == 0) {

                DealerGo();
            }
        }

        private void closeConnection() {
            displayMessage("\nTerminating connection " + myConID + "\n");

            try {
                output.close();
                input.close();
                connection.close();
            }
            catch (IOException ioException) {
            }
        }

        private void sendData(String message) {
            try {
                output.writeObject(message);
                output.flush();

            } catch (IOException ioException) {
                displayArea.append("\nError writing object");
            }
        }


    }


}

