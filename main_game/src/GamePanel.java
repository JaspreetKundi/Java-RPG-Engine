/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;//For rectangle, employs various classes for the shape 
import java.io.IOException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
/**
 *
 * @author jaspr
 */
public class GamePanel extends JPanel implements Runnable{
      public final int titleState = 0; 
  public final int GameState = 0;


  //DECLARING VARIABLES 
  
  public static final int TSize = 48; //Basic tile size that will be used for everthing and everytile , public and static to allow other classes 
  public static final int maxScreencol = 16; //Max amount of coloms on the screen are going to be 16
  public static final int maxScreenrow = 12; //Max amount of coloms on the screen are going to be 12
  public final int ScreenWidth = TSize * maxScreencol; // 768 pixels, this will be the amount of pixels on the screen for (x), displayed on the screen multiplie tile size and the max screen colom that can be displayed
  public final int ScreenHeight = TSize * maxScreenrow; // 576 pixels, this will be the amount of pixels on the screen at (y), that are displayed for user, multipled tile size and max row to get this 
  // Makes the all the tiles in the game 15x15 wich will be used throughout the
  // game

  //CHARACTER MOVEMENT VARIABLES 
  
  String direction = "down";//This direction string will be used to give a direction to the system on where and what direction the character is moving, if it is up, then it will print the up image for the chacarter givin the allusion of an animation 
   public int playerSpeed = 1;//This is the speed at wwitch the character will move, this means it will go 1 pixel per click 

  //MORE WORLD SETTINGS 
  
  public static final int maxWorldCol = 56;//Max amount of tiles in the world x
  public static final int maxWorldRow = 18;//Max amount of tiles in the world y
  public static final int worldWidth = TSize * maxWorldCol;//Width of the world, not all parts visable 
  public static final int worldHeight = TSize * maxWorldRow;//height og the word map, not all parts visable 

  //CHARACTER POSITION VARIBALES 

  public final int ScreenX = ScreenWidth / 2 - (TSize / 2);//Where the player will be drawn on the screen, will scroll the background (x), they are used for the top left that why you minus the tile size divided by two since that gives the exact center
  public final int ScreenY = ScreenHeight / 2 - (TSize / 2);//Where the player will be drawn on the screen, will scroll the background (y), they are used for the top left that why you minus the tile size divided by two since that gives the exact center

  int worldX = TSize * 4; //Defualt posiotn at where the character starts in the world map (x)
  int worldY = TSize * 4; //Defualt posiotn at where the character starts in the world map (y)

  int FPS = 60;//Represents the frames per second in the game, setting this to 60 means that the game will print 60 times per second, this is the averge that fits the hz of average monitors, it will be used in the game loiop to kwwp it updating at 60fps 

  //ENTITY FOR COLLISION NOT DONE 
  
  public Rectangle solidArea = new Rectangle (8, 16, 32, 32); //Is a invisible box on the player that will serve for the collision 
  public Rectangle attackArea = new Rectangle (0, 0, 40, 40);
  
  public int solidAreaDefaultX = 8;
  public int solidAreaDefaultY = 16;
  public boolean collisionOn = false;

  public boolean inv = false;
  int invCounter; 

  boolean attack = false; 

  //Character health
  public static int maxLife = 12; 
  public static int Life = maxLife;//Players current life, 1 life = half heart  

  BufferedImage heartFull, heartHalf, heartLoss; 

  //Boss movements 
  BufferedImage up1, up2, down1, down2, left1, left2, right1, right2; 
   // BufferedImage character, down, right, left; //These are different images that will be used, character represents the image when the character moves up, these varibles are made to then later store the pictures for each one
  BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackRight1, attackRight2, attackLeft1, attackLeft2;
  
  int spriteNum = 1;
  int spriteCounter = 0;
  

  //CLASS IMPORTS AND THREAD IMPORT 
 
