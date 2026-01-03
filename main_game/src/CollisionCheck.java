/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jaspr
 */
public class CollisionCheck {
    GamePanel gp;
NPC npc = new NPC();
FreindlyNPC fnpc = new FreindlyNPC();
  
  public CollisionCheck(GamePanel gp){
    this.gp = gp;
  }

  public void checkTile(GamePanel gp) {

    int entityLeftWorldX = gp.worldX + gp.solidArea.x;//Finds the left side of the triangle and where it is on the map, adds it to the x coordinate
    int entityRightWorldX = gp.worldX + gp.solidArea.x + gp.solidArea.width;//same thing as beefore but adds the width to get the other x side 
    int entityToptWorldY = gp.worldY + gp.solidArea.y;//Finds where the top part of the rectangle is 
    int entityBottomtWorldY = gp.worldY + gp.solidArea.y + gp.solidArea.height;//Adds the height to get the bottom side 

    //FIND THE RECTANGLE COL AND ROW NUMBERS

    int entityLefCol = entityLeftWorldX / gp.TSize;//gets how many tiles away they are
    int entityRightCol = entityRightWorldX / gp.TSize;//gets how many tiles away they are 
    int entityTopRow = entityToptWorldY / gp.TSize;//gets how many tiles away they are 
    int entityBottomRow = entityBottomtWorldY / gp.TSize;//gets how many tiles away they are 

    
    int tileNum1, tileNum2;//Represent 2 tiles, since for collision you only need to ckeck 2 tiles in each direction 

      switch(gp.direction){//Switch the direction
       case "up"://In the case the direction is up 
          entityTopRow = (entityToptWorldY - gp.playerSpeed)/gp.TSize;//Takes the world y position and adds one of the player speed, gives the tile of where the player would be if they move in this direction, it almost like assuming where the player would end up for thiws specific direction, dividing that by tile size gives the row 
          if (entityLefCol <= gp.tileM.mapTileNum.length && entityTopRow <=gp.tileM.mapTileNum[entityLefCol].length){
          tileNum1 = gp.tileM.mapTileNum[entityLefCol][entityTopRow];//Tile number of where the character would have been moving up left side
          tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];//Tile number of where the character would have been moving up right side
          if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){//Checks if tilenum1 is a solid tile and checks if tilenum 2 is a solid tile or not 
          gp.collisionOn = true;//It either is, then collision will start 
          }
          }
       break;//Then break 
       case "down":
          
