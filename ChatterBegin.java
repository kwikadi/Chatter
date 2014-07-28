import java.net.*;
import java.util.*;
import java.io.*;

class ChatterBegin
{
	public static void main(String args[]) 
	{ 
		try (Socket s = new Socket("localhost", 1500)) 
		{
			/*Ignore*/
		}
		catch (IOException ex) 
		{
			ClsServerChat csc = new ClsServerChat();
			csc.start();
		}
		ClsClientChat ccc = new ClsClientChat();
		ccc.start();
	}
}