  ManageTile tileM = new ManageTile(this); //This sends the ManageTile class withing the gamepanel class and allows it to be used 
  KeyMover keyM = new KeyMover(); //This sends the KeyMover class withing the gamepanel class and allows it to be used
  Thread gameThread; //This starts the gameThread, and assigns it withing the Threa variable 
  CollisionCheck cChecker = new CollisionCheck(this); //This sends the CollisionCheck class withing the gamepanel class and allows it to be used
  public Object obj[] = new Object[10];//How many objects can be dispolayed for each type of item
  public EventHandler eventHandler = new EventHandler();//Adds event handler
  Sound sound = new Sound();
  public NPC boss[] = new NPC[10]; 
  public FreindlyNPC[] freindNPC = new FreindlyNPC[3];
  NPC pc = new NPC();
  FreindlyNPC fpc = new FreindlyNPC(); 
  Menu menu = new Menu();

  public GamePanel() {
    this.setPreferredSize(new Dimension(ScreenWidth, ScreenHeight));//This is the screen size that will appear in front of the character 
    this.setDoubleBuffered(true);//Helps with rendering since the JPanel sets in a double buffered function, setting this to true can help with the smoothness and rendering of the game
    this.setBackground(Color.BLACK);//This makes the background behind the tiles black, this is seen in many 2d games where the border is all a black void where the character cant enter
    startGameThread();//Callc the startGameThread methid to the gamepanel 
    this.addKeyListener(keyM);//Adds the keylistenr from the KeyMover class and assigns a varibale to it 
    this.setFocusable(true);//This adds a focus function the the key listener wich can help the program really focus on the keys 
    getPlayerImage();//Calls the get player image method in wich the previous bufferd image varibales get the thir images assigned
    getPlayerAttackImage();
    this.requestFocusInWindow();//requesting keyboard focus

    Object heart = new ObjectHeart(this); 
    //Saves the images in heart object class to variables 
    heartFull = heart.image;
    heartHalf = heart.image2;
    heartLoss = heart.image3;
  }


  public void startGameThread() {
    gameThread = new Thread(this);//Game thread exists withing this class

    //Sest object and boss before gamethread starts 
    setObject();
    setBoss();
    
    gameThread.start();//Because it exists start the thread 
  }
  public void run(){ // When the game thread is started, it automatically becomes a run method
    
    double drawInt = 1000000000/FPS;//Divides a nano second by the amount of framer per second (60), this gives a value of 16666666.67, desired amount of time between each frame to get 60fps 
    double nextDraw = System.nanoTime() + drawInt;//System.nanoTime returns the current time in nanoseconds. drawInt is a value of the duration in nanoseconds, By adding these two values together, you are calculating a new time value that represents the current time plus the specified duration. This will be used inbetween the movements the character make when keys are clicked, allows the character to change direction right away. 
  
    while (gameThread != null){ // Wile the thread exists, basically the main gameloop and source of time in the game 

      update();//Call the repaint method
      repaint();//Then repaint based of the paint component 

      try{//Try this 
      double remainTime = nextDraw - System.nanoTime();//calculates the remaining time in nanoseconds between the next draw
      remainTime = remainTime/1000000;//Converts the remaing time into miliseconds, this is done because thread.sleep only allows miliseconds 

        if(remainTime < 0){ //If the remaing time is lower than zero, then make the remaing time 0 
          remainTime = 0;
        }
        
        Thread.sleep((long)remainTime);//First makes the remaing time a long variable since thread.sleep accepts that only. Thread.sleep() in this context is to control the frame rate and timing of updates in the game loop. By sleeping for the remaining time until the next draw, the program achieves a consistent frame rate, pasuses the game for the amount of remain time, if remain time was lik 7 nanoseconds then the thread wouild stop for 7 nanoseconds an then continue the next code

        nextDraw += drawInt; //Next draw is the time bertween each draw, and the draw int is the desired interval betrween 2 draws, adding these two to the value of next draw, it tells the game loop when to draw again, this keeps the gameloop updating at a constant speed, this helps with the frame rate staying contant as well 
        
        } catch (InterruptedException e) {//Catches the interruption exception class and catches any interruptions  
      }
    }
  }

