import java.util.Scanner;

public class BattleshipCLIVIew {

    private static void run() {
        Scanner scanner = new Scanner(System.in);
        BattleshipModel battleshipModel = new BattleshipModel();

        System.out.print("Load Default Configuration (Y) or Fleet File Configuration (N)?");
        boolean fleetSelection = (scanner.next().equalsIgnoreCase("Y"));
        battleshipModel.setShipConfigurationDecision(fleetSelection);
        battleshipModel.loadShipConfiguration();
        do {


            battleshipModel.displayBoard();

            System.out.print("Enter Missile Coordinates: ");
            String input = scanner.next();

            int col = battleshipModel.convertUserInputToColumn(input.charAt(0));
            int row = Integer.parseInt(String.valueOf(input.split(String.valueOf(input.charAt(0)))[1])) - 1;

            battleshipModel.setIncomingMissileCoordinates(row, col);
            battleshipModel.detectMissilesReceivedOnShips();
            battleshipModel.displayBoard();


        } while (!battleshipModel.isGameOver());

        System.out.println("Fleet Destroyed. Total Missiles Fired: " + battleshipModel.getTotalMissilesFired());
    }

    public static void main(String[] args) {
        run();
    }
}