          entityBottomRow = (entityBottomtWorldY + gp.playerSpeed)/gp.TSize;//Takes the world y position and minuses one of the player speed, gives the tile of where the player would be if they move in this direction, it almost like assuming where the player would end up for thiws specific direction, dividing that by tile size gives the row (for when they going down)
          if (entityLefCol <= gp.tileM.mapTileNum.length && entityTopRow <=gp.tileM.mapTileNum[entityLefCol].length){
          tileNum1 = gp.tileM.mapTileNum[entityLefCol][entityBottomRow];//Takes the bottom postion for left
          tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];//Takes the bottom postion for lright
          if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){//Checks if tilenum1 is a solid tile and checks if tilenum 2 is a solid tile or not
          gp.collisionOn = true;//It either is, then collision will start
          }
          }
       break;
       case "left":
          entityLefCol = (entityLeftWorldX - gp.playerSpeed)/gp.TSize;//Assumes player position when moving left by a tile  
          if (entityLefCol <= gp.tileM.mapTileNum.length && entityTopRow <=gp.tileM.mapTileNum[entityLefCol].length){
          tileNum1 = gp.tileM.mapTileNum[entityLefCol][entityTopRow];//left top
          tileNum2 = gp.tileM.mapTileNum[entityLefCol][entityBottomRow];//left bottom
          if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){//Checks if tilenum1 is a solid tile and checks if tilenum 2 is a solid tile or not
          gp.collisionOn = true;//It either is, then collision will start
          }
          }
       break;
       case "right":
          entityRightCol = (entityRightWorldX - gp.playerSpeed)/gp.TSize;//Assumes player position when moving right by a tile  
          if (entityLefCol <= gp.tileM.mapTileNum.length && entityTopRow <=gp.tileM.mapTileNum[entityLefCol].length){
          tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];//right top
          tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];//right bottom
          if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){//Checks if tilenum1 is a solid tile and checks if tilenum 2 is a solid tile or not
          gp.collisionOn = true;//It either is, then collision will start
          }
          }
       break;
    } 
  }

    public void checkTileBoss(NPC npc) {

    int entityLeftWorldX = npc.WorldX + npc.solidArea.x;//Finds the left side of the triangle and where it is on the map, adds it to the x coordinate
    int entityRightWorldX =  npc.WorldX + npc.solidArea.x + npc.solidArea.width;//same thing as beefore but adds the width to get the other x side 
    int entityToptWorldY =  npc.WorldY + npc.solidArea.y;//Finds where the top part of the rectangle is 
    int entityBottomtWorldY =  npc.WorldY + npc.solidArea.y + npc.solidArea.height;//Adds the height to get the bottom side 

    //FIND THE RECTANGLE COL AND ROW NUMBERS

    int entityLefCol = entityLeftWorldX / gp.TSize;//gets how many tiles away they are
    int entityRightCol = entityRightWorldX / gp.TSize;//gets how many tiles away they are 
    int entityTopRow = entityToptWorldY / gp.TSize;//gets how many tiles away they are 
    int entityBottomRow = entityBottomtWorldY / gp.TSize;//gets how many tiles away they are 

    
    int tileNum1, tileNum2;//Represent 2 tiles, since for collision you only need to ckeck 2 tiles in each direction 

      switch(npc.direction){//Switch the direction
       case "up"://In the case the direction is up 


          try{
             // if (entityLefCol == 35 && entityLeftWorldX==48) {
            entityTopRow = (entityToptWorldY - npc.speed)/gp.TSize;//Takes the world y position and adds one of the player speed, gives the tile of where the player would be if they move in this direction, it almost like assuming where the player would end up for thiws specific direction, dividing that by tile size gives the row 
          tileNum1 = gp.tileM.mapTileNum[entityLefCol][entityTopRow];//Tile number of where the character would have been moving up left side
          tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];//Tile number of where the character would have been moving up right side
          if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){//Checks if tilenum1 is a solid tile and checks if tilenum 2 is a solid tile or not 
            gp.collisionOn = true;//It either is, then collision will start 
          }
         // }
          }catch(Exception e){

            System.out.println(entityLefCol); //This is done due to exception errors
            System.out.println(gp.tileM.mapTileNum);
            System.out.println(entityTopRow);
            System.out.println(entityLeftWorldX);
            System.out.println(gp.TSize);
            e.printStackTrace();
            
          }

          
       break;//Then break 
       case "down":
          entityBottomRow = (entityBottomtWorldY + npc.speed)/gp.TSize;//Takes the world y position and minuses one of the player speed, gives the tile of where the player would be if they move in this direction, it almost like assuming where the player would end up for thiws specific direction, dividing that by tile size gives the row (for when they going down)
          tileNum1 = gp.tileM.mapTileNum[entityLefCol][entityBottomRow];//Takes the bottom postion for left
          tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];//Takes the bottom postion for lright
          if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){//Checks if tilenum1 is a solid tile and checks if tilenum 2 is a solid tile or not
          gp.collisionOn = true;//It either is, then collision will start
          }
       break;
       case "left":
          entityLefCol = (entityLeftWorldX - npc.speed)/gp.TSize;//Assumes player position when moving left by a tile  
          tileNum1 = gp.tileM.mapTileNum[entityLefCol][entityTopRow];//left top
          tileNum2 = gp.tileM.mapTileNum[entityLefCol][entityBottomRow];//left bottom
          if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){//Checks if tilenum1 is a solid tile and checks if tilenum 2 is a solid tile or not
          gp.collisionOn = true;//It either is, then collision will start
          }
       break;
       case "right":
        try {
            //if (entityRightWorldX == 48 || gp.TSize == 36) {
                entityRightCol = (entityRightWorldX - npc.speed) / gp.TSize; // Assumes player position when moving right by a tile
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow]; // right top
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow]; // right bottom
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    gp.collisionOn = true; // If either tileNum1 or tileNum2 is a solid tile, set collisionOn to true
                }
            //}
        } catch (ArrayIndexOutOfBoundsException e) {
            // Handle the exception if it occurs
            e.printStackTrace(); // For debugging purposes, print the stack trace
            gp.collisionOn = false; // Set collisionOn to false in case of an exception
        }
       break;
    } 
  }
        public void checkTileFreindNPC(FreindlyNPC npc) {

    int entityLeftWorldX = npc.WorldX + npc.solidArea.x;//Finds the left side of the triangle and where it is on the map, adds it to the x coordinate
    int entityRightWorldX =  npc.WorldX + npc.solidArea.x + npc.solidArea.width;//same thing as beefore but adds the width to get the other x side 
    int entityToptWorldY =  npc.WorldY + npc.solidArea.y;//Finds where the top part of the rectangle is 
    int entityBottomtWorldY =  npc.WorldY + npc.solidArea.y + npc.solidArea.height;//Adds the height to get the bottom side 

    //FIND THE RECTANGLE COL AND ROW NUMBERS

    int entityLefCol = entityLeftWorldX / gp.TSize;//gets how many tiles away they are
    int entityRightCol = entityRightWorldX / gp.TSize;//gets how many tiles away they are 
    int entityTopRow = entityToptWorldY / gp.TSize;//gets how many tiles away they are 
    int entityBottomRow = entityBottomtWorldY / gp.TSize;//gets how many tiles away they are 

    
    int tileNum1, tileNum2;//Represent 2 tiles, since for collision you only need to ckeck 2 tiles in each direction 

      switch(npc.direction){//Switch the direction
       case "up"://In the case the direction is up 


          try{
             // if (entityLefCol == 35 && entityLeftWorldX==48) {
            entityTopRow = (entityToptWorldY - npc.speed)/gp.TSize;//Takes the world y position and adds one of the player speed, gives the tile of where the player would be if they move in this direction, it almost like assuming where the player would end up for thiws specific direction, dividing that by tile size gives the row 
          tileNum1 = gp.tileM.mapTileNum[entityLefCol][entityTopRow];//Tile number of where the character would have been moving up left side
          tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];//Tile number of where the character would have been moving up right side
          if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){//Checks if tilenum1 is a solid tile and checks if tilenum 2 is a solid tile or not 
            gp.collisionOn = true;//It either is, then collision will start 
          }
         // }
          }catch(Exception e){

            System.out.println(entityLefCol); //This is done due to exception errors
            System.out.println(gp.tileM.mapTileNum);
            System.out.println(entityTopRow);
            System.out.println(entityLeftWorldX);
            System.out.println(gp.TSize);
            e.printStackTrace();
            
          }

          
       break;//Then break 
       case "down":
          entityBottomRow = (entityBottomtWorldY + npc.speed)/gp.TSize;//Takes the world y position and minuses one of the player speed, gives the tile of where the player would be if they move in this direction, it almost like assuming where the player would end up for thiws specific direction, dividing that by tile size gives the row (for when they going down)
          tileNum1 = gp.tileM.mapTileNum[entityLefCol][entityBottomRow];//Takes the bottom postion for left
          tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];//Takes the bottom postion for lright
          if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){//Checks if tilenum1 is a solid tile and checks if tilenum 2 is a solid tile or not
          gp.collisionOn = true;//It either is, then collision will start
          }
       break;
       case "left":
          entityLefCol = (entityLeftWorldX - npc.speed)/gp.TSize;//Assumes player position when moving left by a tile  
          tileNum1 = gp.tileM.mapTileNum[entityLefCol][entityTopRow];//left top
          tileNum2 = gp.tileM.mapTileNum[entityLefCol][entityBottomRow];//left bottom
          if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){//Checks if tilenum1 is a solid tile and checks if tilenum 2 is a solid tile or not
          gp.collisionOn = true;//It either is, then collision will start
          }
       break;
       case "right":
        try {
            //if (entityRightWorldX == 48 || gp.TSize == 36) {
                entityRightCol = (entityRightWorldX - npc.speed) / gp.TSize; // Assumes player position when moving right by a tile
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow]; // right top
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow]; // right bottom
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    gp.collisionOn = true; // If either tileNum1 or tileNum2 is a solid tile, set collisionOn to true
                }
            //}
        } catch (ArrayIndexOutOfBoundsException e) {
            // Handle the exception if it occurs
            e.printStackTrace(); // For debugging purposes, print the stack trace
            gp.collisionOn = false; // Set collisionOn to false in case of an exception
        }
       break;
    } 
  }
  public int checkObject(GamePanel gp, boolean player){//Collsion checker for the object Sword 
    
   int index = 999; // Sets random index

    for (int i = 0; i < gp.obj.length; i++) { // Runs to the amount of swords in the game
        if (gp.obj[i] != null) { // If it exists
            gp.solidArea.x = gp.worldX + gp.solidArea.x; // Solid area for player
            gp.solidArea.y = gp.worldY + gp.solidArea.y;

            gp.obj[i].solidArea.x = gp.obj[i].WorldX + gp.obj[i].solidArea.x; // Solid area for sword
            gp.obj[i].solidArea.y = gp.obj[i].WorldY + gp.obj[i].solidArea.y;

            switch (gp.direction) {// Direction
                case "up":
                    gp.solidArea.y -= gp.playerSpeed; // Checks the solid area of the player's direction, checks the next tile before the player gets there
                    break;
                case "down":
                    gp.solidArea.y += gp.playerSpeed;
                    break;
                case "left":
                    gp.solidArea.x -= gp.playerSpeed;
                    break;
                case "right":
                    gp.solidArea.x += gp.playerSpeed;
                    break;
            }

            if (gp.solidArea.intersects(gp.obj[i].solidArea)) { // If the object's solid area intersects with player's intersection, then collision is true for the object
                if (gp.obj[i].collision == true) {
                    gp.collisionOn = true; // Turns on collision resisting player movement
                }
                if (player == true) { // If the player is true
                    index = i; // The index i take for up
                }
            }

            gp.solidArea.x = gp.solidAreaDefaultX; // Sets solid area to the default solid area, moving back to its original place for the player
            gp.solidArea.y = gp.solidAreaDefaultY;

            gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX; // Sets solid area to the default solid area, moving back to its original place for the object
            gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
        }
    }

    return index; // Returns that index

  }
  
 public int checkBoss(GamePanel gp, NPC[] target) {//Checks collsion from player to boss
     
  int index = 999;//Sets random index for collsion 

  for (int i = 0; i < target.length; i++) {//run to the amount of bosses in the game 
    if (target[i] != null) {//if exsits 
      gp.solidArea.x = gp.worldX - gp.solidArea.x;//Solid area for player 
      gp.solidArea.y = gp.worldY - gp.solidArea.y;

      target[i].solidArea.x = target[i].WorldX - target[i].solidArea.x;//Solid area for boss 
      target[i].solidArea.y = target[i].WorldY - target[i].solidArea.y;

      switch (gp.direction) {//Direction in gamepanel 
        case "up":
          gp.solidArea.y -= gp.playerSpeed;//checks players poition one tile ahead in thea direction 
          break;

        case "down":
          gp.solidArea.y += gp.playerSpeed;
          break;

        case "left":
          gp.solidArea.x -= gp.playerSpeed;
          break;

        case "right":
          gp.solidArea.x += gp.playerSpeed;
          break;
      }
            if (gp.solidArea.intersects(target[i].solidArea)) {//if that position intersects with the boss solid area               
               
                gp.collisionOn = true;//collsion true
                index = i;//and index is i for when it collides 
          }

      gp.solidArea.x = gp.solidAreaDefaultX;//Back to default solid area for player 
      gp.solidArea.y = gp.solidAreaDefaultY;
      target[i].solidArea.x = target[i].solidAreaDefaultX;//Back to default solid area for player 
      target[i].solidArea.y = target[i].solidAreaDefaultY;
    }
  }

  return index;//Returns the index
}
 
 public int checkNPC(GamePanel gp, FreindlyNPC[] target) {//Checks collsion from player to boss
     
  int index = 999;//Sets random index for collsion 

  for (int i = 0; i < target.length; i++) {//run to the amount of bosses in the game 
    if (target[i] != null) {//if exsits 
      gp.solidArea.x = gp.worldX - gp.solidArea.x;//Solid area for player 
      gp.solidArea.y = gp.worldY - gp.solidArea.y;

      target[i].solidArea.x = target[i].WorldX - target[i].solidArea.x;//Solid area for boss 
      target[i].solidArea.y = target[i].WorldY - target[i].solidArea.y;

      switch (gp.direction) {//Direction in gamepanel 
        case "up":
          gp.solidArea.y -= gp.playerSpeed;//checks players poition one tile ahead in thea direction 
          break;

        case "down":
          gp.solidArea.y += gp.playerSpeed;
          break;

        case "left":
          gp.solidArea.x -= gp.playerSpeed;
          break;

        case "right":
          gp.solidArea.x += gp.playerSpeed;
          break;
      }
            if (gp.solidArea.intersects(target[i].solidArea)) {//if that position intersects with the boss solid area               
               
                gp.collisionOn = true;//collsion true
                index = i;//and index is i for when it collides 
          }

      gp.solidArea.x = gp.solidAreaDefaultX;//Back to default solid area for player 
      gp.solidArea.y = gp.solidAreaDefaultY;
      target[i].solidArea.x = target[i].solidAreaDefaultX;//Back to default solid area for player 
      target[i].solidArea.y = target[i].solidAreaDefaultY;
    }
  }

  return index;//Returns the index
}

  public void checkPlayer(NPC npc){//Checks collsion for boss to player

      npc.solidArea.x = npc.WorldX - npc.solidArea.x;//Solid area for player 
      npc.solidArea.y = npc.WorldY - npc.solidArea.y;

      gp.solidArea.x = gp.worldX - gp.solidArea.x;//Solid area for player 
      gp.solidArea.y = gp.worldY - gp.solidArea.y;


      switch (npc.direction) {//Direction in go 
        case "up":
          npc.solidArea.y -= npc.speed;//Checks the boss solid are one tile ahead in that directionj 
          if (npc.solidArea.intersects(gp.solidArea)) {//if that solid area intersects with the player solid area 
            gp.collisionOn = true;//collsion is true 
          }
          break;

        case "down":
           npc.solidArea.y += npc.speed;
          if (npc.solidArea.intersects(gp.solidArea)) {
            gp.collisionOn = true;
   
          }
          break;

        case "left":
           npc.solidArea.x -= npc.speed;
          if (npc.solidArea.intersects(gp.solidArea)) {
            gp.collisionOn = true;
  
          }
          break;

        case "right":
           npc.solidArea.y += npc.speed;
          if (npc.solidArea.intersects(gp.solidArea)) {
            gp.collisionOn = true;
       
          }
          break;
      }

      npc.solidArea.x = npc.solidAreaDefaultX;//Resets the deafualt solid area for boss
      npc.solidArea.y = npc.solidAreaDefaultY;

      gp.solidArea.x = gp.solidAreaDefaultX;//Resets the deafualt solid area for player 
      gp.solidArea.y = gp.solidAreaDefaultY; 
  }
    public void checkPlayer(FreindlyNPC npc){//Checks collsion for boss to player

      npc.solidArea.x = npc.WorldX - npc.solidArea.x;//Solid area for player 
      npc.solidArea.y = npc.WorldY - npc.solidArea.y;

      gp.solidArea.x = gp.worldX - gp.solidArea.x;//Solid area for player 
      gp.solidArea.y = gp.worldY - gp.solidArea.y;


      switch (npc.direction) {//Direction in go 
        case "up":
          npc.solidArea.y -= npc.speed;//Checks the boss solid are one tile ahead in that directionj 
          if (npc.solidArea.intersects(gp.solidArea)) {//if that solid area intersects with the player solid area 
            gp.collisionOn = true;//collsion is true 
          }
          break;

        case "down":
           npc.solidArea.y += npc.speed;
          if (npc.solidArea.intersects(gp.solidArea)) {
            gp.collisionOn = true;
   
          }
          break;

        case "left":
           npc.solidArea.x -= npc.speed;
          if (npc.solidArea.intersects(gp.solidArea)) {
            gp.collisionOn = true;
  
          }
          break;

        case "right":
           npc.solidArea.y += npc.speed;
          if (npc.solidArea.intersects(gp.solidArea)) {
            gp.collisionOn = true;
       
          }
          break;
      }

      npc.solidArea.x = npc.solidAreaDefaultX;//Resets the deafualt solid area for boss
      npc.solidArea.y = npc.solidAreaDefaultY;

      gp.solidArea.x = gp.solidAreaDefaultX;//Resets the deafualt solid area for player 
      gp.solidArea.y = gp.solidAreaDefaultY; 
  }
}
