package player;

import state.GameWorldState;
import state.LevelState;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import bullet.PlayerBullet;
import bullet.Sword;
import graphic.Animation;
import graphic.DataLoader;

public class Player extends Human {

	private int count = 0;
	
    public static final int RUNSPEED = 2;

 //   private Animation runForwardAnim, runBackAnim, runUpAnim, runDownAnim;
 //   private Animation idleForwardAnim, idleUpAnim, idleDownAnim, idleBackAnim,
  //  idleShootingForwardAnim, idleShootingBackAnim, idleShootingUpAnim, idleShootingDownAnim,
  //  idleAttackForwardAnim, idleAttackBackAnim, idleAttackUpAnim, idleAttackDownAnim;

    private long lastShootingTime;
    private long lastAttackTime;
    private long lastHealTime;
    private boolean isShooting = false;
    private boolean isAttack = false;
    private boolean isRunning = false;
    private int numberOfLife;
    private int currentDame;
    public Player(float x, float y, GameWorldState gameWorld) {
        super(x, y, 50, 50, 300, gameWorld);
        int temp = LevelState.getCurrentChoice();
    	if (temp == 1){ 
    		setBlood(200);
    	}
        maxHP = getBlood();
        setTimeForImmortal(200000000);        
        setDamage(0);
        setTeamType(LEAGUE_TEAM);
        numberOfLife = 3;
        setCurrentDame(10000);
    }
    


    @Override
    public void Update() {
        super.Update();        
        heal();
        System.out.println(getPosX() + ", " + getPosY());
        if(isAttack) {
        	if(System.nanoTime() - lastAttackTime > 500*1000000) {		
        		isAttack = false;
        	}
        }
        
        if(isShooting){
            if(System.nanoTime() - lastShootingTime > 5000*1000000){
                isShooting = false;
            }
        }
        
    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {    	
        Rectangle rect = getBoundForCollisionWithMap();
        rect.height -= 8;
        rect.width -= 16;
        rect.x += 11;
        rect.y += 10;
        return rect;   
    }


    @Override
    public void run() {
    	isRunning = true;
        if (getDirection() == LEFT_DIR) setSpeedX(-RUNSPEED);
        else if (getDirection() == RIGHT_DIR) setSpeedX(RUNSPEED);
        else if (getDirection() == UP_DIR) setSpeedY(-RUNSPEED);
        else setSpeedY(RUNSPEED);
    }

    @Override
    public void stopRun() {
    	isRunning = false;
        setSpeedX(0);
        setSpeedY(0);
    }

    @Override
    public void shooting() {
    
        if(!isShooting && count >= 5){
        	System.out.println("shoot1");
            PlayerBullet bullet = new PlayerBullet(getPosX(), getPosY(), getGameWorld());
            if(getDirection() == LEFT_DIR) {
                bullet.setSpeedX(-10);
                bullet.setPosX(bullet.getPosX() - 40);
                
            }else if(getDirection() == RIGHT_DIR){
                bullet.setSpeedX(10);
                bullet.setPosX(bullet.getPosX() + 40);
                
            }else if(getDirection() == UP_DIR) {
            	bullet.setSpeedY(-10);
                bullet.setPosY(bullet.getPosY() - 40);
                
            }else {
            	bullet.setSpeedY(10);
                bullet.setPosY(bullet.getPosY() + 40);
                
            }
            
            bullet.setTeamType(getTeamType());
            getGameWorld().getBulletManager().addObject(bullet);
            
            lastShootingTime = System.nanoTime();
            isShooting = true;
            count = 0;
            
        }
    
    }

    @Override
    public void attack() {
		if(!isAttack){
			System.out.println("attack1");
			
            Sword sword = new Sword(getPosX(), getPosY(), getGameWorld());
            sword.setDamage(currentDame);
            if(sword != null) {
            	System.out.println("Count = " + getGameWorld().getParticularObjectManager().getCount() + "***" + "currDame = " + currentDame);
            }
            if(getDirection() == LEFT_DIR) {
            	sword.setPosX(sword.getPosX() - 5);
                
            }else if(getDirection() == RIGHT_DIR){
            	sword.setPosX(sword.getPosX() + 5);
                
            }else if(getDirection() == UP_DIR){
            	sword.setPosY(sword.getPosY() - 5);
                
            }else {
            	sword.setPosY(sword.getPosY() + 5);
                
            }
            
            sword.setTeamType(getTeamType());
            getGameWorld().getBulletManager().addObject(sword);
            
            lastAttackTime = System.nanoTime();
            isAttack = true;
            count++;
		}
    }

    public void heal() {
    	if(System.nanoTime() - lastHealTime > 300000000
    	 && getBlood() < 100) {
    		setBlood(getBlood() + 1);
        	lastHealTime = System.nanoTime();
    	}

    }

	public int getNumberOfLife() {
		return numberOfLife;
	}

	public void setNumberOfLife(int numberOfLife) {
		this.numberOfLife = numberOfLife;
	}

	public int getCurrentDame() {
		return currentDame;
	}

	public void setCurrentDame(int currentDame) {
		this.currentDame = currentDame;
	}

    public long getLastShootingTime(){
        return lastShootingTime;
    }
    public void setLastShootingTime(long lastShootingTime){
        this.lastShootingTime = lastShootingTime;
    }

    public long getLastAttackTime(){
        return lastShootingTime;
    }
    public void setLastAttackTime(long lastAttackTime){
        this.lastAttackTime = lastAttackTime;
    }

    public long getLastHealTime(){
        return lastHealTime;
    }
    public void setLastHealTime(long lastHealTime){
        this.lastHealTime = lastHealTime;
    }

    public boolean getIsShooting(){
        return isShooting;
    }
    public void setIsShooting(boolean isShooting){
        this.isShooting = isShooting;
    }
    public boolean getIsAttack() {
        return isAttack;
    }

    public void setIsAttack(boolean isAttack){
        this.isAttack = isAttack;
    }

    public boolean getIsRunning(){
        return isRunning;
    }
    public void setIsRunning(boolean isRunning){
        this.isRunning = isRunning;
    }
}