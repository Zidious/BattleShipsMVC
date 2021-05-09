import ships.Carrier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;

public class BattleshipModel extends Observable {
    private final int MAX_X = 10;
    private final int MAX_Y = 10;
    private int[][] nGameBoard;
    private ArrayList<int[]> nSunkShipsCoords;

    // Keep track of total missiles fired
    private int nTotalMissilesFired = 0;

    // Ship Notation (sizes):
    // Carrier = 5   (x1)
    // Battleship = 4 (x1)
    // Cruiser = 3 (x1)
    // Destroyer = 2 (x2)

    // Ship Length
    private Carrier carrier;
    private final int CARRIER_INDICATOR = 5;
    private final int BATTLESHIP = 4;
    private final int CRUISER = 3;
    private final int DESTROYERONE = 2;
    private final int DESTROYERTWO = 1;

    private int nMissileSentX;
    private int nMissileSentY;


    /*
     gameBoard notation:
     0 - water
     7 - hit
     8 - miss
     9 - sunk
    */

    private final int HIT_INDICATOR = 7;
    private final int MISS_INDICATOR = 8;
    private final int SUNK_INDICATOR = 9;

    public BattleshipModel() {
        initialise();
    }

    public void change() {
        detectMissilesReceivedOnShips();
        displayBoard();
        setChanged();
        notifyObservers();
    }

    public void initialise() {
        carrier = new Carrier();
        // Initialise board with 10x10 board
        nGameBoard = new int[MAX_X][MAX_Y];
        nSunkShipsCoords = new ArrayList<>();
        placeShips();
        setChanged();
        notifyObservers();
    }

    public int[][] getGameBoard() {
        return nGameBoard;
    }

    public void placeShips() {
        // Placing CARRIER
        nGameBoard[1][1] = CARRIER_INDICATOR;
        nGameBoard[1][2] = CARRIER_INDICATOR;
        nGameBoard[1][3] = CARRIER_INDICATOR;
        nGameBoard[1][4] = CARRIER_INDICATOR;
        nGameBoard[1][5] = CARRIER_INDICATOR;
        ;

        // Placing Battleship
        nGameBoard[2][8] = BATTLESHIP;
        nGameBoard[3][8] = BATTLESHIP;
        nGameBoard[4][8] = BATTLESHIP;
        nGameBoard[5][8] = BATTLESHIP;

        // Placing Cruiser
        nGameBoard[7][2] = CRUISER;
        nGameBoard[7][3] = CRUISER;
        nGameBoard[7][4] = CRUISER;

        // Placing DESTROYER ONE
        nGameBoard[4][1] = DESTROYERONE;
        nGameBoard[5][1] = DESTROYERONE;

        // Placing DESTROYER TWO
        nGameBoard[8][7] = DESTROYERTWO;
        nGameBoard[8][8] = DESTROYERTWO;

    }

    public void detectMissilesReceivedOnShips() {
        for (int x = 0; x < nGameBoard.length; x++) {
            for (int y = 0; y < nGameBoard.length; y++) {
                if (nGameBoard[getMissileSentX()][getMissileSentY()] == CARRIER_INDICATOR) {
                    carrier.storeMissileHitCoords(new int[]{getMissileSentX(), getMissileSentY()});
                    nGameBoard[getMissileSentX()][getMissileSentY()] = HIT_INDICATOR;
                    // Marks it as hit constant for hit
                    nTotalMissilesFired++;
                    if (carrier.isSunk()) {
                      nSunkShipsCoords.addAll(carrier.getHitCoordinates());
                    }
                }
            }
        }
    }


    public ArrayList<int[]> getSunkShipsCoords() {
        return nSunkShipsCoords;
    }

    public void setMissileCoordinates(int x, int y) {
        nMissileSentX = x;
        nMissileSentY = y;

    }

    public int getMissileSentX() {
        return nMissileSentX;
    }

    public int getMissileSentY() {
        return nMissileSentY;
    }

    public boolean isMissileHit() {
        return nGameBoard[getMissileSentX()][getMissileSentY()] == (HIT_INDICATOR);
    }

    public int getTotalMissilesFired() {
        return nTotalMissilesFired;
    }

    public void displayBoard() {
        // for debugging
        System.out.println(Arrays.deepToString(nGameBoard)
                .replace("], ", "]\n")
                .replace("[[", "[")
                .replace("]]", "]"));
    }


    public int getNumber(char input) {
        int row = 1;
        switch (input) {
            case 'A':
                row = 1;
                break;
            case 'B':
                row = 2;
                break;
            case 'C':
                row = 3;
                break;
            case 'D':
                row = 4;
                break;
            case 'E':
                row = 5;
                break;
            case 'F':
                row = 6;
                break;
            case 'G':
                row = 7;
                break;
            case 'H':
                row = 8;
                break;
            case 'I':
                row = 9;
                break;
            case 'J':
                row = 10;

        }
        return row;
    }


}
