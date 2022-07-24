/************************************************************
 * Brian Banfield
 * 7/24/2022
 * Server example. 
 ***********************************************************/
package socket;
import java.net.*;
import java.io.*;
import java.nio.charset.*;

public class ServerExample {

	public static void main(String[] args) {
		ServerSocket server = null;
		boolean shutdown = false;
		
		try
		{
			server = new ServerSocket(1234);
			System.out.println("Port bound. Connection Accepted. ");
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		while(!shutdown)
		{
			Socket client = null;
			InputStream input = null;
			OutputStream output = null;
			
			try
			{
				client = server.accept();
				input = client.getInputStream();
				output = client.getOutputStream();
				
				int n = input.read();
				byte[] data = new byte[n];
				input.read(data);
				
				String clientInput = new String(data, StandardCharsets.UTF_8);
				clientInput.replace("\n", "");
				
				int t = Integer.parseInt(clientInput);
		//		System.out.println("Client said: " + clientInput);
				boolean prime = true;
				for(int i = 2; i < t; i++) {
					if(t % i == 0) {
						prime = false;
					}
				}
				if(prime == true) {
					String response = "This number is prime [" + clientInput + "]"; 
					output.write(response.length());
					output.write(response.getBytes());
				}
				else {
					String response = "This number is not prime [" + clientInput + "]"; 
					output.write(response.length());
					output.write(response.getBytes());
				}
				
				client.close();
				if(clientInput.equalsIgnoreCase("Shutdown"))
				{
					System.out.println("Shutting down... ");
					shutdown = true;
				}
				
				
			} catch (IOException e) {
				e.printStackTrace();
				continue;
			}

	}

  }

}














