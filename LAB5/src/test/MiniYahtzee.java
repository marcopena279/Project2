package test;
import java.util.*;
import javax.swing.JOptionPane;
import test.Window;
 
public class MiniYahtzee
{
	//WINDOW VARIABLE
 private static Window _w = new Window();
 
 public static void main(String[] args) {
	 //INSTANCE VARIABLES
  int play = 1;
  int scorea = 0;
  int sum = 0;
  int[] wins = new int[15];
	//WHILE LOOP(THE GAME)
  while ((play == 1) && (sum < 15)) {
	  //VARIABLES FOR GAME
   sum = 0;
   int[] aDice = new int[] { 0, 0, 0, 0, 0 }; // creates an array
   int roll = 0;
   int x;
   int y;
   int w;
   int z;
   String input = "";
   int rerolla = 0;
   int rerollb = 3;
   	//CREATES DICE
   Die die = new Die();
   	//SETS VALUES FOR DICE
   for (x = 0; x < 5; x++) {
    die.roll();
    aDice[x] = die.get(); // sets the dice values
   }
	//DISPLAYS VALUES FOR DICE
   _w.msg("Dice #1: " + aDice[0]
 	+ "\nDice #2: " + aDice[1]
 	+ "\nDice #3: " + aDice[2]
 	+ "\nDice #4: " + aDice[3]
 	+ "\nDice #5: " + aDice[4]);
	//DISPLAYS GAME AND REROLL OPTION
   do {
    input = JOptionPane.showInputDialog(
      null,
      "How many of the dice do you want to roll again (1-5)",
      "...Reroll...",
      JOptionPane.INFORMATION_MESSAGE);
    
    rerolla = Integer.parseInt(input);
    //REROLSS
    if (rerolla > 0) {
     int[] reroll = new int[rerolla];
     //ALLOWS FOR REROLL
     for (y = 0; y < rerolla
       ; y++) {
      input = JOptionPane.showInputDialog(
        null,
        "How many of the dice do you want to roll again (1-5)",
        "...Reroll...",
        JOptionPane.INFORMATION_MESSAGE);
      
      rerollb = Integer.parseInt(input);
      reroll[y] = rerollb;      
     }
        	
     for (w = 0; w < rerolla; w++) {
      if (reroll[w] == 1) {
       die.roll();
       aDice[0] = die.get();
      }
      if (reroll[w] == 2) {
       die.roll();
       aDice[1] = die.get();
      }
      if (reroll[w] == 3) {
       die.roll();
       aDice[2] = die.get();
      }
      if (reroll[w] == 4) {
       die.roll();
       aDice[3] = die.get();
      }
      if (reroll[w] == 5) {
       die.roll();
       aDice[4] = die.get();
      }
     }
     
     roll++;
           	
     _w.msg("Dice #1: " + aDice[0]
   	+ "\nDice #2: " + aDice[1]
   	+ "\nDice #3: " + aDice[2]
   	+ "\nDice #4: " + aDice[3]
   	+ "\nDice #5: " + aDice[4]); 
    }
   }
   	//ASSIGNS WINNING
   while ((roll < 2) && (rerolla > 0));
   Winnings prize = new Winnings();
   prize.checkWinnings(aDice, wins);
   wins[prize.choice() - 1] = 1;
  	
   for (z = 0; z < 15; z++) {
    sum += wins[z];
   }
   	
   scorea += prize.score();
   
   _w.msg("The points you scored where: " + scorea);
  	
   if (sum < 15) {
    //play = inputInt("do you want to play again?(1 = yes, 2 = no)");
    input = JOptionPane.showInputDialog(
      null,
      "Do you wanna play again???" + "\nType 1 to play again or 2 to quit.",
      "Play again?",
      JOptionPane.INFORMATION_MESSAGE);
    // EXIT option
    if(input.equals("1")){
     play = 1;
    }
    else {
     JOptionPane.showMessageDialog(null, "Game Over!");
     System.exit(0);
    }
   } 
  }
 }
 
 //KEEPS TRACK OF RESULT
 static int inputInt(String Prompt) {
  int result = 0;
  try {
   result = Integer.parseInt(input(Prompt).trim());
  }
  catch (Exception e) {
   result = 0;
  }
  return result;
 }
 
 //RETURNS INPUT 
 static String input(String prompt) {
  String inputLine = "";
  System.out.print(prompt);
  try {
   java.io.InputStreamReader sys = new java.io.InputStreamReader(System.in);
   java.io.BufferedReader inBuffer = new java.io.BufferedReader(sys);
   inputLine = inBuffer.readLine();
  }
  catch (Exception e) {
   String err = e.toString();
   System.out.println(err);
  }
  
  return inputLine;
 }
}
 
// CLASS DIE
/* The class for handling each of the dice */
class Die {
 private int value;
 private Random rand;
 
 public Die() {
  value = 0;
  rand = new Random();
 }
 
 public void roll() {
  value = 1 + rand.nextInt(6);
 }
 
 public int get() {
  return (value);
 }
}
 
/* The class for determining what you have (i.e. a pair, straight, etc) */
class Winnings {
 private int score;
 private int choice;
 
