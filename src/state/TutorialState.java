package state;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import display.GameFrame;
import display.GamePanel;

public class TutorialState extends State{
	private BufferedImage bufferedImage;
    private Graphics graphicsPaint;
	private int currentChoice = -1;
		
	private Font font = new Font("Arial", Font.PLAIN, 20);
	private String[] options = {
            "Menu"
			/*"Menu",
			"Quit"*/
		};
		
	public TutorialState(GamePanel gamePanel) {
        super(gamePanel); 
        bufferedImage = new BufferedImage(GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);    
    }
	
    @Override
    public void Render() {
    	if(bufferedImage == null) { 
            bufferedImage = new BufferedImage(GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
            return;
        }
        
        graphicsPaint = bufferedImage.getGraphics();     
        if(graphicsPaint == null) { 
            graphicsPaint = bufferedImage.getGraphics();
            return;
        }

        Image help = Toolkit.getDefaultToolkit().getImage("data/tutorialState.jpg");
        graphicsPaint.drawImage(help, 0, 0, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT, null);
		graphicsPaint.setFont(font);
		for(int i = 0; i < options.length; i++) {
			if(i == currentChoice) {
				graphicsPaint.setColor(Color.YELLOW);
			} else {
				graphicsPaint.setColor(Color.WHITE);
			}
			graphicsPaint.drawString(options[i], GameFrame.SCREEN_WIDTH/2 + 350, 500 + i * 30);
		}
    }
    
    @Override
    public void Update() {}

    @Override
    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    @Override
    public void setPressedButton(int code) {
        switch(code) {
        
            case KeyEvent.VK_S:
                currentChoice++;
                if(currentChoice >= options.length) {
                    currentChoice = 0;
                }
                break;
                
            case KeyEvent.VK_W:
                currentChoice--;
                if(currentChoice < 0) {
                    currentChoice = options.length - 1;
                }
                break;
                
            case KeyEvent.VK_ENTER: 
                actionMenu();
                break;
        }
    }
    
    @Override
    public void setReleasedButton(int code) {}
    
    @Override
	public void actionMenu() {
        switch(currentChoice) {
            case 0:
                gamePanel.setState(new MenuState(gamePanel)); 
                break; 
//            case 1:
//            	System.exit(1);
        }
    }
}
