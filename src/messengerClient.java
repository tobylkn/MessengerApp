import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class messengerClient {
	public static void main(String[] args) throws IOException{
		InetAddress localhost = InetAddress.getLocalHost();
				
		int portnum = 4321;
		String serverAddress = localhost.toString();
		
		//Connect to server
		System.out.println("Connecting to server...");
		Socket socket = new Socket(localhost,portnum);
		System.out.println("Connected to server.");
	
		//start threads handling message receive and send
		messageHandler msgHandler = new messageHandler(socket);
		msgHandler.startReceive();
		msgHandler.startSend();
		
		
	}
}
