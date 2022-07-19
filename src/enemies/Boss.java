package enemies;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Hashtable;


import bullet.RedFlame1;
import bullet.RedFlame2;
import graphic.Animation;
import graphic.DataLoader;
import state.GameWorldState;
import state.LevelState;

public class Boss extends Enemy {	
    
    private long startTimeForAttacked;
    
    private Hashtable<String, Long> timeAttack = new Hashtable<String, Long>(); 
    private String[] attackType = new String[5];
    private int attackIndex = 0;
    private long lastAttackTime;

	private Animation flyRight, flyLeft, landingRight, landingLeft,
	  idleRight, idleLeft, atk1Right, atk1Left, atk2Right, atk2Left;

	public Boss(float x, float y, GameWorldState gameWorld) {
		super(x, y, 180, 120, 1500, gameWorld);
		setDamage(6);
		int temp = LevelState.getCurrentChoice();
    	if(temp == 1){
    		setBlood(3000);
    		setDamage(10);
    	}

		setTimeForImmortal(100000000);

        attackType[0] = "flying";
        attackType[1] = "landing";
        attackType[2] = "attacking1";
        attackType[3] = "NONE";
        attackType[4] = "attacking2";

        timeAttack.put("NONE", 3000l);
        timeAttack.put("landing", 500l);
        timeAttack.put("flying", 1500l);
        timeAttack.put("attacking1", 1000l);
        timeAttack.put("attacking2", 1000l);
        maxHP = getBlood();

	}

	public void Update(){
        super.Update();
        setPosX(getPosX() + getSpeedX());
        if(getGameWorld().getPlayer().getPosX() > getPosX())
            setDirection(RIGHT_DIR);
        else setDirection(LEFT_DIR);

        if(startTimeForAttacked == 0)
            startTimeForAttacked = System.currentTimeMillis();
        else if(System.currentTimeMillis() - startTimeForAttacked > 1000){
            attack();
            startTimeForAttacked = System.currentTimeMillis();
        }
        
        if(attackType[attackIndex].equals("flying")) {
    		if(getGameWorld().getPhys_map().haveCollisionWithLeftWall(getBoundForCollisionWithMap())!=null)
                setSpeedX(5);
            if(getGameWorld().getPhys_map().haveCollisionWithRightWall(getBoundForCollisionWithMap())!=null)
                setSpeedX(-5);
        }
    }
	
	@Override
	public void shooting() {}
	
	@Override
	public void attack() {       
        if(System.currentTimeMillis() - lastAttackTime > timeAttack.get(attackType[attackIndex])){
            lastAttackTime = System.currentTimeMillis();
            
            attackIndex ++;
            if(attackIndex >= attackType.length) attackIndex = 0;
            
            if(attackType[attackIndex].equals("flying")) fly();
            else if(attackType[attackIndex].equals("attacking1")) atk1();
            else if(attackType[attackIndex].equals("attacking2")) atk2();
            else setSpeedX(0);          	
        }  
    }

	public void atk1() {
		RedFlame1 redFire1 = new RedFlame1(getPosX(), getGameWorld().getPlayer().getPosY(), 500, 50, getGameWorld());
		RedFlame1 redFire2 = new RedFlame1(getPosX(), getGameWorld().getPlayer().getPosY(), 500, 50, getGameWorld());
		RedFlame1 redFire3 = new RedFlame1(getPosX(), getGameWorld().getPlayer().getPosY(), 500, 50, getGameWorld());
        if(getDirection() == LEFT_DIR) {
        	redFire1.setPosX(redFire1.getPosX()-300);
            redFire1.setPosY(redFire1.getPosY());
            
            redFire2.setPosX(redFire2.getPosX()-300);
            redFire2.setPosY(redFire2.getPosY()+100);
            
            redFire3.setPosX(redFire3.getPosX()-300);
            redFire3.setPosY(redFire3.getPosY()-100);
        } else {
        	redFire1.setPosX(redFire1.getPosX()+300);
            redFire1.setPosY(redFire1.getPosY());
            
            redFire2.setPosX(redFire2.getPosX()+300);
            redFire2.setPosY(redFire2.getPosY()+100);
            
            redFire3.setPosX(redFire3.getPosX()+300);
            redFire3.setPosY(redFire3.getPosY()-100);

        }
        redFire1.setTeamType(getTeamType());
        getGameWorld().getBulletManager().addObject(redFire1);
        
        redFire2.setTeamType(getTeamType());
        getGameWorld().getBulletManager().addObject(redFire2);
        
        redFire3.setTeamType(getTeamType());
        getGameWorld().getBulletManager().addObject(redFire3);
	}
	
	public void atk2() {
		RedFlame2[] redFire2 = new RedFlame2[8];
    	for(int i = 0; i < 8; i++) {
    		redFire2[i] = new RedFlame2(getPosX(), getPosY(), 50, 50, getGameWorld());
    	}
    	redFire2[0].setPosX(getGameWorld().getPlayer().getPosX()-50);
    	redFire2[0].setPosY(getGameWorld().getPlayer().getPosY()-50);
    	
    	redFire2[1].setPosX(getGameWorld().getPlayer().getPosX());
    	redFire2[1].setPosY(getGameWorld().getPlayer().getPosY()-50);
    	
    	redFire2[2].setPosX(getGameWorld().getPlayer().getPosX() + 50);
    	redFire2[2].setPosY(getGameWorld().getPlayer().getPosY()-50);
    	
    	redFire2[3].setPosX(getGameWorld().getPlayer().getPosX()-50);
    	redFire2[3].setPosY(getGameWorld().getPlayer().getPosY());
    	
    	redFire2[4].setPosX(getGameWorld().getPlayer().getPosX() + 50);
    	redFire2[4].setPosY(getGameWorld().getPlayer().getPosY());
    	
    	redFire2[5].setPosX(getGameWorld().getPlayer().getPosX()-50);
    	redFire2[5].setPosY(getGameWorld().getPlayer().getPosY() + 50);
    	
    	redFire2[6].setPosX(getGameWorld().getPlayer().getPosX());
    	redFire2[6].setPosY(getGameWorld().getPlayer().getPosY() + 50);
    	
    	redFire2[7].setPosX(getGameWorld().getPlayer().getPosX() + 50);
    	redFire2[7].setPosY(getGameWorld().getPlayer().getPosY() + 50);
    	
    	for(int i = 0; i < 8; i++) {
    		redFire2[i].setTeamType(ENEMY_TEAM);
    		getGameWorld().getBulletManager().addObject(redFire2[i]);
    	}
	}
	
	public void fly() {
		if(dx() < 0) {
			setSpeedX(5);
			setDirection(RIGHT_DIR);
		} else {
        	setSpeedX(-5);
        	setDirection(LEFT_DIR);
        }
	}

	@Override
	public Rectangle getBoundForCollisionWithEnemy() {
		Rectangle rect = super.getBoundForCollisionWithMap();
		rect.width -= 50;
		rect.height -= 10;
		rect.x += 40;
		rect.y += 10;
		return rect;
	}


	@Override
	public void run() {} 
	public int getAttackIndex(){
		return attackIndex;
	}
	public String getAttackType(int attackIndex){
		return attackType[attackIndex];
	}
}
