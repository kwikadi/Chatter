import java.net.*;
import java.util.*;
import java.io.*;

class ClsClientChat extends Thread
{
	static DataInputStream objDIS; //For sending chats
	static DataOutputStream objDOS; //For recieving chats

	public void run()
	{
		try
		{
			Socket objSocket = new Socket("192.168.1.95",1500); //Send request to server at given address:port
			System.out.println("Connection established");
			objDIS = new DataInputStream(objSocket.getInputStream());  //Input
			objDOS = new DataOutputStream(objSocket.getOutputStream()); //Ouput
			Gooey gui = new Gooey();  //Create GUI for client. Note that server doesnt need any gui.
			System.out.println("Control after gooey object creation");

			while(true)
			{
			    System.out.println("Control in while client");
				String serverMsg = objDIS.readUTF();  //Read message.
				gui.printanswer(serverMsg); //Display message on GUI
			}
		}
		catch(Exception e)
		{
			System.out.println("Server not found. Wut");
		}
	}
	static void printmsg(String text)
	{
		try
		{
			objDOS.writeUTF(text);  //Send message to server when recieved by GUI
		}
		catch (Exception e)
		{}
	}
}

