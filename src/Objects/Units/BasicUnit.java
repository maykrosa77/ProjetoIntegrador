package Objects.Units;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import Objects.Sprite;

public class BasicUnit extends Sprite{

	public float speed;
	
	public int stateIA;
	public int battlefieldX;
	public int battlefieldY;
	
	protected BufferedImage image;
	
	protected float vectorX;
	protected float vectorY;
	
	protected int timerIA;
	
	public BasicUnit(BufferedImage image, int x, int y){
		this.image =  image;
		this.width = image.getWidth();
		this.height = image.getHeight();
		this.radius = width/2;
		
		this.x = x;
		this.y = y;
		
		this.active = true;
		
		speed = 300;
		vectorX = 0;
		vectorY = 0;
		
		stateIA = 0;
		battlefieldX = 0;
		battlefieldY = 0;
		
		timerIA = 0;
	}
	
	@Override
	public void update(int difTime) {
		timerIA += difTime;
		if(timerIA>100){
			timerIA -= 100;
			calculateIA(difTime);
		}
		
        x += vectorX*(difTime/1000f);
        y += vectorY*(difTime/1000f);
	}

	@Override
	public void render(Graphics2D graphics) {
		graphics.drawImage(image, x, y, width, height, null);
	}
	
	public void calculateIA(int difTime){
		/*Stop*/
		if(stateIA == 0){
			vectorX = 0;
			vectorY = 0;
		}
		
		/*Going to battlefield*/
		if(stateIA == 1){
            float difX = battlefieldX - x;
            float difY = battlefieldY - y;
            float moduleVector = (float)Math.sqrt((difX * difX)+(difY * difY));
            
			if(Math.abs(difX)<10 && Math.abs(difY)<10){
				stateIA = 0;
				battlefieldX = 0;
				battlefieldY = 0;
			}
				
            vectorX = (difX/moduleVector)*speed;
            vectorY = (difY/moduleVector)*speed;
		}
	}

}
