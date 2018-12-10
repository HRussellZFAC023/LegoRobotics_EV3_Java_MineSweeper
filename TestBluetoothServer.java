package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TestBluetoothServer {
  public static void main(String[] args) {
	try {
		Socket sc = new Socket("10.0.1.1", 12345);
		Scanner bt = new Scanner(sc.getInputStream());

		while(true) {
			if(bt.hasNext()) {
				String lineRead = bt.next();
				System.out.println(lineRead);
			
			}
			
		}
	} catch (UnknownHostException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	
  }
}