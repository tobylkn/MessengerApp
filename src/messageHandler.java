import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class messageHandler {
	BufferedReader input;
	PrintWriter out;
	//DataOutputStream os;
	Socket socket;

	public messageHandler(Socket socket) throws IOException{
		this.socket = socket;
		out = new PrintWriter(socket.getOutputStream(), true);
		//os = new DataOutputStream(socket.getOutputStream());
	    input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

	}
	
	public void startReceive(){
		Thread t = new Thread(new ReceiveRunnable());
		t.start();
	}

	public void startSend(){
		Thread t = new Thread(new SendRunnable());
		t.start();
		
	}
	public class ReceiveRunnable implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("receiveRunnable started");
			String text;
			try {
				while(true){
					if((text=input.readLine())!=null){
						System.out.println(text);
						if(text.equals("bye")){
							socket.close();
							System.out.println("Says Bye, Socket Closed");
							break;
						}
					}
				}
				input.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("exit receiveRunnable");
		}
		
		
	}

	public class SendRunnable implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("sendRunnable started");
			String text;
			Scanner userInput = new Scanner(System.in);
			while((text=userInput.nextLine())!=null){
				out.println(text);
			}
			
			System.out.println("exit sendRunnable");
		}
		
	}
}
