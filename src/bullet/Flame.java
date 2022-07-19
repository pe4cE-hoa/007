package bullet;

import state.GameWorldState;
import gameobject.ParticularObject;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import graphic.Animation;
import graphic.DataLoader;


public class Flame extends Bullet{
	
    private Animation BulletAnim;
    
    
    public Flame(float x, float y, float width, float height, int damage, GameWorldState gameWorld) {
            super(x, y, width, height, 100, gameWorld);
            setSpeedX(0);
            setSpeedY(0);
            timeExist = 1000*1000000;
            lastAttackTime = System.nanoTime();
            setDamage(5);
          
            setTeamType(ENEMY_TEAM);
    }  
    
    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        Rectangle bound = getBoundForCollisionWithMap();
        bound.x = (int) (getPosX() - 50);
        return bound;
    }


    public void Update() {
    	super.Update();
    	if(getBlood() == 0) setState(DEATH);
        setPosX(getPosX() + getSpeedX());
        setPosY(getPosY() + getSpeedY());
        if(System.nanoTime() - lastAttackTime > timeExist) setBlood(-100);
        ParticularObject object = getGameWorld().getParticularObjectManager().getCollisionWidthEnemyObject(this);
        if(object!=null && object.getState() == ALIVE){
        	setBlood(getBlood()+2*object.getDamage());
            object.setBlood(object.getBlood() - getDamage());
            object.setState(BEHURT);
        }
    }

    @Override
    public void attack() {}
	@Override
	public void shooting() {}

}
