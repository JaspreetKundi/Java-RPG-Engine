/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.IOException;
import java.io.*;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;
/**
 *
 * @author jaspr
 */
public class ObjectHeart extends Object {
      GamePanel gp;

  public ObjectHeart(GamePanel gp) {

    this.gp = gp; // PASSSES THIS GP

    name = "Heart";

    try { // GETS THE IMAGES FOR FOR THE HEARTS
      image = ImageIO.read(getClass().getResourceAsStream("fullHeart.png"));
      image2 = ImageIO.read(getClass().getResourceAsStream("halfHeart.png"));
      image3 = ImageIO.read(getClass().getResourceAsStream("emptyHeart.png"));

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
