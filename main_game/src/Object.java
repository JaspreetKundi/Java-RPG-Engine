/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.IOException;
import java.io.*;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
/**
 *
 * @author jaspr
 */
public class Object {
     // DEFAULT VALUES TO EACH OBJECT INCLUDING SOLID AREAS FOR COLLSION
  public static BufferedImage image, image2, image3, up;
  public String name;
  public boolean collision = false;
  public int WorldX, WorldY;
  public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
  public int solidAreaDefaultX = 0;
  public int solidAreaDefaultY = 0;

  public void draw(Graphics2D g2, GamePanel gp) {

    int ScreenX = WorldX - gp.worldX + gp.ScreenX; // SCREENX IS THE WORLDX OF THE COORDINATES AND THE PLAYER WORLDX +
                                                   // THE SCREENX TO GIVE THE POSITION INTO WHERE IT SPAWNS ON SCREEN
    int ScreenY = WorldY - gp.worldY + gp.ScreenY; // SAME THING BUT IN Y DIRECTION
    

    g2.drawImage(image, ScreenX, ScreenY, gp.TSize, gp.TSize, null); // PRINTS THE OBJECT ON THE MAP ACCORDING THE THE
                                                                     // SCREENX AND SCREEN Y

  }
}
