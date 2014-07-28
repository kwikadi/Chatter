import java.net.*;
import java.util.*;
import java.io.*;

class ClsClientChat extends Thread
{
	static DataInputStream objDIS;
	static DataOutputStream objDOS;
		
	public void run()
	{
		try
		{
			Socket objSocket = new Socket("localhost",1500);
			System.out.println("Connection established");
			objDIS = new DataInputStream(objSocket.getInputStream());
			objDOS = new DataOutputStream(objSocket.getOutputStream());
			Gooey gui = new Gooey();
			System.out.println("Control after gooey object creation");
			
			while(true)
			{
			    System.out.println("Control in while client");
				String serverMsg = objDIS.readUTF();
				gui.printanswer(serverMsg);
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
			objDOS.writeUTF(text);
		}
		catch (Exception e)
		{}
	}
}

