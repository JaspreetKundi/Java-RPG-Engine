
import java.io.IOException;
import java.io.*;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.lang.Math;
import java.util.Random;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jaspr
 */
public class OldMan extends FreindlyNPC {
    
    public OldMan(GamePanel gp){
        
    // SETTING DEFAULT VALUES FOR THE BOSS, INCLUDING ITS SOLDID AREA FOR COLLSION
    name = "Old Man";
    gp.direction = "down";
    gp.playerSpeed = 1;
    solidArea.x = 18;
    solidArea.y = 18;
    solidArea.width = 48;
    solidArea.height = 48;
    solidAreaDefaultX = solidArea.x;
    solidAreaDefaultY = solidArea.y;
    direction = "up";

    getImage(); // CALL GET IMAGE
        
        
    }
     public void getImage() {

    // GETS AKK THE IMAGES FORR THE BUFFERED IMAGES MDE IN NPC
    try {
      up1 = ImageIO.read(getClass().getResourceAsStream("oldman_up_1.png"));
      down1 = ImageIO.read(getClass().getResourceAsStream("oldman_down_1.png"));
      left1 = ImageIO.read(getClass().getResourceAsStream("oldman_left_1.png"));
      right1 = ImageIO.read(getClass().getResourceAsStream("oldman_right_1.png"));
      up2 = ImageIO.read(getClass().getResourceAsStream("oldman_up_2.png"));
      down2 = ImageIO.read(getClass().getResourceAsStream("oldman_down_2.png"));
      left2 = ImageIO.read(getClass().getResourceAsStream("oldman_left_2.png"));
      right2 = ImageIO.read(getClass().getResourceAsStream("oldman_right_2.png"));


    } catch (IOException e) {
      e.printStackTrace();
    }
  }
     public void setAction() {

    // COUNTER ADDED TO SLOW DOWN THE BOSS FOR 2 SECONDS A MOTION

    counter++;
    if (counter == 120) {
      Random random = new Random();
      int randomNumber = random.nextInt(4); // RANDOM NUMBER FROM 0 - 3

      switch (randomNumber) {
        case 0: // IN THE CASE THAT THE RANDOM NUMBER IS 0, THEN DIRECTION IS UP, SAME FOR ALL
                // THE OTHER NUMBER 1 2 3
          direction = "up";
          break;
        case 1:
          direction = "down";
          break;
        case 2:
          direction = "left";
          break;
        case 3:
          direction = "right";
          break;
      }

      counter = 0; // RESETS COUNTER TO RELOOP THE 120 COUNTER
    }
  }
}
