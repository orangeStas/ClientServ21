// Fig. 27.7: Client.java
// Client portion of a stream-socket connection between client and server.
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Player extends JFrame implements Runnable
{
    private JPanel buttons;
	private JTextArea displayArea;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private String message = "";
	private String chatServer;
	private Socket client;
	private int cardamt=0;

    private int countCards;

    private final String cardPath = "images/cards";

	public Player(String host)
	{
		super("Player");

        getContentPane().setBackground(new Color(11, 165, 0));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
		chatServer = host; // set server to which this client connects

		buttons = new JPanel();
		buttons.setLayout(new GridLayout(1,2));
        JButton hitButt = new JButton("Hit");
        JButton stayButton = new JButton("Stay");
		
		hitButt.addActionListener(
                new ActionListener() {
                    // send message to server
                    public void actionPerformed(ActionEvent event) {
                        sendData("hit");
                    } // end method actionPerformed
                }
        );
		
		stayButton.addActionListener(
                new ActionListener() {
                    // send message to server
                    public void actionPerformed(ActionEvent event) {
                        sendData("stay");
                    }
                }
        );

		buttons.add(hitButt);
		buttons.add(stayButton);
		buttons.setVisible(true);
		add(buttons);
		displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
		scrollPane.setPreferredSize(new Dimension(200,140));
        add(scrollPane);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int sizeWidth = 350;
        int sizeHeight = 200;
        int locationX = (screenSize.width - sizeWidth) / 2;
        int locationY = (screenSize.height - sizeHeight) / 2;
        setBounds(locationX, locationY, sizeWidth, sizeHeight);
        //setSize(350, 200);
        //pack();

		setVisible( true );

        countCards = 0;
	}


    @Override
    public void run() {
        try
        {
            connectToServer();
            getStreams();
            processConnection();
        }
        catch ( EOFException eofException )
        {
            displayMessage( "\nClient terminated connection" );
        }
        catch ( IOException ioException )
        {}
        finally
        {
            closeConnection();
        }
    }

	// connect to server
	private void connectToServer() throws IOException
	{      
		displayMessage( "Attempting connection\n" );

		client = new Socket( InetAddress.getByName( chatServer ), 23555 );

		displayMessage( "Connected to: " + client.getInetAddress().getHostName() );
	}

	// get streams to send and receive data
	private void getStreams() throws IOException
	{

		output = new ObjectOutputStream( client.getOutputStream() );      
		output.flush();

		// set up input stream for objects
		input = new ObjectInputStream( client.getInputStream() );

		displayMessage( "\nGot I/O streams\n" );
	}


	private void processConnection() throws IOException
	{
		do
		{ 
			try
			{
				message = ( String ) input.readObject(); // read new message
				displayMessage( "\n" + message ); // display message
				if (message.contains("Bust!")){
                    JOptionPane.showMessageDialog(this, "Busted!!!", "Info", JOptionPane.ERROR_MESSAGE);
				}
                else if (message.contains("Win")){
                    printResults("Win!!!");
                }
                else if (message.contains("Lose")){
                    printResults("Lose!");
                }
                else if (message.contains("Tie")){
                    printResults("Tie!");
                }
                try {
                    drawCards(message);
                } catch (Exception e){
                    e.printStackTrace();
                }
				
			}
			catch ( ClassNotFoundException classNotFoundException ) 
			{
				displayMessage( "\nUnknown object type received" );
			}

		} while ( !message.equals( "SERVER>>> TERMINATE" ) );
	}

    private void printResults(String string){
        buttons.setVisible(false);
        setSize(getSize().width - 120, getSize().height);
        if (!string.isEmpty()){
            JOptionPane.showMessageDialog(this, string, "Info",JOptionPane.ERROR_MESSAGE);
        }
    }

    private void drawCards(String message){
        String[] tokens = message.split(" ");
        if (tokens.length == 7){
            ImageIcon imageIcon = new ImageIcon(startForm.class.getResource(cardPath + "/" + tokens[3] + "of" + tokens[4] + "s" +".png"));
            add(new JLabel(imageIcon));

            imageIcon = new ImageIcon(startForm.class.getResource(cardPath + "/" + tokens[5] + "of" + tokens[6] + "s" +".png"));
            add(new JLabel(imageIcon));

            countCards += 2;

            setSize(getSize().width + 160, getSize().height);
            getContentPane().revalidate();
            getContentPane().repaint();
        }
        else if (tokens.length == 2) {
            ImageIcon imageIcon = new ImageIcon(startForm.class.getResource(cardPath + "/" + tokens[0] + "of" + tokens[1] + "s" +".png"));
            add(new JLabel(imageIcon));

            countCards++;

            setSize(getSize().width + 80, getSize().height);
            getContentPane().revalidate();
            getContentPane().repaint();
        }
        else
        {
            getContentPane().revalidate();
            getContentPane().repaint();
        }
        //pack();
        //revalidate();
        //repaint();
    }

	private void closeConnection() 
	{
		displayMessage( "\nClosing connection" );
		try 
		{
			output.close();
			input.close();
			client.close();
		} // end try
		catch ( IOException ioException ) 
		{
            ioException.printStackTrace();
        }
	}


	private void sendData( String message )
	{
		try
		{
			output.writeObject(  message );
			output.flush();
			
		}
		catch ( IOException ioException )
		{
			displayArea.append( "\nError writing object" );
		}
	}

	private void displayMessage( final String messageToDisplay )
	{
		SwingUtilities.invokeLater(
				new Runnable()
				{
					public void run() // updates displayArea
					{
						displayArea.append( messageToDisplay );
					} // end method run
				}
				);
	}



}
