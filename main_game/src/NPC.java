/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.awt.Color;
import java.io.IOException;
import java.io.*;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
/**
 *
 * @author jaspr
 */
public class NPC {
    GamePanel gp;

  // BUFFERED IMAGES FOR SPRITES, SOME ARE NOT GOING TO BE USED

  BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;

  // DEFAULT VALUES FOR THE NPC, SINCE NPC ONLY HAS A BOSS, THE BOSS LIFE IS SET
  // HERE RATHER THAN THE BOSS CLASS, INCLUDES COLLSION FOR SOLID AREAS
  public String name;
  public boolean collision = false;
  public int WorldX, WorldY;
  public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
  public Rectangle attackArea = new Rectangle(0, 0, 0, 0);// EXPLAINED MORE IN COLLISION CLASS
  public int solidAreaDefaultX = 0;
  public int solidAreaDefaultY = 0;
  int speed = 1;
  int counter;
  int Life = 3; 
  
  String direction = "down";

  public void NPC(GamePanel gp) {
    this.gp = gp;// PASSES THIS GP
  }

  public void draw(Graphics g2D, GamePanel gp) {

    BufferedImage image = null; // SETS BUFFERED IMAGE AS NULL, SINCE IT IS REQUIRED

    int ScreenX = WorldX - gp.worldX + gp.ScreenX; // again gets the screenx (where the boss/npc is being printed) (gets
                                                   // the worldx of the boss thats set in gamepanel then minuses the
                                                   // player world x) same thing with the y
    int ScreenY = WorldY - gp.worldY + gp.ScreenY;

    if (WorldX + gp.TSize > gp.worldX - gp.ScreenX && WorldX - gp.TSize < gp.worldX + gp.ScreenX
        && WorldY + gp.TSize > gp.worldY - gp.ScreenY && WorldY - gp.TSize < gp.worldY + gp.ScreenY) {

      switch (direction) {
        case "up":
            if (gp.spriteNum == 1){image = up1;}
            if (gp.spriteNum == 2){image = up2;}
          break;
        case "down":
          if (gp.spriteNum == 1){image = down1;}
            if (gp.spriteNum == 2){image = down2;}
          break;
        case "left":
          if (gp.spriteNum == 1){image = left1;}
            if (gp.spriteNum == 2){image = left2;}
          break;
        case "right":
          if (gp.spriteNum == 1){image = right1;}
            if (gp.spriteNum == 2){image = right2;}
          break;
      }
      
      double oneScale = (double)gp.TSize/Life;
      double hpBarValue = oneScale*gp.pc.Life; 
      
        g2D.setColor(new Color(35,35,35));
        g2D.fillRect(ScreenX-1,ScreenY - 16, gp.TSize + 2, 12);
        g2D.setColor(new Color(255,0,30));
        g2D.fillRect(ScreenX,ScreenY - 15,(int)hpBarValue, 10);

      g2D.drawImage(image, ScreenX, ScreenY, 48, 48, null);// Draws that image sprite based of its position

      
    }
  }

  public void setAction() {
    // Empty method automatically takes the same method from the boss class since
    // this is its super class
  }

  public void update(GamePanel gp) {
    setAction(); // Calls set action, with the reandom number generator

    // Does this before movement to make sure there is collision
    gp.collisionOn = false;// Auto sets the collision to false
    gp.cChecker.checkTileBoss(this);// Checks the tiles with the boss in collision, to make sure if the boss is
                                    // touvhing a collision block

    if (gp.collisionOn == false) {// If there is not collision then boss moves, however if it is touching a blovk
                                  // collision would be true in collison class

      switch (direction) {
        case "up": // then the movement upwards start alongwith the other ones, moves accroding to
                   // their speed
          WorldY -= speed;
          break;
        case "down":
          WorldY += speed;
          break;
        case "left":
          WorldX -= speed;
          break;
        case "right":
          WorldX += speed;
          break;
      }

    }

  }

}
