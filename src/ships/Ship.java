package ships;

import java.util.ArrayList;

public class Ship {

    // Ship Length
    protected int nShipLength;
    // Ship Name
    protected String nShipName;
    // Missile Tracker
    protected int nMissilesReceived;
    // Array list to store all the [x] and [y] coords of hits on each type of ship
    private final ArrayList<int[]> nMissileHits;
    // Array list to hold [x] [y] coords for ship configuration by user
    private final ArrayList<int[]> nShipConfigurationCoordinates;

    public Ship() {
        nMissilesReceived = 0;
        nMissileHits = new ArrayList<>();
        nShipConfigurationCoordinates = new ArrayList<>();
    }

    public String getShipName() {
        return nShipName;
    }


    public int getShipLength() {
        return nShipLength;
    }

    public boolean isSunk() {

        return getMissilesReceived() >= getShipLength();
    }

    public int getMissilesReceived() {
        return nMissilesReceived;
    }

    public void storeMissileHitCoords(int[] coords) {
        nMissilesReceived++;
        nMissileHits.add(coords);
    }

    public void storeShipConfigurationCoords(int[] coords) {
        nShipConfigurationCoordinates.add(coords);
    }

    public boolean isShipConfigurationValid() {
        return nShipConfigurationCoordinates.size() <= getShipLength();
    }

    public ArrayList<int[]> getShipConfigurationCoordinates(){
        return nShipConfigurationCoordinates;
    }

    public ArrayList<int[]> getHitCoordinates() {
        return nMissileHits;
    }
}
