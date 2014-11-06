import java.net.*;
import java.util.*;
import java.io.*;

class ClsServerChat extends Thread
{
	static DataInputStream objDIS; //For sending chats
	static DataOutputStream objDOS;  //For recieving chats
	int i=0;  //Identity tag

	public void run()
	{
		try
		{
			//InetAddress addname = InetAddress.getByName("192.168.1.95");
			ServerSocket objServerSocket = new ServerSocket(1500);  //Create server socket object at port 1500. Port no can be changed.
			System.out.println("Server connected");  //Debugging. Ignore.

			while(true)
			{
			  //System.out.println("Waiting for Client to Connect");
			  Socket client = objServerSocket.accept();  //Wait for client connection request, and accept request when recieved.
			  //System.out.println("Client connected");
			  ChatHandler c = new ChatHandler(client,i); //Send the socket, along with the identifier, to ChatHandler, which manages chats for server.
			  c.start(); //Start handler instance. Note that new instance is created for each chat.
			  i++;  //Increment to ensure unique ids.
			  /* Note that the identity tag isnt needed (The chat hadler can differentiate using the socket itself). A bit of verbosity is added here
			  to ensure clarity of concepts applied */
		    }
		}
		catch(Exception e)
		{
			//System.out.println("Oh, well");
		}
	}
}