  public void update() { //The update method will be used for the key movements, and collision. It will update, then be sendt to the game thread, right after it updates it calls the repaint method which take the information that ios updated and repaint what was updatted, this makes the character move as well

    
    if (attack == true){//If the attack is true 
      attacking();//Calls the attacking ,ethod 
    }
    if(keyM.upPressed == true || keyM.downPressed == true || keyM.leftPressed == true || keyM.rightPressed == true){ //If either key is pressed
           
    if (keyM.upPressed == true) { //If the up key aka w is pressed, then chagne the dirction to up, and move the player position up by the player speed, in this case we minus from y 
      direction = "up";
    }
    else if (keyM.downPressed == true){//If the down key is pressed aka s is pressed, then chagne the dirction to up, and move the player position down by the player speed, in this case we add to y 
       direction = "down";
    }
    else if (keyM.leftPressed == true) { //If the left key is pressed aka the a key is predssed then change the direction to left and move the character postion to the left, in this case we minus from x 
       direction = "left";
    }
    else if (keyM.rightPressed == true) {//If the right key is pressed aka the d key is predssed then change the direction to right and move the character postion to the right, in this case we add to x 
       direction = "right";
    }
    
    //CHECK TILE COLLISION 
    collisionOn = false;//Setting the collsion auto false maing no collision start
    cChecker.checkTile(this); //Runs the cChecher through, BASED ON DIRECTION IT RUNS THE CHECK TILE, if it is not happening then the chacrater moves 

    
      int objectIndex = cChecker.checkObject(this, true); //Does collsion check for object 
      ObjectPick(objectIndex);//calls the method to do whats happens when it is colliding for the sword

      int bossIndex = cChecker.checkBoss(this, boss);//Does collsion check for object  
      interactBoss(bossIndex);//calls the method to do whats happens when it is colliding for the boss
      
      int freindNPCIndex = cChecker.checkNPC(this, freindNPC);//Does collsion check for npc 
      interactNPC(freindNPCIndex);//calls the method to do whats happens when it is colliding for the boss


      checkAttackKey();//Checks the attack key on if its pressed, if its pressed then attack is true 

      cChecker.checkPlayer(pc);//Boss to player collision checker
      cChecker.checkPlayer(fpc);//Freindly NPC to player collision checker


 
    //IF COLLISION IS FALSE THEN THEY CAN MOVE 
    if(collisionOn == false){//iF COLLSION IS FALSE THEN IT RUNS, if collsion is true then it wont un this making the character not able to move 
      switch(direction){
          case "up":
          worldY -= playerSpeed;//If the up key aka w is pressed, then chagne the dirction to up, and move the player position up by the player speed, in this case we minus from y 
          break;
          case "down": 
          worldY += playerSpeed;//If the down key is pressed aka s is pressed, then chagne the dirction to up, and move the player position down by the player speed, in this case we add to y 
          break;
          case "left":
           worldX -= playerSpeed;//If the left key is pressed aka the a key is predssed then change the direction to left and move the character postion to the left, in this case we minus from x
          break;
          case "right":
          worldX += playerSpeed;//If the right key is pressed aka the d key is predssed then change the direction to right and move the character postion to the right, in this case we add to x
          break;
      }
     }
    
    spriteCounter++;
    if (spriteCounter > 20){
        if(spriteNum == 1){
            spriteNum = 2;
        }
        else if (spriteNum == 2){
            spriteNum = 1;
        }
       spriteCounter = 0;  
    }
      //NPC boss to player damage, dead code that will be used, kept it for testing 
      if (inv == true){
        invCounter++;
        if(invCounter > 60){
          inv = false;
          invCounter = 0; 
        } 
      } 
    }
    
    for (int i = 0; i < boss.length; i++){  //Prints the boss 
        if (boss[i] != null){
          boss[i].update(this);//Updates the position and paints 
        } 
      }
    
    for (int i = 0; i < freindNPC.length; i++){  //Prints the freindlyNPC 
        if (freindNPC[i] != null){
          freindNPC[i].update(this);//Updates the position and paints 
        } 
      }
  }

