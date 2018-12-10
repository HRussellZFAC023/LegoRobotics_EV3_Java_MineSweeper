package src;

import lejos.robotics.RegulatedMotor;

public class Sweeper extends Thread {

	private RegulatedMotor sc;

	public Sweeper(RegulatedMotor sc) {
		this.sc = sc;
	}

	@Override
	public void run() {
		super.run(); // always call super
		// make a motor
		sc.setSpeed((int) MoveList.SWEEPER_SPEED.getMove());
		while (true) {
			// move
			sc.rotate((int) MoveList.ARM_ROTATION.getMove());
			sc.rotate(-(int) MoveList.ARM_ROTATION.getMove());
		}
	}
}
