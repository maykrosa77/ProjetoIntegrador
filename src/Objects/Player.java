package Objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import Manager.Image;
import ProjetoIntegrador.StaticContent;
import Scenes.Scene;

public class Player extends Sprite{

	public int[] cards;
	public BufferedImage[] cardsImage;
	public boolean showCards;
	
	public float gold;
	public float points;
	
	private Scene parent;
	
	public Player(int[] cards, Scene parent){
		this.active = true;
		
		this.cards = cards;
		cardsImage = new BufferedImage[5];
		for(int i=0; i<cardsImage.length; i++)
			cardsImage[i] = Image.cards[cards[i]];
		showCards = false;
		
		this.gold = StaticContent.goldBase;
		this.points = 0;
		
		this.parent = parent;
	}
	
    /** 
    * Update objects of map. 
    * 
    * @param int different time between frames
    */
    @Override
    public void update(int difTime) {
    	gold += difTime/1000f;
    }

    /** 
    * Render objects of map.
    * 
    * @param Graphics2D graphics
    */
	@Override
	public void render(Graphics2D graphics) {
		
    	/*interface componets*/
        graphics.setColor(Color.orange);
        graphics.drawString(""+(int)gold, 20, 20);
        graphics.drawString(""+(int)points, 20, 40);
        
        if(showCards){
	        graphics.setColor(new Color(0, 0, 0, 150));
	        graphics.fillRect(0, (int)(parent.height*0.7f), parent.width, parent.height);
	        
	        for(int i=0; i<cards.length; i++){
	        	graphics.drawImage(cardsImage[i], i*(int)(parent.width*0.2f)+50, (int)(parent.height*0.75f), (int)(parent.width*0.1f), (int)(parent.height*0.2f), null);
	        }
        }
	}
}
