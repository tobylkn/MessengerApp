import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class messengerServer {

	public static void main(String[] args) throws IOException{
		//init server info
		int portnum = 4321;
		ServerSocket listener = null;
		try{
			listener = new ServerSocket(portnum);
			
				//wait for client to connect
				System.out.println("Server Listening...");
				Socket socket = listener.accept();
				//Client connected
				System.out.println("Client Connected");
				
				//start threads handling message receive and send
				messageHandler msgHandler = new messageHandler(socket);
				msgHandler.startReceive();
				msgHandler.startSend();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			listener.close();
		}
		
	}
}
