
package enemies;

import state.GameWorldState;
import state.LevelState;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import graphic.Animation;
import graphic.DataLoader;



public class Bull extends Enemy {
	
	int x = 0;
    private long lastAttackTime;
	
    private boolean isAttack = false;
    private boolean isRuning = false;

    private long startTimeToAttack;      
    
    private float x1, x2;
    
    public Bull(float x, float y, GameWorldState gameWorld) {
        super(x, y, 80, 80, 150, gameWorld);
        int temp = LevelState.getCurrentChoice();
    	if (temp == 0) {
    		setBlood(150);
    		
    	} else if (temp == 1){ 
    		setBlood(300);
    	}

        startTimeToAttack = 0;
        if (temp == 0) {
			setDamage(6);     		
    	} else if (temp == 1){ 
    		setDamage(8);
    	}
        setTimeForImmortal(100000000);

        x1 = getPosX() - 50;
        x2 = getPosX() + 50;
        maxHP = getBlood();
        setSpeedX(1);
    }
    
    public void teleport() {
    	if(getGameWorld().getPlayer().getDirection() == LEFT_DIR) {
    		setPosX(getGameWorld().getPlayer().getPosX()+100);
    		setPosY(getGameWorld().getPlayer().getPosY());
    		setDirection(LEFT_DIR);
    	}
    	else if(getGameWorld().getPlayer().getDirection() == RIGHT_DIR) {
    		setPosX(getGameWorld().getPlayer().getPosX()-100);
    		setPosY(getGameWorld().getPlayer().getPosY());
    		setDirection(RIGHT_DIR);
    	}
    	else if(getGameWorld().getPlayer().getDirection() == UP_DIR) {
    		setPosY(getGameWorld().getPlayer().getPosY()+100);
    		setPosX(getGameWorld().getPlayer().getPosX());
    		setDirection(UP_DIR);
    	}
    	else {
    		setPosY(getGameWorld().getPlayer().getPosY()-100);
    		setPosX(getGameWorld().getPlayer().getPosX());
    		setDirection(DOWN_DIR);
    	}
    }
    
    public void run2() {
    	if(getPosX() < x1) {
            setSpeedX(1);
            setDirection(RIGHT_DIR);
            isRuning = true;
    	}
        else if(getPosX() > x2) {
            setSpeedX(-1);
            setDirection(LEFT_DIR);   
            isRuning = true;
        }
        setPosX(getPosX() + getSpeedX());
    }
    
    public void stopRun() {
    	isRuning = false;
    	setSpeedX(0);
    	setSpeedY(0);
    }
    
    public void run() {
    	isAttack = false;
    	isRuning = true;
    	System.out.println("run");
    	if(dx() == 0 && dy() > 0) {
    		setSpeedY(-1);
    		setDirection(UP_DIR); 
            setPosY(getPosY() + getSpeedY());
    	}
    	else if(dx() == 0 && dy() < 0) {   		
    		setSpeedY(1);
    		setDirection(DOWN_DIR);
            setPosY(getPosY() + getSpeedY());
    	}   	
    	else if(dx() < - getGameWorld().getPlayer().getWidth()
    		 && dy() == 0) {
    		setSpeedX(1);
    		setDirection(RIGHT_DIR);
    		setPosX(getPosX() + getSpeedX());
    	}
    	else if(dx() > getGameWorld().getPlayer().getWidth()
    		 && dy() == 0) {
    		setSpeedX(-1);
    		setDirection(LEFT_DIR);
    		setPosX(getPosX() + getSpeedX());
    	}
    	else if(dy() > 0) {
    		setSpeedY(-1);
    		setDirection(UP_DIR);
            setPosY(getPosY() + getSpeedY());
    	}
    	else if(dy() < 0) {
    		setSpeedY(1);
    		setDirection(DOWN_DIR);
            setPosY(getPosY() + getSpeedY());
    	}
    	else if(dx()*dx() + dy()*dy() <= 2500  
             && (dy() == 0 || dx() == 0 || haveCollisionWithMap() == true)) stopRun();
    }
    
    @Override
    public void attack() {  

		System.out.println("attack2");
    	isAttack = true;
    	lastAttackTime = System.nanoTime();
    	getGameWorld().getPlayer().beHurt(getDamage());
	}   
    
    public void Update(){
        super.Update(); 
        
        if(isAttack) {
        	if(System.nanoTime() - lastAttackTime > 1000*1000000) {		
        		isAttack = false;       		
        	}
        }
        
        if((dx()*dx() + dy() * dy()) >= 50000) run2();
        
        if( (dx()*dx() + dy()*dy() < 50000) && (dx()*dx() + dy()*dy() > 2500 ) ) {
        	if(haveCollisionWithMap()) {
        		stopRun();
        	}
        	else run();
        }
        
        if( (dx()*dx() + dy()*dy() <= 2500)
            && (System.nanoTime() - startTimeToAttack > 1000*10000000*1.5) ){   
        	stopRun();
        	attack();
        	x++;
            startTimeToAttack = System.nanoTime(); 
        }
        
        if( (dx()*dx() + dy()*dy() <= 20000) && (x == 2) && getGameWorld().getPlayer().getIsAttack() == true) {
        	teleport();
        	isAttack = false;
        	x = 0;
        }
    }
    
    @Override
    public Rectangle getBoundForCollisionWithEnemy() {               
        Rectangle bound = new Rectangle();
        bound.x = (int)(getPosX() - 10);
        bound.y = (int)(getPosY() - 20);
        bound.height = (int)(getHeight()-30);
        bound.width = (int)(getWidth()-40);
        return bound;
    }


	@Override
	public void shooting() {
	}
	public boolean getIsRunning(){
		return isRuning;
	}
	public boolean getIsAttack(){
		return isAttack;
	}
	public int getMaxHP() {
		return maxHP;
	}
}
