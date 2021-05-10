import ships.*;

import java.util.ArrayList;
import java.util.Observable;

public class BattleshipModel extends Observable {
    private final int MAX_X = 10;
    private final int MAX_Y = 10;
    private int[][] nGameBoard;
    private ArrayList<int[]> nSunkShipsCoords;

    // Keep track of total missiles fired
    private int nTotalMissilesFired = 0;
    // Keep track of sunk ships
    private int nTotalSunkShips = 0;

    // Declare Ships
    private Carrier nCarrier;
    private Battleship nBattleship;
    private Cruiser nCruiser;
    private Destroyer nDestroyerOne;
    private Destroyer nDestroyerTwo;

    // Ship Notation (sizes):
    // Carrier = 5   (x1)
    // Battleship = 4 (x1)
    // Cruiser = 3 (x1)
    // Destroyer = 2 (x2)
    private final int CARRIER_INDICATOR = 5;
    private final int BATTLESHIP_INDICATOR = 4;
    private final int CRUISER_INDICATOR = 3;
    private final int DESTROYER_ONE_INDICATOR = 2;
    private final int DESTROYER_TWO_INDICATOR = 1;

    // Store [x] [y] coordinates
    private int nMissileSentX;
    private int nMissileSentY;


    /*
     gameBoard notation:
     7 - hit
     8 - miss
     9 - sunk
    */
    private final int WATER_INDICATOR = 0;
    private final int HIT_INDICATOR = 7;
    private final int MISS_INDICATOR = 8;
    private final int SUNK_INDICATOR = 9;

    public BattleshipModel() {
        initialise();
    }

    public void change() {
        detectMissilesReceivedOnShips();
//        displayBoard();
        setChanged();
        notifyObservers();
    }

    public void initialise() {
        // Initialise ships
        nCarrier = new Carrier();
        nBattleship = new Battleship();
        nCruiser = new Cruiser();
        nDestroyerOne = new Destroyer();
        nDestroyerTwo = new Destroyer();
        // Initialise board with 10x10 board
        nGameBoard = new int[MAX_X][MAX_Y];
        nSunkShipsCoords = new ArrayList<>();
        defaultPlacementOfShips();
        setChanged();
        notifyObservers();
    }

    public int[][] getGameBoard() {
        return nGameBoard;
    }

    public void defaultPlacementOfShips() {
        // Placing CARRIER
        nGameBoard[1][1] = CARRIER_INDICATOR;
        nGameBoard[1][2] = CARRIER_INDICATOR;
        nGameBoard[1][3] = CARRIER_INDICATOR;
        nGameBoard[1][4] = CARRIER_INDICATOR;
        nGameBoard[1][5] = CARRIER_INDICATOR;
        ;

        // Placing Battleship
        nGameBoard[2][8] = BATTLESHIP_INDICATOR;
        nGameBoard[3][8] = BATTLESHIP_INDICATOR;
        nGameBoard[4][8] = BATTLESHIP_INDICATOR;
        nGameBoard[5][8] = BATTLESHIP_INDICATOR;

        // Placing Cruiser
        nGameBoard[7][2] = CRUISER_INDICATOR;
        nGameBoard[7][3] = CRUISER_INDICATOR;
        nGameBoard[7][4] = CRUISER_INDICATOR;

        // Placing DESTROYER ONE
        nGameBoard[4][1] = DESTROYER_ONE_INDICATOR;
        nGameBoard[5][1] = DESTROYER_ONE_INDICATOR;

        // Placing DESTROYER TWO
        nGameBoard[8][7] = DESTROYER_TWO_INDICATOR;
        nGameBoard[8][8] = DESTROYER_TWO_INDICATOR;

    }

    // Loops over the entire array calling the logic for each type of ship
    public void detectMissilesReceivedOnShips() {
        for (int row = 0; row < nGameBoard.length; row++) {
            for (int col = 0; col < nGameBoard.length; col++) {
                battleshipHitAndSunkLogic();
                cruiserHitAndSunkLogic();
                carrierHitAndSunkLogic();
                destroyerOneHitAndSunkLogic();
                destroyerTwoHitAndSunkLogic();
            }
        }
    }

    /*
     Function that takes a Ship and the ship indicator i.e. Battleship = 4
     Checks if the missile received is a "hit"
     Stores the hit[x] and hit[y[ in an array lise for each type of ship
     Sets the hit[x] and hit[y] to 7, meaning it is hit
     Checks if the ship is sunk by checking if the missiles received is greater than the length of the ship
     If it is sunk, increment total ships sunk
     change all of the hit coords to indicate it has been sunk
     Increment total missiles fired
    */
    private void gameBoardMissileHitIndicator(Ship ship, int indicator) {
        if (nGameBoard[getMissileSentX()][getMissileSentY()] == indicator) {
            ship.storeMissileHitCoords(new int[]{getMissileSentX(), getMissileSentY()});
            nGameBoard[getMissileSentX()][getMissileSentY()] = HIT_INDICATOR;
            if (ship.isSunk()) {
                nSunkShipsCoords.addAll(ship.getHitCoordinates());
                nTotalSunkShips++;
                for (int[] coords : ship.getHitCoordinates()) {
                    nGameBoard[coords[0]][coords[1]] = SUNK_INDICATOR;
                }
            }
            nTotalMissilesFired++;
        }
    }

