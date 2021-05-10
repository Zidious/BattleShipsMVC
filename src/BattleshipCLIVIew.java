import java.util.Scanner;

public class BattleshipCLIVIew {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BattleshipModel battleshipModel = new BattleshipModel();
        battleshipModel.displayBoard();

        do {
            System.out.print("Enter Missile Coordinates: ");
            String input = scanner.next().toUpperCase();

            int col = battleshipModel.getNumber(input.charAt(0));
            int row = Integer.parseInt(String.valueOf(input.split(String.valueOf(input.charAt(0)))[1])) - 1;
            System.out.println(row);

            battleshipModel.setMissileCoordinates(row, col);
            battleshipModel.detectMissilesReceivedOnShips();
            battleshipModel.displayBoard();


        } while (!battleshipModel.isGameOver());
        System.out.println("Fleet Destroyed. Total Missiles Fired: " + battleshipModel.getTotalMissilesFired());

    }
}
