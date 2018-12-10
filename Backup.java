package src;

import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class Backup implements Behavior {
	private boolean backupInterupt = false;

	@Override
	public boolean takeControl() {
		// System.out.println("Backup?");
		boolean backup = false;
		backupInterupt = false;

//		Driver.COLOUR_SP.fetchSample(Driver.COLOUR_SAMPLES, 0);
//		if (Driver.COLOUR_SAMPLES[0] == 7f) backup = true;// is it brown (aka wall)
		
		Driver.FRONT_SP.fetchSample(Driver.FRONT_SAMPLES, 0);
		if (Driver.FRONT_SAMPLES[0] < MoveList.WALL_DISTANCE.getMove()) backup = true;
		return(backup);
	}

	@Override
	public void action() {

		Faces.SAD.getFaces();

		Driver.RIGHT_SP.fetchSample(Driver.RIGHT_SAMPLES, 0);
		Driver.LEFT_SP.fetchSample(Driver.LEFT_SAMPLES, 0);

//		Driver.US.disable();
		Driver.power.stop();
		Delay.msDelay((long) MoveList.TIMEOUT.getMove());

		
		if (backupInterupt)
			return;
//		Driver.power.travel(MoveList.REVERSE.getMove() -20);

		if (Driver.RIGHT_SAMPLES[0] < Driver.LEFT_SAMPLES[0]) {
			Driver.power.rotate(MoveList.TURNING_ANGLE.getMove());
		} else {
			Driver.power.rotate(-MoveList.TURNING_ANGLE.getMove());
		}
		backupInterupt = false;

	}

	@Override
	public void suppress() {
		backupInterupt = true;
	}

}