 public Winnings() {
  score = 0;
 }
 
public void checkWinnings(int[] aDice, int[] wins) {
  Window _w = new Window();
  _w.msg ("Which do you want to see if you have?");
  
  // YAHTZEE
  _w.msg(" 1. YAHTZEE"
    
  // OPTIONAL REQUIRMENT
  // Full House
  + "\n 2. Full House"
  // Large Straight
  + "\n 3 - Large Straight"
  // Small Straight
  + "\n 4. Small Straight"
  // Four of a kind
  + "\n 5. Four of a Kind"
  // Three of a kind
  + "\n 6. Three of a Kind"
  // Pair
  + "\n 7. Pair"
  // Two Pair
  + "\n 8. Two Pair"
  
  // NUMBERS
  // ONE
  + "\n 9. Number of 1's"
  // TWO
  + "\n10. Number of 2's"
  // THREE
  + "\n11. Number of 3's"
  // FOUR
  + "\n12. Number of 4's"
  // FIVE
  + "\n13. Number of 5's"
  // SIX
  + "\n14. Number of 6's"
  
  // OPTIONAL
  // Chance
  + "\n15. Chance");
  String choice = JOptionPane.showInputDialog(
    null,
    "Which do you want to pick" + "\n(Type the number you want)",
    "Choice",
    JOptionPane.INFORMATION_MESSAGE);
  
 	int x = 0, y = 0;
 	int winings = 0;
 	int winingsa = 0;
 	int ones = 0;
 	int twos = 0;
 	int threes = 0;
 	int fours = 0;
 	int fives = 0;
 	int sixes = 0;
 	Arrays.sort(aDice);
 
 	
 	// Numbers of Rolling Dice
 	for (y = 0; y < 5; y++) {
   	if (aDice[y] == 1) {
     	ones++;
   	}
   	if (aDice[y] == 2) {
     	twos++;
   	}
   	if (aDice[y] == 3) {
     	threes++;
   	}
   	if (aDice[y] == 4) {
     	fours++;
   	}
   	if (aDice[y] == 5) {
     	fives++;
   	}
   	if (aDice[y] == 6) {
     	sixes++;
   	}
 	}
 
 	
  //Straight
  if ((aDice[0] == aDice[1] - 1) && (aDice[1] == aDice[2] - 1) && (aDice[2] == aDice[3] - 1) && (aDice[3] == aDice[4] - 1) && (Integer.parseInt(choice) == 3)) {
   winingsa = 1;
  }
  else if ((ones > 0) && (twos > 0) && (threes > 0) && (fours > 0)) {
   winingsa = 2;
  }
  else if ((threes > 0) && (fours > 0) && (fives > 0) && (sixes > 0)) {
   winingsa = 2;
  }
  else if ((twos > 0) && (threes > 0) && (fours > 0) && (fives > 0)) {
   winingsa = 2;
  }
  
  
  //Pairs
  for (x = 0; x < 5; x++) {
   if (x != 0) {
    if ((aDice[0] == aDice[x])) {
     winings++;
    }
   }
   if ((x != 0) && (x != 1)) {
    if ((aDice[1] == aDice[x])) {
     winings++;
    }
   }
   if ((x != 0) && (x != 1) && (x != 2)) {
    if ((aDice[2] == aDice[x])) {
     winings++;
    }
   }
   if ((x != 0) && (x != 1) && (x != 2) && (x != 3)) {
    if ((aDice[3] == aDice[x])) {
     winings++;
    }
   }
  }
  
  
  //Winnings
  if ((winingsa == 1) && (Integer.parseInt(choice) == 3)) {
   _w.msg("You got a straight ayyye.");
   score = 40;
  }
  else if ((winingsa == 2) && (Integer.parseInt(choice) == 4)) {
   _w.msg("You have a small straight. *Clap Clap*");
   score = 30;
  }
  else if ((winings == 10) && (Integer.parseInt(choice) == 1)) {
   _w.msg("YAHTZEE! YOU DID ITTTTTTT");
   score = 50;
  }
  else if ((Integer.parseInt(choice) == 6) && (winings >= 3)) {
   _w.msg("You have three of a kind. That's impressive");
   score = aDice[0] + aDice[1] + aDice[2] + aDice[3] + aDice[4];
  }
  else if ((Integer.parseInt(choice) == 7) && (winings > 0)) {
   _w.msg("You have a pair. Nice...");
   score = 5;
  }
  else if ((winings == 2) && (Integer.parseInt(choice) == 8)) {
   _w.msg("You have two pairs. Woah that's not bad");
   score = 10;
  }
  else if ((winings == 4) && (Integer.parseInt(choice) == 2)) {
   _w.msg("You got a full house. Good Job");
   score = 25;
  }
  else if ((winings >= 6) && (Integer.parseInt(choice) == 5)) {
   _w.msg("You have four of a kind. That's hard to do my friend");
   score = aDice[0] + aDice[1] + aDice[2] + aDice[3] + aDice[4];
  }
  else if (Integer.parseInt(choice) == 9) {
   _w.msg("You got " + ones + " one(s). Not bad");
   score = ones;
  }
  else if (Integer.parseInt(choice) == 10) {
   _w.msg("You got " + twos + " two(s). Im proud");
   score = twos * 2;
  }
  else if (Integer.parseInt(choice) == 11) {
   _w.msg("You got " + threes + " three(s). Nice Nice");
   score = threes * 3;
  }
  else if (Integer.parseInt(choice) == 12) {
   _w.msg("You got " + fours + " four(s). Woahhhh");
   score = fours * 4;
  }
  else if (Integer.parseInt(choice) == 13) {
   _w.msg("You got " + fives + " five(s). Impressive");
   score = fives * 5;
  }
  else if (Integer.parseInt(choice) == 14) {
   _w.msg("You got " + sixes + " sixe(s). 10/10");
   score = sixes * 6;
  }
  else if (Integer.parseInt(choice) == 15) {
   score = aDice[0] + aDice[1] + aDice[2] + aDice[3] + aDice[4];
   _w.msg("Your score: " + score);
  }
  else {
   _w.msg("You got zilch bud.");
   score = 0;
  }
  
  _w.msg("Your total points is: " + score);
 }

 public int score() {
  return score;
 }
  
 
 public int choice() {
  return choice;
 }
}


