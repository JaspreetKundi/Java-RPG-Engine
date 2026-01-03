/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;
import java.io.FileWriter;
import java.lang.Math;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collections;
/**
 *
 * @author jaspr
 */
public class Menu extends JPanel {
      //BACKGROUND IMAGE 
  
  private String BGimage = "Blades (1).png";
  private BufferedImage background;

  public Menu() {
    try {
      background = ImageIO.read(getClass().getResource(BGimage));//SETS BACKGROUND AS IMAGE
    } catch (IOException e) {
      e.printStackTrace();
    }

    //NEW WINDOW

    setPreferredSize(new Dimension(768, 576)); // Set the preferred size of the panel
    setLayout(new BorderLayout()); // Use BorderLayout for centering the button panel

    //MAKES LAYOUT FOR BUTTONPANEL
    
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
    buttonPanel.setOpaque(false);
    buttonPanel.setBorder(new EmptyBorder(310, 0, 0, 0)); // Add empty space above the buttons

    //CREATES BUTTONS AND MESSAGE 

    JButton play = new JButton("Start Game");
    JButton stop = new JButton("Quit Game");
    JButton help = new JButton("Help (Click this first)");
    JButton scores = new JButton("Scores and Search"); 
    JLabel message = new JLabel("Search for either (Help,Controls,About)");
    JButton clearScores = new JButton("Clear High Scores");

    //SIZE OF THE BUTTON
    Dimension buttonSize = new Dimension(200, 40); // Set the desired size for buttons

    //SETS SIZE OF BUTTONS
    play.setPreferredSize(buttonSize);
    stop.setPreferredSize(buttonSize);
    help.setPreferredSize(buttonSize);
    scores.setPreferredSize(buttonSize);

    //SETS COLOR OF BUTTONS
    play.setBackground(Color.BLACK);
    stop.setBackground(Color.BLACK);
    help.setBackground(Color.BLACK);
    scores.setBackground(Color.BLACK);

    //SETS TEXT COLOR FOR BUTTONS
    play.setForeground(Color.WHITE);
    stop.setForeground(Color.WHITE);
    help.setForeground(Color.WHITE);
    scores.setForeground(Color.WHITE);
    
    //SETS BORDER FOR BUTTON AND MAKES IT WHITE
    play.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
    stop.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
    help.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
    scores.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
    
        clearScores.setPreferredSize(buttonSize);
        clearScores.setBackground(Color.BLACK);
        clearScores.setForeground(Color.WHITE);
     clearScores.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
     clearScores.setAlignmentX(Component.CENTER_ALIGNMENT);


    //ALLIGNS IT IN THE CENTER
    play.setAlignmentX(Component.CENTER_ALIGNMENT);
    stop.setAlignmentX(Component.CENTER_ALIGNMENT);
    help.setAlignmentX(Component.CENTER_ALIGNMENT);
    scores.setAlignmentX(Component.CENTER_ALIGNMENT);

    play.addActionListener(new ActionListener() { //WHEN PLAYER CLICKS START GAME
      public void actionPerformed(ActionEvent e) {

      GamePanel gamepanel = new GamePanel();

      //ASKS FOR NAME WHEN PLAYER CLICKS START GAME
        
      String name = JOptionPane.showInputDialog(null, "Enter your name:", "Name Input", JOptionPane.PLAIN_MESSAGE);
      
        
        // Trigger the game
       
        gamepanel.startGameThread();//STARTS GAME THREAD

        gamepanel.playMusic(0);
        try{
          int scoreLife = gamepanel.Life; //TAKES THE LIFE
        int FinalScore = (scoreLife * 1000) - (int) (Math.random() * 1000) + 1;; //TIMES THE LIFE BY 1000 AND MINUSES RANDOM AMOUNT NUMBER TO GIVE A RANDOM SCRE
        FileWriter writer = new FileWriter("HighScore.txt", true); //CREATES FILEWROTE 
        writer.write("\n" + name + "," + FinalScore); // PRINTS THE NEW SCORE IN THE HighScore text file 
        writer.close();//CLOSES WRITER      
        }catch (IOException f){  
        }
        
        // Remove the main menu panel
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(Menu.this);//CREATES A NEW FRAME 
        frame.remove(Menu.this);//REMOVES THE MENU SCREEN AFTER THE PLAYER INPUTS NAME AND PLAYER CLIKC TART GAME 

        // Add the game panel
        frame.add(gamepanel);//INSTEAD, THE FRAM ADDS THE GAMEPANEL CONTRUCTOR WICH RUNS THE GAME
        frame.pack(); //PACKS SCREEN
        frame.revalidate();
        frame.setLocationRelativeTo(null);
        frame.repaint();

        // Request focus on the game panel
        gamepanel.requestFocusInWindow();//FOCUSES WINDOW
      }
    });

    stop.addActionListener(new ActionListener() { //IF THE EXIT BUTTON IS CLICKED THE GAME CLOSES
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    });

    help.addActionListener(new ActionListener() {//IF HELP IS CLICKED
      public void actionPerformed(ActionEvent e) {
        if (e.getSource() == help) {

          JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(Menu.this);
          frame.remove(Menu.this);//AGAIN REMOVES THE MENU 
          frame.dispose();
          
          //MAKES NEW FRAME
          final JFrame fr = new JFrame();
          fr.getContentPane().setBackground(Color.BLACK);
          fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          fr.setSize(768, 576);
          
          //MAKES JLABEL METHOD
          JLabel message = new JLabel("Search for Either (Help,About,Controls)");
          message.setForeground(Color.WHITE);
          // message.setHorizontalAlignment(SwingConstants.CENTER);
          message.setFont(message.getFont().deriveFont(Font.BOLD, 17f));
          
          //CREATES A SEARCHBOX 
          JTextField searchBox = new JTextField();
          searchBox.setFont(searchBox.getFont().deriveFont(Font.PLAIN, 18f));
          searchBox.setPreferredSize(new Dimension(300, 30));
          
          //CREATES A BACK BUTTON 
          JButton back = new JButton("Back");
          back.setBackground(Color.BLACK);
          back.setForeground(Color.WHITE);
          back.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));

          //SETS THE FRAM IN A FLOWCHART LAYOUT, AND ADDS THE JLBEL SEARCH BOX AND BACK 
          fr.setLayout(new FlowLayout(FlowLayout.LEADING));
          fr.add(message);
          fr.add(Box.createVerticalStrut(20));//CREATES BLANK VERTICAL BOX FOR SPCAING
          fr.add(searchBox);
          fr.add(back);

          fr.setLocationRelativeTo(null);
          fr.setVisible(true);//MAKES THE FRAME VISIBLE

          back.addActionListener(new ActionListener() {//IF BACK IS CLICKED
            public void actionPerformed(ActionEvent e) {
              fr.getContentPane().removeAll();//REMOVES ALL THE CURRENT FRAM
              fr.getContentPane().add(new Menu());//ADDA THE MENU TO GIVE THE EFFEECT OF THE SCREEN GOING BACK
              fr.revalidate();
              fr.repaint();
            }
          });

          searchBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              String searchText = searchBox.getText();//TAKES WHAT WAS ADDED IN THE SEARCH BOX

              if (searchText.equalsIgnoreCase("controls")) {//IF IT EQUALS CONTROLS
                fr.getContentPane().removeAll();//GETS RID OF CURRENT FRAME 
                fr.revalidate();
                fr.dispose();

                final JFrame controlsScreen = new JFrame();//CREATES NEW FRAME
                controlsScreen.setSize(768, 676);
                controlsScreen.getContentPane().setBackground(Color.BLACK);

                //CREATES ANOTHER BACCK BUTTON
                JButton back = new JButton("Back");
                
                //ADDS THE BACK BUTTON ON A FLOWLAYOUTH
                controlsScreen.setLayout(new FlowLayout(FlowLayout.LEADING));
                controlsScreen.add(back);

                //IF THE BACK BUTTON IS CLICKES IT GETS RID OF THE FRAM AND SENDS BACK TO THE MENU SCREEN, NOT THE HELP SCREEN
                back.addActionListener(new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                    controlsScreen.getContentPane().removeAll();
                    controlsScreen.getContentPane().add(new Menu());
                    controlsScreen.revalidate();
                    controlsScreen.repaint();
                  }
                });

                try {
                  BufferedImage controlImage = ImageIO.read(getClass().getResource("controlScreen.png"));//MAKES A IMAGE FOR THE CONTROL
                  ImageIcon imageIcon = new ImageIcon(controlImage);
                  JLabel imageLabel = new JLabel(imageIcon);
                  controlsScreen.add(imageLabel);//ADDS THE IMAGE LABEL OR PICTURE TO THE NEW FRAME
                } catch (IOException ex) {
                  ex.printStackTrace();
                }

                controlsScreen.setLocationRelativeTo(null);
                controlsScreen.setVisible(true);//MAKES IT VISIBLE

              } else if (searchText.equalsIgnoreCase("about")) {//ELSE IF SEARCH EQUALS ABOUT
                fr.getContentPane().removeAll();//GETS RID OF CURRENT FRAM
                fr.revalidate();
                fr.dispose();

                final JFrame controlsScreen = new JFrame();//CREATES NEW JFRAM AND MAKES A NEW FRAME 
                controlsScreen.setSize(775, 576);
                controlsScreen.getContentPane().setBackground(Color.BLACK);

                //ANOTHER BACK BUTTON THAT WHN IT CLICKS IT SENDS TO MENU SCREEN
                
                JButton back = new JButton("Back");
                back.setBackground(Color.BLACK);
                back.setForeground(Color.WHITE);
                back.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));

