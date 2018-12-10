import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import lejos.hardware.Button;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.NXTUltrasonicSensor;
import lejos.robotics.MirrorMotor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class Driver {

	public static MovePilot power; 

	final static RegulatedMotor CLAW = MirrorMotor.invertMotor(new EV3MediumRegulatedMotor(MotorPort.A)); // claw
	final static RegulatedMotor SC = new EV3LargeRegulatedMotor(MotorPort.B); // sweeper claw

	final static NXTUltrasonicSensor UR = new NXTUltrasonicSensor(SensorPort.S1); // right ultra-sound
	final static NXTUltrasonicSensor UL = new NXTUltrasonicSensor(SensorPort.S2); // left ultra-sound
	final static EV3UltrasonicSensor US = new EV3UltrasonicSensor(SensorPort.S3);
	final static EV3ColorSensor CS = new EV3ColorSensor(SensorPort.S4);

	final static SampleProvider FRONT_SP = US.getDistanceMode();
	final static float[] FRONT_SAMPLES = new float[1];

	final static SampleProvider LEFT_SP = UL.getDistanceMode();
	static float[] LEFT_SAMPLES = new float[1];

	final static SampleProvider RIGHT_SP = UR.getDistanceMode();
	static float[] RIGHT_SAMPLES = new float[1];

	final static SampleProvider COLOUR_SP = CS.getColorIDMode();
	static float[] COLOUR_SAMPLES = new float[1];

	public static boolean holding_mine = false;

	public static void main(String[] args) {
		startup();
	}

	public static void startup() {

		CLAW.rotate(-(int) MoveList.CLOSE_CLAW.getMove());
		SC.backward(); // sets initial position for sweeper
		while (!SC.isStalled());
		
		Faces.HAPPY.getFaces();
	
		final EV3LargeRegulatedMotor ML = new EV3LargeRegulatedMotor(MotorPort.C);
		final EV3LargeRegulatedMotor MR = new EV3LargeRegulatedMotor(MotorPort.D);
		final Wheel WHEEL_LEFT = WheeledChassis.modelWheel(ML, MoveList.WHEEL_DIAMETER.getMove())
				.offset(-MoveList.AXEL_LENGTH.getMove() / 2).invert(true);
		final Wheel WHEEL_RIGHT = WheeledChassis.modelWheel(MR, MoveList.WHEEL_DIAMETER.getMove())
				.offset(MoveList.AXEL_LENGTH.getMove() / 2).invert(true);
		Chassis chassis = new WheeledChassis((new Wheel[] { WHEEL_RIGHT, WHEEL_LEFT }), WheeledChassis.TYPE_DIFFERENTIAL);
		power = new MovePilot(chassis);
		power.setAngularSpeed(MoveList.ANGULAR_SPEED.getMove());
		power.setLinearSpeed(MoveList.LINEAR_SPEED.getMove());

		Thread moveListener = new SendMessage();
		moveListener.setDaemon(true);

		moveListener.start();
		
		Button.ENTER.waitForPressAndRelease();
		
		Thread sweep = new Sweeper(SC);
		sweep.setDaemon(true);

		sweep.start();

		
		Behavior b1 = new Trundle();
		Behavior b2 = new Backup();
		Behavior b3 = new Grabber();
		Behavior b4 = new Checkpoint();
		Behavior b5 = new EmergencyStop();
		
		//Behavior b5 = new sendMessage();

		Behavior[] behaviours = { b1, b2, b3, b4, b5 };
		Arbitrator ab = new Arbitrator(behaviours);
		lcdClear();
		ab.go();

	}

	public static void lcdClear() {
		for (int i = 0; i < 8; i++) {
			System.out.println("");
		}
	}
}