  public void attacking(){
    
    // Saves the current x and y solid area 
    spriteCounter++;
    
    if(spriteCounter <= 500){
        spriteNum = 1;
    }
    if(spriteCounter > 300 && spriteCounter <= 2500){
        spriteNum = 2;
    }
    if(spriteCounter > 2500){
        spriteNum = 1;
        spriteCounter = 0; 
        attack = false; 
    }
    int currentWorldX = worldX;
    int currentWorldY = worldY;
    int solidAreaWidth = solidArea.width; 
    int solidAreaHeight = solidArea.height; 

    //Adjusting the area for the attack area 
    switch(direction){
        case"up":
        worldY -= attackArea.height;
        break;
        case"down":
        worldY += attackArea.height;
        break;
        case"left":
        worldX -= attackArea.width;
        break;
        case"right":
        worldX += attackArea.width;
        break;
    }

    //Sets the area, changes the size of the sold are to the attack area    
    solidArea.width = attackArea.width;
    solidArea.height = attackArea.height;

    //Check boss collision, with updates solid areas, along with world x and world y 
    int bossIndex = cChecker.checkBoss(this, boss);//Checks the boss collsion 
    damageBoss(bossIndex); //sends to the damae method to check if the boss is colliding, then if it is it takess images

    
    //Resets back to the orignal solid area and world coordinates
    worldX = currentWorldX;
    worldY = currentWorldY;
    solidArea.width = solidArea.width;
    solidArea.height = solidArea.height;
  
  }
  public void  checkAttackKey(){
      if (keyM.attackPressed == true) { //if player click j then attack = truw
      attack = true; 
    }
    if (keyM.attackPressed == false) { //otherwise attack false
      attack = false; 
    }
  }
  public void ObjectPick(int i){ //For object collsioin

    if(i != 999){//If the random index is not i, aka when the player is collisind witrh sword
      obj[i] = null; //the sword disapears 
      playSoundEffect(1);
       JOptionPane.showMessageDialog(null, "Blade of Eternity Picked up" + "\nClick J to attack" + "\nYou can use your sword while your moving");
    }
  }
public void interactBoss(int index) {

    if (index != 999) {//If player collides
        invCounter++; //counter increases

        if (invCounter % 60 == 0) {//Basically ultil 60
            Life--; // Decrease Life by 1
            playSoundEffect(2);

            if (Life == 0) {//until life is 0 where the game ends 
              JOptionPane.showMessageDialog(null, "Game Over" + "\nYou were not strong enough" + "\nThe game is going to exit NOW RETRY");
                System.exit(0);
            }
        }
    } else {
        invCounter = 0; // Reset invCounter when index is 999
    }
}
private int npcInteractionCounter = 0;

public void interactNPC(int i){
    if(i != 999 && npcInteractionCounter == 0){//If the random index is not i, aka when the player is collisind witrh sword
        JOptionPane.showMessageDialog(null, "Hello young one");//Dialog for NPC
        JOptionPane.showMessageDialog(null, "I am but an old man in the woods" + "\nLooking for someone to help me");
        JOptionPane.showMessageDialog(null, "I need your help " + "\nMonsters are attacking the sacred woods, you must help");
        JOptionPane.showMessageDialog(null, "Go upwards and all the way to the right " + " \nThere will be the blade of eternity over there");
        JOptionPane.showMessageDialog(null, "Pick it up and go downwards" + "\nAnd kill those monsters!");
        JOptionPane.showMessageDialog(null, "Watch out, they are a sticky bunch " + "\nIf you damage one of them, all of them get damaged " + "\nHowever, if you kill one, all of them go back to full health");
        npcInteractionCounter++;
    }
}