                controlsScreen.setLayout(new FlowLayout(FlowLayout.LEADING));
                controlsScreen.add(back);

                back.addActionListener(new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                    controlsScreen.getContentPane().removeAll();
                    controlsScreen.getContentPane().add(new Menu());
                    controlsScreen.revalidate();
                    controlsScreen.repaint();
                  }
                });

                try {
                  BufferedImage controlImage = ImageIO.read(getClass().getResource("aboutScreen (2).png"));//SETS THE ABOUT IMAGE 
                  ImageIcon imageIcon = new ImageIcon(controlImage);
                  JLabel imageLabel = new JLabel(imageIcon);
                  controlsScreen.add(imageLabel);//ADDS IMAGE TO THE SCREEN 
                } catch (IOException ex) {
                  ex.printStackTrace();
                }

                controlsScreen.setLocationRelativeTo(null);
                controlsScreen.setVisible(true);//MAKES THE FRAME VISIBLE 

              } else if (searchText.equalsIgnoreCase("help")) {//ELSE IF THE SEARCH EQUALS HELP 
                fr.getContentPane().removeAll();//REMOVES THE CURRENT FRAME 
                fr.revalidate();
                fr.dispose();

                final JFrame controlsScreen = new JFrame();//CREATES A NEW FRAME
                controlsScreen.setSize(768, 700);
                controlsScreen.getContentPane().setBackground(Color.BLACK);

                //ANOTHER BACK BUTTON THAT WHN IT CLICKS IT SENDS TO MENU SCREEN

                JButton back = new JButton("Back");
                back.setBackground(Color.BLACK);
                back.setForeground(Color.WHITE);
                back.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));

                controlsScreen.setLayout(new FlowLayout(FlowLayout.LEADING));
                controlsScreen.add(back);

                back.addActionListener(new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                    controlsScreen.getContentPane().removeAll();
                    controlsScreen.getContentPane().add(new Menu());
                    controlsScreen.revalidate();
                    controlsScreen.repaint();
                  }
                });

                try {
                  BufferedImage image = ImageIO.read(getClass().getResource("helpScreen.png"));//ADDS A HELP IMAGE 
                  ImageIcon imageIcon = new ImageIcon(image);
                  JLabel imageL = new JLabel(imageIcon);
                  controlsScreen.add(imageL);//ADS THE IMAGE TO THE SCREEN 
                } catch (IOException ex) {
                  ex.printStackTrace();
                }

                controlsScreen.setLocationRelativeTo(null);
                controlsScreen.setVisible(true);//SETS IT VISIBLE 
              }

            }
          });

        }
      }
    });

     scores.addActionListener(new ActionListener() {//IF THE BUTTON SCORE IS PRESSED ON THE MENU
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == scores) {

            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(Menu.this); //Gets rod of the menu screen
            frame.remove(Menu.this);
            frame.dispose();

            final JFrame fr = new JFrame();//Creats new frame 
            fr.getContentPane().setBackground(Color.BLACK);
            fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            fr.setSize(768, 576);

          //SEARCH SORT WRITE READ FROM FILE
           try {
    BufferedReader br = new BufferedReader(new FileReader("HighScore.txt"));//FILE READER FOR THE HIGHSCORE TXT
    List<String> names = new ArrayList<>();//ARRAY LIST FOR NAME, ARRAY LIST BECAUSE A NEW PLAYER IS ADDED EVERTIME GAME STARTS
    List<Double> marks = new ArrayList<>();//ARRAY LIST FOR SCORES, ARRAY LIST BECAUSE A NEW SCORE IS ADDED EVERTIME GAME STARTS 

       String line; // Variable to store each line read from the file
    while ((line = br.readLine()) != null) {
        if (line.contains(",")) { // Check if the line contains a comma
            String[] split = line.split(","); // Split the line into an array of strings at the comma
            names.add(split[0]); // Add the first element (name) to the 'names' list
            marks.add(Double.parseDouble(split[1])); // Add the second element (score) as a double to the 'marks' list
        }
    }

    br.close();

    // Sort names and marks in descending order based on marks
    for (int i = 0; i < marks.size() - 1; i++) {
    // Iterate over the 'marks' list from the first element to the second-to-last element
    for (int j = i + 1; j < marks.size(); j++) {
        // Iterate over the 'marks' list starting from the element next to 'i'
        if (marks.get(i) < marks.get(j)) {
            // Compare the score at index 'i' with the score at index 'j'
            Collections.swap(marks, i, j);
            // Swap the scores in the 'marks' list at indices 'i' and 'j'
            Collections.swap(names, i, j);
            // Swap the corresponding names in the 'names' list at indices 'i' and 'j'
        }
    }
}

    // Display the sorted names and marks
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < names.size(); i++) {
      // Append the name, a colon, the corresponding score, and a newline character to the StringBuilder
        sb.append(names.get(i)).append(": ").append(marks.get(i)).append("\n");//appends each value and formats how its printed
    }

    //search
    String searchName = JOptionPane.showInputDialog(null, sb.toString() + "\nEnter the name to search:"); //displays that append formated string, then adds search

    // Search for the name
   boolean found = false; // Initialize a boolean variable 'found' and set it to false

int searchIndex = -1; // Initialize an integer variable 'searchIndex' and set it to -1

for (int i = 0; i < names.size(); i++) {
    if (names.get(i).equalsIgnoreCase(searchName)) {
        // Check if the name at index 'i' matches the 'searchName'
        JOptionPane.showMessageDialog(null, "Name: " + names.get(i) + "\nScore: " + marks.get(i));
        // Display a message dialog showing the name 
        found = true; // Set 'found' to true to indicate a match was found
        searchIndex = i; // Store the index 
        break; 
    }
}

    // If the name is not found
    if (!found) {
        JOptionPane.showMessageDialog(null, "Name not found: " + searchName);
    }

    // Save sorted information to a new file
    try (BufferedWriter bw = new BufferedWriter(new FileWriter("SortedHighScore (1).txt"))) {
        for (int i = 0; i < names.size(); i++) {
            if (names.get(i) != null){
            bw.write(names.get(i) + "," + marks.get(i));
            bw.newLine();
            }
        }
    } catch (IOException l) {
        l.printStackTrace();
    }
} catch (FileNotFoundException r) {
    r.printStackTrace();
} catch (IOException t) {
    t.printStackTrace();
}


          //AFTER THE SEARCH IS DONE, THE USER SEE THE SAME BLACK SCREEN CREATES IN THE BEGGINGING OF THIS ELSE IF, ANOTHER BACK BUTTON THAT TAKES THEM BACK TO THE MENU
          
            JButton back = new JButton("Back");
            back.setBackground(Color.BLACK);
            back.setForeground(Color.WHITE);
            back.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));

            fr.setLayout(new FlowLayout(FlowLayout.LEADING));
            
            fr.add(Box.createVerticalStrut(20));
          
            fr.add(Box.createVerticalStrut(20));
         
            fr.add(back);//ADDS BACK

            fr.setLocationRelativeTo(null);
            fr.setVisible(true);//SET VISIBLE

            back.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    fr.getContentPane().removeAll();
                    fr.getContentPane().add(new Menu());
                    fr.revalidate();
                    fr.repaint();
                }
            });
        }
    }
});
     
clearScores.addActionListener(new ActionListener() {//Clear high score button
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to clear all high scores?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    clearHighScores();
                    JOptionPane.showMessageDialog(null, "High scores cleared.");
                }
            }
        });

    //ADDS ALL BUTTON WITH RECTANGLES BETWEEN FOR VISUAL EFFECT AND SPACING
    buttonPanel.add(play);
    buttonPanel.add(Box.createVerticalStrut(20)); // Add vertical spacing between the buttons
    buttonPanel.add(help);
    buttonPanel.add(Box.createVerticalStrut(20));
    buttonPanel.add(stop);
    buttonPanel.add(Box.createVerticalStrut(20));
    buttonPanel.add(scores);
    buttonPanel.add(Box.createVerticalStrut(20));
    buttonPanel.add(clearScores);

    add(buttonPanel, BorderLayout.CENTER);//FORMATS THE BUTTON PANEL FOR THE BUTTONS
  }

  protected void paintComponent(Graphics g) {//PRINTS THE BACKGEOUND IMAGE FOR THE MENU 
    super.paintComponent(g);
    g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
  }
  private void clearHighScores() {
        try (FileWriter writer = new FileWriter("HighScore.txt", false)) {
            writer.write(""); // Write an empty string to clear the content
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
