/*
A212 STIA1113 Programming 1

Group 5 Members:
Lee Juan (280027)
Muhammad Zuhair Afham bin Mohd Nasir (280782)
Edwin Lim Wei Bin (281775)

Date of completion: 14 July 2022

--------

This programme is a Tic Tac Toe game that consists of two 2-player (easy and medium) modes and CPU mode along with
a tips function that could provide some suggestions to the player during gameplay. This programme also has a
simple high score and leaderboard function that would let players view their rankings in the game.

*/


import java.util.Scanner;
import java.util.Random;     //"Random" utility needed for CPU to make decisions in CPU mode


public class Group5TicTacToe {
   
   
   public static Scanner input = new Scanner(System.in);          //global variable for creating input function
   public static int option;                                      //global variable for option input
   public static char tipsCheck;                                  //tipsCheck = Y if tips are enabled
   public static String name1, name2, nameCPU = "CPU";
   public static int score1 = 0, score2 = 0, tipsCount;
   
   
   //main method
   public static void main(String[] myVariable) {
   
      for (;;) {       //infinite loop to always return to menu whenever a mode ends
         menu();
      }
      
   } //end main
   
   
   //--------------------------------------------- MENU ---------------------------------------------
   
   
   //method for main menu display
   public static void menu() {
   
      System.out.println("oooxxxoooxxxoooxxxoooxxxoooxxxooo\n");
      System.out.println("HELLO there, i see you're bored :)\nWelcome to our Tic Tac Toe!\n");
      System.out.println("1\tPLAY");
      System.out.println("2\tHow to Play?");
      System.out.println("3\tLeaderboard");
      System.out.println("4\tCredits");
      System.out.println("5\tEXIT GAME");
      
      do {
         System.out.println("\nWhat do you want to do?");
         System.out.print("Enter a number: ");
         option = input.nextInt();
         
         if ((option != 1) && (option != 2) && (option != 3) && (option != 4) && (option != 5)) {
            System.out.println("Invalid number! Try again.");
         }
         
      } while ((option != 1) && (option != 2) && (option != 3) && (option != 4) && (option != 5));    //do-while loop if user input is invalid
      
      //where user will proceed for each input option
      if (option == 1) {
                  
         System.out.println("\n-------------\n\n1\t2-PLAYER: EASY MODE   \t('>')/*\\('<')");
         System.out.println("2\t2-PLAYER: MEDIUM MODE \t(\">\")/***\\(\"<\")");
         System.out.println("3\tCPU MODE  \t\t\t\t\t(@_@)");
         System.out.println("4\tBack to main menu");
         System.out.print("\nEnter the mode that you want to play, or back to menu: ");
         option = input.nextInt();
         
         if (option == 1) {
            System.out.println("\nEnable TIPS during gameplay?");
            System.out.println("(it might include some eXtRa cOmmEntaRy too :P)");
            System.out.print("Enter \"Y\" to enable, or any other alphabet to disable tips: ");
            tipsCheck = input.next().toUpperCase().charAt(0);
            
            play2Easy();
         }
         else if (option == 2) {
            System.out.println("\nEnable TIPS during gameplay?");
            System.out.println("(it might include some eXtRa cOmmEntaRy too :P)");
            System.out.print("Enter \"Y\" to enable, or any other alphabet to disable tips: ");
            tipsCheck = input.next().toUpperCase().charAt(0);
            
            play2Medium();
         }
         else if (option == 3) {
            System.out.println("\nEnable *TIPS* during gameplay?");
            System.out.print("Enter \"Y\" to enable, or any other alphabet to disable tips: ");
            tipsCheck = input.next().toUpperCase().charAt(0);
            
            playComputer();
         }
         else {
            System.out.println();
            return;
         }
         
      }
      else if (option == 2) {
         rules();
      }
      else if (option == 3) {
         leaderboard();
      }
      else if (option == 4) {
         credits();
      }
      else {
         System.out.println("\nSee ya!");
         System.exit(0);                     //end programme
      }
   } //end menu()
      
      
   //--------------------------------------------- METHODS FOR 2-PLAYER MODES ---------------------------------------------
   
   
   //method for 2-player easy mode
   public static void play2Easy() {
      
      do {
         
         System.out.println("\noooxxxoooxxxoooxxxoooxxxoooxxxooo\n");
         System.out.println("--- 2-PLAYER: EASY MODE  ('>')/*\\('<') ---\nIt's more fun playing with friends, right!\n");
         
         boolean player1 = true;             //player 1 or 2's turn
         boolean endGame = false;            //boolean that is false in default, otherwise game ends
         char mark;                          //Xs or Os
         int row, col;                       //row and column number, input by user
         char[][] board = new char[3][3];    //array for 2D-board
         
         tipsCount = 0;              //reset tipsCount
         
         //assign initial char '-' to board
         for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
               board[i][j] = '-';
            }
         }
         
