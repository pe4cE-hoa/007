package bullet;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import graphic.Animation;
import graphic.DataLoader;
import gameobject.ParticularObject;
import state.GameWorldState;
import state.LevelState;

public class RedFlame1 extends Bullet {
	
	//private long timeExist, lastAttackTime;	
	private Animation redFlame;

	public RedFlame1(float x, float y, float width, float height, GameWorldState gameWorld) {
		super(x, y, width, height, 20, gameWorld);
		timeExist = 1000*1000000;
        lastAttackTime = System.nanoTime();
        setDamage(30);
        if(LevelState.getCurrentChoice() == 1)
        	setDamage(50);
	}
	   
    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
    	Rectangle bound = getBoundForCollisionWithMap();
    	bound.x = (int)(getPosX() - 250);
        return bound;
    }

    @Override
    public void Update() {
    	super.Update();
    	if(getBlood() == 0) setDamage(0);
        setPosX(getPosX() + getSpeedX());
        setPosY(getPosY() + getSpeedY());
        if(System.nanoTime() - lastAttackTime > timeExist) setBlood(0);
        ParticularObject object = getGameWorld().getParticularObjectManager().getCollisionWidthEnemyObject(this);
        if(object!=null && object.getState() == ALIVE){
            object.beHurt(getDamage());
        }
    }
}

