import java.net.*;
import java.util.*;
import java.io.*;

class ClsServerChat extends Thread
{
	static DataInputStream objDIS;
	static DataOutputStream objDOS;
	int i=0;

	public void run()
	{
		try
		{
			ServerSocket objServerSocket = new ServerSocket(1500);
			System.out.println("Server connected");

			while(true)
			{
			  System.out.println("Waiting for Client to Connect");
			  Socket client = objServerSocket.accept();
			  System.out.println("Client connected");
			  ChatHandler c = new ChatHandler(client,i);
			  c.start();
			  i++;
		    }
		}
		catch(Exception e)
		{
			System.out.println("Oh, well");
		}
	}
}

