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
public class ObjectSword extends Object {
    
  public ObjectSword() {

    name = "Sword";

    try {
      image = ImageIO.read(getClass().getResourceAsStream("Sword.png"));// GETS SWORD IMAGE

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
