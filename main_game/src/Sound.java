/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import javax.sound.sampled.Clip;
import java.net.URL;
import javax.sound.sampled.AudioSystem; 
import javax.sound.sampled.AudioInputStream; 
/**
 *
 * @author jaspr
 */
public class Sound {
    
    //Variables for url and clip
    Clip clip; 
    URL soundURL[] = new URL[30];    
    
    public Sound(){//Sound url array holding sound and sound effects 
        
        soundURL[0] = getClass().getResource("mainTheme1.wav");
        soundURL[1] = getClass().getResource("swordEffect.wav");
        soundURL[2] = getClass().getResource("damageEffect.wav");
      
    }
    public void setFile(int i){//Seeting each file method
        
        try{
            
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            
        }catch(Exception e){
            
            
        }
        
    }
    public void play(){//Play method
        
        clip.start();
        
    }
    public void loop(){//Loop method
        
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        
    }
    public void stop(){//stop method
        
        clip.stop();
        
    }
}
