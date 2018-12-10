package src;

import lejos.hardware.Button;
import lejos.robotics.subsumption.Behavior;

public class EmergencyStop implements Behavior {

	@Override
	public boolean takeControl() {
		return (Button.ESCAPE.isDown());
	}

	@Override
	public void action() {
		System.exit(1);
	}

	@Override
	public void suppress() {
	}

}
