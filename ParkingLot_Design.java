public enum VehicleSize {
    Compact(1);
    Large(2);
    
    private final int size;
    
    VehicleSize(int size) {
        this.size = size;
    }
    
    public int getSize() {
        return size;
    }
}


public abstract class Vehicle {
    public abstract VehicleSize getSize();
}


public class Car extends Vehicle {
    @Override
    public VehicleSize getSize() {
        return VehicleSize.Compact;
    }
}


public class Truck extends Vehicle {
    @Override
    public VehicleSize getSize() {
        return VehicleSize.Large;
    }
}


public class ParkingSpot {
    private final VehicleSize size;
    private Vehicle currentVahicle;
    
    ParkingSpot(VehicleSize size) {
        this.size = size;
    }

    boolean fit(Vehicle v) {
        return currentVehicle == null && size.getSize() >= v.getSize().getSize();
    }

    void park(Vehicle v) {
        currentVehicle = v;
    }
    
    void leave() {
        currentVehicle = null;
    }

    Vehicle getVehicle() {
        return currentVehicle;
    }
}


public class Level {
    private final List<ParkingSpot> spots;

    Level(int numOfSpots) {
        List<ParkingSpots> list = new ArrayList<>(numOfSpots);
        
        int i = 0;
        for (; i < numOfSpots / 2; i++) {
            list.add(new ParkingSpot(VehicleSize.Compact));
        }
        for (; i < numOfSpots; i++) {
            list.add(new ParkingSpot(VehicleSize.Large));
        }
        
        spots = Collections.unmodifiableList(list); 
    }

    boolean hasSpot(Vehicle v) {
        for (ParkingSpot s : spots) {
            if (s.fit(v)) {
                return true;
            }
        }
        return false;
    }
    
    boolean park(Vehicle v) {
        for (ParkingSpot s : spots) {
            if (s.fit(v)) {
                s.park(v);
                return true;
            }
        }
        return false;
    }
    
    boolean leave(Vehicle v) {
        for (ParkingSpot s : spots) {
            if (s.getVehicle() == v) {
                s.leave();
                return true;
            }
        }
        return false;
    }
}


public class ParkingLot {
    private final Level[] levels;

    public ParkingLot(int numLevels, int numSpotsPerLevel) {
        levels = new Level[numLevels];
        for (int i = 0; i < numLevels; i++) {
            levels[i] = new Level(numSpotsPerLevel);
        }
    }

    public boolean hasSpot(Vehicle v) {
        for (Level l : levels) {
            if (l.hasSpot(v)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean park(Vehicle v) {
        for (Level l : levels) {
            if (l.park(v)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean leave(Vehicle v) {
        for (Level l : levels) {
            if (l.leave(v)) {
                return true;
            }
        }
        return false;
    }
}


// main function
public class Test {

    public static void main(String[] args) {
        ParkingLot lot = new ParkingLot(4, 10);
        
        List<Vehicle> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            final Vehicle v = i % 2 == 0 ? new Car() : new Truck();
            list.add(v);
            boolean hasSpot = lot.hasSpot(v);
            
            if (i < 40) {
                // make sure to enable "assert" if using it for test
                assert hasSpot;
                assert lot.park(v);
            } else {
                assert !hasSpot;
                assert !lot.park(v);
            }
        }
        
        assert list.size() == 50;
        int i = 0;
        for (Vehicle v : list) {
            assert i >= 40 || lot.leave(v);
            i ++;
        }
    }
}