 private int lifeZeroCounter = 0;

public void damageBoss(int i) {
    if (i != 999) {// If player attack form collides with boss
        invCounter++; // Counter goes up

        if (invCounter % 20 == 0) {// Until basically 20, for an interval
            pc.Life--; // Decrease Life by 1

            if (pc.Life == 0) {// If it's zero
                lifeZeroCounter++; // Increment the lifeZeroCounter

                if (lifeZeroCounter == 6) { // If lifeZeroCounter reaches 6
                    boss[i] = null;
                    JOptionPane.showMessageDialog(null, "Congratulations you Defeated all the mosters and saved the forest" + "\nThe old man thanks you very much");
                    JOptionPane.showMessageDialog(null, "The game will exit now" + "\nRelaunch the game and search your name in the scores and search" + "\nYou will get your randomly generated score");
                    System.exit(0);
                    // Additional actions or end the game can be added here
                    lifeZeroCounter = 0; // Reset the counter after showing the message
                }

                pc.Life = 3; // Reset player's life
                boss[i] = null; // Remove the boss
            }
        }
    } else {
        invCounter = 0; // Resets counter for the interval
    }
}
  
  public void getPlayerImage(){//Get Player Image method 
    try{//Try this 
      up1 = ImageIO.read(getClass().getResourceAsStream("boy_up_1.png"));//ImageIo reads the file from the input stream, then retreives it as one getClass() returns the Class object for the current class, and getResourceAsStream retreives the recourse and stores it in the character bufferedimage variable 
      up2 = ImageIO.read(getClass().getResourceAsStream("boy_up_2.png"));//ImageIo reads the file from the input stream, then retreives it as one getClass() returns the Class object for the current class, and getResourceAsStream retreives the recourse and stores it in the character bufferedimage variable 
      down1 = ImageIO.read(getClass().getResourceAsStream("boy_down_1.png"));//ImageIo reads the file from the input stream, then retreives it as one getClass() returns the Class object for the current class, and getResourceAsStream retreives the recourse and stores it in the down bufferedimage variable 
      down2 = ImageIO.read(getClass().getResourceAsStream("boy_down_2.png"));//ImageIo reads the file from the input stream, then retreives it as one getClass() returns the Class object for the current class, and getResourceAsStream retreives the recourse and stores it in the character bufferedimage variable 
      left1 = ImageIO.read(getClass().getResourceAsStream("boy_left_1.png"));//ImageIo reads the file from the input stream, then retreives it as one getClass() returns the Class object for the current class, and getResourceAsStream retreives the recourse and stores it in the left bufferedimage variable 
      left2 = ImageIO.read(getClass().getResourceAsStream("boy_left_2.png"));//ImageIo reads the file from the input stream, then retreives it as one getClass() returns the Class object for the current class, and getResourceAsStream retreives the recourse and stores it in the left bufferedimage variable 
      right1 = ImageIO.read(getClass().getResourceAsStream("boy_right_1.png"));//ImageIo reads the file from the input stream, then retreives it as one getClass() returns the Class object for the current class, and getResourceAsStream retreives the recourse and stores it in the right bufferedimage variable 
      right2 = ImageIO.read(getClass().getResourceAsStream("boy_right_2.png"));//ImageIo reads the file from the input stream, then retreives it as one getClass() returns the Class object for the current class, and getResourceAsStream retreives the recourse and stores it in the left bufferedimage variable 
      }catch(IOException e) //IOException e) in the catch block, you are indicating that you are specifically interested in handling IOException
      {
        e.printStackTrace();//e.printStackTrace() is a method in Java that prints the stack trace of an exception to the standard error stream
      }
  }
  
