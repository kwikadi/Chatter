/* The first and only class that needs to run, ChatterBegin checks if a server is present and if not, sets up a server. Them , it proceeds to create a client. */

import java.net.*;
import java.util.*;
import java.io.*;

class ChatterBegin
{
	public static void main(String args[])
	{
		try (Socket s = new Socket("192.168.1.95", 1500))   //Check for server
		{
			//Server exists. Do nothing.
		}
		catch (IOException ex)   //Server does not exist.
		{
			ClsServerChat csc = new ClsServerChat();  //Create instance of server.
			csc.start(); //Run server.
		}
		ClsClientChat ccc = new ClsClientChat();  //Create instance of client.
		ccc.start();  //Run Client. Note that client creation is not dependant on server, it is always created and run.
	}
}
