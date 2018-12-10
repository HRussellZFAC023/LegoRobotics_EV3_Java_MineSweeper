package src;

import java.util.Random;

import lejos.hardware.lcd.LCD;

public enum Faces {
	//Faces were taken from http://smiley.cool/en/emoticons.php
	HAPPY( new String[] {"(^.^)", "(^_^)", "(^o^)", "(*o*)", "(^u^)"}),
	SAD( new String[] { "(-.-)", "( v_v)", "(-o-）", "(o_o)", "(+_+)"}),
	ANGRY(new String[] {"(*`O´*)", "[x_x]", "(~_~)", "(-`o´- )" });
	
	String[] face;
	
	Faces( String[] s){
		this.face = s;
	}
	
	public String getFaces() {
		//generate a random face from the name
		
		
		Random r = new Random();
		int low = 0;
		int high = face.length;
		int result = r.nextInt(high-low) + low;
		Driver.lcdClear();
		LCD.drawString("   "+ face[result], 2, 3 ); 
		return(face[result]);
		
	}
	
	
 
}
