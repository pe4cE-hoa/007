package bullet;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import graphic.Animation;
import graphic.DataLoader;
import gameobject.ParticularObject;
import state.GameWorldState;

public class LittleGirlBullet extends Bullet {

	private Animation BulletAnim;    

    public LittleGirlBullet(float x, float y, float width, float height, int damage, GameWorldState gameWorld) {
            super(x, y, width, height, 100, gameWorld);
            setSpeedX(0);
            setSpeedY(0);
            timeExist = 1000*1000000;
            lastAttackTime = System.nanoTime();
            setDamage(5);
    }  
    
    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
    	Rectangle bound = getBoundForCollisionWithMap();
        return bound;
    }

    public void Update() {
    	super.Update();
    	if(getBlood() == 0) setDamage(0);
        setPosX(getPosX() + getSpeedX());
        setPosY(getPosY() + getSpeedY());
        if(System.nanoTime() - lastAttackTime > timeExist) setBlood(-100);
        ParticularObject object = getGameWorld().getParticularObjectManager().getCollisionWidthEnemyObject(this);
        if(object!=null && object.getState() == ALIVE){
            object.setBlood(object.getBlood() - getDamage());
            object.setState(BEHURT);
        }
    }

    @Override
    public void attack() {}
	@Override
	public void shooting() {}

}


