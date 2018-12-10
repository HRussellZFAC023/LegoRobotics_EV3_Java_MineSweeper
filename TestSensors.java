package src;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class TestSensors {

	private static final EV3UltrasonicSensor EV3_ULTRASONIC_SENSOR = new EV3UltrasonicSensor(SensorPort.S3);
	private static final EV3ColorSensor ev3ColorSensor = new EV3ColorSensor(SensorPort.S4);
	private static final RegulatedMotor c = new EV3MediumRegulatedMotor(MotorPort.A);

	public static void main(String[] args) {
		
		SampleProvider sp1 = ev3ColorSensor.getColorIDMode();
		float[] samples1 =  new float[1];
		
		while (Button.ENTER.isUp()) {
			sp1.fetchSample(samples1,0);
			LCD.drawString(Float.toString(samples1[0]), 0, 0);
			Delay.msDelay(500);
			LCD.clear();
		}

		SampleProvider sp2 = EV3_ULTRASONIC_SENSOR.getDistanceMode();
		float[] samples2 = new float[1];
		while (Button.ENTER.isUp()) {
			sp2.fetchSample(samples2, 0);
			LCD.drawString(Float.toString(samples2[0]), 0, 0);
			Delay.msDelay(500);
			LCD.clear();
		}
		c.forward();
		Button.waitForAnyPress();
		c.stop();
		LCD.drawInt(c.getTachoCount(), 0, 0); 
		Button.waitForAnyPress();
		LCD.clear();
		c.backward();
		Button.waitForAnyPress();
		c.stop();
		LCD.drawInt(c.getTachoCount(), 0, 0);
		Button.waitForAnyPress();
		c.close();
	}

}
