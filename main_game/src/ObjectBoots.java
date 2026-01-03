
import java.io.IOException;
import java.io.*;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jaspr
 */

//TESTING CLASS NOT IN GAME
public class ObjectBoots extends Object{
    
    ObjectBoots(){
        
        name = "Boots";
        
        try {
      image = ImageIO.read(getClass().getResourceAsStream("Sword.png"));// GETS SWORD IMAGE

    } catch (IOException e) {
      e.printStackTrace();
    }
       
    }
    
}