  public void getPlayerAttackImage(){
 try{//Try this 
      attackUp1 = ImageIO.read(getClass().getResourceAsStream("boy_attack_up_1.png"));//ImageIo reads the file from the input stream, then retreives it as one getClass() returns the Class object for the current class, and getResourceAsStream retreives the recourse and stores it in the character bufferedimage variable 
      attackDown1 = ImageIO.read(getClass().getResourceAsStream("boy_attack_down_1.png"));//ImageIo reads the file from the input stream, then retreives it as one getClass() returns the Class object for the current class, and getResourceAsStream retreives the recourse and stores it in the down bufferedimage variable 
      attackLeft1 = ImageIO.read(getClass().getResourceAsStream("boy_attack_left_1.png"));//ImageIo reads the file from the input stream, then retreives it as one getClass() returns the Class object for the current class, and getResourceAsStream retreives the recourse and stores it in the left bufferedimage variable 
      attackRight1 = ImageIO.read(getClass().getResourceAsStream("boy_attack_right_1.png"));//ImageIo reads the file from the input stream, then retreives it as one getClass() returns the Class object for the current class, and getResourceAsStream retreives the recourse and stores it in the right bufferedimage variable 
      attackUp2 = ImageIO.read(getClass().getResourceAsStream("boy_attack_up_2.png"));//ImageIo reads the file from the input stream, then retreives it as one getClass() returns the Class object for the current class, and getResourceAsStream retreives the recourse and stores it in the character bufferedimage variable 
      attackDown2 = ImageIO.read(getClass().getResourceAsStream("boy_attack_down_2.png"));//ImageIo reads the file from the input stream, then retreives it as one getClass() returns the Class object for the current class, and getResourceAsStream retreives the recourse and stores it in the down bufferedimage variable 
      attackLeft2 = ImageIO.read(getClass().getResourceAsStream("boy_attack_left_2.png"));//ImageIo reads the file from the input stream, then retreives it as one getClass() returns the Class object for the current class, and getResourceAsStream retreives the recourse and stores it in the left bufferedimage variable 
      attackRight2 = ImageIO.read(getClass().getResourceAsStream("boy_attack_right_2.png"));
      }catch(IOException e) //IOException e) in the catch block, you are indicating that you are specifically interested in handling IOException
      {
        e.printStackTrace();//e.printStackTrace() is a method in Java that prints the stack trace of an exception to the standard error stream
      }      
    }
  
