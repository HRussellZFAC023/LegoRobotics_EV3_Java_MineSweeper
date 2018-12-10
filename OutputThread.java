import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class OutputThread extends Thread {

	@Override
	public void run() {
		String x = "", y = "", h = "";
		boolean found = false;
		try {
			Socket sc = new Socket("10.0.1.1", 12345);
			Scanner bt = new Scanner(sc.getInputStream());

			while (true) {
				if (bt.hasNext()) {
					String lineRead = bt.next();
					System.out.println(lineRead);
					if(lineRead.charAt(0) == 'X') {
						x = lineRead.substring(2);
					}
					if (lineRead.charAt(0) == 'Y') {
						y = lineRead.substring(2);
						JeffFX.addPoint(x, y);
					}
					if(lineRead.indexOf('M') != -1) {
						//JeffFX.addMine()
					}
				}
			}

		} catch (

		UnknownHostException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}

	}

}
