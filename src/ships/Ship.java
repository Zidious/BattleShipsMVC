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
    private final ArrayList<int[]> missileHits;

    public Ship() {
        nMissilesReceived = 0;
        missileHits = new ArrayList<>();
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
        missileHits.add(coords);
    }

    public ArrayList<int[]> getHitCoordinates() {
        return missileHits;
    }
}
