/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.Color;
import java.io.*;
/**
 *
 * @author jaspr
 */
public class ManageTile extends Tile {
    GamePanel gp; //Makes variable for gmaepanel to be used in this class 

  Tile[] tile = new Tile[8]; //Array of tiles, set to 8 since there might be 8 tiles, however right now there is only 4

  public int mapTileNum[][] = new int[gp.maxWorldCol][gp.maxWorldRow];//All the numbers in the map txt file will be stored here 

  public ManageTile(GamePanel gp) { //Manage tile method 

    this.gp = gp; //Assigns this.gp to the varibale gp 

    getTile();//Loads the get tile method 
    LoadMap("tile.txt");//After that is loaded then it loads the loadmap method, however there is a tile txt folder wich holds the map in the form of numbers
  }

  public void getTile() { //Get tile method  
    try {

      tile[0] = new Tile();//Creates a new instance of the tile, wich will be used to assign object to index 0 or 1
      tile[0].image = ImageIO.read(getClass().getResourceAsStream("grassBlock.png"));//Assigning as recourse to the first tile, in trhis case buffered image is used then ot sends to a class gets that and recives it as a recourse file whivh is the picture of the grass block, buffered image tile was made in tile class

      tile[1] = new Tile();//Creates a new instance of the tile, wich will be used to assign object to index 2
      tile[1].image = ImageIO.read(getClass().getResourceAsStream("dirtBlock.png"));//Assigning as recourse to the first tile, in trhis case buffered image is used then ot sends to a class gets that and recives it as a recourse file whivh is the picture of the dirt block, buffered image tile was made in tile class

      tile[2] = new Tile();//Creates a new instance of the tile, wich will be used to assign object to index 3
      tile[2].image = ImageIO.read(getClass().getResourceAsStream("treeBlock.png"));//Assigning as recourse to the first tile, in trhis case buffered image is used then ot sends to a class gets that and recives it as a recourse file whivh is the picture of the tree block, buffered image tile was made in tile class
      tile[2].collision = true;//Collision set to true here since this block will make it impossible for the player to get by, orignally set false in tile class

      tile[3] = new Tile();//Creates a new instance of the tile, wich will be used to assign object to index 4
      tile[3].image = ImageIO.read(getClass().getResourceAsStream("stoneBlock2.png"));//Assigning as recourse to the first tile, in trhis case buffered image is used then ot sends to a class gets that and recives it as a recourse file whivh is the picture of the stone block, buffered image tile was made in tile class
      tile[3].collision = true;//Collision set to true here since this block will make it impossible for the player to get by, orignally set false in tile class
      
      tile[4] = new Tile();//Creates a new instance of the tile, wich will be used to assign object to index 4
      tile[4].image = ImageIO.read(getClass().getResourceAsStream("waterBlock.png"));//Assigning as recourse to the first tile, in trhis case buffered image is used then ot sends to a class gets that and recives it as a recourse file whivh is the picture of the stone block, buffered image tile was made in tile class
      tile[4].collision = true;//Collision set to true here since this block will make it impossible for the player to get by, orignally set false in tile class
      
      tile[5] = new Tile();//Creates a new instance of the tile, wich will be used to assign object to index 4
      tile[5].image = ImageIO.read(getClass().getResourceAsStream("bridgeBlock.png"));//Assigning as recourse to the first tile, in trhis case buffered image is used then ot sends to a class gets that and recives it as a recourse file whivh is the picture of the stone block, buffered image tile was made in tile class
      


    } catch (IOException e) {
      e.printStackTrace(); //Used to handle exceptions and errors
    }
  }

  public void LoadMap(String filePath) {//String filepath since we loaded a file into the method 

    try { //Try this 
      InputStream is = getClass().getResourceAsStream(filePath);
      BufferedReader br = new BufferedReader(new InputStreamReader(is));


      int col = 0;//Colum count 
      int row = 0;//Row count 

      while (col < gp.maxWorldCol && row < gp.maxWorldRow) {//This is the limit of the loop, since the map does not go past this and it does not have data to receive 

        String line = br.readLine();//Will read text file and read a line and put it in the line variable 

        while (col < gp.maxWorldCol) {//While the coloum count is lower that the max colum count do this code 

          String[] nums = line.split(" ");//From the line that was read, there will be numbers taken from that line, iteach number will be split and kept differnt from eachother based of wether they haev a space beetween them, if they do then the line gets split between those two 
          int num = Integer.parseInt(nums[col]);//Col is basically the index number here, changing the nums to integers , this is so it can be used as a number 

          mapTileNum[col][row] = num; //Stores first the collum values, then keeps on incrementingg in ones, when it reaches its mac colum size for the forst row, the system moves to the next row and stores that in the row section with the previous collum stored in the col section, by assigning num we are storing speicif integers for the col and number of the row 
          col++;//Goes up in increments of one until it reaches the max, then it goes to next row 
        }
        if (col == gp.maxWorldCol) { //If the colum reaches the maxium collum length 
          col = 0; //sets col to zero again, when this happens the rows start to form, when that coloum on that row reaches the max then col resets to 0 again, and then it goes to the next colum 
          row++;//Rows form in increments of one when the colum reaches max in the previous row 
        }
      }

      br.close();//Good practice to close the buffered reader since it can cause issues 

      } catch (Exception e) {
          e.printStackTrace();
    }
  }

  public void draw(Graphics2D g2) { //MAIN METHOD FOR THE GAME CAMERA
    int worldCol = 0;
    int worldRow = 0;

    while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

      int tileNum = mapTileNum[worldCol][worldRow];//Stores wich col and row

      int worldX = worldCol * gp.TSize;//Position on the map is wrold x for x, checkc the tiles world x, wich is the col times the tile size cnahges through each loop, basically gives coordinates 
      int worldY = worldRow * gp.TSize;//Position on the map is wrold y for y, checkc the tiles world y, wich is the col times the tile size cnahges through each loop, basically gives coordinateS, 
      int screenX = worldX - gp.worldX + gp.ScreenX;//World x is the position or cords, and screen x is where you draw it on those cords, subtracting the world x moves the screen to the correct postion, if the character was 500 pixels away from the 0,0 point, this would make it so the 0,0 point is printed at the 500 pixel away spot giving the allusion of the camera movement, minuses the character orginal position in the center from this classes world x to give the cords, adding the screen x offsets the differenc and puts the character back in the middle rather than the 0,0 point 
      int screenY = worldY - gp.worldY + gp.ScreenY;//WORLD Y REPRESESNTS THE COORDNITE OF A VERTICLE TILE, gp.worldY represents the vertical offset of the game world within the game panel. gp.ScreenY represents the vertical offset of the visible screen (the 0,0 cords) area within the game, MINUSING THE FORST 2 GIVES the vettical position of a tile relative to the visible screen

        g2.drawImage(tile[tileNum].image, screenX, screenY, gp.TSize, gp.TSize, null);//Prints the screen x and y for the tiles
      worldCol++;

      if (worldCol == gp.maxWorldCol) { //When worlcol reaches its max them it sets to zero and moves to the next row and increases in increments of 1
        worldCol = 0;
        worldRow++;
      }
    }
  }
}
