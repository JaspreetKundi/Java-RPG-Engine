/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/**
 *
 * @author jaspr
 */
public class KeyMover implements KeyListener {
   public boolean upPressed, downPressed, leftPressed, rightPressed, attackPressed;// BOLLEANS

  public void keyPressed(KeyEvent ke) {

    int code = ke.getKeyCode();

    if (code == KeyEvent.VK_W) {// IF W IS CLICKED THEN UP PRESSED IS TRUE
      upPressed = true;
    }
    if (code == KeyEvent.VK_S) {// SAME BUT FOR DOWN
      downPressed = true;
    }
    if (code == KeyEvent.VK_A) {// SAME BUT FOR LEFT
      leftPressed = true;
    }
    if (code == KeyEvent.VK_D) {// SAME BUT FOR RIGHT
      rightPressed = true;
    }
    if (code == KeyEvent.VK_J) {// iF ATTACK IS CLICKED ATTACK CLICK IS TRUE
      attackPressed = true;
    }
  }

  public void keyReleased(KeyEvent ke) { // iF NON OF THE BUTTONS ARE PRESSED THEERE BOOLEANS ARE FALSE

    int code = ke.getKeyCode();

    if (code == KeyEvent.VK_W) {
      upPressed = false;
    }
    if (code == KeyEvent.VK_S) {
      downPressed = false;
    }
    if (code == KeyEvent.VK_A) {
      leftPressed = false;
    }
    if (code == KeyEvent.VK_D) {

      rightPressed = false;
    }
    if (code == KeyEvent.VK_J) {
      resetAttack();
    }

  }

  public void keyTyped(KeyEvent ke) {
  }

  private void resetAttack() {
    attackPressed = false;
    // Reset any other attack-related variables or animation states here
  }
  
}
