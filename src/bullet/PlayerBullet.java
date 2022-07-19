package bullet;

import state.GameWorldState;
import gameobject.ParticularObject;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import graphic.Animation;
import graphic.DataLoader;


public class PlayerBullet extends Bullet{
	
    private Animation BulletAnim;

    public PlayerBullet(float x, float y, GameWorldState gameWorld) {
        super(x, y, 20, 20, 60, gameWorld);
        timeExist = 150*1000000;
        lastAttackTime = System.nanoTime();
    }    
    
    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        return getBoundForCollisionWithMap();
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
            setBlood(0);
            object.setBlood(object.getBlood() - getDamage());
            object.setState(BEHURT);
            System.out.println("Bullet set behurt for enemy");
        }
    }

    @Override
    public void attack() {}
    
	@Override
	public void shooting() {}
}
