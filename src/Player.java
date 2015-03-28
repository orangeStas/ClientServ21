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

public class Player extends JFrame 
{
	private JButton Hit;
	private JButton Stay;
	private JPanel buttons;
	private JTextArea displayArea;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private String message = "";
	private String chatServer;
	private Socket client;
	private int cardamt=0;
	// initialize chatServer and set up GUI
	public Player( String host )
	{
		super( "Player" );

		chatServer = host; // set server to which this client connects

		buttons = new JPanel();
		buttons.setLayout(new GridLayout(1,2));
		Hit = new JButton("Hit");
		Stay = new JButton("Stay");
		
		Hit.addActionListener(
				new ActionListener() 
				{
					// send message to server
					public void actionPerformed( ActionEvent event )
					{
						sendData( "hit" );
					} // end method actionPerformed
				}
				);
		
		Stay.addActionListener(
				new ActionListener() 
				{
					// send message to server
					public void actionPerformed( ActionEvent event )
					{
						sendData( "stay" );
					}
				}
				);

		buttons.add(Hit, BorderLayout.SOUTH);
		buttons.add(Stay, BorderLayout.SOUTH);
		buttons.setVisible(true);
		add(buttons,BorderLayout.SOUTH);
		displayArea = new JTextArea();
		add( new JScrollPane( displayArea ), BorderLayout.CENTER );

		setSize( 300, 300 );
		setVisible( true );
	}

	// connect to server and process messages from server
	public void runClient() 
	{
		try // connect to server, get streams, process connection
		{
			connectToServer(); // create a Socket to make connection
			getStreams(); // get the input and output streams
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
			closeConnection(); // close connection
		}
	}

	// connect to server
	private void connectToServer() throws IOException
	{      
		displayMessage( "Attempting connection\n" );

		// create Socket to make connection to server
		client = new Socket( InetAddress.getByName( chatServer ), 23555 );

		// display connection information
		displayMessage( "Connected to: " + 
				client.getInetAddress().getHostName() );
	} // end method connectToServer

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


		do // process messages sent from server
		{ 
			try // read message and display it
			{
				message = ( String ) input.readObject(); // read new message
				displayMessage( "\n" + message ); // display message
				if (message.contains("Bust!") || message.contains("Please Wait")){
					buttons.setVisible(false);				
				}
				
			}
			catch ( ClassNotFoundException classNotFoundException ) 
			{
				displayMessage( "\nUnknown object type received" );
			}

		} while ( !message.equals( "SERVER>>> TERMINATE" ) );
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
		{}
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
