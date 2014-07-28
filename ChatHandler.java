import java.net.*;
import java.io.*;
import java.util.*;

public class ChatHandler extends Thread
{

    protected Socket s;
    protected DataInputStream i;
	protected DataOutputStream o;
	int id;
	
	public ChatHandler(Socket s, int id) throws IOException
	{
	    this.s=s;
		this.id=id;
		i=new DataInputStream(new BufferedInputStream(s.getInputStream()));
		o=new DataOutputStream(new BufferedOutputStream(s.getOutputStream()));
		
		//connections establish
		//thread on
	}
	
	protected static Vector handlers=new Vector();
	
	public void run()
	{
	   try
	   {
          handlers.add(this);
          while(true)
          {
                 String msg=i.readUTF();
                 broadcast(msg, this.id);
				 
				 //i.flush();
          }
       }
       catch (IOException ex)
	   {
           ex.printStackTrace ();
       } 
	   finally
	   {
          handlers.remove(this);
          try 
		  {
             s.close ();
          }
		  catch (IOException ex)
		  {
              ex.printStackTrace();
          }
       }
    }	   

	
    public static void broadcast(String message, int id)
	{
	     synchronized(handlers)
		 {
		    Iterator e=handlers.iterator();
			while(e.hasNext())
			{
			    ChatHandler c=(ChatHandler) e.next();
				try
				{
				    synchronized(c.o)
					{
						if(c.id != id)
							c.o.writeUTF(message);
					}
					c.o.flush();
                }
				catch(IOException ex)
				{
				    c.stop();
				}
            }				
		 }
    }
	
}  