         System.out.println("You ready? LET'S GO!");
         System.out.print("Player 1, what is your name?: ");
         name1 = input.next();
         System.out.print("Player 2, what is your name?: ");
         name2 = input.next();
         
         //while loop when endGame is not true
         while (!endGame) {
            
            drawBoard(board);
            tips();
            
            if (player1) {
               System.out.println(name1 + ", make your turn (O): ");
            }
            else {
               System.out.println(name2 + ", make your turn (X): ");
            }
            
            if (player1) {
               mark = 'O';
            }
            else {
               mark = 'X';
            }
            
            while (true) { //loop if user input is invalid
               System.out.print("Enter a row number (1, 2, or 3): ");
               row = input.nextInt();
               System.out.print("Enter a column number (1, 2, or 3): ");
               col = input.nextInt();
               
               //check validity of row and col input
               if (row<1 || col<1 || row>3 || col>3) {
   					System.out.println("This position is off the bounds of the board! Try again.");
   				}
               else if (board[row-1][col-1] != '-') {
   					System.out.println("Someone has already made a move at this position! Try again.");
   				}
               else {
   					break;
   				}
               
            }//end while(true)
            
            //assign the position of row and col to mark Xs or Os to the board
            board[row-1][col-1] = mark;
            
            //check if any player has won or if the game is a tie
            if (win(board) == 'O') {
               System.out.println("\n***" + name1 + " HAS WON!!***");
               score1++;
               endGame = true;
            }
            else if (win(board) == 'X') {
               System.out.println("\n***" + name2 + " HAS WON!!***");
               score2++;
               endGame = true;
            }
            else if (boardFull(board)) {
               System.out.println("\n***GAME IS A TIE! Nobody won :\\***");
               endGame = true;
            }
            else {
               player1 = !player1;           //continue to next player's turn if either conditions are true
            }
            
         }//end while (!endGame)
         
         drawBoard(board);
         
         System.out.println("Play again?");
         System.out.print("Enter 1 to continue playing or any number to exit to the main menu: ");
         option = input.nextInt();
         
