/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
import javax.swing.JFrame;
import java.awt.Container;
/**
 *
 * @author jaspr
 */
public class Main {

  public static void main(String[] args) {

    JFrame window = new JFrame(); // Creates a JFrame variable
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Allows the user to click the "x" on the wndow to close the
                                                           // operation properly
    window.setResizable(false); // Makes sure that the window does not change sizes and stays constant
    window.setTitle("The Way of The Sword"); // Sets the title of the Window

    GamePanel gamePanel = new GamePanel();
    Menu menu = new Menu(); // Brings in the game screen setting from the GamePanel Class
    window.add(menu); // Adds the GamePanel setting to the screen
    window.pack(); // Sized to fit the perferred size and layouts
    window.setVisible(true); // Allows the window to be visible for the user

    gamePanel.startGameThread();// Because it exists start the thread

  }
    
}
