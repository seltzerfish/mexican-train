/**
 * Created by connerlane on 7/17/16.
 */


import java.util.*;


public class Main {




   public static void main(String args[]) {
      int startingDouble;
      int numOfDominos;
      ArrayList<Domino> dominoList = new ArrayList<Domino>();
      Stack<Domino> longest = new Stack<Domino>();
   
      System.out.println("  __  __         _                _____         _        ___                              \n" +
                " |  \\/  |_____ _(_)__ __ _ _ _   |_   _| _ __ _(_)_ _   | _ \\_ _ ___  __ _ _ _ __ _ _ __  \n" +
                " | |\\/| / -_) \\ / / _/ _` | ' \\    | || '_/ _` | | ' \\  |  _/ '_/ _ \\/ _` | '_/ _` | '  \\ \n" +
                " |_|  |_\\___/_\\_\\_\\__\\__,_|_||_|   |_||_| \\__,_|_|_||_| |_| |_| \\___/\\__, |_| \\__,_|_|_|_|\n" +
                "                                                                     |___/                ");
      System.out.println("~By Conner Lane~\n\n");
   
      Scanner scan = new Scanner(System.in);
      print("Please enter the number on the starting double: ");
      startingDouble = scan.nextInt();
      print("Please enter the number of dominos you have: ");
      numOfDominos = scan.nextInt();
      println("");
      int holdingPoints = 0;
      println("For each of your dominos, type in the numbers on each side separated by a space, and then press enter.\n if one side is blank, use a 0.");
      for (int i = 0; i < numOfDominos; i++) {
         int side1;
         int side2;
         print("Domino #"+ (i + 1) +": ");
         side1 = scan.nextInt();
         side2 = scan.nextInt();
         Domino d = new Domino(side1, side2, i);
         holdingPoints += d.points;
         dominoList.add(d);
      
      }
   
        //Check if can play
   
      boolean canPlay = false;
      for (int i = 0; i < numOfDominos; i++) {
         Domino d = dominoList.get(i);
         if (d.side1 == startingDouble || d.side2 == startingDouble) {
            canPlay = true;
            break;
         }
      
      }
      while (!canPlay) {
      
         println("\nYou do not have any dominos that match the starting double... You will have to draw.");
         print("Enter the sides of the drawn double and then press enter: ");
         Domino d = new Domino(scan.nextInt(), scan.nextInt(), (numOfDominos));
         numOfDominos++;
         if (d.side1 == startingDouble || d.side2 == startingDouble)
            canPlay = true;
      }
      print("\nYou are holding " + holdingPoints + " points total.\n");
      print("Calculating...");
        //Do calculations
      int length = 0;
      int record = 0;
      int score = 0;
      int recordScore = 0;
      Stack<Integer> tailStack = new Stack<Integer>();
      Stack<Domino> dominoStack = new Stack<Domino>();
      boolean available[] = new boolean[numOfDominos];
      Stack<boolean[]> availableStack = new Stack<boolean[]>();
      int tail = startingDouble;
   
        //set all available things to true
      for (int i = 0; i < numOfDominos; i++) {
         available[i] = true;
      }
      availableStack.push(available);
   
      while(!availableStack.isEmpty()) {
         available = availableStack.pop();
         for (int i = 0; i < numOfDominos; i++) {
            if (available[i] == false)
               continue;
            Domino d = dominoList.get(i);
            if (d.side1 == tail) {
               dominoStack.push(d);
               score += d.points;
               available[i] = false;
               tailStack.push(tail);
               tail = d.side2;
            
               length++;
               if (score > recordScore) {
                  recordScore = score;
                  longest = (Stack<Domino>) dominoStack.clone();
               }
               availableStack.push(available);
               Stack<Domino> copy = (Stack<Domino>) dominoStack.clone();
               boolean temp[] = new boolean[numOfDominos];
                        //set all available things to true
               for (int l = 0; l < numOfDominos; l++) {
                  temp[l] = true;
               }
               while (!copy.empty()) {
                  temp[copy.pop().position] = false;
               }
               available = temp;
               availableStack.push(available);
               break;
            }
            if (d.side2 == tail) {
               dominoStack.push(d);
               score += d.points;
               available[i] = false;
               tailStack.push(tail);
               tail = d.side1;
            
               length++;
               if (score > recordScore) {
                  recordScore = score;
                  longest = (Stack<Domino>) dominoStack.clone();
               }
               availableStack.push(available);
               Stack<Domino> copy = (Stack<Domino>) dominoStack.clone();
               boolean temp[] = new boolean[numOfDominos];
                        //set all available things to true
               for (int l = 0; l < numOfDominos; l++) {
                  temp[l] = true;
               }
               while (!copy.empty()) {
                  temp[copy.pop().position] = false;
               }
               available = temp;
               availableStack.push(available);
               break;
            }
            available[i] = false;
         
         }
         boolean allFalse = true;
         for (int a = 0; a < numOfDominos; a++) {
            if (available[a] == true) {
               allFalse = false;
               break;
            }
         }
         if (length > 0 && allFalse) {
            tail = tailStack.pop();
            Domino d = dominoStack.pop();
            score -= d.points;
            length--;
         
         }
      }
   
      Domino longr[] = new Domino[numOfDominos];
      int finalLength = longest.size();
      while (!longest.isEmpty()) {
         longr[( longest.size() - 1)] = ( longest.pop());
      }
      print("\n\nHere is the highest scoring domino train you can make: ");
      tail = startingDouble;
      for (int i = 0; i < finalLength; i++) {
         Domino d = longr[i];
         if (i % 6 == 0)
            print("\n\n");
         if (d.side1 == tail) {
            print("[ " + d.side1 + " | " + d.side2 + " ] ");
            tail = d.side2;
         }
         else {
            print("[ " + d.side2 + " | " + d.side1 + " ] ");
            tail = d.side1;
         }
         if (i <  (finalLength-1))
            print("--> ");
      }
      println("\n\n\nThis train contains " + recordScore + " points.\n");
      int left = holdingPoints - recordScore;
      return;
   }










   private static void print(String in) {
   
      System.out.print(in);
   }

   private static void println(String in) {
   
      System.out.println(in);
   }

}