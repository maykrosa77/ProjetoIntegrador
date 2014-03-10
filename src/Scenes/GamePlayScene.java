package Scenes;

import Manager.Image;
import Objects.Map;
import Objects.Player;
import Objects.Units.Archer;
import Objects.Units.Soldier;
import ProjetoIntegrador.GamePanel;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.Random;

/**
* GamePlayScene, scene presentation, configuration and 
* look to start the gameplay.
* 
* @author Gregorio
* @version 1.0
*/
public class GamePlayScene extends Scene{
       
	private Player player;
    private Map map;
    
    private Random random;
    
    /** 
    * Constructor, init parameters of scene. 
    * 
    * @param GamePanel instance of gamePanel
    * @param int width of screen
    * @param int height of screen
    */
    public GamePlayScene(GamePanel gamePanel, int width, int height){
       this.gamePanel = gamePanel;
       this.width = width;
       this.height = height;
       
       this.font = gamePanel.font;
       
       /*Positions bases and battlefields in map.*/
       int[] cards = new int[5];
       cards[0] = 0;
       cards[1] = 1;
       cards[2] = 1;
       cards[3] = 1;
       cards[4] = 0;
       player = new Player(cards, this);
       
       /*Positions bases and battlefields in map.*/
       Rectangle2D[] bases = new Rectangle2D[2];
       bases[0] = new Rectangle((int)(width*0.45f), (int)(height*0.85f), (int)(width*0.1f), (int)(height*0.1f));
       bases[1] = new Rectangle((int)(width*0.45f), (int)(height*0.05f), (int)(width*0.1f), (int)(height*0.1f));
		
       Rectangle2D[] battlefields = new Rectangle2D[2];
       battlefields[0] = new Rectangle((int)(width*0.2f), (int)(height*0.45f), (int)(width*0.1f), (int)(height*0.1f));
       battlefields[1] = new Rectangle((int)(width*0.7f), (int)(height*0.45f), (int)(width*0.1f), (int)(height*0.1f));
       map = new Map(Image.terrain, 0, 0, bases, battlefields, this);
       
       random = new Random();
    }

    @Override
    public void keyPressed(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {
    	 if(e.getKeyCode() == KeyEvent.VK_ENTER){
    		 player.showCards = !player.showCards;
    	 }
    	 
     	if(player.showCards){
     		if(e.getKeyCode() == KeyEvent.VK_1){
    			player.units.add(new Soldier(Image.soldier, random.nextInt((int)(width*0.1f))+(int)(width*0.45f), (int)(height*0.75f)));
     		}else if(e.getKeyCode() == KeyEvent.VK_2){
    			player.units.add(new Archer(Image.archer,  random.nextInt((int)(width*0.1f))+(int)(width*0.45f), (int)(height*0.75f)));
     		}
    	}
    }
    @Override
    public void mouseMoved(MouseEvent e) {}    
    @Override
    public void mouseDragged(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {
    	for(int i=0; i<player.units.size(); i++){
    		player.units.get(i).stateIA = 1;
    		player.units.get(i).battlefieldX = (int)(width*0.25f);
    		player.units.get(i).battlefieldY = (int)(height*0.5f);
    	}
    }
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseClicked(MouseEvent e) {}
        
    /** 
    * Update objects of scene. 
    * 
    * @param int different time between frames
    */
    @Override
    public void update(int difTime) {
    	player.update(difTime);
    	map.update(difTime);
    }

    /** 
    * Render objects of scene.
    * 
    * @param Graphics2D graphics
    */
    @Override
    public void render(Graphics2D graphics) {
    	map.render(graphics);
    	player.render(graphics);
    }

}
