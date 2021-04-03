package battleship;

import java.util.Scanner;

public class Player {
    static boolean usedA = true;
    static boolean usedB = true;
    static boolean usedC = true;
    static boolean usedD = true;
    static boolean usedE = true;
    public Player() {
        this.x1=-1;
        this.x2=-1;
        this.y1=-1;
        this.y2=-1;
        this.playBoard =new char[10][10];
        this.fogPlayBoard =new char[10][10];
        this.shipSinkBoard =new char[10][10];
        this.gameFinished = false;
    }
     int x1,x2,y1,y2;
     char [][] playBoard;
     char [][] fogPlayBoard;
     char [][] shipSinkBoard;
     int [] coordinates={x1,y1,x2,y2};
     boolean gameFinished;

    public  boolean checkLength(int [] coordinates, int l){
        x1=coordinates[0];
        y1=coordinates[1];
        x2=coordinates[2];
        y2=coordinates[3];
        return x1 - x2 == l || y1 - y2 == l || x2 - x1 == l || y2 - y1 == l;
    }
    public boolean checkShip(char[][] shipSinkBoard) {
        int countA=0;
        int countB=0;
        int countC=0;
        int countD=0;
        int countE=0;
        boolean result=true;
        for(int i=0;i<10;i++) {
            for (int j = 0; j < 10; j++) {
                if(shipSinkBoard[i][j]=='A'){
                    countA++;}
                else if(shipSinkBoard[i][j]=='B'){
                    countB++;}
                else if(shipSinkBoard[i][j]=='C'){
                    countC++;}
                else if(shipSinkBoard[i][j]=='D'){
                    countD++;}
                else if(shipSinkBoard[i][j]=='E'){
                    countE++; }
            }
        }
        if(countA==0&&usedA) {
            result = false;
            usedA = false;
        } else if (countB==0&&usedB) {
            result = false;
            usedB = false;
        }else if (countC==0&&usedC) {
            result = false;
            usedC = false;
        }else if (countD==0&&usedD) {
            result = false;
            usedD = false;
        }else if (countE==0&&usedE) {
            result = false;
            usedE = false;
        }
        if (countA==0&&countB==0&&countC==0&&countD==0&&countE==0)
            gameFinished=true;
        return !result;
    }
    public void createFogPlayBoard(char[][] fogPlayBoard) {
        for(int i=0;i<10;i++) {
            for (int j = 0; j < 10; j++) {
                fogPlayBoard[i][j]='~';
            }
        }
    }

    public void checkShot(char[][] playBoard) {
        Scanner scanner=new Scanner(System.in);
        int x,y;
        do {
            String shotCoordinates = scanner.next();
            x = Character.getNumericValue(shotCoordinates.charAt(0)) - 9;
            y = Integer.parseInt(shotCoordinates.substring(1));
        } while (x < 1 || x > 10 || y < 1 || y > 10);
        for(int i=0;i<10;i++) {
            for (int j = 0; j < 10; j++) {
                if (i == x - 1 && j == y - 1) {

                    if (playBoard[i][j]=='O') {
                        playBoard[i][j] = 'X';
                        fogPlayBoard[i][j]='X';
                        shipSinkBoard[i][j]='X';
                        boolean sankShip=checkShip(shipSinkBoard);
                        if(sankShip &&gameFinished) {

                            System.out.println("\nYou sank the last ship. You won. Congratulations!");
                            return;
                        }else if(sankShip){
                            System.out.println("\nYou sank a ship! Specify a new target:");
                            break;
                        }
                        else{
                            System.out.println("\nYou hit a ship!  Try again:");
                            break;}
                    } else if(playBoard[i][j] == 'X'){
                        System.out.println("\nYou hit a ship!  Try again:");
                        break;
                    } else{
                        playBoard[i][j] = 'M';
                        fogPlayBoard[i][j]='M';
                        System.out.println("\nYou missed! Try again:");
                        break;
                    }
                }
            }
        }
    }

    public void coordinateShip(String shipName, int l, char[][] playBoard) {
        System.out.println("\nEnter the coordinates of the "+shipName+" ("+(l+1)+" cells):");
        while(true) {
            int[] coordinates = findCoordinates();
            if (!checkLength(coordinates, l)){
                System.out.println("Error! Wrong length of the "+shipName+"! Try again:");
            }
            else if (coordinates[0]!= coordinates[2]&& coordinates[1]!= coordinates[3]){
                System.out.println("Error! Wrong ship location! Try again:");
            }
            else if (!checkClose(coordinates, playBoard)){
                System.out.println("Error! You placed it too close to another one. Try again:");
            }
            else {
                createPlayboard(playBoard, coordinates,shipName);
                showBoard(playBoard);
                break;
            }
        }
    }