  public void paintComponent(Graphics g) {//Pain component takes care of mostly the character printing, and prints the tile as well 

    super.paintComponent(g);//super.paintComponent(g) is used to invoke the paintComponent() method and ensure that the default painting operations are performed before you add your custom painting code, from the JPanel
    
    Graphics2D g2D = (Graphics2D) g;

    //Tiles
  
    tileM.draw(g2D);//Draws all the tiles using the Graphics 2D

    //Object

    for (int i = 0; i < obj.length; i++) //If obj is not null then draw
      {
        if(obj[i] != null){
           

          obj[i].draw(g2D, this);
      
              
        }
     
      }

    //player life 
      
    int x = TSize/2;
    int y = TSize/2;
    int i = 0;


    //Blank hearts 
    while(i < maxLife/2){

      g2D.drawImage(heartLoss, x, y, TSize, TSize, null);
      i++; 
      x += TSize; 
    }

    //Reset value
     x = TSize/2;
     y = TSize/2;
     i = 0;

    //Draw max life 

    while(i < Life){

      g2D.drawImage(heartHalf, x, y, TSize, TSize, null);
      i++;
      
      if (i < Life){
         g2D.drawImage(heartFull, x, y, TSize, TSize, null);
      }
      i++;
      x += TSize; 
    }



      if(freindNPC[0] != null){
        freindNPC[0].draw(g2D, this);
      }
    
    
    //Boss

    for (int z = 0; z < boss.length; z++){

      if(boss[z] != null){
        boss[z].draw(g2D, this);
        
      }
    }
    

    //Player
    
    BufferedImage image = null;//Image is also a buffered image, however null means it does not currently refrence any specific BufferedImage 

    image = up1;//Gives the image variable a specific buffed image, in this case it would be character 

    switch(direction){ //Switch statement, smae as if, shorter and easier, switch direction
      case "up"://In the case the direction is up then use the character image 
        if (attack == false){
           if (spriteNum == 1){
               image = up1;
           }
           if (spriteNum == 2){
               image = up2;
           }          
        }    
        if (attack == true){
          if (spriteNum == 1){
               image = attackUp1;
           }
           if (spriteNum == 2){
               image = attackUp2;
           }  
        }
        break;
      case "down"://In the case the direction is down then use the character down
        if (attack == false){
            if (spriteNum == 1){
               image = down1;
           }
            if (spriteNum == 2){
               image = down2;
           }
        }    
        if (attack == true){
          if (spriteNum == 1){
               image = attackDown1;
           }
           if (spriteNum == 2){
               image = attackDown2;
           } 
        }
        break;
      case "left"://In the case the direction is left then use the character left
        if (attack == false){
             if (spriteNum == 1){
               image = left1;
           }
            if (spriteNum == 2){
               image = left2;
           }
        }   
        if (attack == true){
          if (spriteNum == 1){
               image = attackLeft1;
           }
           if (spriteNum == 2){
               image = attackLeft2;
           } 
        }
        break;
      case "right"://In the case the direction is right then use the character right
        if (attack == false){
            if (spriteNum == 1){
               image = right1;
           }
            if (spriteNum == 2){
               image = right2;
           }
        }   
        if (attack == true){
          if (spriteNum == 1){
               image = attackRight1;
           }
           if (spriteNum == 2){
               image = attackRight2;
           } 
        }
        break;
    }

    g2D.drawImage(image, ScreenX, ScreenY, TSize, TSize, null);//Draws the immage that is refrenced by the key, (if it is w then it will print the charcter image etc..), px and py are the base coordinates to where the image is printed, in this casee px and py are the middle of the screen. Tsize and Tsize are the x and y sizes, in this case their both 48 pixels, and null is set for the ImageObserver, meaning that no specific object is used to observe the image 


    g.dispose();//Good practice to implement this since it clears up any unnecessary items within the graphics,this helps clear up memory within the grpahics method 
  }
  public void setObject(){

    obj[0] = new ObjectSword(); //Sest objects coordinates on map place sword
    obj[0].WorldX = TSize * 50; 
    obj[0].WorldY = TSize * 4; 
    
  }
  public void setBoss(){

    boss[0] = new Boss(this); //sets bosses coordinates on the map 
    boss[0].WorldX = TSize * 50;
    boss[0].WorldY = TSize * 15;
    
    boss[1] = new Boss(this); //sets bosses coordinates on the map 
    boss[1].WorldX = TSize * 49;
    boss[1].WorldY = TSize * 14;
    
    boss[2] = new Boss(this); //sets bosses coordinates on the map 
    boss[2].WorldX = TSize * 51;
    boss[2].WorldY = TSize * 16;
    
    boss[3] = new Boss(this); //sets bosses coordinates on the map 
    boss[3].WorldX = TSize * 45;
    boss[3].WorldY = TSize * 13;
    
    boss[4] = new Boss(this); //sets bosses coordinates on the map 
    boss[4].WorldX = TSize * 53;
    boss[4].WorldY = TSize * 14;
    
    boss[5] = new Boss(this); //sets bosses coordinates on the map 
    boss[5].WorldX = TSize * 47;
    boss[5].WorldY = TSize * 16;
    
    freindNPC[0] = new OldMan(this);//Sets old man NPC Cords
    freindNPC[0].WorldX = TSize * 26;
    freindNPC[0].WorldY = TSize * 14;
        
  } 
  public void playMusic(int i){//Play music method
      
      sound.setFile(i);
      sound.play();
      sound.loop();
  }
  public void stopMusic(){//Stop music methid
      
      sound.stop();
  }
  public void playSoundEffect(int i){ //play sound effect method
      
      sound.setFile(i);
      sound.play();
  }
}
