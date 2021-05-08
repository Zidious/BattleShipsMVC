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

    // Ships
    private final int CARRIER = 5;
    private final int BATTLESHIP = 4;
    private final int CRUISER = 3;
    private final int DESTROYER = 2;

    /*
     gameBoard notation:
     o - miss
     x - hit
     ~ - water
    */






    public BattleshipModel() {
        // Initialise board with 10x10 board
        nGameBoard = new int[MAX_X][MAX_Y];
        fillBoard();
        displayBoard();
        initialise();
    }

    public void change() {

        setChanged();
        notifyObservers();
    }

    public void initialise() {

        displayBoard();

        setChanged();
        notifyObservers();
    }

    // Fill board will all 0s
    public void fillBoard() {
        for (int i = 0; i < nGameBoard.length; i++) {
            for (int j = 0; j < nGameBoard.length; j++) {
                nGameBoard[i][j] = 0;
            }
        }
//        // for debugging
//        System.out.println(Arrays.deepToString(nGameBoard)
//                .replace("], ", "]\n")
//                .replace("[[", "[")
//                .replace("]]", "]"));

    }

    public void displayBoard() {
        char water = '~';
        char miss = 'o';
        char hit = 'x';

        // loop through columns of current row
        for (int column = 0; column < nGameBoard[0].length; column++) {
            if (nGameBoard[MAX_X][column] == 0)
                System.out.printf("%3c", water);
            if (nGameBoard[MAX_X][column] == 1)
                System.out.printf("%3c", miss);
            if (nGameBoard[MAX_X][column] == 2)
                System.out.printf("%3c", hit);
            if (nGameBoard[MAX_X][column] == 'A' || nGameBoard[MAX_X][column] == 'B' || nGameBoard[MAX_X][column] == 'D' || nGameBoard[MAX_X][column] == 'S' || nGameBoard[MAX_X][column] == 'P')
                System.out.printf("%3c", water);

        }

        System.out.println();


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
