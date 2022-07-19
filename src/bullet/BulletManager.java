package bullet;

import gameobject.ParticularObject;
import gameobject.ParticularObjectManager;
import graphic.Animation;
import graphic.DataLoader;
import state.GameWorldState;

import java.awt.*;

public class BulletManager extends ParticularObjectManager {
    private Animation DuckBullet,Flame,LittleGirlBullet,PlayerBullet,RedFlame1,RedFlame2;

    public BulletManager(GameWorldState gameWorld) {
        super(gameWorld);
    }

    @Override
    public void UpdateObjects() {
        super.UpdateObjects(); 
        synchronized(particularObjects){
            for(int id = 0; id < particularObjects.size(); id++){
                
                ParticularObject object = particularObjects.get(id);
                
                if(object.isObjectOutOfCameraView() || object.getState() == ParticularObject.DEATH){
                    particularObjects.remove(id);
                }
            }
        }
    }   
    public void getBulletImage(){
        DuckBullet = DataLoader.getInstance().getAnimation("bullet03");
        Flame = DataLoader.getInstance().getAnimation("bullet04_enemy4");
        LittleGirlBullet = DataLoader.getInstance().getAnimation("bullet05");
        PlayerBullet = DataLoader.getInstance().getAnimation("bullet01");
        RedFlame1= DataLoader.getInstance().getAnimation("bullet06");
        RedFlame2 = DataLoader.getInstance().getAnimation("bullet07");
    }
    public void draw(DuckBullet duckBullet,Graphics2D g2) {
        if(duckBullet.getBlood() == 0) return ;
        DuckBullet.Update(System.nanoTime());
        DuckBullet.draw((int) (duckBullet.getPosX() - duckBullet.getGameWorld().getCamera().getPosX()),
                (int) duckBullet.getPosY() - (int) duckBullet.getGameWorld().getCamera().getPosY(),
                (int) duckBullet.getWidth(), (int) duckBullet.getHeight(), g2);
    }
    public void draw(Flame flame,Graphics2D g2) {
        if(flame.getBlood()==0) return;
        Flame.Update(System.nanoTime());
        Flame.draw(flame.dx_cam(), flame.dy_cam(),(int) flame.getWidth(), (int) flame.getHeight(), g2);
    }
    public void draw(LittleGirlBullet littleGirlBullet,Graphics2D g2) {
        if(littleGirlBullet.getBlood()==0) return;
        LittleGirlBullet.Update(System.nanoTime());
        LittleGirlBullet.draw(littleGirlBullet.dx_cam(), littleGirlBullet.dy_cam(),(int) littleGirlBullet.getWidth(), (int) littleGirlBullet.getHeight(), g2);
    }
    public void draw(PlayerBullet playerBullet,Graphics2D g2) {
        if(playerBullet.getBlood() == 0) return;
        PlayerBullet.Update(System.nanoTime());
        PlayerBullet.draw(playerBullet.dx_cam(), playerBullet.dy_cam(), (int) playerBullet.getWidth(), (int) playerBullet.getHeight(), g2);
    }
    public void draw(RedFlame1 redFlame1,Graphics2D g2) {
        if(redFlame1.getBlood()==0) return;
        RedFlame1.Update(System.nanoTime());
        RedFlame1.draw(redFlame1.dx_cam(), redFlame1.dy_cam(), (int)redFlame1.getWidth(), (int)redFlame1.getHeight(), g2);
    }
    public void draw(RedFlame2 redFlame2,Graphics2D g2) {
        if(redFlame2.getBlood()==0) return;
        RedFlame2.Update(System.nanoTime());
        RedFlame2.draw(redFlame2.dx_cam(), redFlame2.dy_cam(), (int)redFlame2.getWidth(), (int)redFlame2.getHeight(), g2);
    }
}
