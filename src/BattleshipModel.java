import java.util.Arrays;
import java.util.Observable;

public class BattleshipModel extends Observable {
    private final int MAX_X = 10;
    private final int MAX_Y = 10;
    private int[][] nGameBoard;

    // Keep track of total missiles fired
    private int nTotalMissilesFired = 0;

    // Ship Notation (sizes):
    // Carrier = 5   (x1)
    // Battleship = 4 (x1)
    // Cruiser = 3 (x1)
    // Destroyer = 2 (x2)

    // Ship Length
    private final int CARRIER = 5;
    private final int BATTLESHIP = 4;
    private final int CRUISER = 3;
    private final int DESTROYERONE = 2;
    private final int DESTROYERTWO = 1;

    /*
     gameBoard notation ?:
     M - miss
     H - hit
     ~ - water
     X - sunk
    */

    public BattleshipModel() {
        // Initialise board with 10x10 board
        nGameBoard = new int[MAX_X][MAX_Y];
        initialise();
    }

    public void change() {

        setChanged();
        notifyObservers();
    }

    public void initialise() {
        placeShips();
        setChanged();
        notifyObservers();
    }

    public int[][] getGameBoard() {
        return nGameBoard;
    }

    public void placeShips() {
        // Placing CARRIER
        nGameBoard[1][1] = CARRIER;
        nGameBoard[1][2] = CARRIER;
        nGameBoard[1][3] = CARRIER;
        nGameBoard[1][4] = CARRIER;
        nGameBoard[1][5] = CARRIER;

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

    public int getTotalMissilesFired() {
        return nTotalMissilesFired;
    }

}
