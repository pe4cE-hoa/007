package item;

import graphic.Animation;
import graphic.DataLoader;

import java.awt.*;

public class ItemManager {
    private Animation dame,heart,hp;

    public void geItemImage(){
        dame = DataLoader.getInstance().getAnimation("itemdame");
        heart = DataLoader.getInstance().getAnimation("itemheart");
        hp = DataLoader.getInstance().getAnimation("itemhp");
    }
    public void draw(ItemDame itemDame,Graphics2D g2) {
        if(itemDame.getIsExist()) {
            dame.Update(System.nanoTime());
            dame.draw(itemDame.dx_cam(), itemDame.dy_cam(), (int)itemDame.getWidth(), (int)itemDame.getHeight(), g2);
        }
    }
    public void draw(ItemHeart itemHeart,Graphics2D g2) {
        if(itemHeart.getIsExist()) {
            heart.Update(System.nanoTime());
            heart.draw(itemHeart.dx_cam(), itemHeart.dy_cam(), (int)itemHeart.getWidth(), (int)itemHeart.getHeight(), g2);
        }
    }
    public void draw(ItemHP itemHP,Graphics2D g2) {
        if(itemHP.getIsExist()) {
            hp.Update(System.nanoTime());
            hp.draw(itemHP.dx_cam(), itemHP.dy_cam(), (int)itemHP.getWidth(), (int)itemHP.getHeight(), g2);
        }
    }
}
