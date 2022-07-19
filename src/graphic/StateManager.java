package graphic;

import player.Player;
import enemies.*;

import java.awt.*;
import java.util.Hashtable;

import static gameobject.ParticularObject.*;

public  class StateManager {

    private Animation runForwardAnimP, runBackAnimP, runUpAnimP, runDownAnimP,
            idleForwardAnimP, idleUpAnimP, idleDownAnimP, idleBackAnimP,
            idleShootingForwardAnimP, idleShootingBackAnimP, idleShootingUpAnimP, idleShootingDownAnimP,
            idleAttackForwardAnimP, idleAttackBackAnimP, idleAttackUpAnimP ,idleAttackDownAnimP,

            flyRightB, flyLeftB, landingRightB, landingLeftB,
            idleRightB, idleLeftB, atk1RightB, atk1LeftB, atk2RightB, atk2LeftB,

            runUpD, runDownD, runLeftD, runRightD,
            idleUpD, idleDownD,idleRightD,idleLeftD,
            atkXuoiD, atkNguocD,

            runUpL, runDownL,runLeftL,runRightL,
            idleLeftL,idleRightL,idleUpL,idleDownL,

            idle,

            runUpS,runDownS,runLeftS,runRightS,
            idleLeftS,idleRightS,idleUpS,idleDownS,
            atkLeftS,atkRightS,atkUpS,atkDownS ,

            runForwardAnimB, runBackAnimB, runUpAnimB, runDownAnimB,
            idleForwardAnimB, idleUpAnimB, idleDownAnimB, idleBackAnimB,

            idleAttackForwardAnimB, idleAttackBackAnimB, idleAttackUpAnimB ,idleAttackDownAnimB,
            deathBull;

    private Hashtable<String, Long> timeAttack = new Hashtable<String, Long>();
    private String[] attackType = new String[5];




