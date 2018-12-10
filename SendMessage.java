package src;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import lejos.hardware.Button;
import lejos.robotics.localization.OdometryPoseProvider;
import lejos.robotics.localization.PoseProvider;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class SendMessage extends Thread {

	final private static PoseProvider POSEP = new OdometryPoseProvider(Driver.power);
	final private static int PORT_NUMBER = 12345;

	@Override
	public void run() {
		super.run(); // always call super

		System.out.println("  server open  ");

		try (ServerSocket serverSocket = new ServerSocket(PORT_NUMBER); Socket clientSocket = serverSocket.accept();) {
			System.out.println(" server initiated ");
			Button.ENTER.waitForPressAndRelease();

			write("I am ready :) ", clientSocket);

			System.out.println("initiated");
			while (true) {
				if (!Driver.power.isMoving()) {
					// print pose provider
					// (POSEP.getPose().toString(),clientSocket);
					write("X:" + String.valueOf(POSEP.getPose().getX() + " "), clientSocket);
					write("Y:" + String.valueOf(POSEP.getPose().getY() + " "), clientSocket);
					// write M if there is a mine
					// write C if there is a no mine
					while (!Driver.power.isMoving()) {
						// wait till it stops moving so only prints once per move
					}

				}

			}

		} catch (IOException io) {
			io.printStackTrace();
			System.exit(1);
		}
	}

	public static void write(String message, Socket clientSocket) throws IOException {
		for (char c : message.toCharArray()) {
			clientSocket.getOutputStream().write(c);
		}
	}

}
