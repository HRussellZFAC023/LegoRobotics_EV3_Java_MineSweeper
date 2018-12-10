package src;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.robotics.subsumption.Behavior;

public class Checkpoint implements Behavior {

	@Override
	public boolean takeControl() {
		Driver.COLOUR_SP.fetchSample(Driver.COLOUR_SAMPLES, 0);
		return (Driver.COLOUR_SAMPLES[0] == 1f && Driver.holding_mine);// is it Green for a checkpoint AND is it holding
																		// a mine?
	}

	@Override
	public void action() {
		Faces.HAPPY.getFaces();
		 Button.LEDPattern(4);
		 Sound.beepSequenceUp();

		float offset = (Driver.SC.getTachoCount() - MoveList.ARM_ROTATION.getMove() / 2);
		offset = offset / MoveList.ARM_ROTATION.getMove() / 2; // fraction of how far it has moved
		offset *= 20; // multiplied by scale factor

		Driver.power.travel(MoveList.REVERSE.getMove());
		Driver.power.rotate(MoveList.HALF_ROTATION.getMove() - offset);
		Driver.power.travel(MoveList.REVERSE.getMove() +0.5*MoveList.SAFESTOPPINGDISTANCE.getMove()
				- Math.abs(offset - 10)/* length of the arm */);
		Driver.power.travel(MoveList.SAFESTOPPINGDISTANCE.getMove());
		Driver.CLAW.rotate(-(int) MoveList.CLOSE_CLAW.getMove());
		Driver.holding_mine = false;
		
		 Sound.beepSequence();
		 Button.LEDPattern(0);
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub

	}

}
