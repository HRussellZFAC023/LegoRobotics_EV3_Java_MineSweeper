package src;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import lejos.hardware.Button;
 
public class TestBluetoothPc {
    public static void main(String[] args) throws IOException {
    	final String I_AM_READY = "I am ready :)" + System.lineSeparator();
		int portNumber = 12345;
		
		System.out.println("server test version working");
		try (ServerSocket serverSocket = new ServerSocket(portNumber); Socket clientSocket = serverSocket.accept();) {
			System.out.println("server initiated");
			Button.waitForAnyPress();

			for (char c : I_AM_READY.toCharArray()) {
				clientSocket.getOutputStream().write(c);			
			}
		    
		} catch (IOException io) {
			io.printStackTrace();
		}

         
    }
}