         //if option != 1, return to main menu; otherwise loop cuurent method (game mode)
         if (option != 1) {
            System.out.println();
            return;
         }
         
      } while (option == 1);
      //end do-while for method loop
   
   } //end play2Easy() or 2-player easy mode
   
   
   //method for 2-player medium mode
   public static void play2Medium() {
      
      do {
         
         System.out.println("\noooxxxoooxxxoooxxxoooxxxoooxxxooo\n");
         System.out.println("--- 2-PLAYER: NORMAL MODE  (\">\")/***\\(\"<\") ---\nReady for a challenge?\n");
         
         boolean player1 = true;
         boolean endGame = false;
         char mark;
         int row, col;
         char[][] board = new char[3][3];
         boolean correct;                    //added a boolean to check if input answer is correct
         
         tipsCount = 0;              //reset tipsCount
         
         //assign initial char '-' to board
         for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
               board[i][j] = '-';
            }
         }
         
         System.out.println("You ready? LET'S GO!");
         System.out.print("Player 1, what is your name?: ");
         name1 = input.next();
         System.out.print("Player 2, what is your name?: ");
         name2 = input.next();
         
         //while loop when endGame is not true
         while (!endGame) {
            
            drawBoard(board);
            tips();
            
            Random randomNumber = new Random();
            int num1 = randomNumber.nextInt(100), num2 = randomNumber.nextInt(100);    //initialise random numbers between 0-100 to num1 and num2
            int answer, realAnswer;
      
            System.out.println("Answer a question correctly to make a move, otherwise, you skip a turn :D");
            System.out.println("What is " + num1 + " + " + num2 + "?");                //question on addition of two random numbers
      
            if (player1) {
               System.out.print(name1 + ", enter your answer: ");
            }
            else {
               System.out.print(name2 + ", enter your answer: ");
            }
            answer = input.nextInt();
      
            realAnswer = num1 + num2;
      
            if (answer == realAnswer) {
               System.out.println("You got it right!");
               correct = true;
            }
            else {
               System.out.println("Oops, wrong answer. You lost a turn :(");
               correct = false;
            }
            
            //if answer is correct, proceed with turn
            if (correct) {
               if (player1) {
                  System.out.println(name1 + ", make your turn (O): ");
               }
               else {
                  System.out.println(name2 + ", make your turn (X): ");
               }
               
               if (player1) {
                  mark = 'O';
               }
               else {
                  mark = 'X';
               }
               
               while (true) { //loop if user input is invalid
                  System.out.print("Enter a row number (1, 2, or 3): ");
                  row = input.nextInt();
                  System.out.print("Enter a column number (1, 2, or 3): ");
                  col = input.nextInt();
                  
                  //check validity of row and col input
                  if (row<1 || col<1 || row>3 || col>3) {
      					System.out.println("This position is off the bounds of the board! Try again.");
      				}
                  else if (board[row-1][col-1] != '-') {
      					System.out.println("Someone has already made a move at this position! Try again.");
      				}
                  else {
      					break;
      				}
                  
               }//end while(true)
               
               //assign the position of row and col to mark Xs or Os to the board
               board[row-1][col-1] = mark;
               
               //check if any player has won or if the game is a tie
               if (win(board) == 'O') {
                  System.out.println("\n***" + name1 + " HAS WON!!***");
                  score1++;
                  endGame = true;
               }
               else if (win(board) == 'X') {
                  System.out.println("\n***" + name2 + " HAS WON!!***");
                  score2++;
                  endGame = true;
               }
               else if (boardFull(board)) {
                  System.out.println("\n***GAME IS A TIE! Nobody won :\\***");
                  endGame = true;
               }
               else {
                  player1 = !player1;           //continue to next player's turn if either conditions are true
               }

            }
            //lose turn if answer is wrong, switch to next player's turn
            else {
               player1 = !player1;
            }
                        
         }//end while (!endGame)
         
         drawBoard(board);
         
         System.out.println("Play again?");
         System.out.print("Enter 1 to continue playing or any number to exit to the main menu: ");
         option = input.nextInt();
         
         //if option != 1, return to main menu; otherwise loop cuurent method (game mode)
         if (option != 1) {
            System.out.println();
            return;
         }
         
      } while (option == 1);
      //end do-while for method loop
   
   } //end play2Medium() or 2-player medium mode
   
   
   //method to draw board in 2-player mode
   public static void drawBoard(char[][] board) {
      
      int r = 1;
      System.out.println("\n\t\t  Column:");
      System.out.println("\t\t  123\n");
      System.out.print("Row: " + r + " |");
      
      for (int i=0; i<3; i++) {
         for (int j=0; j<3; j++) {
            System.out.print(board[i][j]);
         }
         
         System.out.print("|");
         r++;
         if (r<=3) {
            System.out.print("\n\t  " + r + " |");
         }
         else {
            System.out.println("\n");
         }
         
      } //end for loop
      
   } //end drawBoard()
   
   
   //method to check whether someone has won in 2-player mode
   public static char win(char[][] board) {
      
      //check for each row (horizontally)
      for (int i=0; i<3; i++) {
         if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != '-') {
            return board[i][0];
         }
      }
      
      //check for each column (vertically)
      for (int j=0; j<3; j++) {
         if (board[0][j] == board[1][j] && board[1][j] == board[2][j] && board[0][j] != '-') {
            return board[0][j];
         }
      }
      
      //check for diagonals
      if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != '-') {
			return board[0][0];
		}
		if(board[2][0] == board[1][1] && board[1][1] ==  board[0][2] && board[2][0] != '-') {
			return board[2][0];
		}
      
      //otherwise nobody wins yet, game continues
      return '-';
      
   } //end win()
   
   //method to check if entire board is filled up to end game as a tie in 2-player mode
   public static boolean boardFull(char[][] board) {
      
      for (int i=0; i<3; i++) {
			for (int j=0; j<3; j++) {
				if (board[i][j] == '-') {
					return false;
				}
			}
		}
		return true;
      
   } //end boardFull()
   
   
   //--------------------------------------------- METHODS FOR CPU MODE ---------------------------------------------
   
   
   //method for CPU mode
   public static void playComputer() {
      
      do {
      
         System.out.println("\noooxxxoooxxxoooxxxoooxxxoooxxxooo\n");
         System.out.println("--- CPU MODE  (@_@) ---\nCan you defeat a non-living thing?\n");
         System.out.println("The numbers representing each cell:");
         System.out.println("1" + "|" + "2"  + "|" + "3");
         System.out.println("-----");
         System.out.println("4" + "|" + "5"  + "|" + "6");
         System.out.println("-----");
         System.out.println("7" + "|" + "8"  + "|" + "9");
         System.out.println("\n************\n");
         
         tipsCount = 0;              //reset tipsCount
         
         char [] [] board = {{' ', ' ' , ' '},
                             {' ', ' ' , ' '},
                             {' ', ' ' , ' '}}; //create board
         
         printBoard(board); //print board
         
         while (true) {
            
            tips();   
            playerMove(board);
               
            if (gameOver(board)){
               break;
            }
            printBoard(board);
            computerRandNo(board);
               
            if (gameOver(board)){
               break;
            }
               
            printBoard(board);
         } //end while
         
         System.out.println("Play again?");
         System.out.print("Enter 1 to continue playing or any number to exit to the main menu: ");
         option = input.nextInt();
         
         //if option != 1, return to main menu; otherwise loop cuurent method (game mode)
         if (option != 1) {
            System.out.println();
            return;
         }
         
      } while (option == 1);
      //end do-while for method loop
      
   }//end playComputer() or CPU mode
   
   
   //method to print board for CPU mode
   public static void printBoard(char [] [] board) {
      
      System.out.println(board [0][0] + "|" + board [0][1] + "|" + board [0][2] );
      System.out.println("-----");
      System.out.println(board [1][0] + "|" + board [1][1] + "|" + board [1][2] );
      System.out.println("----");
      System.out.println(board [2][0] + "|" + board [2][1] + "|" + board [2][2] + "\n" );
        
   }// end printBoard();
   
   
   //method to check number input from user in CPU mode
   public static void playerMove(char [][] board) {
      
      String userInput;
      
      while (true) {
         System.out.print("X, make a move by entering a number (1~9): ");
         userInput = input.next();
         
         if (emptyCells(board, userInput)) {
            break;
         }
         else {
            System.out.println("************");
            System.out.println(userInput + " is not a valid move."); 
            System.out.println("************");
         }
       
       } //end while
       
       System.out.println(userInput);
       
       switch (userInput) {
          case "1": 
             board[0][0] = 'X';
             break;
          case "2": 
             board[0][1] = 'X';
             break;
          case "3": 
             board[0][2] = 'X';
             break;
          case "4": 
             board[1][0] = 'X';
             break;
          case "5": 
             board[1][1] = 'X';
             break;
          case "6": 
             board[1][2] = 'X';
             break;
          case "7": 
             board[2][0] = 'X';
             break;
          case "8": 
             board[2][1] = 'X';
             break;
          case "9": 
             board[2][2] = 'X';
             break;
           default:
             System.out.println();
       } //end switch
       
   } // end playerMove()
   
   
   //method to insert symbol (Xs or Os) into board in CPU mode
   public static void placeMove(char[][] board, String place,char letter) {
      
       switch (place) {
          case "1": 
             board[0][0] = letter;
             break;
          case "2": 
             board[0][1] = letter;
             break;
          case "3": 
             board[0][2] = letter;
             break;
          case "4": 
             board[1][0] = letter;
             break;
          case "5": 
             board[1][1] = letter;
             break;
          case "6": 
             board[1][2] = letter;
             break;
          case "7": 
             board[2][0] = letter;
             break;
          case "8": 
             board[2][1] = letter;
             break;
          case "9": 
             board[2][2] = letter;
             break;
          default:
             System.out.println();
       }//end switch
   
   } //end placeMove()
   
   
   //method to create move for CPU
   public static void computerRandNo (char [][] board) {
   
      Random randomNumber = new Random(); //declare Random function
      int computerMove;
      
      while(true) {
         computerMove = randomNumber.nextInt(9) + 1; //obtain random number for computer's move
         
         if (emptyCells(board,Integer.toString(computerMove))) {
            break;
         }
      } //end while
      
      System.out.println("Computer (O) choose to move at " + computerMove + "\n");
      placeMove(board, Integer.toString(computerMove), 'O');
      
   } //end computerRandNo();
   

   //method to display empty cells when necessary in CPU mode
   public static boolean emptyCells(char[][] board, String place) {
      
      switch (place) {
         case "1":
            return (board[0][0] == ' ');
         case "2":
            return (board[0][1] == ' ');
         case "3":
            return (board[0][2] == ' ');
         case "4":
            return (board[1][0] == ' ');
         case "5":
            return (board[1][1] == ' ');
         case "6":
            return (board[1][2] == ' ');
         case "7":
            return (board[2][0] == ' ');
         case "8":
            return (board[2][1] == ' ');
         case "9":
            return (board[2][2] == ' ');
         default:
            return false;
         } //end switch
         
   } //end emptyCells()

   
   //method for CPU mode to check if someone has won the game, otherwise game continues
   public static boolean someoneWon (char[][] board, char letter) {
   
       if((board[0][0] == letter && board[0][1] == letter && board[0][2] == letter ) ||
         (board[1][0] == letter && board[1][1] == letter && board[1][2] == letter ) ||
         (board[2][0] == letter && board[2][1] == letter && board[2][2] == letter ) ||
         
         (board[0][0] == letter && board[1][0] == letter && board[2][0] == letter ) ||
         (board[0][1] == letter && board[1][1] == letter && board[2][1] == letter ) ||
         (board[0][2] == letter && board[1][2] == letter && board[2][2] == letter ) ||
   
         (board[0][0] == letter && board[1][1] == letter && board[2][2] == letter ) ||
         (board[0][2] == letter && board[1][1] == letter && board[2][0] == letter ) )
         {
            return true;
         }
         
         return false;
         
   } //end someoneWon()


   //method to display end game for CPU mode
   public static boolean gameOver(char[][] board){
   
      if (someoneWon(board, 'X')) {
            printBoard(board);
            System.out.println("Congratulations! You are the winner!!!");
            return true;
      }
            
      if (someoneWon(board,'O')) {
            printBoard(board);
            System.out.println("Computer wins! You lose T.T");
            return true;
      }     
   
      
      for (int i = 0;i<board.length; i++ ) {
         for (int j = 0; j < board[i].length; j++) {
             if (board[i][j] == ' ') {
               return false;
             }
         }
      }
      printBoard(board);
      System.out.println("The game ended in a draw.");
      return true;
   
   } //end gameOver()
      
      
   //--------------------------------------------- TIPS ---------------------------------------------
   
   
   //method to display tips when enabled by user
   public static void tips() {
      
      if (tipsCheck == 'Y') {
         tipsCount++;
      }
      
      switch (tipsCount) {
      
         case 1:
            System.out.println("Tip: Secure the corners first and you'll decrease the chances of your opponent winning!\n");
            break;
         case 2:
            System.out.println("Tip: Take the center spot if your opponent took the corners ;)\n");
            break;
         case 3:
            System.out.println("Tip: Always be aware of your opponent's move, one slight miss and you'll lose easily.\n");
            break;
         case 4:
            System.out.println("Tip: Remember to check for diagonals too, your opponent could be sneaky!\n");
            break;
         case 5:
            System.out.println("Tip: Do you think you could trap your opponent by forcing them to mark on a specific spot?\n");
            break;
         case 6:
            System.out.println("Tip: If they are one spot away from winning and you know it, take that spot!\n");
            break;
         case 7:
            System.out.println(":::: I can't think of any more tips for you at this point in the game HAHA!\n");
            break;
         case 8:
            System.out.println(":::: U CAN DO THIS\n");
            break;
         case 9:
            System.out.println(":::: It's victory or defeat, or a tie??\n");
            break;
         case 10:
            System.out.println("     If you're reading this, I know you're playing medium mode #.#");
            System.out.println("Tip: Try use a calcula- oh nevermind\n");
            break;
         case 11:
            System.out.println("Tip: You have a huge opportunity to block their line if your opponent answered the wrong math question.\n");
            break;
         case 12:
            System.out.println(":::: No tip this time. Why? Cause I'm out of tips, haha!\n");
            break;
         case 13:
            System.out.println(":::: ......\n");
            break;
         case 14:
            System.out.println(":::: *sweating*\n");
            break;
         case 15:
            System.out.println(":::: This is taking too long. I guess... you're on your own now?");
            System.out.println("     KEEP GOING\n");
            break;
         default:
            ;  
            
      } //end switch for tips display
      
   } //end tips()
   
   
   //--------------------------------------------- RULES ---------------------------------------------
   
   
   //method to view rules
   public static void rules() {
      
      System.out.println("\noooxxxoooxxxoooxxxoooxxxoooxxxooo\n");
      System.out.println("--- RULES ---\n");
      System.out.println("The game is played on a 3x3 grid.");
      System.out.println("Assuming you are 'X', and your friend (or the computer) is 'O'.");
      System.out.println("The first player to get 3 of thier marks in a row, either HORIZONTALLY, VERTICALLY, or DIAGONALLY; is the winner.");
      System.out.println("However, when all 9 squares are full, the game is tied and nobody wins.");
      System.out.println("It's that simple. but ARE YA READY?! ;)");
      
      System.out.println("\nReturn to main menu?");
      System.out.print("Enter any number: ");
      option = input.nextInt();
      System.out.println();
      return;
      
   } //end rules()
   
   
   //--------------------------------------------- METHODS FOR LEADERBOARD ---------------------------------------------
   
   
   //method to view leaderboard
   public static void leaderboard() {
      
      String names[] = {name1, name2, nameCPU};
      int scores[] = {score1, score2, 0};
         
      do {
         
         System.out.println("\noooxxxoooxxxoooxxxoooxxxoooxxxooo\n");
         System.out.println("--- LEADERBOARD ---\n");
         System.out.println("Place\t\tName\t\t\tScore");
         
         if ((score1 == 0) && (score2 == 0)) {
            System.out.printf(1 + "\t\t\t%-10s%5d%n", names[2], scores[2]);
            System.out.printf(1 + "\t\t\t%-10s%5d%n", name1, score1);
            System.out.printf(1 + "\t\t\t%-10s%5d%n", name2, score2);
         }
         else if (score1 > score2) {
            System.out.printf(1 + "\t\t\t%-10s%5d%n", name1, score1);
            System.out.printf(2 + "\t\t\t%-10s%5d%n", name2, score2);
            System.out.printf(3 + "\t\t\t%-10s%5d%n", names[2], scores[2]);
         }
         else if (score2 > score1) {
            System.out.printf(1 + "\t\t\t%-10s%5d%n", name2, score2);
            System.out.printf(2 + "\t\t\t%-10s%5d%n", name1, score1);
            System.out.printf(3 + "\t\t\t%-10s%5d%n", names[2], scores[2]);
         }
         else {
            System.out.printf(1 + "\t\t\t%-10s%5d%n", name1, score1);
            System.out.printf(1 + "\t\t\t%-10s%5d%n", name2, score2);
            System.out.printf(3 + "\t\t\t%-10s%5d%n", names[2], scores[2]);
         }
         
         System.out.println("\n--------------");
         System.out.println("1\tSearch name in leaderboard");
         System.out.println("2\tReset leaderboard");
         System.out.println("3\tReturn to main menu");
         
         do {
            System.out.print("\nEnter a number: ");
            option = input.nextInt();
            
            if ((option != 1) && (option != 2) && (option != 3)) {
               System.out.println("Invalid input! Enter 1, 2, or 3 only.");
            }
            
         } while ((option != 1) && (option != 2) && (option != 3));
         
         if (option == 1) {
            searchName(names);
         }
         else if (option == 2) {
            int num;
            
            System.out.println("\nAre you sure to reset the leaderboard?");
            System.out.print("Enter 1 to proceed or any number to cancel: ");
            num = input.nextInt();
            
            if (num == 1) {
               resetLeaderboard(names, scores);
            }
         }
         else {
            System.out.println();
            return;
         }
      } while ((option == 1) || (option == 2));

   } //end leaderboard()
   
   
   //method to search the name of the selected player
   public static void searchName(String[] names) {
      
      int flag = 0;
      int num;
            
      System.out.println("\n1\tPlayer 1");
      System.out.println("2\tPlayer 2");
      System.out.print("Enter the player number that you want to search: ");
      num = input.nextInt();
            
      for (int i=1; i <= names.length; i++) {
         if (i == num) {
            System.out.println("\nYou searched for Player " + i + ", their name is " + names[i-1]);
            flag = 1;
            break;
         }
      }
            
      if (flag == 0) {
         System.out.println("\nInvalid number!");
      }
            
      System.out.println("\nReturn to leaderboard?");
      System.out.print("Enter any number to proceed: ");
      num = input.nextInt();
      
   } //end searchName()
   
   
   //method to reset the leaderboard
   public static void resetLeaderboard(String[] names, int[] scores) {
            
      for (int i=0; i < names.length; i++) {
         names[i] = "null";
         scores[i] = 0;
      }
      
   } //end resetLeaderboard()
   
   
   //--------------------------------------------- CREDITS ---------------------------------------------
      
      
   //method to display credits
   public static void credits() {
   
      System.out.println("\noooxxxoooxxxoooxxxoooxxxoooxxxooo\n");
      System.out.println("Creators of this project:\n");
      System.out.println("[GROUP 5]");
      System.out.println("1\tLee Juan (280027)");
      System.out.println("2\tMuhammad Zuhair Afham bin Mohd Nasir (280782)");
      System.out.println("3\tEdwin Lim Wei Bin (281775)");
      System.out.println("\nMade for Dr. Noradila binti Nordin <3");
      System.out.println("A212 STIA1113(A) Programming 1");
      
      String justForFun = "heart, blood, and soul";
      System.out.printf("%nThough it's not perfect, we put our %s into this!!%n", justForFun);
      
      System.out.println("\nReturn to main menu?");
      System.out.print("Enter any number: ");
      option = input.nextInt();
      System.out.println();
      return;
      
   } //end credits()
   
} //end class