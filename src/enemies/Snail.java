package enemies;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import graphic.Animation;
import graphic.DataLoader;
import state.GameWorldState;
import state.LevelState;

public class Snail extends Enemy {

    private boolean isAttack = false;
    private boolean isRuning = false;
    private long lastAttackTime;
    private long startTimeToAttack;
    private float x1, x2;
	
	public Snail(float x, float y, GameWorldState gameWorld) {
		super(x, y, 40, 40, 125, gameWorld);
		setTimeForImmortal(100000000);
		int temp = LevelState.getCurrentChoice();
    	if (temp == 0) {
			setDamage(2);     		
    	} else if (temp == 1){ 
    		setDamage(4);
    	}
        maxHP = getBlood();
        x1 = getPosX() - 50;
        x2 = getPosX() + 50;
        setSpeedX(1);
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

	@Override
	public void attack() {
		isAttack = true;
		stopRun();
		System.out.println("take dame by Snail!");

		getGameWorld().getPlayer().beHurt(getDamage());
		lastAttackTime = System.nanoTime();
	}

	@Override
	public void shooting() {}
    @Override
    public void run() {
    	isRuning = true;
    	
		if((dx()*dx() + dy()*dy()) <= 5000 && (dx() == 0 || dy() == 0) && (dx()*dx() + dy()*dy()) >= 400) {
			stopRun();
		}
		if(dx() == 0 && dy() > 0) {
			setDirection(UP_DIR); 
			setSpeedY(-0.4f);
			setPosY(getPosY() + getSpeedY());
		}
		else if(dx() == 0 && dy() < 0) {
			setDirection(DOWN_DIR); 
			setSpeedY(0.4f);
			setPosY(getPosY() + getSpeedY());
		}
		else if(dy() == 0 && dx() > 0) {
			setDirection(LEFT_DIR);  
			setSpeedX(-0.4f);
			setPosX(getPosX() + getSpeedX());
		}
		else if(dy() == 0 && dx() < 0) {
			setDirection(RIGHT_DIR); 
			setSpeedX(0.4f);
			setPosX(getPosX() + getSpeedX());
		}
		else if(dx() != 0 && dy() != 0) { 
			if(Math.abs(dx()) > Math.abs(dy())) { 
				if(dy() > 0) {
					setDirection(UP_DIR);
					setSpeedY(-0.4f);
					setPosY(getPosY() + getSpeedY());
				}
					
				else {
					setDirection(DOWN_DIR);
					setSpeedY(0.4f);
					setPosY(getPosY() + getSpeedY());
				}
			}
			else {
				if(dx() > 0) {
					setDirection(LEFT_DIR);
					setSpeedX(-0.4f);
					setPosX(getPosX() + getSpeedX());
				}
				else {
					setDirection(RIGHT_DIR); 
					setSpeedX(0.4f);
					setPosX(getPosX() + getSpeedX());
				}
			}
		}		
	}
	
    public void stopRun() {
		isRuning = false;
		setSpeedX(0);
		setSpeedY(0);
	}
    
    @Override
    public void Update() {
    	super.Update();
        
        if((dx()*dx() + dy() * dy()) >= 50000) run2();
         if((dx()*dx() + dy()*dy() < 50000)
          && (dx()*dx() + dy()*dy() > 2000)) {        	
         	run();
         }
         
         if(isAttack) {
         	if(System.nanoTime() - lastAttackTime > 500*1000000) {		
         		isAttack = false;       		
         	}
         }
         
     	if( (dx()*dx() + dy()*dy() <= 2000) && System.nanoTime() - startTimeToAttack > 1000*1000000) {
        	stopRun();
        	attack();
            startTimeToAttack = System.nanoTime();              
        }
         
         
    }

	@Override
	public Rectangle getBoundForCollisionWithEnemy() {
		Rectangle rect = getBoundForCollisionWithMap();
		return rect;
	}

	public boolean getIsRunning() {
		return isRuning;
	}

	public boolean getIsAttack() {
		return isAttack;
	}
	public int getMaxHP(){
		return maxHP;
	}
}
