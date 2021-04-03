package battleship;

import java.util.Scanner;

public class Main {

   public static void main(String[] args)  {
      Player player1 = new Player();
      Player player2 = new Player();
      char[][] player1play = player1.getPlayBoard();
      char[][] player1Fog = player1.getFogPlayBoard();
      int[] player1Coordinate = player1.getCoordinates();

      char[][] player2play = player2.getPlayBoard();
      char[][] player2Fog = player2.getFogPlayBoard();
      int[] player2Coordinate = player2.getCoordinates();

      System.out.println("Player 1, place your ships to the game field\n");
      player1.createPlayboard(player1play, player1Coordinate,"");
      player1.showBoard(player1play);
      player1.coordinateShip("Aircraft Carrier", 4, player1play);
      player1.coordinateShip("Battleship", 3, player1play);
      player1.coordinateShip("Submarine", 2, player1play);
      player1.coordinateShip("Cruiser", 2, player1play);
      player1.coordinateShip("Destroyer", 1, player1play);

      promptEnterKey();

      System.out.println("\nPlayer 2, place your ships to the game field");
      player2.createPlayboard(player2play, player2Coordinate,"");
      Player.showBoard(player2play);
      player2.coordinateShip("Aircraft Carrier", 4, player2play);
      player2.coordinateShip("Battleship", 3, player2play);
      player2.coordinateShip("Submarine", 2, player2play);
      player2.coordinateShip("Cruiser", 2, player2play);
      player2.coordinateShip("Destroyer", 1, player2play);

      promptEnterKey();

      player1.createFogPlayBoard(player1Fog);
      player2.createFogPlayBoard(player2Fog);

      do {
         player2.showBoard(player2Fog);
     System.out.println("\n--------------------");
         player1.showBoard(player1play);
         System.out.println("\nPlayer 1, it's your turn:");
         player2.checkShot(player2play);

         promptEnterKey();

            player1.showBoard(player1Fog);
            System.out.println("\n--------------------");
            player2.showBoard(player2play);
            System.out.println("\nPlayer 2, it's your turn:");
            player1.checkShot(player1play);

         promptEnterKey();
      } while (!player1.gameFinished||!player2.gameFinished);
   }

   public static void promptEnterKey() {
      System.out.println("\nPress Enter and pass the move to another player");
      Scanner scanner=new Scanner(System.in);
      if (scanner.nextLine().isEmpty())
         return;
   }

}