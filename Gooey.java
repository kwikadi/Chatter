import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.net.*;
import java.io.*;

public class Gooey
{
	JFrame fr;
	JDialog jd;
	JTextField tf;
	String text="";
	JEditorPane editor;
	String check;
	String them;
	JScrollPane scrollPane;
	JButton fp;
	String name = System.getProperty("user.name");
	public Gooey()
	{		
		fr = new JFrame(name);
		
		fr.setLayout(null);
		
		editor = new JEditorPane("text/html",text);
		scrollPane = new JScrollPane(editor);
		
		fp = new JButton("File");
		fp.setBounds(124,332,60,30);
		final JFileChooser  fileDialog = new JFileChooser();
		fp.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				int returnVal = fileDialog.showOpenDialog(fr);
				if (returnVal == JFileChooser.APPROVE_OPTION) 
				{
					java.io.File file = fileDialog.getSelectedFile();
					
					/*tf.setText("File Selected :" + file.getName());
					byte [] bytearray  = new byte [(int)file.length()];
					FileInputStream fin = new FileInputStream(file);
					BufferedInputStream bin = new BufferedInputStream(fin);
					bin.read(bytearray,0,bytearray.length);
					OutputStream os = socket.getOutputStream();
					System.out.println("Sending Files...");
					os.write(bytearray,0,bytearray.length);
					os.flush();
					System.out.println("File transfer complete");*/
				}
				else
				{
					tf.setText("Open command cancelled by user." );           
				}      
			}
		});		
		tf = new JTextField();
		tf.setBounds(0,332,124,30);
		
		scrollPane.setBounds(0,0,185,330);
		editor.setEditable(false);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		fr.add(fp);
		fr.setSize(200,400);
		fr.setVisible(true);
		fr.add(tf);
		fr.add(scrollPane);
		tf.addKeyListener(new KeyAdapter() 
		{
			public void keyPressed(KeyEvent e) 
			{                
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					check = tf.getText();
					if(!check.equals(""))
					{
						ClsClientChat.printmsg("<strong>" + name + ": </strong>" +check);//broadcast
						check = "<strong>You: </strong>" + check;
						text = text + "<br>" + check;
						editor.setText(text);
						tf.setText("");
					}
				}	
            }
        });

		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void printanswer(String txt)
	{
		them = txt;
		text = text + "<br>" + them ;
		editor.setText(text);	
	}
}