    /*
     Function to check missed missiles
     Check if the sent missile hit the water indicator which is 0
     If it did miss, sent it 0
     Increment total missiles fired
    */
    private void gameBoardMissileMissIndicator() {
        if (nGameBoard[getMissileSentX()][getMissileSentY()] == WATER_INDICATOR) {
            nGameBoard[getMissileSentX()][getMissileSentY()] = MISS_INDICATOR;
            nTotalMissilesFired++;
        }
    }

    // Function for each type of ship to detect hits and misses by passing their ship object and ship indicator

    private void battleshipHitAndSunkLogic() {

        gameBoardMissileHitIndicator(nBattleship, BATTLESHIP_INDICATOR);
        gameBoardMissileMissIndicator();
    }

    private void cruiserHitAndSunkLogic() {

        gameBoardMissileHitIndicator(nCruiser, CRUISER_INDICATOR);
        gameBoardMissileMissIndicator();
    }

    private void carrierHitAndSunkLogic() {

        gameBoardMissileHitIndicator(nCarrier, CARRIER_INDICATOR);
        gameBoardMissileMissIndicator();
    }

    private void destroyerOneHitAndSunkLogic() {

        gameBoardMissileHitIndicator(nDestroyerOne, DESTROYER_ONE_INDICATOR);
        gameBoardMissileMissIndicator();
    }

    private void destroyerTwoHitAndSunkLogic() {

        gameBoardMissileHitIndicator(nDestroyerTwo, DESTROYER_TWO_INDICATOR);
        gameBoardMissileMissIndicator();
    }

    // Function to check if the all the ships are sunk
    public boolean isGameOver() {
        return getTotalSunkShips() >= 5;
    }

    // Getter: sunk ships
    public int getTotalSunkShips() {
        return nTotalSunkShips;
    }

    // Getter: Sunk ships coords[]
    public ArrayList<int[]> getSunkShipsCoords() {
        return nSunkShipsCoords;
    }

    // Setter: Set missile coords[][]
    public void setMissileCoordinates(int x, int y) {
        nMissileSentX = x;
        nMissileSentY = y;

    }

    // Getter: Get coord[x] for missile
    public int getMissileSentX() {
        return nMissileSentX;
    }

    // Getter: Get coord[Y] for missile
    public int getMissileSentY() {
        return nMissileSentY;
    }

    // Function to detect missile hit
    public boolean isMissileHit() {
        return nGameBoard[getMissileSentX()][getMissileSentY()] == HIT_INDICATOR;
    }

    // Function to detect missile miss
    public boolean isMissileMiss() {
        return nGameBoard[getMissileSentX()][getMissileSentY()] == MISS_INDICATOR;
    }

    // Getter: Get return total missiles fired
    public int getTotalMissilesFired() {
        return nTotalMissilesFired;
    }

    // Getter: ASCII values for 0 - 10 to A B C etc
    public char boardLetterLabel(int i) {
        return (char) (i + 64);
    }

    // Function to display the CLI board based on each indicator
    public void displayBoard() {
        System.out.print("   ");
        for (int x = 0; x < nGameBoard.length; x++) {
            System.out.printf("%3c", boardLetterLabel(x + 1));
        }
        System.out.println();
        for (int row = 0; row < nGameBoard.length; row++) {
            System.out.printf("%3d", row + 1);
            for (int col = 0; col < nGameBoard.length; col++) {
                if (nGameBoard[row][col] == WATER_INDICATOR) {
                    System.out.printf("%3c", '~');
                } else if (nGameBoard[row][col] == MISS_INDICATOR) {
                    System.out.printf("%3c", 'M');
                } else if (nGameBoard[row][col] == HIT_INDICATOR) {
                    System.out.printf("%3c", 'H');
                } else if (nGameBoard[row][col] == SUNK_INDICATOR) {
                    System.out.printf("%3c", 'S');
                } else {
                    System.out.printf("%3c", '~');

                }
            }
            System.out.println();
        }
    }


    // Function to convert the char input for CLI to column number A = 0, B = 1 etc
    public int getNumber(char input) {
        int col = 0;
        switch (input) {
            case 'A':
                col = 0;
                break;
            case 'B':
                col = 1;
                break;
            case 'C':
                col = 2;
                break;
            case 'D':
                col = 3;
                break;
            case 'E':
                col = 4;
                break;
            case 'F':
                col = 5;
                break;
            case 'G':
                col = 6;
                break;
            case 'H':
                col = 7;
                break;
            case 'I':
                col = 8;
                break;
            case 'J':
                col = 9;

        }
        return col;
    }
}
