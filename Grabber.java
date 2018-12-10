package src;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.robotics.subsumption.Behavior;

public class Grabber implements Behavior {

	@Override
	public boolean takeControl() {
		Driver.COLOUR_SP.fetchSample(Driver.COLOUR_SAMPLES, 0);
		return (Driver.COLOUR_SAMPLES[0] == 0f && !Driver.holding_mine);// is it red for a mine AND is it not already holding a mine?
	}

	@Override
	public void action() {
		Faces.ANGRY.getFaces();
		 Button.LEDPattern(4);
		 Sound.beepSequenceUp();
		 
		float offset = (Driver.SC.getTachoCount() - MoveList.ARM_ROTATION.getMove() / 2);
		offset = offset / MoveList.ARM_ROTATION.getMove() / 2; // fraction of how far it has moved
		offset *= 20; // multiplied by scale factor

		Driver.power.travel(MoveList.REVERSE.getMove());
		Driver.power.rotate(MoveList.HALF_ROTATION.getMove() - offset);
		Driver.power.travel(MoveList.REVERSE.getMove() - 120 - Math.abs(offset - 10)/* length of the arm */);
		Driver.CLAW.rotate((int) MoveList.CLOSE_CLAW.getMove());
		Driver.power.rotate(-(MoveList.HALF_ROTATION.getMove() - offset));
		Driver.holding_mine = true;
		
		 Sound.beepSequence();
		 Button.LEDPattern(0);
		
	}

	@Override
	public void suppress() {
	}
}