    public static boolean checkClose(int [] coordinates, char [][] playboard){
        int x1=coordinates[0];
        int y1=coordinates[1];
        int x2=coordinates[2];
        int y2=coordinates[3];
        boolean check=true;
        for(int i=0;i<10;i++) {
            for (int j = 0; j < 10; j++) {
                if(playboard[i][j]=='O'){
                    if((x1==x2&&i==x1-1&&j>=(y1-2)&&j<=(y2))||(x1==x2&&i==x1-1&&j<=(y1)&&j>=(y2-2))) {
                        check = false;
                        break;
                    }
                    else if((y1==y2&&j==y1-1&&i>=(x1-2)&&i<=(x2))||(y1==y2&&j==y1-1&&i<=(x1)&&i>=(x2-2))) {
                        check = false;
                        break;
                    } else if((x1==x2&&i==x1-2&&j>=(y1-1)&&j<=(y2-1))||(x1==x2&&i==x1-2&&j<=(y1-1)&&j>=(y2-1))) {
                        check = false;
                        break;
                    }else if((x1==x2&&i==x1&&j>=(y1-1)&&j<=(y2-1))||(x1==x2&&i==x1&&j<=(y1-1)&&j>=(y2-1))) {
                        check = false;
                        break;}
                    else if((y1==y2&&j==y1-2&&i>=(x1-1)&&i<=(x2-1))||(y1==y2&&j==y1-2&&i<=(x1-1)&&i>=(x2-1))) {
                        check = false;
                        break;
                    }else if((y1==y2&&j==y1&&i>=(x1-1)&&i<=(x2-1))||(y1==y2&&j==y1&&i<=(x1-1)&&i>=(x2-1))) {
                        check = false;
                        break;
                    }
                }
            }
        }
        return check;
    }
    public  int[] findCoordinates() {
        Scanner scanner=new Scanner(System.in);
        String x1y1 = scanner.next();
        String x2y2 = scanner.next();
        coordinates[0] = Character.getNumericValue(x1y1.charAt(0)) - 9;
        coordinates[1] = Integer.parseInt(x1y1.substring(1));
        coordinates[2] = Character.getNumericValue(x2y2.charAt(0)) - 9;
        coordinates[3] = Integer.parseInt(x2y2.substring(1));
        return coordinates;
    }

    public void createPlayboard(char[][] playBoard, int[] coordinates, String shipName) {
        int x1=coordinates[0];
        int y1=coordinates[1];
        int x2=coordinates[2];
        int y2=coordinates[3];
        for(int i=0;i<10;i++) {
            for (int j = 0; j < 10; j++) {
                if(((x1-1)==i&&(x2-1)==i&&j>=(y1-1)&&j<=(y2-1))||((x1-1)==i&&(x2-1)==i&&j<=(y1-1)&&j>=(y2-1))) {
                    playBoard[i][j] = 'O';
                    switch (shipName){
                        case "Aircraft Carrier":
                            shipSinkBoard[i][j]='A';
                            break;
                        case "Battleship":
                            shipSinkBoard[i][j]='B';
                            break;
                        case "Submarine":
                            shipSinkBoard[i][j]='C';
                            break;
                        case "Cruiser":
                            shipSinkBoard[i][j]='D';
                            break;
                        case "Destroyer":
                            shipSinkBoard[i][j]='E';
                            break;
                    }
                } else if(((y1-1)==j&&(y2-1)==j&&i>=(x1-1)&&i<=(x2-1))||((y1-1)==j&&(y2-1)==j&&i<=(x1-1)&&i>=(x2-1))) {
                    playBoard[i][j] = 'O';
                    playBoard[i][j] = 'O';
                    switch (shipName){
                        case "Aircraft Carrier":
                            shipSinkBoard[i][j]='A';
                            break;
                        case "Battleship":
                            shipSinkBoard[i][j]='B';
                            break;
                        case "Submarine":
                            shipSinkBoard[i][j]='C';
                            break;
                        case "Cruiser":
                            shipSinkBoard[i][j]='D';
                            break;
                        case "Destroyer":
                            shipSinkBoard[i][j]='E';
                            break;
                    }
                }else if(playBoard[i][j]=='O') {
                    continue;
                }
                else {
                    playBoard[i][j] = '~';
                }
            }
        }
    }
    public static void showBoard(char [][] playBoard) {
        System.out.print("  ");
        for (int i = 1; i <= 10; i++)
            System.out.print(i + " ");

        for (int i = 0; i < 10; i++) {
            System.out.print("\n" + (char) ('A' + i) + " ");
            for (int j = 0; j < 10; j++) {
                System.out.print(playBoard[i][j] + " ");
            }
        }
    }

    public char[][] getPlayBoard() {
        return playBoard;
    }
    public char[][] getFogPlayBoard() {
        return fogPlayBoard;
    }
    public int[] getCoordinates() {
        return coordinates;
    }
}



