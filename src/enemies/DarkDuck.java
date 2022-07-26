package enemies;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import bullet.DuckBullet;
import graphic.Animation;
import graphic.DataLoader;
import state.GameWorldState;
import state.LevelState;

public class DarkDuck extends Enemy {
	
	    private int x1, x2, y1, y2;


	    private boolean isShooting = false;
	    private boolean isRuning = false;
	    private long lastShootingTime;
	    private long startTimeToShooting;
		
		public DarkDuck (float x, float y, GameWorldState gameWorld) {
			super(x, y, 32, 32, 100, gameWorld);

	        
			setTimeForImmortal(100000000);
			int temp = LevelState.getCurrentChoice();
	    	if (temp == 0) {
				setDamage(2);     		
	    	} else if (temp == 1){ 
	    		setDamage(4);
	    	}	        
	        maxHP = getBlood();
		}
		
		public void runBack() {
			if(getPosX() == (getX1()+getX2())/2 && getPosY() == (getY1()+getY2())/2) stopRun();
			else if(getPosX() == (getX1()+getX2())/2 && getPosY() > (getY1()+getY2())/2) {
				setSpeedY(-1);
				setDirection(UP_DIR);
				setPosY(getPosY() + getSpeedY());
			}
			else if(getPosX() == (getX1()+getX2())/2 && getPosY() < (getY1()+getY2())/2) {
				setSpeedY(1);
				setDirection(DOWN_DIR);
				setPosY(getPosY() + getSpeedY());
			}
			else if(getPosX() > (getX1()+getX2())/2) {
				setSpeedX(-1);
				setDirection(LEFT_DIR);
				setPosX(getPosX() + getSpeedX());
			}
			else if(getPosX() < (getX1()+getX2())/2) {
				setSpeedX(1);
				setDirection(RIGHT_DIR);
				setPosX(getPosX() + getSpeedX());
			}			
		}
		
		@Override
		public void attack() {}

		@Override
		public void shooting() {
			if(!isShooting) {
			    DuckBullet[] bullet  = new DuckBullet[8];
			    for(int i = 0; i < 8; i++) {
			    	bullet[i] = new DuckBullet(getPosX(), getPosY(), getGameWorld());
			    	if(LevelState.getCurrentChoice() == 1)
			    		bullet[i].setDamage(10);
				}
			    bullet[0].setSpeedX(-10);
				bullet[0].setPosX(getPosX() - 40);
				
				bullet[2].setSpeedY(10);
				bullet[2].setPosY(getPosY() + 40);
				
				bullet[4].setSpeedX(10);
				bullet[4].setPosX(getPosX() + 40);
				
				bullet[6].setSpeedY(-10);
				bullet[6].setPosY(getPosY() - 40);

				bullet[1].setSpeedX(-10);
				bullet[1].setSpeedY(10);
				bullet[1].setPosX(getPosX() - 28);
				bullet[1].setPosY(getPosY() + 28);
				
				
				bullet[3].setSpeedX(10);
				bullet[3].setSpeedY(10);
				bullet[3].setPosX(getPosX() + 28);
				bullet[3].setPosY(getPosY() + 28);
				
				bullet[5].setSpeedX(10);
				bullet[5].setSpeedY(-10);
				bullet[5].setPosX(getPosX() + 28);
				bullet[5].setPosY(getPosY() - 28);
				
				bullet[7].setSpeedX(-10);
				bullet[7].setSpeedY(-10);
				bullet[7].setPosX(getPosX() - 28);
				bullet[7].setPosY(getPosY() - 28);
					
				for(int i = 0; i < 8; i++) {
					bullet[i].setTeamType(ENEMY_TEAM);
					getGameWorld().getBulletManager().addObject(bullet[i]);
				}
			}			
			
			isShooting = true;
			lastShootingTime = System.nanoTime();
		}
	    
		@Override
	    public void run() {
	    	//System.out.println(getPosX()+" "+getPosY());
	    	isRuning = true;
	    	
			if((dx()*dx() + dy()*dy()) <= 20000 && (dx() == 0 || dy() == 0) 
												&& (dx()*dx() + dy()*dy()) >= 15000) {
				stopRun();
			}
			
			if(dx() == 0 && dy() > 0) {
				setDirection(UP_DIR); 
				setSpeedY(-1);
				setPosY(getPosY() + getSpeedY());
			}
			else if(dx() == 0 && dy() < 0) {
				setDirection(DOWN_DIR); 
				setSpeedY(1);
				setPosY(getPosY() + getSpeedY());
			}
			else if(dy() == 0 && dx() > 0) {
				setDirection(LEFT_DIR);  
				setSpeedX(-1);
				setPosX(getPosX() + getSpeedX());
			}
			else if(dy() == 0 && dx() < 0) {
				setDirection(RIGHT_DIR); 
				setSpeedX(1);
				setPosX(getPosX() + getSpeedX());
			}
			else if(dx() != 0 && dy() != 0) { 
				if(Math.abs(dx()) > Math.abs(dy())) { 
					if(dy() > 0) {
						setDirection(UP_DIR);
						setSpeedY(-1);
						setPosY(getPosY() + getSpeedY());
					} else {
						setDirection(DOWN_DIR);
						setSpeedY(1);
						setPosY(getPosY() + getSpeedY());
					}
				} else {
					if(dx() > 0) {
						setDirection(LEFT_DIR); 
						setSpeedX(-1);
						setPosX(getPosX() + getSpeedX());
					}
					else {
						setDirection(RIGHT_DIR);
						setSpeedX(1);
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
	    	
	    	if(dx()*dx() + dy()*dy() >= 50000 
	   			 || getPosX() > getX2()
	   			 || getPosX() < getX1()
	   			 || getPosY() < getY1()
	   			 || getPosY() > getY2()) {
	   				runBack();
	   			}
	    	
	         if((dx()*dx() + dy()*dy() < 50000)
	          && (dx()*dx() + dy()*dy() > 20000)
	          && getPosX() < getX2()
	   		  && getPosX() >= getX1()
	   		  && getPosY() >= getY1()
	   		  && getPosY() < getY2()) {        	
	         	run();
	         }
	         
	         if(isShooting) {
	         	if(System.nanoTime() - lastShootingTime > 500*1000000) {		
	         		isShooting = false;       		
	         	}
	         }
	         
         	if ((dx()*dx() + dy()*dy() <= 20000) && System.nanoTime() - startTimeToShooting > 1000*1000000) {
 	        	stopRun();
 	        	shooting();
 	            startTimeToShooting = System.nanoTime();
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

	public boolean getIsShooting() {
		return isShooting;
	}
	public int getMaxHP(){
			return maxHP;
	}



		public void setXY(int x1, int x2, int y1, int y2) {
	    	this.x1 = x1;
	    	this.x2 = x2;
	    	this.y1 = y1;
	    	this.y2 = y2;
	    }
	    
	    public int getX1() {
			return x1;
		}

		public int getX2() {
			return x2;
		}

		public int getY1() {
			return y1;
		}

		public int getY2() {
			return y2;
		}
}