    public  void getPlayerImage() {

        runForwardAnimP = DataLoader.getInstance().getAnimation("player_runright");
        runBackAnimP = DataLoader.getInstance().getAnimation("player_runleft");
        runUpAnimP = DataLoader.getInstance().getAnimation("player_runup");
        runDownAnimP = DataLoader.getInstance().getAnimation("player_rundown");

        idleForwardAnimP = DataLoader.getInstance().getAnimation("player_idleright");
        idleBackAnimP = DataLoader.getInstance().getAnimation("player_idleleft");
        idleUpAnimP = DataLoader.getInstance().getAnimation("player_idleup");
        idleDownAnimP = DataLoader.getInstance().getAnimation("player_idledown");

        idleShootingForwardAnimP = DataLoader.getInstance().getAnimation("player_atkright_2");
        idleShootingBackAnimP = DataLoader.getInstance().getAnimation("player_atkleft_2");
        idleShootingUpAnimP = DataLoader.getInstance().getAnimation("player_atkup_2");
        idleShootingDownAnimP = DataLoader.getInstance().getAnimation("player_atkdown_2");

        idleAttackForwardAnimP = DataLoader.getInstance().getAnimation("player_atkright");
        idleAttackBackAnimP = DataLoader.getInstance().getAnimation("player_atkleft");
        idleAttackUpAnimP = DataLoader.getInstance().getAnimation("player_atkup");
        idleAttackDownAnimP= DataLoader.getInstance().getAnimation("player_atkdown");
    }
    public void draw (Player Player, Graphics2D g2){
            switch (Player.getState()) {
                case ALIVE:

                case IMMORTAL:
                    if (Player.getDirection() == RIGHT_DIR) {
                        if (Player.getIsRunning()) {
                            runForwardAnimP.Update(System.nanoTime());
                            runForwardAnimP.draw(Player.dx_cam(), Player.dy_cam(), (int) Player.getWidth(), (int) Player.getHeight(), g2);
                            if (runForwardAnimP.getcurrentImageIndex() == 1) runForwardAnimP.setIgnoreImage(0);
                        } else if (Player.getIsAttack()) {
                            idleAttackForwardAnimP.Update(System.nanoTime());
                            idleAttackForwardAnimP.draw(Player.dx_cam(), Player.dy_cam(), (int) Player.getWidth(), (int) Player.getHeight(), g2);
                        } else if (Player.getIsShooting()) {
                            idleShootingForwardAnimP.Update(System.nanoTime());
                            idleShootingForwardAnimP.draw(Player.dx_cam(), Player.dy_cam(), (int) Player.getWidth(), (int) Player.getHeight(), g2);
                        } else {
                            idleForwardAnimP.Update(System.nanoTime());
                            idleForwardAnimP.draw(Player.dx_cam(), Player.dy_cam(), (int) Player.getWidth(), (int) Player.getHeight(), g2);
                        }
                    } else if (Player.getDirection() == LEFT_DIR) {
                        if (Player.getIsRunning()) {
                            runBackAnimP.Update(System.nanoTime());
                            runBackAnimP.draw(Player.dx_cam(), Player.dy_cam(), (int) Player.getWidth(), (int) Player.getHeight(), g2);
                            if (runBackAnimP.getcurrentImageIndex() == 1) runBackAnimP.setIgnoreImage(0);
                        } else if (Player.getIsAttack()) {
                            idleAttackBackAnimP.Update(System.nanoTime());
                            idleAttackBackAnimP.draw(Player.dx_cam(), Player.dy_cam(), (int) Player.getWidth(), (int) Player.getHeight(), g2);
                        } else if (Player.getIsShooting()) {
                            idleShootingBackAnimP.Update(System.nanoTime());
                            idleShootingBackAnimP.draw(Player.dx_cam(), Player.dy_cam(), (int) Player.getWidth(), (int) Player.getHeight(), g2);
                        } else {
                            idleBackAnimP.Update(System.nanoTime());
                            idleBackAnimP.draw(Player.dx_cam(), Player.dy_cam(), (int) Player.getWidth(), (int) Player.getHeight(), g2);
                        }
                    } else if (Player.getDirection() == UP_DIR) {
                        if (Player.getIsRunning()) {
                            runUpAnimP.Update(System.nanoTime());
                            runUpAnimP.draw(Player.dx_cam(), Player.dy_cam(), (int) Player.getWidth(), (int) Player.getHeight(), g2);
                            if (runUpAnimP.getcurrentImageIndex() == 1) runUpAnimP.setIgnoreImage(0);
                        } else if (Player.getIsAttack()) {
                            idleAttackUpAnimP.Update(System.nanoTime());
                            idleAttackUpAnimP.draw(Player.dx_cam(), Player.dy_cam(), (int) Player.getWidth(), (int) Player.getHeight(), g2);
                        } else if (Player.getIsShooting()) {
                            idleShootingUpAnimP.Update(System.nanoTime());
                            idleShootingUpAnimP.draw(Player.dx_cam(), Player.dy_cam(), (int) Player.getWidth(), (int) Player.getHeight(), g2);
                        } else {
                            idleUpAnimP.Update(System.nanoTime());
                            idleUpAnimP.draw(Player.dx_cam(), Player.dy_cam(), (int) Player.getWidth(), (int) Player.getHeight(), g2);
                        }
                    } else {
                        if (Player.getIsRunning()) {
                            runDownAnimP.Update(System.nanoTime());
                            runDownAnimP.draw(Player.dx_cam(), Player.dy_cam(), (int) Player.getWidth(), (int) Player.getHeight(), g2);
                            if (runDownAnimP.getcurrentImageIndex() == 1) runDownAnimP.setIgnoreImage(0);
                        } else if (Player.getIsAttack()) {
                            idleAttackDownAnimP.Update(System.nanoTime());
                            idleAttackDownAnimP.draw(Player.dx_cam(), Player.dy_cam(), (int) Player.getWidth(), (int) Player.getHeight(), g2);
                        } else if (Player.getIsShooting()) {
                            idleShootingDownAnimP.Update(System.nanoTime());
                            idleShootingDownAnimP.draw(Player.dx_cam(), Player.dy_cam(), (int) Player.getWidth(), (int) Player.getHeight(), g2);
                        } else {
                            idleDownAnimP.Update(System.nanoTime());
                            idleDownAnimP.draw(Player.dx_cam(), Player.dy_cam(), (int) Player.getWidth(), (int) Player.getHeight(), g2);
                        }
                    }
                    break;

                case BEHURT:
                    break;

            }
    }
    public void getBossImage(){
        flyRightB = DataLoader.getInstance().getAnimation("newboss_flyr");
        flyLeftB = DataLoader.getInstance().getAnimation("newboss_flyl");

        landingRightB = DataLoader.getInstance().getAnimation("newboss_tiepdatr");
        landingLeftB = DataLoader.getInstance().getAnimation("newboss_tiepdatl");

        idleRightB = DataLoader.getInstance().getAnimation("newboss_idler");
        idleLeftB = DataLoader.getInstance().getAnimation("newboss_idlel");

        atk1RightB = DataLoader.getInstance().getAnimation("newboss_atkright1");
        atk1LeftB = DataLoader.getInstance().getAnimation("newboss_atkleft1");
        atk2RightB = DataLoader.getInstance().getAnimation("newboss_atkright2");
        atk2LeftB = DataLoader.getInstance().getAnimation("newboss_atkleft2");
    }
    public void draw(Boss boss,Graphics2D g2) {
        int x = (int)(boss.dx_cam() - 70);
        int y = (int)(boss.dy_cam() - 100);
        g2.setColor(Color.GRAY);
        g2.fillRect(x, y, 150, 6);
        g2.setColor(Color.RED);
        g2.fillRect(x, y, boss.getBlood()/(boss.getBlood()/150), 5);

        if(boss.getAttackType(boss.getAttackIndex()).equals("NONE")){
            if(boss.getDirection() == RIGHT_DIR){
                idleRightB.Update(System.nanoTime());
                idleRightB.draw(boss.dx_cam(), boss.dy_cam(), (int) boss.getWidth(), (int) boss.getHeight(), g2);
            }else{
                idleLeftB.Update(System.nanoTime());
                idleLeftB.draw(boss.dx_cam(), boss.dy_cam(), (int) boss.getWidth(), (int) boss.getHeight(), g2);
            }
        }else if(boss.getAttackType(boss.getAttackIndex()).equals("attacking1")){

            if(boss.getDirection() == RIGHT_DIR){
                atk1RightB.Update(System.nanoTime());
                atk1RightB.draw(boss.dx_cam(), boss.dy_cam(), (int) boss.getWidth(), (int) boss.getHeight(), g2);
            }else{
                atk1LeftB.Update(System.nanoTime());
                atk1LeftB.draw(boss.dx_cam(), boss.dy_cam(), (int) boss.getWidth(), (int) boss.getHeight(), g2);
            }

        }else if(boss.getAttackType(boss.getAttackIndex()).equals("attacking2")){
            if(boss.getDirection() == RIGHT_DIR){
                atk2RightB.Update(System.nanoTime());
                atk2RightB.draw(boss.dx_cam(), boss.dy_cam(), (int) boss.getWidth(), (int) boss.getHeight(), g2);
            }else{
                atk2LeftB.Update(System.nanoTime());
                atk2LeftB.draw(boss.dx_cam(), boss.dy_cam(), (int) boss.getWidth(), (int) boss.getHeight(), g2);
            }
        }else if(boss.getAttackType(boss.getAttackIndex()).equals("flying")) {
            if(boss.getSpeedX() > 0) {
                flyRightB.Update(System.nanoTime());
                if(flyRightB.getcurrentImageIndex() == 0) flyRightB.setIgnoreImage(0);
                flyRightB.draw(boss.dx_cam(), boss.dy_cam(), (int) boss.getWidth(), (int) boss.getHeight(), g2);
            }else {
                flyLeftB.Update(System.nanoTime());
                if(flyLeftB.getcurrentImageIndex() == 0) flyLeftB.setIgnoreImage(0);
                flyLeftB.draw(boss.dx_cam(), boss.dy_cam(), (int) boss.getWidth(), (int) boss.getHeight(), g2);
            }
        }else if(boss.getAttackType(boss.getAttackIndex()).equals("landing")) {
            if(boss.getDirection() == RIGHT_DIR) {
                landingRightB.Update(System.nanoTime());
                landingRightB.draw(boss.dx_cam(), boss.dy_cam(), (int) boss.getWidth(), (int) boss.getHeight(), g2);
            }else {
                landingLeftB.Update(System.nanoTime());
                landingLeftB.draw(boss.dx_cam(), boss.dy_cam(), (int) boss.getWidth(), (int) boss.getHeight(), g2);
            }
        }
    }
    public void getDarkDuckImage(){
        runUpD = DataLoader.getInstance().getAnimation("enemy2_runup");
        runDownD = DataLoader.getInstance().getAnimation("enemy2_rundown");
        runLeftD = DataLoader.getInstance().getAnimation("enemy2_runleft");
        runRightD = DataLoader.getInstance().getAnimation("enemy2_runright");

        idleLeftD = DataLoader.getInstance().getAnimation("enemy2_idleleft");
        idleRightD = DataLoader.getInstance().getAnimation("enemy2_idleright");
        idleUpD = DataLoader.getInstance().getAnimation("enemy2_idleup");
        idleDownD = DataLoader.getInstance().getAnimation("enemy2_idledown");

        atkXuoiD = DataLoader.getInstance().getAnimation("enemy2_atk_xuoi");
        atkNguocD = DataLoader.getInstance().getAnimation("enemy2_atk_nguoc");
    }
    public void draw(DarkDuck darkDuck,Graphics2D g2){
        int x = (int)(darkDuck.dx_cam() - 10);
        int y = (int)(darkDuck.dy_cam() - 30);
        g2.setColor(Color.GRAY);
        g2.fillRect(x, y, 25, 3);
        g2.setColor(Color.RED);
        g2.fillRect(x, y, darkDuck.getBlood()/(darkDuck.getMaxHP()/25), 2);

        if(darkDuck.getDirection() == RIGHT_DIR) {
            if(darkDuck.getIsRunning()) {
                runRightD.Update(System.nanoTime());
                runRightD.draw(darkDuck.dx_cam(), darkDuck.dy_cam(), (int)darkDuck.getWidth(), (int)darkDuck.getHeight(), g2);
            }
            else if(darkDuck.getIsShooting()) {
                atkXuoiD.Update(System.nanoTime());
                atkXuoiD.draw(darkDuck.dx_cam(), darkDuck.dy_cam(), (int)darkDuck.getWidth(), (int)darkDuck.getHeight(), g2);
            }
            else if(!darkDuck.getIsRunning() && !darkDuck.getIsShooting()){
                idleRightD.Update(System.nanoTime());
                idleRightD.draw(darkDuck.dx_cam(), darkDuck.dy_cam(), (int)darkDuck.getWidth(), (int)darkDuck.getHeight(), g2);
            }
        }
        else if(darkDuck.getDirection() == LEFT_DIR) {
            if(darkDuck.getIsRunning()) {
                runLeftD.Update(System.nanoTime());
                runLeftD.draw(darkDuck.dx_cam(), darkDuck.dy_cam(), (int)darkDuck.getWidth(), (int)darkDuck.getHeight(), g2);
                if(runLeftD.getcurrentImageIndex() == 1) runLeftD.setIgnoreImage(0);
            }
            else if(darkDuck.getIsShooting()) {
                atkNguocD.Update(System.nanoTime());
                atkNguocD.draw(darkDuck.dx_cam(), darkDuck.dy_cam(), (int)darkDuck.getWidth(), (int)darkDuck.getHeight(), g2);
            }
            else if(!darkDuck.getIsRunning() && !darkDuck.getIsShooting()) {
                idleLeftD.Update(System.nanoTime());
                idleLeftD.draw(darkDuck.dx_cam(), darkDuck.dy_cam(), (int)darkDuck.getWidth(), (int)darkDuck.getHeight(), g2);
            }
        }
        else if(darkDuck.getDirection() == UP_DIR) {
            if(darkDuck.getIsRunning()) {
                runUpD.Update(System.nanoTime());
                runUpD.draw(darkDuck.dx_cam(), darkDuck.dy_cam(), (int)darkDuck.getWidth(), (int)darkDuck.getHeight(), g2);
                //if(runUp.getcurrentImageIndex() == 1) runUp.setIgnoreImage(0);
            }
            else if(darkDuck.getIsShooting()) {
                atkXuoiD.Update(System.nanoTime());
                atkXuoiD.draw(darkDuck.dx_cam(), darkDuck.dy_cam(), (int)darkDuck.getWidth(), (int)darkDuck.getHeight(), g2);
            }
            else if(!darkDuck.getIsRunning() && !darkDuck.getIsShooting()){
                idleUpD.Update(System.nanoTime());
                idleUpD.draw(darkDuck.dx_cam(), darkDuck.dy_cam(), (int)darkDuck.getWidth(), (int)darkDuck.getHeight(), g2);
            }
        }
        else {
            if(darkDuck.getIsRunning()) {
                runDownD.Update(System.nanoTime());
                runDownD.draw(darkDuck.dx_cam(), darkDuck.dy_cam(), (int)darkDuck.getWidth(), (int)darkDuck.getHeight(), g2);
                //if(runDown.getcurrentImageIndex() == 1) runDown.setIgnoreImage(0);
            }
            else if(darkDuck.getIsShooting()) {
                atkNguocD.Update(System.nanoTime());
                atkNguocD.draw(darkDuck.dx_cam(), darkDuck.dy_cam(), (int)darkDuck.getWidth(), (int)darkDuck.getHeight(), g2);
            }
            else if(!darkDuck.getIsRunning() && !darkDuck.getIsShooting()) {
                idleDownD.Update(System.nanoTime());
                idleDownD.draw(darkDuck.dx_cam(), darkDuck.dy_cam(), (int)darkDuck.getWidth(), (int)darkDuck.getHeight(), g2);
            }
        }
    }
    public void getLittleGirlImage(){
        runUpL = DataLoader.getInstance().getAnimation("enemy1_runup");
        runDownL = DataLoader.getInstance().getAnimation("enemy1_rundown");
        runLeftL = DataLoader.getInstance().getAnimation("enemy1_runleft");
        runRightL = DataLoader.getInstance().getAnimation("enemy1_runright");

        idleLeftL = DataLoader.getInstance().getAnimation("enemy1_idleleft");
        idleRightL = DataLoader.getInstance().getAnimation("enemy1_idleright");
        idleUpL= DataLoader.getInstance().getAnimation("enemy1_idleup");
        idleDownL = DataLoader.getInstance().getAnimation("enemy1_idledown");
        DataLoader.getInstance().getAnimation("bullet05");
    }
    public void draw(LittleGirl littleGirl,Graphics2D g2){
        int x = (int)(littleGirl.dx_cam() - 15);
        int y = (int)(littleGirl.dy_cam() - 30);
        g2.setColor(Color.GRAY);
        g2.fillRect(x, y, 25, 3);
        g2.setColor(Color.RED);
        g2.fillRect(x, y, littleGirl.getBlood()/(littleGirl.getMaxHP()/25), 2);

        if(littleGirl.getState() == ALIVE || littleGirl.getState() == IMMORTAL) {
            if(littleGirl.getDirection() == RIGHT_DIR) {
                if(littleGirl.getIsRunning()) {
                    runRightL.Update(System.nanoTime());
                    runRightL.draw(littleGirl.dx_cam(), littleGirl.dy_cam(), (int)littleGirl.getWidth(), (int)littleGirl.getHeight(), g2);
                }
                else if(littleGirl.getIsAttack()) {
                    idleRightL.Update(System.nanoTime());
                    idleRightL.draw(littleGirl.dx_cam(), littleGirl.dy_cam(), (int)littleGirl.getWidth(), (int)littleGirl.getHeight(), g2);
                }
                else {
                    idleRightL.Update(System.nanoTime());
                    idleRightL.draw(littleGirl.dx_cam(), littleGirl.dy_cam(), (int)littleGirl.getWidth(), (int)littleGirl.getHeight(), g2);
                }
            }
            else if(littleGirl.getDirection() == LEFT_DIR) {
                if(littleGirl.getIsRunning()) {
                    runLeftL.Update(System.nanoTime());
                    runLeftL.draw(littleGirl.dx_cam(), littleGirl.dy_cam(), (int)littleGirl.getWidth(), (int)littleGirl.getHeight(), g2);
                    if(runLeftL.getcurrentImageIndex() == 1) runLeftL.setIgnoreImage(0);
                }
                else if(littleGirl.getIsAttack()) {
                    idleLeftL.Update(System.nanoTime());
                    idleLeftL.draw(littleGirl.dx_cam(), littleGirl.dy_cam(), (int)littleGirl.getWidth(), (int)littleGirl.getHeight(), g2);
                }
                else {
                    idleLeftL.Update(System.nanoTime());
                    idleLeftL.draw(littleGirl.dx_cam(), littleGirl.dy_cam(), (int)littleGirl.getWidth(), (int)littleGirl.getHeight(), g2);
                }
            }
            else if(littleGirl.getDirection() == UP_DIR) {
                if(littleGirl.getIsRunning()) {
                    runUpL.Update(System.nanoTime());
                    runUpL.draw(littleGirl.dx_cam(), littleGirl.dy_cam(), (int)littleGirl.getWidth(), (int)littleGirl.getHeight(), g2);
                }
                else if(littleGirl.getIsAttack()) {
                    idleUpL.Update(System.nanoTime());
                    idleUpL.draw(littleGirl.dx_cam(), littleGirl.dy_cam(), (int)littleGirl.getWidth(), (int)littleGirl.getHeight(), g2);
                }
                else {
                    idleUpL.Update(System.nanoTime());
                    idleUpL.draw(littleGirl.dx_cam(), littleGirl.dy_cam(), (int)littleGirl.getWidth(), (int)littleGirl.getHeight(), g2);
                }
            }
            else {
                if(littleGirl.getIsRunning()) {
                    runDownL.Update(System.nanoTime());
                    runDownL.draw(littleGirl.dx_cam(), littleGirl.dy_cam(), (int)littleGirl.getWidth(), (int)littleGirl.getHeight(), g2);
                }
                else if(littleGirl.getIsAttack()) {
                    idleDownL.Update(System.nanoTime());
                    idleDownL.draw(littleGirl.dx_cam(), littleGirl.dy_cam(), (int)littleGirl.getWidth(), (int)littleGirl.getHeight(), g2);
                }
                else {
                    idleDownL.Update(System.nanoTime());
                    idleDownL.draw(littleGirl.dx_cam(), littleGirl.dy_cam(), (int)littleGirl.getWidth(), (int)littleGirl.getHeight(), g2);
                }
            }
        }
    }
    public void getSlimeImage(){
        idle = DataLoader.getInstance().getAnimation("enemy4_atk");
    }
    public void draw(Slime slime, Graphics2D g2){
        int x = (int)(slime.dx_cam() - 160);
        int y = (int)(slime.dy_cam() - 150);
        g2.setColor(Color.GRAY);
        g2.fillRect(x, y, 100, 4);
        g2.setColor(Color.RED);
        g2.fillRect(x, y, slime.getBlood()/(slime.getMaxHP()/100), 3);
        idle.Update(System.nanoTime());
        idle.draw(slime.dx_cam(), slime.dy_cam(), (int) slime.getWidth(), (int) slime.getHeight(), g2);
    }
    public void getSnailImage(){
        runUpS = DataLoader.getInstance().getAnimation("enemy3_runup");
        runDownS = DataLoader.getInstance().getAnimation("enemy3_rundown");
        runLeftS = DataLoader.getInstance().getAnimation("enemy3_runleft");
        runRightS= DataLoader.getInstance().getAnimation("enemy3_runright");

        idleLeftS = DataLoader.getInstance().getAnimation("enemy3_idleleft");
        idleRightS = DataLoader.getInstance().getAnimation("enemy3_idleright");
        idleUpS = DataLoader.getInstance().getAnimation("enemy3_idleup");
        idleDownS = DataLoader.getInstance().getAnimation("enemy3_idledown");

        atkLeftS = DataLoader.getInstance().getAnimation("enemy3_atkleft");
        atkRightS = DataLoader.getInstance().getAnimation("enemy3_atkright");
        atkUpS = DataLoader.getInstance().getAnimation("enemy3_atkup");
        atkDownS = DataLoader.getInstance().getAnimation("enemy3_atkdown");
    }
    public void draw(Snail snail, Graphics2D g2){
        int x = (int)(snail.dx_cam() - 8);
        int y = (int)(snail.dy_cam() - 30);
        g2.setColor(Color.GRAY);
        g2.fillRect(x, y, 25, 3);
        g2.setColor(Color.RED);
        g2.fillRect(x, y, snail.getBlood()/(snail.getMaxHP()/25), 2);


        if(snail.getDirection() == RIGHT_DIR) {
            if(snail.getIsRunning()) {
                runRightS.Update(System.nanoTime());
                runRightS.draw(snail.dx_cam(), snail.dy_cam(), (int)snail.getWidth(), (int)snail.getHeight(), g2);
            }
            else if(snail.getIsAttack()) {
                atkRightS.Update(System.nanoTime());
                atkRightS.draw(snail.dx_cam(), snail.dy_cam(), (int)snail.getWidth(), (int)snail.getHeight(), g2);
            }
            else if(!snail.getIsRunning() && !snail.getIsAttack()){
                idleRightS.Update(System.nanoTime());
                idleRightS.draw(snail.dx_cam(), snail.dy_cam(), (int)snail.getWidth(), (int)snail.getHeight(), g2);
            }
        }
        else if(snail.getDirection() == LEFT_DIR) {
            if(snail.getIsRunning()) {
                runLeftS.Update(System.nanoTime());
                runLeftS.draw(snail.dx_cam(), snail.dy_cam(), (int)snail.getWidth(), (int)snail.getHeight(), g2);
                if(runLeftS.getcurrentImageIndex() == 1) runLeftS.setIgnoreImage(0);
            }
            else if(snail.getIsAttack()) {
                atkLeftS.Update(System.nanoTime());
                atkLeftS.draw(snail.dx_cam(), snail.dy_cam(), (int)snail.getWidth(), (int)snail.getHeight(), g2);
            }
            else if(!snail.getIsRunning() && !snail.getIsAttack()) {
                idleLeftS.Update(System.nanoTime());
                idleLeftS.draw(snail.dx_cam(), snail.dy_cam(), (int)snail.getWidth(), (int)snail.getHeight(), g2);
            }
        }
        else if(snail.getDirection() == UP_DIR) {
            if(snail.getIsRunning()) {
                runUpS.Update(System.nanoTime());
                runUpS.draw(snail.dx_cam(), snail.dy_cam(), (int)snail.getWidth(), (int)snail.getHeight(), g2);
            }
            else if(snail.getIsAttack()) {
                atkUpS.Update(System.nanoTime());
                atkUpS.draw(snail.dx_cam(), snail.dy_cam(), (int)snail.getWidth(), (int)snail.getHeight(), g2);
            }
            else if(!snail.getIsRunning() && !snail.getIsAttack()){
                idleUpS.Update(System.nanoTime());
                idleUpS.draw(snail.dx_cam(), snail.dy_cam(), (int)snail.getWidth(), (int)snail.getHeight(), g2);
            }
        }
        else {
            if(snail.getIsRunning()) {
                runDownS.Update(System.nanoTime());
                runDownS.draw(snail.dx_cam(), snail.dy_cam(), (int)snail.getWidth(), (int)snail.getHeight(), g2);

            }
            else if(snail.getIsAttack()) {
                atkDownS.Update(System.nanoTime());
                atkDownS.draw(snail.dx_cam(), snail.dy_cam(), (int)snail.getWidth(), (int)snail.getHeight(), g2);
            }
            else if(!snail.getIsRunning() && !snail.getIsAttack()) {
                idleDownS.Update(System.nanoTime());
                idleDownS.draw(snail.dx_cam(), snail.dy_cam(), (int)snail.getWidth(), (int)snail.getHeight(), g2);
            }
        }
    }
    public void getBullImage(){
        runForwardAnimB = DataLoader.getInstance().getAnimation("bull_runright");
        runBackAnimB = DataLoader.getInstance().getAnimation("bull_runleft");
        runUpAnimB= DataLoader.getInstance().getAnimation("bull_runup");
        runDownAnimB = DataLoader.getInstance().getAnimation("bull_rundown");

        idleForwardAnimB = DataLoader.getInstance().getAnimation("bull_idleright");
        idleBackAnimB = DataLoader.getInstance().getAnimation("bull_idleleft");
        idleUpAnimB = DataLoader.getInstance().getAnimation("bull_idleup");
        idleDownAnimB= DataLoader.getInstance().getAnimation("bull_idledown");

        idleAttackForwardAnimB = DataLoader.getInstance().getAnimation("bull_attackright");
        idleAttackBackAnimB = DataLoader.getInstance().getAnimation("bull_attackleft");
        idleAttackUpAnimB = DataLoader.getInstance().getAnimation("bull_attackup");
        idleAttackDownAnimB = DataLoader.getInstance().getAnimation("bull_attackdown");

        deathBull = DataLoader.getInstance().getAnimation("bull_death");
    }
    public void draw(Bull bull, Graphics2D g2){
        int x = (int)(bull.dx_cam() - 15);
        int y = (int)(bull.dy_cam() - 35);
        g2.setColor(Color.GRAY);
        g2.fillRect(x, y, 50, 4);
        g2.setColor(Color.RED);
        g2.fillRect(x, y, bull.getBlood()/(bull.getMaxHP()/50), 3);

        if(bull.getState() == ALIVE || bull.getState() == IMMORTAL) {
            if(bull.getDirection() == RIGHT_DIR) {
                if(bull.getIsRunning()) {
                    runForwardAnimB.Update(System.nanoTime());
                    runForwardAnimB.draw(bull.dx_cam(), bull.dy_cam(), (int)bull.getWidth(), (int)bull.getHeight(), g2);
                }
                if(bull.getIsAttack()) {
                    idleAttackForwardAnimB.Update(System.nanoTime());
                    idleAttackForwardAnimB.draw(bull.dx_cam(), bull.dy_cam(), (int)bull.getWidth(), (int)bull.getHeight(), g2);
                }
                if(!bull.getIsRunning() && !bull.getIsAttack()) {
                    idleBackAnimB.Update(System.nanoTime());
                    idleBackAnimB.draw(bull.dx_cam(), bull.dy_cam(), (int)bull.getWidth(), (int)bull.getHeight(), g2);
                }
            }
            else if(bull.getDirection() == LEFT_DIR) {
                if(bull.getIsRunning()) {
                    runBackAnimB.Update(System.nanoTime());
                    runBackAnimB.draw(bull.dx_cam(), bull.dy_cam(), (int)bull.getWidth(), (int)bull.getHeight(), g2);
                }
                if(bull.getIsAttack()) {
                    idleAttackBackAnimB.Update(System.nanoTime());
                    idleAttackBackAnimB.draw(bull.dx_cam(), bull.dy_cam(), (int)bull.getWidth(), (int)bull.getHeight(), g2);
                }
                if(!bull.getIsRunning() && !bull.getIsAttack()) {
                    idleForwardAnimB.Update(System.nanoTime());
                    idleForwardAnimB.draw(bull.dx_cam(), bull.dy_cam(), (int)bull.getWidth(), (int)bull.getHeight(), g2);
                }
            }
            else if(bull.getDirection() == UP_DIR) {
                if(bull.getIsRunning()) {
                    runUpAnimB.Update(System.nanoTime());
                    runUpAnimB.draw(bull.dx_cam(), bull.dy_cam(), (int)bull.getWidth(), (int)bull.getHeight(), g2);

                }
                if(bull.getIsAttack()) {
                    idleAttackUpAnimB.Update(System.nanoTime());
                    idleAttackUpAnimB.draw(bull.dx_cam(), bull.dy_cam(), (int)bull.getWidth(), (int)bull.getHeight(), g2);
                }
                if(!bull.getIsRunning() && !bull.getIsAttack()) {
                    idleUpAnimB.Update(System.nanoTime());
                    idleUpAnimB.draw(bull.dx_cam(), bull.dy_cam(), (int)bull.getWidth(), (int)bull.getHeight(), g2);
                }
            }
            else {
                if(bull.getIsRunning()) {
                    runDownAnimB.Update(System.nanoTime());
                    runDownAnimB.draw(bull.dx_cam(), bull.dy_cam(), (int)bull.getWidth(), (int)bull.getHeight(), g2);

                }
                if(bull.getIsAttack()) {
                    idleAttackDownAnimB.Update(System.nanoTime());
                    idleAttackDownAnimB.draw(bull.dx_cam(), bull.dy_cam(), (int)bull.getWidth(), (int)bull.getHeight(), g2);
                }
                if(!bull.getIsRunning() && !bull.getIsAttack()) {
                    idleDownAnimB.Update(System.nanoTime());
                    idleDownAnimB.draw(bull.dx_cam(), bull.dy_cam(), (int)bull.getWidth(), (int)bull.getHeight(), g2);
                }
            }
        }
        if(bull.getState() == DEATH) {
            deathBull.Update(System.nanoTime());
            deathBull.draw(bull.dx_cam(), bull.dy_cam(), (int)bull.getWidth(), (int)bull.getHeight(), g2);
        }
    }
}


