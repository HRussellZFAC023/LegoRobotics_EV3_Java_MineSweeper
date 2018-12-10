package src;

public enum MoveList {

	WHEEL_DIAMETER(45), // The diameter (mm) of the wheels
	AXEL_LENGTH(230), // The distance (mm) your two driven wheels
	ANGULAR_SPEED(25), // How fast around corners (degrees/sec)
	LINEAR_SPEED(50), // How fast in a straight line (mm/sec)
	WALL_DISTANCE(0.18f),//How far the robot should stop from a wall
	TURNING_ANGLE(90),//The angle witch the Jeff need to turn when it face a wall
	
	SWEEPER_SPEED(100), //the speed of front arm
	ARM_ROTATION(100), //angle of the front arm

	REVERSE(-75), //the ev3 backs up 
	SAFESTOPPINGDISTANCE(80), //the ev3 moves forward a safe distance before opening
	CLAW_SPEED(200), //Speed the claw will shut
	CLOSE_CLAW(3200), //angle the claw closes
	HALF_ROTATION(190), //turning 180* almost as colour sensor is off center
	
	TIMEOUT(500) // second wait to scan for mines
	;
	
	private float value;
	
	MoveList(float move){
		this.value = move;
	}
	public float getMove() {
		return(value);
	}
}
